package com.application.bris.ikurma_nos_gadai.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqRumahFlpp {
    @SerializedName("idLokasi")
    private String id_lokasi;

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }
}
