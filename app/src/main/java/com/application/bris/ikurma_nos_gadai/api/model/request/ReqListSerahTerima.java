package com.application.bris.ikurma_nos_gadai.api.model.request.pipeline;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ReqListSerahTerima{

	@SerializedName("data")
	private JsonObject data;

	@SerializedName("channel")
	private String channel;

	@SerializedName("rrn")
	private String rrn;


	public void setkchannel(String channel) {
		this.channel = channel;
	}

	public void setdata(JsonObject data) {
		this.data = data;
	}

	public void setrrn(String rrn) {
		this.rrn = rrn;

	}
}