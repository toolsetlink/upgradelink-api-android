// SignatureUtils.java
package com.toolsetlink.upgradelink.api.utils;

import android.annotation.SuppressLint;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class SignatureUtils {

    @SuppressLint("DefaultLocale")
    public static String timeRFC3339() {
        // 方案1：使用UTC时间（推荐用于跨时区场景）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        String formattedDate = sdf.format(date);
        
        // UTC时间使用Z时区标识符
        return formattedDate + "Z";
    }

    // 生成8字节随机数据并转换为16位十六进制字符串
    public static String generateNonce() {
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