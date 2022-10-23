package ai.munim.testassignment_weatherforecasts.viewmodels

import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.repositories.HomeRepository
import ai.munim.testassignment_weatherforecasts.helper.LocationManager
import ai.retailai.bipononsupport.utils.Resource
import ai.retailai.bipononsupport.utils.Status
import android.location.Location
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

const val STATE_KEY_WEATHER_FORECASTS = "STATE_KEY_WEATHER_FORECASTS";

@HiltViewModel
class HomeViewModel
    @Inject constructor(
        private val repository: HomeRepository)
    : ViewModel(){

    lateinit var mLocation:Location
    lateinit var status: MutableLiveData<Status>

    init {

    }


    fun getLocationManager(): LocationManager?{
        return repository.getLocationManager()
    }

    fun getWeatherForecasts() : LiveData<Resource<WeatherForecastsModel>> {
        return repository.getWeatherForecasts()
    }

    fun saveLat(lat: Double) {
        repository.saveLat(lat)
    }

    fun saveLng(lng: Double) {
        repository.saveLng(lng)
    }

    fun getLat(): Double {
        return repository.getLat()
    }

    fun getLng(): Double {
        return repository.getLng()
    }

    fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>>{
        return repository.getWeatherInfo(date)

    }

}
