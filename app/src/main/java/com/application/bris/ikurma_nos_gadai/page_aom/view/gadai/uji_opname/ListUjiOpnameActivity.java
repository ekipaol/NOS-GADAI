package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
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
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListUjiOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListOpname;
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

public class ListUjiOpnameActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    ActivityListUjiOpnameBinding binding;
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListUjiOpnameAdapter ListUjiOpnameAdapter;

    public static int idAplikasi=0;
    private List<ListOpname> dataAgunan =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding= ActivityListUjiOpnameBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbarReguler.tbRegular);
        setContentView(binding.getRoot());

        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding.tvCabang.setText("Kode Cabang : "+ appPreferences.getNamaKantor());
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initialize();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Tanggal ...");
        searchUjiOpname();
        return true;

    }

    private void searchUjiOpname(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ListUjiOpnameAdapter.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    ListUjiOpnameAdapter.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
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
    
    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("FilterKodeCabang", appPreferences.getKodeKantor());
//        obj1.addProperty("FilterKodeCabang", "ID001211");
        obj1.addProperty("FilterKodeAgunan", "NONE");
        obj1.addProperty("FilterIdRequest", "NONE");
        obj1.addProperty("FilterPengusul", "NONE");
        obj1.addProperty("FilterReviewer", "NONE");
        obj1.addProperty("FilterPemutus", "NONE");
        obj1.addProperty("FilterReasonAksesBrankas", "Uji Stock Opname");
        obj1.addProperty("FilterConfValidasiPengembalian", "NONE");
        obj1.addProperty("FilterStatus", "APROVE");
        obj1.addProperty("TanggalAksesBrankas", "NONE");
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponseGadai> call = apiClientAdapter.getApiInterface().listTanggalOpname(req);
        call.enqueue(new Callback<ParseResponseGadai>() {
            @Override
            public void onResponse(Call<ParseResponseGadai> call, Response<ParseResponseGadai> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListOpname>>() {}.getType();
                            dataAgunan = gson.fromJson(listDataString, type);
                            if (dataAgunan.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                ListUjiOpnameAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListUjiOpnameAdapter(ListUjiOpnameActivity.this,dataAgunan);
                                binding.rvListOpname.setLayoutManager(new LinearLayoutManager(ListUjiOpnameActivity.this));
                                binding.rvListOpname.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListOpname.setAdapter(ListUjiOpnameAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("14")) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }
                        else{
                            AppUtil.notiferror(ListUjiOpnameActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListUjiOpnameActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListUjiOpnameActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initialize(){
        binding.rvListOpname.setVisibility(View.VISIBLE);
        binding.rvListOpname.setHasFixedSize(true);
        ListUjiOpnameAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListUjiOpnameAdapter(this, dataAgunan);
        binding.rvListOpname.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListOpname.setItemAnimator(new DefaultItemAnimator());
        binding.rvListOpname.setAdapter(ListUjiOpnameAdapter);
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        binding.refresh.setEnabled(false);
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar(){
        binding.toolbarReguler.tvPageTitle.setText("List Uji Opname");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

    }
}
