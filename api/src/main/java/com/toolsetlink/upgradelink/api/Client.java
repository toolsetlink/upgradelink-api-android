package com.toolsetlink.upgradelink.api;

import com.google.gson.Gson;
import com.toolsetlink.upgradelink.api.models.*;
import com.toolsetlink.upgradelink.api.utils.SignatureUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final String _accessKey;
    private final String _secretKey;
    private final String _protocol;
    private final String _endpoint;

    public Client(Config config) {
        this._accessKey = config.accessKey;
        this._secretKey = config.secretKey;
        if (Objects.equals(config.protocol, "HTTPS")) {
            this._protocol = "HTTPS";
        } else {
            this._protocol = "HTTP";
        }

        if (Objects.equals(config.endpoint, "")) {
            this._endpoint = "api.upgrade.toolsetlink.com";
        } else {
            this._endpoint = config.endpoint;
        }
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
        String signature = SignatureUtils.generateSignature(bodyStr, nonce, _secretKey, timestamp, uri);

        Map<String, String> headers = new HashMap<>();
        headers.put("host", _endpoint);
        headers.put("content-type", "application/json");
        headers.put("X-Timestamp", timestamp);
        headers.put("X-Nonce", nonce);
        headers.put("X-AccessKey", _accessKey);
        headers.put("X-Signature", signature);

        RequestBody requestBody = RequestBody.create(bodyStr, JSON);
        Request httpRequest = new Request.Builder()
                .url(_protocol+"://" + _endpoint + uri)
                .post(requestBody)
                .headers(okhttp3.Headers.of(headers))
                .build();

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
        String signature = SignatureUtils.generateSignature(bodyStr, nonce, _secretKey, timestamp, uri);

        Map<String, String> headers = new HashMap<>();
        headers.put("host", _endpoint);
        headers.put("content-type", "application/json");
        headers.put("X-Timestamp", timestamp);
        headers.put("X-Nonce", nonce);
        headers.put("X-AccessKey", _accessKey);
        headers.put("X-Signature", signature);

        RequestBody requestBody = RequestBody.create(bodyStr, JSON);
        Request httpRequest = new Request.Builder()
                .url(_protocol+"://" + _endpoint + uri)
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