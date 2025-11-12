package com.toolsetlink.upgradelink.api.models;

public class ApkVersionRequest {
    public String apkKey;
    public Integer versionCode;

    public ApkVersionRequest(String apkKey, Integer versionCode) {
        this.apkKey = apkKey;
        this.versionCode = versionCode;
    }
}