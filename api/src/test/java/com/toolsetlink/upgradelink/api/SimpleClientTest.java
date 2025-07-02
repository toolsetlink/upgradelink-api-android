package com.toolsetlink.upgradelink.api;

import com.toolsetlink.upgradelink.api.models.*;
import org.junit.*;

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
    public void urlUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        UrlUpgradeRequest request = new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );

        try {
            UrlUpgradeResponse response = client.UrlUpgrade(request);
            System.out.println("getUrlUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getUrlUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void urlUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
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

        client.UrlUpgradeAsync(request, callback);
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
            FileUpgradeResponse response = client.FileUpgrade(request);
            System.out.println("getFileUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getFileUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void fileUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
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

        client.FileUpgradeAsync(request, callback);
        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }


    @Test
    public void getApkUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        ApkUpgradeRequest request = new ApkUpgradeRequest(
                "isVZBUvkFhv6oHxk_X-D0Q",
                1,
                0,
                "",
                ""
        );

        try {
            ApkUpgradeResponse response = client.ApkUpgrade(request);
            System.out.println("getApkUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getApkUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void apkUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
        ApkUpgradeRequest request = new ApkUpgradeRequest(
                "isVZBUvkFhv6oHxk_X-D0Q",
                1,
                0,
                "",
                ""
        );
        CountDownLatch latch = new CountDownLatch(1);

        Client.Callback<ApkUpgradeResponse> callback = new Client.Callback<>() {
            @Override
            public void onSuccess(ApkUpgradeResponse response) {
                System.out.println("getApkUpgrade 请求响应: " + response.toString());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Assert.fail("getApkUpgrade 请求失败: " + t.getClass().getSimpleName() + ": " + t.getMessage());
                latch.countDown();
            }
        };

        client.ApkUpgradeAsync(request, callback);
        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }




    @Test
    public void getConfigurationUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        ConfigurationUpgradeRequest request = new ConfigurationUpgradeRequest(
                "q1hfB1VUQaK9VksTZGPU1Q",
                1,
                0,
                "",
                ""
        );

        try {
            ConfigurationUpgradeResponse response = client.ConfigurationUpgrade(request);
            System.out.println("getConfigurationUpgrade 请求响应: " + response.toString());
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
            // 让测试失败，并给出错误信息
            Assert.fail("getConfigurationUpgrade 请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }


    @Test
    public void configurationUpgradeAsync_shouldCallCallbackOnSuccess() throws Exception {
        ConfigurationUpgradeRequest request = new ConfigurationUpgradeRequest(
                "q1hfB1VUQaK9VksTZGPU1Q",
                1,
                0,
                "",
                ""
        );
        CountDownLatch latch = new CountDownLatch(1);

        Client.Callback<ConfigurationUpgradeResponse> callback = new Client.Callback<>() {
            @Override
            public void onSuccess(ConfigurationUpgradeResponse response) {
                System.out.println("getConfigurationUpgrade 请求响应: " + response.toString());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Assert.fail("getConfigurationUpgrade 请求失败: " + t.getClass().getSimpleName() + ": " + t.getMessage());
                latch.countDown();
            }
        };

        client.ConfigurationUpgradeAsync(request, callback);
        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }
}