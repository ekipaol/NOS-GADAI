package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqDashboard{
	@SerializedName("SummarySelindo")
	private Boolean sumSelindo;
	@SerializedName("ListBranch")
	private List<String> listBranch;

	public Boolean getSumSelindo() {
		return sumSelindo;
	}

	public void setSumSelindo(Boolean sumSelindo) {
		this.sumSelindo = sumSelindo;
	}

	public List<String> getListBranch() {
		return listBranch;
	}

	public void setListBranch(List<String> listBranch) {
		this.listBranch = listBranch;
	}
}
