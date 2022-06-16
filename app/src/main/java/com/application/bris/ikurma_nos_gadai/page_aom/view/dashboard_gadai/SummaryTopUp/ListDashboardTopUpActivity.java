package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.SummaryTopUp;

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
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListTopUpDashboardBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DashboardTopUpGadai;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
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

public class ListDashboardTopUpActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, GenericListenerOnSelectRecycler {

    private TopUpDashboardGagalAdapter topUpDashboardGagalAdapter;
    ActivityListTopUpDashboardBinding binding;
    View view;

    private DatePickerDialog dpEndDate;
    private DatePickerDialog dpStartDate;
    private Calendar calStartDate;
    private Calendar calEndDate;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List<DashboardTopUpGadai> dataDashboardTopUpGagal = new ArrayList<>();
    private DataCabang dataCabang;
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
        binding = ActivityListTopUpDashboardBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(binding.getRoot());
//        binding.collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        customToolbar();
        backgroundStatusBar();
        onClickSelectDialog();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this,true);

        if(getIntent().hasExtra("fidArea")) {
            fidArea = getIntent().getStringExtra("fidArea");
        }

//        loadData();

        try {
            loadData();
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initialize();
        return;
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

    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);
        ReqTopUpDashboard req = new ReqTopUpDashboard();
//        req.setStartDate(binding.etStartDate.getText().toString().trim());
//        req.setEndDate(binding.etEndDate.getText().toString().trim());
        req.setStartDate("");
        req.setEndDate("");
        req.setListBranch("");
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().listDashboardTopUpGadai(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        binding.rvListTopUpDashboard.setVisibility(View.VISIBLE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DashboardTopUpGadai>>() {}.getType();
                            dataDashboardTopUpGagal = gson.fromJson(listDataString, type);
                            if (dataDashboardTopUpGagal.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                topUpDashboardGagalAdapter = new TopUpDashboardGagalAdapter(ListDashboardTopUpActivity.this,dataDashboardTopUpGagal);
                                binding.rvListTopUpDashboard.setLayoutManager(new LinearLayoutManager(ListDashboardTopUpActivity.this));
                                binding.rvListTopUpDashboard.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListTopUpDashboard.setAdapter(topUpDashboardGagalAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else{
                            AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
                String calLahirString = ListDashboardTopUpActivity.dateClient.format(calStartDate.getTime());
                binding.etStartDate.setText(calLahirString);
            }
        };

        dpStartDate = new DatePickerDialog(ListDashboardTopUpActivity.this,R.style.AppTheme_TimePickerTheme, ds_star_date, calStartDate.get(Calendar.YEAR),
                calStartDate.get(Calendar.MONTH), calStartDate.get(Calendar.DAY_OF_MONTH));
        dpStartDate.getDatePicker().setMaxDate(calStartDate.getTimeInMillis());
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

        dpEndDate = new DatePickerDialog(ListDashboardTopUpActivity.this,R.style.AppTheme_TimePickerTheme, ds_end_date, calEndDate.get(Calendar.YEAR),
                calEndDate.get(Calendar.MONTH), calEndDate.get(Calendar.DAY_OF_MONTH));
        dpEndDate.getDatePicker().setMaxDate(calEndDate.getTimeInMillis());
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

                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                        }
                        else{
                            AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public void initialize(){
        binding.rvListTopUpDashboard.setVisibility(View.VISIBLE);
        binding.rvListTopUpDashboard.setHasFixedSize(true);
        topUpDashboardGagalAdapter = new TopUpDashboardGagalAdapter(ListDashboardTopUpActivity.this, dataDashboardTopUpGagal);
        binding.rvListTopUpDashboard.setLayoutManager(new LinearLayoutManager(ListDashboardTopUpActivity.this));
        binding.rvListTopUpDashboard.setItemAnimator(new DefaultItemAnimator());
        binding.rvListTopUpDashboard.setAdapter(topUpDashboardGagalAdapter);
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

    private void onClickSelectDialog(){
        binding.tfStartDate.setOnClickListener(this);
        binding.etStartDate.setOnClickListener(this);
        binding.tfStartDate.getEndIconImageButton().setOnClickListener(v -> dpStartDate(binding.etStartDate));
        binding.tfEndDate.setOnClickListener(this);
        binding.etEndDate.setOnClickListener(this);
        binding.tfEndDate.getEndIconImageButton().setOnClickListener(v -> dpEndDate(binding.etStartDate));
        binding.ivCari.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_start_date:
            case R.id.tf_start_date:
                dpStartDate(binding.etStartDate);
            case R.id.et_end_date:
            case R.id.tf_end_date:
                dpEndDate(binding.etEndDate);
            case R.id.iv_cari:
                searchSerahTerima();
                validateSearch();


        }
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
        binding.toolbarReguler.tvPageTitle.setText("Top UP Gagal");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void searchSerahTerima(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    private boolean validateSearch() {
        if (binding.etStartDate.getText().toString().trim().isEmpty() || binding.etStartDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfStartDate.setError(binding.tfStartDate.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), binding.tfStartDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etEndDate.getText().toString().trim().isEmpty() || binding.etEndDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfStartDate.setError(binding.tfEndDate.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ListDashboardTopUpActivity.this, findViewById(android.R.id.content), binding.tfStartDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;
        }
        return false;
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvListTopUpDashboard.setVisibility(View.VISIBLE);
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

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onSelect(String title, MGenericModel data, int position) {

    }
}
