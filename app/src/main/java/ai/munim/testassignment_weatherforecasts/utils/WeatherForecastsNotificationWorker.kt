package ai.munim.testassignment_weatherforecasts.utils

import ai.munim.testassignment_weatherforecasts.data.DbHelper
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.*
import javax.inject.Inject

class WeatherForecastsNotificationWorker
@Inject constructor(
    private val dbHelper: DbHelper,
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        val data = dbHelper.getWeatherInfo(CommonTasks.getCurrentDay()).value?.get(0)

        if (data!=null)
        for (entity in data.list!!) {
            var current_timestamp = System.currentTimeMillis()/1000

            if (current_timestamp < entity.dt!! && entity.dt!! < current_timestamp + (15 *60) &&
                    entity.weather.get(0).main.equals(Constants.Rain)){

                var millisInFuture = (current_timestamp - entity.dt!!)

                if (millisInFuture > (5 *60)){
                    millisInFuture -= 360
                }

                object : CountDownTimer(millisInFuture, Constants.MILLIES) {

                    override fun onTick(millisUntilFinished: Long) {

                    }

                    override fun onFinish() {
                        notify_()

                    }
                }.start()

                break
            }
        }


        return Result.success()
    }



    private fun notify_() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = applicationContext.packageName
            val name = applicationContext.packageName
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            // Register the channel with the system
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(ai.munim.testassignment_weatherforecasts.R.drawable.weather09d)
                .setContentTitle("Rainy conditions")
                .setContentText("Rain will start within 5 minutes")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationManager.notify(0,notification.build())
        }
    }

}