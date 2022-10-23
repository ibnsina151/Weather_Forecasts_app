package ai.munim.testassignment_weatherforecasts.data

interface SharedPreferenceHelper {

    companion object {
        const val MODULE_NAME = "ai.munim.textassignment_weaterforecasts.PREFERENCE_FILE"
        const val VERSION = 1
    }

    fun saveLat(lat: Double)

    fun saveLng(lng : Double)

    fun getLat() : Double

    fun getLng() : Double

}