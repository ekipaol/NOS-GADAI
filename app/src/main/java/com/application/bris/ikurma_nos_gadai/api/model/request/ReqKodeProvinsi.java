package com.application.bris.ikurma_nos_gadai.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqKodeProvinsi {

    @SerializedName("kodeProvinsi")
    private String kodeProvinsi;

    public String getKodeProvinsi() {
        return kodeProvinsi;
    }

    public void setKodeProvinsi(String kodeProvinsi) {
        this.kodeProvinsi = kodeProvinsi;
    }
}
