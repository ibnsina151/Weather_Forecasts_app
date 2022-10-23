package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null
)
