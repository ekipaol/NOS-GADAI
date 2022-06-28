package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_program_gadai;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.Error;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai.ReqSumProgram;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListSumProgramBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.model.gadai.SumProgramGadai;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSumProgramGadai extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    private SumProgramGadaiAdapter sumProgramGadaiAdapter;
    ActivityListSumProgramBinding binding;
    View view;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List<SumProgramGadai> dataSumProgram = new ArrayList<>();
    private List <DataCabang> dataCabang= new ArrayList<>() ;
    private List<String> listCabang = new ArrayList<>();
    public static int idAplikasi = 0;
    private String fidArea="1";
    private ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    private AppPreferences appPreferences;
    private List<MGenericModel> dropdownTahun = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = ActivityListSumProgramBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this,true);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        setContentView(binding.getRoot());
        customToolbar();
        backgroundStatusBar();
        isiDropdown();
        allEndOnClick();
        allOnClick();

        if (getIntent().hasExtra("fidArea")) {
            fidArea = getIntent().getStringExtra("fidArea");
        }
        loadData();
        initialize();
        return;

    }


    private void loadData(){
        apiClientAdapter = new ApiClientAdapter(this,true);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getBranchByKodeArea(fidArea,getString(R.string.limit_page_branch_gadai));
        AppUtil.logSecure("lontong",fidArea);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){

                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("content").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataCabang>>() {}.getType();
                            dataCabang = gson.fromJson(listDataString, type);{
                            }
                            for (int i = 0; i < dataCabang.size(); i++) {
                                listCabang.add(dataCabang.get(i).getKodeCabang());
                            }
                            setData();
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                        }
                        else{
                            AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    private void setData()throws JSONException{
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);
        ReqSumProgram req = new ReqSumProgram();
        req.setListCabang(listCabang);
        req.setTahun(binding.etPilihTahun.getText().toString());
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().sumProgramGadai(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        binding.rvSumProgramGadai.setVisibility(View.VISIBLE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<SumProgramGadai>>() {}.getType();
                            dataSumProgram = gson.fromJson(listDataString, type);
                            if (dataSumProgram.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                sumProgramGadaiAdapter = new SumProgramGadaiAdapter(ListSumProgramGadai.this,dataSumProgram);
                                binding.rvSumProgramGadai.setLayoutManager(new LinearLayoutManager(ListSumProgramGadai.this));
                                binding.rvSumProgramGadai.setItemAnimator(new DefaultItemAnimator());
                                binding.rvSumProgramGadai.setAdapter(sumProgramGadaiAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else{
                            AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

//    private void setData() throws JSONException {
//        CaptureAgunan dd = new CaptureAgunan();
//        dd.setCabang ("KCP SUNTER");
//        dd.setNomorAplikasiGadai ("1430000000000");
//        dd.setNomorAplikasiGadai("123000000000");
//        dd.setTanggalTransaksi("20000000000000000");
//
//        CaptureAgunan dd2 = new CaptureAgunan();
//        dd2.setCabang ("KCP SUNTER");
//        dd2.setNomorAplikasiGadai ("123000000000");
//        dd2.setNomorAplikasiGadai("1430000000000");
//        dd2.setTanggalTransaksi("20000000000000000");
//
//        dataAgunan.add(dd);
//        dataAgunan.add(dd2);
//    }

    public void initialize(){
        binding.rvSumProgramGadai.setVisibility(View.VISIBLE);
        binding.rvSumProgramGadai.setHasFixedSize(true);
        sumProgramGadaiAdapter = new SumProgramGadaiAdapter(ListSumProgramGadai.this, dataSumProgram);
        binding.rvSumProgramGadai.setLayoutManager(new LinearLayoutManager(ListSumProgramGadai.this));
        binding.rvSumProgramGadai.setItemAnimator(new DefaultItemAnimator());
        binding.rvSumProgramGadai.setAdapter(sumProgramGadaiAdapter);
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
//        binding.toolbarReguler.etSearchTool.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                perpanjanganGagalAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                perpanjanganGagalAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                perpanjanganGagalAdapter.getFilter().filter(s);
//            }
//        });
    }


    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cari:
                try {
                    setData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                validasi();
                break;
            case R.id.tf_pilih_tahun:
            case R.id.et_pilih_tahun:
                DialogGenericDataFromService.display(getSupportFragmentManager(), "tahun", dropdownTahun, ListSumProgramGadai.this);


        }
    }

    private boolean validasi() {
        if (binding.etPilihTahun.getText().toString().trim().isEmpty() || binding.etPilihTahun.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPilihTahun.setError(binding.tfPilihTahun.getLabelText() + " " + "is required", true);
            AppUtil.notiferror(ListSumProgramGadai.this, findViewById(android.R.id.content), binding.tfPilihTahun.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        return false;
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    public void customToolbar() {
        binding.toolbarReguler.tvPageTitle.setText("Laporan Summary Program Gadai");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvSumProgramGadai.setVisibility(View.VISIBLE);
        loadData();
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void refreshData() throws JSONException {
        setData();
    }

    private void setclickable() {

    }

    private void allOnClick(){
        binding.tfPilihTahun.setOnClickListener(this);
        binding.etPilihTahun.setOnClickListener(this);
        binding.btnCari.setOnClickListener(this);
    }

    private void allEndOnClick(){
        binding.tfPilihTahun.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), "tahun", dropdownTahun, ListSumProgramGadai.this);
            }
        });
    }

    private void isiDropdown(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int baseYear = 2020;
        for (int i = baseYear; i <= year; i++) {
            dropdownTahun.add(new MGenericModel(Integer.toString(i),Integer.toString(i)));
        }
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase("tahun")){
            binding.etPilihTahun.setText(data.getDESC());
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
