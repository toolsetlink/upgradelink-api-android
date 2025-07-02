package com.toolsetlink.upgradelink.api.models;

public class ApkUpgradeRequest {
    public String apkKey;
    public Integer versionCode;
    public Integer appointVersionCode;
    public String devModelKey;
    public String devKey;

    public ApkUpgradeRequest(String apkKey, Integer versionCode, Integer appointVersionCode, String devModelKey, String devKey) {
        this.apkKey = apkKey;
        this.versionCode = versionCode;
        this.appointVersionCode = appointVersionCode;
        this.devModelKey = devModelKey;
        this.devKey = devKey;
    }
}