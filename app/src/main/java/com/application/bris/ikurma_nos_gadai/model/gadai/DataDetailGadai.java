package com.application.bris.ikurma_nos_gadai.model.gadai;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDetailGadai {
    @SerializedName("PortofolioPawning")
    @Expose
    private List<PortfolioPawning> PortofolioPawning;
    @SerializedName("ListObjectGadai")
    @Expose
    private List<ObjekGadai> ListObjectGadai;
    @SerializedName("IDPerefferal")
    @Expose
//    private List<IdPerefferall> IDPerefferal;
    private String IDPerefferal;

    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("KodeCabang")
    @Expose
    private String kodeCabang;
    @SerializedName("NamaCabang")
    @Expose
    private String namaCabang;
    @SerializedName("JenisProduk")
    @Expose
    private String jenisProduk;
    @SerializedName("TipeProduk")
    @Expose
    private String tipeProduk;
    @SerializedName("LoanType")
    @Expose
    private String loanType;
    @SerializedName("NoKTP")
    @Expose
    private String noKTP;
    @SerializedName("NamaSesuaiKTP")
    @Expose
    private String namaSesuaiKTP;
    @SerializedName("NoCIF")
    @Expose
    private String noCIF;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("NamaIbuKandung")
    @Expose
    private String namaIbuKandung;
    @SerializedName("NomorHandphone")
    @Expose
    private String nomorHandphone;
    @SerializedName("Pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("Tempatlahir")
    @Expose
    private String tempatlahir;
    @SerializedName("SumberInformasi")
    @Expose
    private String sumberInformasi;
    @SerializedName("Tanggallahir")
    @Expose
    private String tanggallahir;
    @SerializedName("TotalPortofolioPawningExisting ")
    @Expose
    private String totalPortofolioPawningExisting;
    @SerializedName("SisaPlafonGadai")
    @Expose
    private String sisaPlafonGadai;
    @SerializedName("KolektibilitasCIF")
    @Expose
    private String kolektibilitasCIF;
    @SerializedName("NasabahEmasPalsu")
    @Expose
    private String nasabahEmasPalsu;
    @SerializedName("NasabahWriteOff")
    @Expose
    private String nasabahWriteOff;
    @SerializedName("NasabahEXWriteOff")
    @Expose
    private String nasabahEXWriteOff;
    @SerializedName("DaftarHitam")
    @Expose
    private String daftarHitam;
    @SerializedName("PPATK")
    @Expose
    private String ppatk;
    @SerializedName("DTTOT")
    @Expose
    private String dttot;
    @SerializedName("HasilIDE")
    @Expose
    private String hasilIDE;
    @SerializedName("ProgranGadai")
    @Expose
    private String progranGadai;
    @SerializedName("EventGadai")
    @Expose
    private String eventGadai;
    @SerializedName("TanggalSimulasi")
    @Expose
    private String tanggalSimulasi;
    @SerializedName("TotalPortofolioEmasExisting")
    @Expose
    private String totalPortofolioEmasExisting;
    @SerializedName("TotalPortofolioPawning")
    @Expose
    private String totalPortofolioPawning;
    @SerializedName("TanggalPencairan")
    @Expose
    private String tanggalPencairan;
    @SerializedName("TanggalJatuhTempo")
    @Expose
    private String tanggalJatuhTempo;
    @SerializedName("PinjamanMaximumGadai")
    @Expose
    private String pinjamanMaximumGadai;
    @SerializedName("TotalBeratKering")
    @Expose
    private String totalBeratKering;
    @SerializedName("TotalBeratBasah")
    @Expose
    private String totalBeratBasah;
    @SerializedName("TotalBeratBersih")
    @Expose
    private String totalBeratBersih;
    @SerializedName("TotalBeratJenis")
    @Expose
    private String totalBeratJenis;
    @SerializedName("TotalTaksiran")
    @Expose
    private String totalTaksiran;
    @SerializedName("TotalPinjamanMaximum")
    @Expose
    private String totalPinjamanMaximum;
    @SerializedName("PinjamanGadaiDiambil")
    @Expose
    private String pinjamanGadaiDiambil;
    @SerializedName("UjrohTaksiran")
    @Expose
    private String ujrohTaksiran;
    @SerializedName("ProsentaseDiskonUjroh")
    @Expose
    private String prosentaseDiskonUjroh;
    @SerializedName("NominalDiskonUjroh")
    @Expose
    private String nominalDiskonUjroh;
    @SerializedName("NominalUjrohFinal")
    @Expose
    private String nominalUjrohFinal;
    @SerializedName("BiayaAdministrasi")
    @Expose
    private String biayaAdministrasi;
    @SerializedName("BiayaMaterai")
    @Expose
    private String biayaMaterai;
    @SerializedName("BiayaAsuransi")
    @Expose
    private String biayaAsuransi;
    @SerializedName("TotalBiaya")
    @Expose
    private String totalBiaya;
    @SerializedName("PejabatPengusul")
    @Expose
    private String pejabatPengusul;
    @SerializedName("PejabatPemutus")
    @Expose
    private String pejabatPemutus;
    @SerializedName("PejabatReviewer")
    @Expose
    private String pejabatReviewer;
    @SerializedName("UserDDEEntry")
    @Expose
    private String userDDEEntry;
    @SerializedName("UserDDEOtor")
    @Expose
    private String userDDEOtor;
    @SerializedName("NoRekeningPencairan")
    @Expose
    private String noRekeningPencairan;
    @SerializedName("NoRekeningPelunasan")
    @Expose
    private String noRekeningPelunasan;
    @SerializedName("PemutusBeradaDiTempat")
    @Expose
    private String pemutusBeradaDiTempat;
    @SerializedName("SBGE")
    @Expose
    private String sbge;
    @SerializedName("LDNumber")
    @Expose
    private String lDNumber;
    @SerializedName("DateTimeIDE")
    @Expose
    private String dateTimeIDE;
    @SerializedName("DateTimeFotoAgunan")
    @Expose
    private String dateTimeFotoAgunan;
    @SerializedName("DateTimeSimulasiPengusul")
    @Expose
    private String dateTimeSimulasiPengusul;
    @SerializedName("DateTimeSimulasiPemutus")
    @Expose
    private String dateTimeSimulasiPemutus;
    @SerializedName("DateTimeSimulasiReviewer")
    @Expose
    private String dateTimeSimulasiReviewer;
    @SerializedName("DateTimePencairan")
    @Expose
    private String dateTimePencairan;
    @SerializedName("DateTimeDDEEntry")
    @Expose
    private String dateTimeDDEEntry;
    @SerializedName("DateTimeDDEOtor")
    @Expose
    private String dateTimeDDEOtor;
    @SerializedName("DateTimePelunasan")
    @Expose
    private String dateTimePelunasan;
    @SerializedName("DateTimePerpanjangan")
    @Expose
    private String dateTimePerpanjangan;
    @SerializedName("UjiKwalitasHariIni")
    @Expose
    private String ujiKwalitasHariIni;
    @SerializedName("PerpanjanganOtomatis")
    @Expose
    private String perpanjanganOtomatis;
    @SerializedName("SlotPenempatan")
    @Expose
    private String slotPenempatan;
    @SerializedName("HasilUjiKualitas")
    @Expose
    private String hasilUjiKualitas;
    @SerializedName("JumlahPerpanjangan")
    @Expose
    private String jumlahPerpanjangan;
    @SerializedName("WorkFlowStatus")
    @Expose
    private String workFlowStatus;
    @SerializedName("KodeAgunan")
    @Expose
    private String kodeAgunan;
    @SerializedName("IDLimitinduk")
    @Expose
    private String iDLimitinduk;
    @SerializedName("IDLimitAnak")
    @Expose
    private String iDLimitAnak;
    @SerializedName("IDLinkJaminan")
    @Expose
    private String iDLinkJaminan;

    public List<PortfolioPawning> getPortofolioPawning() {
        return PortofolioPawning;
    }

    public void setPortofolioPawning(List<PortfolioPawning> portofolioPawning) {
        PortofolioPawning = portofolioPawning;
    }

    public List<ObjekGadai> getListObjectGadai() {
        return ListObjectGadai;
    }

    public void setListObjectGadai(List<ObjekGadai> listObjectGadai) {
        ListObjectGadai = listObjectGadai;
    }

    public String getIDPerefferal() {
        return IDPerefferal;
    }

    public void setIDPerefferal(String IDPerefferal) {
        this.IDPerefferal = IDPerefferal;
    }

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getNamaSesuaiKTP() {
        return namaSesuaiKTP;
    }

    public void setNamaSesuaiKTP(String namaSesuaiKTP) {
        this.namaSesuaiKTP = namaSesuaiKTP;
    }

    public String getNoCIF() {
        return noCIF;
    }

    public void setNoCIF(String noCIF) {
        this.noCIF = noCIF;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getTempatlahir() {
        return tempatlahir;
    }

    public void setTempatlahir(String tempatlahir) {
        this.tempatlahir = tempatlahir;
    }

    public String getSumberInformasi() {
        return sumberInformasi;
    }

    public void setSumberInformasi(String sumberInformasi) {
        this.sumberInformasi = sumberInformasi;
    }

    public String getTanggallahir() {
        return tanggallahir;
    }

    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    public String getTotalPortofolioPawningExisting() {
        return totalPortofolioPawningExisting;
    }

    public void setTotalPortofolioPawningExisting(String totalPortofolioPawningExisting) {
        this.totalPortofolioPawningExisting = totalPortofolioPawningExisting;
    }

    public String getSisaPlafonGadai() {
        return sisaPlafonGadai;
    }

    public void setSisaPlafonGadai(String sisaPlafonGadai) {
        this.sisaPlafonGadai = sisaPlafonGadai;
    }

    public String getKolektibilitasCIF() {
        return kolektibilitasCIF;
    }

    public void setKolektibilitasCIF(String kolektibilitasCIF) {
        this.kolektibilitasCIF = kolektibilitasCIF;
    }

    public String getNasabahEmasPalsu() {
        return nasabahEmasPalsu;
    }

    public void setNasabahEmasPalsu(String nasabahEmasPalsu) {
        this.nasabahEmasPalsu = nasabahEmasPalsu;
    }

    public String getNasabahWriteOff() {
        return nasabahWriteOff;
    }

    public void setNasabahWriteOff(String nasabahWriteOff) {
        this.nasabahWriteOff = nasabahWriteOff;
    }

    public String getNasabahEXWriteOff() {
        return nasabahEXWriteOff;
    }

    public void setNasabahEXWriteOff(String nasabahEXWriteOff) {
        this.nasabahEXWriteOff = nasabahEXWriteOff;
    }

    public String getDaftarHitam() {
        return daftarHitam;
    }

    public void setDaftarHitam(String daftarHitam) {
        this.daftarHitam = daftarHitam;
    }

    public String getPpatk() {
        return ppatk;
    }

    public void setPpatk(String ppatk) {
        this.ppatk = ppatk;
    }

    public String getDttot() {
        return dttot;
    }

    public void setDttot(String dttot) {
        this.dttot = dttot;
    }

    public String getHasilIDE() {
        return hasilIDE;
    }

    public void setHasilIDE(String hasilIDE) {
        this.hasilIDE = hasilIDE;
    }

    public String getProgranGadai() {
        return progranGadai;
    }

    public void setProgranGadai(String progranGadai) {
        this.progranGadai = progranGadai;
    }

    public String getEventGadai() {
        return eventGadai;
    }

    public void setEventGadai(String eventGadai) {
        this.eventGadai = eventGadai;
    }

    public String getTanggalSimulasi() {
        return tanggalSimulasi;
    }

    public void setTanggalSimulasi(String tanggalSimulasi) {
        this.tanggalSimulasi = tanggalSimulasi;
    }

    public String getTotalPortofolioEmasExisting() {
        return totalPortofolioEmasExisting;
    }

    public void setTotalPortofolioEmasExisting(String totalPortofolioEmasExisting) {
        this.totalPortofolioEmasExisting = totalPortofolioEmasExisting;
    }

    public String getTotalPortofolioPawning() {
        return totalPortofolioPawning;
    }

    public void setTotalPortofolioPawning(String totalPortofolioPawning) {
        this.totalPortofolioPawning = totalPortofolioPawning;
    }

    public String getTanggalPencairan() {
        return tanggalPencairan;
    }

    public void setTanggalPencairan(String tanggalPencairan) {
        this.tanggalPencairan = tanggalPencairan;
    }

    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getPinjamanMaximumGadai() {
        return pinjamanMaximumGadai;
    }

    public void setPinjamanMaximumGadai(String pinjamanMaximumGadai) {
        this.pinjamanMaximumGadai = pinjamanMaximumGadai;
    }

    public String getTotalBeratKering() {
        return totalBeratKering;
    }

    public void setTotalBeratKering(String totalBeratKering) {
        this.totalBeratKering = totalBeratKering;
    }

    public String getTotalBeratBasah() {
        return totalBeratBasah;
    }

    public void setTotalBeratBasah(String totalBeratBasah) {
        this.totalBeratBasah = totalBeratBasah;
    }

    public String getTotalBeratBersih() {
        return totalBeratBersih;
    }

    public void setTotalBeratBersih(String totalBeratBersih) {
        this.totalBeratBersih = totalBeratBersih;
    }

    public String getTotalBeratJenis() {
        return totalBeratJenis;
    }

    public void setTotalBeratJenis(String totalBeratJenis) {
        this.totalBeratJenis = totalBeratJenis;
    }

    public String getTotalTaksiran() {
        return totalTaksiran;
    }

    public void setTotalTaksiran(String totalTaksiran) {
        this.totalTaksiran = totalTaksiran;
    }

    public String getTotalPinjamanMaximum() {
        return totalPinjamanMaximum;
    }

    public void setTotalPinjamanMaximum(String totalPinjamanMaximum) {
        this.totalPinjamanMaximum = totalPinjamanMaximum;
    }

    public String getPinjamanGadaiDiambil() {
        return pinjamanGadaiDiambil;
    }

    public void setPinjamanGadaiDiambil(String pinjamanGadaiDiambil) {
        this.pinjamanGadaiDiambil = pinjamanGadaiDiambil;
    }

    public String getUjrohTaksiran() {
        return ujrohTaksiran;
    }

    public void setUjrohTaksiran(String ujrohTaksiran) {
        this.ujrohTaksiran = ujrohTaksiran;
    }

    public String getProsentaseDiskonUjroh() {
        return prosentaseDiskonUjroh;
    }

    public void setProsentaseDiskonUjroh(String prosentaseDiskonUjroh) {
        this.prosentaseDiskonUjroh = prosentaseDiskonUjroh;
    }

    public String getNominalDiskonUjroh() {
        return nominalDiskonUjroh;
    }

    public void setNominalDiskonUjroh(String nominalDiskonUjroh) {
        this.nominalDiskonUjroh = nominalDiskonUjroh;
    }

    public String getNominalUjrohFinal() {
        return nominalUjrohFinal;
    }

    public void setNominalUjrohFinal(String nominalUjrohFinal) {
        this.nominalUjrohFinal = nominalUjrohFinal;
    }

    public String getBiayaAdministrasi() {
        return biayaAdministrasi;
    }

    public void setBiayaAdministrasi(String biayaAdministrasi) {
        this.biayaAdministrasi = biayaAdministrasi;
    }

    public String getBiayaMaterai() {
        return biayaMaterai;
    }

    public void setBiayaMaterai(String biayaMaterai) {
        this.biayaMaterai = biayaMaterai;
    }

    public String getBiayaAsuransi() {
        return biayaAsuransi;
    }

    public void setBiayaAsuransi(String biayaAsuransi) {
        this.biayaAsuransi = biayaAsuransi;
    }

    public String getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(String totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public String getPejabatPengusul() {
        return pejabatPengusul;
    }

    public void setPejabatPengusul(String pejabatPengusul) {
        this.pejabatPengusul = pejabatPengusul;
    }

    public String getPejabatPemutus() {
        return pejabatPemutus;
    }

    public void setPejabatPemutus(String pejabatPemutus) {
        this.pejabatPemutus = pejabatPemutus;
    }

    public String getPejabatReviewer() {
        return pejabatReviewer;
    }

    public void setPejabatReviewer(String pejabatReviewer) {
        this.pejabatReviewer = pejabatReviewer;
    }

    public String getUserDDEEntry() {
        return userDDEEntry;
    }

    public void setUserDDEEntry(String userDDEEntry) {
        this.userDDEEntry = userDDEEntry;
    }

    public String getUserDDEOtor() {
        return userDDEOtor;
    }

    public void setUserDDEOtor(String userDDEOtor) {
        this.userDDEOtor = userDDEOtor;
    }

    public String getNoRekeningPencairan() {
        return noRekeningPencairan;
    }

    public void setNoRekeningPencairan(String noRekeningPencairan) {
        this.noRekeningPencairan = noRekeningPencairan;
    }

    public String getNoRekeningPelunasan() {
        return noRekeningPelunasan;
    }

    public void setNoRekeningPelunasan(String noRekeningPelunasan) {
        this.noRekeningPelunasan = noRekeningPelunasan;
    }

    public String getPemutusBeradaDiTempat() {
        return pemutusBeradaDiTempat;
    }

    public void setPemutusBeradaDiTempat(String pemutusBeradaDiTempat) {
        this.pemutusBeradaDiTempat = pemutusBeradaDiTempat;
    }

    public String getSbge() {
        return sbge;
    }

    public void setSbge(String sbge) {
        this.sbge = sbge;
    }

    public String getlDNumber() {
        return lDNumber;
    }

    public void setlDNumber(String lDNumber) {
        this.lDNumber = lDNumber;
    }

    public String getDateTimeIDE() {
        return dateTimeIDE;
    }

    public void setDateTimeIDE(String dateTimeIDE) {
        this.dateTimeIDE = dateTimeIDE;
    }

    public String getDateTimeFotoAgunan() {
        return dateTimeFotoAgunan;
    }

    public void setDateTimeFotoAgunan(String dateTimeFotoAgunan) {
        this.dateTimeFotoAgunan = dateTimeFotoAgunan;
    }

    public String getDateTimeSimulasiPengusul() {
        return dateTimeSimulasiPengusul;
    }

    public void setDateTimeSimulasiPengusul(String dateTimeSimulasiPengusul) {
        this.dateTimeSimulasiPengusul = dateTimeSimulasiPengusul;
    }

    public String getDateTimeSimulasiPemutus() {
        return dateTimeSimulasiPemutus;
    }

    public void setDateTimeSimulasiPemutus(String dateTimeSimulasiPemutus) {
        this.dateTimeSimulasiPemutus = dateTimeSimulasiPemutus;
    }

    public String getDateTimeSimulasiReviewer() {
        return dateTimeSimulasiReviewer;
    }

    public void setDateTimeSimulasiReviewer(String dateTimeSimulasiReviewer) {
        this.dateTimeSimulasiReviewer = dateTimeSimulasiReviewer;
    }

    public String getDateTimePencairan() {
        return dateTimePencairan;
    }

    public void setDateTimePencairan(String dateTimePencairan) {
        this.dateTimePencairan = dateTimePencairan;
    }

    public String getDateTimeDDEEntry() {
        return dateTimeDDEEntry;
    }

    public void setDateTimeDDEEntry(String dateTimeDDEEntry) {
        this.dateTimeDDEEntry = dateTimeDDEEntry;
    }

    public String getDateTimeDDEOtor() {
        return dateTimeDDEOtor;
    }

    public void setDateTimeDDEOtor(String dateTimeDDEOtor) {
        this.dateTimeDDEOtor = dateTimeDDEOtor;
    }

    public String getDateTimePelunasan() {
        return dateTimePelunasan;
    }

    public void setDateTimePelunasan(String dateTimePelunasan) {
        this.dateTimePelunasan = dateTimePelunasan;
    }

    public String getDateTimePerpanjangan() {
        return dateTimePerpanjangan;
    }

    public void setDateTimePerpanjangan(String dateTimePerpanjangan) {
        this.dateTimePerpanjangan = dateTimePerpanjangan;
    }

    public String getUjiKwalitasHariIni() {
        return ujiKwalitasHariIni;
    }

    public void setUjiKwalitasHariIni(String ujiKwalitasHariIni) {
        this.ujiKwalitasHariIni = ujiKwalitasHariIni;
    }

    public String getPerpanjanganOtomatis() {
        return perpanjanganOtomatis;
    }

    public void setPerpanjanganOtomatis(String perpanjanganOtomatis) {
        this.perpanjanganOtomatis = perpanjanganOtomatis;
    }

    public String getSlotPenempatan() {
        return slotPenempatan;
    }

    public void setSlotPenempatan(String slotPenempatan) {
        this.slotPenempatan = slotPenempatan;
    }

    public String getHasilUjiKualitas() {
        return hasilUjiKualitas;
    }

    public void setHasilUjiKualitas(String hasilUjiKualitas) {
        this.hasilUjiKualitas = hasilUjiKualitas;
    }

    public String getJumlahPerpanjangan() {
        return jumlahPerpanjangan;
    }

    public void setJumlahPerpanjangan(String jumlahPerpanjangan) {
        this.jumlahPerpanjangan = jumlahPerpanjangan;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }

    public String getKodeAgunan() {
        return kodeAgunan;
    }

    public void setKodeAgunan(String kodeAgunan) {
        this.kodeAgunan = kodeAgunan;
    }

    public String getiDLimitinduk() {
        return iDLimitinduk;
    }

    public void setiDLimitinduk(String iDLimitinduk) {
        this.iDLimitinduk = iDLimitinduk;
    }

    public String getiDLimitAnak() {
        return iDLimitAnak;
    }

    public void setiDLimitAnak(String iDLimitAnak) {
        this.iDLimitAnak = iDLimitAnak;
    }

    public String getiDLinkJaminan() {
        return iDLinkJaminan;
    }

    public void setiDLinkJaminan(String iDLinkJaminan) {
        this.iDLinkJaminan = iDLinkJaminan;
    }
}
