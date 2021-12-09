package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class ListBrankas {
    @SerializedName("KodeCabang")
    private String Cabang;
    @SerializedName("idBrankas")
    private String IdBrankas;
    @SerializedName("IsiLaci")
    private JsonArray IsiLaci;
    @SerializedName("namaCabang")
    private String namaCabang;

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getCabang() {
        return Cabang ;
    }
    public void setCabang(String cabang) {
        this.Cabang = cabang;
    }

    public String getIdBrankas() {
        return IdBrankas;
    }

    public void setIdBrankas(String IdBrankas) {
        this.IdBrankas = IdBrankas;
    }

    public JsonArray getIsiLaci() {
        return IsiLaci;
    }
    public void setIsiLaci(JsonArray IsiLaci) {
        this.IsiLaci = IsiLaci;
    }

}
