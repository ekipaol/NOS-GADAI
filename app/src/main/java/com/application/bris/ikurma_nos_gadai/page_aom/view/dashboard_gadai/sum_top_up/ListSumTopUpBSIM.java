package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_top_up;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

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
import com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai.ReqTopUpDashboard;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListTopUpBsimBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.model.gadai.SumTopUpGadai;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelectRecycler;
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

public class ListSumTopUpBSIM extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, GenericListenerOnSelectRecycler {

    private SumTopUpAdapter sumTopUpAdapter;
    ActivityListTopUpBsimBinding binding;
    View view;

    private DatePickerDialog dpEndDate;
    private DatePickerDialog dpStartDate;
    private Calendar calStartDate;
    private Calendar calEndDate;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List<SumTopUpGadai> dataDashboardTopUpGagal = new ArrayList<>();
    private List <DataCabang> dataCabang= new ArrayList<>() ;
    private List<String> listCabang = new ArrayList<>();
    public static int idAplikasi = 0;
    private String fidArea="1";
    private String branchCode = " ";
    private ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    private AppPreferences appPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = ActivityListTopUpBsimBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this,true);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        setContentView(binding.getRoot());
        customToolbar();
        backgroundStatusBar();
        onClickSelectDialog();

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this,true);

        if(getIntent().hasExtra("fidArea")) {
            fidArea = getIntent().getStringExtra("fidArea");
        }

        loadData();
        initialize();
        return;
    }


    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);
        ReqTopUpDashboard req = new ReqTopUpDashboard();
        req.setStartDate(binding.etStartDate.getText().toString());
        req.setEndDate(binding.etEndDate.getText().toString());
        req.setSumSelindo(false);
        req.setListBranch(listCabang);
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().sumTopUpGadai(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        binding.rvSumTopUpCair.setVisibility(View.VISIBLE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<SumTopUpGadai>>() {}.getType();
                            dataDashboardTopUpGagal = gson.fromJson(listDataString, type);
                            if (dataDashboardTopUpGagal.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                sumTopUpAdapter = new SumTopUpAdapter(ListSumTopUpBSIM.this,dataDashboardTopUpGagal);
                                binding.rvSumTopUpCair.setLayoutManager(new LinearLayoutManager(ListSumTopUpBSIM.this));
                                binding.rvSumTopUpCair.setItemAnimator(new DefaultItemAnimator());
                                binding.rvSumTopUpCair.setAdapter(sumTopUpAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else{
                            AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void dpStartDate(EditText edit){
        calStartDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ds_star_date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calStartDate.set(Calendar.YEAR, year);
                calStartDate.set(Calendar.MONTH, month);
                calStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = ListSumTopUpBSIM.dateClient.format(calStartDate.getTime());
                binding.etStartDate.setText(calLahirString);
            }
        };

        dpStartDate = new DatePickerDialog(ListSumTopUpBSIM.this,R.style.AppTheme_TimePickerTheme, ds_star_date, calStartDate.get(Calendar.YEAR),
                calStartDate.get(Calendar.MONTH), calStartDate.get(Calendar.DAY_OF_MONTH));
        dpStartDate.show();
    }

    private void dpEndDate(EditText edit){
        calEndDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ds_end_date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calEndDate.set(Calendar.YEAR, year);
                calEndDate.set(Calendar.MONTH, month);
                calEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calEndDate.getTime());
                binding.etEndDate.setText(calLahirString);
            }
        };

        dpEndDate = new DatePickerDialog(ListSumTopUpBSIM.this,R.style.AppTheme_TimePickerTheme, ds_end_date, calEndDate.get(Calendar.YEAR),
                calEndDate.get(Calendar.MONTH), calEndDate.get(Calendar.DAY_OF_MONTH));
        dpEndDate.show();
    }


    private void loadData() {
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
                            AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public void initialize(){
        binding.rvSumTopUpCair.setVisibility(View.VISIBLE);
        binding.rvSumTopUpCair.setHasFixedSize(true);
        sumTopUpAdapter = new SumTopUpAdapter(ListSumTopUpBSIM.this, dataDashboardTopUpGagal);
        binding.rvSumTopUpCair.setLayoutManager(new LinearLayoutManager(ListSumTopUpBSIM.this));
        binding.rvSumTopUpCair.setItemAnimator(new DefaultItemAnimator());
        binding.rvSumTopUpCair.setAdapter(sumTopUpAdapter);
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
//        binding.toolbarReguler.etSearchTool.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                topUpDashboardGagalAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                topUpDashboardGagalAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                topUpDashboardGagalAdapter.getFilter().filter(s);
//            }
//        });

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
        binding.toolbarReguler.tvPageTitle.setText("Summary Top Up BSIM");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void onClickSelectDialog(){
        binding.btnCari.setOnClickListener(this);
        binding.tfStartDate.setOnClickListener(this);
        binding.etStartDate.setOnClickListener(this);
        binding.tfStartDate.getEndIconImageButton().setOnClickListener(v -> dpStartDate(binding.etStartDate));
        binding.tfEndDate.setOnClickListener(this);
        binding.etEndDate.setOnClickListener(this);
        binding.tfEndDate.getEndIconImageButton().setOnClickListener(v -> dpEndDate(binding.etStartDate));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_start_date:
            case R.id.tf_start_date:
                dpStartDate(binding.etStartDate);
                break;
            case R.id.et_end_date:
            case R.id.tf_end_date:
                dpEndDate(binding.etEndDate);
                break;
            case R.id.btn_cari:
                try {
                    setData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                validasi();
                break;
        }
    }

    private boolean validasi() {
        if (binding.etStartDate.getText().toString().trim().isEmpty() || binding.etStartDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfStartDate.setError(binding.tfStartDate.getLabelText() + " " + "is required", true);
            AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), binding.tfStartDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etEndDate.getText().toString().trim().isEmpty() || binding.etEndDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfEndDate.setError(binding.tfEndDate.getLabelText() + " " + "is required", true);
            AppUtil.notiferror(ListSumTopUpBSIM.this, findViewById(android.R.id.content), binding.tfEndDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;
        }
        else {
            return false;
        }
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvSumTopUpCair.setVisibility(View.VISIBLE);
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadData();
    }


    public void refreshData() throws JSONException {
        setData();
    }

    private void setclickable() {

    }

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onSelect(String title, MGenericModel data, int position) {

    }
}
