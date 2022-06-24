package com.application.bris.ikurma_nos_gadai.page_aom.view.resubmit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqBranch;
import com.application.bris.ikurma_nos_gadai.api.model.request.gadai.ReqRetryGadaiGagal;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListGadaiBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemResubmitBinding;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataGadai;
import com.application.bris.ikurma_nos_gadai.model.gadai.DataResubmit;
import com.application.bris.ikurma_nos_gadai.page_putusan_gadai.detail_putusan_gadai.DetailPutusanGadaiActivity;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResubmitAdapter extends RecyclerView.Adapter<ResubmitAdapter.MenuViewHolder> implements Filterable {
    private List<DataResubmit> data;
    private List<DataResubmit> datafiltered;
    private Context context;
    private ItemResubmitBinding binding;
    AppPreferences appPreferences;
    ApiClientAdapter apiClientAdapter;

    public ResubmitAdapter(Context context, List< DataResubmit> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public ResubmitAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemResubmitBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        appPreferences=new AppPreferences(context);
        apiClientAdapter=new ApiClientAdapter(context);
        return new ResubmitAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResubmitAdapter.MenuViewHolder holder, final int position) {
        final DataResubmit datas = datafiltered.get(position);

        holder.tvNamaCabang.setText(appPreferences.getNamaKantor());
        holder.tvNamaGadai.setText(datas.getNamaNasabah());
        holder.tvNoAplikasi.setText(datas.getNoAplikasi());
        holder.tvNoLd.setText(datas.getNoLoan());
        holder.tvTanggalPencairan.setText(datas.getTanggalPencairan());
        holder.tvOutstanding.setText(AppUtil.parseRupiah(datas.getoSPembiayaan()));
        holder.tvJenisTransaksi.setText(datas.getProduk());
        holder.tvJenisGagal.setText(datas.getState());

        holder.btnResubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, DetailPutusanGadaiActivity.class);
//                intent.putExtra("NoAplikasi",datas.getNoAplikasi());
//                context.startActivity(intent);
                doResubmit(holder,datas.getNoAplikasi(),datas.getActivityID(),position);
//                Toast.makeText(context, "Dor pura pura resubmit", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void doResubmit(ResubmitAdapter.MenuViewHolder holder, String noAplikasi, Long activityId,int position) {
        //  dataUser = getListUser();
       holder.loading.setVisibility(View.VISIBLE);
        ReqRetryGadaiGagal req=new ReqRetryGadaiGagal();
        req.setNoAplikasi(noAplikasi);
        req.setWaitActivityId(activityId);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().retryGadaiGagal(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                holder.loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    String listDataString;
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Toast.makeText(context, "Retry Sukses", Toast.LENGTH_SHORT).show();
                        datafiltered.remove(position);
                        notifyDataSetChanged();
                        ((ResubmitActivity)context).doRefresh();
                    }
                    else  {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                holder.loading.setVisibility(View.GONE);
                Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(datafiltered==null){
            return 0;
        }
        else{
            return datafiltered.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = data;
                } else {
                    List<DataResubmit> filteredList = new ArrayList<>();
                    for (DataResubmit row : data){
                        if(row.getNamaNasabah().toLowerCase().contains(charString.toLowerCase()) || row.getNoAplikasi().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataResubmit>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaGadai,tvNoAplikasi,tvNoLd,tvTanggalPencairan,tvOutstanding,tvJenisTransaksi,tvJenisGagal,tvNamaCabang;
        RelativeLayout loading;
        Button btnResubmit;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNamaCabang=binding.tvCabang;
            tvNamaGadai=binding.tvNamaNasabah;
            tvNoAplikasi=binding.tvNomorApplikasi;
            tvNoLd=binding.tvNomorLd;
            tvTanggalPencairan=binding.tvTanggalPencairan;
            tvOutstanding=binding.tvOutstanding;
            tvJenisTransaksi=binding.tvJenisTransaksi;
            tvJenisGagal=binding.tvJenisGagal;
            btnResubmit=binding.btnResubmit;
            loading=binding.loading.progressbarLoading;
        }

    }

}