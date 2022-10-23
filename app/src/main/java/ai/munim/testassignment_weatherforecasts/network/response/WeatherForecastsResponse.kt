package ai.munim.testassignment_weatherforecasts.network.response

import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.CityDto
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherListDto
import com.google.gson.annotations.SerializedName

data class WeatherForecastsResponse (

    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<WeatherListDto> = arrayListOf(),
    @SerializedName("city"    ) var city    : CityDto?           = null

)

