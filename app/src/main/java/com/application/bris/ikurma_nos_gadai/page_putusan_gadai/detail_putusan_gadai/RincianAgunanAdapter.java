package com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemRincianAgunanGadaiBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.ObjekGadai;

import java.util.List;

public class RincianAgunanAdapter extends RecyclerView.Adapter<RincianAgunanAdapter.MenuViewHolder> {
    private List<ObjekGadai> data;
    private Context context;
    private ItemRincianAgunanGadaiBinding binding;

    public RincianAgunanAdapter(Context context, List< ObjekGadai> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public RincianAgunanAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemRincianAgunanGadaiBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new RincianAgunanAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RincianAgunanAdapter.MenuViewHolder holder, final int position) {
        final ObjekGadai datas = data.get(position);
        int posisiWhole=position+1;

        holder.tvNomorAgunan.setText("Agunan "+posisiWhole);
        holder.tvRincianAgunan.setText("Rincian Agunan :  "+datas.getRincianAgunan());
        holder.tvJumlahAgunan.setText("Jumlah Agunan :  "+datas.getJumlahEmas());
        holder.tvKaratase.setText("Karatase :  "+datas.getKaratase());
        holder.tvBeratBersih.setText("Berat Bersih :  "+datas.getBeratBersih()+ " Gram");
        holder.tvBeratJenis.setText("Berat Jenis :  "+datas.getBeratJenis()+ " Gram");
        holder.tvEquivalenUjroh.setText("Equivalen Ujroh :  "+datas.getEqv()+ " %");
        holder.tvPersentaseUjroh.setText("Persentase Ujroh :  "+datas.getProsentaseUjroh()+ " %");
    }

    @Override
    public int getItemCount() {
        if(data==null){
            return 0;
        }
        else{
            return data.size();
        }

    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvRincianAgunan,tvJumlahAgunan,tvKaratase,tvBeratBersih,tvNomorAgunan,tvBeratJenis,tvEquivalenUjroh,tvPersentaseUjroh;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvRincianAgunan=binding.tvRincianAgunan;
            tvJumlahAgunan=binding.tvJumlahAgunan;
            tvKaratase=binding.tvKarataseAgunan;
            tvBeratBersih=binding.tvBeratBersih;
            tvNomorAgunan=binding.tvNomorAgunan;
            tvBeratJenis=binding.tvBeratJenis;
            tvEquivalenUjroh=binding.tvEqUjroh;
            tvPersentaseUjroh=binding.tvPersentaseUjroh;
        }

    }

}
