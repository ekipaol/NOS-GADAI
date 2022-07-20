package com.application.bris.ikurma_nos_gadai.api.model;

import com.application.bris.ikurma_nos_gadai.model.gadai.SumPencairanGadai;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseSumPencairan {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private SumPencairanGadai data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SumPencairanGadai getData() {
        return data;
    }

    public void setData(SumPencairanGadai data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


}
