package ai.munim.testassignment_weatherforecasts.data.impl

import ai.munim.testassignment_weatherforecasts.data.SharedPreferenceHelper
import ai.munim.testassignment_weatherforecasts.utils.Constants
import android.content.SharedPreferences
import javax.inject.Inject


class SharedPreferenceHelperImpl
@Inject constructor(
    private val sharedPreference:SharedPreferences
) : SharedPreferenceHelper{

    var MODULE_NAME = "Test_Weather_Forecasts_SP"
    val KEY_LOCATION_LAT = "KEY_LOCATION_LAT"
    val KEY_LOCATION_LNG = "KEY_LOCATION_LNG"

    private val mSPE: SharedPreferences.Editor? = sharedPreference.edit()
    private val mSP: SharedPreferences = sharedPreference

    override fun saveLat(lat: Double) {
        mSPE?.putString(KEY_LOCATION_LAT,lat.toString())
        mSPE?.apply()
    }

    override fun saveLng(lng: Double) {
        mSPE?.putString(KEY_LOCATION_LNG,lng.toString())
        mSPE?.apply()

    }

    override fun getLat(): Double {
        return mSP.getString(KEY_LOCATION_LAT,Constants.EMPTY_STRING)?.toDouble() ?: Constants.DEFAULT_DOUBLE
    }

    override fun getLng(): Double {
        return mSP.getString(KEY_LOCATION_LAT,Constants.EMPTY_STRING)?.toDouble() ?: Constants.DEFAULT_DOUBLE
    }


}