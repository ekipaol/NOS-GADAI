package com.application.bris.ikurma_nos_gadai.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseUjiAcak {

    @SerializedName("NoAplikasi")
    private String noAplikasi;

    @SerializedName("WorkFlowStatus")
    private String workFlowStatus;

    @SerializedName("UjiKwalitasHariIni")
    private String ujiKwalitasHariIni;

    @SerializedName("kodeCabang")
    private String kodeCabang;

    public String getNoAplikasi(){
        return noAplikasi;
    }

    public String getWorkFlowStatus(){
        return workFlowStatus;
    }

    public String getUjiKwalitasHariIni(){
        return ujiKwalitasHariIni;
    }

    public String getKodeCabang(){
        return kodeCabang;
    }

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
