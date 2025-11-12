package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ApkVersionDataResponse {
    public String apkKey;
    public String packageName;
    public String versionName;
    public Integer versionCode;
    public String description;

    @NonNull
    @Override
    public String toString() {
        return "ApkUpgradeDataResponse{" +
                "apkKey='" + apkKey + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", description='" + description + '\'' +
                '}';
    }

}