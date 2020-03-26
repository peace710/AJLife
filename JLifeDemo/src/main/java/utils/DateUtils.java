package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final String TAG = DateUtils.class.getSimpleName();
    public static void main(String[] args) {
        LogUtils.i(TAG,"currentDate -> " + currentDate());
        LogUtils.i(TAG,"currentTime  -> " + currentTime());
        LogUtils.i(TAG,"currentDateTime  -> " + currentDateTime());
        LogUtils.i(TAG,"formatDate(timestamp) -> " + formatDate(System.currentTimeMillis()));
        LogUtils.i(TAG,"formatTime(timestamp)  -> " + formatTime(System.currentTimeMillis()));
        LogUtils.i(TAG,"formatDateTime(timestamp)  -> " + formatDateTime(System.currentTimeMillis()));
        LogUtils.i(TAG,"currentWeekDate  -> " + currentWeekDate());
        LogUtils.i(TAG,"lastWeekDate -> " + lastWeekDate());
        LogUtils.i(TAG,"weekDate(2)  -> " + weekDate(2));
        LogUtils.i(TAG,"weekDate(-2)  -> " + weekDate(-2));
        LogUtils.i(TAG,"formatDate  -> " + formatDate("2020-03-26"));
        LogUtils.i(TAG,"formatTime  -> " + formatTime("15:06:48"));
        LogUtils.i(TAG,"formatDateTime  -> " + formatDateTime("2020-03-26 15:06:48"));
        LogUtils.i(TAG,"currentWeek  -> " + currentWeek(currentDate()));
        LogUtils.i(TAG,"dayOfWeek  -> " + dayOfWeek(currentDate()));
        LogUtils.i(TAG,"getSeconds  -> " + getSeconds(System.currentTimeMillis()));
        LogUtils.i(TAG,"getMinutes  -> " + getMinutes(System.currentTimeMillis()));
        LogUtils.i(TAG,"compare  -> " + compare(currentDateTime(),"2020-03-26 15:06:48.200"));
    }

    public static String currentDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String currentTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        return format.format(new Date());
    }

    public static String currentDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.format(new Date());
    }

    public static String formatDate(long timestamp){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(timestamp));
    }

    public static String formatTime(long timestamp){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        return format.format(new Date(timestamp));
    }

    public static String formatDateTime(long timestamp){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.format(new Date(timestamp));
    }

    //本周一
    public static String currentWeekDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == 1){
            week += 7;
        }
        c.add(Calendar.DATE,2 - week);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(c.getTime());
    }

    //上周一
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


    /**
     * 获取前几周或后几周的周一日期
     * @param index
     * @return
     * index > 0 过去
     * index = 0 本周
     * index < 0 未来
     *
     */
    public static String weekDate(int index){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == 1){
            week += 7;
        }
        c.add(Calendar.DATE,2 - week - (7 * index));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(c.getTime());
    }

    public static String formatDate(String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat out = new SimpleDateFormat("yyyy年M月dd日");
            return out.format(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatTime(String time){
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat out = new SimpleDateFormat("H时m分s秒");
            return out.format(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDateTime(String dateTime){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat out = new SimpleDateFormat("yyyy年M月dd日H时m分s秒");
            return out.format(format.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String currentWeek(String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(date));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
               return "周日";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 2) {
               return "周一";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 3) {
               return "周二";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 4) {
               return "周三";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 5) {
               return "周四";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 6) {
               return "周五";
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 7) {
               return "周六";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static int dayOfWeek(String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(date));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                return 0;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                return 1;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 3) {
                return 2;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 4) {
                return 3;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 5) {
                return 4;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 6) {
                return 5;
            }
            if (c.get(Calendar.DAY_OF_WEEK) == 7) {
                return 6;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getSeconds(long timestamp){
        return (int)(toSeconds(timestamp) % 60);
    }

    public static int getMinutes(long timestamp){
        return (int)(toSeconds(timestamp) / 60) % 60;
    }

    private static long toSeconds(long timestamp){
        return timestamp / 1000;
    }

    public static long compare(String targetTime,String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            if (TextUtils.isEmpty(targetTime) || TextUtils.isEmpty(time)){
                return Integer.MIN_VALUE;
            }
            return format.parse(targetTime).getTime() - format.parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.MIN_VALUE;
    }
}
