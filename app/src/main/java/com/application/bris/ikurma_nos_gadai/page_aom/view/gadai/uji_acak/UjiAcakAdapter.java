package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_acak;


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
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiAcakBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataUjiAcak;

import java.util.ArrayList;
import java.util.List;

public class UjiAcakAdapter extends RecyclerView.Adapter<UjiAcakAdapter.MenuViewHolder> implements Filterable {

    private List<DataUjiAcak> data;
    private Context context;
    private List<DataUjiAcak> datafiltered;
    private AppPreferences appPreferences;

    private ItemListUjiAcakBinding binding;

    public UjiAcakAdapter(Context context, List<DataUjiAcak> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;

    }

    @NonNull
    @Override
    public UjiAcakAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListUjiAcakBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new UjiAcakAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UjiAcakAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataUjiAcak data = datafiltered.get(position);

        holder.tvCabang.setText(appPreferences.getNamaKantor());
        holder.tvNamaNasabah.setText(data.getNamaNasabah());
        holder.tvNomorApplikasi.setText(data.getNomorAplikasiGadai());
        holder.tvTanggalTransaksi.setText(data.getTanggalTransaksi());
        holder.tvTanggalJatohTempo.setText(data.getTanggalJatuhTempo());

        onClicks(position,holder);



    }

    private void onClicks(int position, MenuViewHolder holder) {
        binding.btnUjiAcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUjiAcak.class);
                intent.putExtra("idAplikasi",data.get(position).getNomorAplikasiGadai());
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
                    List<DataUjiAcak> filteredList = new ArrayList<>();
                    for (DataUjiAcak row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getNomorAplikasiGadai().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataUjiAcak>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang,tvNamaNasabah,tvNomorApplikasi,tvTanggalTransaksi,tvTanggalJatohTempo;
        Button btnUjiAcak;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang =binding.tvCabang;
            tvNamaNasabah=binding.tvNamaNasabah;
            tvNomorApplikasi=binding.tvNomorApplikasi;
            tvTanggalTransaksi=binding.tvTanggalTransaksi;
            tvTanggalJatohTempo=binding.tvTanggalJatuhTempo;
            btnUjiAcak=binding.btnUjiAcak;
        }

    }
}
