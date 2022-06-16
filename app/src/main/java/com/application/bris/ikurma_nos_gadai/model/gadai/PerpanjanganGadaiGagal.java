package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class PerpanjanganGadaiGagal {

    @SerializedName("NoAplikasi")
    private String noAplikasi;

    @SerializedName("NomorCIF")
    private String nomorCIF;

    @SerializedName("OSPembiayaan")
    private String oSPembiayaan;

    @SerializedName("TanggalPencairan")
    private String tanggalPencairan;

    @SerializedName("Produk")
    private String produk;

    @SerializedName("NamaNasabah")
    private String namaNasabah;

    @SerializedName("State")
    private String state;

    @SerializedName("ActivityID")
    private int activityID;

    @SerializedName("NoLoan")
    private String noLoan;

    public void setNoAplikasi(String noAplikasi){
        this.noAplikasi = noAplikasi;
    }

    public String getNoAplikasi(){
        return noAplikasi;
    }

    public void setNomorCIF(String nomorCIF){
        this.nomorCIF = nomorCIF;
    }

    public String getNomorCIF(){
        return nomorCIF;
    }

    public void setOSPembiayaan(String oSPembiayaan){
        this.oSPembiayaan = oSPembiayaan;
    }

    public String getOSPembiayaan(){
        return oSPembiayaan;
    }

    public void setTanggalPencairan(String tanggalPencairan){
        this.tanggalPencairan = tanggalPencairan;
    }

    public String getTanggalPencairan(){
        return tanggalPencairan;
    }

    public void setProduk(String produk){
        this.produk = produk;
    }

    public String getProduk(){
        return produk;
    }

    public void setNamaNasabah(String namaNasabah){
        this.namaNasabah = namaNasabah;
    }

    public String getNamaNasabah(){
        return namaNasabah;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setActivityID(int activityID){
        this.activityID = activityID;
    }

    public int getActivityID(){
        return activityID;
    }

    public void setNoLoan(String noLoan){
        this.noLoan = noLoan;
    }

    public String getNoLoan(){
        return noLoan;
    }
}