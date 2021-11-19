package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataGadai {
    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("KodeCabang")
    @Expose
    private String kodeCabang;
    @SerializedName("NoCIF")
    @Expose
    private String noCIF;
    @SerializedName("NoKTP")
    @Expose
    private String noKTP;
    @SerializedName("NamaSesuaiKTP")
    @Expose
    private String namaSesuaiKTP;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("NamaIbuKandung")
    @Expose
    private String namaIbuKandung;
    @SerializedName("NomorHandphone")
    @Expose
    private String nomorHandphone;
    @SerializedName("LDNumber")
    @Expose
    private String lDNumber;
    @SerializedName("SBGENumber")
    @Expose
    private String sBGENumber;
    @SerializedName("TanggalCair")
    @Expose
    private String tanggalCair;
    @SerializedName("TanggalJatuhTempo")
    @Expose
    private String tanggalJatuhTempo;
    @SerializedName("TotalTaksiran")
    @Expose
    private String totalTaksiran;
    @SerializedName("TotalPinjamanMaximum")
    @Expose
    private String totalPinjamanMaximum;
    @SerializedName("TotalPembiayaanAwal")
    @Expose
    private String totalPembiayaanAwal;
    @SerializedName("TotalUjrohAwal")
    @Expose
    private String totalUjrohAwal;
    @SerializedName("TotalOSPembiayaan")
    @Expose
    private String totalOSPembiayaan;
    @SerializedName("TotalOSUjroh")
    @Expose
    private String totalOSUjroh;
    @SerializedName("RekeningPencairan")
    @Expose
    private String rekeningPencairan;
    @SerializedName("SlotPenempatan")
    @Expose
    private String slotPenempatan;
    @SerializedName("PinjamanGadaiDiambil")
    @Expose
    private String pinjamanGadaiDiambil;
    @SerializedName("TotalBeratBersih")
    @Expose
    private String totalBeratBersih;
    @SerializedName("TotalBeratKotor")
    @Expose
    private String totalBeratKotor;
    @SerializedName("KodeAgunan")
    @Expose
    private String kodeAgunan;
    @SerializedName("WorkFlowStatus")
    @Expose
    private String workFlowStatus;
    @SerializedName("PerpanjanganOtomatis")
    @Expose
    private String perpanjanganOtomatis;
    @SerializedName("HasilUjiKualitas")
    @Expose
    private String hasilUjiKualitas;
    @SerializedName("JumlahPerpanjangan")
    @Expose
    private String jumlahPerpanjangan;

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNoCIF() {
        return noCIF;
    }

    public void setNoCIF(String noCIF) {
        this.noCIF = noCIF;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getNamaSesuaiKTP() {
        return namaSesuaiKTP;
    }

    public void setNamaSesuaiKTP(String namaSesuaiKTP) {
        this.namaSesuaiKTP = namaSesuaiKTP;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public String getlDNumber() {
        return lDNumber;
    }

    public void setlDNumber(String lDNumber) {
        this.lDNumber = lDNumber;
    }

    public String getsBGENumber() {
        return sBGENumber;
    }

    public void setsBGENumber(String sBGENumber) {
        this.sBGENumber = sBGENumber;
    }

    public String getTanggalCair() {
        return tanggalCair;
    }

    public void setTanggalCair(String tanggalCair) {
        this.tanggalCair = tanggalCair;
    }

    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getTotalTaksiran() {
        return totalTaksiran;
    }

    public void setTotalTaksiran(String totalTaksiran) {
        this.totalTaksiran = totalTaksiran;
    }

    public String getTotalPinjamanMaximum() {
        return totalPinjamanMaximum;
    }

    public void setTotalPinjamanMaximum(String totalPinjamanMaximum) {
        this.totalPinjamanMaximum = totalPinjamanMaximum;
    }

    public String getTotalPembiayaanAwal() {
        return totalPembiayaanAwal;
    }

    public void setTotalPembiayaanAwal(String totalPembiayaanAwal) {
        this.totalPembiayaanAwal = totalPembiayaanAwal;
    }

    public String getTotalUjrohAwal() {
        return totalUjrohAwal;
    }

    public void setTotalUjrohAwal(String totalUjrohAwal) {
        this.totalUjrohAwal = totalUjrohAwal;
    }

    public String getTotalOSPembiayaan() {
        return totalOSPembiayaan;
    }

    public void setTotalOSPembiayaan(String totalOSPembiayaan) {
        this.totalOSPembiayaan = totalOSPembiayaan;
    }

    public String getTotalOSUjroh() {
        return totalOSUjroh;
    }

    public void setTotalOSUjroh(String totalOSUjroh) {
        this.totalOSUjroh = totalOSUjroh;
    }

    public String getRekeningPencairan() {
        return rekeningPencairan;
    }

    public void setRekeningPencairan(String rekeningPencairan) {
        this.rekeningPencairan = rekeningPencairan;
    }

    public String getSlotPenempatan() {
        return slotPenempatan;
    }

    public void setSlotPenempatan(String slotPenempatan) {
        this.slotPenempatan = slotPenempatan;
    }

    public String getPinjamanGadaiDiambil() {
        return pinjamanGadaiDiambil;
    }

    public void setPinjamanGadaiDiambil(String pinjamanGadaiDiambil) {
        this.pinjamanGadaiDiambil = pinjamanGadaiDiambil;
    }

    public String getTotalBeratBersih() {
        return totalBeratBersih;
    }

    public void setTotalBeratBersih(String totalBeratBersih) {
        this.totalBeratBersih = totalBeratBersih;
    }

    public String getTotalBeratKotor() {
        return totalBeratKotor;
    }

    public void setTotalBeratKotor(String totalBeratKotor) {
        this.totalBeratKotor = totalBeratKotor;
    }

    public String getKodeAgunan() {
        return kodeAgunan;
    }

    public void setKodeAgunan(String kodeAgunan) {
        this.kodeAgunan = kodeAgunan;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }

    public String getPerpanjanganOtomatis() {
        return perpanjanganOtomatis;
    }

    public void setPerpanjanganOtomatis(String perpanjanganOtomatis) {
        this.perpanjanganOtomatis = perpanjanganOtomatis;
    }

    public String getHasilUjiKualitas() {
        return hasilUjiKualitas;
    }

    public void setHasilUjiKualitas(String hasilUjiKualitas) {
        this.hasilUjiKualitas = hasilUjiKualitas;
    }

    public String getJumlahPerpanjangan() {
        return jumlahPerpanjangan;
    }

    public void setJumlahPerpanjangan(String jumlahPerpanjangan) {
        this.jumlahPerpanjangan = jumlahPerpanjangan;
    }
}
