package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataArea {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fid_area")
    @Expose
    private String fidArea;
    @SerializedName("fid_region")
    @Expose
    private String fidRegion;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("region_name")
    @Expose
    private String regionName;

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

    public String getFidRegion() {
        return fidRegion;
    }

    public void setFidRegion(String fidRegion) {
        this.fidRegion = fidRegion;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
