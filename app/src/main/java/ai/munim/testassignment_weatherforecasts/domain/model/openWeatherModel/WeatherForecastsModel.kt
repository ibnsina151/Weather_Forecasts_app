package ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel

import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.CityDto
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherListDto

data class WeatherForecastsModel(
    val cod     : String?,
    val message : Int?,
    val cnt     : Int?,
    val list    : ArrayList<WeatherListModel>? = arrayListOf(),
    val city    : CityModel?
)
