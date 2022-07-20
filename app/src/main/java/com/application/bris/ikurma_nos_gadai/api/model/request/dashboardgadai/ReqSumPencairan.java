package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqSumPencairan {
	@SerializedName("StartDate")
	private String startDate;
	@SerializedName("EndDate")
	private String endDate;
	@SerializedName("SummarySelindo")
	private Boolean sumSelindo;
	@SerializedName("List Branch")
	private List<String> listBranch;

	public void setSumSelindo(Boolean sumSelindo) {
		this.sumSelindo = sumSelindo;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public void setListBranch(List<String> listBranch){
		this.listBranch = listBranch;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
}
