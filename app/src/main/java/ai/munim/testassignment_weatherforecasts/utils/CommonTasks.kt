package ai.munim.testassignment_weatherforecasts.utils

import ai.munim.testassignment_weatherforecasts.R
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherModel
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherDto
import ai.munim.testassignment_weatherforecasts.network.model.openWeatherDTOs.WeatherListDto
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.view.WindowManager.BadTokenException
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.io.IOException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class CommonTasks {

    companion object {

        fun getImage(weatherIcon : String, context : Context): Drawable? {
            return when (weatherIcon) {
                "01n" -> ContextCompat.getDrawable(context, R.drawable.weather01n)
                "01d" -> ContextCompat.getDrawable(context, R.drawable.weather01d)
                "02n" -> ContextCompat.getDrawable(context, R.drawable.weather02n)
                "02d" -> ContextCompat.getDrawable(context, R.drawable.weather02d)
                "03n", "03d" -> ContextCompat.getDrawable(context, R.drawable.weather03d)
                "04n", "04d" -> ContextCompat.getDrawable(context, R.drawable.weather04d)
                "09n", "09d" -> ContextCompat.getDrawable(context, R.drawable.weather09d)
                "10d" -> ContextCompat.getDrawable(context, R.drawable.weather10d)
                "11n", "11d" -> ContextCompat.getDrawable(context, R.drawable.weather11d)
                "13n", "13d" -> ContextCompat.getDrawable(context, R.drawable.weather13d)
                "50n", "50d" -> ContextCompat.getDrawable(context, R.drawable.weather50d)
                else -> {
                    object : ColorDrawable(Color.TRANSPARENT) {}
                }
            }
        }

        fun isOnline(context: Context?): Boolean {
            return if (context == null) {
                false
            } else {
                try {
                    val cm = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val netInfo = cm.activeNetworkInfo
                    if (netInfo != null && netInfo.isConnectedOrConnecting) {
                        return true
                    }
                    false
                } catch (e: Exception) {
                    false
                }
            }
        }


        fun getTemperatureInCelcius( temp:Double):Long? {
            return  Math.round(temp - Constants.kelvin)
        }




        fun getDayOfWeek(dayOfWeek: Int): String {
            when (dayOfWeek) {
                1 -> return Constants.day1
                2 -> return Constants.day2
                3 -> return Constants.day3
                4 -> return Constants.day4
                5 -> return Constants.day5
                6 -> return Constants.day6
                7 -> return Constants.day7
            }
            return Constants.EMPTY_STRING
        }

        fun getYearOfMonths(monthOfYear: Int): String {
            when (monthOfYear) {
                0 -> return YearOfMonths.January.toString()
                1 -> return YearOfMonths.February.toString()
                2 -> return YearOfMonths.March.toString()
                3 -> return YearOfMonths.April.toString()
                4 -> return YearOfMonths.May.toString()
                5 -> return YearOfMonths.June.toString()
                6 -> return YearOfMonths.July.toString()
                7 -> return YearOfMonths.August.toString()
                8 -> return YearOfMonths.September.toString()
                9 -> return YearOfMonths.October.toString()
                10 -> return YearOfMonths.November.toString()
                11 -> return YearOfMonths.December.toString()
            }
            return Constants.EMPTY_STRING
        }

        fun getYearFromTimestamp(timestamp: Long?): String? {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return calendar[Calendar.YEAR].toString()
        }

        fun getMonthFromTimestamp(timestamp: Long?): String? {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return format.format(java.lang.Double.valueOf((calendar[Calendar.MONTH] + 1).toDouble()))
        }

        fun getDateFromTimestamp(timestamp: Long?): String? {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return format.format(java.lang.Double.valueOf(calendar[Calendar.DATE].toDouble()))
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getAPIKEY(key:String): String{
            val decodedBytes: ByteArray = Base64.getDecoder().decode(key)
            return String(decodedBytes);
        }

        fun getCurrentYear(): String? {
            val calendar = Calendar.getInstance()
            return calendar[Calendar.YEAR].toString()
        }

        fun getCurrentMonth(): String? {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            return format.format(java.lang.Double.valueOf((calendar[Calendar.MONTH] + 1).toDouble()))
        }

        fun getCurrentDate(): String? {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            return format.format(java.lang.Double.valueOf(calendar[Calendar.DATE].toDouble()))
        }

        fun getAddress(lat: Double, lng: Double,context: Context?): String {
            val geocoder = context?.let { Geocoder(it, Locale.getDefault()) }
            try {
                val addresses: List<Address>? = geocoder?.getFromLocation(lat, lng, 1)
                val obj: Address = addresses!![0]
                return obj.adminArea
            } catch (e: IOException) {
                return "BD"
            }
        }

        fun getCurrentDay(): String {
            return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDate()
        }

        fun showToast(context: Context?, message: String?) {
            try {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            } catch (e: BadTokenException) {
                //use a log message
            }
        }

        fun getListModel(list: ArrayList<WeatherListDto>): ArrayList<WeatherListModel>{
            var temp : ArrayList<WeatherListModel> = ArrayList()

            for (model in list) {
                var temp_w = WeatherListModel(
                    model.dt,
                    model.main?.temp,
                    model.main?.feelsLike,
                    model.main?.tempMin,
                    model.main?.tempMax,
                    model.main?.pressure,
                    model.main?.seaLevel,
                    model.main?.grndLevel,
                    model.main?.humidity,
                    model.main?.tempKf,
                    getWeatherList(model.weather),
                    model.clouds?.all,
                    model.wind?.speed,
                    model.wind?.deg,
                    model.wind?.gust,
                    model.visibility,
                    model.rain?._3h,
                    model.dtTxt
                )
                temp.add(temp_w)
            }

            return temp
        }


        fun getWeatherList(weather: ArrayList<WeatherDto>) : ArrayList<WeatherModel> {
            val temp : ArrayList<WeatherModel>? = ArrayList()
            for (w in weather){
                val temp_w = WeatherModel(
                    w.id,
                    w.main,
                    w.description,
                    w.icon
                )
                temp?.add(temp_w)
            }
            return temp!!
        }


        fun getDateTimeFromTimestamp(dt:Int?):String{
            val date: Date? = dt?.let { Date(it.toLong()) }
            val calendar = Calendar.getInstance()
            if (dt != null) {
                calendar.timeInMillis = dt.toLong()
            }
            val sdf = SimpleDateFormat("MM/dd/yyyy h:mm:ss a")
            val formattedDate = sdf.format(date)
            return getYearOfMonths(calendar.get(Calendar.MONTH))+", " +  getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))+", " + formattedDate
        }


    }




}