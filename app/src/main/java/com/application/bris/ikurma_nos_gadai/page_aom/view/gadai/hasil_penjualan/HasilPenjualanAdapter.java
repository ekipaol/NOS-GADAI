package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.hasil_penjualan;

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
import com.application.bris.ikurma_nos_gadai.databinding.ItemListHasilPenjualanBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.CaptureAgunan;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class HasilPenjualanAdapter extends RecyclerView.Adapter<HasilPenjualanAdapter.MenuViewHolder> implements Filterable {

    private List<CaptureAgunan> data;
    private Context context;
    private ItemListHasilPenjualanBinding binding;
    private List<CaptureAgunan> datafiltered;
    private AppPreferences appPreferences;

    public HasilPenjualanAdapter(Context context, List<CaptureAgunan> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListHasilPenjualanBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final CaptureAgunan datas = datafiltered.get(position);

        holder.tvCabang.setText(appPreferences.getNamaKantor());
        holder.tvNamaNasabah.setText(datas.getNamaNasabah());
        holder.tvNomorSbge.setText(datas.getSBGENumber());
        holder.tvNomorKontrak.setText(datas.getNomorAplikasiGadai());
        holder.tvTotalKewajiban.setText(AppUtil.parseRupiah(datas.getPinjamanGadaiDiambil()));
        onClicks(position, holder);


    }

    private void onClicks(int currentPosition, @NonNull MenuViewHolder holder) {

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHasilPenjualanActivity.class);
                intent.putExtra("NoAplikasi", data.get(currentPosition).getNomorAplikasiGadai());
                intent.putExtra("NamaNasabah", data.get(currentPosition).getNamaNasabah());
                intent.putExtra("KodeCabang", data.get(currentPosition).getCabang());
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
                    List<CaptureAgunan> filteredList = new ArrayList<>();
                    for (CaptureAgunan row : data) {
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
                datafiltered = (ArrayList<CaptureAgunan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang, tvNamaNasabah, tvNomorSbge, tvNomorKontrak, tvTotalKewajiban;
        Button btnDetail;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang = binding.tvCabang;
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorSbge = binding.tvNomorSbge;
            tvNomorKontrak = binding.tvNomorKontrak;
            tvTotalKewajiban = binding.tvTotalKewajiban;
            btnDetail = binding.btnDetail;
        }
    }
}
