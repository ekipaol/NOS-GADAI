package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

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
import com.application.bris.ikurma_nos_gadai.databinding.ItemListAreaBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataCabang;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_acak.ListUjiAcak;
import com.application.bris.ikurma_nos_gadai.page_putusan_gadai.PutusanGadaiActivity;

import java.util.ArrayList;
import java.util.List;

public class ListCabangAdapter extends RecyclerView.Adapter<ListCabangAdapter.MenuViewHolder>  implements Filterable {
    private List<DataCabang> data;
    private List<DataCabang> datafiltered;
    private Context context;
    private ItemListAreaBinding binding;
    private String asalmenu;


    private AppPreferences appPreferences;

    public ListCabangAdapter(Context context, List<DataCabang> mdata, String asalmenu) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
        this.asalmenu = asalmenu;
    }


    @NonNull
    @Override
    public ListCabangAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListAreaBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new ListCabangAdapter.MenuViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ListCabangAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataCabang data = datafiltered.get(position);

        holder.tvNamaArea.setText(data.getBranchName());
        onClicks(holder,position);

    }


    private void onClicks( @NonNull ListCabangAdapter.MenuViewHolder holder,int position) {
        holder.btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (asalmenu.equalsIgnoreCase("putusan")){
                    Intent intent = new Intent(context, PutusanGadaiActivity.class);
                    intent.putExtra("idCabang", datafiltered.get(position).getKodeCabang());
                    context.startActivity(intent);
                }
                else {
                    if(appPreferences.getMenuClick().equalsIgnoreCase("uji opname")){
                        Intent intent = new Intent(context, ListUjiOpnameActivity.class);
                        intent.putExtra("idCabang", datafiltered.get(position).getFidBranch());
                        context.startActivity(intent);
                    }
                    else    if(appPreferences.getMenuClick().equalsIgnoreCase("uji acak")){
                        Intent intent = new Intent(context, ListUjiAcak.class);
                        intent.putExtra("idCabang", datafiltered.get(position).getFidBranch());
                        context.startActivity(intent);
                    }
                }
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
                    List<DataCabang> filteredList = new ArrayList<>();
                    for (DataCabang row : data) {
                        if (row.getBranchName().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<DataCabang>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaArea;
        Button btnPilih;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaArea = binding.tvNamaArea;
            btnPilih = binding.btnPilih;

        }

    }
}