package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqChannelDataApprove {
    @SerializedName("rrn")
    @Expose
    private String rrn;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("data")
    @Expose
    private ReqDataApprove data;

    @SerializedName("dataChanges")
    @Expose
    private ReqDataChanges dataChanges;

    @SerializedName("ChangeIndicator")
    @Expose
    private String changeIndicator;

    public String getChangeIndicator() {
        return changeIndicator;
    }

    public void setChangeIndicator(String changeIndicator) {
        this.changeIndicator = changeIndicator;
    }

    public ReqDataChanges getDataChanges() {
        return dataChanges;
    }

    public void setDataChanges(ReqDataChanges dataChanges) {
        this.dataChanges = dataChanges;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public ReqDataApprove getData() {
        return data;
    }

    public void setData(ReqDataApprove data) {
        this.data = data;
    }
}
