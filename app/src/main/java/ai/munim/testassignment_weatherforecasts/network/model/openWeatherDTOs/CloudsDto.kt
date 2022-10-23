package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all" ) var all : Int? = null
)
