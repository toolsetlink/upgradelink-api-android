// This file is auto-generated, don't edit it. Thanks.
package com.toolsetlink.upgradelink.api.models;

public class Config {
    public String accessKey;
    public String secretKey;
    public String protocol;
    public String endpoint;

    public static Config build(java.util.Map<String, ?> map) throws Exception {
        Config config = new Config();
        if (map.containsKey("accessKey")) {
            config.accessKey = (String) map.get("accessKey");
        }
        if (map.containsKey("secretKey")) {
            config.secretKey = (String) map.get("secretKey");
        }
        if (map.containsKey("protocol")) {
            config.protocol = (String) map.get("protocol");
        }
        if (map.containsKey("endpoint")) {
            config.endpoint = (String) map.get("endpoint");
        }
        return config;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    public String getAccessKey() {
        return this.accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public String getSecretKey() {
        return this.secretKey;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getProtocol() {
        return this.protocol;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getEndpoint() {
        return this.endpoint;
    }

}
