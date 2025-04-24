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

    @Test(timeout = 20_000) // 设置全局测试超时
    public void getUrlUpgrade_shouldCallCallbackOnSuccess() throws Exception {
        UrlUpgradeRequest request = new UrlUpgradeRequest(
                "uJ47NPeT7qjLa1gL3sVHqw",
                1,
                0,
                "",
                ""
        );
//        CountDownLatch latch = new CountDownLatch(1);

        Client.Callback<UrlUpgradeResponse> callback = new Client.Callback<>() {
            @Override
            public void onSuccess(UrlUpgradeResponse response) {
                System.out.println("响应详情: " + response.code.toString());
//                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                Assert.fail("请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
//                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Assert.fail("请求失败: " + t.getClass().getSimpleName() + ": " + t.getMessage());
//                latch.countDown();
            }
        };

        client.getUrlUpgrade(request, callback);
//        Assert.assertTrue("测试超时", latch.await(15, TimeUnit.SECONDS));
    }

    // 同理修改 getFileUpgrade 测试方法
}