package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan;

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
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityDetailHasilPenjualanBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DetailCaptureAgunan;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHasilPenjualanActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, GenericListenerOnSelect {

    ActivityDetailHasilPenjualanBinding binding;

    Call<ParseResponse> call;
    String clicker;

    private Uri uri_dokumen;
    private Bitmap bitmap_dokumen;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    DetailCaptureAgunan dataAgunan;
    List<MGenericModel> dataDropdownPenjualan = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityDetailHasilPenjualanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setclick
        setClickListener();
        endIconClick();
        //disableText
        setDisabledText();
        //initData
        initialize();
        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //Parameter Dropdown
        setParameterDropdown();
    }

    private void initialize() {
        binding.etStatusPenjualan.setText("Terjual");
        binding.etCabang.setText(getIntent().getStringExtra("KodeCabang"));
        binding.etNamaNasabah.setText(getIntent().getStringExtra("NamaNasabah"));
        binding.etNomerApplikasi.setText(getIntent().getStringExtra("NoAplikasi"));
    }

    private void setParameterDropdown() {
        dataDropdownPenjualan.add(new MGenericModel("1", "Terjual"));
        dataDropdownPenjualan.add(new MGenericModel("2", "Tidak Terjual"));
    }

    private void setDisabledText() {
        binding.etCabang.setFocusable(false);
        binding.etNamaNasabah.setFocusable(false);
        binding.etNomerApplikasi.setFocusable(false);
        binding.etStatusPenjualan.setFocusable(false);
    }

    private void setClickListener() {
        //Image Click
        binding.btnDokumen.setOnClickListener(this);
        binding.rlDokumen.setOnClickListener(this);
        binding.ivDokumen.setOnClickListener(this);
        //Button Click
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
        //Textediting Click
        binding.etStatusPenjualan.setOnClickListener(this);
        binding.tfStatusPenjualan.setOnClickListener(this);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("HASIL PENJUALAN");
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_dokumen:
            case R.id.rl_dokumen:
            case R.id.iv_dokumen:
                clicker = "agunan";
                BSBottomCamera.display(this.getSupportFragmentManager(), this);
                break;
            case R.id.btn_send:
            case R.id.ll_btn_send:
                SendData();
                break;
            case R.id.et_status_penjualan:
            case R.id.tf_status_penjualan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfStatusPenjualan.getLabelText(), dataDropdownPenjualan, this);
                break;
        }
    }

    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfStatusPenjualan.getLabelText())) {
            binding.etStatusPenjualan.setText(data.getDESC());
        }
    }

    private void endIconClick() {
        binding.tfStatusPenjualan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfStatusPenjualan.getLabelText(), dataDropdownPenjualan, this));
    }

    private void SendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("NoAplikasi", getIntent().getStringExtra("NoAplikasi"));
//        obj1.addProperty("NoAplikasi", "GDE2021092800002");
        obj1.addProperty("kodeCabang", binding.etCabang.getText().toString());
        obj1.addProperty("FotoDokumentPenjualan", AppUtil.encodeImageTobase64(bitmap_dokumen).toString());
        obj1.addProperty("StatusPenjualan", binding.etStatusPenjualan.getText().toString());
        obj1.addProperty("UserSubmit", appPreferences.getKodeAo());
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        call = apiClientAdapter.getApiInterface().UpdatehasilPenjualan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            AppUtil.notifsuccess(DetailHasilPenjualanActivity.this, findViewById(android.R.id.content), "Berhasil Update");
                            Toast.makeText(DetailHasilPenjualanActivity.this, "Berhasil capture", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            AppUtil.notiferror(DetailHasilPenjualanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailHasilPenjualanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DetailHasilPenjualanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
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
                setDataImage(uri_dokumen, bitmap_dokumen, binding.ivDokumen, imageReturnedIntent, "dokumen-penjualan");
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

                bitmap_dokumen = bitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
