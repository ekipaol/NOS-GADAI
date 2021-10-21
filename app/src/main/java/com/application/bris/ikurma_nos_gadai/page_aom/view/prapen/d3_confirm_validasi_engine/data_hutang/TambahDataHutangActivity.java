package com.application.bris.ikurma_nos_gadai.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.PrapenAoTambahDataHutangActivityBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.application.bris.ikurma_nos_gadai.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

public class TambahDataHutangActivity extends AppCompatActivity implements  View.OnClickListener, GenericListenerOnSelect {

    private PrapenAoTambahDataHutangActivityBinding binding;
    private List<MGenericModel> dataDropdownTreatment=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoTambahDataHutangActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Tambah Kewajibah Lainnya");

        allOnClicks();
        disableEditTexts();
        isiDropdown();
        allOnChange();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(TambahDataHutangActivity.this);
            }
        });
    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(TambahDataHutangActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etTreatmentPembiayaan.setOnClickListener(this);
        binding.tfTreatmentPembiayaan.setOnClickListener(this);
        binding.btnTambahDataHutang.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //MENGGUNAKAN LNGP
            case R.id.et_sumber_aplikasi:
            case R.id.tf_sumber_aplikasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(),dataDropdownTreatment,TambahDataHutangActivity.this);
                break;
            case R.id.btn_tambah_data_hutang:
                Toast.makeText(this, "Nit Not, menyimpan data hutang", Toast.LENGTH_SHORT).show();

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfTreatmentPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(),dataDropdownTreatment,TambahDataHutangActivity.this);
            }
        });

    }

    private void disableEditTexts(){
        binding.etTreatmentPembiayaan.setFocusable(false);

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfTreatmentPembiayaan.getLabelText())){
            binding.etTreatmentPembiayaan.setText(data.getDESC());
        }

    }

    private void isiDropdown(){
        dataDropdownTreatment.add(new MGenericModel("1","Pembiayaan tetap dilanjutkan"));
        dataDropdownTreatment.add(new MGenericModel("2","Pembiayaan akan dilunasi melalui pencairan"));
        dataDropdownTreatment.add(new MGenericModel("3","Pembiayaan dilakukan takeover"));

    }

    private void allOnChange(){
        binding.etNominalPinjaman.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalPinjaman));
        binding.etAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etAngsuranBulanan));
    }
}