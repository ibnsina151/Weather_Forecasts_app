package ai.munim.testassignment_weatherforecasts.di

import ai.munim.testassignment_weatherforecasts.data.DataManager
import ai.munim.testassignment_weatherforecasts.domain.repositories.HomeRepository
import ai.munim.testassignment_weatherforecasts.domain.repositories.impl.HomeRepositoryImpl
import ai.munim.testassignment_weatherforecasts.helper.LocationManager
import ai.munim.testassignment_weatherforecasts.network.interfaces.OpenWeatherRetrofitService
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherForecastsDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        openWeatherRetrofitService: OpenWeatherRetrofitService,
        locationManager: LocationManager,
        dataManager: DataManager,
        mapper:WeatherForecastsDtoMapper
    ): HomeRepository{
        return HomeRepositoryImpl(
            openWeatherRetrofitService = openWeatherRetrofitService,
            locationManager = locationManager,
            dataManager = dataManager,
            mapper = mapper
        )
    }



}