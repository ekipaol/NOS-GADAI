package com.application.bris.ikurma_nos_gadai.view.corelayout.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemCaptureAgunanFrontBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataGadai;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan.CaptureAgunanActivity;
import com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai.DetailPutusanGadaiActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class CaptureAgunanFrontAdapter extends RecyclerView.Adapter<CaptureAgunanFrontAdapter.MenuViewHolder> {
    private List<DataGadai> data;
    private Context context;
    private ItemCaptureAgunanFrontBinding binding;
    boolean isPengusul;

    public CaptureAgunanFrontAdapter(Context context,List<DataGadai> mdata,boolean isPengusul) {
        this.context = context;
        this.data = mdata;
        this.isPengusul=isPengusul;
    }

    @NonNull
    @Override
    public CaptureAgunanFrontAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemCaptureAgunanFrontBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new CaptureAgunanFrontAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptureAgunanFrontAdapter.MenuViewHolder holder, final int position) {
        final DataGadai datas = data.get(position);

        holder.tvNama.setText(datas.getNamaSesuaiKTP());
        holder.tvIdAplikasi.setText(datas.getNoAplikasi());
        holder.tvTanggal.setText(datas.getTanggalCair());
        holder.cvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPengusul){
                    Intent intent = new Intent(context, CaptureAgunanActivity.class);
                    intent.putExtra("NoAplikasi", holder.tvIdAplikasi.getText().toString());
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, DetailPutusanGadaiActivity.class);
                    intent.putExtra("NoAplikasi", holder.tvIdAplikasi.getText().toString());
                    context.startActivity(intent);
                }



            }
        });

        try{
            holder.tvPlafon.setText(AppUtil.parseRupiah(datas.getTotalPinjamanMaximum()));
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }
        else return 0;
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama,tvIdAplikasi,tvPlafon,tvTanggal;
        CardView cvData;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNama=binding.tvNama;
            tvIdAplikasi=binding.tvIdAplikasi;
            tvPlafon=binding.tvPlafond;
            tvTanggal=binding.tvTanggal;
            cvData=binding.cvCaptureAgunanFront;
        }

    }

}