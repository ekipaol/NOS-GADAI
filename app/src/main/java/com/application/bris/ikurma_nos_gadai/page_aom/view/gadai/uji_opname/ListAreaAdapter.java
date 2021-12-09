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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListAreaBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListSerahTerimaBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataArea;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataSerahTerima;
import com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.serah_terima.DetailSerahTerimaActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ListAreaAdapter extends RecyclerView.Adapter<ListAreaAdapter.MenuViewHolder>  implements Filterable {
    private List<DataArea> data;
    private List<DataArea> datafiltered;
    private Context context;
    private ItemListAreaBinding binding;


    private AppPreferences appPreferences;

    public ListAreaAdapter(Context context, List<DataArea> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }


    @NonNull
    @Override
    public ListAreaAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListAreaBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new ListAreaAdapter.MenuViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ListAreaAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final DataArea data = datafiltered.get(position);

       holder.tvNamaArea.setText(data.getAreaName());
        onClicks(holder,position);

    }


    private void onClicks( @NonNull ListAreaAdapter.MenuViewHolder holder,int position) {
        holder.btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListCabangActivity.class);
                intent.putExtra("fidArea",datafiltered.get(position).getFidArea());
                context.startActivity(intent);
//                Toast.makeText(context, "Belum ada", Toast.LENGTH_SHORT).show();
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
                    List<DataArea> filteredList = new ArrayList<>();
                    for (DataArea row : data) {
                        if (row.getAreaName().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<DataArea>) filterResults.values;
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