package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

public class ReqTopUpDashboard{
	@SerializedName("StartDate")
	private String startDate;
	@SerializedName("List Branch")
	private String listBranch;
	@SerializedName("EndDate")
	private String endDate;

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public void setListBranch(String listBranch){
		this.listBranch = listBranch;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
}
