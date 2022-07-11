package com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqSumTSUjikualitas {
	@SerializedName("ListCabang")
	private List<String> listCabang;
	@SerializedName("Tanggal")
	private String tanggal;


	public List<String> getListCabang() {
		return listCabang;
	}

	public void setListCabang(List<String> listCabang) {
		this.listCabang = listCabang;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTahun(){
		return tanggal;
	}

}