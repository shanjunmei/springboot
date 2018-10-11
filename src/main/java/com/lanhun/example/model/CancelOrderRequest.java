package com.lanhun.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 撤单请求
 */
public class CancelOrderRequest {

    /**
     * 第三方桌台标识
     */
    @JsonProperty("external_table_id")
    private String externalTableId;

    /**
     * 撤单原因
     */
    private String reason;

    public String getExternalTableId() {
        return externalTableId;
    }

    public void setExternalTableId(String externalTableId) {
        this.externalTableId = externalTableId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
