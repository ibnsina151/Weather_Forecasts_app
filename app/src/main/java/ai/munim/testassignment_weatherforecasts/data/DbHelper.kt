package ai.munim.testassignment_weatherforecasts.data

import ai.munim.testassignment_weatherforecasts.dbhelper.DbConstants
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import androidx.lifecycle.LiveData
import androidx.room.*

interface DbHelper {

    fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>>

    fun saveWeatherInfo(entity: WeatherForecastsEntity)

    fun deleteWeatherInfo(entity: WeatherForecastsEntity)

    fun deleteAllWeatherInfo()

    fun updateWeatherInfo(entity: WeatherForecastsEntity)
}