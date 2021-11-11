package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.menu_penjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityDetailSerahTerimaBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityMenuPenjualanGadaiBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.view.flpp.FollowUpFlppActivity;
import com.application.bris.ikurma_nos_gadai.page_aom.view.flpp.InputUlangFlppActivity;
import com.application.bris.ikurma_nos_gadai.page_aom.view.flpp.KonfirmasiFlppActivity;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan.HasilPenjualanActivity;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.serah_terima.ListSerahTerimaActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.application.bris.ikurma_nos_gadai.view.corelayout.CoreLayoutActivity;

public class MenuPenjualanActivity extends AppCompatActivity {

    ActivityMenuPenjualanGadaiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuPenjualanGadaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppUtil.toolbarRegular(this, "Menu Penjualan");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuPenjualanActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });


        binding.btHasilPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuPenjualanActivity.this, HasilPenjualanActivity.class);
                startActivity(intent);

            }
        });

        binding.btSerahTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuPenjualanActivity.this, ListSerahTerimaActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuPenjualanActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}