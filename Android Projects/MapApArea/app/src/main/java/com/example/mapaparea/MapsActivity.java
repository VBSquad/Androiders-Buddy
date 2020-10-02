package com.example.mapaparea;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapaparea.utils.calUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsContract.View {


    private GoogleMap mMap;
    MapsContract.Presenter mPresenter;
    private TextView areaTextView;
    ArrayList<LatLng> points;
    List<Marker> markerList;
    private Polygon polygon;
    FloatingActionButton fabundo,fabsatellite;
    PolylineOptions polylineOptions;
    Polyline polylineFinal;
    private boolean mMapIsTouched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        points = new ArrayList<LatLng>();
        markerList = new ArrayList<Marker>();
        mPresenter = new MapsPresenter(this);
        areaTextView = findViewById(R.id.tv_area);
        fabundo = findViewById(R.id.fab_undo);
        fabsatellite=findViewById(R.id.btnSatellite);
       // mMap.setMyLocationEnabled(true);

       requestStoragePermission();
        fabundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (points.size() > 0) {
                    polylineFinal.remove();
                    Marker marker = markerList.get(markerList.size() - 1);
                    marker.remove();
                    markerList.remove(marker);
                    points.remove(points.size() - 1);
                    if (points.size() > 0)
                        drawPolygon();


                }
            }
        });
        fabsatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID ? GoogleMap.MAP_TYPE_NORMAL : GoogleMap.MAP_TYPE_HYBRID);
                fabsatellite.setImageResource(mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID ? R.drawable.ic_satellite_off : R.drawable.ic_satellite_on);

            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mPresenter = new MapsPresenter(this);
        areaTextView = findViewById(R.id.tv_area);
    }

    private void requestStoragePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getMaxZoomLevel();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Instantiating the class MarkerOptions to plot marker on the map
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting latitude and longitude of the marker position
                markerOptions.position(latLng);

                // Setting titile of the infowindow of the marker
                markerOptions.title("Position");

                // Setting the content of the infowindow of the marker
                markerOptions.snippet("Latitude:" + latLng.latitude + "," + "Longitude:" + latLng.longitude).draggable(true);

                // Instantiating the class PolylineOptions to plot polyline in the map
                polylineOptions = new PolylineOptions();

                // Setting the color of the polyline
                polylineOptions.color(Color.RED);

                // Setting the width of the polyline
                polylineOptions.width(3);

                // Adding the taped point to the ArrayList
                points.add(latLng);

                // Setting points of polyline
                polylineOptions.addAll(points);

                // Adding the polyline to the map
                polylineFinal = mMap.addPolyline(polylineOptions);

                // Adding the marker to the map
                Marker marker = mMap.addMarker(markerOptions);


                markerList.add(marker);
                drawPolygon();
                /**

                 // Creating a marker
                 MarkerOptions markerOptions = new MarkerOptions();

                 // Setting the position for the marker
                 markerOptions.position(latLng);

                 // Setting the title for the marker.
                 // This will be displayed on taping the marker
                 markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                 // Clears the previously touched position
                 //  mMap.clear();

                 // Animating to the touched position
                 mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                 // Placing a marker on the touched position
                 mMap.addMarker(markerOptions);
                 // mPresenter.addMarker(latLng);
                 **/
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                updateMarkerLocation(marker, false);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                updateMarkerLocation(marker, true);
            }
        });


    }

    private void updateMarkerLocation(Marker marker, boolean calculate) {
        LatLng latLng = (LatLng) marker.getTag();
        int position = points.indexOf(latLng);
        points.set(position, marker.getPosition());
        marker.setTag(marker.getPosition());
        drawPolygon();
        if (calculate)
            setAreaLength(points);
    }


    private void setAreaLength(List<LatLng> points) {
        areaTextView.setText(getString(R.string.area_label) + String.format(Locale.ENGLISH, "%.2f", calUtils.getArea(points)) + " m\u00B2");
        // lengthTextView.setText(getString(R.string.length_label) + String.format(Locale.ENGLISH, "%.2f", CalUtils.getLength(points)) + getString(R.string.m_label));

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMapIsTouched = true;
                break;

            case MotionEvent.ACTION_UP:
                mMapIsTouched = false;
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void loadMap() {
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.locationPermissionGranted();
                } else {
                    mPresenter.locationPermissionRefused();
                }
                return;
            }
        }
    }

    @Override
    public void showLocationPermissionNeeded() {

    }

    @Override
    public void addMarkerToMap(MarkerOptions options, LatLng latLng) {
        Marker marker = mMap.addMarker(options);
        marker.setTag(latLng);
        markerList.add(marker);
        points.add(latLng);
        drawPolygon();

    }

    private void drawPolygon() {
        if (polygon != null) {
            polylineFinal.remove();
            polygon.remove();

            //mMap.clear();
        }
        PolygonOptions polygonOptions = new PolygonOptions();
        //polygonOptions.fillColor(Color.argb(255, 0, 0, 255));
        polygonOptions.fillColor(getResources().getColor(R.color.silverwithAlpha));
        polygonOptions.strokeColor(Color.argb(255, 255, 0, 0));
        polygonOptions.strokeWidth(3);
        polygonOptions.addAll(points);
        setAreaLength(points);
        polygon = mMap.addPolygon(polygonOptions);
       // polygon.setFillColor(Color.argb(0, 128, 128, 128));
       // polygon.setStrokeColor(Color.argb(0, 128, 128, 128));
    }

    @Override
    public void setPresenter(MapsContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.start();

    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}
