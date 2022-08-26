package com.echo.fastdbproj.util;

import org.jetbrains.annotations.NotNull;

public final class DistanceCalc implements Comparable<DistanceCalc> {
    private final double lng;
    private final double lat;
    private double des_lng;
    private double des_lat;

    private boolean isNullPlace = false;

    @Override
    public int compareTo(@NotNull DistanceCalc that) {
        if (isNullPlace) {
            return Integer.MAX_VALUE;
        }
        return Double.compare(calcDistance(lng, lat, des_lng, des_lat), calcDistance(that.lng, that.lat,  des_lng, des_lat));
    }

    public DistanceCalc(double lng, double lat, double des_lng, double des_lat) {
        this.lng = lng;
        this.lat = lat;
        this.des_lng = des_lng;
        this.des_lat = des_lat;
    }

    public DistanceCalc(String preferPlaceLL, String destPlaceLL) {
        var LL = parseLL2Double(preferPlaceLL);
        if (!(destPlaceLL == null) && !(destPlaceLL.isBlank())) {
            isNullPlace = true;
            var LL_ = parseLL2Double(destPlaceLL);
            this.des_lng = LL_[0];
            this.des_lat = LL_[1];
        }
        this.lng = LL[0];
        this.lat = LL[1];
    }

    public static double[] parseLL2Double(String LL) {
        var str = LL.replace('[', ' ').replace(']', ' ').split(",");
        var d1 = Double.parseDouble(str[0]);
        var d2 = Double.parseDouble(str[1]);
        return new double[]{d1, d2};
    }

    public static double calcDistance(double lngA, double latA, double lngB, double latB) {
        var square = (lngA - lngB) * (lngA - lngB) + (latA - latB) * (latA - latB);
        return Math.sqrt(square);
    }
}
