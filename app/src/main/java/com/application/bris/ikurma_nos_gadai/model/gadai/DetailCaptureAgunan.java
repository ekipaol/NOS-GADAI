package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class DetailCaptureAgunan {
    @SerializedName("NoAplikasi")
    private String NoAplikasi;
    @SerializedName("KodeCabang")
    private String KodeCabang;
    @SerializedName("NamaSesuaiKTP")
    private String NamaSesuaiKTP;
    @SerializedName("NoKTP")
    private String NoKTP;
    @SerializedName("TanggalPencairan")
    private String TanggalPencairan;
    @SerializedName("TanggalJatuhTempo")
    private String TanggalJatuhTempo;
    @SerializedName("Tenor")
    private String Tenor;


    public String getNoAplikasi() {
        return NoAplikasi;
    }

    public void setNoAplikasi(String NoAplikasi) {
        this.NoAplikasi = NoAplikasi;
    }

    public String getKodeCabang() {
        return KodeCabang;
    }

    public void setKodeCabang(String cabang) {
        this.KodeCabang = KodeCabang;
    }

    public String getNamaSesuaiKTP() {
        return NamaSesuaiKTP;
    }

    public void setNamaSesuaiKTP(String NamaSesuaiKTP) {
        this.NamaSesuaiKTP = NamaSesuaiKTP;
    }

    public String getNoKTP() {
        return NoKTP;
    }

    public void setNoKTP(String NoKTP) {
        this.NoKTP = NoKTP;
    }

    public String getTanggalPencairan() {
        return TanggalPencairan;
    }

    public void setTanggalPencairan(String TanggalPencairan) {
        this.TanggalPencairan = TanggalPencairan;
    }

    public String getTanggalJatuhTempo() {
        return TanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String TanggalJatuhTempo) {
        this.TanggalJatuhTempo = TanggalJatuhTempo;
    }

    public String getTenor(){
        return Tenor;
    }
    public void setTenor(String Tenor) {
        this.Tenor = Tenor;
    }

    }
