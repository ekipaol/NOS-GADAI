package com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.databinding.ItemFotoAgunanBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.ListFoto;
import com.application.bris.ikurma_nos_gadai.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;

import java.util.List;

public class FotoAgunanAdapter extends RecyclerView.Adapter<FotoAgunanAdapter.MenuViewHolder> {
    private List<ListFoto> data;
    private Context context;
    private ItemFotoAgunanBinding binding;

    public FotoAgunanAdapter(Context context, List<ListFoto> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public FotoAgunanAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemFotoAgunanBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new FotoAgunanAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoAgunanAdapter.MenuViewHolder holder, final int position) {
        final ListFoto datas = data.get(position);

        holder.tvLabelFoto.setText(datas.getDescription());

        Bitmap decodedByte;
        if(datas.getImage()!=null&&!datas.getImage().isEmpty()){
             AppUtil.convertBase64ToImage(datas.getImage(),holder.imgFoto);
            holder.imgFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogPreviewPhoto.display(((AppCompatActivity) context).getSupportFragmentManager(), "Preview Foto", ((BitmapDrawable)holder.imgFoto.getDrawable()).getBitmap());
                }
            });
        }
        else{
            //nothing for now
//            holder.rlAgunan.setVisibility(View.GONE);
        }


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

        ImageView imgFoto;
        TextView tvLabelFoto;
        RelativeLayout rlAgunan;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvLabelFoto=binding.tvLabelFoto;
            imgFoto=binding.imgAgunan;
            rlAgunan=binding.rlAgunan;
        }

    }

}