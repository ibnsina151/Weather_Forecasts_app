package ai.munim.testassignment_weatherforecasts.dbhelper.entity

import ai.munim.testassignment_weatherforecasts.dbhelper.DbConstants
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.CityModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = DbConstants.WEATHER_FORECASTS_TABLE,
    primaryKeys = [DbConstants.WEATHER_FORECASTS_TABLE_PRIMARYKEY_TIMESTAMP, DbConstants.WEATHER_FORECASTS_TABLE_PRIMARYKEY__DATE]
)
data class WeatherForecastsEntity(

    @ColumnInfo(name = "timestamp")
    var timestamp : Long,

    @ColumnInfo(name = "date")
    var date : String,

    @ColumnInfo(name = "cod")
    var cod : String?,

    @ColumnInfo(name = "message")
    var message : Int?,

    @ColumnInfo(name = "cnt")
    var cnt : Int?,

    @ColumnInfo(name = "list")
    val list    : ArrayList<WeatherListModel>? = arrayListOf(),

    @ColumnInfo(name = "city")
    val city    : CityModel?



)

