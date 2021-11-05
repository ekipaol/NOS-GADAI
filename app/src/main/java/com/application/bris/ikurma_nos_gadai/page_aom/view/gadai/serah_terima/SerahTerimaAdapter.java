package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.serah_terima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListSerahTerimaBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataSerahTerima;

import java.util.List;

public class SerahTerimaAdapter extends RecyclerView.Adapter<SerahTerimaAdapter.MenuViewHolder> /*implements Filterable */{
    private List<DataSerahTerima> data;
    private Context context;
    private ItemListSerahTerimaBinding binding;
    private List<DataSerahTerima> datafiltered;

    private AppPreferences appPreferences;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public SerahTerimaAdapter(Context context, List<DataSerahTerima> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListSerahTerimaBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataSerahTerima data = datafiltered.get(position);

        holder.tvCabang.setText(data.getCabang());
        holder.tvNamaNasabah.setText(data.getNamaNasabah());
        holder.tvNomorAplikasi.setText(data.getNomorAplikasiGadai());
        holder.tvNomorSbge.setText(data.getSBGENumber());
        holder.tvTanggalJatuhTempo.setText(data.getTanggalJatuhTempo());
        onClicks(position, holder);

    }


    private void onClicks(int currentPosition, @NonNull MenuViewHolder holder) {

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailSerahTerimaActivity.class);
                intent.putExtra("NoAplikasi", data.get(currentPosition).getNomorAplikasiGadai());
                intent.putExtra("NamaNasabah", data.get(currentPosition).getNamaNasabah());
                intent.putExtra("NomorSbge", data.get(currentPosition).getSBGENumber());
                intent.putExtra("KodeCabang", data.get(currentPosition).getCabang());

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    /*@Override
    public int getItemCount() {
        if (datafiltered == null) {
            return 0;
        } else {
            return datafiltered.size();
        }
    }*/

    /*@Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<DataSerahTerima> filteredList = new ArrayList<>();
                    for (DataSerahTerima row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getSBGENumber().toLowerCase().contains(charString.toLowerCase())
                                || row.getLDNumber().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<DataSerahTerima>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang, tvNamaNasabah, tvNomorSbge, tvNomorAplikasi, tvTanggalJatuhTempo;
        Button btnDetail;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang = binding.tvCabang;
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorSbge = binding.tvNomorSbge;
            tvNomorAplikasi = binding.tvNomorAplikasi;
            tvTanggalJatuhTempo = binding.tvTanggalJatuhTempo;
            btnDetail = binding.btnDetail;

        }

    }
}
