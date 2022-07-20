package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_pencairan;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai.ReqTopUpDashboard;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListSumPencairanBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.model.gadai.SumPencairanGadai;
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

public class SumPencairanActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, GenericListenerOnSelectRecycler {

    SumPencairanAdapter sumPencairanAdapter;
    ActivityListSumPencairanBinding binding;
    View view;

    private DatePickerDialog dpEndDate;
    private DatePickerDialog dpStartDate;
    private Calendar calStartDate;
    private Calendar calEndDate;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List <SumPencairanGadai> dataSumPencairan= new ArrayList<>() ;
    private List <DataCabang> dataCabang= new ArrayList<>();
    private List <String> listCabang = new ArrayList<>();
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
        binding = ActivityListSumPencairanBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this,true);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        setContentView(binding.getRoot());
        binding.etEndDate.setFocusable(false);
        binding.etStartDate.setFocusable(false);
        customToolbar();
        backgroundStatusBar();
        onClickSelectDialog();

        if(getIntent().hasExtra("fidArea")) {
            fidArea = getIntent().getStringExtra("fidArea");
        }
//        try {
//            setData();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        loadData();
        initialize();
        return;
    }

//
//    private void setData() throws JSONException {
//        SumTopUpGadai dd = new SumTopUpGadai();
//        dd.setJumlahCIF (Long.valueOf("121312"));
//        dd.setJumlahLoan (Long.valueOf("543212"));
//        dd.setTotalOutstanding(Long.valueOf("123312000000"));
//
//        SumTopUpGadai dd2 = new SumTopUpGadai();
//        dd2.setJumlahCIF (Long.valueOf("121312"));
//        dd2.setJumlahLoan (Long.valueOf("543212"));
//        dd2.setTotalOutstanding(Long.valueOf("123312000000"));
//
//        dataSumPencairan.add(dd);
//        dataSumPencairan.add(dd2);
//    }

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
                            AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);
        ReqTopUpDashboard req = new ReqTopUpDashboard();
        req.setStartDate(AppUtil.parseTanggalGeneral(binding.etStartDate.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        req.setEndDate(AppUtil.parseTanggalGeneral(binding.etEndDate.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        req.setSumSelindo(false);
        req.setListBranch(listCabang);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sumPencairanGadai(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvSumPencairan.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    String listDataString;
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        try {
                            listDataString = "["+response.body().getData().toString()+"]" ;
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            listDataString = "[]";
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<SumPencairanGadai>>() {
                        }.getType();
                        dataSumPencairan = gson.fromJson(listDataString, type);

                        sumPencairanAdapter = new SumPencairanAdapter(SumPencairanActivity.this, dataSumPencairan);
                        binding.rvSumPencairan.setLayoutManager(new LinearLayoutManager(SumPencairanActivity.this));
                        binding.rvSumPencairan.setItemAnimator(new DefaultItemAnimator());
                        binding.rvSumPencairan.setAdapter(sumPencairanAdapter);

                        if (dataSumPencairan.size() == 0) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        } else {
                            binding.llEmptydata.setVisibility(View.GONE);
                        }
                    } else if (response.body().getStatus().equalsIgnoreCase("14")) {
                        AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                        binding.rvSumPencairan.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());

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
                String calLahirString = SumPencairanActivity.dateClient.format(calStartDate.getTime());
                binding.etStartDate.setText(calLahirString);
            }
        };

        dpStartDate = new DatePickerDialog(SumPencairanActivity.this,R.style.AppTheme_TimePickerTheme, ds_star_date, calStartDate.get(Calendar.YEAR),
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

        dpEndDate = new DatePickerDialog(SumPencairanActivity.this,R.style.AppTheme_TimePickerTheme, ds_end_date, calEndDate.get(Calendar.YEAR),
                calEndDate.get(Calendar.MONTH), calEndDate.get(Calendar.DAY_OF_MONTH));
        dpEndDate.show();
    }

    public void initialize(){
        binding.rvSumPencairan.setVisibility(View.VISIBLE);
        binding.rvSumPencairan.setHasFixedSize(true);
        sumPencairanAdapter = new SumPencairanAdapter(SumPencairanActivity.this, dataSumPencairan);
        binding.rvSumPencairan.setLayoutManager(new LinearLayoutManager(SumPencairanActivity.this));
        binding.rvSumPencairan.setItemAnimator(new DefaultItemAnimator());
        binding.rvSumPencairan.setAdapter(sumPencairanAdapter);
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
        binding.toolbarReguler.tvSecondTitle.setVisibility(View.VISIBLE);
        binding.toolbarReguler.tvSecondTitle.setText("Dashboard");
        binding.toolbarReguler.tvPageTitle.setText("Summary Pencairan");
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
        return;
    }

    private boolean validasi() {
        if (binding.etStartDate.getText().toString().trim().isEmpty() || binding.etStartDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfStartDate.setError(binding.tfStartDate.getLabelText() + " " + "is required", true);
            AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), binding.tfStartDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etEndDate.getText().toString().trim().isEmpty() || binding.etEndDate.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfEndDate.setError(binding.tfEndDate.getLabelText() + " " + "is required", true);
            AppUtil.notiferror(SumPencairanActivity.this, findViewById(android.R.id.content), binding.tfEndDate.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;
        }
        else {
            return false;
        }
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvSumPencairan.setVisibility(View.VISIBLE);
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

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onSelect(String title, MGenericModel data, int position) {

    }
}
