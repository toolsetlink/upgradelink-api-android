package com.toolsetlink.upgradelink.api;

import com.google.gson.Gson;
import com.toolsetlink.upgradelink.api.Client;
import com.toolsetlink.upgradelink.api.models.*;
import com.toolsetlink.upgradelink.api.Client.Callback;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SimpleClientTest {

    private Client client;
    private final String accessKeyId = "mui2W50H1j-OC4xD6PgQag";
    private final String accessKeySecret = "PEbdHFGC0uO_Pch7XWBQTMsFRxKPQAM2565eP8LJ3gc";

    @Before
    public void setUp() {
        client = new Client(accessKeyId, accessKeySecret);
    }

    @Test
    public void shouldTriggerSuccessCallback() throws Exception {

        CountDownLatch latch = new CountDownLatch(1);

        // 使用带打印功能的回调
        TestCallback callback = new TestCallback(latch) {
            @Override
            public void onSuccess(UrlUpgradeResponse result) {
                // 打印返回参数
                System.out.println("Received response: ");
                System.out.println("Code: " + result.code);
                System.out.println("msg: " + result.msg);
                super.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Error: " + e.getMessage());
                super.onFailure(e);
            }
        };

        client.getUrlUpgrade(new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        ), callback);

        assertTrue(latch.await(1, TimeUnit.SECONDS));

        // 也可以通过Gson格式化输出
        if (callback.result != null) {
            String json = new Gson().toJson(callback.result);
            System.out.println("Formatted JSON response: \n" + json);
        }
    }
    @Test
    public void shouldTriggerFailureCallback() throws Exception {
        // 模拟失败响应
        CountDownLatch latch = new CountDownLatch(1);

        // 使用带打印功能的回调
        TestCallback callback = new TestCallback(latch) {
            @Override
            public void onSuccess(UrlUpgradeResponse result) {
                // 打印返回参数
                System.out.println("Received response: ");
                System.out.println("Code: " + result.code);
                System.out.println("msg: " + result.msg);
                super.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Error: " + e.getMessage());
                super.onFailure(e);
            }
        };

        client.getUrlUpgrade(new UrlUpgradeRequest(
                "123123123",
                1,
                0,
                "",
                ""
        ), callback);

        assertTrue(latch.await(1, TimeUnit.SECONDS));

        // 也可以通过Gson格式化输出
        if (callback.result != null) {
            String json = new Gson().toJson(callback.result);
            System.out.println("Formatted JSON response: \n" + json);
        }
    }

    private static class TestCallback implements Callback<UrlUpgradeResponse> {
        final CountDownLatch latch;
        UrlUpgradeResponse result;
        Exception error;

        TestCallback(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onSuccess(UrlUpgradeResponse result) {
            this.result = result;
            latch.countDown();
        }

        @Override
        public void onFailure(Exception e) {
            this.error = e;
            latch.countDown();
        }
    }
}