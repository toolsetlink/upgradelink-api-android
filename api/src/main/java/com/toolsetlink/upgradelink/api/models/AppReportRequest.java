package com.toolsetlink.upgradelink.api.models;

public class AppReportRequest {
    public String fileKey;
    public Integer versionCode;
    public Integer appointVersionCode;
    public String devModelKey;
    public String devKey;

    public AppReportRequest(String fileKey, Integer versionCode, Integer appointVersionCode, String devModelKey, String devKey) {
        this.fileKey = fileKey;
        this.versionCode = versionCode;
        this.appointVersionCode = appointVersionCode;
        this.devModelKey = devModelKey;
        this.devKey = devKey;
    }
}