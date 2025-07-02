package com.toolsetlink.upgradelink.api.models;

import androidx.annotation.NonNull;

public class AppReportResponse {
    public Integer code;
    public String msg;
    public String docs;
    public String traceId;

    public AppReportResponse(Integer code, String msg, String docs, String traceId) {
        this.code = code;
        this.msg = msg;
        this.docs = docs;
        this.traceId = traceId;
    }

    @NonNull
    @Override
    public String toString() {
        return "FileUpgradeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", docs='" + docs + '\'' +
                ", traceId='" + traceId + '\'' +
                '}';
    }

}