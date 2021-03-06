package com.application.bris.ikurma_nos_gadai.api.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseUjiKualitas {

    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("listPoto")
    private JsonArray listPoto;
    @SerializedName("channel")
    private String channel;
    @SerializedName("status")
    private String status;
    @SerializedName("rrn")
    private String rrn;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonArray getListPoto() {
        return listPoto;
    }

    public String getRrn() { return rrn;
    }
}
