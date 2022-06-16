package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.SummaryPerpanjangan;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.application.bris.ikurma_nos_gadai.api.model.request.dashboardgadai.ReqDashboard;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListPerpanjanganGagalBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.model.gadai.SummaryPerpanjanganGadai;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSummaryPerpanjanganActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, GenericListenerOnSelectRecycler {

    private SummaryPerpanjanganAdapter summaryPerpanjanganAdapter;
    ActivityListPerpanjanganGagalBinding binding;
    View view;

    private List<SummaryPerpanjanganGadai> dataSummaryPerpanjangan = new ArrayList<>();
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
        binding = ActivityListPerpanjanganGagalBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(binding.getRoot());
//        binding.collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        customToolbar();
        backgroundStatusBar();

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this,true);

        if(getIntent().hasExtra("fidArea")) {
            fidArea = getIntent().getStringExtra("fidArea");
        }

//        loadData();

        try {
//            loadData();
//            setData();
//
            setDataDummy();
        } catch (Exception e) {
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
        ReqDashboard req = new ReqDashboard();
        req.setBranch("");
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().listPerpanjanganGadaiGagal(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        binding.rvListPerpanjanganGagal.setVisibility(View.VISIBLE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<SummaryPerpanjanganGadai>>() {}.getType();
                            dataSummaryPerpanjangan = gson.fromJson(listDataString, type);
                            if (dataSummaryPerpanjangan.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                summaryPerpanjanganAdapter = new SummaryPerpanjanganAdapter(ListSummaryPerpanjanganActivity.this, dataSummaryPerpanjangan);
                                binding.rvListPerpanjanganGagal.setLayoutManager(new LinearLayoutManager(ListSummaryPerpanjanganActivity.this));
                                binding.rvListPerpanjanganGagal.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListPerpanjanganGagal.setAdapter(summaryPerpanjanganAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else{
                            AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDataDummy(){
        SummaryPerpanjanganGadai data1=new SummaryPerpanjanganGadai();
        SummaryPerpanjanganGadai data2=new SummaryPerpanjanganGadai();
        SummaryPerpanjanganGadai data3=new SummaryPerpanjanganGadai();

        data1.setBelumDireview("25");
        data1.setBelumOtorisasi("12");
        data1.setBelumPemutusan("30");
        data1.setBelumSimulasi("99");
        data1.setBlokirSaldoRekening("139");
        data1.setReleaseSaldoRekening("448");
        data1.setPelunasan("991");
        data1.setPencairan("11");
        data1.setPengembalianAgunan("6");

        dataSummaryPerpanjangan.add(data1);

        if (dataSummaryPerpanjangan.size() > 0){
            binding.llEmptydata.setVisibility(View.GONE);
            summaryPerpanjanganAdapter = new SummaryPerpanjanganAdapter(ListSummaryPerpanjanganActivity.this, dataSummaryPerpanjangan);
            binding.rvListPerpanjanganGagal.setLayoutManager(new LinearLayoutManager(ListSummaryPerpanjanganActivity.this));
            binding.rvListPerpanjanganGagal.setItemAnimator(new DefaultItemAnimator());
            binding.rvListPerpanjanganGagal.setAdapter(summaryPerpanjanganAdapter);
        }
        else {
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }


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
                            AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ListSummaryPerpanjanganActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public void initialize(){
        binding.rvListPerpanjanganGagal.setVisibility(View.VISIBLE);
        binding.rvListPerpanjanganGagal.setHasFixedSize(true);
        summaryPerpanjanganAdapter = new SummaryPerpanjanganAdapter(ListSummaryPerpanjanganActivity.this, dataSummaryPerpanjangan);
        binding.rvListPerpanjanganGagal.setLayoutManager(new LinearLayoutManager(ListSummaryPerpanjanganActivity.this));
        binding.rvListPerpanjanganGagal.setItemAnimator(new DefaultItemAnimator());
        binding.rvListPerpanjanganGagal.setAdapter(summaryPerpanjanganAdapter);
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        binding.toolbarReguler.etSearchTool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                summaryPerpanjanganAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                summaryPerpanjanganAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                summaryPerpanjanganAdapter.getFilter().filter(s);
            }
        });
    }


    @Override

    public void onClick(View view) {

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
        binding.toolbarReguler.tvPageTitle.setText("Dashboard Summary Perpanjangan");
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
//        binding.rvListPerpanjanganGagal.setVisibility(View.VISIBLE);
//        try {
//            setData();
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        }
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
