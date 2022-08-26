package com.echo.fastdbproj.util;


import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Component
public class BinUtils {

    public static void main(String[] args) {
        BinUtils utils = new BinUtils();
        var str = "PLACE: 1.23432, 4.123123";
        var db = utils.parseLngLat2Double(str);
        Arrays.stream(db).forEach(System.out::println);
        var msg = "ID: POGY98gdiasugb98";
        var id = utils.parseId(msg);
        System.out.println(id);

    }
    public double calcDistance(double lngA, double latA, double lngB, double latB) {
        var square = (lngA - lngB) * (lngA - lngB) + (latA - latB) * (latA - latB);
        return Math.sqrt(square);
    }

    // "PLACE: lng, lat"
    // return an array of length 2.
    public double[] parseLngLat2Double(String msg) {
            checkStartWith(msg, "PLACE: ");
            var _pattern = msg.replaceFirst("PLACE: ", "");
            var strs = _pattern.split(",");
            for (var str : strs) {
                str = str.strip();
            }
            var d1 = Double.parseDouble(strs[0]);
            var d2 = Double.parseDouble(strs[1]);
            return new double[]{d1, d2};
    }

    // type: [lng, lat]
    public double[] parseLL2Double(String LL) {
        var str = LL.replace('[', ' ').replace(']', ' ').split(",");
        for (var s : str) {
            s = s.trim();
        }
        var d1 = Double.parseDouble(str[0]);
        var d2 = Double.parseDouble(str[1]);
        return new double[]{d1, d2};
    }

    public void checkStartWith(String msg, String prefix) {
        var flg =  msg.startsWith(prefix);
        if (!flg) {
            UnitedLog.err("Parse Err: Expected \"" + prefix + "......\", Got " + msg);
        }
    }

    // "ID: "
    public String parseId(String msg) {
        checkStartWith(msg, "ID: ");
        return msg.replaceFirst("ID: ", "").trim();
    }

    public String calcTimeDuration(Instant start, Instant end) {
        Duration duration = Duration.between(start, end);
        return duration.toString();
    }

    public String mixPreferPlace(String honRai, int times, String genZai) {
        var lngLat = parseLL2Double(honRai);
        var l_ = parseLL2Double(genZai);
        var lng = ( lngLat[0] * times + l_[0] ) / (times + 1);
        var lat = ( lngLat[1] * times + l_[1] ) / (times + 1);
        return "[%f , %f]".formatted(lng, lat);
    }

}
