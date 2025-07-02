package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ApkUpgradeDataResponse {
    public String apkKey;
    public String versionName;
    public Integer versionCode;
    public String urlPath;
    public Integer urlFileSize;
    public String urlFileMd5;
    public Integer upgradeType;
    public String promptUpgradeContent;

    @NonNull
    @Override
    public String toString() {
        return "ApkUpgradeDataResponse{" +
                "apkKey='" + apkKey + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", urlPath='" + urlPath + '\'' +
                ", urlFileSize='" + urlFileSize + '\'' +
                ", urlFileMd5='" + urlFileMd5 + '\'' +
                ", upgradeType=" + upgradeType +
                ", promptUpgradeContent='" + promptUpgradeContent + '\'' +
                '}';
    }

}