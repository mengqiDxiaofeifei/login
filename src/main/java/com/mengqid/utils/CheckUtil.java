package com.mengqid.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {


    public static Boolean checkStrLength(String str, Integer min, Integer max) {
        return (!isEmpty(str) && str.length() >= min && str.length() <= max);
    }


    public static Boolean checkNumber(String str) {
        return !isEmpty(str) && str.matches("^\\d+$");
    }


    public static Boolean isAllEmpty(Object[] o) {
        if (o == null) {
            return true;
        }

        for (Object tmp : o) {
            if (!isEmpty(tmp)) {
                return false;
            }
        }
        return true;
    }


    public static Boolean isEmpty(String o) {
        return (o == null || o.equals("") || o.equals("null") || o.equals("NULL") || o.trim().length() == 0);
    }


    public static Boolean isEmpty(Integer value) {
        return (value == null || value == 0);
    }


    public static Boolean isEmpty(Long value) {
        return (value == null || value == 0);
    }


    public static Boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }


    public static Boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.size() == 0);
    }


    public static Boolean isEmpty(Set<?> set) {
        return (set == null || set.size() == 0);
    }


    public static Boolean isEmpty(Object o) {
        // return o == null;
        if (o == null) {
            return true;
        }
        boolean isEmpty = false;
        if (o instanceof String) {
            isEmpty = isEmpty((String) o);
        } else if (o instanceof Integer) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof List<?>) {
            isEmpty = isEmpty((List) o);
        } else if (o instanceof Map<?, ?>) {
            isEmpty = isEmpty((Map) o);
        } else if (o instanceof Set<?>) {
            isEmpty = isEmpty((Set) o);
        }
        return isEmpty;
    }


    public static Boolean isEmpty(Object[] o) {
        return (o == null || o.length == 0);
    }


    public static Boolean checkRegex(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static Boolean checkPositive(String str, int min, int max) {
        return checkRegex("^\\d{" + min + "," + max + "}$", str);
    }


    public static Boolean checkPositive(String str, int length) {
        return checkRegex("^\\d{" + length + "}$", str);
    }


    public static boolean oneMoreEmpty(Object[] o) {
        boolean b = false;

        for (int i = 0; i < o.length; i++) {
            if (isEmpty(o[i])) {
                b = true;
                break;
            }
        }

        return b;
    }

    public static Boolean isEmptyInteger(Integer value) {
        return (value == null);
    }

    public static String replaceAll(String s) {
        return s.replaceAll("\r|\n|\\s|\t", "").replace(",", "，");
    }
}