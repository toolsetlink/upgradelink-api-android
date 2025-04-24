package com.toolsetlink.upgradelink.api;

import com.toolsetlink.upgradelink.api.models.*;
import org.junit.*;
import java.io.IOException;
import java.util.concurrent.*;

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
    public void getUrlUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        UrlUpgradeRequest request = new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );

        try {
            UrlUpgradeResponse response = client.getUrlUpgrade(request);
            System.out.println("getUrlUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getUrlUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void getUrlUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
        UrlUpgradeRequest request = new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );
        CountDownLatch latch = new CountDownLatch(1);

        Client.Callback<UrlUpgradeResponse> callback = new Client.Callback<>() {
            @Override
            public void onSuccess(UrlUpgradeResponse response) {
                System.out.println("getUrlUpgrade 请求响应: " + response.toString());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Assert.fail("getUrlUpgrade 请求失败: " + t.getClass().getSimpleName() + ": " + t.getMessage());
                latch.countDown();
            }
        };

        client.getUrlUpgradeAsync(request, callback);
        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }


    @Test
    public void getFileUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        FileUpgradeRequest request = new FileUpgradeRequest(
                "LOYlLXNy7wV3ySuh0XgtSg",
                1,
                0,
                "",
                ""
        );

        try {
            FileUpgradeResponse response = client.getFileUpgrade(request);
            System.out.println("getFileUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getUrlUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void getFileUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
        FileUpgradeRequest request = new FileUpgradeRequest(
                "LOYlLXNy7wV3ySuh0XgtSg",
                1,
                0,
                "",
                ""
        );
        CountDownLatch latch = new CountDownLatch(1);

        Client.Callback<FileUpgradeResponse> callback = new Client.Callback<>() {
            @Override
            public void onSuccess(FileUpgradeResponse response) {
                System.out.println("getFileUpgrade 请求响应: " + response.toString());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Assert.fail("getFileUpgrade 请求失败: " + t.getClass().getSimpleName() + ": " + t.getMessage());
                latch.countDown();
            }
        };

        client.getFileUpgradeAsync(request, callback);
        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }
}