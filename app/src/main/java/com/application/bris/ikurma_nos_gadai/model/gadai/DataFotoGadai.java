package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataFotoGadai {
    @SerializedName("ListFoto")
    @Expose
    private List<ListFoto> ListFoto;

    @SerializedName("TanggalAktifitas")
    @Expose
    private String tanggalAktifitas;
    @SerializedName("Aktifitas")
    @Expose
    private String aktifitas;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("StatusAgunan")
    @Expose
    private String statusAgunan;
    @SerializedName("ReffNoAktifitas")
    @Expose
    private String reffNoAktifitas;

    public List<com.application.bris.ikurma_nos_gadai.model.gadai.ListFoto> getListFoto() {
        return ListFoto;
    }

    public void setListFoto(List<com.application.bris.ikurma_nos_gadai.model.gadai.ListFoto> listFoto) {
        ListFoto = listFoto;
    }

    public String getTanggalAktifitas() {
        return tanggalAktifitas;
    }

    public void setTanggalAktifitas(String tanggalAktifitas) {
        this.tanggalAktifitas = tanggalAktifitas;
    }

    public String getAktifitas() {
        return aktifitas;
    }

    public void setAktifitas(String aktifitas) {
        this.aktifitas = aktifitas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusAgunan() {
        return statusAgunan;
    }

    public void setStatusAgunan(String statusAgunan) {
        this.statusAgunan = statusAgunan;
    }

    public String getReffNoAktifitas() {
        return reffNoAktifitas;
    }

    public void setReffNoAktifitas(String reffNoAktifitas) {
        this.reffNoAktifitas = reffNoAktifitas;
    }
}
