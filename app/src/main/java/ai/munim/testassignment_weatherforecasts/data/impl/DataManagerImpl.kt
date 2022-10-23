package ai.munim.testassignment_weatherforecasts.data.impl

import ai.munim.testassignment_weatherforecasts.data.DataManager
import ai.munim.testassignment_weatherforecasts.data.DbHelper
import ai.munim.testassignment_weatherforecasts.data.SharedPreferenceHelper
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DataManagerImpl
@Inject constructor(
    private val dbHelper: DbHelper,
    private val preferenceHelper: SharedPreferenceHelper
    ): DataManager {

    override fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>> {
        return dbHelper.getWeatherInfo(date)
    }

    override fun saveWeatherInfo(entity: WeatherForecastsEntity) {
        dbHelper.saveWeatherInfo(entity)
    }

    override fun deleteWeatherInfo(entity: WeatherForecastsEntity) {
        dbHelper.deleteWeatherInfo(entity)
    }

    override fun deleteAllWeatherInfo() {
        dbHelper.deleteAllWeatherInfo()
    }

    override fun updateWeatherInfo(entity: WeatherForecastsEntity) {
        dbHelper.updateWeatherInfo(entity)
    }

    override fun saveLat(lat: Double) {
        preferenceHelper.saveLat(lat)
    }

    override fun saveLng(lng: Double) {
        preferenceHelper.saveLng(lng)
    }

    override fun getLat(): Double {
        return preferenceHelper.getLat()
    }

    override fun getLng(): Double {
        return preferenceHelper.getLng()
    }
}