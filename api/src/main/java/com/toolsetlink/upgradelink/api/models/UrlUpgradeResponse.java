package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class UrlUpgradeResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;
    public UrlUpgradeDataResponse data;

    @NonNull
    @Override
    public String toString() {
        return "UrlUpgradeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                ", data=" + data +
                '}';
    }
}
