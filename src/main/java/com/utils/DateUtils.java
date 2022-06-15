package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static long now() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return date.getTime();
    }
}
