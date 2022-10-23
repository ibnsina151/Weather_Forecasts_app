package ai.munim.testassignment_weatherforecasts.data.impl

import ai.munim.testassignment_weatherforecasts.data.DbHelper
import ai.munim.testassignment_weatherforecasts.dbhelper.database.ProjectDB
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DbHelperImpl
@Inject constructor(val projectDB: ProjectDB) : DbHelper{
    override fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>> {
        return projectDB.getWeatherDao().getWeatherInfo(date)
    }

    override fun saveWeatherInfo(entity: WeatherForecastsEntity) {
        projectDB.getWeatherDao().saveWeatherInfo(entity)
    }

    override fun deleteWeatherInfo(entity: WeatherForecastsEntity) {
        projectDB.getWeatherDao().deleteWeatherInfo(entity)
    }

    override fun deleteAllWeatherInfo() {
        projectDB.getWeatherDao().deleteAllWeatherInfo()
    }

    override fun updateWeatherInfo(entity: WeatherForecastsEntity) {
        projectDB.getWeatherDao().updateWeatherInfo(entity)
    }


}