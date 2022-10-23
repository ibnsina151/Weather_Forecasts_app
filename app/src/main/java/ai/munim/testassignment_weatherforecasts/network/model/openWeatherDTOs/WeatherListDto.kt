package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import com.google.gson.annotations.SerializedName

data class WeatherListDto(
    @SerializedName("dt"         ) var dt         : Int?               = null,
    @SerializedName("main"       ) var main       : MainDto?              = MainDto(),
    @SerializedName("weather"    ) var weather    : ArrayList<WeatherDto> = arrayListOf(),
    @SerializedName("clouds"     ) var clouds     : CloudsDto?            = CloudsDto(),
    @SerializedName("wind"       ) var wind       : WindDto?              = WindDto(),
    @SerializedName("visibility" ) var visibility : Int?               = null,
    @SerializedName("pop"        ) var pop        : Double?            = null,
    @SerializedName("rain"       ) var rain       : RainDto?              = RainDto(),
    @SerializedName("sys"        ) var sys        : SysDto?               = SysDto(),
    @SerializedName("dt_txt"     ) var dtTxt      : String?            = null)
