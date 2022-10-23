package ai.munim.testassignment_weatherforecasts.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

open class PermissionManager  {

    companion object{
        val LOCATION_PERMISSION_REQUEST_CODE = 200



    }

    fun hasCoarseLocationPermission(context: Activity?): Boolean? {
        return context?.let { checkPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
    }

    fun hasFineLocationPermission(context: Activity?): Boolean? {
        return context?.let { checkPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) }
    }

    fun checkPermission(context: Context, permission: String): Boolean {
        return try {
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun shouldShowLocationPermissionRationale(context: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            context!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun requestForLocationPermission(ctx: Activity?) {
        ActivityCompat.requestPermissions(
            ctx!!,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    fun hasLocationPermission(context: Activity?): Boolean {
        return hasCoarseLocationPermission(context)!! && hasFineLocationPermission(context)!!
    }


}