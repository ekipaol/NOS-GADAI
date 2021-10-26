package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemListAgunanBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.CaptureAgunan;

import java.util.List;

public class ListAgunanAdapter extends RecyclerView.Adapter<ListAgunanAdapter.MenuViewHolder> {

    private List<CaptureAgunan> data;
    private Context context;
    private ItemListAgunanBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public ListAgunanAdapter(Context context, List<CaptureAgunan> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListAgunanBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.tvCabang.setText(data.get(position).getCabang());
        holder.tvNamaNasabah.setText(data.get(position).getNamaNasabah());
        holder.tvNomorApplikasi.setText(data.get(position).getNomorAplikasiGadai());
        holder.tvTanggalTransaksi.setText(data.get(position).getTanggalTransaksi());
        holder.tvTanggalJatohTempo.setText(data.get(position).getTanggalJatuhTempo());

        onClicks(position,holder);



    }

    private void onClicks(int currentPosition,@NonNull MenuViewHolder holder){

        holder.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context , CaptureAgunanActivity.class);
                intent.putExtra("NoAplikasi",data.get(currentPosition).getNomorAplikasiGadai());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang,tvNamaNasabah,tvNomorApplikasi,tvTanggalTransaksi,tvTanggalJatohTempo;
        Button btnCapture;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang =binding.tvCabang;
            tvNamaNasabah=binding.tvNamaNasabah;
            tvNomorApplikasi=binding.tvNomorApplikasi;
            tvTanggalTransaksi=binding.tvTanggalTransaksi;
            tvTanggalJatohTempo=binding.tvTanggalJatuhTempo;
            btnCapture = binding.btnCaptureAgunan;

        }

    }
}
