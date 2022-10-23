package ai.munim.testassignment_weatherforecasts.dbhelper.database

import ai.munim.testassignment_weatherforecasts.dbhelper.Converters
import ai.munim.testassignment_weatherforecasts.dbhelper.DbConstants
import ai.munim.testassignment_weatherforecasts.dbhelper.dao.WeatherDao
import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [WeatherForecastsEntity::class],
    version = DbConstants.DB_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProjectDB : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao


}