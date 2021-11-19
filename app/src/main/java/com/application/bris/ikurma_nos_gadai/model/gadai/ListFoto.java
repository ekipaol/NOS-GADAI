package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFoto {
    @SerializedName("Id")
    @Expose
    private String Id;
    @SerializedName("ReffObjectFoto")
    @Expose
    private String ReffObjectFoto;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Image")
    @Expose
    private String Image;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getReffObjectFoto() {
        return ReffObjectFoto;
    }

    public void setReffObjectFoto(String reffObjectFoto) {
        ReffObjectFoto = reffObjectFoto;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
