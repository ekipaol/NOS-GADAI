package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemListLaciOpnameBinding;

import java.util.List;

public class ListLaciAdapter extends RecyclerView.Adapter<ListLaciAdapter.MenuViewHolder> {
    private List<Integer> data;
    private Context context;
    private ItemListLaciOpnameBinding binding;
    private String b,ReffNoAktifitas;

    public ListLaciAdapter(Context context,String brankas ,String ReffNoAktifitas, List<Integer> mdata) {
        this.context = context;
        this.data = mdata;
        this.b = brankas;
        this.ReffNoAktifitas = ReffNoAktifitas;
    }

    @NonNull
    @Override
    public ListLaciAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListLaciOpnameBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new ListLaciAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListLaciAdapter.MenuViewHolder holder, int position) {
        holder.tvlaci.setText("Laci " + String.valueOf(data.get(position)));
        onClicks(position, holder);
    }

    private void onClicks(int currentPosition, @NonNull ListLaciAdapter.MenuViewHolder holder) {

        holder.btnBrankas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListIsiLaciActivity.class);
                intent.putExtra("ReffNoAktifitas",ReffNoAktifitas);
                intent.putExtra("seqBrankas", b);
                intent.putExtra("isilaci", currentPosition+1);
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
            tvlaci = binding.tvLaci;
            btnBrankas = binding.llLaci;

        }

    }
}
