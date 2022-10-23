package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class RainDto(
    @SerializedName("3h")
    var _3h: Double? = null)
