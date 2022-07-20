package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class ListPastDue {

    @SerializedName("NoAplikasi")
    private String noAplikasi;
    @SerializedName("NamaNasabah")
    private String namaNasabah;
    @SerializedName("Pokok")
    private String pokok;
    @SerializedName("BiayaPemeliharaan")
    private String biayaPemeliharaan;
    @SerializedName("TanggalJatuhTempo")
    private String tanggalJatuhTempo;
    @SerializedName("NomorCIF")
    private String nomorCIF;
    @SerializedName("NoLoan")
    private String noLoan;
    @SerializedName("Program")
    private String program;
    @SerializedName("Event")
    private String event;
    @SerializedName("StatusUjiKualitas")
    private String statusUjiKualitas;
    @SerializedName("HasilUjiKualitas")
    private String hasilUjiKualitas;
    @SerializedName("SBGE")
    private String sbge;

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getPokok() {
        return pokok;
    }

    public void setPokok(String pokok) {
        this.pokok = pokok;
    }

    public String getBiayaPemeliharaan() {
        return biayaPemeliharaan;
    }

    public void setBiayaPemeliharaan(String biayaPemeliharaan) {
        this.biayaPemeliharaan = biayaPemeliharaan;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStatusUjiKualitas() {
        return statusUjiKualitas;
    }

    public void setStatusUjiKualitas(String statusUjiKualitas) {
        this.statusUjiKualitas = statusUjiKualitas;
    }

    public String getHasilUjiKualitas() {
        return hasilUjiKualitas;
    }

    public void setHasilUjiKualitas(String hasilUjiKualitas) {
        this.hasilUjiKualitas = hasilUjiKualitas;
    }

    public String getSbge() {
        return sbge;
    }

    public void setSbge(String sbge) {
        this.sbge = sbge;
    }
}
