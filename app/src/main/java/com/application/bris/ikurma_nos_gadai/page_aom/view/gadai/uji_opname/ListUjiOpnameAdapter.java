package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.Error;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListOpname;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUjiOpnameAdapter extends RecyclerView.Adapter<ListUjiOpnameAdapter.MenuViewHolder>{
    private List<ListOpname> data;
    private Context context;
    private ItemListUjiOpnameBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public ListUjiOpnameAdapter (Context context, List<ListOpname> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public ListUjiOpnameAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListUjiOpnameBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new ListUjiOpnameAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListUjiOpnameAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.tvTglOpname.setText(data.get(position).getTanggalAksesBrankas());
        onClicks(position,holder);
    }

    private void onClicks(int currentPosition,@NonNull ListUjiOpnameAdapter.MenuViewHolder holder){

        holder.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject obj1 = new JsonObject();
                obj1.addProperty("UserSubmit", "110145101");
                obj1.addProperty("ReffNoAktifitas", data.get(currentPosition).getKodeRequest());
                obj1.addProperty("DescriptionAktifitas", "Opname "+ data.get(currentPosition).getTanggalAksesBrankas());
                obj1.addProperty("Action", "MULAI/SELESAI");
                ReqListGadai req = new ReqListGadai();
                req.setkchannel("Mobile");
                req.setdata(obj1);
                Call<ParseResponse> call = apiClientAdapter.getApiInterface().UpdateIsiLaci(req);
                call.enqueue(new Callback<ParseResponse>() {
                    @Override
                    public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                        try {
                            if(response.isSuccessful()){
                                if(response.body().getStatus().equalsIgnoreCase("00")){
                                    AppUtil.notifsuccess(context,view.findViewById(android.R.id.content),"Data Telah Berubah");
                                }
                                else{
                                    AppUtil.notiferror(context, view.findViewById(android.R.id.content), response.body().getMessage());
                                }
                            }
                            else{
                                Error error = ParseResponseError.confirmEror(response.errorBody());
                                AppUtil.notiferror(context, view.findViewById(android.R.id.content), error.getMessage());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ParseResponse> call, Throwable t) {
                        AppUtil.notiferror(context, view.findViewById(android.R.id.content), context.getString(R.string.txt_connection_failure));
                    }
                });
                if (holder.btnCapture.getText() == "MULAI"){
                    holder.btnCapture.setText("SELESAI");
                    Intent intent=new Intent(context , ListBrankasActivity.class);
                    intent.putExtra("kodeCabang",data.get(currentPosition).getKodeCabang());
                    intent.putExtra("ReffNoAktifitas",data.get(currentPosition).getKodeRequest());
                    context.startActivity(intent);
                }else{
                    holder.btnCapture.setText("MULAI");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTglOpname;
        Button btnCapture;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvTglOpname =binding.tvTglOpname;
            btnCapture = binding.btnListOpname;
        }

    }
}
