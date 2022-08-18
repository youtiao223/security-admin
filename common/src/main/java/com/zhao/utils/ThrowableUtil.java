package com.zhao.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获取堆栈信息
 */
public class ThrowableUtil {
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)){
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
