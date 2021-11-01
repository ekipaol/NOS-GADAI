package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemListBrankasOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListBrankas;

import java.util.List;

public class ListBrankasAdapter extends RecyclerView.Adapter<ListBrankasAdapter.MenuViewHolder> {

    private List<ListBrankas> data;
    private Context context;
    private String ReffNoAktifitas,KodeCabang;
    private ItemListBrankasOpnameBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public ListBrankasAdapter(Context context,String ReffNoAktifitas, List<ListBrankas> mdata,String KodeCabang, DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.ReffNoAktifitas = ReffNoAktifitas;
        this.KodeCabang = KodeCabang;
    }

    @NonNull
    @Override
    public ListBrankasAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListBrankasOpnameBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new ListBrankasAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListBrankasAdapter.MenuViewHolder holder, int position) {
        holder.tvlaci.setText(String.valueOf(data.get(position).getIsiLaci().size()));
        holder.tvbrankas.setText("Brankas "+ data.get(position).getIdBrankas());
        onClicks(position, holder);
    }

    private void onClicks(int currentPosition, @NonNull ListBrankasAdapter.MenuViewHolder holder) {

        holder.btnBrankas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListLaciActivity.class);
                intent.putExtra("KodeCabang",KodeCabang);
                intent.putExtra("ReffNoAktifitas",ReffNoAktifitas);
                intent.putExtra("seqBrankas", data.get(currentPosition).getIdBrankas());
                intent.putExtra("isilaci", data.get(currentPosition).getIsiLaci().size());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvlaci, tvbrankas;
        LinearLayout btnBrankas;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvbrankas = binding.tvBrankas;
            tvlaci = binding.tvTotalLaci;
            btnBrankas = binding.btnBrankas;

        }

    }
}
