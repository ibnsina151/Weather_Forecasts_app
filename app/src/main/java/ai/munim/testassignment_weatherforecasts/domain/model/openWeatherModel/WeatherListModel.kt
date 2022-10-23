package ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel

import com.google.gson.annotations.SerializedName

data class WeatherListModel(
    var dt         : Int? = null,

    var temp      : Double? = null,
    var feelsLike : Double? = null,
    var tempMin   : Double? = null,
    var tempMax   : Double? = null,
    var pressure  : Int?    = null,
    var seaLevel  : Int?    = null,
    var grndLevel : Int?    = null,
    var humidity  : Int?    = null,
    var tempKf    : Double? = null,

    var weather    : ArrayList<WeatherModel> = arrayListOf(),

    var all : Int? = null,
    private var speed: Double? = null,
    val deg: Int? = null,
    val gust: Double? = null,
    var visibility : Int?,
    var _3h: Double? = null,
    var dtTxt      : String?            = null)
