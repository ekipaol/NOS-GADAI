package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.capture_agunan;

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
import com.application.bris.ikurma_nos_gadai.databinding.ItemListAgunanBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.CaptureAgunan;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ListAgunanAdapter extends RecyclerView.Adapter<ListAgunanAdapter.MenuViewHolder> implements Filterable {

    private List<CaptureAgunan> data;
    private Context context;
    private ItemListAgunanBinding binding;
    private List<CaptureAgunan> datafiltered;
    private AppPreferences appPreferences;

    public ListAgunanAdapter(Context context, List<CaptureAgunan> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListAgunanBinding.inflate(layoutInflater, parent, false);
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
        holder.tvNomorApplikasi.setText(datas.getNomorAplikasiGadai());
        holder.tvTanggalTransaksi.setText(datas.getTanggalTransaksi());
        holder.tvTanggalJatohTempo.setText(datas.getTanggalJatuhTempo());
        onClicks(holder);


    }

    private void onClicks(@NonNull MenuViewHolder holder) {
        AppUtil.logSecure("logM", holder.tvNomorApplikasi.getText().toString());
        holder.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CaptureAgunanActivity.class);
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
                    List<CaptureAgunan> filteredList = new ArrayList<>();
                    for (CaptureAgunan row : data) {
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
                datafiltered = (ArrayList<CaptureAgunan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvCabang, tvNamaNasabah, tvNomorApplikasi, tvTanggalTransaksi, tvTanggalJatohTempo;
        Button btnCapture;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvCabang = binding.tvCabang;
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorApplikasi = binding.tvNomorApplikasi;
            tvTanggalTransaksi = binding.tvTanggalTransaksi;
            tvTanggalJatohTempo = binding.tvTanggalJatuhTempo;
            btnCapture = binding.btnCaptureAgunan;

        }

    }
}
