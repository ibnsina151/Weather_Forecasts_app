package ai.munim.testassignment_weatherforecasts.dbhelper

import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.CityModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromWeatherListString(value: String): ArrayList<WeatherListModel> {
        val listType = object : TypeToken<ArrayList<WeatherListModel>>() {}.type
        return Gson().fromJson<ArrayList<WeatherListModel>>(value, listType)
    }

    @TypeConverter
    fun fromWeatherListArrayList(list: ArrayList<WeatherListModel>): String {
        val listType = object : TypeToken<ArrayList<WeatherListModel>>() {}.type
        return Gson().toJson(list, listType)
    }

    @TypeConverter
    fun fromCityModelString(value: String): CityModel {
        val listType = object : TypeToken<CityModel>() {}.type
        return Gson().fromJson<CityModel>(value, listType)
    }

    @TypeConverter
    fun fromCityModel(cityModel: CityModel): String {
        val listType = object : TypeToken<CityModel>() {}.type
        return Gson().toJson(cityModel, listType)
    }


}