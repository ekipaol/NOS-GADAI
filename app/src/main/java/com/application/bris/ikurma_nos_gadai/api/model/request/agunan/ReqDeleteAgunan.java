package com.application.bris.ikurma_nos_gadai.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqDeleteAgunan {
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idCif")
    private int idCif;
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("fidjenisAgunan")
    private int fidjenisAgunan;

    public int getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getFidjenisAgunan() {
        return fidjenisAgunan;
    }

    public void setFidjenisAgunan(int fidjenisAgunan) {
        this.fidjenisAgunan = fidjenisAgunan;
    }
}
