package com.application.bris.ikurma_nos_gadai.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqUidLong {
    @SerializedName("uid")
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
