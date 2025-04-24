package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class FileUpgradeResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;
    public FileUpgradeDataResponse data;

    @NonNull
    @Override
    public String toString() {
        return "FileUpgradeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                ", data=" + data +
                '}';
    }

}