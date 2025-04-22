package com.toolsetlink.upgradelink.api;

import com.toolsetlink.upgradelink.api.models.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SimpleClientTest {

    private Client client;

    @Before
    public void setUp() {
        Config config = new Config();
        config.accessKey = "mui2W50H1j-OC4xD6PgQag";
        config.secretKey = "PEbdHFGC0uO_Pch7XWBQTMsFRxKPQAM2565eP8LJ3gc";
        client = new Client(config);
    }

    @Test
    public void getUrlUpgrade_shouldCallCallbackOnSuccess() throws IOException {
        UrlUpgradeRequest request = new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );

        // 创建一个匿名的 Callback 实现
        Client.Callback<UrlUpgradeResponse> callback = new Client.Callback<UrlUpgradeResponse>() {
            @Override
            public void onSuccess(UrlUpgradeResponse response) {
                System.out.println("UrlUpgrade 请求成功，响应结果: " + response);
                // 验证响应是否成功
                assert response != null;
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("UrlUpgrade 请求失败，异常信息: " + e.getMessage());
                assert false : "Request failed: Exception: " + e.getMessage();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("UrlUpgrade 请求失败，异常信息: " + t.getMessage());
                assert false : "Request failed: Throwable: " + t.getMessage();
            }

        };

        client.getUrlUpgrade(request, callback);
    }

    @Test
    public void getFileUpgrade_shouldCallCallbackOnSuccess() throws IOException {
        FileUpgradeRequest request = new FileUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );

        // 创建一个匿名的 Callback 实现
        Client.Callback<FileUpgradeResponse> callback = new Client.Callback<FileUpgradeResponse>() {
            @Override
            public void onSuccess(FileUpgradeResponse response) {
                System.out.println("FileUpgrade 请求成功，响应结果: " + response);
                // 验证响应是否成功
                assert response != null;
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("FileUpgrade 请求失败，异常信息: " + e.getMessage());
                assert false : "Request failed: Exception: " + e.getMessage();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FileUpgrade 请求失败，异常信息: " + t.getMessage());
                assert false : "Request failed:  Throwable: " + t.getMessage();
            }
        };

        client.getFileUpgrade(request, callback);
    }
}