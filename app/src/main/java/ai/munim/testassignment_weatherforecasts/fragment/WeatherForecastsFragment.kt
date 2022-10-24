package ai.munim.testassignment_weatherforecasts.fragment

import ai.munim.testassignment_weatherforecasts.R
import ai.munim.testassignment_weatherforecasts.adapter.WeatherForecastsAdapter
import ai.munim.testassignment_weatherforecasts.adapter.diffutils.WeatherForecastsItemCallBacks
import ai.munim.testassignment_weatherforecasts.databinding.FragmentWeatherForecastsInfoDetailsBinding
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import ai.munim.testassignment_weatherforecasts.helper.PermissionManager
import ai.munim.testassignment_weatherforecasts.utils.CommonTasks
import ai.munim.testassignment_weatherforecasts.utils.Conversion
import ai.munim.testassignment_weatherforecasts.viewmodels.HomeViewModel
import ai.retailai.bipononsupport.utils.Status
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherForecastsFragment() : Fragment() {

    private lateinit var mBinding: FragmentWeatherForecastsInfoDetailsBinding

    @Inject
    lateinit var mPermissionManager: PermissionManager

    private val mViewModel: HomeViewModel by viewModels()

    lateinit var weatherForecastsAdapter: WeatherForecastsAdapter

    private var data_model: ArrayList<WeatherListModel> = ArrayList()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        proceedRequest()
        subscribeRoom()
        setupList()
    }

    fun initVariable(){
        weatherForecastsAdapter = WeatherForecastsAdapter(WeatherForecastsItemCallBacks())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentWeatherForecastsInfoDetailsBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    private fun subscribeLocation() {
        mViewModel.getLocationManager()?.observe(viewLifecycleOwner, mLocationChangeObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeRoom(){
        mViewModel.getWeatherInfo(CommonTasks.getCurrentDay()).observe(viewLifecycleOwner, Observer {
            if (!it.isEmpty()){
                data_model.clear()
                data_model.addAll(Conversion.getWeatherForecastsModel(it.get(0)).list!!)
                weatherForecastsAdapter.submitList(data_model)
                weatherForecastsAdapter.notifyDataSetChanged()
                mBinding.txtRefreshing.setText(getString(R.string.txt_refreshed))

            }
        })
    }

    private fun unsubscribeLocation() {
        mViewModel.getLocationManager()?.removeObserver(mLocationChangeObserver)
    }

    private fun setupList() {
        mBinding.rvWeather.adapter = weatherForecastsAdapter
        mBinding.rvWeather.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        mBinding.rvWeather.addItemDecoration(decoration)
    }


    private val mLocationChangeObserver: Observer<Location> =
        Observer {
            mViewModel.mLocation = it
            unsubscribeLocation()
            mViewModel.saveLat(it.latitude)
            mViewModel.saveLng(it.longitude)


            if (CommonTasks.isOnline(context)){
                mViewModel.getWeatherForecasts().observe(viewLifecycleOwner, {
                    when (it?.status){
                        Status.LOADING ->{

                        }
                        Status.SUCCESS ->{
                            mBinding.txtRefreshing.setText(getString(R.string.txt_refreshed))
                        }
                        Status.ERROR ->{
                            mBinding.txtRefreshing.setText(it.message)
                        }
                        else -> {

                        }
                    }
                })
            }else{
                mBinding.txtRefreshing.setText(getString(R.string.txt_not_internet_access))
            }

        }


    private fun proceedRequest() {
        if (mPermissionManager.shouldShowLocationPermissionRationale(activity) ||
            checkPermissions()
        ) {
            subscribeLocation()
        } else {
            CommonTasks.showToast(
                context, getString(R.string.text_provide_permission),
            )
        }
    }

    private fun checkPermissions(): Boolean {
        if (!mPermissionManager.hasLocationPermission(activity)) {
            mPermissionManager.requestForLocationPermission(activity)
            return false
        }
        return true
    }


}