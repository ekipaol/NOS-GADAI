package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataUjiAcak {
    @SerializedName("KodeCabang")
    private String Cabang;
    @SerializedName("NamaSesuaiKTP")
    private String NamaNasabah;
    @SerializedName("NoAplikasi")
    private String NomorAplikasiGadai;
    @SerializedName("TanggalCair")
    private String TanggalCair;
    @SerializedName("TanggalPencairan")
    private String TanggalPencairan;
    @SerializedName("TanggalJatuhTempo")
    private String TanggalJatuhTempo;
    @SerializedName("LDNumber")
    private String LDNumber;

    public String getLDNumber() {
        return LDNumber;
    }

    public void setLDNumber(String LDNumber) {
        this.LDNumber = LDNumber;
    }

    public String getCabang() {
        return Cabang;
    }

    public void setCabang(String cabang) {
        this.Cabang = cabang;
    }

    public String getNamaNasabah() {
        return NamaNasabah;
    }

    public void setNamaNasabah(String nasabah) {
        this.NamaNasabah = nasabah;
    }

    public String getNomorAplikasiGadai() {
        return NomorAplikasiGadai;
    }

    public void setNomorAplikasiGadai(String nomorAplikasiGadai) {
        this.NomorAplikasiGadai = nomorAplikasiGadai;
    }

    public String getTanggalJatuhTempo() {
        return TanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.TanggalJatuhTempo = tanggalJatuhTempo;
    }


    public String getTanggalCair() {
        return TanggalCair;
    }

    public void setTanggalCair(String tanggalCair) {
        this.TanggalCair = tanggalCair;
    }

    public String getTanggalPencairan() {
        return TanggalPencairan;
    }

    public void setTanggalPencairan(String tanggalPencairan) {
        TanggalPencairan = tanggalPencairan;
    }
}
