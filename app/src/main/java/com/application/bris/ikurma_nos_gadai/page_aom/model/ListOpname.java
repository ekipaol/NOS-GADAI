package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class ListOpname {
    @SerializedName("Id")
    private String Id;
    @SerializedName("KodeCabang")
    private String KodeCabang;
    @SerializedName("TanggalAksesBrankas")
    private String TanggalAksesBrankas;
    @SerializedName("Inputer")
    private String Inputer;
    @SerializedName("Otorisator")
    private String Otorisator;
    @SerializedName("StatusRequest")
    private String StatusRequest;
    @SerializedName("KodeRequest")
    private String KodeRequest;
    @SerializedName("Catatan")
    private String Catatan;
    @SerializedName("namaCabang")
    private String namaCabang;

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getId() {
        return Id ;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    public String getKodeCabang() {
        return KodeCabang;
    }
    public void setKodeCabang(String KodeCabang) {
        this.KodeCabang = KodeCabang;
    }

    public String getTanggalAksesBrankas() {
        return TanggalAksesBrankas;
    }
    public void setTanggalAksesBrankas(String TanggalAksesBrankas) {
        this.TanggalAksesBrankas = TanggalAksesBrankas;
    }

    public String getInputer() {
        return Inputer ;
    }
    public void setInputer(String Inputer) {
        this.Inputer = Inputer;
    }

    public String getOtorisator() {
        return Otorisator;
    }
    public void setOtorisator(String Otorisator) {
        this.Otorisator = Otorisator;
    }

    public String getStatusRequest() {
        return StatusRequest;
    }
    public void setStatusRequest(String StatusRequest) {
        this.StatusRequest = StatusRequest;
    }

    public String getKodeRequest() {
        return KodeRequest;
    }
    public void setKodeRequest(String KodeRequest) {
        this.KodeRequest = KodeRequest;
    }

    public String getCatatan() {
        return Catatan;
    }
    public void setCatatan(String Catatan) {
        this.Catatan = Catatan;
    }
}
