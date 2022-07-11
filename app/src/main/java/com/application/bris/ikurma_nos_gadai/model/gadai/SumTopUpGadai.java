package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class SumTopUpGadai {

	@SerializedName("TotalOutstanding")
	private Long totalOutstanding;
	@SerializedName("JumlahLoan")
	private Long jumlahLoan;
	@SerializedName("JumlahCIF")
	private Long jumlahCIF;

	public Long getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(Long totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public Long getJumlahLoan() {
		return jumlahLoan;
	}

	public void setJumlahLoan(Long jumlahLoan) {
		this.jumlahLoan = jumlahLoan;
	}

	public Long getJumlahCIF() {
		return jumlahCIF;
	}

	public void setJumlahCIF(Long jumlahCIF) {
		this.jumlahCIF = jumlahCIF;
	}
}

