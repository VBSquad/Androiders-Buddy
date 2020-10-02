package com.example.mapaparea;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

class MapsPresenter implements MapsContract.Presenter, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    MapsContract.View mView;

    MapsPresenter(MapsContract.View view) {
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void locationPermissionGranted() {

    }

    @Override
    public void locationPermissionRefused() {

    }

    @Override
    public void requestGps() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(mView.getViewActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);
//   Config.CHECK_GPS_ON_SETTING=102
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(mView.getViewActivity(),
                                    102);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });



    }

    @Override
    public void addMarker(LatLng latLng) {
        Activity activity = mView.getViewActivity();
        @SuppressLint("ResourceType") @IdRes int icon = R.drawable.ic_add_location_black_24dp;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(getBitmapFromDrawable(activity, icon));
        MarkerOptions options = new MarkerOptions().position(latLng).icon(bitmap).draggable(true);
        mView.addMarkerToMap(options,latLng);

    }

    private Bitmap getBitmapFromDrawable(Context context, int icon) {
        Drawable drawable = ContextCompat.getDrawable(context, icon);
        Bitmap obm = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(obm);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return obm;
    }

    @Override
    public void start() {
        checkPermission();
    }

    //101:PERMISSION_LOCATION
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (mView.getViewActivity().checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (mView.getViewActivity().shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(mView.getViewActivity(), "Please grant location permission", Toast.LENGTH_SHORT).show();
                    mView.getViewActivity().requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                } else {
                    mView.getViewActivity().requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                }
            } else {
                mView.loadMap();
            }

        } else {
            mView.loadMap();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
