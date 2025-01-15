package com.example.fmjavaserver.equipment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static boolean isGreaterThanDaysInMonth(int month, int year, int day) {
        LocalDate date = LocalDate.of(year, month, 1);
        int lastDayOfMonth = date.lengthOfMonth();
        return day > lastDayOfMonth;
    }

    public static int getLastDayOfMonth(int month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        return date.lengthOfMonth();
    }

    public static String getTodayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }
}

