package com.application.bris.ikurma_nos_gadai.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataVerifikasiHutang {
    @SerializedName("namaPemberiHutang")
    private String namaPemberiHutang;
    @SerializedName("nominalPinjaman")
    private String nominalPinjaman;
    @SerializedName("sisaJangkaWaktu")
    private String sisaJangkaWaktu;
    @SerializedName("angsuranBulanan")
    private String angsuranBulanan;
    @SerializedName("treatmentPembiayaan")
    private String treatmentPembiayaan;
    @SerializedName("angsuranVerifikator")
    private String angsuranVerifikator;
    @SerializedName("hasilVerifikasi")
    private String hasilVerifikasi;
    @SerializedName("verifikasiFasilitas")
    private String verifikasiFasilitas;

    public String getAngsuranVerifikator() {
        return angsuranVerifikator;
    }

    public void setAngsuranVerifikator(String angsuranVerifikator) {
        this.angsuranVerifikator = angsuranVerifikator;
    }

    public String getHasilVerifikasi() {
        return hasilVerifikasi;
    }

    public void setHasilVerifikasi(String hasilVerifikasi) {
        this.hasilVerifikasi = hasilVerifikasi;
    }

    public String getNamaPemberiHutang() {
        return namaPemberiHutang;
    }

    public void setNamaPemberiHutang(String namaPemberiHutang) {
        this.namaPemberiHutang = namaPemberiHutang;
    }

    public String getNominalPinjaman() {
        return nominalPinjaman;
    }

    public void setNominalPinjaman(String nominalPinjaman) {
        this.nominalPinjaman = nominalPinjaman;
    }

    public String getSisaJangkaWaktu() {
        return sisaJangkaWaktu;
    }

    public void setSisaJangkaWaktu(String sisaJangkaWaktu) {
        this.sisaJangkaWaktu = sisaJangkaWaktu;
    }

    public String getAngsuranBulanan() {
        return angsuranBulanan;
    }

    public void setAngsuranBulanan(String angsuranBulanan) {
        this.angsuranBulanan = angsuranBulanan;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }

    public String getVerifikasiFasilitas() {
        return verifikasiFasilitas;
    }

    public void setVerifikasiFasilitas(String verifikasiFasilitas) {
        this.verifikasiFasilitas = verifikasiFasilitas;
    }
}
