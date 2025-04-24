package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class FileUpgradeDataResponse {
    public String fileKey;
    public String versionName;
    public Integer versionCode;
    public String urlPath;
    public Integer upgradeType;
    public String promptUpgradeContent;

    @NonNull
    @Override
    public String toString() {
        return "FileUpgradeDataResponse{" +
                "fileKey='" + fileKey + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", urlPath='" + urlPath + '\'' +
                ", upgradeType=" + upgradeType +
                ", promptUpgradeContent='" + promptUpgradeContent + '\'' +
                '}';
    }

}