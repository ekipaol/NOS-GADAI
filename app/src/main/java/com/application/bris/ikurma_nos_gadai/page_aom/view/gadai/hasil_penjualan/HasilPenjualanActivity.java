package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.Error;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListHasilPenjualanBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.CaptureAgunan;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilPenjualanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    ActivityListHasilPenjualanBinding binding;
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan.HasilPenjualanAdapter listAgunanAdapter;

    public static int idAplikasi = 0;
    private List<CaptureAgunan> dataAgunan = new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private SearchView searchView;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding = ActivityListHasilPenjualanBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbarReguler.tbRegular);
        setContentView(binding.getRoot());
        //Button Click
        setclickable();

        //Navbar
        customToolbar();

        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initialize();

    }

    private void setData() throws JSONException {
        binding.rvListPenjualan.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences = new AppPreferences(this);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("FilterKodeCabang", appPreferences.getKodeKantor());
//        obj1.addProperty("FilterKodeCabang", "NONE");
        obj1.addProperty("FilterNoAplikasi", "NONE");
        obj1.addProperty("FilterNoKTP", "NONE");
        obj1.addProperty("FilterPengusul", "NONE");
        obj1.addProperty("FilterReviewer", "NONE");
        obj1.addProperty("FilterPemutus", "NONE");
        obj1.addProperty("FilterAOPembiayaan", "NONE");
        obj1.addProperty("FilterWorkFlowStatus", "Dalam Proses Penjualan");
//        obj1.addProperty("FilterWorkFlowStatus", "LOLOS IDE");
        obj1.addProperty("FilterNoCif", "NONE");
        obj1.addProperty("FilterSBGE", "NONE");
        obj1.addProperty("FilterKodeAgunan", "NONE");
        obj1.addProperty("FilterLDNumber", "NONE");
        obj1.addProperty("FilterUjiKwalitasKapan", "NONE");
        obj1.addProperty("FilterTanggalPencairan", "NONE");
        obj1.addProperty("FilterTanggalJatuhTempo", "NONE");
        obj1.addProperty("FilterHasilIDE", "NONE");
        obj1.addProperty("FilterSlotPenempatan", "NONE");
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().sendDataListApplikasi(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        binding.rvListPenjualan.setVisibility(View.VISIBLE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<CaptureAgunan>>() {
                            }.getType();
                            dataAgunan = gson.fromJson(listDataString, type);
                            if (dataAgunan.size() > 0) {
                                binding.llEmptydata.setVisibility(View.GONE);
                                listAgunanAdapter = new HasilPenjualanAdapter(HasilPenjualanActivity.this, dataAgunan);
                                binding.rvListPenjualan.setLayoutManager(new LinearLayoutManager(HasilPenjualanActivity.this));
                                binding.rvListPenjualan.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListPenjualan.setAdapter(listAgunanAdapter);
                            } else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        } else {
                            AppUtil.notiferror(HasilPenjualanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(HasilPenjualanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(HasilPenjualanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initialize() {
        binding.rvListPenjualan.setVisibility(View.VISIBLE);
        binding.rvListPenjualan.setHasFixedSize(true);
        listAgunanAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan.HasilPenjualanAdapter(HasilPenjualanActivity.this, dataAgunan);
        binding.rvListPenjualan.setLayoutManager(new LinearLayoutManager(HasilPenjualanActivity.this));
        binding.rvListPenjualan.setItemAnimator(new DefaultItemAnimator());
        binding.rvListPenjualan.setAdapter(listAgunanAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Nomor SBGE, dll ..");
        searchAgunan();
        return true;

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar() {
        binding.toolbarReguler.tvPageTitle.setText("List Hasil Penjualan");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setclickable() {

    }

    private void searchAgunan() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    listAgunanAdapter.getFilter().filter(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    listAgunanAdapter.getFilter().filter(query);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvListPenjualan.setVisibility(View.VISIBLE);
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refreshData() throws JSONException {
        setData();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        HasilPenjualanActivity.this.recreate();
    }

}
