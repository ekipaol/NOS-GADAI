package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.serah_terima;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_gadai.BuildConfig;
import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.Error;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseListSerahTerima;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqSerahTerima;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityDetailSerahTerimaBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DetailCaptureAgunan;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataSerahTerima;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan.CaptureAgunanActivity;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_kualitas.ActivityUjiNanti;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSerahTerimaActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, GenericListenerOnSelect {

    ActivityDetailSerahTerimaBinding binding;
    Call<ParseResponseAgunan> call;
    String clicker;
    private Uri uri_nasabah;
    private Bitmap bitmap_nasabah;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataSerahTerima dataSerahTerima;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bersama_nasabah:
            case R.id.rl_bersama_nasabah:
            case R.id.iv_bersama_nasabah:
                clicker = "nasabah";
                BSBottomCamera.displayWithTitle(this.getSupportFragmentManager(), this, "Foto Nasabah");
                break;
            case R.id.btn_send:
            case R.id.ll_btn_send:
                SendData();
        }
    }

    private void SendData() {
        if (bitmap_nasabah == null) {
            AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), "Foto tidak lengkap, mohon lengkapi lerbebih dahulu");
        } else {
            binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
            JsonObject obj1 = new JsonObject();
            obj1.addProperty("NoAplikasi", getIntent().getStringExtra("NoAplikasi"));
            obj1.addProperty("kodeCabang", appPreferences.getKodeKantor());
            obj1.addProperty("konfirmasi", "YA");
            obj1.addProperty("Pemberi", getIntent().getStringExtra("IDPemberi"));
            obj1.addProperty("Penerima", getIntent().getStringExtra("IDPenerima"));
            obj1.addProperty("Description", binding.etDeskripsi.getText().toString());
            obj1.addProperty("Aktifitas", "SerahTerimaKeNasabah");
            obj1.addProperty("FotoSerahTerima", AppUtil.encodeImageTobase64(bitmap_nasabah).toString());
            ReqListGadai req = new ReqListGadai();
            req.setkchannel("Mobile");
/*
        req.setrrn("001100323129");
*/
            req.setdata(obj1);
            call = apiClientAdapter.getApiInterface().sendSerahTerima(req);
            call.enqueue(new Callback<ParseResponseAgunan>() {
                @Override
                public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                    try {
                        if (response.isSuccessful()) {
                            binding.loading.progressbarLoading.setVisibility(View.GONE);
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                AppUtil.notifsuccess(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), "Berhasil Update");
                                Toast.makeText(DetailSerahTerimaActivity.this, "Berhasil Serah Terima", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityDetailSerahTerimaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setclick
        setClickListener();
        //disableText
        setDisabledText();
        //initialize
        initilize();
        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //Parameter Dropdown

        apiClientAdapter = new ApiClientAdapter(this, "https://10.0.116.105/");
        appPreferences = new AppPreferences(this);
    }

    private void initilize() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("FilterNoAplikasi", getIntent().getStringExtra("NoAplikasi"));
        obj1.addProperty("FilterLDNumber", "NONE");
        obj1.addProperty("FilterSBGE", "NONE");

        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        call = apiClientAdapter.getApiInterface().sendDetailAplikasiGadai(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataSerahTerima>() {
                            }.getType();
                            dataSerahTerima = gson.fromJson(listDataString, type);
                            binding.etNomerApplikasi.setText(dataSerahTerima.getNoAplikasi());
                            binding.etCabang.setText(dataSerahTerima.getKodeCabang());
                            binding.etNamaNasabah.setText(dataSerahTerima.getNamaSesuaiKTP());
                            binding.etNomerLd.setText(dataSerahTerima.getlDNumber());
                            binding.etTglAktifitas.setText(AppUtil.parseTanggalGeneral(dataSerahTerima.getTanggalJatuhTempo(), "yyyy-MM-dd hh:mm:ss", "dd-MMM-YYYY"));
                        } else {
                            AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DetailSerahTerimaActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDisabledText() {
        binding.etCabang.setFocusable(false);
        binding.etNamaNasabah.setFocusable(false);
        binding.etNomerApplikasi.setFocusable(false);
        binding.etTglAktifitas.setFocusable(false);
        binding.etNomerLd.setFocusable(false);
//        binding.etNamaPemberi.setFocusable(false);
//        binding.etNamaPenerima.setFocusable(false);
    }

    private void setClickListener() {
        //Image Click
        binding.btnBersamaNasabah.setOnClickListener(this);
        binding.rlBersamaNasabah.setOnClickListener(this);
        binding.ivBersamaNasabah.setOnClickListener(this);
        //Button Click
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("Serah Terima Agunan");
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                openCamera(TAKE_PICTURE_NASABAH, "Nasabah");

                break;
            case "Pick Photo":
                openGalery(PICK_PICTURE_NASABAH);
                break;
        }

    }

    private final int TAKE_PICTURE_NASABAH = 1;
    private final int PICK_PICTURE_NASABAH = 2;

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case TAKE_PICTURE_NASABAH:
            case PICK_PICTURE_NASABAH:
                setDataImage(uri_nasabah, bitmap_nasabah, binding.ivBersamaNasabah, imageReturnedIntent, "nasabah");
                break;
        }
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data, String namaFoto) {
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv.setImageBitmap(bitmap);
                bitmap_nasabah = bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}