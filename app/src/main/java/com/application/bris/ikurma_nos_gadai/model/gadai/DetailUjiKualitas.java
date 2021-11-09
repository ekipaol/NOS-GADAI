package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.SerializedName;

public class DetailUjiKualitas {

	@SerializedName("NoAplikasi")
	private String noAplikasi;

	@SerializedName("Description")
	private String description;

	@SerializedName("UjiKwalitasHariIni")
	private String ujiKwalitasHariIni;

	@SerializedName("kodeCabang")
	private String kodeCabang;

	@SerializedName("FotoAgunan")
	private String fotoAgunan;

	@SerializedName("UserSubmit")
	private String userSubmit;

	@SerializedName("StatusAgunan")
	private String statusAgunan;

	@SerializedName("FotoPengujian")
	private String fotoPengujian;

	@SerializedName("FotoAgunanTersegel")
	private String fotoAgunanTersegel;

	public String getNoAplikasi(){
		return noAplikasi;
	}

	public String getDescription(){
		return description;
	}

	public String getUjiKwalitasHariIni(){
		return ujiKwalitasHariIni;
	}

	public String getKodeCabang(){
		return kodeCabang;
	}

	public String getFotoAgunan(){
		return fotoAgunan;
	}

	public String getUserSubmit(){
		return userSubmit;
	}

	public String getStatusAgunan() {
		return statusAgunan;
	}

	public String getFotoPengujian(){
		return fotoPengujian;
	}

	public String getFotoAgunanTersegel(){
		return fotoAgunanTersegel;
	}
}