package com.max.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @auther MaxLiu
 * @time 2017/1/14
 */

public class StringUtil {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern phone = Pattern
            .compile("^((13[0-9])|170|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 精确到秒
     */
    private static final ThreadLocal<SimpleDateFormat> dateFormater
            = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 精确到天
     */
    private static final ThreadLocal<SimpleDateFormat> dateFormater2
            = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 返回当前系统时间
     *
     * @param format 时间类型
     * @return 时间
     */
    public static String getDateTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 返回当前系统时间
     *
     * @return HH:mm 类型的时间
     */
    public static String getDateTime() {
        return getDateTime("HH:mm");
    }

    /**
     * 毫秒转换为mm:ss
     * @param ms 毫秒数
     * @return mm:ss格式的时间
     */
    public static String timeFormat(int ms) {
        StringBuilder time = new StringBuilder();//创建StringBuilder对象
        time.delete(0, time.length());//清空time
        ms /= 1000;//换算为秒
        int s = ms % 60;//取余数拿到剩余的秒数
        int min = s % 60;//取余数拿到剩余的分钟数
        if (min < 10) {
            time.append(0);//小于时在time后面拼接0 --> 0,否则不执行
        }
        time.append(min).append(":");//time后面拼接分钟数和: --> 08:/15:
        if (s < 10) {
            time.append(0);//同样，秒数小于10,后面加0 --> 08:0,否则不执行
        }
        time.append(s);//time后面拼接秒数 --> 08:08/15:15/08:15/15:08
        return time.toString();//转换成String类型并返回
    }

    /**
     * 将字符串转换为日期类型
     * @param date 字符串
     * @return 日期类型
     */
    public static Date toDate(String date){
        try {
            return dateFormater.get().parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断给定字符串时间是否是今天
     * @param date 字符串
     * @return boolean类型
     */
    public static boolean isToday(String date){
        boolean b = false;
        Date time = toDate(date);
        Date today = new Date();
        if(time != null){
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if(nowDate.equals(timeDate)){
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true。
     * @param input 输入的字符串
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(String email) {
        return !(email == null || email.trim().length() == 0)
                && emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(String phoneNum) {
        return !(phoneNum == null || phoneNum.trim().length() == 0)
                && phone.matcher(phoneNum).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str 需要转换的字符串
     * @param defValue 默认值
     * @return int类型
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ignored) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj 需要转整的对象
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj 需要转long的字符串
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception ignored) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取AppKey
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        }
        return apiKey;
    }

    /**
     * 获取手机IMEI码
     */
    public static String getPhoneIMEI(Activity aty) {
        TelephonyManager tm = (TelephonyManager) aty
                .getSystemService(Activity.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * MD5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * KJ加密
     */
    public static String KJencrypt(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c + 5));
        }
        return hex.toString();
    }

    /**
     * KJ解密
     */
    public static String KJdecipher(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c - 5));
        }
        return hex.toString();
    }
}
