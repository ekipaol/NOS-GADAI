package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListUjiOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListBrankas;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListBrankas;
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

public class ListBrankasActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {
    ActivityListUjiOpnameBinding binding;
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListBrankasAdapter listBrankasAdapter;

    public static int idAplikasi=0;
    private List<ListBrankas> dataBrankas =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding= ActivityListUjiOpnameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Button Click
        setclickable();

        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        binding.tvCabang.setText("Kode Cabang : "+ getIntent().getStringExtra("kodeCabang"));
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
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("kodeCabang", getIntent().getStringExtra("kodeCabang"));
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataBrankasInfo(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("BrankasInfo").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListBrankas>>() {}.getType();
                            dataBrankas = gson.fromJson(listDataString, type);
                            if (dataBrankas.size() > 0){
                                binding.llEmptydata.setVisibility(View.GONE);
                                listBrankasAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListBrankasAdapter(ListBrankasActivity.this,getIntent().getStringExtra("ReffNoAktifitas"),dataBrankas,getIntent().getStringExtra("kodeCabang"),ListBrankasActivity.this);
                                binding.rvListOpname.setLayoutManager(new LinearLayoutManager(ListBrankasActivity.this));
                                binding.rvListOpname.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListOpname.setAdapter(listBrankasAdapter);
                            }
                            else {
                                binding.llEmptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            AppUtil.notiferror(ListBrankasActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ListBrankasActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListBrankasActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initialize(){
        binding.rvListOpname.setVisibility(View.VISIBLE);
        binding.rvListOpname.setHasFixedSize(true);
        listBrankasAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListBrankasAdapter(this,getIntent().getStringExtra("ReffNoAktifitas"), dataBrankas,getIntent().getStringExtra("kodeCabang"),this);
        binding.rvListOpname.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListOpname.setItemAnimator(new DefaultItemAnimator());
        binding.rvListOpname.setAdapter(listBrankasAdapter);
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
        binding.toolbarNosearch.tvPageTitle.setText("List Brankas Opname");
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

    }

    @Override
    public void onDropdownRecyclerClick(int position, String title) {

    }
}
