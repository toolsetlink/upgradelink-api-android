package com.toolsetlink.upgradelink.api;

import android.util.Log;

import com.google.gson.Gson;
import com.toolsetlink.upgradelink.api.models.FileUpgradeRequest;
import com.toolsetlink.upgradelink.api.models.FileUpgradeResponse;
import com.toolsetlink.upgradelink.api.models.UrlUpgradeRequest;
import com.toolsetlink.upgradelink.api.models.UrlUpgradeResponse;
import com.toolsetlink.upgradelink.api.utils.SignatureUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {
    public static final String ENDPOINT = "api.upgrade.toolsetlink.com";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final String accessKeyId;
    private final String accessKeySecret;

    public Client(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public void getUrlUpgrade(UrlUpgradeRequest request, Callback<UrlUpgradeResponse> callback) {
        executorService.submit(() -> {
            try {
                UrlUpgradeResponse result = performUrlUpgrade(request);
                callback.onSuccess(result);
            } catch (Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void getFileUpgrade(FileUpgradeRequest request, Callback<FileUpgradeResponse> callback) {
        executorService.submit(() -> {
            try {
                FileUpgradeResponse result = performFileUpgrade(request);
                callback.onSuccess(result);
            } catch (Exception e) {
                callback.onFailure(e);
            }
        });
    }

    private UrlUpgradeResponse performUrlUpgrade(UrlUpgradeRequest request) throws Exception {
        String bodyStr = gson.toJson(request);
        String timestamp = SignatureUtils.timeRFC3339();
        String nonce = SignatureUtils.generateNonce();
        String uri = "/v1/url/upgrade";
        String signature = SignatureUtils.generateSignature(bodyStr, nonce, accessKeySecret, timestamp, uri);

        Map<String, String> headers = new HashMap<>();
        headers.put("host", ENDPOINT);
        headers.put("content-type", "application/json");
        headers.put("X-Timestamp", timestamp);
        headers.put("X-Nonce", nonce);
        headers.put("X-AccessKey", accessKeyId);
        headers.put("X-Signature", signature);

        RequestBody requestBody = RequestBody.create(bodyStr, JSON);
        Request httpRequest = new Request.Builder()
                .url("http://" + ENDPOINT + uri)
                .post(requestBody)
                .headers(okhttp3.Headers.of(headers))
                .build();

//        client = new OkHttpClient.Builder()
//                .eventListener(new EventListener() {
//                    @Override
//                    public void requestHeadersEnd(Call call, Request request) {
//                        System.out.println(request.headers());
//                    }
//
//                    @Override
//                    public void requestBodyEnd(Call call, long byteCount) {
//                    }
//                })
//                .build();

        Response response = client.newCall(httpRequest).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            String responseData = response.body().string();
            return gson.fromJson(responseData, UrlUpgradeResponse.class);
        } else {
            assert response.body() != null;
            String responseData = response.body().string();
            throw new IOException("Unexpected msg " + responseData);
        }
    }

    private FileUpgradeResponse performFileUpgrade(FileUpgradeRequest request) throws Exception {
        String bodyStr = gson.toJson(request);
        String timestamp = SignatureUtils.timeRFC3339();
        String nonce = SignatureUtils.generateNonce();
        String uri = "/v1/file/upgrade";
        String signature = SignatureUtils.generateSignature(bodyStr, nonce, accessKeySecret, timestamp, uri);

        Map<String, String> headers = new HashMap<>();
        headers.put("host", ENDPOINT);
        headers.put("content-type", "application/json");
        headers.put("X-Timestamp", timestamp);
        headers.put("X-Nonce", nonce);
        headers.put("X-AccessKey", accessKeyId);
        headers.put("X-Signature", signature);

        RequestBody requestBody = RequestBody.create(bodyStr, JSON);
        Request httpRequest = new Request.Builder()
                .url("http://" + ENDPOINT + uri)
                .post(requestBody)
                .headers(okhttp3.Headers.of(headers))
                .build();

        Response response = client.newCall(httpRequest).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            String responseData = response.body().string();
            return gson.fromJson(responseData, FileUpgradeResponse.class);
        } else {
            assert response.body() != null;
            String responseData = response.body().string();
            throw new IOException("Unexpected msg " + responseData);
        }
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }
}    