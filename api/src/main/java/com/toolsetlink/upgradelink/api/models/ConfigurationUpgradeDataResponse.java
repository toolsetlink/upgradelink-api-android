package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ConfigurationUpgradeDataResponse {
    public String configurationKey;
    public String versionName;
    public Integer versionCode;
    public Integer upgradeType;
    public String promptUpgradeContent;
    public String content;

    @NonNull
    @Override
    public String toString() {
        return "ConfigurationUpgradeDataResponse{" +
                "configurationKey='" + configurationKey + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", upgradeType=" + upgradeType +
                ", promptUpgradeContent='" + promptUpgradeContent + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}