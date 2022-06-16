package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.TopUpGagal;

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
import com.application.bris.ikurma_nos_gadai.model.gadai.PerpanjanganGadaiGagal;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class PerpanjanganGagalAdapter extends RecyclerView.Adapter<PerpanjanganGagalAdapter.MenuViewHolder> implements Filterable {

    private List<PerpanjanganGadaiGagal> data;
    private Context context;
    private ItemListPerpanjanganGagalBinding binding;
    private List<PerpanjanganGadaiGagal> datafiltered;
    private AppPreferences appPreferences;

    public PerpanjanganGagalAdapter(Context context, List<PerpanjanganGadaiGagal>mdata) {
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
        final PerpanjanganGadaiGagal datas = datafiltered.get(position);

        holder.tvNamaNasabah.setText(datas.getNamaNasabah());
        holder.tvNomorAplikasi.setText(datas.getNoAplikasi());
        holder.tvNomorCIF.setText(datas.getNomorCIF());
        holder.tvNomorLoan.setText(datas.getNoLoan());
        holder.tvTotalPembiayaan.setText(dataTotalProcesesor(datas.getOSPembiayaan()));
        holder.tvTanggalTransaksi.setText(AppUtil.parseTanggalGeneral(datas.getTanggalPencairan(), "yyyy-MM-dd hh:mm:ss", "dd-MMM-YYYY"));
        onClicks(holder);


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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<PerpanjanganGadaiGagal> filteredList = new ArrayList<>();
                    for (PerpanjanganGadaiGagal row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getNoAplikasi().toLowerCase().contains(charString.toLowerCase())
                                || row.getTanggalPencairan().toLowerCase().contains(charString.toLowerCase())
                                || row.getNoLoan().toLowerCase().contains(charString.toLowerCase())) {

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
                datafiltered = (ArrayList<PerpanjanganGadaiGagal>) filterResults.values;
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
        TextView tvNamaNasabah,tvNomorAplikasi,tvNomorLoan,tvNomorCIF,tvTanggalTransaksi,tvTotalPembiayaan;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorAplikasi = binding.tvNomorAplikasi;
            tvNomorLoan = binding.tvNomorLoan;
            tvNomorCIF = binding.tvNomorCif;
            tvTanggalTransaksi = binding.tvTanggalTransaksi;
            tvTotalPembiayaan = binding.tvTotalPembiayaan;

        }

    }
}
