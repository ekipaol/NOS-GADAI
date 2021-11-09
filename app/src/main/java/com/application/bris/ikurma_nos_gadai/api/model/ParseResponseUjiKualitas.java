package com.application.bris.ikurma_nos_gadai.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseUjiKualitas {

    @SerializedName("statusMsg")
    private String statusMsg;

    @SerializedName("data")
    private JsonObject data;

    @SerializedName("channel")
    private String channel;

    @SerializedName("status")
    private String status;

    @SerializedName("rrn")
    private String rrn;

    public String getStatusMsg() {
        return statusMsg;
    }

    public JsonObject getData() {
        return data;
    }

    public String getChannel() {
        return channel;
    }

    public String getStatus() {
        return status;
    }

    public String getRrn() {
        return rrn;
    }

    public String getMessage() {
        return null;
    }
}
