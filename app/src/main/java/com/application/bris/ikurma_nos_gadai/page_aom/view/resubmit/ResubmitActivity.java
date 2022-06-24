package com.application.bris.ikurma_nos_gadai.page_aom.view.resubmit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqBranch;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListResubmitGadaiBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataGadai;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataResubmit;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResubmitActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SearchView searchView;
    private String kodeCabang="";
    List<DataResubmit> dataResubmit;
    ResubmitAdapter adapterResubmit;
    ApiClientAdapter apiClientAdapter;
    ActivityListResubmitGadaiBinding binding;
    AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListResubmitGadaiBinding.inflate(getLayoutInflater());
        appPreferences=new AppPreferences(ResubmitActivity.this);
        setContentView(binding.getRoot());
        backgroundStatusBar();

        if(getIntent().hasExtra("idCabang")){
            kodeCabang=getIntent().getStringExtra("idCabang");
        }
        else {
            kodeCabang = appPreferences.getIdCabang();
        }

        main();
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ResubmitActivity.this.recreate();
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

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        setSupportActionBar(binding.toolbar.tbRegular);
        apiClientAdapter=new ApiClientAdapter(ResubmitActivity.this);

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("List Gadai Gagal");
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        loadData();
    }


    public void loadData() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqBranch req=new ReqBranch();
        req.setBranch(kodeCabang);
        
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listGadaiGagal(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListResubmitGadai.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    String listDataString;
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        try{
                            listDataString= response.body().getData().toString();
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                            listDataString= "[]";
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataResubmit>>() {
                        }.getType();

                        DataResubmit dataDummy1=new DataResubmit();
                        DataResubmit dataDummy2=new DataResubmit();
                        dataDummy1.setNamaNasabah("Alexander Lontong");
                        dataDummy1.setNoAplikasi("NOS12121212");
                        dataDummy1.setNoLoan("LD12121212");
                        dataDummy1.setTanggalPencairan("18-03-2022");
                        dataDummy1.setoSPembiayaan("20000000");
                        dataDummy1.setProduk("Top up");
                        dataDummy1.setState("Bayar Materai");
                        dataDummy1.setActivityID(0l);

                        dataDummy2.setNamaNasabah("Si Uwong");
                        dataDummy2.setNoAplikasi("NOS78121212");
                        dataDummy2.setNoLoan("LD12991212");
                        dataDummy2.setTanggalPencairan("11-01-2022");
                        dataDummy2.setoSPembiayaan("41000000");
                        dataDummy2.setProduk("Top up");
                        dataDummy2.setState("Bayar Materai");
                        dataDummy2.setActivityID(0l);


                        dataResubmit = gson.fromJson(listDataString, type);
                        dataResubmit.add(dataDummy1);
                        dataResubmit.add(dataDummy2);

                        adapterResubmit = new ResubmitAdapter(ResubmitActivity.this, dataResubmit);
                        binding.rvListResubmitGadai.setLayoutManager(new LinearLayoutManager(ResubmitActivity.this));
                        binding.rvListResubmitGadai.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListResubmitGadai.setAdapter(adapterResubmit);


                        if (dataResubmit.size() == 0) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);

                        } else {
                            binding.llEmptydata.setVisibility(View.GONE);

                        }
                    }
                    else if (response.body().getStatus().equalsIgnoreCase("14")) {
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ResubmitActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }


    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterResubmit.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterResubmit.getFilter().filter(query);
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
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
//        progressbar_loading.setVisibility(View.VISIBLE);
      doRefresh();
    }

    //dibikin terpisah biar bisa dipanggil dari adapter
    public void doRefresh(){
        binding.rvListResubmitGadai.setVisibility(View.GONE);

        loadData();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(PutusanGadaiActivity.this, CoreLayoutActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
//        startActivity(intent);
        finish();
    }


}