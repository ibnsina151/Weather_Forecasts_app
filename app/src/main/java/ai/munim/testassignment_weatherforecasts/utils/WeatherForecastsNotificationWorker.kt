package ai.munim.testassignment_weatherforecasts.utils

import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.repositories.impl.HomeRepositoryImpl
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.*
import javax.inject.Inject

class WeatherForecastsNotificationWorker
@Inject constructor(
    private val homeRepository: HomeRepositoryImpl,
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        val LOCATION_INTERVAL: Long = 5000
        val FASTEST_LOCATION_INTERVAL: Long = 2000

        var mFusedLocationProviderClient: FusedLocationProviderClient? = null
        var mLocationRequest: LocationRequest? = null
        var mLocationCallback: LocationCallback? = null

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        mLocationRequest = LocationRequest()
        mLocationRequest.setInterval(LOCATION_INTERVAL)
        mLocationRequest.setFastestInterval(FASTEST_LOCATION_INTERVAL)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                for (location in locationResult.locations) {
                    if (location != null) {
                        if (CommonTasks.isOnline(context = applicationContext)){
                            val weatherForecastsModel: WeatherForecastsModel = homeRepository.getWeatherForecasts_e()
                            if (!weatherForecastsModel.list?.isEmpty()!!){
                                homeRepository.deleteAllWeatherInfo()
                                homeRepository.saveWeatherInfo(Conversion.getWeatherEntity(weatherForecastsModel))
                            }
                        }
                    }
                }
            }
        }


        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }else {
            mFusedLocationProviderClient.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                null
            )
        }


        return Result.success()
    }





}