package com.application.bris.ikurma_nos_gadai.page_putusan_gadai;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqAplikasiGadai;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqChannelAplikasiGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.GadaiPutusanActivityBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataGadai;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutusanGadaiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SearchView searchView;
    private String kodeCabang="";
    List<DataGadai> dataPutusanGadai;
    PutusanGadaiAdapter AdapterGadai;
    ApiClientAdapter apiClientAdapter;
    GadaiPutusanActivityBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=GadaiPutusanActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backgroundStatusBar();

        if(getIntent().hasExtra("idCabang")){
            kodeCabang=getIntent().getStringExtra("idCabang");
        }
        else {
            kodeCabang = "NONE";
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
        PutusanGadaiActivity.this.recreate();
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
        apiClientAdapter=new ApiClientAdapter(PutusanGadaiActivity.this);

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("Putusan Gadai");
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        loadData();
    }


    public void loadData() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqAplikasiGadai reqGadai=new ReqAplikasiGadai();
        ReqChannelAplikasiGadai req = new ReqChannelAplikasiGadai();
        AppPreferences appPreferences=new AppPreferences(PutusanGadaiActivity.this);


        //pantekan kode cabang
//        reqGadai.setFilterKodeCabang(appPreferences.getKodeKantor());
//        reqGadai.setFilterKodeCabang();

//        AppUtil.logSecure("banyu",kodeCabang);
        reqGadai.setFilterKodeCabang(kodeCabang);
        reqGadai.setFilterWorkFlowStatus("Waiting Review Pemutus");
        reqGadai.setFilterPemutus(appPreferences.getNik());
        reqGadai.setPemutusBeradaDiTempat("Tidak");

        req.setData(reqGadai);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listAplikasiGadai(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListPutusanGadai.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataGadai>>() {
                        }.getType();

                        dataPutusanGadai = gson.fromJson(listDataString, type);
                        AdapterGadai = new PutusanGadaiAdapter(PutusanGadaiActivity.this, dataPutusanGadai);
                        binding.rvListPutusanGadai.setLayoutManager(new LinearLayoutManager(PutusanGadaiActivity.this));
                        binding.rvListPutusanGadai.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListPutusanGadai.setAdapter(AdapterGadai);


                        if (dataPutusanGadai.size() == 0) {
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
                AppUtil.notiferror(PutusanGadaiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }


    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AdapterGadai.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    AdapterGadai.getFilter().filter(query);
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
        binding.rvListPutusanGadai.setVisibility(View.GONE);

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