package ai.munim.testassignment_weatherforecasts.domain.repositories.impl

import ai.munim.testassignment_weatherforecasts.data.DataManager
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.repositories.HomeRepository
import ai.munim.testassignment_weatherforecasts.helper.LocationManager
import ai.munim.testassignment_weatherforecasts.network.interfaces.OpenWeatherRetrofitService
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherForecastsDtoMapper
import ai.munim.testassignment_weatherforecasts.network.response.WeatherForecastsResponse
import ai.munim.testassignment_weatherforecasts.utils.Conversion
import ai.retailai.bipononsupport.utils.Resource
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepositoryImpl(
    private val openWeatherRetrofitService: OpenWeatherRetrofitService,
    private val locationManager: LocationManager,
    private val dataManager: DataManager,
    private val mapper:WeatherForecastsDtoMapper
) : HomeRepository {

    private lateinit var weatherForecastsModel: MutableLiveData<Resource<WeatherForecastsModel>>

    override fun getLocationManager(): LocationManager {
        return locationManager;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getWeatherForecasts(): LiveData<Resource<WeatherForecastsModel>> {
        weatherForecastsModel = MutableLiveData()
        weatherForecastsModel.setValue(Resource.loading(null))

        openWeatherRetrofitService.getFiveDaysForecastData(dataManager.getLat(),dataManager.getLng(),
            "0a7a164d3e8198ca4d83a377654599a5").enqueue(object: Callback<WeatherForecastsResponse> {
            override fun onFailure(call: Call<WeatherForecastsResponse>, t: Throwable) {
                weatherForecastsModel.value = Resource.error("error occur:- "+t.message,null)
            }

            override fun onResponse(
                call: Call<WeatherForecastsResponse>,
                response: Response<WeatherForecastsResponse>
            ) {

                val data = response.body()
                val msg = data!!.message
                if (response.isSuccessful){
                    saveWeatherInfo(Conversion.getWeatherEntity(mapper.mapToDomainModel(data)))
                    weatherForecastsModel.value = Resource.success(mapper.mapToDomainModel(data))
                }else{
                    weatherForecastsModel.value = Resource.error("error occur:- "+msg,null)
                }
            }
        })

        return weatherForecastsModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getWeatherForecasts_e(): WeatherForecastsModel {
        return mapper.mapToDomainModel(openWeatherRetrofitService.getFiveDaysForecastData_e(dataManager.getLat(),
            dataManager.getLng(),
            "0a7a164d3e8198ca4d83a377654599a5"))
    }

    override fun saveLat(lat: Double) {
        dataManager.saveLat(lat)
    }

    override fun saveLng(lng: Double) {
        dataManager.saveLng(lng)
    }

    override fun getLat(): Double {
        return dataManager.getLat()
    }

    override fun getLng(): Double {
        return dataManager.getLng()
    }

    override fun getWeatherInfo(date: kotlin.String): LiveData<List<WeatherForecastsEntity>> {
        return dataManager.getWeatherInfo(date)
    }

    override fun saveWeatherInfo(entity: WeatherForecastsEntity) {
        CoroutineScope(IO).launch {
            dataManager.saveWeatherInfo(entity)
        }
    }

    override fun deleteWeatherInfo(entity: WeatherForecastsEntity) {
        CoroutineScope(IO).launch { dataManager.deleteWeatherInfo(entity) }
    }

    override fun deleteAllWeatherInfo() {
        CoroutineScope(IO).launch { dataManager.deleteAllWeatherInfo() }
    }

    override fun updateWeatherInfo(entity: WeatherForecastsEntity) {
        CoroutineScope(IO).launch {  dataManager.updateWeatherInfo(entity) }
    }


}

