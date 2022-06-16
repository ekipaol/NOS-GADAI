package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

public class ReqDashboard{
	@SerializedName("Branch")
	private String branch;

	public void setBranch(String branch){
		this.branch = branch;
	}

	public String getBranch(){
		return branch;
	}
}
