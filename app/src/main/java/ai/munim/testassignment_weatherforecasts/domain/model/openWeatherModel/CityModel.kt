package ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel

import com.google.gson.annotations.SerializedName

data class CityModel(
    var id: Int?,
    var name: String?,
    var country: String?,
    var population: Int?,
    var timezone: Int?,
    var sunrise: Int?,
    var sunset: Int?)
