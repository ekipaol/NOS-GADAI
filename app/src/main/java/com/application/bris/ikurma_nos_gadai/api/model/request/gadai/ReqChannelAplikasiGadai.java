package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqChannelAplikasiGadai {



    @SerializedName("channel")
    @Expose
    private String channel;

    @SerializedName("data")
    @Expose
    private ReqAplikasiGadai data;

    public ReqChannelAplikasiGadai() {
        channel="Mobile";
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public ReqAplikasiGadai getData() {
        return data;
    }

    public void setData(ReqAplikasiGadai data) {
        this.data = data;
    }
}
