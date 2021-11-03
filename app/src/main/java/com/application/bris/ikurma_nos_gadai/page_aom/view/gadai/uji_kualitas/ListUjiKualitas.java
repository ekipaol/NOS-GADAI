package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_kualitas;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
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
import com.application.bris.ikurma_nos_gadai.databinding.ActivityUjiKualitasListBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiKualitasBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataUjiKualitas;
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

public class ListUjiKualitas extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_kualitas.UjiKualitasAdapter ujiKualitasAdapter;

    private List<DataUjiKualitas> dataUjiKualitas = new ArrayList<>();
    ActivityUjiKualitasListBinding binding;
    ItemListUjiKualitasBinding bindingNamaField;

    private SearchView searchView;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUjiKualitasListBinding.inflate(getLayoutInflater());
        bindingNamaField = ItemListUjiKualitasBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbarReguler.tbRegular);

        //ini binding buat ngambil nama fieldnya aja
        setContentView(binding.getRoot());

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status

        setDropdownData();
        initialize();
        onClicks();
        setclickable();
        apiClientAdapter = new ApiClientAdapter(this,"http://10.0.116.105/");
        appPreferences = new AppPreferences(this);
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initialize();

    }

    private void setData() throws JSONException {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("FilterKodeCabang","ID001211");
        obj1.addProperty("FilterNoAplikasi", "NONE");
        obj1.addProperty("FilterNoKTP", "NONE");
        obj1.addProperty("FilterPengusul", "NONE");
        obj1.addProperty("FilterReviewer", "NONE");
        obj1.addProperty("FilterPemutus", "NONE");
        obj1.addProperty("FilterAOPembiayaan", "NONE");
        obj1.addProperty("FilterWorkFlowStatus", "LOLOS IDE");
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
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataUjiKualitas>>() {}.getType();
                            dataUjiKualitas = gson.fromJson(listDataString, type);
                            if (dataUjiKualitas.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                ujiKualitasAdapter = new UjiKualitasAdapter(ListUjiKualitas.this,dataUjiKualitas,ListUjiKualitas.this);
                                binding.rvListUjiKualitas.setLayoutManager(new LinearLayoutManager(ListUjiKualitas.this));
                                binding.rvListUjiKualitas.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListUjiKualitas.setAdapter(ujiKualitasAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            AppUtil.notiferror(ListUjiKualitas.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListUjiKualitas.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseGadai> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListUjiKualitas.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    private void onClicks() {

    }

    public void initialize() {
        binding.rvListUjiKualitas.setVisibility(View.VISIBLE);
        binding.rvListUjiKualitas.setHasFixedSize(true);
        ujiKualitasAdapter = new UjiKualitasAdapter(ListUjiKualitas.this, dataUjiKualitas, this);
        binding.rvListUjiKualitas.setLayoutManager(new LinearLayoutManager(ListUjiKualitas.this));
        binding.rvListUjiKualitas.setItemAnimator(new DefaultItemAnimator());
        binding.rvListUjiKualitas.setAdapter(ujiKualitasAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        //disable dlu smenetara
        binding.refresh.setEnabled(false);
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

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void customToolbar(){
        binding.toolbarReguler.tvPageTitle.setText("List Uji Kualitas");
        binding.toolbarReguler.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setclickable(){

    }

    private void searchAgunan(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ujiKualitasAdapter.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    ujiKualitasAdapter.getFilter().filter(query);
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
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {

    }

    private void setDropdownData() {

    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {
/*        if (title.equalsIgnoreCase(bindingNamaField.tfDpKualitas.getLabelText())) {
            data.get(position).setPilihanData(dataModel.getDESC());
            AppUtil.logSecure("setsuperdata", "set posisi : " + String.valueOf(position) + " dengan nilai : " + dataModel.getDESC());
            dataUjiKualitas.notifyDataSetChanged();

        }*/
    }

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onDropdownRecyclerClick(int position, String title) {
/*DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfDpKualitas.getLabelText(),dataDropdownUKS, ListUjiKualitas.this,position);

}*/

    }
}
