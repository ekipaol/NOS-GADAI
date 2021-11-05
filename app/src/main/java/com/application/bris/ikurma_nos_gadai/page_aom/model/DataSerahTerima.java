package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataSerahTerima {
    @SerializedName("KodeCabang")
    private String Cabang;
    @SerializedName("NamaNasabah")
    private String NamaNasabah;
    @SerializedName("NoAplikasi")
    private String NomorAplikasiGadai;
    @SerializedName("TanggalCair")
    private String TanggalTransaksi;
    @SerializedName("TanggalAktifitas")
    private String TanggalAktifitas;
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
    @SerializedName("IDPemberi")
    private String IDPemberi;
    @SerializedName("NamaPemberi")
    private String NamaPemberi;
    @SerializedName("Aktifitas")
    private String aktifitas;
    @SerializedName("IDPenerima")
    private String IDPenerima;
    @SerializedName("NamaPenerima")
    private String NamaPenerima;
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

    public String getTanggalAktifitas() {
        return TanggalAktifitas;
    }

    public void setTanggalAktifitas(String TanggalAktifitas) {
        this.TanggalAktifitas = TanggalAktifitas;
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

    public String getIDPemberi(){
        return IDPemberi;}
    public void setPemberi(String IDPemberi){
        this.IDPemberi = IDPemberi ;
    }


    public String getNamaPemberi(){
        return NamaPemberi;}
    public void sNamaPemberi(String NamaPemberi){
        this.NamaPemberi = NamaPemberi ;
    }



    public String getAktifitas(){
        return aktifitas;
    }

    public String getIDPenerima(){
        return IDPenerima;}
    public void setIDPenerima (String IDPemberi){
        this.IDPenerima = IDPenerima ;
    }

    public String getNamaPenerima(){
        return NamaPenerima;}
    public void setNamaPenerima (String NamaPenerima){
        this.NamaPenerima = NamaPenerima ;
    }

    public String getKonfirmasi(){
        return konfirmasi;
    }


}

