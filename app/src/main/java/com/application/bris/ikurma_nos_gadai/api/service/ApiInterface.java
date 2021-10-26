package com.application.bris.ikurma_nos_gadai.api.service;

import com.application.bris.ikurma_nos_gadai.api.config.UriApi;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArrAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArrAgunanByCif;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseDataDukcapil;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseDataInstansi;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGadai;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGmapsV3;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseSaldo;
import com.application.bris.ikurma_nos_gadai.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqFollowUpFlpp;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqKodeProvinsi;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqKodeWilayah;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUid;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUidLong;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqAgunanByCif;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqIkatAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqSaveAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqSaveAgunanKendaraan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqSaveAgunanTanahKosong;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.ReqSetPengikatan;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.kpr.ReqAppraisal;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.kpr.ReqKirimAppraisal;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.kpr.SelesaiAppraisal;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.listAppraisal;
import com.application.bris.ikurma_nos_gadai.api.model.request.agunan.rejectAppraisal;
import com.application.bris.ikurma_nos_gadai.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqDataSikasep;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqFotoFlpp;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqLoginSikasep;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqPihakKetiga;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqRumahFlpp;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqSimpanFollowUpFlpp;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqSimpanKonfirmasiFlpp;
import com.application.bris.ikurma_nos_gadai.api.model.request.flpp.ReqSimpanUpdateIdLokasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.Activation;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.Checkupdate;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ListDeviasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ReqFirebase;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.ReqRegisterBrisnotif;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.SimpanFeedback;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.home;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.login;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchAddress;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchListSektorEkonomi;
import com.application.bris.ikurma_nos_gadai.api.model.request.general.searchSektorEkonomi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.InputSektorEkonomiKpr;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.KonsumerKMGInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.KonsumerKPRKaryawanPnsInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.KonsumerKPRWiraswastaInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.ReqScoringKmg;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.SimpanDataFinansial;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.SimpanDataFinansialKpr;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.ValidasiDataFinansialKmg;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.cekRekomendasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputAgunanDeposito;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputAgunanKios;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputHotprospek;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputKelengkapanDokumen;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputRemaksSlik;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputScoring;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inputSektorEkonomi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryHistory;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryIjk;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryLkn;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryNikPasangan;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.inquirySektorEkonomi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.kirimPutusanMikro;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.kirimPutusanMikroDeviasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.listHotprospek;
import com.application.bris.ikurma_nos_gadai.api.model.request.hotprospek.rejectHotprospek;
import com.application.bris.ikurma_nos_gadai.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.ikurma_nos_gadai.api.model.request.monitoring.ReqRankingAo;
import com.application.bris.ikurma_nos_gadai.api.model.request.monitoring.ReqRiwayatMutasi;
import com.application.bris.ikurma_nos_gadai.api.model.request.monitoring.ReqSaldoNasabah;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.KonsumerKMGInputPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.KonsumerKMGInquiryPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.KonsumerKPRInputPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inputPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryListKateg;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryProject;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.inquiryTujuan;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.listPipeline;
import com.application.bris.ikurma_nos_gadai.api.model.request.pipeline.processRejectPipeline;
import com.application.bris.ikurma_nos_gadai.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma_nos_gadai.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_gadai.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_gadai.database.pojo.LknPojo;
import com.application.bris.ikurma_nos_gadai.model.hotprospek.KonsumerKprFlppInputKelengkapanDokumen;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST(UriApi.ceknasabah.cekNasabah)
    Call<ParseResponse> cekNasabah(@Body cekNasabah cekNasabah);

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



    /* **************** HOTPROSPEK ********************* */
    @POST(UriApi.hotprospek.listHotprospek)
    Call<ParseResponseArr> listHotprospek (@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospek.inquiryHotprospek)
    Call<ParseResponse> inquiryHotprospek (@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospek.sendDataHotprospek)
    Call<ParseResponse> sendDataHotprospek (@Body inputHotprospek inputHotprospek);

    @POST(UriApi.hotprospek.rejectHotprospek)
    Call<ParseResponse> rejectHotprospek (@Body rejectHotprospek rejectHotprospek);

    @POST(UriApi.hotprospek.rejectHotprospekKpr)
    Call<ParseResponse> rejectHotprospekKpr (@Body rejectHotprospek rejectHotprospek);

//    @POST(UriApi.hotprospek.inquiryDataLengkap)
//    Call<ParseResponse> inquiryDataLengkap (@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospek.sendDataLengkap)
    Call<ParseResponse> sendDataLengkap (@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.flpp.sendDataLengkapFlpp)
    Call<ParseResponse> sendDataLengkapFlpp (@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.hotprospek.inquiryHistory)
    Call<ParseResponse> inquiryHistory (@Body inquiryHistory inquiryHistory);

    @POST(UriApi.hotprospek.inquirySektorEkonomi)
    Call<ParseResponse> inquirySektorEkonomi (@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospek.sendDataSektorEkonomi)
    Call<ParseResponse> sendDataSektorEkonomi (@Body inputSektorEkonomi inputSektorEkonomi);

    @POST(UriApi.hotprospek.hitungRPC)
    Call<ParseResponse> hitungRPC(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.inquiryRPC)
    Call<ParseResponse> inquiryRPC(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumen)
    Call<ParseResponse> inquiryKelengkapanDokumen (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumen)
    Call<ParseResponse> sendKelengkapanDokumen (@Body inputKelengkapanDokumen inputKelengkapanDokumen);

    @POST(UriApi.hotprospek.cekRekomendasi)
    Call<ParseResponse> cekRekomendasi (@Body cekRekomendasi cekRekomendasi);

    @POST(UriApi.hotprospek.inquiryLKN)
    Call<ParseResponse> inquiryLKN (@Body inquiryLkn inquiryLkn);

    @POST(UriApi.hotprospek.sendLkn)
    Call<ParseResponse> sendLkn (@Body LknPojo lknPojo);

    @POST(UriApi.hotprospek.cekSikp)
    Call<ParseResponse> cekSikp (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekDHNKonsumer)
    Call<ParseResponse> cekDHNKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.cekDukcapilKonsumer)
    Call<ParseResponse> cekDukcapilKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.cekSlikKonsumer)
    Call<ParseResponse> cekSlikKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.inquiryPrescreeningKonsumer)
    Call<ParseResponse> inquiryPrescreeningKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.sendPrescreeningKonsumer)
    Call<ParseResponse> sendPrescreeningKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.downloadSlikKonsumer)
    Call<ParseResponse> downloadSlikKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.downloadSlikPasanganKonsumer)
    Call<ParseResponse> downloadSlikPasanganKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.inquiryRemaksSlikKonsumer)
    Call<ParseResponse> inquiryRemaksSlikKonsumer (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.sendRemaksSlikKonsumer)
    Call<ParseResponse> sendRemaksSlikKonsumer (@Body inputRemaksSlik inputRemaksSlik);




    @POST(UriApi.hotprospek.inquiryScoring)
    Call<ParseResponse> inquiryScoring (@Body inquiryScoring inquiryScoring);
    @POST(UriApi.hotprospek.sendScoring)
    Call<ParseResponse> sendScoring (@Body inputScoring inputScoring);

    @POST(UriApi.hotprospek.sendPutusanMikro)
    Call<ParseResponse> sendPutusanMikro (@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.sendPutusanMikroDeviasi)
    Call<ParseResponse> sendPutusanMikroDeviasi (@Body kirimPutusanMikroDeviasi kirimPutusanMikroDeviasi);

    //AGUNAN


    @POST(UriApi.hotprospek.listAgunan)
    Call<ParseResponseArrAgunan> listAgunan(@Body ReqAgunan ReqAgunan);

    @POST(UriApi.hotprospek.listAgunanAll)
    Call<ParseResponseArrAgunanByCif> listAgunanAll(@Body ReqAgunanByCif ReqAgunanByCif);

    @POST(UriApi.hotprospek.inquiryAgunanTanahBangunan)
    Call<ParseResponse> inquiryAgunanTanahBangunan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanTanahBangunan)
    Call<ParseResponse> sendAgunanTanahBangunan(@Body AgunanTanahBangunanPojo agunanTanahBangunanPojo);

    @POST(UriApi.hotprospek.inquiryInfoAgunan)
    Call<ParseResponse> inquiryInfoAgunan(@Body ReqInfoAgunan ReqInfoAgunan);

    @POST(UriApi.hotprospek.deleteAgunan)
    Call<ParseResponse> deleteAgunan(@Body ReqDeleteAgunan ReqDeleteAgunan);

    @POST(UriApi.hotprospek.setPengikatan)
    Call<ParseResponse> setPengikatan(@Body ReqSetPengikatan ReqSetPengikatan);

    @POST(UriApi.hotprospek.ikatAgunan)
    Call<ParseResponse> ikatAgunan(@Body ReqIkatAgunan ReqIkatAgunan);

    @POST(UriApi.hotprospek.jenisKlasifikasi)
    Call<ParseResponseArr> jenisKlasifikasi(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.hotprospek.saveAgunanTerikat)
    Call<ParseResponse> saveAgunanTerikat(@Body ReqSaveAgunan ReqSaveAgunan);


    @POST(UriApi.hotprospek.inquiryKendaraan)
    Call<ParseResponseAgunan> inquiryKendaraan(@Body ReqAgunanData ReqAgunanData);

    @POST(UriApi.hotprospek.saveKendaraan)
    Call<ParseResponseAgunan> saveKendaraan(@Body ReqSaveAgunanKendaraan ReqSaveAgunanKendaraan);


    @POST(UriApi.hotprospek.inquiryAgunanKios)
    Call<ParseResponseArr> inquiryAgunanKios(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanKios)
    Call<ParseResponse> sendAgunanKios(@Body inputAgunanKios inputAgunanKios);


    @POST(UriApi.hotprospek.inquiryAgunanTanahKosong)
    Call<ParseResponseArr> inquiryAgunanTanahKosong(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.saveTanahKosong)
    Call<ParseResponse> saveTanahKosong(@Body ReqSaveAgunanTanahKosong ReqSaveAgunanTanahKosong);

    @POST(UriApi.hotprospek.inquiryAgunanDeposito)
    Call<ParseResponse> inquiryAgunanDeposito(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanDeposito)
    Call<ParseResponse> sendAgunanDeposito(@Body inputAgunanDeposito inputAgunanDeposito);

    @POST(UriApi.hotprospek.inquiryAgunanKendaraan)
    Call<ParseResponse> inquiryAgunanKendaraan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanKendaraan)
    Call<ParseResponse> sendAgunanKendaraan(@Body AgunanKendaraanPojo agunanKendaraanPojo);

    @POST(UriApi.hotprospek.requestAppraisal)
    Call<ParseResponse> requestAppraisal(@Body ReqAppraisal ReqAppraisal);



    /* **************** APPROVED ********************* */
    @POST(UriApi.approved.listApproved)
    Call<ParseResponseArr> listApproved (@Body listHotprospek listHotprospek);

    /* **************** REJECTED ********************* */
    @POST(UriApi.rejected.listRejected)
    Call<ParseResponseArr> listRejected (@Body listHotprospek listHotprospek);


    //KONSUMER KPR

    @POST(UriApi.hotprospek.inquiryHotprospekKpr)
    Call<ParseResponse> inquiryHotprospekKpr (@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospek.inquiryDataLengkap)
    Call<ParseResponse> inquiryDataLengkap (@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospek.sendDataLengkapKpr)
    Call<ParseResponse> sendDataLengkapKpr (@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.hotprospek.listHotprospekKpr)
    Call<ParseResponseArr> listHotprospekKpr (@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospek.inquiryPrescreeningKpr)
    Call<ParseResponse> inquiryPrescreeningKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.cekDHNKpr)
    Call<ParseResponse> cekDHNKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.cekDukcapilKpr)
    Call<ParseResponse> cekDukcapilKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.cekSlikKpr)
    Call<ParseResponse> cekSlikKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.sendPrescreeningKpr)
    Call<ParseResponse> sendPrescreeningKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.downloadSlikKpr)
    Call<ParseResponse> downloadSlikKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.downloadSlikPasanganKpr)
    Call<ParseResponse> downloadSlikPasanganKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.inquiryRemaksSlikKpr)
    Call<ParseResponse> inquiryRemaksSlikKpr (@Body Prescreening prescreening);
    @POST(UriApi.hotprospek.sendRemaksSlikKpr)
    Call<ParseResponse> sendRemaksSlikKpr (@Body inputRemaksSlik inputRemaksSlik);

    @POST(UriApi.pipeline.listPipelineKpr)
    Call<ParseResponseArr> listPipelineKpr(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipelineKpr)
    Call<ParseResponse> inquiryPipelineKpr(@Body KonsumerKMGInquiryPipeline inquiryPipeline);

    @POST(UriApi.pipeline.listPihakKetiga)
    Call<ParseResponseArr> listPihakKetiga(@Body inquiryProject inquiryProject);

    @POST(UriApi.pipeline.listProject)
    Call<ParseResponseArr> listProject(@Body inquiryProject inquiryProject);

    @POST(UriApi.pipeline.listProgramKpr)
    Call<ParseResponseArr> listProgramKpr(@Body EmptyRequest EmptyRequest);


    @POST(UriApi.pipeline.listTujuanPenggunaanKpr)
    Call<ParseResponseArr> listTujuanPenggunaanKpr(@Body inquiryInstansi inquiryInstansi);

    @POST(UriApi.pipeline.listBidangPekerjaan)
    Call<ParseResponseArr> listBidangPekerjaan(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listJenisKPR)
    Call<ParseResponseArr> listJenisKPR(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.sendDataPipelineKpr)
    Call<ParseResponse> sendDataPipelineKpr(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospekKpr)
    Call<ParseResponse> savePipelineHotprospekKpr(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.flpp.savePipelineHotprospekFlpp)
    Call<ParseResponse> savePipelineHotprospekFlpp(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.hotprospek.listJenisPekerjaan)
    Call<ParseResponse> listJenisPekerjaan(@Body EmptyRequest emptyRequest);

    @POST(UriApi.flpp.listJenisPekerjaanFlpp)
    Call<ParseResponse> listJenisPekerjaanFlpp(@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospek.inquiryDataFinansialKpr)
    Call<ParseResponse> inquiryDataFinansialKpr (@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.inquiryDataFinansialKprFlpp)
    Call<ParseResponse> inquiryDataFinansialKprFlpp (@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.simpanDataFinansialKpr)
    Call<ParseResponse> simpanDataFinansialKpr (@Body SimpanDataFinansialKpr SimpanDataFinansialKpr);

    @POST(UriApi.hotprospek.validasiDataFinansialKpr)
    Call<ParseResponse> validasiDataFinansialKpr (@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);

    @POST(UriApi.flpp.simpanDataFinansialFlpp)
    Call<ParseResponse> simpanDataFinansialKprFlpp (@Body SimpanDataFinansialKpr SimpanDataFinansialKpr);

    @POST(UriApi.flpp.validasiDataFinansialFlpp)
    Call<ParseResponse> validasiDataFinansialKprFlpp (@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);

    @POST(UriApi.flpp.listRumahFlpp)
    Call<ParseResponseArr> listRumahFlpp (@Body ReqRumahFlpp ReqRumahFlpp);

    @POST(UriApi.flpp.getBlokByIdLokasi)
    Call<ParseResponseArr> getBlokByIdLokasi (@Body ReqRumahFlpp ReqRumahFlpp);

    @POST(UriApi.hotprospek.updateScoringKpr)
    Call<ParseResponse> updateScoringKpr (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.flpp.updateScoringFlpp)
    Call<ParseResponse> updateScoringFlpp (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumenKpr)
    Call<ParseResponse> inquiryKelengkapanDokumenKpr (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.flpp.inquiryKelengkapanDokumenFlpp)
    Call<ParseResponse> inquiryKelengkapanDokumenFlpp (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKprKaryawanPns)
    Call<ParseResponse> sendKelengkapanDokumenKprKaryawanPns (@Body KonsumerKPRKaryawanPnsInputKelengkapanDokumen KonsumerKPRKaryawanPnsInputKelengkapanDokumen);

    @POST(UriApi.flpp.sendKelengkapanDokumenKprFlpp)
    Call<ParseResponse> sendKelengkapanDokumenKprFlpp (@Body KonsumerKprFlppInputKelengkapanDokumen KonsumerKprFlppInputKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKprKaryawanPns)
    Call<ParseResponse> sendKelengkapanDokumenKprWiraswasta (@Body KonsumerKPRWiraswastaInputKelengkapanDokumen KonsumerKPRWiraswastaInputKelengkapanDokumen);

    @POST(UriApi.hotprospek.inquirySektorEkonomiKpr)
    Call<ParseResponse> inquirySektorEkonomiKpr (@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.flpp.inquirySektorEkonomiFlpp)
    Call<ParseResponse> inquirySektorEkonomiFlpp (@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospek.sendDataSektorEkonomiKpr)
    Call<ParseResponse> sendDataSektorEkonomiKpr (@Body InputSektorEkonomiKpr InputSektorEkonomiKpr);

    @POST(UriApi.hotprospek.getKategSektorEkonomiKpr)
    Call<ParseResponse> getKategSektorEkonomiiByGroupKpr (@Body searchListSektorEkonomi searchListSektorEkonomi);

    @POST(UriApi.hotprospek.searcSektorEkonomiKpr)
    Call<ParseResponse> searcSektorEkonomiKpr (@Body searchSektorEkonomi searchSektorEkonomi);

    @POST(UriApi.pipeline.pipelineToHotprospekKpr)
    Call<ParseResponse> pipelineToHotprospekKpr (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipelineKpr)
    Call<ParseResponse> rejectPipelineKpr (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.hotprospek.searchPasar)
    Call<ParseResponseArr> searchPasar (@Body searchAddress searchAddress);

    @POST(UriApi.hotprospek.listJenisBangunan)
    Call<ParseResponseArr> listJenisBangunan (@Body EmptyRequest emptyRequest);

    //AO SILANG
    @POST(UriApi.hotprospek.listPenilaianAgunan)
    Call<ParseResponse> listPenilaianAgunan (@Body listAppraisal listAppraisal);

    @POST(UriApi.hotprospek.rejectAppraisal)
    Call<ParseResponse> rejectAppraisal (@Body rejectAppraisal rejectAppraisal);

    @POST(UriApi.hotprospek.selesaiAppraisal)
    Call<ParseResponse> selesaiAppraisal (@Body SelesaiAppraisal SelesaiAppraisal);

    @POST(UriApi.hotprospek.sendPutusanKpr)
    Call<ParseResponse> sendPutusanKpr (@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.kirimApraisal)
    Call<ParseResponse> kirimApraisal(@Body ReqKirimAppraisal reqKirimAppraisal);

    //MONITORING
    @POST(UriApi.hotprospek.listMonitoringNasabah)
    Call<ParseResponse> listMonitoringNasabah(@Body ReqMonitoringNasabah ReqMonitoringNasabah);

    @POST(UriApi.hotprospek.listMonitoringNasabahNpf)
    Call<ParseResponse> listMonitoringNasabahNpf(@Body ReqMonitoringNasabah ReqMonitoringNasabah);


    @POST(UriApi.hotprospek.getSaldoNasabah)
    Call<ParseResponseSaldo> getSaldoNasabah(@Body ReqSaldoNasabah ReqSaldoNasabah);

    @POST(UriApi.hotprospek.inquiryRekeningAmanah)
    Call<ParseResponse> inquiryRekeningAmanah(@Body ReqSaldoNasabah ReqSaldoNasabah);

    @POST(UriApi.hotprospek.rankingAoTop)
    Call<ParseResponseArr> getRankingAoTop(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.hotprospek.rankingAoBottom)
    Call<ParseResponseArr> getRankingAoBottom(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.hotprospek.getRiwayatMutasi)
    Call<ParseResponse> getRiwayatMutasi(@Body ReqRiwayatMutasi ReqRiwayatMutasi);

    @POST(UriApi.hotprospek.getRataRata)
    Call<ParseResponse> getRataRata(@Body EmptyRequest EmptyRequest);






    //END OF KONSUMER KPR


    //pakai inquiry rpc karena dia sama sama request id Aplikasi
    @POST(UriApi.hotprospek.inquiryDataFinansial)
    Call<ParseResponseDataInstansi> inquiryDataFinansial (@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.simpanDataFinansial)
    Call<ParseResponse> simpanDataFinansial (@Body SimpanDataFinansial SimpanDataFinansial);

    @POST(UriApi.hotprospek.validasiDataFinansial)
    Call<ParseResponse> validasiDataFinansial (@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);


    @POST(UriApi.hotprospek.inquiryScoringKmg)
    Call<ParseResponse> inquiryScoringKmg (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.updateScoringKmg)
    Call<ParseResponse> updateScoringKmg (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.general.simpanFeedback)
    Call<ParseResponse> simpanFeedback (@Body SimpanFeedback SimpanFeedback);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumenKonsumer)
    Call<ParseResponse> inquiryKelengkapanDokumenKonsumer (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKonsumer)
    Call<ParseResponse> sendKelengkapanDokumenKonsumer (@Body KonsumerKMGInputKelengkapanDokumen inputKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendPutusanKmg)
    Call<ParseResponse> sendPutusanKmg (@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.cekNikPasangan)
    Call<ParseResponseDataDukcapil> cekNikPasangan (@Body inquiryNikPasangan inquiryNikPasangan);

    @POST(UriApi.hotprospek.listAsuransi)
    Call<ParseResponseArr> listAsuransi (@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospek.inquiryIjk)
    Call<ParseResponse> inquiryIjk (@Body inquiryIjk inquiryIjk);

    @POST(UriApi.pipeline.getTujuanPenggunaan)
    Call<ParseResponseArr> getTujuanPenggunaan (@Body inquiryTujuan inquiryTujuan);

    @Multipart
    @POST(UriApi.hotprospek.uploadFile)
    Call<ParseResponse> uploadFile (@Part MultipartBody.Part file);




    //KONSUMER PURNA
    @POST(UriApi.hotprospek.inquiryDataLengkapKpr)
    Call<ParseResponse> inquiryDataLengkapKpr (@Body inquiryDataLengkap inquiryDataLengkap);


    //Purna
    @POST(UriApi.pipeline.listProgram)
    Call<ParseResponseArr> getListProgram (@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listInstitusi)
    Call<ParseResponseArr> listInstitusi (@Body inquiryTujuan inquiryTujuan);

    @POST(UriApi.pipeline.listRekananDM)
    Call<ParseResponseArr> getListRekananDM (@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listKategNasabah)
    Call<ParseResponseArr> getListKategNasabah (@Body inquiryListKateg inquiryListKateg);

    //FLPP
    @POST(UriApi.flpp.listFollowupFlpp)
    Call<ParseResponseArr> listFollowupFlpp (@Body ReqFollowUpFlpp ReqFollowUpFlpp);

    @POST(UriApi.flpp.listKonfirmasiFlpp)
    Call<ParseResponseArr> listKonfirmasiFlpp (@Body ReqUid ReqUid);

    @POST(UriApi.flpp.listTelahKonfirmasiFlpp)
    Call<ParseResponseArr> listTelahKonfirmasiFlpp (@Body ReqUid ReqUid);


    @POST(UriApi.flpp.listKotaKabupatenFlpp)
    Call<ParseResponseArr> listKotaKabupatenFlpp (@Body ReqKodeProvinsi ReqKodeProvinsi);

    @POST(UriApi.flpp.listKelurahanFlpp)
    Call<ParseResponseArr> listKelurahanFlpp (@Body ReqKodeWilayah ReqKodeWilayah);

    @POST(UriApi.flpp.getPihakketiga)
    Call<ParseResponse> getPihakketiga (@Body ReqPihakKetiga ReqPihakKetiga);

    @POST(UriApi.flpp.getDataSikasep)
    Call<ParseResponse> getDataSikasep (@Body ReqDataSikasep ReqDataSikasep);

    @POST(UriApi.flpp.sendAgunanTanahBangunanFlpp)
    Call<ParseResponse> sendAgunanTanahBangunanFlpp(@Body AgunanTanahBangunanPojo agunanTanahBangunanPojo);


    @POST(UriApi.flpp.inquiryKodeWilayah)
    Call<ParseResponse> inquiryKodeWilayah(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.getFotoFlpp)
    Call<ParseResponse> getFotoFlpp(@Body ReqFotoFlpp ReqFotoFlpp);

    @POST(UriApi.flpp.cekSikasepByKtp)
    Call<ParseResponse> cekSikasepByKtp(@Body ReqFotoFlpp ReqFotoFlpp);

    @POST(UriApi.flpp.getDataUpdateIdLokasi)
    Call<ParseResponse> getDataUpdateIdLokasi(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.updateIdLokasiFlpp)
    Call<ParseResponse> updateIdLokasiFlpp(@Body ReqSimpanUpdateIdLokasi ReqSimpanUpdateIdLokasi);

    @POST(UriApi.flpp.loginSikasep)
    Call<ParseResponse> loginSikasep(@Body ReqLoginSikasep ReqLoginSikasep);

    @POST(UriApi.flpp.simpanFollowUpFlpp)
    Call<ParseResponse> simpanFollowUpFlpp(@Body ReqSimpanFollowUpFlpp ReqSimpanFollowUpFlpp);

    @POST(UriApi.flpp.simpanKonfirmasiFlpp)
    Call<ParseResponse> simpanKonfirmasiFlpp(@Body ReqSimpanKonfirmasiFlpp ReqSimpanKonfirmasiFlpp);

    @POST(UriApi.flpp.updatePipelineFlpp)
    Call<ParseResponse> updatePipelineFlpp(@Body ReqSimpanKonfirmasiFlpp ReqSimpanKonfirmasiFlpp);

    @POST(UriApi.flpp.sendDataPipelineKprFlpp)
    Call<ParseResponse> sendDataPipelineKprFlpp(@Body KonsumerKPRInputPipeline inputPipeline);

    //List Gadai
    @POST(UriApi.gadaiNOS.listAplikasiGadai)
    Call<ParseResponseGadai> sendDataListApplikasi(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.detailAplikasiGadai)
    Call<ParseResponseAgunan> sendDetailAplikasiGadai(@Body ReqListGadai ReqListGadai);

    @POST(UriApi.gadaiNOS.sendAplikasiGadai)
    Call<ParseResponseAgunan> sendDataCaptureGadai(@Body ReqListGadai ReqListGadai);
}

