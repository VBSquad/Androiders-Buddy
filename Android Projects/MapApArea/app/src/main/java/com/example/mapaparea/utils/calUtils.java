package com.example.mapaparea.utils;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.List;

public class calUtils {
    public static  double getArea(List<LatLng> latLngs) {
        return SphericalUtil.computeArea(latLngs);
    }

    public static  double getLength(List<LatLng> latLngs) {
        return SphericalUtil.computeLength(latLngs);
    }
}
