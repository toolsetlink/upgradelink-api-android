package com.toolsetlink.upgradelink.api.models;

import com.google.gson.annotations.SerializedName;

public class AppReportRequest {
    @SerializedName("timestamp")
    public String timestamp;
    @SerializedName("appKey")
    public String appKey;
    @SerializedName("eventData")
    public EventData eventData;

    public String eventType;

    public AppReportRequest(String eventType, String timestamp, String appKey) {
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.appKey = appKey;
        this.eventData = new EventData();
    }

    public static class EventData {
        @SerializedName("launchTime")
        public String launchTime;
        @SerializedName("downloadVersionCode")
        public Integer downloadVersionCode;
        @SerializedName("upgradeVersionCode")
        public Integer upgradeVersionCode;
        @SerializedName("code")
        public Integer code;
        @SerializedName("versionCode")
        public Integer versionCode;
        @SerializedName("devModelKey")
        public String devModelKey;
        @SerializedName("devKey")
        public String devKey;
        @SerializedName("target")
        public String target;
        @SerializedName("arch")
        public String arch;
    }
}