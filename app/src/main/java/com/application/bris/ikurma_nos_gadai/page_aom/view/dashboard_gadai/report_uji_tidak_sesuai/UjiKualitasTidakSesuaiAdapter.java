package com.application.bris.ikurma_nos_gadai.page_aom.view.dashboard_gadai.report_uji_tidak_sesuai;

import static com.application.bris.ikurma_nos_gadai.util.AppUtil.parseRupiahNoSymbol;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemSumTsUjiKualitasBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.SumTopUpGadai;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class UjiKualitasTidakSesuaiAdapter extends RecyclerView.Adapter<UjiKualitasTidakSesuaiAdapter.MenuViewHolder> {

    private List<SumTopUpGadai> data;
    private Context context;
    private ItemSumTsUjiKualitasBinding binding;
    private List<SumTopUpGadai> datafiltered;
    private AppPreferences appPreferences;

    public UjiKualitasTidakSesuaiAdapter(Context context, List<SumTopUpGadai> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemSumTsUjiKualitasBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UjiKualitasTidakSesuaiAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final SumTopUpGadai datas = datafiltered.get(position);

        if (datas.getJumlahLoan()== null){
            holder.etJumlahLoan.setText("0");
        }else{
            holder.etJumlahLoan.setText(datas.getJumlahLoan().toString());
        }

        if (datas.getJumlahCIF()== null){
            holder.etJumlahCIF.setText("0");
         }else{
        holder.etJumlahCIF.setText(datas.getJumlahLoan().toString());
        }

        if(datas.getTotalOutstanding() == null){
            holder.etTotalStanding.setText("0");
        }else {
            holder.etTotalStanding.setText(dataTotalProcesesor(datas.getTotalOutstanding().toString()));
        }
        onClicks(holder);
    }



    private void onClicks(@NonNull UjiKualitasTidakSesuaiAdapter.MenuViewHolder holder) {

    }


    @Override
    public int getItemCount() {
        if (datafiltered == null) {
            return 0;
        } else {
            return datafiltered.size();
        }
    }

//        @Override
//        public Filter getFilter() {
//            return new Filter() {
//                @Override
//                protected FilterResults performFiltering(CharSequence charSequence) {
//                    String charString = charSequence.toString();
//                    if (charString.isEmpty()) {
//                        datafiltered = data;
//                    } else {
//                        List<DashboardTopUpGadai> filteredList = new ArrayList<>();
//                        for (DashboardTopUpGadai row : data) {
//                            if (row.getJumlahCIF().toLowerCase().contains(charString.toLowerCase())
//                                    || row.getJumlahLoan().toLowerCase().contains(charString.toLowerCase())
//                                    || row.getTotalOutstanding().toLowerCase().contains(charString.toLowerCase())) {
//
//                                    filteredList.add(row);
//                            }
//                        }
//                        datafiltered = filteredList;
//                    }
//                    FilterResults filterResults = new FilterResults();
//                    filterResults.values = datafiltered;
//                    return filterResults;
//                }
//
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults filterResults) {
//                    datafiltered = (ArrayList<DashboardTopUpGadai>) filterResults.values;
//                    notifyDataSetChanged();
//                }
//            };
//        }

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
        ExtendedEditText etJumlahCIF,etJumlahLoan,etTotalStanding;

        public MenuViewHolder(View itemView) {
            super(itemView);
            etJumlahLoan = binding.etJumlahLoan;
            etJumlahCIF = binding.etJumlahCif;
            etTotalStanding = binding.etTotalStanding;
        }

    }
}
