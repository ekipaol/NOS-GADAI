package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCabang {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fid_area")
    @Expose
    private String fidArea;
    @SerializedName("fid_branch")
    @Expose
    private String fidBranch;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("branch_name")
    @Expose
    private String branchName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFidArea() {
        return fidArea;
    }

    public void setFidArea(String fidArea) {
        this.fidArea = fidArea;
    }

    public String getFidBranch() {
        return fidBranch;
    }

    public void setFidBranch(String fidBranch) {
        this.fidBranch = fidBranch;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
