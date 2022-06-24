package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResubmit {
    @SerializedName("NamaNasabah")
    @Expose
    private String namaNasabah;
    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("NomorCIF")
    @Expose
    private String nomorCIF;
    @SerializedName("NoLoan")
    @Expose
    private String noLoan;
    @SerializedName("Produk")
    @Expose
    private String produk;
    @SerializedName("OSPembiayaan")
    @Expose
    private String oSPembiayaan;
    @SerializedName("TanggalPencairan")
    @Expose
    private String tanggalPencairan;
    @SerializedName("ActivityID")
    @Expose
    private Long activityID;
    @SerializedName("State")
    @Expose
    private String state;

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getNomorCIF() {
        return nomorCIF;
    }

    public void setNomorCIF(String nomorCIF) {
        this.nomorCIF = nomorCIF;
    }

    public String getNoLoan() {
        return noLoan;
    }

    public void setNoLoan(String noLoan) {
        this.noLoan = noLoan;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getoSPembiayaan() {
        return oSPembiayaan;
    }

    public void setoSPembiayaan(String oSPembiayaan) {
        this.oSPembiayaan = oSPembiayaan;
    }

    public String getTanggalPencairan() {
        return tanggalPencairan;
    }

    public void setTanggalPencairan(String tanggalPencairan) {
        this.tanggalPencairan = tanggalPencairan;
    }

    public Long getActivityID() {
        return activityID;
    }

    public void setActivityID(Long activityID) {
        this.activityID = activityID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
