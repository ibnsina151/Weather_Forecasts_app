package ai.munim.testassignment_weatherforecasts.dbhelper.dao

import ai.munim.testassignment_weatherforecasts.dbhelper.DbConstants
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.Date

@Dao
interface WeatherDao {

    @Query("SELECT * FROM " + DbConstants.WEATHER_FORECASTS_TABLE+ " WHERE date LIKE :date ORDER BY timestamp DESC")
    fun getWeatherInfo(date: String): LiveData<List<WeatherForecastsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeatherInfo(entity: WeatherForecastsEntity)

    @Delete
    fun deleteWeatherInfo(entity: WeatherForecastsEntity)

    @Query("DELETE FROM " + DbConstants.WEATHER_FORECASTS_TABLE)
    fun deleteAllWeatherInfo()

    @Update
    fun updateWeatherInfo(entity: WeatherForecastsEntity)
}