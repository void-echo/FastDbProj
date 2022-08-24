package com.echo.fastdbproj;

import com.echo.fastdbproj.entity.Bill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tests {

    public static void main(String[] args) {
        testListForeachRemoveBack2NotThrow();
    }
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void testListForeachRemoveBack2NotThrow() {
        String now_formatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(now_formatted);
        Timestamp timestamp = Timestamp.from(Instant.now());
        Bill bill = new Bill();
        bill.setTime(timestamp);
        bill.setCustomerId("void-echo");
        bill.setDriverId("driver_123");
        bill.setDuration("2H30M");
        bill.setMoney("￥12");
        bill.setScore(4);
        bill.setStatus("FINISHED");
        var json = gson.toJson(bill);
        System.out.println(json);
        // 2022-08-24 17:50:48.678
        var p0 = (Bill) gson.fromJson(json, Bill.class);
        System.out.println("\n\n");
        System.out.println(gson.toJson(p0));
    }

}
