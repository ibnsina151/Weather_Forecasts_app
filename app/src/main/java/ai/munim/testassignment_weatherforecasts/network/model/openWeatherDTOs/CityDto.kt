package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("coord")
    var coord: CoordDto,
    @SerializedName("country")
    var country: String,
    @SerializedName("population")
    var population: Int,
    @SerializedName("timezone")
    var timezone: Int,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int)
