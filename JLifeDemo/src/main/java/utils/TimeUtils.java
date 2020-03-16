package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static String currentDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static void currentWeek(String time){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(time));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                System.out.println("周日");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                System.out.println("周一");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 3) {
                System.out.println("周二");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 4) {
                System.out.println("周三");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 5) {
                System.out.println("周四");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 6) {
                System.out.println("周五");
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 7) {
                System.out.println("周六");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String lastWeekDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == 1){
            week += 7;
        }
        c.add(Calendar.DATE,2 - week - 7);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(c.getTime());
    }

    public static String getDate(String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat out = new SimpleDateFormat("M月dd日");
            return out.format(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(TimeUtils.currentDate());
        TimeUtils.currentWeek("2020-02-16");
        TimeUtils.currentWeek(TimeUtils.currentDate());
        TimeUtils.currentWeek("2020-02-11");
        System.out.println(TimeUtils.lastWeekDate());
        System.out.println(TimeUtils.getDate("2020-02-11"));
        System.out.println(TimeUtils.getDate("2020-10-11"));
    }
}
