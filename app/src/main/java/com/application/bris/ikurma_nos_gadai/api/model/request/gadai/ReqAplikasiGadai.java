package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqAplikasiGadai {
    @SerializedName("FilterKodeCabang")
    @Expose
    private String filterKodeCabang;
    @SerializedName("FilterNoAplikasi")
    @Expose
    private String filterNoAplikasi;
    @SerializedName("FilterNoKTP")
    @Expose
    private String filterNoKTP;
    @SerializedName("FilterPengusul")
    @Expose
    private String filterPengusul;
    @SerializedName("FilterReviewer")
    @Expose
    private String filterReviewer;
    @SerializedName("FilterPemutus")
    @Expose
    private String filterPemutus;
    @SerializedName("FilterAOPembiayaan")
    @Expose
    private String filterAOPembiayaan;
    @SerializedName("FilterWorkFlowStatus")
    @Expose
    private String filterWorkFlowStatus;
    @SerializedName("FilterNoCif")
    @Expose
    private String filterNoCif;
    @SerializedName("FilterSBGE")
    @Expose
    private String filterSBGE;
    @SerializedName("FilterKodeAgunan")
    @Expose
    private String filterKodeAgunan;
    @SerializedName("FilterLDNumber")
    @Expose
    private String filterLDNumber;
    @SerializedName("FilterUjiKwalitasKapan")
    @Expose
    private String filterUjiKwalitasKapan;
    @SerializedName("FilterTanggalPencairan ")
    @Expose
    private String filterTanggalPencairan;
    @SerializedName("FilterTanggalJatuhTempo ")
    @Expose
    private String filterTanggalJatuhTempo;
    @SerializedName("FilterHasilIDE")
    @Expose
    private String filterHasilIDE;
    @SerializedName("FilterSlotPenempatan")
    @Expose
    private String filterSlotPenempatan;
    @SerializedName("FilterAktifitas")
    @Expose
    private String filterAktifitas;
    @SerializedName("PemutusBeradadiTempat")
    @Expose
    private String pemutusBeradaDiTempat;

    public ReqAplikasiGadai() {
        filterKodeCabang=("NONE");
        filterNoAplikasi=("NONE");
        filterNoKTP=("NONE");
        filterLDNumber=("NONE");
        filterReviewer=("NONE");
        filterPemutus=("NONE");
        filterAOPembiayaan=("NONE");
        filterPengusul=("NONE");
        filterWorkFlowStatus=("NONE");
        filterNoCif=("NONE");
        filterSBGE=("NONE");
        filterKodeAgunan=("NONE");
        filterUjiKwalitasKapan=("NONE");
        filterTanggalPencairan=("NONE");
        filterTanggalJatuhTempo=("NONE");
        filterHasilIDE=("NONE");
        filterSlotPenempatan=("NONE");
        filterAktifitas=("NONE");
    }

    public String getPemutusBeradaDiTempat() {
        return pemutusBeradaDiTempat;
    }

    public void setPemutusBeradaDiTempat(String pemutusBeradaDiTempat) {
        this.pemutusBeradaDiTempat = pemutusBeradaDiTempat;
    }

    public String getFilterAktifitas() {
        return filterAktifitas;
    }

    public void setFilterAktifitas(String filterAktifitas) {
        this.filterAktifitas = filterAktifitas;
    }

    public String getFilterKodeCabang() {
        return filterKodeCabang;
    }

    public void setFilterKodeCabang(String filterKodeCabang) {
        this.filterKodeCabang = filterKodeCabang;
    }

    public String getFilterNoAplikasi() {
        return filterNoAplikasi;
    }

    public void setFilterNoAplikasi(String filterNoAplikasi) {
        this.filterNoAplikasi = filterNoAplikasi;
    }

    public String getFilterNoKTP() {
        return filterNoKTP;
    }

    public void setFilterNoKTP(String filterNoKTP) {
        this.filterNoKTP = filterNoKTP;
    }

    public String getFilterPengusul() {
        return filterPengusul;
    }

    public void setFilterPengusul(String filterPengusul) {
        this.filterPengusul = filterPengusul;
    }

    public String getFilterReviewer() {
        return filterReviewer;
    }

    public void setFilterReviewer(String filterReviewer) {
        this.filterReviewer = filterReviewer;
    }

    public String getFilterPemutus() {
        return filterPemutus;
    }

    public void setFilterPemutus(String filterPemutus) {
        this.filterPemutus = filterPemutus;
    }

    public String getFilterAOPembiayaan() {
        return filterAOPembiayaan;
    }

    public void setFilterAOPembiayaan(String filterAOPembiayaan) {
        this.filterAOPembiayaan = filterAOPembiayaan;
    }

    public String getFilterWorkFlowStatus() {
        return filterWorkFlowStatus;
    }

    public void setFilterWorkFlowStatus(String filterWorkFlowStatus) {
        this.filterWorkFlowStatus = filterWorkFlowStatus;
    }

    public String getFilterNoCif() {
        return filterNoCif;
    }

    public void setFilterNoCif(String filterNoCif) {
        this.filterNoCif = filterNoCif;
    }

    public String getFilterSBGE() {
        return filterSBGE;
    }

    public void setFilterSBGE(String filterSBGE) {
        this.filterSBGE = filterSBGE;
    }

    public String getFilterKodeAgunan() {
        return filterKodeAgunan;
    }

    public void setFilterKodeAgunan(String filterKodeAgunan) {
        this.filterKodeAgunan = filterKodeAgunan;
    }

    public String getFilterLDNumber() {
        return filterLDNumber;
    }

    public void setFilterLDNumber(String filterLDNumber) {
        this.filterLDNumber = filterLDNumber;
    }

    public String getFilterUjiKwalitasKapan() {
        return filterUjiKwalitasKapan;
    }

    public void setFilterUjiKwalitasKapan(String filterUjiKwalitasKapan) {
        this.filterUjiKwalitasKapan = filterUjiKwalitasKapan;
    }

    public String getFilterTanggalPencairan() {
        return filterTanggalPencairan;
    }

    public void setFilterTanggalPencairan(String filterTanggalPencairan) {
        this.filterTanggalPencairan = filterTanggalPencairan;
    }

    public String getFilterTanggalJatuhTempo() {
        return filterTanggalJatuhTempo;
    }

    public void setFilterTanggalJatuhTempo(String filterTanggalJatuhTempo) {
        this.filterTanggalJatuhTempo = filterTanggalJatuhTempo;
    }

    public String getFilterHasilIDE() {
        return filterHasilIDE;
    }

    public void setFilterHasilIDE(String filterHasilIDE) {
        this.filterHasilIDE = filterHasilIDE;
    }

    public String getFilterSlotPenempatan() {
        return filterSlotPenempatan;
    }

    public void setFilterSlotPenempatan(String filterSlotPenempatan) {
        this.filterSlotPenempatan = filterSlotPenempatan;
    }
}
