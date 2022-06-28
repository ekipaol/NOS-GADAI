package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.menu_penjualan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityMenuPenjualanGadaiBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_program_gadai.ListSumProgramGadai;
import com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_top_up.ListSumTopUpBSIM;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.application.bris.ikurma_nos_gadai.view.corelayout.CoreLayoutActivity;

public class MenuPenjualanActivity extends AppCompatActivity {

    ActivityMenuPenjualanGadaiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuPenjualanGadaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Dashboard");

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


        binding.btSumProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuPenjualanActivity.this, ListSumProgramGadai.class);
                startActivity(intent);

            }
        });

        binding.btTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuPenjualanActivity.this, ListSumTopUpBSIM.class);
                startActivity(intent);

            }
        });

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuPenjualanActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}