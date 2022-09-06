package com.echo.fastdbproj.entity.status;

import java.util.Objects;

public class DayAndNum {
    private String day;
    private int num;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayAndNum dayAndNum)) return false;
        return getNum() == dayAndNum.getNum() && Objects.equals(getDay(), dayAndNum.getDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getNum());
    }

    @Override
    public String toString() {
        return "DayAndNum{" +
                "day='" + day + '\'' +
                ", num=" + num +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
