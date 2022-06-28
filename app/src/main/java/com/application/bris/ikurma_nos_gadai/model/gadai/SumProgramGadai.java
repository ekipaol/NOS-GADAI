package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class SumProgramGadai {

    @SerializedName("ProgramGadai")
    private String programGadai;

    @SerializedName("JumlahCIF")
    private String jumlahCIF;

    @SerializedName("JumlahLoan")
    private String jumlahLoan;

    @SerializedName("TotalOutstanding")
    private String totalOutstanding;

    public String getProgramGadai() {
        return programGadai;
    }

    public void setProgramGadai(String programGadai) {
        this.programGadai = programGadai;
    }

    public String getJumlahCIF() {
        return jumlahCIF;
    }

    public void setJumlahCIF(String jumlahCIF) {
        this.jumlahCIF = jumlahCIF;
    }

    public String getJumlahLoan() {
        return jumlahLoan;
    }

    public void setJumlahLoan(String jumlahLoan) {
        this.jumlahLoan = jumlahLoan;
    }
    public String getTotalOutstanding() {
        return totalOutstanding;
    }

    public void setTotalOutstanding(String totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }
}