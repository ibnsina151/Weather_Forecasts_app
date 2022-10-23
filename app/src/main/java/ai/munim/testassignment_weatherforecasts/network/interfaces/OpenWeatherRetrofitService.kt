package ai.munim.testassignment_weatherforecasts.network.interfaces

import ai.munim.testassignment_weatherforecasts.network.response.WeatherForecastsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface OpenWeatherRetrofitService {

    @GET("forecast")
    fun getFiveDaysForecastData(@Query("lat") lat: Double,
                                        @Query("lon") lng: Double,
                                        @Query("appid") key: String
    ): Call<WeatherForecastsResponse>


    @GET("forecast")
    fun getFiveDaysForecastData_e(@Query("lat") lat: Double,
                                        @Query("lon") lng: Double,
                                        @Query("appid") key: String
    ): WeatherForecastsResponse



}