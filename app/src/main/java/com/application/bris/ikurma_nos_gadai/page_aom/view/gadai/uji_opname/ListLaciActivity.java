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
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListLaciOpnameBinding;
import java.util.ArrayList;
import java.util.List;

public class ListLaciActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    ActivityListLaciOpnameBinding binding;
    private com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListLaciAdapter listBrankasAdapter;

    public static int idAplikasi=0;
    private List<Integer> dataBrankas =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding View
        binding= ActivityListLaciOpnameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Navbar
        customToolbar();
        //Sdk untuk background toolbar
        backgroundStatusBar();
        //initialize List
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding.tvCabang.setText("Kode Cabang : "+ appPreferences.getNamaKantor());
        addData();
        initialize();
    }

    private void addData(){
        for (int i = 0; i < getIntent().getIntExtra("isilaci",0); i++) {
            dataBrankas.add(i+1);
        }

        if(dataBrankas.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    public void initialize(){
        binding.rvListBerangkas.setVisibility(View.VISIBLE);
        binding.rvListBerangkas.setHasFixedSize(true);
        listBrankasAdapter = new com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname.ListLaciAdapter(this,getIntent().getStringExtra("seqBrankas"),getIntent().getStringExtra("ReffNoAktifitas"), dataBrankas);
        binding.rvListBerangkas.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListBerangkas.setItemAnimator(new DefaultItemAnimator());
        binding.rvListBerangkas.setAdapter(listBrankasAdapter);
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
        binding.toolbarNosearch.tvPageTitle.setText("Opname Laci Brankas "+ getIntent().getStringExtra("seqBrankas"));
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}
