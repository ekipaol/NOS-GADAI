package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan;

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
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityCaptureAgunanBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DetailCaptureAgunan;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.CameraListener;
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


public class CaptureAgunanActivity extends AppCompatActivity implements View.OnClickListener, CameraListener {

    ActivityCaptureAgunanBinding binding;

    Date c = Calendar.getInstance().getTime();
    View view;
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    String formattedDate = df.format(c);
    Call<ParseResponseAgunan> call;
    Date date = new Date();
    SimpleDateFormat df1 = new SimpleDateFormat("YYYY-MM-dd");
    Calendar c1 = Calendar.getInstance();
    String currentDate = df1.format(date);
    String clicker;

    private Uri uri_ktp, uri_nasabah, uri_agunan, uri_additional1, uri_additional2;
    private Bitmap bitmap_ktp=null, bitmap_nasabah, bitmap_agunan=null, bitmap_additional1, bitmap_additional2;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    DetailCaptureAgunan dataAgunan;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_additional1:
            case R.id.rl_additional1:
            case R.id.iv_additional1:
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                clicker = "additional1";
                break;
            case R.id.btn_additional2:
            case R.id.rl_additional2:
            case R.id.iv_additional2:
                clicker = "additional2";
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                break;
            case R.id.btn_foto_agunan:
            case R.id.rl_foto_agunan:
            case R.id.iv_foto_agunan:
                clicker = "agunan";
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                break;
            case R.id.btn_bersama_nasabah:
            case R.id.rl_bersama_nasabah:
            case R.id.iv_bersama_nasabah:
                clicker = "nasabah";
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                break;
            case R.id.btn_foto_ktp:
            case R.id.rl_foto_ktp:
            case R.id.iv_foto_ktp:
                clicker = "ktp";
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                break;
            case R.id.btn_send:
            case R.id.ll_btn_send:
                SendData();
        }
    }

    private void SendData() {
        if (bitmap_nasabah == null || bitmap_agunan == null || bitmap_ktp == null) {
            AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content),"Foto Tidak Lengkap, Mohon Lengkapi Terbebih Dahulu");
        } else {
            binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
            JsonObject obj1 = new JsonObject();
            obj1.addProperty("NoAplikasi", getIntent().getStringExtra("NoAplikasi"));
//        obj1.addProperty("NoAplikasi", "GDE2021092800002");
            obj1.addProperty("kodeCabang", binding.etCabang.getText().toString());

            obj1.addProperty("photoNasabah", AppUtil.encodeImageTobase64(bitmap_nasabah).toString());
            obj1.addProperty("photoAgunan", AppUtil.encodeImageTobase64(bitmap_agunan).toString());
            obj1.addProperty("photoKTP", AppUtil.encodeImageTobase64(bitmap_ktp).toString());

            if (bitmap_additional1 != null) {
                obj1.addProperty("photoAdd1", AppUtil.encodeImageTobase64(bitmap_additional1).toString());
            }
            if (bitmap_additional2 != null) {
                obj1.addProperty("photoAdd2", AppUtil.encodeImageTobase64(bitmap_additional2).toString());
            }
            obj1.addProperty("userSubmit", appPreferences.getKodeAo());
            ReqListGadai req = new ReqListGadai();
            req.setkchannel("Mobile");
            req.setdata(obj1);
            call = apiClientAdapter.getApiInterface().sendDataCaptureGadai(req);
            call.enqueue(new Callback<ParseResponseAgunan>() {
                @Override
                public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                    try {
                        if (response.isSuccessful()) {
                            binding.loading.progressbarLoading.setVisibility(View.GONE);
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                AppUtil.notifsuccess(CaptureAgunanActivity.this, findViewById(android.R.id.content), "Berhasil Update");
                                Toast.makeText(CaptureAgunanActivity.this, "Berhasil capture", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize
        apiClientAdapter = new ApiClientAdapter(this, "https://10.0.116.105/");
        appPreferences = new AppPreferences(this);
        binding = ActivityCaptureAgunanBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(binding.getRoot());
        initilize();
        //setclick
        setClickListener();
        //disableText
        setDisabledText();
        //setTextValue
        binding.etTglTransaksi.setText(formattedDate.toString());


        c1.add(android.icu.util.Calendar.MONTH, 4);
        df1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date resultDate = c1.getTime();
        String dueDate = df1.format(resultDate);
        binding.etTglJatohTempo.setText(dueDate);

        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
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
                            Type type = new TypeToken<DetailCaptureAgunan>() {
                            }.getType();
                            dataAgunan = gson.fromJson(listDataString, type);
                            binding.etNomerApplikasi.setText(dataAgunan.getNoAplikasi());
                            binding.etKtp.setText(dataAgunan.getNoKTP());
                            binding.etCabang.setText(dataAgunan.getKodeCabang());
                            binding.etNamaNasabah.setText(dataAgunan.getNamaSesuaiKTP());
//                            binding.etTglTransaksi.setText(dataAgunan.getTanggalPencairan());
//                            binding.etTglJatohTempo.setText(dataAgunan.getTanggalJatuhTempo());
                            binding.etJangkaWaktu.setText(dataAgunan.getTenor());
                        } else {
                            AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(CaptureAgunanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("Add Photo Jaminan");
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setDisabledText() {
        binding.etCabang.setFocusable(false);
        binding.etJangkaWaktu.setFocusable(false);
        binding.etKtp.setFocusable(false);
        binding.etNamaNasabah.setFocusable(false);
        binding.etNomerApplikasi.setFocusable(false);
        binding.etTglJatohTempo.setFocusable(false);
        binding.etTglTransaksi.setFocusable(false);
    }

    private void setClickListener() {
        //Image Click
        binding.btnAdditional1.setOnClickListener(this);
        binding.btnAdditional2.setOnClickListener(this);
        binding.btnFotoAgunan.setOnClickListener(this);
        binding.btnBersamaNasabah.setOnClickListener(this);
        binding.btnFotoKtp.setOnClickListener(this);
        binding.rlAdditional1.setOnClickListener(this);
        binding.rlAdditional2.setOnClickListener(this);
        binding.rlFotoAgunan.setOnClickListener(this);
        binding.rlBersamaNasabah.setOnClickListener(this);
        binding.rlFotoKtp.setOnClickListener(this);
        binding.ivAdditional1.setOnClickListener(this);
        binding.ivAdditional2.setOnClickListener(this);
        binding.ivFotoAgunan.setOnClickListener(this);
        binding.ivBersamaNasabah.setOnClickListener(this);
        binding.ivFotoKtp.setOnClickListener(this);
        //Button Click
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);


    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                if (clicker.equalsIgnoreCase("nasabah")) {
                    openCamera(TAKE_PICTURE_NASABAH, "Nasabah");
                } else if (clicker.equalsIgnoreCase("ktp")) {
                    openCamera(TAKE_PICTURE_KTP, "ktp");
                } else if (clicker.equalsIgnoreCase("agunan")) {
                    openCamera(TAKE_PICTURE_AGUNAN, "agunan");
                } else if (clicker.equalsIgnoreCase("additional1")) {
                    openCamera(TAKE_PICTURE_ADDITIONAL1, "additional1");
                } else if (clicker.equalsIgnoreCase("additional2")) {
                    openCamera(TAKE_PICTURE_ADDITIONAL2, "additional2");
                }
                break;
            case "Pick Photo":
                if (clicker.equalsIgnoreCase("nasabah")) {
                    openGalery(PICK_PICTURE_NASABAH);
                } else if (clicker.equalsIgnoreCase("ktp")) {
                    openGalery(PICK_PICTURE_KTP);
                } else if (clicker.equalsIgnoreCase("agunan")) {
                    openGalery(PICK_PICTURE_AGUNAN);
                } else if (clicker.equalsIgnoreCase("additional1")) {
                    openGalery(PICK_PICTURE_ADDITIONAL1);
                } else if (clicker.equalsIgnoreCase("additional2")) {
                    openGalery(PICK_PICTURE_ADDITIONAL2);
                }
                break;
        }

    }

    private final int TAKE_PICTURE_NASABAH = 1, TAKE_PICTURE_AGUNAN = 3, TAKE_PICTURE_KTP = 5, TAKE_PICTURE_ADDITIONAL1 = 7, TAKE_PICTURE_ADDITIONAL2 = 9;
    private final int PICK_PICTURE_NASABAH = 2, PICK_PICTURE_AGUNAN = 4, PICK_PICTURE_KTP = 6, PICK_PICTURE_ADDITIONAL1 = 8, PICK_PICTURE_ADDITIONAL2 = 10;

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
            case TAKE_PICTURE_AGUNAN:
            case PICK_PICTURE_AGUNAN:
                setDataImage(uri_agunan, bitmap_agunan, binding.ivFotoAgunan, imageReturnedIntent, "agunan");
                break;
            case TAKE_PICTURE_KTP:
            case PICK_PICTURE_KTP:
                setDataImage(uri_ktp, bitmap_ktp, binding.ivFotoKtp, imageReturnedIntent, "ktp");
                break;
            case TAKE_PICTURE_ADDITIONAL1:
            case PICK_PICTURE_ADDITIONAL1:
                setDataImage(uri_additional1, bitmap_additional1, binding.ivAdditional1, imageReturnedIntent, "additional1");
                break;
            case TAKE_PICTURE_ADDITIONAL2:
            case PICK_PICTURE_ADDITIONAL2:
                setDataImage(uri_additional2, bitmap_additional2, binding.ivAdditional2, imageReturnedIntent, "additional2");
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
                if (clicker.equalsIgnoreCase("nasabah")) {
                    bitmap_nasabah = bitmap;
                } else if (clicker.equalsIgnoreCase("ktp")) {
                    bitmap_ktp = bitmap;
                } else if (clicker.equalsIgnoreCase("agunan")) {
                    bitmap_agunan = bitmap;
                } else if (clicker.equalsIgnoreCase("additional1")) {
                    bitmap_additional1 = bitmap;
                } else if (clicker.equalsIgnoreCase("additional2")) {
                    bitmap_additional2 = bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
