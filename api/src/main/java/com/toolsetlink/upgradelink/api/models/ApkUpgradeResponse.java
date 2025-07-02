package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ApkUpgradeResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;
    public ApkUpgradeDataResponse data;

    @NonNull
    @Override
    public String toString() {
        return "ApkUpgradeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                ", data=" + data +
                '}';
    }

}