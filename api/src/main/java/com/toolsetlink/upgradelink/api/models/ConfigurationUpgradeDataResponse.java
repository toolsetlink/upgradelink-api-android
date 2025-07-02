package com.toolsetlink.upgradelink.api.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConfigurationUpgradeDataResponse {
    public String configurationKey;
    public String versionName;
    public Integer versionCode;
    public Integer upgradeType;
    public String promptUpgradeContent;
    @SerializedName("content")
    @Nullable
    private JsonObject content;

    /**
     * 获取原始JSON对象
     */
    @Nullable
    public JsonObject getContent() {
        return content;
    }

    /**
     * 将content解析为指定类型的对象
     * @param clazz 目标类型Class
     * @param <T> 泛型类型
     * @return 解析后的对象，若content为null则返回null
     * @throws com.google.gson.JsonSyntaxException 当JSON格式不匹配时抛出
     */
    @Nullable
    public <T> T getContentAs(@NonNull Class<T> clazz) {
        if (content == null) {
            return null;
        }
        return new Gson().fromJson(content, clazz);
    }

    /**
     * 检查content中是否包含指定字段
     */
    public boolean hasContentField(@NonNull String fieldName) {
        return content != null && content.has(fieldName);
    }

    /**
     * 获取content中指定字段的值
     */
    @Nullable
    public String getContentFieldAsString(@NonNull String fieldName) {
        if (!hasContentField(fieldName)) {
            return null;
        }
        return content.get(fieldName).getAsString();
    }

    @NonNull
    @Override
    public String toString() {
        return "ConfigurationUpgradeDataResponse{" +
                "configurationKey='" + configurationKey + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", upgradeType=" + upgradeType +
                ", promptUpgradeContent='" + promptUpgradeContent + '\'' +
                ", content='" + (content != null ? content.toString() : "null") +
                "'" +
                '}';
    }

}