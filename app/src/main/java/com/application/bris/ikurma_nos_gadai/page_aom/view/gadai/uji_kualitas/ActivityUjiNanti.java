package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_kualitas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseUjiKualitas;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqUjiKualitas;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.UjiKualitasGadaiNantiBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataUjiKualitas;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityUjiNanti extends AppCompatActivity implements View.OnClickListener, CameraListener {
    UjiKualitasGadaiNantiBinding binding;
    Date c = Calendar.getInstance().getTime();
    View view;
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    String formattedDate = df.format(c);
    Date date = new Date();
    Call <ParseResponseUjiKualitas> call;
    SimpleDateFormat df1 = new SimpleDateFormat("YYYY-MM-dd");
    Calendar c1 = Calendar.getInstance();
    String currentDate = df1.format(date);
    private Uri uri_agunan_tersegel;
    private Bitmap bitmap_agunan_tersegel;
    String clicker;
    private String idAplikasi;
    DataUjiKualitas dataUjiKualitas;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private void SendData() {
        if (bitmap_agunan_tersegel == null) {
            AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), "Foto tidak lengkap, mohon lengkapi terbebih dahulu");
        } else {
            binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
            JsonObject obj1 = new JsonObject();
            obj1.addProperty("UserSubmit", appPreferences.getNik());
            obj1.addProperty("NoAplikasi", getIntent().getStringExtra("NoAplikasi"));
            obj1.addProperty("kodeCabang", appPreferences.getKodeCabang());
            obj1.addProperty("UjiKwalitasHariIni", "NANTI");
            /*obj1.addProperty("FotoAgunan", AppUtil.encodeImageTobase64(bitmap_agunan).toString());
            obj1.addProperty("FotoPengunjian",  AppUtil.encodeImageTobase64(bitmap_pengunjian).toString());*/
            obj1.addProperty("FotoAgunanTersegel", AppUtil.encodeImageTobase64(bitmap_agunan_tersegel).toString());
            /*obj1.addProperty("StatusAgunan", "SESUAI");
            obj1.addProperty("Description", "OK");*/
            ReqUjiKualitas req = new ReqUjiKualitas();
            req.setRrn(AppUtil.getRandomReferenceNumber());
            req.setchannel("Mobile");
            req.setdata(obj1);
            call = apiClientAdapter.getApiInterface().sendUjiKualitas(req);
            call.enqueue(new Callback<ParseResponseUjiKualitas>() {
                @Override
                public void onResponse(Call<ParseResponseUjiKualitas> call, Response<ParseResponseUjiKualitas> response) {
                    try {
                        if (response.isSuccessful()) {
                            binding.loading.progressbarLoading.setVisibility(View.GONE);
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                AppUtil.notifsuccess(ActivityUjiNanti.this, findViewById(android.R.id.content), "Berhasil Update");
                                Toast.makeText(ActivityUjiNanti.this, "Berhasil capture", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponseUjiKualitas> call, Throwable t) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = UjiKualitasGadaiNantiBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(binding.getRoot());
        idAplikasi = getIntent().getStringExtra("idAplikasi");
        customToolbar();
        setClicker();
        initilize();
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
        Call <ParseResponseAgunan> call = apiClientAdapter.getApiInterface().sendDetailAplikasiGadai(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataUjiKualitas>() {
                            }.getType();
                            dataUjiKualitas = gson.fromJson(listDataString, type);
                            binding.etNomerApplikasi.setText(dataUjiKualitas.getNomorAplikasiGadai());
                            binding.etCabang.setText(dataUjiKualitas.getCabang());
                            binding.etNamaNasabah.setText(dataUjiKualitas.getNamaNasabah());
                            binding.etNomerLd.setText(dataUjiKualitas.getLDNumber());
                            binding.etTglTransaksi.setText(AppUtil.parseTanggalGeneral(dataUjiKualitas.getTanggalPencairan(), "yyyy-MM-dd hh:mm:ss", "dd-MMM-YYYY"));
                            binding.etTglJatohTempo.setText(AppUtil.parseTanggalGeneral(dataUjiKualitas.getTanggalJatuhTempo(), "yyyy-MM-dd hh:mm:ss", "dd-MMM-YYYY"));
                        } else {
                            AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityUjiNanti.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("Uji Kualitas Nanti");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setClicker(){
        binding.rlAgunanTersegel.setOnClickListener(this);
        binding.ivAgunanTersegel.setOnClickListener(this);
        binding.btnAgunanTersegel.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
        //disable
        binding.etNamaNasabah.setFocusable(false);
        binding.etTglJatohTempo.setFocusable(false);
        binding.etCabang.setFocusable(false);
        binding.etNomerApplikasi.setFocusable(false);
        binding.etNomerLd.setFocusable(false);
        binding.etTglTransaksi.setFocusable(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_agunan_tersegel:
            case R.id.iv_agunan_tersegel:
            case R.id.btn_agunan_tersegel:
                clicker = "agunantersegel";
                BSBottomCamera.displayWithTitle(ActivityUjiNanti.this.getSupportFragmentManager(), this, "Upload Segel Agunan");
                break;
            case R.id.btn_send:
                SendData();
                break;
        }


    }


    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                if (clicker.equalsIgnoreCase("agunantersegel")) {
                    openCamera(TAKE_PICTURE_AGUNAN_TERSEGEL, "agunantersegel");
                }
                break;
            case "Pick Photo":
                if (clicker.equalsIgnoreCase("agunantersegel")) {
                    openGalery(PICK_PICTURE_AGUNAN_TERSEGEL);
                }
                break;
        }

    }

    private final int TAKE_PICTURE_ = 1, TAKE_PICTURE_AGUNAN = 3, TAKE_PICTURE_PENGUNJIAN = 5, TAKE_PICTURE_AGUNAN_TERSEGEL = 7;
    private final int PICK_PICTURE_ = 2, PICK_PICTURE_AGUNAN = 4, PICK_PICTURE_PENGUNJIAN = 6, PICK_PICTURE_AGUNAN_TERSEGEL = 8;

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
        if (resultCode != 0) {
        switch (requestCode) {
            case TAKE_PICTURE_AGUNAN_TERSEGEL:
            case PICK_PICTURE_AGUNAN_TERSEGEL:
                setDataImage(uri_agunan_tersegel, bitmap_agunan_tersegel, binding.ivAgunanTersegel, imageReturnedIntent, "agunantersegel");
                break;
        }
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
                if (clicker.equalsIgnoreCase("agunantersegel")) {
                    bitmap_agunan_tersegel= bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
