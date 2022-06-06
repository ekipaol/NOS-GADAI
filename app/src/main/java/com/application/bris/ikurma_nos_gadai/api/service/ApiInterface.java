package com.application.bris.ikurma_nos_gadai.api.service;

import com.application.bris.ikurma_nos_gadai.api.config.UriApi;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGadai;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGmapsV3;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseUjiAcak;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseUjiKualitas;
import com.application.bris.ikurma_nos_gadai.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUidLong;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUjiAcak;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUjiKualitas;
import com.application.bris.ikurma_nos_gadai.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqChannelAplikasiGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqChannelDataApprove;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.Activation;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.Checkupdate;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ListDeviasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ReqFirebase;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ReqRegisterBrisnotif;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.home;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.login;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchAddress;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchListSektorEkonomi;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchSektorEkonomi;

import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.KonsumerKMGInputPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.KonsumerKMGInquiryPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inputPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.listPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.processRejectPipeline;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PID on 4/5/2019.
 */

public interface ApiInterface {
    @POST(UriApi.general.login)
    Call<ParseResponse> login (@Body login login);

    @POST(UriApi.general.login2)
    Call<ParseResponse> login2 (@Body login login);

    @POST(UriApi.general.home)
    Call<ParseResponse> home (@Body home home);

    @POST(UriApi.general.getProduct)
    Call<ParseResponse> getProduct (@Body EmptyRequest emptyRequest);

    @POST(UriApi.general.getProduct)
    Call<ParseResponse> getProductAmanah (@Body ReqUidLong ReqUidLong);

    @POST(UriApi.general.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomii (@Body EmptyRequest emptyRequest);

    @POST(UriApi.general.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomiiByGroup (@Body searchListSektorEkonomi searchListSektorEkonomi);

    @POST(UriApi.general.searcSektorEkonomi)
    Call<ParseResponse> searcSektorEkonomi (@Body searchSektorEkonomi searchSektorEkonomi);

    @POST(UriApi.general.listDeviasi)
    Call<ParseResponse> listDeviasi (@Body ListDeviasi listDeviasi);

    @POST(UriApi.general.activation)
    Call<ParseResponse> activation (@Body Activation activation);

    @POST(UriApi.general.checkUpdate)
    Call<ParseResponse> checkUpdate (@Body Checkupdate checkupdate);

    @GET(UriApi.general.geocoding+"pretty=1&limit=1")
    Call<ParseResponseGmapsV3> geocoding(@Query("address") String address, @Query("key") String key);

    @POST(UriApi.general.updateFirebase)
    Call<ParseResponse> updateFirebase(@Body ReqFirebase ReqFirebase);

    @POST(UriApi.general.brisnotifRegister)
    Call<ParseResponse> brisnotifRegister(@Body ReqRegisterBrisnotif ReqRegisterBrisnotif);

    /* ************** PIPELINE *************** */
    /* ************** PIPELINE *************** */
    @POST(UriApi.pipeline.listPipeline)
    Call<ParseResponseArr> listPipeline(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipeline)
    Call<ParseResponse> inquiryPipeline(@Body inquiryPipeline inquiryPipeline);

    @Multipart
    @POST(UriApi.pipeline.uploadFoto)
    Call<ParseResponse> uploadFoto (@Part MultipartBody.Part file);

    @POST(UriApi.pipeline.sendDataPipeline)
    Call<ParseResponse> sendDataPipeline(@Body inputPipeline inputPipeline);

    @POST(UriApi.pipeline.pipelineToHotprospek)
    Call<ParseResponse> pipelineToHotprospek (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipeline)
    Call<ParseResponse> rejectPipeline (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospek)
    Call<ParseResponse> savePipelineHotprospek(@Body inputPipeline inputPipeline);

    @POST(UriApi.general.searchAddress)
    Call<ParseResponseArr> searchAddress (@Body searchAddress searchAddress);


    @POST(UriApi.pipeline.getListInstansi)
    Call<ParseResponseArr> getListInstansi (@Body inquiryInstansi inquiryInstansi);

    @POST(UriApi.pipeline.listPipelineKonsumer)
    Call<ParseResponseArr> listPipelineKonsumer(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipelineKonsumer)
    Call<ParseResponse> inquiryPipelineKonsumer(@Body KonsumerKMGInquiryPipeline inquiryPipeline);

    @POST(UriApi.pipeline.sendDataPipelineKonsumer)
    Call<ParseResponse> sendDataPipelineKonsumer(@Body KonsumerKMGInputPipeline inputPipeline);

    @POST(UriApi.pipeline.pipelineToHotprospekKonsumer)
    Call<ParseResponse> pipelineToHotprospekKonsumer (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipelineKonsumer)
    Call<ParseResponse> rejectPipelineKonsumer (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospekKonsumer)
    Call<ParseResponse> savePipelineHotprospekKonsumer(@Body KonsumerKMGInputPipeline inputPipeline);


    //List Gadai
    @POST(UriApi.gadaiNOS.listAplikasiGadai)
    Call<ParseResponseGadai> sendDataListApplikasi(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.detailAplikasiGadai)
    Call<ParseResponseAgunan> sendDetailAplikasiGadai(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.sendAplikasiGadai)
    Call<ParseResponseAgunan> sendDataCaptureGadai(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.sendBrankasInfo)
    Call<ParseResponse> sendDataBrankasInfo(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.listTanggalOpname)
    Call<ParseResponseGadai> listTanggalOpname(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.listIsiLaci)
    Call<ParseResponse> listIsiLaci(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.updateIsiLaci)
    Call<ParseResponse> UpdateIsiLaci(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.updatehasilpenjualan)
    Call<ParseResponse> UpdatehasilPenjualan(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.ujiAcak)
    Call<ParseResponseUjiAcak> sendUjiAcak(@Body ReqUjiAcak ReqUjiAcak);

    @POST(UriApi.gadaiNOS.ujiKualitas)
    Call<ParseResponseUjiKualitas> sendUjiKualitas(@Body ReqUjiKualitas ReqUjiKualitas);

    @POST(UriApi.gadaiNOS.sendSerahTerima)
    Call<ParseResponseAgunan> sendSerahTerima(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.listTandaTerima)
    Call<ParseResponseAgunan> sendListSerahTerima(@Body ReqListGadai ReqListGadai);

    //GADAI
    @POST(UriApi.GadaiPutusan.listAplikasiGadai)
    Call<ParseResponseArr> listAplikasiGadai (@Body ReqChannelAplikasiGadai ReqChannelAplikasiGadai);

    @POST(UriApi.GadaiPutusan.detailAplikasiGadai)
    Call<ParseResponse> detailAplikasiGadai (@Body ReqChannelAplikasiGadai ReqChannelAplikasiGadai);

    @POST(UriApi.GadaiPutusan.inquiryListFoto)
    Call<ParseResponseArr> inquiryListFoto (@Body ReqChannelAplikasiGadai ReqChannelAplikasiGadai);

    @POST(UriApi.GadaiPutusan.submitTaksiranPemutus)
    Call<ParseResponse> submitTaksiranPemutus (@Body ReqChannelDataApprove ReqChannelDataApprove);

    @GET(UriApi.general.getAreaByKodeRegion)
    Call<ParseResponse> getAreaByKodeRegion (@Query(value="fid_region", encoded=true) String fidRegion,@Query(value="limit", encoded=true) String limit);

    @GET(UriApi.general.getBranchByKodeArea)
    Call<ParseResponse> getBranchByKodeArea (@Query(value="fid_area", encoded=true) String fidArea,@Query(value="limit", encoded=true) String limit);

    @GET(UriApi.general.getDetailBranch)
    Call<ParseResponse> getDetailBranch(@Path(value = "branch_id", encoded = true) String branchId);

}

