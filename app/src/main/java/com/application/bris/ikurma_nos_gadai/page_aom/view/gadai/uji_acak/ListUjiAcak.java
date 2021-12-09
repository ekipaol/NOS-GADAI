package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_acak;

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
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityUjiAcakListBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiAcakBinding;
import com.application.bris.ikurma_nos_gadai.model.login_bsi.DataLoginBranch;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataUjiAcak;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListUjiOpnameActivity;
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

public class ListUjiAcak extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_acak.UjiAcakAdapter ujiAcakAdapter;

    private List<DataUjiAcak> dataUjiAcak = new ArrayList<>();
    ActivityUjiAcakListBinding binding;
    ItemListUjiAcakBinding bindingNamaField;


    private SearchView searchView;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idCabang="0";
    private String kodeCabang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUjiAcakListBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbarReguler.tbRegular);
        //ini binding buat ngambil nama fieldnya aja
        setContentView(binding.getRoot());
        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        kodeCabang=appPreferences.getKodeCabang();
        idCabang=getIntent().getStringExtra("idCabang");

        if(getIntent().hasExtra("kodeCabang")){
            kodeCabang=getIntent().getStringExtra("kodeCabang");
        }

        //initialize status
        initialize();
        onClicks();
        setclickable();

        try {
            if(AppUtil.checkIsReviewer(appPreferences.getFidRole())){
                loadDetailBranch();
            }
            else{
                loadData();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        initialize();
    }

    private void loadData() throws JSONException {
        binding.rvListUjiAcak.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
//        obj1.addProperty("FilterKodeCabang", appPreferences.getKodeKantor());
        obj1.addProperty("FilterKodeCabang", kodeCabang);
        obj1.addProperty("FilterNoAplikasi", "NONE");
        obj1.addProperty("FilterNoKTP", "NONE");
        obj1.addProperty("FilterPengusul", "NONE");
        obj1.addProperty("FilterReviewer", "NONE");
        obj1.addProperty("FilterPemutus", "NONE");
        obj1.addProperty("FilterAOPembiayaan", "NONE");
        obj1.addProperty("FilterWorkFlowStatus", "Sudah Serah Terima Ke RBC");
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
                        binding.rvListUjiAcak.setVisibility(View.VISIBLE);
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataUjiAcak>>() {
                            }.getType();
                            dataUjiAcak = gson.fromJson(listDataString, type);
                            if (dataUjiAcak.size() > 0) {
                                binding.llEmptydata.setVisibility(View.GONE);
                                ujiAcakAdapter = new UjiAcakAdapter(ListUjiAcak.this, dataUjiAcak);
                                binding.rvListUjiAcak.setLayoutManager(new LinearLayoutManager(ListUjiAcak.this));
                                binding.rvListUjiAcak.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListUjiAcak.setAdapter(ujiAcakAdapter);
                            } else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else {
                            AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void loadDetailBranch() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getDetailBranch(idCabang);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataLoginBranch>() {}.getType();
                            DataLoginBranch dataDetailBranch = gson.fromJson(listDataString, type);
                            kodeCabang=dataDetailBranch.getBranch_code();
                            loadData();
                        }
                        else{
                            AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListUjiAcak.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }



    private void onClicks() {
    }

    public void initialize() {
        binding.rvListUjiAcak.setVisibility(View.VISIBLE);
        binding.rvListUjiAcak.setHasFixedSize(true);
        ujiAcakAdapter = new UjiAcakAdapter(ListUjiAcak.this, dataUjiAcak);
        binding.rvListUjiAcak.setLayoutManager(new LinearLayoutManager(ListUjiAcak.this));
        binding.rvListUjiAcak.setItemAnimator(new DefaultItemAnimator());
        binding.rvListUjiAcak.setAdapter(ujiAcakAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

    }


    public void customToolbar() {
        binding.toolbarReguler.tvPageTitle.setText("List Uji Acak");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Nomor Aplikasi, dll ..");
        searchAgunan();
        return true;

    }

    private void searchAgunan(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ujiAcakAdapter.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    ujiAcakAdapter.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setclickable(){

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
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvListUjiAcak.setVisibility(View.VISIBLE);
        try {
            loadData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refreshData() throws JSONException {
        loadData();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        ListUjiAcak.this.recreate();
    }
}
