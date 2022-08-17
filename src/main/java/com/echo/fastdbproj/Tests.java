package com.echo.fastdbproj;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    public static void main(String[] args) {
        testListForeachRemoveBack2NotThrow();
    }
    public static void testListForeachRemoveBack2NotThrow() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");

        list.removeIf((el) -> "2".equals(el) || el.length() > 2);

        System.out.println("\n\n");
        list.forEach(System.out::println);
    }

}
