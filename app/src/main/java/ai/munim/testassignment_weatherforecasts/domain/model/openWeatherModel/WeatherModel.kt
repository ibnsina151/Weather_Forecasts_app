package ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    var id: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null)
