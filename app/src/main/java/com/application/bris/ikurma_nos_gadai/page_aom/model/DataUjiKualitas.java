package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataUjiKualitas {
    @SerializedName("KodeCabang")
    private String Cabang;
    @SerializedName("NamaSesuaiKTP")
    private String NamaNasabah;
    @SerializedName("NoAplikasi")
    private String NomorAplikasiGadai;
    @SerializedName("TanggalPencairan")
    private String TanggalPencairan;
    @SerializedName("TanggalCair")
    private String TanggalTransaksi;
    @SerializedName("TanggalJatuhTempo")
    private String TanggalJatuhTempo;

    public String getCabang() {
        return Cabang ;
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

    public String getTanggalTransaksi() {
        return TanggalTransaksi;
    }
    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.TanggalTransaksi = tanggalTransaksi;
    }

    public String getTanggalJatuhTempo(){
        return TanggalJatuhTempo;
    }
    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.TanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getTanggalPencairan() {
        return TanggalPencairan;
    }

    public void setTanggalPencairan(String tanggalPencairan) {
        TanggalPencairan = tanggalPencairan;
    }

}
