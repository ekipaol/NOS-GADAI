package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.SummaryPerpanjangan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListPerpanjanganGagalBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.SummaryPerpanjanganGadai;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class SummaryPerpanjanganAdapter extends RecyclerView.Adapter<SummaryPerpanjanganAdapter.MenuViewHolder> implements Filterable {

    private List<SummaryPerpanjanganGadai> data;
    private Context context;
    private ItemListPerpanjanganGagalBinding binding;
    private List<SummaryPerpanjanganGadai> datafiltered;
    private AppPreferences appPreferences;

    public SummaryPerpanjanganAdapter(Context context, List<SummaryPerpanjanganGadai>mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListPerpanjanganGagalBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final SummaryPerpanjanganGadai datas = datafiltered.get(position);

        holder.tvBelumDireview.setText(datas.getBelumDireview());
        holder.tvBelumOtorisasi.setText(datas.getBelumOtorisasi());
        holder.tvBelumPemutusan.setText(datas.getBelumPemutusan());
        holder.tvBelumSimulasi.setText(datas.getBelumSimulasi());
        holder.tvBlokirSaldoRekening.setText(datas.getBlokirSaldoRekening());
        holder.tvPelunasan.setText(datas.getPelunasan());
        holder.tvPencairan.setText(datas.getPencairan());
        holder.tvPengembalianAgunan.setText(datas.getPengembalianAgunan());
        holder.tvReleaseSaldoRekening.setText(datas.getReleaseSaldoRekening());


    }

    private void onClicks(@NonNull MenuViewHolder holder) {

    }


    @Override
    public int getItemCount() {
        if (datafiltered == null) {
            return 0;
        } else {
            return datafiltered.size();
        }
    }


    //ini filternya lupakan dlu, soalnya datanya kayaknya masi 1
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<SummaryPerpanjanganGadai> filteredList = new ArrayList<>();
                    for (SummaryPerpanjanganGadai row : data) {
                        if (row.getBelumDireview().toLowerCase().contains(charString.toLowerCase())
                                || row.getBelumOtorisasi().toLowerCase().contains(charString.toLowerCase())) {

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
                datafiltered = (ArrayList<SummaryPerpanjanganGadai>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private String dataTotalProcesesor(String nominal){
        String formattedString="";
        String removeComma=nominal.substring(0,nominal.length()-3);
        String[] stringCutter;

        Log.d("nominalString",removeComma);

        if(removeComma.length()<=12){
            formattedString=AppUtil.parseRupiahNoSymbolWithTitik(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }
            Log.d("formattedstring",formattedString);
            return stringCutter[0]+","+stringCutter[1].substring(0,2)+" M";
        }
        else if(removeComma.length()<=15){
            formattedString=AppUtil.parseRupiahNoSymbolWithTitik(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }
            Log.d("formattedstring",formattedString);
            return stringCutter[0]+","+stringCutter[1].substring(0,2)+" T";
        }
        else{
            return AppUtil.parseRupiahNoSymbolWithTitik(removeComma);
        }
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvBelumSimulasi,tvBelumDireview,tvBelumPemutusan,tvPengembalianAgunan,tvBelumOtorisasi,tvBlokirSaldoRekening,tvPencairan,tvPelunasan,tvReleaseSaldoRekening;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvBelumSimulasi = binding.tvBelumSimulasi;
            tvBelumDireview = binding.tvBelumDireview;
            tvBelumPemutusan = binding.tvBelumPemutusan;
            tvPengembalianAgunan = binding.tvPengembalianAgunan;
            tvBelumOtorisasi = binding.tvBelumOtorisasi;
            tvBlokirSaldoRekening = binding.tvBlokirSaldoRekening;
            tvPencairan = binding.tvPencairan;
            tvPelunasan = binding.tvPelunasan;
            tvReleaseSaldoRekening = binding.tvReleaseSaldoRekening;

        }

    }
}
