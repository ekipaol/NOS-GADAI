package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class DataBelumUjiKualitas {
    @SerializedName("NoAplikasi")
    private String noAplikasi;
    @SerializedName("NoKTP")
    private String noKTP;
    @SerializedName("Nama")
    private String nama;
    @SerializedName("NilaiTaksiran")
    private String nilaiTaksiran;
    @SerializedName("NilaiPembiayaan")
    private String nilaiPembiayaan;
    @SerializedName("NilaiBiayaPemeliharaan")
    private String nilaiBiayaPemeliharaan;

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNilaiTaksiran() {
        return nilaiTaksiran;
    }

    public void setNilaiTaksiran(String nilaiTaksiran) {
        this.nilaiTaksiran = nilaiTaksiran;
    }

    public String getNilaiPembiayaan() {
        return nilaiPembiayaan;
    }

    public void setNilaiPembiayaan(String nilaiPembiayaan) {
        this.nilaiPembiayaan = nilaiPembiayaan;
    }

    public String getNilaiBiayaPemeliharaan() {
        return nilaiBiayaPemeliharaan;
    }

    public void setNilaiBiayaPemeliharaan(String nilaiBiayaPemeliharaan) {
        this.nilaiBiayaPemeliharaan = nilaiBiayaPemeliharaan;
    }
}
