package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class SysDto (

  @SerializedName("pod" ) var pod : String? = null

)