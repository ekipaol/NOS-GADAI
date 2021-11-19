package com.application.bris.ikurma_nos_gadai.api.model.request.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDataChanges {
    @SerializedName("ProgramGadai")
    @Expose
    private String ProgramGadai;

    public String getProgramGadai() {
        return ProgramGadai;
    }

    public void setProgramGadai(String programGadai) {
        ProgramGadai = programGadai;
    }
}
