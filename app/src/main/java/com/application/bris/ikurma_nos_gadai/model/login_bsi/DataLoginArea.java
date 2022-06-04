package com.application.bris.ikurma_nos_gadai.model.login_bsi;

import com.google.gson.annotations.SerializedName;

public class DataLoginArea {
    @SerializedName("id")
    private int idArea;
    @SerializedName("branch_name")
    private String namaArea;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNamaArea() {
        return namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }
}
