package com.hades.quicklibrary.utils;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 说明：
 * 作者：逍遥子
 * 时间：2016/3/11 19:54
 */
public class ValidateUtils {
    /**
     * 字符串分隔符
     */
    public static String separator = ",";

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        if (isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 验证邮箱格式是否正确
     */
    public static boolean emailValidation(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    /**
     * 验证是否是文字
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(String chinese) {
        String telRegex = "^[\u4e00-\u9fa5]+$";
        if (isEmpty(chinese))
            return false;
        else
            return chinese.matches(telRegex);
    }

    /***
     * 方法说明：验证是否为空
     *
     * @param content
     * @return
     */
    public static boolean isEmpty(String content) {
        if (content == null) return true;
        content = content.trim();
        while (content.startsWith("　")) {//这里判断是不是全角空格
            content = content.substring(1, content.length()).trim();
        }
        while (content.endsWith("　")) {
            content = content.substring(0, content.length() - 1).trim();
        }
        if (TextUtils.isEmpty(content)) return true;
        if ("null".equals(content)) return true;
        return false;
    }

    /***
     * 获取数据
     *
     * @param content
     * @return
     */
    public static String getValue(String content) {
        return getValue(content, "");
    }

    public static String getValue(String content, String defaultValue) {
        if (isEmpty(content)) return defaultValue;
        return content;
    }

    /***
     * list转字符串 用 separator隔开
     *
     * @param list
     * @return
     */
    public static String listToString(List list) {
        return listToString(list, separator);
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static List<String> stringToList(String listString) {
        return stringToList(listString, separator);
    }
    public static String stringRemoveSeparator(String listString) {
        return stringRemoveSeparator(listString, "");
    }
    public static String stringRemoveSeparator(String listString, String separator) {
        try {
            return  listString.replace(",",separator);
        }catch (Exception ex){

        }
        return "";
    }
    public static List<String> stringToList(String listString, String separator) {
        List<String> list;
        try {
            list = new ArrayList();
            for(String t : listString.split(",")){
                list.add(t);
            }
        } catch (Exception e) {
            list = new ArrayList();
        }
        return list;
    }

    public static String getMobile(String mobile){
        try{
            return mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
        }catch (Exception e){
            return mobile;
        }
    }

    /***
     * 把由数字组成的字符串转换成货币的格式来进行展示。比如12345678.90，往往我们需要它是这个样子来呈现：￥12,345,678.90。
     * @param string
     * @return
     */
    public static String getMoneyType(double string) {
        return  getMoneyType(string+"");
    }

    /***
     * 把由数字组成的字符串转换成货币的格式来进行展示。比如12345678.90，往往我们需要它是这个样子来呈现：￥12,345,678.90。
     * @param string
     * @return
     */
    public static String getMoneyType(String string) {
        if(isEmpty(string)){
            string = "0.00";
        }
        // 把string类型的货币转换为double类型。
        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        String numString = format.format(numDouble);
        return numString;
    }
}
