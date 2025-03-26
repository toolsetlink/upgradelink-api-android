package com.toolsetlink.upgradelink.api.models;

public class UrlUpgradeRequest {
    public String urlKey;
    public Integer versionCode;
    public Integer appointVersionCode;
    public String devModelKey;
    public String devKey;

    public UrlUpgradeRequest(String urlKey, Integer versionCode, Integer appointVersionCode, String devModelKey, String devKey) {
        this.urlKey = urlKey;
        this.versionCode = versionCode;
        this.appointVersionCode = appointVersionCode;
        this.devModelKey = devModelKey;
        this.devKey = devKey;
    }
}