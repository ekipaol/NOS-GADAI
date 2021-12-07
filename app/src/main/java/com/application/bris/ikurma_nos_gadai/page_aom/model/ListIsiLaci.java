package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class ListIsiLaci {
    @SerializedName("idSlot")
    private String idSlot;
    @SerializedName("idLaci")
    private String idLaci;
    @SerializedName("idBrankas")
    private String idBrankas;
    @SerializedName("idCabangPemilik")
    private String idCabangPemilik;
    @SerializedName("NoAplikasi")
    private String NoAplikasi;
    @SerializedName("sizeSlot")
    private String sizeSlot;
    @SerializedName("NamaPemilik")
    private String NamaPemilik;
    @SerializedName("LDNumber")
    private String LDNumber;
    @SerializedName("TanggalPencairan")
    private String TanggalPencairan;
    @SerializedName("TanggalJatuhTempo")
    private String TanggalJatuhTempo;
    @SerializedName("WorkFlowStatus")
    private String WorkFlowStatus;
    @SerializedName("AgunanPembiayaan")
    private String AgunanPembiayaan;
    @SerializedName("StatusStockOpname")
    private String StatusOpname;
    @SerializedName("KodeAgunan")
    private String KodeAgunan;

    public String getKodeAgunan() {
        return KodeAgunan;
    }

    public void setKodeAgunan(String kodeAgunan) {
        KodeAgunan = kodeAgunan;
    }

    public String getidSlot() {
        return idSlot ;
    }
    public void setidSlot(String idSlot) {
        this.idSlot = idSlot;
    }

    public String getidLaci() {
        return idLaci;
    }
    public void setidLaci(String idLaci) {
        this.idLaci = idLaci;
    }

    public String getidBrankas() {
        return idBrankas;
    }
    public void setidBrankas(String idBrankas) {
        this.idBrankas = idBrankas;
    }

    public String getidCabangPemilik() {
        return idCabangPemilik ;
    }
    public void setidCabangPemilik(String idCabangPemilik) {
        this.idCabangPemilik = idCabangPemilik;
    }

    public String getNoAplikasi() {
        return NoAplikasi;
    }
    public void setNoAplikasi(String NoAplikasi) {
        this.NoAplikasi = NoAplikasi;
    }

    public String getsizeSlot() {
        return sizeSlot;
    }
    public void setsizeSlot(String sizeSlot) {
        this.sizeSlot = sizeSlot;
    }

    public String getNamaPemilik() {
        return NamaPemilik;
    }
    public void setNamaPemilik(String NamaPemilik) {
        this.NamaPemilik = NamaPemilik;
    }

    public String getWorkFlowStatus() {
        return WorkFlowStatus;
    }
    public void setWorkFlowStatus(String WorkFlowStatus) {
        this.WorkFlowStatus = WorkFlowStatus;
    }

    public String getAgunanPembiayaan() {
        return AgunanPembiayaan;
    }
    public void setAgunanPembiayaan(String AgunanPembiayaan) {
        this.AgunanPembiayaan = AgunanPembiayaan;
    }

    public String getStatusOpname() {
        return StatusOpname;
    }
    public void setStatusOpname(String StatusOpname) {
        this.StatusOpname = StatusOpname;
    }

    public String getLDNumber() {
        return LDNumber;
    }
    public void setLDNumber(String LDNumber) {
        this.LDNumber = LDNumber;
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
}
