package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqRetryGadaiGagal {
    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("waitActivityId")
    @Expose
    private Long waitActivityId;

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public Long getWaitActivityId() {
        return waitActivityId;
    }

    public void setWaitActivityId(Long waitActivityId) {
        this.waitActivityId = waitActivityId;
    }
}
