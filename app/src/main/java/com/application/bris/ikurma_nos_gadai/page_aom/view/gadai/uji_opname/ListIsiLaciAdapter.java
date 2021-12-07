package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListIsiLaciOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListIsiLaci;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ListIsiLaciAdapter extends RecyclerView.Adapter<ListIsiLaciAdapter.MenuViewHolder> implements Filterable {
    private List<ListIsiLaci> data;
    private List<ListIsiLaci> datafiltered;
    private Context context;
    private ItemListIsiLaciOpnameBinding binding;
    private AppPreferences appPreferences;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public ListIsiLaciAdapter(Context context, List<ListIsiLaci> mdata, DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
        this.dropdownRecyclerListener = dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public ListIsiLaciAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListIsiLaciOpnameBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new ListIsiLaciAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListIsiLaciAdapter.MenuViewHolder holder, int position) {
        final ListIsiLaci datas = datafiltered.get(position);
        holder.tvIdCabangPemilik.setText(appPreferences.getNamaKantor());
        holder.tvKodeSlot.setText(datas.getidSlot());
        holder.tvSizeSlot.setText(datas.getsizeSlot());
        holder.tvLdNumber.setText(datas.getLDNumber());
        holder.tvNamaNasabah.setText(datas.getNamaPemilik());
        holder.tvNomorApplikasi.setText(datas.getNoAplikasi());
        holder.tvTglJatuhTempo.setText(datas.getTanggalJatuhTempo());
        holder.tvTglPencairan.setText(datas.getTanggalPencairan());
        holder.etopname.setFocusable(false);
        holder.etopname.setText(datas.getStatusOpname());
        onClicks(position, holder);
        defaultView(holder);
    }

    private void onClicks(int currentPosition, @NonNull ListIsiLaciAdapter.MenuViewHolder holder) {
        holder.tfopname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition, "Stok Opname");
            }
        });

        holder.etopname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition, "Stok Opname");
            }
        });

        holder.tfopname.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition, "Stok Opname");
            }
        });

        holder.btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llNomorAplikasi.setVisibility(View.VISIBLE);
                holder.llSizeSlot.setVisibility(View.VISIBLE);
                holder.llTanggalJatuhTempo.setVisibility(View.VISIBLE);
                holder.llTanggalPencairan.setVisibility(View.VISIBLE);
                holder.btCollapse.setVisibility(View.VISIBLE);
                holder.btShow.setVisibility(View.GONE);
            }
        });

        holder.btCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llNomorAplikasi.setVisibility(View.GONE);
                holder.llSizeSlot.setVisibility(View.GONE);
                holder.llTanggalJatuhTempo.setVisibility(View.GONE);
                holder.llTanggalPencairan.setVisibility(View.GONE);
                holder.btShow.setVisibility(View.VISIBLE);
                holder.btCollapse.setVisibility(View.GONE);
            }
        });
    }

    private void defaultView( ListIsiLaciAdapter.MenuViewHolder holder){
        holder.llNomorAplikasi.setVisibility(View.GONE);
        holder.llSizeSlot.setVisibility(View.GONE);
        holder.llTanggalJatuhTempo.setVisibility(View.GONE);
        holder.llTanggalPencairan.setVisibility(View.GONE);
        holder.btCollapse.setVisibility(View.GONE);
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
                    List<ListIsiLaci> filteredList = new ArrayList<>();
                    for (ListIsiLaci row : data) {
                        if (row.getNamaPemilik().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getLDNumber().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getNoAplikasi().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<ListIsiLaci>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdCabangPemilik, tvKodeSlot, tvSizeSlot, tvLdNumber, tvNamaNasabah, tvNomorApplikasi, tvTglJatuhTempo, tvTglPencairan;
        Button btShow,btCollapse;
        LinearLayout llNomorAplikasi,llSizeSlot,llTanggalPencairan,llTanggalJatuhTempo;
        TextFieldBoxes tfopname;
        EditText etopname;

        public MenuViewHolder(View itemView) {
            super(itemView);
            etopname = binding.etOpname;
            tfopname = binding.tfOpname;
            tvIdCabangPemilik = binding.tvIdCabangPemilik;
            tvKodeSlot = binding.tvKodeSlot;
            tvSizeSlot = binding.tvSizeSlot;
            tvLdNumber = binding.tvLdNumber;
            tvNamaNasabah = binding.tvNamaNasabah;
            tvNomorApplikasi = binding.tvNomorApplikasi;
            tvTglJatuhTempo = binding.tvTglJatuhTempo;
            tvTglPencairan = binding.tvTglPencairan;
            btShow=binding.btShow;
            btCollapse=binding.btCollapse;
            llNomorAplikasi=binding.llIdAplikasi;
            llSizeSlot=binding.llSizeSlot;
            llTanggalPencairan=binding.llTanggalPencairan;
            llTanggalJatuhTempo=binding.llTanggalJatuhTempo;
        }

    }
}
