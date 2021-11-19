package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDataApprove {
    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("kodeCabang")
    @Expose
    private String kodeCabang;
    @SerializedName("ReasonPenolakan")
    @Expose
    private String reasonPenolakan;
    @SerializedName("ActionPemutus")
    @Expose
    private String actionPemutus;
    @SerializedName("PejabatPemutus")
    @Expose
    private String PejabatPemutus;

    public String getPejabatPemutus() {
        return PejabatPemutus;
    }

    public void setPejabatPemutus(String pejabatPemutus) {
        PejabatPemutus = pejabatPemutus;
    }

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

    public String getReasonPenolakan() {
        return reasonPenolakan;
    }

    public void setReasonPenolakan(String reasonPenolakan) {
        this.reasonPenolakan = reasonPenolakan;
    }

    public String getActionPemutus() {
        return actionPemutus;
    }

    public void setActionPemutus(String actionPemutus) {
        this.actionPemutus = actionPemutus;
    }
}
