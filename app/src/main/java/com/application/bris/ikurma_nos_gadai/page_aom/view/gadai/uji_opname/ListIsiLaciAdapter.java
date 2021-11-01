package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemListIsiLaciOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListIsiLaci;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ListIsiLaciAdapter extends RecyclerView.Adapter<ListIsiLaciAdapter.MenuViewHolder> {
    private List<ListIsiLaci> data;
    private Context context;
    private ItemListIsiLaciOpnameBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public ListIsiLaciAdapter(Context context, List<ListIsiLaci> mdata, DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.dropdownRecyclerListener = dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public ListIsiLaciAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListIsiLaciOpnameBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new ListIsiLaciAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListIsiLaciAdapter.MenuViewHolder holder, int position) {
        holder.tvIdCabangPemilik.setText(data.get(position).getidCabangPemilik());
        holder.tvKodeSlot.setText(data.get(position).getidSlot());
        holder.tvSizeSlot.setText(data.get(position).getsizeSlot());
        holder.tvLdNumber.setText(data.get(position).getLDNumber());
        holder.tvNamaNasabah.setText(data.get(position).getNamaPemilik());
        holder.tvNomorApplikasi.setText(data.get(position).getNoAplikasi());
        holder.tvTglJatuhTempo.setText(data.get(position).getTanggalJatuhTempo());
        holder.tvTglPencairan.setText(data.get(position).getTanggalPencairan());
        holder.etopname.setFocusable(false);
        holder.etopname.setText(data.get(position).getStatusOpname());
        onClicks(position, holder);
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
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdCabangPemilik, tvKodeSlot, tvSizeSlot, tvLdNumber, tvNamaNasabah, tvNomorApplikasi, tvTglJatuhTempo, tvTglPencairan;
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
        }

    }
}
