package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataSerahTerima {
    @SerializedName("KodeCabang")
    private String Cabang;
    @SerializedName("NamaSesuaiKTP")
    private String NamaNasabah;
    @SerializedName("NoAplikasi")
    private String NomorAplikasiGadai;
    @SerializedName("TanggalCair")
    private String TanggalTransaksi;
    @SerializedName("TanggalJatuhTempo")
    private String TanggalJatuhTempo;
    @SerializedName("SBGENumber")
    private String SBGENumber;
    @SerializedName("PinjamanGadaiDiambil")
    private String PinjamanGadaiDiambil;
    @SerializedName("LDNumber")
    private String LDNumber;
    @SerializedName("FotoSerahTerima")
    private String fotoSerahTerima;
    @SerializedName("Description")
    private String description;
    @SerializedName("Pemberi")
    private String namaPemberi;
    @SerializedName("Aktifitas")
    private String aktifitas;
    @SerializedName("Penerima")
    private String namaPenerima;
    @SerializedName("konfirmasi")
    private String konfirmasi;


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

    public String getTanggalTransaksi() {
        return TanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.TanggalTransaksi = tanggalTransaksi;
    }

    public String getTanggalJatuhTempo() {
        return TanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.TanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getSBGENumber() {
        return SBGENumber;
    }

    public void setSBGENumber(String SBGENumber) {
        this.SBGENumber = SBGENumber;
    }

    public String getPinjamanGadaiDiambil() {
        return PinjamanGadaiDiambil;
    }

    public void setPinjamanGadaiDiambil(String PinjamanGadaiDiambil) {
        this.PinjamanGadaiDiambil = PinjamanGadaiDiambil;
    }

    public String getLDNumber() {
        return LDNumber;
    }

    public void setLDNumber(String LDNumber) {
        this.LDNumber = LDNumber;
    }



    public String getFotoSerahTerima(){
        return fotoSerahTerima;
    }

    public String getDescription(){
        return description;
    }

    public String getNamaPemberi(){
        return namaPemberi;}
    public void setPemberi(String namaPemberi){
        this.namaPemberi = namaPemberi ;
    }



    public String getAktifitas(){
        return aktifitas;
    }

    public String getNamaPenerima(){
        return namaPenerima;}
    public void setNamaPenerima (String namaPemberi){
        this.namaPenerima = namaPenerima ;
    }

    public String getKonfirmasi(){
        return konfirmasi;
    }


}

