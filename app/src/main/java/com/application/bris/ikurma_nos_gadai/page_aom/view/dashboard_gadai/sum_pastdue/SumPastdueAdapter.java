package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.sum_pastdue;

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
import com.application.bris.ikurma_nos_gadai.databinding.ItemSumPastdueBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.SumPastDue;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.application.bris.ikurma_nos_gadai.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class SumPastdueAdapter extends RecyclerView.Adapter<SumPastdueAdapter.MenuViewHolder> implements Filterable {

    private List<SumPastDue> data;
    private Context context;
    private ItemSumPastdueBinding binding;
    private List<SumPastDue> datafiltered;
    private AppPreferences appPreferences;

    public SumPastdueAdapter(Context context, List<SumPastDue> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemSumPastdueBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final SumPastDue datas = datafiltered.get(position);

        holder.tvNamaNasabah.setText(datas.getNamaNasabah());
        holder.tvNamaProgram.setText(datas.getProgram());
        holder.tvTglTempo.setText(datas.getTanggalJatuhTempo());
        holder.etByPemeliharaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etByPemeliharaan));
        holder.etByPemeliharaan.setText(dataTotalProcesesor(datas.getBiayaPemeliharaan().toString()));
        holder.etPokok.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etPokok));
        holder.etPokok.setText(dataTotalProcesesor(datas.getPokok().toString()));
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
                    List<SumPastDue> filteredList = new ArrayList<>();
                    for (SumPastDue row : data) {
                        if (row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase())
                                || row.getProgram().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<SumPastDue>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private String dataTotalProcesesor(String nominal) {
        String formattedString = "";
        String removeComma = nominal.substring(0, nominal.length() - 3);
        String[] stringCutter;

        Log.d("nominalString", removeComma);

        if (removeComma.length() <= 12) {
            formattedString = AppUtil.parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if (formattedString.substring(0, 4).contains(",")) {
                stringCutter = formattedString.split(",");
            } else {
                stringCutter = formattedString.split("\\.");
            }
            Log.d("formattedstring", formattedString);
            return stringCutter[0] + "," + stringCutter[1].substring(0, 2) + " M";
        } else if (removeComma.length() <= 15) {
            formattedString = AppUtil.parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if (formattedString.substring(0, 4).contains(",")) {
                stringCutter = formattedString.split(",");
            } else {
                stringCutter = formattedString.split("\\.");
            }
            Log.d("formattedstring", formattedString);
            return stringCutter[0] + "," + stringCutter[1].substring(0, 2) + " T";
        } else {
            return AppUtil.parseRupiahNoSymbol(removeComma);
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaNasabah,tvNamaProgram,tvTglTempo;
        ExtendedEditText etPokok,etByPemeliharaan;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNamaProgram = binding.tvNamaProgram;
            tvTglTempo = binding.tvTanggalJatuhTempo;
            etPokok = binding.etBiayaPokok;
            etByPemeliharaan = binding.etBiayaPemeliharaan;

        }

    }
}
