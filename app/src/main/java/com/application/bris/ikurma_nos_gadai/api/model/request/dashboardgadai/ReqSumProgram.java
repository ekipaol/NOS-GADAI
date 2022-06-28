package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqSumProgram {
	@SerializedName("ListCabang")
	private List<String> listCabang;
	@SerializedName("Tahun")
	private String tahun;


	public List<String> getListCabang() {
		return listCabang;
	}

	public void setListCabang(List<String> listCabang) {
		this.listCabang = listCabang;
	}

	public void setTahun(String tahun){
		this.tahun = tahun;
	}

	public String getTahun(){
		return tahun;
	}

}