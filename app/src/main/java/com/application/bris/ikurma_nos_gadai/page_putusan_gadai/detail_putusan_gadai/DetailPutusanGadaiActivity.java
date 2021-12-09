package com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqAplikasiGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqChannelAplikasiGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqChannelDataApprove;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqDataApprove;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqDataChanges;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.DetailGadaiActivityBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataDetailGadai;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataFotoGadai;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPutusanGadaiActivity extends AppCompatActivity implements View.OnClickListener {


    DataDetailGadai dataDetailGadai;
    DataFotoGadai dataFotoGadai;
    RincianAgunanAdapter AdapterRincianAgunan;
    FotoAgunanAdapter AdapterFotoAgunan;
    ApiClientAdapter apiClientAdapter;
    DetailGadaiActivityBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;
    private String noAplikasi;
    private SweetAlertDialog dialogConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DetailGadaiActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        main();
        backgroundStatusBar();
        disableOnClicks();
        onClicks();
        otherViewChanges();


        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DetailPutusanGadaiActivity.this.recreate();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        bottomSheetBehavior= BottomSheetBehavior.from(binding.bottomSheet.bottomSheet);
        setSupportActionBar(binding.toolbar.tbRegular);
        if(getIntent().hasExtra("noAplikasi")){
            noAplikasi=getIntent().getStringExtra("noAplikasi");
        }
        else {
            noAplikasi="";
        }

        //pantekan no aplikasi, delete this
//        Toast.makeText(this, "Ada pantekan no aplikasi dan jenis aktifitas foto", Toast.LENGTH_SHORT).show();
//        noAplikasi="GDE2021102500002";
        apiClientAdapter=new ApiClientAdapter(DetailPutusanGadaiActivity.this,"https://10.0.116.105/");

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("Detil Aplikasi");
        loadData();
    }


    public void loadData() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqAplikasiGadai reqGadai=new ReqAplikasiGadai();
        ReqChannelAplikasiGadai req = new ReqChannelAplikasiGadai();
        AppPreferences appPreferences=new AppPreferences(DetailPutusanGadaiActivity.this);

//        reqGadai.setFilterNoAplikasi(noAplikasi);

        //pantekan no aplikasi
        reqGadai.setFilterNoAplikasi(noAplikasi);

        req.setData(reqGadai);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().detailAplikasiGadai(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListRincianAgunan.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataDetailGadai>() {
                        }.getType();

                        dataDetailGadai = gson.fromJson(listDataString, type);
                        AdapterRincianAgunan = new RincianAgunanAdapter(DetailPutusanGadaiActivity.this, dataDetailGadai.getListObjectGadai());
                        binding.rvListRincianAgunan.setLayoutManager(new LinearLayoutManager(DetailPutusanGadaiActivity.this));
                        binding.rvListRincianAgunan.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListRincianAgunan.setAdapter(AdapterRincianAgunan);

                        if(dataDetailGadai.getListObjectGadai()==null||dataDetailGadai.getListObjectGadai().size()==0){
                            binding.labelRincianAgunan.setVisibility(View.GONE);
                        }

                        setData();
                        loadDataFoto();

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DetailPutusanGadaiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void loadDataFoto() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqAplikasiGadai reqGadai=new ReqAplikasiGadai();
        ReqChannelAplikasiGadai req = new ReqChannelAplikasiGadai();
        AppPreferences appPreferences=new AppPreferences(DetailPutusanGadaiActivity.this);

//        reqGadai.setFilterNoAplikasi(noAplikasi);

        //pantekan no aplikasi dan aktifitas
        reqGadai.setFilterNoAplikasi(noAplikasi);
        reqGadai.setFilterAktifitas("Capture Agunan");

        req.setData(reqGadai);

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryListFoto(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListFoto.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get(0).toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataFotoGadai>() {
                        }.getType();

                        dataFotoGadai = gson.fromJson(listDataString, type);


//                        for (int i = 0; i <dataFotoGadai.getListFoto().size() ; i++) {
//                            if(dataFotoGadai.getListFoto().get(i).getImage()==null||dataFotoGadai.getListFoto().get(i).getImage().isEmpty()){
//                                dataFotoGadai.getListFoto().remove(i);
//                            }
//                        }

                        AdapterFotoAgunan = new FotoAgunanAdapter(DetailPutusanGadaiActivity.this, dataFotoGadai.getListFoto());
                        binding.rvListFoto.setLayoutManager(new LinearLayoutManager(DetailPutusanGadaiActivity.this));
                        binding.rvListFoto.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListFoto.setAdapter(AdapterFotoAgunan);

                        if(dataFotoGadai.getListFoto()==null||dataFotoGadai.getListFoto().size()==0){
                            binding.labelRincianAgunan.setVisibility(View.GONE);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DetailPutusanGadaiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
               t.printStackTrace();
            }
        });
    }

    private void lakukanPutusan(String jenisPutusan){

        AppPreferences appPreferences = new AppPreferences(DetailPutusanGadaiActivity.this);
        ReqChannelDataApprove reqChannelDataApprove = new ReqChannelDataApprove();
        ReqDataApprove reqDataApprove=new ReqDataApprove();
        ReqDataChanges reqDataChanges=new ReqDataChanges();


        //set data approve
        reqDataApprove.setActionPemutus(jenisPutusan);
        reqDataApprove.setKodeCabang(appPreferences.getKodeKantor());
        reqDataApprove.setNoAplikasi(noAplikasi);
        reqDataApprove.setPejabatPemutus((appPreferences.getNik()));

        if(jenisPutusan.equalsIgnoreCase("tolak")){
            reqDataApprove.setReasonPenolakan(binding.bottomSheet.etCatatan.getText().toString());
        }
        else{
            reqDataApprove.setReasonPenolakan("");
        }

        //set data changes
        reqDataChanges.setProgramGadai(dataDetailGadai.getProgranGadai());

        //compile in data channel

        //get tanggal to generate random rrn
        String date = String.valueOf(android.text.format.DateFormat.format("ddMMyyyy", new java.util.Date()));
        reqChannelDataApprove.setRrn(date+AppUtil.generateRandom6DigitNumber());
        reqChannelDataApprove.setChannel("Mobile");
        reqChannelDataApprove.setData(reqDataApprove);
        reqChannelDataApprove.setDataChanges(reqDataChanges);
        reqChannelDataApprove.setChangeIndicator("FALSE");
        Call<ParseResponse> call;

        call = apiClientAdapter.getApiInterface().submitTaksiranPemutus(reqChannelDataApprove);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();

                        dialogConfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialogConfirm.setTitleText("Putusan Berhasil");

                        if(jenisPutusan.equalsIgnoreCase("tolak")){
                            dialogConfirm.setContentText("Aplikasi berhasil ditolak\n");
                        }
                        else{
                            dialogConfirm.setContentText("Aplikasi berhasil diapprove\n");
                        }
                        dialogConfirm.setConfirmText("OK");
                        dialogConfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialogConfirm.dismissWithAnimation();
                               finish();
                            }
                        });
                        dialogConfirm.showCancelButton(false);


                    } else {
                        dialogConfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialogConfirm.setTitle("Terjadi Kesalahan");
                        dialogConfirm.setContentText(response.body().getMessage()+"\n");
                        dialogConfirm.setConfirmText("Coba lagi");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                dialogConfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialogConfirm.setTitle("Gagal");
                dialogConfirm.setContentText("Gagal terhubung ke server");
                dialogConfirm.setConfirmText("Ok");

            }
        });
    }

    private void setData(){
        //DATA PENGAJUAN
        binding.etNoPermohonan.setText(dataDetailGadai.getNoAplikasi());
        binding.etNama.setText(dataDetailGadai.getNamaSesuaiKTP());
        binding.etNoRekening.setText(dataDetailGadai.getNoRekeningPencairan());

        //PENGUSUL DAN REVIEWER
        binding.etNamaNipPengusul.setText(dataDetailGadai.getNamaPengusul()+"/"+dataDetailGadai.getPejabatPengusul());
        binding.etNamaNipReviewer.setText(dataDetailGadai.getNamaReviewer()+"/"+dataDetailGadai.getPejabatReviewer());

        //FASILITAS DIBERIKAN
        binding.etNilaiTaksiran.setText(AppUtil.parseRupiahNoSymbolWithTitik(dataDetailGadai.getTotalTaksiran()));
        binding.etNilaiPembiayaan.setText(AppUtil.parseRupiahNoSymbolWithTitik(dataDetailGadai.getPinjamanGadaiDiambil()));
        binding.etNilaiPengajuan.setText(AppUtil.parseRupiahNoSymbolWithTitik(dataDetailGadai.getPinjamanGadaiDiambil()));
        binding.etBiayaAdministrasi.setText(AppUtil.parseRupiahNoSymbolWithTitik(dataDetailGadai.getBiayaAdministrasi()));
        binding.etBiayaUjroh.setText(AppUtil.parseRupiahNoSymbolWithTitik(dataDetailGadai.getNominalUjrohFinal()));

    }

    private void disableOnClicks(){
//        AppUtil.disableEditTexts(binding.getRoot());
        binding.etNoPermohonan.setFocusable(false);
        binding.etNama.setFocusable(false);
        binding.etNoRekening.setFocusable(false);
        binding.etNamaNipReviewer.setFocusable(false);
        binding.etNamaNipPengusul.setFocusable(false);
        binding.etNilaiTaksiran.setFocusable(false);
        binding.etNilaiPembiayaan.setFocusable(false);
        binding.etNilaiPengajuan.setFocusable(false);
        binding.etBiayaAdministrasi.setFocusable(false);
        binding.etBiayaUjroh.setFocusable(false);
    }

    private void otherViewChanges(){
        //default bottom shit position
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


    @Override
    public void onBackPressed() {
        finish();
    }


    private void onClicks(){
        binding.btPutusanGadai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bottomSheet.etCatatan.setEnabled(true);
                binding.bottomSheet.etCatatan.setFocusable(true);

                binding.bottomSheet.etCatatan.requestFocus();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.bottomSheet.ivCapsuleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //APPROVE on click
        binding.bottomSheet.btSetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    dialogConfirm= new SweetAlertDialog(DetailPutusanGadaiActivity.this, SweetAlertDialog.NORMAL_TYPE);
                    dialogConfirm.setTitleText("Konfirmasi")
                            .setContentText("Dengan menekan tombol approve maka : \n1. Anda telah melakukan pemutusan fasilitas pembiayaan gadai emas diatas sesuai limit kewenangan memutus pembiayaan (LWWP) yang ada miliki.\n\n")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    dialogConfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                    dialogConfirm.setTitleText("Memproses");
                                    dialogConfirm.setContentText("");
                                    lakukanPutusan("APROVE");
                                }
                            }).setCancelText("Batal")
                            .show();


            }
        });

        //tolak on click
        binding.bottomSheet.btTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.bottomSheet.etCatatan.getText().toString().isEmpty()) {
                    binding.bottomSheet.tfCatatan.setError("Catatan Wajib Diisi", true);
                } else {
                         dialogConfirm= new SweetAlertDialog(DetailPutusanGadaiActivity.this, SweetAlertDialog.NORMAL_TYPE);
                    dialogConfirm.setTitleText("Konfirmasi")
                                .setContentText("Anda yakin akan menolak pembiayaan?\n")
                                .setConfirmText("Ya")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dialogConfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                        dialogConfirm.setTitleText("Memproses");
                                        dialogConfirm.setContentText("");
                                        lakukanPutusan("TOLAK");
                                    }
                                }).setCancelText("Batal")
                                .show();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            //close bottom sheet on click outside
            if (bottomSheetBehavior.getState()== BottomSheetBehavior.STATE_EXPANDED) {

                Rect outRect = new Rect();
                binding.bottomSheet.bottomSheet.getGlobalVisibleRect(outRect);

                if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}