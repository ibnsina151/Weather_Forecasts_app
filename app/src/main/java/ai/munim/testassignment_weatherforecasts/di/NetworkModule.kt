package ai.munim.testassignment_weatherforecasts.di

import ai.munim.testassignment_weatherforecasts.network.interfaces.OpenWeatherRetrofitService
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherForecastsDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherForecastsMapper(): WeatherForecastsDtoMapper {
        return WeatherForecastsDtoMapper()
    }


    @Singleton
    @Provides
    fun provideOpenWeatherRetrofitService(): OpenWeatherRetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OpenWeatherRetrofitService::class.java)
    }



}