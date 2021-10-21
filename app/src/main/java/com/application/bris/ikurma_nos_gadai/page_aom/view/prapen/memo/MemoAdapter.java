package com.application.bris.ikurma_nos_gadai.page_aom.view.prapen.memo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.PrapenItemMemoBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.DataMemo;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MenuViewHolder> {
    private List<DataMemo> data;
    private Context context;
    private PrapenItemMemoBinding binding;

    public MemoAdapter(Context context,List< DataMemo> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public MemoAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= PrapenItemMemoBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new MemoAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.MenuViewHolder holder, final int position) {
        binding.tvNamaPemutus.setText(data.get(position).getNama());
        binding.tvJabatanPemutus.setText(data.get(position).getJabatan());
        binding.tvTahapAplikasi.setText(data.get(position).getTahapAplikasi());
        binding.tvJenisPutusan.setText(data.get(position).getJenisPutusan());
        binding.tvTanggalMemo.setText(data.get(position).getTanggal());
        binding.tvMemo.setText(data.get(position).getMemo());


        binding.btSalinHistoryCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "clicking", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("history_catatan", binding.tvMemo.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Memo sudah disalin", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        Button btnhapus;

        public MenuViewHolder(View itemView) {
            super(itemView);
            btnhapus=binding.btSalinHistoryCatatan;

        }

    }

}