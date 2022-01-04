package com.application.bris.ikurma_nos_gadai.page_putusan_gadai;

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

import com.application.bris.ikurma_nos_gadai.databinding.ItemListGadaiBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataGadai;
import com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai.DetailPutusanGadaiActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class PutusanGadaiAdapter extends RecyclerView.Adapter<PutusanGadaiAdapter.MenuViewHolder> implements Filterable {
    private List<DataGadai> data;
    private List<DataGadai> datafiltered;
    private Context context;
    private ItemListGadaiBinding binding;

    public PutusanGadaiAdapter(Context context, List< DataGadai> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public PutusanGadaiAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListGadaiBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new PutusanGadaiAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PutusanGadaiAdapter.MenuViewHolder holder, final int position) {
        final DataGadai datas = datafiltered.get(position);

        holder.tvNamaGadai.setText(datas.getNamaSesuaiKTP());
        holder.tvNoPermohonan.setText(datas.getNoAplikasi());
        holder.tvTanggalJam.setText(datas.getTanggalCair());
        holder.tvNominal.setText(AppUtil.parseRupiahTitik(datas.getPinjamanGadaiDiambil()));

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailPutusanGadaiActivity.class);
                intent.putExtra("NoAplikasi",datas.getNoAplikasi());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        if(datafiltered==null){
            return 0;
        }
        else{
            return datafiltered.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = data;
                } else {
                    List<DataGadai> filteredList = new ArrayList<>();
                    for (DataGadai row : data){
                        if(row.getNamaSesuaiKTP().toLowerCase().contains(charString.toLowerCase()) || row.getNoAplikasi().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataGadai>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaGadai,tvNoPermohonan,tvTanggalJam,tvNominal;
        Button btnDetail;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNamaGadai=binding.tvNamaGadai;
            tvNoPermohonan=binding.tvNoPermohonan;
            tvTanggalJam=binding.tvTanggalJam;
            tvNominal=binding.tvNominal;
            btnDetail=binding.btnDetail;
        }

    }

}