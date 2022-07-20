package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.report_jatuh_tempo;

import static com.application.bris.ikurma_nos_gadai.util.AppUtil.parseRupiahNoSymbol;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListJatuhTempoBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.ListPastDue;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class JatuhTempoAdapter extends RecyclerView.Adapter<JatuhTempoAdapter.MenuViewHolder> implements Filterable {

    private List<ListPastDue> data;
    private Context context;
    private ItemListJatuhTempoBinding binding;
    private List<ListPastDue> datafiltered;
    private AppPreferences appPreferences;

    public JatuhTempoAdapter(Context context, List<ListPastDue> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListJatuhTempoBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final ListPastDue datas = datafiltered.get(position);

        holder.tvNamaNasabah.setText(datas.getNamaNasabah());
        holder.tvNomorAplikasi.setText(datas.getNoAplikasi());
        holder.tvTglTempo.setText(AppUtil.parseTanggalGeneral(datas.getTanggalJatuhTempo(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy"));
//        holder.etByPemeliharaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etByPemeliharaan));
        holder.etByPemeliharaan.setText(dataTotalProcesesor(datas.getBiayaPemeliharaan().toString()));
//        holder.etPokok.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etPokok));
        holder.etPokok.setText(dataTotalProcesesor(datas.getPokok().toString()));
        System.out.println(datas.getPokok());

        holder.btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llJatuhTempo.setVisibility(View.VISIBLE);
                holder.rlPastdue.setVisibility(View.VISIBLE);
                holder.vPastdue.setVisibility(View.VISIBLE);
                holder.btCollapse.setVisibility(View.VISIBLE);
                holder.btShow.setVisibility(View.GONE);
            }
        });

        holder.btCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llJatuhTempo.setVisibility(View.GONE);
                holder.rlPastdue.setVisibility(View.GONE);
                holder.vPastdue.setVisibility(View.GONE);
                holder.btShow.setVisibility(View.VISIBLE);
                holder.btCollapse.setVisibility(View.GONE);
            }
        });

        onClicks(holder);
        defaultView(holder);
    }

    private void defaultView( MenuViewHolder holder){
        holder.llJatuhTempo.setVisibility(View.GONE);
        holder.rlPastdue.setVisibility(View.GONE);
        holder.vPastdue.setVisibility(View.GONE);
        holder.btShow.setVisibility(View.VISIBLE);
        holder.btCollapse.setVisibility(View.GONE);
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
                    List<ListPastDue> filteredList = new ArrayList<>();
                    for (ListPastDue row : data) {
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
                datafiltered = (ArrayList<ListPastDue>) filterResults.values;
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
            formattedString=parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);
            stringCutter=formattedString.split("\\.");
            Log.d("formattedstring",formattedString);

            if(stringCutter.length == 3){

                if(stringCutter[0].length() == 4){
                    return stringCutter[0]+"."+stringCutter[1].substring(0,2)+" M";
                } else{

                    return stringCutter[0]+"."+stringCutter[1].substring(0,2)+" JT";
                }

            }else {
                if (stringCutter.length <= 2){
                    if (stringCutter.length == 1){
                        return stringCutter[0];
                    } else {
                        return stringCutter[0]+"."+stringCutter[1];
                    }

                }else {
                    return stringCutter[0]+"."+stringCutter[1].substring(0,2)+" M";
                }

            }
        }
        else if(removeComma.length()<=15){
            formattedString=parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }
            Log.d("formattedstring",formattedString);
            return stringCutter[0]+"."+stringCutter[1].substring(0,2)+" T";
        }
        else{
            return parseRupiahNoSymbol(removeComma);
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaNasabah,tvNomorAplikasi,tvTglTempo;
        ExtendedEditText etPokok,etByPemeliharaan;
        Button btShow,btCollapse;
        LinearLayout llJatuhTempo;
        RelativeLayout rlPastdue;
        View vPastdue;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorAplikasi = binding.tvNomorAplikasi;
            tvTglTempo = binding.tvTanggalJatuhTempo;
            etPokok = binding.etBiayaPokok;
            etByPemeliharaan = binding.etBiayaPemeliharaan;
            btShow = binding.btShow;
            btCollapse = binding.btCollapse;
            llJatuhTempo = binding.llTglJatuhTempo;
            rlPastdue = binding.rvPastdue;
            vPastdue =binding.vPastdue;

        }

    }
}
