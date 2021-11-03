package com.application.bris.ikurma_nos_gadai.api.model.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ReqUjiAcak{

	@SerializedName("data")
	private JsonObject data;

	@SerializedName("channel")
	private String channel;

	@SerializedName("rrn")
	private String rrn;

	public void setchannel(String channel) { this.channel = channel;}
	public void setdata(JsonObject data) { this.data = data;}
	public void setRrn(String setRrn) { this.data = data;}
}