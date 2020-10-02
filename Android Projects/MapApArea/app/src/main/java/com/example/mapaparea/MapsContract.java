package com.example.mapaparea;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public interface MapsContract {
    interface View extends BaseView<Presenter> {

        void loadMap();

        void showLocationPermissionNeeded();

        void addMarkerToMap(MarkerOptions options, LatLng latLng);
    }
    interface Presenter extends BasePresenter{

        void locationPermissionGranted();
        void locationPermissionRefused();

        void requestGps();

        void addMarker(LatLng latLng);
    }
}
