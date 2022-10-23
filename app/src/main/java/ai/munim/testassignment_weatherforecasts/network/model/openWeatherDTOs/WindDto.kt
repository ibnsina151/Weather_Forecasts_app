package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("speed")
    var speed: Double? = null,
    @SerializedName("deg")
    val deg: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null
    )
