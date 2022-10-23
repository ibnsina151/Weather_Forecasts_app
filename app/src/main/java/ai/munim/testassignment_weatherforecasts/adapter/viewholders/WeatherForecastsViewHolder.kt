package ai.munim.testassignment_weatherforecasts.adapter.viewholders

import ai.munim.testassignment_weatherforecasts.R
import ai.munim.testassignment_weatherforecasts.databinding.ItemViewerWeatherInfoDetailsBinding
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import ai.munim.testassignment_weatherforecasts.utils.CommonTasks
import ai.munim.testassignment_weatherforecasts.utils.Constants
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WeatherForecastsViewHolder(
    private val mBinding: ItemViewerWeatherInfoDetailsBinding
) : RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {

    private lateinit var mWeatherListModel: WeatherListModel

    fun bindTo(item: WeatherListModel) {
        mWeatherListModel = item;

        mBinding.txtDate.setText(CommonTasks.getDateTimeFromTimestamp(item.dt))
        mBinding.txtHumidity.setText(String.format(itemView.context.getString(R.string.txt_humidity),item.humidity))
        mBinding.txtTempRange.setText(String.format(itemView.context.getString(R.string.txt_temp_min_max),Math.round(
            item.tempMin?.minus(Constants.kelvin) ?:0.0),
            item.tempMax?.minus(Constants.kelvin) ?:0.0))

        mBinding.ivWeatherCondition.setImageDrawable(item.weather.get(0).icon?.let {
            CommonTasks.getImage(
                it,itemView.context)
        })


    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}