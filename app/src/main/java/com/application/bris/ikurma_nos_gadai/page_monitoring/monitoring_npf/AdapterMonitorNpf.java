package com.application.bris.ikurma_nos_gadai.page_monitoring.monitoring_npf;


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

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.model.monitoring.NasabahMonitoring;
import com.application.bris.ikurma_nos_gadai.page_monitoring.monitoring_pencairan.DetailMonitoringNasabahActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterMonitorNpf extends RecyclerView.Adapter<AdapterMonitorNpf.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<NasabahMonitoring> data;
    private List<NasabahMonitoring> datafiltered;

    public AdapterMonitorNpf(Context context, List<NasabahMonitoring> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterMonitorNpf.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_monitoring_pencairan, parent, false);
        return new AdapterMonitorNpf.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMonitorNpf.HotprospekViewHolder holder, final int position) {
        final NasabahMonitoring datas = datafiltered.get(position);

        holder.tv_nama_nasabah.setText(datas.getNamaNasabah());
        holder.tv_pencairan_nasabah.setText(AppUtil.parseRupiah(datas.getPlafondAwal()));
        holder.tv_dpk_nasabah.setText(AppUtil.parseRupiah(datas.getDpk()));
        holder.tv_outstanding_nasabah.setText(AppUtil.parseRupiah(datas.getOs()));
        holder.tv_kolek.setText(datas.getKol());

        holder.bt_detail_nasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailMonitoringNasabahActivity.class);
                intent.putExtra("dataNasabah",datas);
                context.startActivity(intent);
//              Toast.makeText(context, "Anda memencet user : "+datas.getNamaNasabah(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datafiltered.size();
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
                    List<NasabahMonitoring> filteredList = new ArrayList<>();
                    for (NasabahMonitoring row : data){
                        if(row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase()) || row.getProduk().toLowerCase().contains(charString.toLowerCase())
                        ){
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
                datafiltered = (ArrayList<NasabahMonitoring>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private Button bt_detail_nasabah;
        private TextView tv_nama_nasabah, tv_pencairan_nasabah, tv_outstanding_nasabah, tv_dpk_nasabah, tv_kolek;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_nama_nasabah = (TextView) itemView.findViewById(R.id.tv_nama_nasabah);
            tv_pencairan_nasabah = (TextView) itemView.findViewById(R.id.tv_pencairan_nasabah);
            tv_outstanding_nasabah = (TextView) itemView.findViewById(R.id.tv_outstanding_nasabah);
            tv_dpk_nasabah = (TextView) itemView.findViewById(R.id.tv_dpk_nasabah);
            tv_kolek = (TextView) itemView.findViewById(R.id.tv_kol_nasabah);
            bt_detail_nasabah = (Button) itemView.findViewById(R.id.bt_detil_monitoring_pencairan_nasabah);

        }
    }
}