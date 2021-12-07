package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_kualitas;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiKualitasBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataUjiKualitas;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan.CaptureAgunanActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class UjiKualitasAdapter extends RecyclerView.Adapter<UjiKualitasAdapter.MenuViewHolder> implements Filterable {
    private List<DataUjiKualitas> data;
    private Context context;
    private ItemListUjiKualitasBinding binding;
    private List<DataUjiKualitas> datafiltered;
    private AppPreferences appPreferences;

    public UjiKualitasAdapter(Context context, List<DataUjiKualitas> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListUjiKualitasBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new UjiKualitasAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UjiKualitasAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataUjiKualitas data = datafiltered.get(position);

        holder.tvCabang.setText(appPreferences.getNamaKantor());
        holder.tvNamaNasabah.setText(data.getNamaNasabah());
        holder.tvNomorApplikasi.setText(data.getNomorAplikasiGadai());
        holder.tvTanggalTransaksi.setText(data.getTanggalTransaksi());
        holder.tvTanggalJatohTempo.setText(data.getTanggalJatuhTempo());

        onClicks(position,holder);



    }

    private void onClicks(int position, MenuViewHolder holder) {
        AppUtil.logSecure("logM", holder.tvNomorApplikasi.getText().toString());
        holder.btnSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUjiSekarang.class);
                intent.putExtra("NoAplikasi", holder.tvNomorApplikasi.getText().toString());
                context.startActivity(intent);
            }
        });
        AppUtil.logSecure("logM", holder.tvNomorApplikasi.getText().toString());
        holder.btnNanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUjiNanti.class);
                intent.putExtra("NoAplikasi", holder.tvNomorApplikasi.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datafiltered == null) {
            return 0;
        } else {
            return datafiltered.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<DataUjiKualitas> filteredList = new ArrayList<>();
                    for (DataUjiKualitas row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getNomorAplikasiGadai().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    datafiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datafiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                datafiltered = (ArrayList<DataUjiKualitas>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang,tvNamaNasabah,tvNomorApplikasi,tvTanggalTransaksi,tvTanggalJatohTempo;
        Button btnSekarang,btnNanti;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang =binding.tvCabang;
            tvNamaNasabah=binding.tvNamaNasabah;
            tvNomorApplikasi=binding.tvNomorApplikasi;
            tvTanggalTransaksi=binding.tvTanggalTransaksi;
            tvTanggalJatohTempo=binding.tvTanggalJatuhTempo;
            btnSekarang = binding.btnSekarang;
            btnNanti = binding.btnNanti;
        }

    }
}
