package com.echo.fastdbproj.util;


import org.springframework.stereotype.Component;

@Component
public class BinUtils {
    public double calcDistance(double lngA, double latA, double lngB, double latB) {
        var square = (lngA - lngB) * (lngA - lngB) + (latA - latB) * (latA - latB);
        return Math.sqrt(square);
    }
}
