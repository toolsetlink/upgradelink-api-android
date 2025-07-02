package com.toolsetlink.upgradelink.api.models;

public class ConfigurationUpgradeRequest {
    public String configurationKey;
    public Integer versionCode;
    public Integer appointVersionCode;
    public String devModelKey;
    public String devKey;

    public ConfigurationUpgradeRequest(String configurationKey, Integer versionCode, Integer appointVersionCode, String devModelKey, String devKey) {
        this.configurationKey = configurationKey;
        this.versionCode = versionCode;
        this.appointVersionCode = appointVersionCode;
        this.devModelKey = devModelKey;
        this.devKey = devKey;
    }
}