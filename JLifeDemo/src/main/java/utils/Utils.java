package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final String TAG = "Utils";

    /**
     * 用于判断字符串是否为整型数字
     * @param string
     * @return
     */
    public static boolean isInt(String string) {
        if (string == null)
            return false;

        String regEx1 = "[\\-|\\+]?\\d+";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        LogUtils.i(TAG,isInt("1.0") + "");
        LogUtils.i(TAG,isInt("1.0f") + "");
        LogUtils.i(TAG,isInt("10abcd") + "");
        LogUtils.i(TAG,isInt("10") + "");
        LogUtils.i(TAG,isInt("+10") + "");
        LogUtils.i(TAG,isInt("-10") + "");
        LogUtils.i(TAG,Integer.parseInt("10")+"");
        LogUtils.i(TAG,Integer.parseInt("-10")+"");
        LogUtils.i(TAG,Integer.parseInt("+10")+"");
    }
}
