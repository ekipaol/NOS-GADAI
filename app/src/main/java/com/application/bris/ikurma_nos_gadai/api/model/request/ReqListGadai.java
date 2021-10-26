package com.application.bris.ikurma_nos_gadai.api.model.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ReqListGadai {
    @SerializedName("channel")
    private String channel;
    @SerializedName("data")
    private JsonObject data;

    public void setkchannel(String channel) { this.channel = channel;}
    public void setdata(JsonObject data) { this.data = data;}

}