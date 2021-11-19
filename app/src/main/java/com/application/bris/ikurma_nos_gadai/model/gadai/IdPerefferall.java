package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdPerefferall {
    @SerializedName("JabatanAOPerefferal")
    @Expose
    private String jabatanAOPerefferal;
    @SerializedName("NamaAOPerefferal")
    @Expose
    private String namaAOPerefferal;
    @SerializedName("NIP")
    @Expose
    private String nip;
    @SerializedName("Rekening")
    @Expose
    private String rekening;

    public String getJabatanAOPerefferal() {
        return jabatanAOPerefferal;
    }

    public void setJabatanAOPerefferal(String jabatanAOPerefferal) {
        this.jabatanAOPerefferal = jabatanAOPerefferal;
    }

    public String getNamaAOPerefferal() {
        return namaAOPerefferal;
    }

    public void setNamaAOPerefferal(String namaAOPerefferal) {
        this.namaAOPerefferal = namaAOPerefferal;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }
}
