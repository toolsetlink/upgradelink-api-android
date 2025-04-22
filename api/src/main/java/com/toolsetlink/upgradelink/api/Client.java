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
import java.util.concurrent.TimeUnit;

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
    private static final String DEFAULT_PROTOCOL = "HTTP";
    private static final String DEFAULT_ENDPOINT = "api.upgrade.toolsetlink.com";

    private final String _accessKey;
    private final String _secretKey;
    private final String _protocol;
    private final String _endpoint;

    public Client(Config config) {
        this._accessKey = config.accessKey;
        this._secretKey = config.secretKey;
        this._protocol = Objects.equals(config.protocol, "HTTPS") ? "HTTPS" : DEFAULT_PROTOCOL;
        this._endpoint = Objects.equals(config.endpoint, "") ? DEFAULT_ENDPOINT : config.endpoint;
    }

    public void getUrlUpgrade(UrlUpgradeRequest request, Callback<UrlUpgradeResponse> callback) {
        executeRequest(request, "/v1/url/upgrade", callback, UrlUpgradeResponse.class);
    }

    public void getFileUpgrade(FileUpgradeRequest request, Callback<FileUpgradeResponse> callback) {
        executeRequest(request, "/v1/file/upgrade", callback, FileUpgradeResponse.class);
    }

    private <T> void executeRequest(Object request, String uri, Callback<T> callback, Class<T> responseClass) {
        executorService.submit(() -> {
            try {
                T result = performRequest(request, uri, responseClass);
                callback.onSuccess(result);
            } catch (IOException e) {
                callback.onFailure(new IOException("Network error: " + e.getMessage()));
            } catch (Exception e) {
                callback.onFailure(new Exception("Unexpected error: " + e.getMessage()));
            }
        });
    }

    private <T> T performRequest(Object request, String uri, Class<T> responseClass) throws Exception {
        String bodyStr = gson.toJson(request);
        String timestamp = SignatureUtils.timeRFC3339();
        String nonce = SignatureUtils.generateNonce();
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
                .url(_protocol + "://" + _endpoint + uri)
                .post(requestBody)
                .headers(okhttp3.Headers.of(headers))
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return gson.fromJson(responseData, responseClass);
            } else {
                String responseData = response.body().string();
                throw new IOException("Unexpected msg " + responseData);
            }
        }
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);

        void onFailure(Throwable t);
    }

    public static void shutdownExecutorService() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}    