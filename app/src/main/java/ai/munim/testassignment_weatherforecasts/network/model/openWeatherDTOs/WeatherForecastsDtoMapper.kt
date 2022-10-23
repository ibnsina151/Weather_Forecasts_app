package ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs

import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.CityModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherModel
import ai.munim.testassignment_weatherforecasts.domain.utils.DomainMapper
import ai.munim.testassignment_weatherforecasts.network.response.WeatherForecastsResponse
import ai.munim.testassignment_weatherforecasts.utils.CommonTasks.Companion.getListModel

class WeatherForecastsDtoMapper : DomainMapper<WeatherForecastsResponse, WeatherForecastsModel> {

    override fun mapToDomainModel(model: WeatherForecastsResponse): WeatherForecastsModel {
        return WeatherForecastsModel(
            cod = model.cod,
            message = model.message!!,
            cnt = model.cnt!!,
            list = getListModel(model.list),
            city = CityModel(
                model.city?.id,
                model.city?.name,
                model.city?.country,
                model.city?.population,
                model.city?.timezone,
                model.city?.sunrise,
                model.city?.sunset
            )
        )
    }






    override fun mapFromDomainModel(domainModel: WeatherForecastsModel): WeatherForecastsResponse {
        return WeatherForecastsResponse(
            cod = null ,
            message = null,
            cnt = null,
            list = ArrayList(),
            city = null
        )
    }

    fun toDomainList(initial: List<WeatherForecastsResponse>): List<WeatherForecastsModel>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<WeatherForecastsModel>): List<WeatherForecastsResponse>{
        return initial.map { mapFromDomainModel(it) }
    }


}
