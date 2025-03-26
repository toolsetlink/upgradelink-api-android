// SignatureUtils.java
package com.toolsetlink.upgradelink.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class SignatureUtils {
    public static String timeRFC3339() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
        return sdf.format(new Date());
    }

    public static String generateNonce() {
        return UUID.randomUUID().toString();
    }

    public static String generateSignature(String bodyStr, String nonce, String accessKeySecret, String timestamp, String uri) {
        // 这里需要实现具体的签名算法，原代码未给出实现，需要根据实际情况补充
        return "";
    }
}