package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class SumPencairanGadai {

	@SerializedName("jumlahCIF")
	private Long jumlahCIF;
	@SerializedName("jumlahLoan")
	private Long jumlahLoan;
	@SerializedName("totalOutstanding")
	private String totalOutstanding;

	public Long getJumlahCIF() {
		return jumlahCIF;
	}

	public void setJumlahCIF(Long jumlahCIF) {
		this.jumlahCIF = jumlahCIF;
	}

	public Long getJumlahLoan() {
		return jumlahLoan;
	}

	public void setJumlahLoan(Long jumlahLoan) {
		this.jumlahLoan = jumlahLoan;
	}

	public String getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(String totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

}


