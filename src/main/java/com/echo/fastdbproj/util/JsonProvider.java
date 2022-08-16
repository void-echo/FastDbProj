package com.echo.fastdbproj.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * We use JsonProvider class instead of `@RestController` annotation
 * because it can provide a more beautiful format (setPrettyPrinting)
 * using Gson in an easier way.
 */
@Component
public class JsonProvider {


    public static void main(String[] args) {
        Map<String, List<Double>> map = new ConcurrentHashMap<>();
        map.put("123123pPP", List.of(1.12312, 3.234));
        map.put("BD_O_UH(*\\)(*)", List.of(1.12312, 3.234));
        JsonProvider jsonProvider = new JsonProvider();
        String strJson = jsonProvider.toJson(map);
        System.out.println(strJson);
        System.out.println("\n\n");
        var obj = jsonProvider.fromJson(strJson, map.getClass());
        @SuppressWarnings("unchecked") Map<String, List<Double>> _map = (Map<String, List<Double>>) obj;


        System.out.println("HASH: map  == " + map.hashCode());
        System.out.println("HASH: _map == " + _map.hashCode());
        System.out.println(_map.equals(map));
        System.out.println("\n\n");

        String myJson =
                """
                {
                    "123123": [3.14186, 4.123],
                    "JK": [0.00012, 0.998]
                }
                """;
        var map2Raw = jsonProvider.fromJson(myJson, map.getClass());
        @SuppressWarnings("unchecked") Map<String, List<Double>> map2 = (Map<String, List<Double>>) map2Raw;
        System.out.println(jsonProvider.toJson(map2));
    }
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public String toJson(Object o) {
        return gson.toJson(o);
    }

    public Object fromJson(String json, Class<?> type) {
        return gson.fromJson(json, type);
    }
}
