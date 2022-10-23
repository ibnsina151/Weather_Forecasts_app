package ai.munim.testassignment_weatherforecasts.di

import ai.munim.testassignment_weatherforecasts.WeatherApplication
import ai.munim.testassignment_weatherforecasts.data.DataManager
import ai.munim.testassignment_weatherforecasts.data.DbHelper
import ai.munim.testassignment_weatherforecasts.data.SharedPreferenceHelper
import ai.munim.testassignment_weatherforecasts.data.impl.DataManagerImpl
import ai.munim.testassignment_weatherforecasts.data.impl.DbHelperImpl
import ai.munim.testassignment_weatherforecasts.data.impl.SharedPreferenceHelperImpl
import ai.munim.testassignment_weatherforecasts.dbhelper.DbConstants
import ai.munim.testassignment_weatherforecasts.dbhelper.database.ProjectDB
import ai.munim.testassignment_weatherforecasts.di.qualifiers.DatabaseInfo
import ai.munim.testassignment_weatherforecasts.di.qualifiers.PreferenceInfo
import ai.munim.testassignment_weatherforecasts.helper.LocationManager
import ai.munim.testassignment_weatherforecasts.helper.PermissionManager
import ai.munim.testassignment_weatherforecasts.utils.WeatherForecastsDataFetchingWorker
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.work.PeriodicWorkRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): WeatherApplication {
        return app as WeatherApplication
    }


    @Provides
    @Singleton
    fun getPermissionManager(): PermissionManager {
        return PermissionManager()
    }

    @Singleton
    @Provides
    fun getLocationManager(@ApplicationContext context: Context): LocationManager {
        return LocationManager(context)
    }

    @Provides
    @PreferenceInfo
    @Singleton
    fun provideSharedPreferenceName(): String = SharedPreferenceHelper.MODULE_NAME

    @Singleton
    @Provides
    fun getSharedPreference(
        @ApplicationContext context: Context,
        @PreferenceInfo moduleName: String?):
            SharedPreferences = context.getSharedPreferences(moduleName,Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPrefHelper(sharedPreferences: SharedPreferences):
            SharedPreferenceHelper {
        return SharedPreferenceHelperImpl(sharedPreferences)
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return DbConstants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @DatabaseInfo dbName:String,
        @ApplicationContext context: Context
    ): ProjectDB{
        return Room.databaseBuilder(context,ProjectDB::class.java, dbName).build()
    }


    @Provides
    @Singleton
    fun provideDbHelper(projectDB: ProjectDB): DbHelper {
        return DbHelperImpl(projectDB)
    }


    @Provides
    @Singleton
    fun provideDataManager(dbHelper: DbHelper, preferenceHelper: SharedPreferenceHelper) :
            DataManager = DataManagerImpl(dbHelper, preferenceHelper)


    @Provides
    @Singleton
    fun periodicWorkerRequest(): PeriodicWorkRequest {
        return PeriodicWorkRequest.Builder(
            WeatherForecastsDataFetchingWorker::class.java,
            12, TimeUnit.HOURS
        ).build()
    }

}