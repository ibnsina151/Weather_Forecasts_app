package ai.munim.testassignment_weatherforecasts.utils

import ai.munim.testassignment_weatherforecasts.dbhelper.entity.WeatherForecastsEntity
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel

class Conversion {

    companion object {

        fun getWeatherEntity(responseModel:WeatherForecastsModel):WeatherForecastsEntity {
            return WeatherForecastsEntity(
                System.currentTimeMillis(),
                CommonTasks.getCurrentDay(),
                responseModel.cod,
                responseModel.message,
                responseModel.cnt,
                responseModel.list,
                responseModel.city
            )

        }

        fun getWeatherForecastsModel(entity: WeatherForecastsEntity):WeatherForecastsModel {
            return WeatherForecastsModel(
                entity.cod,
                entity.message,
                entity.cnt,
                entity.list,
                entity.city
            )

        }
    }

}