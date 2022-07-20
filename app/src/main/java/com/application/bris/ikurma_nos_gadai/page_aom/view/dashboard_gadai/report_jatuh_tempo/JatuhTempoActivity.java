package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.report_jatuh_tempo;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai.ReqDashboard;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListReportPastdueBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.model.gadai.ListPastDue;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JatuhTempoActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    private JatuhTempoAdapter jatuhTempoAdapter;
    ActivityListReportPastdueBinding binding;
    View view;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List <ListPastDue> dataListPastdue = new ArrayList<>();
    private List <DataCabang> dataCabang= new ArrayList<>() ;
    private List <String> listCabang = new ArrayList<>();
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
        binding = ActivityListReportPastdueBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this,true);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        setContentView(binding.getRoot());
        customToolbar();
        backgroundStatusBar();

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
                            AppUtil.notiferror(JatuhTempoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(JatuhTempoActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(JatuhTempoActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    private void setData()throws JSONException{
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences=new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this);
        ReqDashboard req = new ReqDashboard();
        req.setListBranch(listCabang);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listReportJatuhTempo(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListJatuhTempo.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    String listDataString;
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        try {
                            listDataString = response.body().getData().toString();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            listDataString = "[]";
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<ListPastDue>>() {
                        }.getType();
                        dataListPastdue = gson.fromJson(listDataString, type);

                        jatuhTempoAdapter = new JatuhTempoAdapter(JatuhTempoActivity.this, dataListPastdue);
                        binding.rvListJatuhTempo.setLayoutManager(new LinearLayoutManager(JatuhTempoActivity.this));
                        binding.rvListJatuhTempo.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListJatuhTempo.setAdapter(jatuhTempoAdapter);

                    if (dataListPastdue.size() == 0) {
                        binding.llEmptydata.setVisibility(View.VISIBLE);

                    } else {
                        binding.llEmptydata.setVisibility(View.GONE);

                    }
                } else if (response.body().getStatus().equalsIgnoreCase("14")) {
                    binding.llEmptydata.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onFailure(Call<ParseResponseArr> call, Throwable t) {
            binding.loading.progressbarLoading.setVisibility(View.GONE);
            AppUtil.notiferror(JatuhTempoActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            Log.d("LOG D", t.getMessage());
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
        binding.rvListJatuhTempo.setVisibility(View.VISIBLE);
        binding.rvListJatuhTempo.setHasFixedSize(true);
        jatuhTempoAdapter = new JatuhTempoAdapter(JatuhTempoActivity.this, dataListPastdue);
        binding.rvListJatuhTempo.setLayoutManager(new LinearLayoutManager(JatuhTempoActivity.this));
        binding.rvListJatuhTempo.setItemAnimator(new DefaultItemAnimator());
        binding.rvListJatuhTempo.setAdapter(jatuhTempoAdapter);
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        binding.toolbarReguler.etSearchTool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                jatuhTempoAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                jatuhTempoAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                jatuhTempoAdapter.getFilter().filter(s);
            }
        });
    }


    @Override

    public void onClick(View v) {

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
        binding.toolbarReguler.tvPageTitle.setText("Laporan Akan Jatuh Tempo H+7");
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
        binding.rvListJatuhTempo.setVisibility(View.VISIBLE);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
