package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortfolioPawning {
    @SerializedName("NomorLD")
    @Expose
    private String nomorLD;
    @SerializedName("Outstanding")
    @Expose
    private String outstanding;
    @SerializedName("JenisProduk")
    @Expose
    private String jenisProduk;
    @SerializedName("TanggalCair")
    @Expose
    private String tanggalCair;
    @SerializedName("TanggalJatemp")
    @Expose
    private String tanggalJatemp;
    @SerializedName("KolLD")
    @Expose
    private String kolLD;
    @SerializedName("KOLCIF")
    @Expose
    private String kolcif;

    public String getNomorLD() {
        return nomorLD;
    }

    public void setNomorLD(String nomorLD) {
        this.nomorLD = nomorLD;
    }

    public String getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(String outstanding) {
        this.outstanding = outstanding;
    }

    public String getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public String getTanggalCair() {
        return tanggalCair;
    }

    public void setTanggalCair(String tanggalCair) {
        this.tanggalCair = tanggalCair;
    }

    public String getTanggalJatemp() {
        return tanggalJatemp;
    }

    public void setTanggalJatemp(String tanggalJatemp) {
        this.tanggalJatemp = tanggalJatemp;
    }

    public String getKolLD() {
        return kolLD;
    }

    public void setKolLD(String kolLD) {
        this.kolLD = kolLD;
    }

    public String getKolcif() {
        return kolcif;
    }

    public void setKolcif(String kolcif) {
        this.kolcif = kolcif;
    }
}
