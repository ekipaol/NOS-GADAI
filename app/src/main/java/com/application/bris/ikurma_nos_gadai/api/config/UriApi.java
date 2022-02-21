package com.application.bris.ikurma_nos_gadai.api.config;

import com.application.bris.ikurma_nos_gadai.BuildConfig;

/**
 * Created by PID on 4/5/2019.
 */

public class UriApi {
    
    public static class Baseurl{
//        public static final String URLDEV = "http://10.1.25.55:8080/MobileBRISIAPI-EKI/webresources/"; //DEV
//        public static final String URLDEV = "https://10.0.116.105/"; //DEV
//        public static final String URLDEV = "http://10.0.116.37:8054/nos-gadai/"; //DEV lewat middle tier
        public static final String URLDEV = "https://103.23.117.26/nos-gadai/"; //IP public NOS GADAI

        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/"; //PROD
//        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI-EKI/webresources/"; //PROD EKI

        public static String URL = (BuildConfig.IS_PRODUCTION) ? URLPROD : URLDEV ; //ENV BASED URI SELECTOR

        public static final String URL_MAPS = "https://api.opencagedata.com/";
    }

    public class general {
        public static final String searchAddress = "generic/pencarianKodePos";
        public static final String login = "https://103.23.117.26/user-service/logins";
        public static final String simpanFeedback = "generic/simpanFeedback";
        public static final String login2 =  "https://103.23.117.26/user-service/logins";
        public static final String home = "generic/dashboardKmg";
        public static final String getProduct = "generic/getProduk";
        public static final String getKategSektorEkonomii = "generic/mikro/hotprospek/datapembiayaan/kategoriSektorEkonomi";
        public static final String searcSektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/pencarianSektorEkonomi";
        public static final String listDeviasi = "generic/mikro/hotprospek/kirimPutusan/listDeviasiMikro";
        public static final String activation = "generic/aktivasi/activate";
        public static final String checkUpdate = "generic/getAppVersion";
        public static final String geocoding = "geocode/v1/google-v3-json?";
        public static final String updateFirebase = "generic/aktivasi/updateFirebaseMessagingID";
        public static final String brisnotifRegister = "generic/brisnotifRegister";
        public static final String getAreaByKodeRegion = "http://10.0.116.37:8054/user-service/mapping-regions";
        public static final String getBranchByKodeArea = "http://10.0.116.37:8054/user-service/mapping_area";
        public static final String getDetailBranch = "http://10.0.116.37:8054/user-service/branches/{branch_id}";
    }

    public class pipeline {

        public static final String listPipeline = "generic/mikro/pipeline/listPipeline";
        public static final String inquiryPipeline = "generic/mikro/pipelinek/inquirePipeline";
        public static final String uploadFoto = "generic/uploadImage";
        public static final String sendDataPipeline = "generic/mikro/pipeline/updatePipeline";
        public static final String pipelineToHotprospek = "generic/mikro/pipeline/kirimHotProspek";
        public static final String rejectPipeline = "generic/mikro/pipeline/tolak";
        public static final String savePipelineHotprospek = "generic/mikro/pipeline/simpanDanKirimHotProspek";

        public static final String getListInstansi = "generic/konsumer/pipeline/listInstansi";
        public static final String listPipelineKonsumer = "generic/konsumer/pipeline/listPipeline";
        public static final String inquiryPipelineKonsumer = "generic/konsumer/pipeline/inquirePipeline";
        public static final String sendDataPipelineKonsumer = "generic/konsumer/pipeline/updatePipeline";
        public static final String pipelineToHotprospekKonsumer = "generic/konsumer/pipeline/kirimHotProspek";
        public static final String rejectPipelineKonsumer = "generic/konsumer/pipeline/tolak";
        public static final String savePipelineHotprospekKonsumer = "generic/konsumer/pipeline/simpanDanKirimHotProspek";

    }

    public class gadaiNOS{
        //Gadai
        public static final String listAplikasiGadai = "GadaiEmasService/rest/Gadai/ListAplikasiGadai";
        //Activity
        public static final String sendAplikasiGadai = "GadaiEmasService/rest/Activity/SubmitFotoAgunanEmas";
        public static final String updateIsiLaci = "GadaiEmasService/rest/Activity/ujiStockOpname";
        public static final String ujiAcak = "GadaiEmasService/rest/Activity/UjiAcak";
        public static final String ujiKualitas = "GadaiEmasService/rest/Activity/UjiKualitas";
        public static final String sendSerahTerima= "GadaiEmasService/rest/Activity/SubmitTandaTerima";
        public static final String listTandaTerima= "GadaiEmasService/rest/Activity/ListTandaTerima";

        //Disburse
        public static final String detailAplikasiGadai = "GadaiEmasService/rest/Disburse/DetailAplikasiGadai";
        //Inquiry
        public static final String listIsiLaci = "GadaiEmasService/rest/Vault/InquiryListBrankasOpname";
        public static final String sendBrankasInfo = "GadaiEmasService/rest/Vault/BrankasInfo";
        public static final String listTanggalOpname = "GadaiEmasService/rest/Vault/ListRequestAksesBrankas";
        //Lunas
        public static final String updatehasilpenjualan = "GadaiEmasService/rest/Lunas/KonfirmasiPenjualanAgunan";

    }
    public class GadaiPutusan {
        public static final String listAplikasiGadai = "GadaiEmasService/rest/Gadai/ListAplikasiGadai";
        public static final String detailAplikasiGadai = "GadaiEmasService/rest/Disburse/DetailAplikasiGadai";
        public static final String inquiryListFoto = "GadaiEmasService/rest/Gadai/InquiryListFoto";
        public static final String submitTaksiranPemutus = "GadaiEmasService/rest/Disburse/SubmitSimulasiTaksiranPemutus";
    }
}


