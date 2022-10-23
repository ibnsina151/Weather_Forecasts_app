package ai.munim.testassignment_weatherforecasts.helper;

import static ai.munim.testassignment_weatherforecasts.utils.Constants.LOCATION_ACCURACY_THRESHOLD;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class LocationManager extends LiveData<Location> implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final long LOCATION_INTERVAL = 5000;
    private static final long FASTEST_LOCATION_INTERVAL = 2000;
    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    private int MAX_RETRY = 7;
    private int mCurrentCount;
    private Location mBestLocation;
    private android.location.LocationManager locationManager = null;

    public LocationManager(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context, this, this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onActive() {
        // Wait for the GoogleApiClient to be connected
        connectLocationRequest();
    }

    @Override
    protected void onInactive() {
        disconnectLocationRequest();
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(Bundle connectionHint) {
        // Request updates if there’s someone observing

        if (hasActiveObservers()) {
            mCurrentCount = 0;
            mBestLocation = null;
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(LOCATION_INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_LOCATION_INTERVAL);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);

        }


    }

    @Override
    public void onLocationChanged(Location location) {
        if (mBestLocation == null) mBestLocation = location;
        else if (mBestLocation.getAccuracy() > location.getAccuracy())
            mBestLocation = location;
        mCurrentCount++;

        if (location.getAccuracy() <= LOCATION_ACCURACY_THRESHOLD)
            setValue(location);
        else if (mCurrentCount >= MAX_RETRY) {
            mBestLocation.setElapsedRealtimeNanos(location.getElapsedRealtimeNanos());
            setValue(mBestLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connectLocationRequest();
    }

    public void connectLocationRequest() {

        googleApiClient.connect();
    }

    public void disconnectLocationRequest() {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
        googleApiClient.disconnect();
    }




}
