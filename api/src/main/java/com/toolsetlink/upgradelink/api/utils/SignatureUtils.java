// SignatureUtils.java
package com.toolsetlink.upgradelink.api.utils;

import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class SignatureUtils {
//    public static String timeRFC3339() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
//        return sdf.format(new Date());
//    }

    public static String timeRFC3339() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        Date date = new Date();
        String formattedDate = sdf.format(date);

        // 获取时区偏移量
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        int offsetInMillis = timeZone.getOffset(calendar.getTimeInMillis());

        // 计算小时和分钟的偏移量
        int offsetHours = Math.abs(offsetInMillis) / (1000 * 60 * 60);
        int offsetMinutes = Math.abs(offsetInMillis) / (1000 * 60) % 60;

        // 构建时区偏移量字符串
        String offsetString;
        if (offsetInMillis >= 0) {
            offsetString = String.format("+%02d:%02d", offsetHours, offsetMinutes);
        } else {
            offsetString = String.format("-%02d:%02d", offsetHours, offsetMinutes);
        }

        // 拼接日期和时区偏移量
        return formattedDate + offsetString;
    }

    public static String generateNonce() {
//        return UUID.randomUUID().toString();
        // 生成8字节随机数据并转换为16位十六进制字符串
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }

    public static String generateSignature(String body, String nonce, String secretKey, String timestamp, String uri) throws Exception {
        List<String> parts = new ArrayList<>();
        if (body != null && !body.isEmpty()) {
            parts.add("body=" + body);
        }
        parts.add("nonce=" + nonce);
        parts.add("secretKey=" + secretKey);
        parts.add("timestamp=" + timestamp);
        parts.add("url=" + uri);

        // 手动拼接字符串替代 String.join() 以兼容所有 Android 版本
        StringBuilder signStrBuilder = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            if (i > 0) {
                signStrBuilder.append("&");
            }
            signStrBuilder.append(parts.get(i));
        }
        String signStr = signStrBuilder.toString();

        MessageDigest md = MessageDigest.getInstance("MD5");
        // 使用显式字符编码替代 StandardCharsets 以增强兼容性
        byte[] hash = md.digest(signStr.getBytes("UTF-8"));

        // 转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}