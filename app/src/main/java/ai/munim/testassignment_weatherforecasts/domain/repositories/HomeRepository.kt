package ai.munim.testassignment_weatherforecasts.domain.repositories

import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.helper.LocationManager
import ai.retailai.bipononsupport.utils.Resource
import androidx.lifecycle.LiveData

interface HomeRepository {

    fun getLocationManager(): LocationManager?

    fun getWeatherForecasts() : LiveData<Resource<WeatherForecastsModel>>

    fun getWeatherForecasts_e() : WeatherForecastsModel

    fun saveLat(lat: Double)

    fun saveLng(lng : Double)

    fun getLat() : Double

    fun getLng() : Double

    fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>>

    fun saveWeatherInfo(entity: WeatherForecastsEntity)

    fun deleteWeatherInfo(entity: WeatherForecastsEntity)

    fun deleteAllWeatherInfo()

    fun updateWeatherInfo(entity: WeatherForecastsEntity)
}