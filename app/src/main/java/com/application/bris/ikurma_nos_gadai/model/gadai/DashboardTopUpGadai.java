package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class DashboardTopUpGadai{

	@SerializedName("totalOutstanding")
	private Integer totalOutstanding;
	@SerializedName("jumlahLoan")
	private Integer jumlahLoan;
	@SerializedName("jumlahCIF")
	private Integer jumlahCIF;

	public int getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(int totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public int getJumlahLoan() {
		return jumlahLoan;
	}

	public void setJumlahLoan(int jumlahLoan) {
		this.jumlahLoan = jumlahLoan;
	}

	public int getJumlahCIF() {
		return jumlahCIF;
	}

	public void setJumlahCIF(int jumlahCIF) {
		this.jumlahCIF = jumlahCIF;
	}


}

