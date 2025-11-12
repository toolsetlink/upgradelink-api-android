package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class ApkVersionResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;
    public ApkVersionDataResponse data;

    @NonNull
    @Override
    public String toString() {
        return "ApkVersionResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                ", data=" + data +
                '}';
    }

}