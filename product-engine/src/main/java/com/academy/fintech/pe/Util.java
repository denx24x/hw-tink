package com.academy.fintech.pe;

import java.util.Calendar;
import java.util.Date;

public class Util {
    /**
     * Calculates next date of payment by changing month
     */
    public static Date nextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
