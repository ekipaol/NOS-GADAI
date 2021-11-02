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
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListIsiLaciOpnameBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListIsiLaciOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListIsiLaci;
import com.application.bris.ikurma_nos_gadai.page_aom.model.MGenericModel;
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

public class ListIsiLaciActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {
    ActivityListIsiLaciOpnameBinding binding;
    ItemListIsiLaciOpnameBinding bindingIsi;

    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListIsiLaciAdapter ListIsiLaciAdapter;

    public static int idAplikasi=0;
    private List<ListIsiLaci> isilaci =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private SearchView searchView;
    List<MGenericModel> dataLaci = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding= ActivityListIsiLaciOpnameBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbarNosearch.tbRegular);
        setContentView(binding.getRoot());
        //Button Click
        setclickable();

        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        setDropdownData();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        try {
//            setData();
            initData();
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
        searchView.setQueryHint("Nama , Nomor LD, Nomor Applikasi ...");
        searchUjiOpname();
        return true;

    }

    private void searchUjiOpname(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ListIsiLaciAdapter.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    ListIsiLaciAdapter.getFilter().filter(query);
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

    private void initData()throws JSONException{
        ListIsiLaci dd = new ListIsiLaci();
        dd.setAgunanPembiayaan("Gadai Emas");
        dd.setidBrankas("1");
        dd.setidSlot("0101002");
        dd.setStatusOpname("Ada");
        dd.setidCabangPemilik("ID001211");
        dd.setNamaPemilik("ROSALYN FEBRIYANI");
        dd.setWorkFlowStatus("Gagal Lolos IDE");
        dd.setsizeSlot("Medium");
        dd.setidLaci("1");
        dd.setNoAplikasi("GDE2021102100023");
        dd.setLDNumber("LD1809553946");
        dd.setTanggalPencairan("28 Agustus 2021");
        dd.setTanggalJatuhTempo("28 Desember 2021");

        ListIsiLaci dd2 = new ListIsiLaci();
        dd2.setAgunanPembiayaan("Gadai Emas");
        dd2.setidBrankas("1");
        dd2.setidSlot("0101001");
        dd2.setStatusOpname("Ada");
        dd2.setidCabangPemilik("ID001211");
        dd2.setNamaPemilik("ROSALYN FEBRIYANI");
        dd2.setWorkFlowStatus("Gagal Lolos IDE");
        dd2.setsizeSlot("Medium");
        dd2.setidLaci("2");
        dd2.setNoAplikasi("GDE2021102100022");
        dd2.setLDNumber("LD1809553947");
        dd2.setTanggalPencairan("28 Agustus 2021");
        dd2.setTanggalJatuhTempo("28 Desember 2021");

        isilaci.add(dd);
        isilaci.add(dd2);
    }

    private void setDropdownData() {
        dataLaci.add(new MGenericModel("1", "Ada"));
        dataLaci.add(new MGenericModel("2", "Tidak Ada"));
    }

    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("KodeCabang", "ID001211");
        obj1.addProperty("ReffNoAktifitas", getIntent().getStringExtra("ReffNoAktifitas"));
        obj1.addProperty("seqBrankas", getIntent().getStringExtra("seqBrankas"));
        obj1.addProperty("seqLaci", String.valueOf(getIntent().getIntExtra("isilaci",0)));
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listIsiLaci(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("isiBrankas").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListIsiLaci>>() {}.getType();
                            isilaci = gson.fromJson(listDataString, type);
                            if (isilaci.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                ListIsiLaciAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListIsiLaciAdapter(ListIsiLaciActivity.this ,isilaci,ListIsiLaciActivity.this);
                                binding.rvListIsiLaci.setLayoutManager(new LinearLayoutManager(ListIsiLaciActivity.this));
                                binding.rvListIsiLaci.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListIsiLaci.setAdapter(ListIsiLaciAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void initialize(){
        binding.rvListIsiLaci.setVisibility(View.VISIBLE);
        binding.rvListIsiLaci.setHasFixedSize(true);
        ListIsiLaciAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListIsiLaciAdapter(this, isilaci,this);
        binding.rvListIsiLaci.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListIsiLaci.setItemAnimator(new DefaultItemAnimator());
        binding.rvListIsiLaci.setAdapter(ListIsiLaciAdapter);
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
        binding.toolbarNosearch.tvPageTitle.setText("Opname Laci " + String.valueOf(getIntent().getIntExtra("isilaci",0)) + " Brankas "+getIntent().getStringExtra("seqBrankas"));
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setclickable(){

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onSelect(String title, MGenericModel data, int position) {
        if (title.equalsIgnoreCase("Stok Opname")) {
            isilaci.get(position).setStatusOpname(data.getDESC());
            ListIsiLaciAdapter.notifyDataSetChanged();
            UpdateData(position,data.getDESC());
        }
    }


    private void UpdateData(int position,String desc){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("UserSubmit", "110145101");
        obj1.addProperty("ReffNoAktifitas", getIntent().getStringExtra("ReffNoAktifitas"));
        obj1.addProperty("DescriptionAktifitas", "Opname "+ isilaci.get(position).getTanggalPencairan());
        obj1.addProperty("Action", "UPDATE");
        obj1.addProperty("NoAplikasi", isilaci.get(position).getNoAplikasi());
        obj1.addProperty("Status", desc);
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().UpdateIsiLaci(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            AppUtil.notifsuccess(ListIsiLaciActivity.this,findViewById(android.R.id.content),"Data Telah Berubah");
                        }
                        else{
                            AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListIsiLaciActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onDropdownRecyclerClick(int position, String title) {
        DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),"Stok Opname",dataLaci, ListIsiLaciActivity.this,position);
    }
}
