package com.application.bris.ikurma_nos_gadai.api.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class ParseResponseGadai {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonArray data;
    @SerializedName("channel")
    private String channel;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonArray getData() {
        return data;
    }

    public String getChannel() {
        return channel;
    }
}
