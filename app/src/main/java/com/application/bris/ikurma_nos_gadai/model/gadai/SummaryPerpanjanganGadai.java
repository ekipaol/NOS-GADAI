package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class SummaryPerpanjanganGadai {

    @SerializedName("belumSimulasi")
    private String belumSimulasi;

    @SerializedName("belumDireview")
    private String belumDireview;

    @SerializedName("belumPemutusan")
    private String belumPemutusan;

    @SerializedName("pengembalianAgunan")
    private String pengembalianAgunan;

    @SerializedName("belumOtorisasi")
    private String belumOtorisasi;

    @SerializedName("blokirSaldoRekening")
    private String blokirSaldoRekening;

    @SerializedName("pencairan")
    private String pencairan;

    @SerializedName("pelunasan")
    private String pelunasan;

    @SerializedName("releaseSaldoRekening")
    private String releaseSaldoRekening;

    public String getBelumSimulasi() {
        return belumSimulasi;
    }

    public void setBelumSimulasi(String belumSimulasi) {
        this.belumSimulasi = belumSimulasi;
    }

    public String getBelumDireview() {
        return belumDireview;
    }

    public void setBelumDireview(String belumDireview) {
        this.belumDireview = belumDireview;
    }

    public String getBelumPemutusan() {
        return belumPemutusan;
    }

    public void setBelumPemutusan(String belumPemutusan) {
        this.belumPemutusan = belumPemutusan;
    }

    public String getPengembalianAgunan() {
        return pengembalianAgunan;
    }

    public void setPengembalianAgunan(String pengembalianAgunan) {
        this.pengembalianAgunan = pengembalianAgunan;
    }

    public String getBelumOtorisasi() {
        return belumOtorisasi;
    }

    public void setBelumOtorisasi(String belumOtorisasi) {
        this.belumOtorisasi = belumOtorisasi;
    }

    public String getBlokirSaldoRekening() {
        return blokirSaldoRekening;
    }

    public void setBlokirSaldoRekening(String blokirSaldoRekening) {
        this.blokirSaldoRekening = blokirSaldoRekening;
    }

    public String getPencairan() {
        return pencairan;
    }

    public void setPencairan(String pencairan) {
        this.pencairan = pencairan;
    }

    public String getPelunasan() {
        return pelunasan;
    }

    public void setPelunasan(String pelunasan) {
        this.pelunasan = pelunasan;
    }

    public String getReleaseSaldoRekening() {
        return releaseSaldoRekening;
    }

    public void setReleaseSaldoRekening(String releaseSaldoRekening) {
        this.releaseSaldoRekening = releaseSaldoRekening;
    }
}