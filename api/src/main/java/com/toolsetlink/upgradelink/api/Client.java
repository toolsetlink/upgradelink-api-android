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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final Gson gson = new Gson();
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final String DEFAULT_PROTOCOL = "HTTPS";
    private static final String DEFAULT_ENDPOINT = "api.upgrade.toolsetlink.com";

    private final String _accessKey;
    private final String _secretKey;
    private final String _protocol;
    private final String _endpoint;

    public Client(Config config) {
        this._accessKey = config.accessKey;
        this._secretKey = config.secretKey;
        this._protocol = Objects.equals(config.protocol, "HTTPS") ? "HTTPS" : DEFAULT_PROTOCOL;
        this._endpoint = Objects.requireNonNullElse(config.endpoint, DEFAULT_ENDPOINT);
    }

    public UrlUpgradeResponse UrlUpgrade(UrlUpgradeRequest request) throws Exception {
        return performRequest(request, "/v1/url/upgrade", UrlUpgradeResponse.class);
    }

    public FileUpgradeResponse FileUpgrade(FileUpgradeRequest request) throws Exception {
        return performRequest(request, "/v1/file/upgrade", FileUpgradeResponse.class);
    }

    public ApkUpgradeResponse ApkUpgrade(ApkUpgradeRequest request) throws Exception {
        return performRequest(request, "/v1/apk/upgrade", ApkUpgradeResponse.class);
    }

    public AppReportResponse ApkUpgrade(AppReportUpgradeRequest request) throws Exception {
        return performRequest(request, "/v1/app/report", AppReportUpgradeResponse.class);
    }

    public ConfigurationUpgradeResponse ConfigurationUpgrade(ConfigurationUpgradeRequest request) throws Exception {
        return performRequest(request, "/v1/configuration/upgrade", ConfigurationUpgradeResponse.class);
    }

    public void UrlUpgradeAsync(UrlUpgradeRequest request, Callback<UrlUpgradeResponse> callback) {
        executeRequest(request, "/v1/url/upgrade", callback, UrlUpgradeResponse.class);
    }

    public void FileUpgradeAsync(FileUpgradeRequest request, Callback<FileUpgradeResponse> callback) {
        executeRequest(request, "/v1/file/upgrade", callback, FileUpgradeResponse.class);
    }

    public void ApkUpgradeAsync(ApkUpgradeRequest request, Callback<ApkUpgradeResponse> callback) {
        executeRequest(request, "/v1/apk/upgrade", callback, ApkUpgradeResponse.class);
    }

    public void ConfigurationUpgradeAsync(ConfigurationUpgradeRequest request, Callback<ConfigurationUpgradeResponse> callback) {
        executeRequest(request, "/v1/configuration/upgrade", callback, ConfigurationUpgradeResponse.class);
    }

    public void AppReportAsync(AppReportRequest request, Callback<AppReportResponse> callback) {
        executeRequest(request, "/v1/app/report", callback, AppReportResponse.class);
    }

    private <T> void executeRequest(Object request, String uri, Callback<T> callback, Class<T> responseClass) {
        executorService.submit(() -> {
            try {
                T result = performRequest(request, uri, responseClass);
                callback.onSuccess(result);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Request execution failed", e);
                callback.onFailure(e);
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

        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseData = response.body().string();
                try {
                    return gson.fromJson(responseData, responseClass);
                } catch (com.google.gson.JsonSyntaxException e) {
                    throw new Exception("JSON parsing error: " + e.getMessage());
                }
            } else {
                assert response.body() != null;
                String responseData = response.body().string();
                throw new IOException("Unexpected msg: " + responseData);
            }
        }
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(Throwable t);
    }
}