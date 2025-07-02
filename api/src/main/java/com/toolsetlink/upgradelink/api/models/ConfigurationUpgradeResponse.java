package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ConfigurationUpgradeResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;
    public ConfigurationUpgradeDataResponse data;

    @NonNull
    @Override
    public String toString() {
        return "ConfigurationUpgradeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                ", data=" + data +
                '}';
    }

}