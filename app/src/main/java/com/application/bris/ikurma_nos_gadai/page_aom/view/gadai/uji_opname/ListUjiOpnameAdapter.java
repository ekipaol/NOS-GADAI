package com.application.bris.ikurma_nos_gadai.page_aom.view.gadai.uji_opname;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_gadai.R;
import com.application.bris.ikurma_nos_gadai.api.model.Error;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponse;
import com.application.bris.ikurma_nos_gadai.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_gadai.api.model.request.ReqListGadai;
import com.application.bris.ikurma_nos_gadai.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_gadai.database.AppPreferences;
import com.application.bris.ikurma_nos_gadai.databinding.ActivityListUjiOpnameBinding;
import com.application.bris.ikurma_nos_gadai.databinding.ItemListUjiOpnameBinding;
import com.application.bris.ikurma_nos_gadai.page_aom.model.ListOpname;
import com.application.bris.ikurma_nos_gadai.util.AppUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUjiOpnameAdapter extends RecyclerView.Adapter<ListUjiOpnameAdapter.MenuViewHolder> implements Filterable {
    private List<ListOpname> data;
    private List<ListOpname> datafiltered;
    private Context context;
    private ItemListUjiOpnameBinding binding;
    private ActivityListUjiOpnameBinding binding2;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String namaCabang;
    public ListUjiOpnameAdapter(Context context, List<ListOpname> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = mdata;
    }

    @NonNull
    @Override
    public ListUjiOpnameAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListUjiOpnameBinding.inflate(layoutInflater, parent, false);
        binding2 = ActivityListUjiOpnameBinding.inflate(layoutInflater, parent, false);
        View view2 = binding2.getRoot();
        View view = binding.getRoot();
        apiClientAdapter = new ApiClientAdapter(context);
        appPreferences = new AppPreferences(context);
        namaCabang=appPreferences.getNamaKantor();
        return new ListUjiOpnameAdapter.MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListUjiOpnameAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final ListOpname datas = datafiltered.get(position);
        holder.tvTglOpname.setText(datas.getTanggalAksesBrankas());
        onClicks(position, holder);
    }

    private void onClicks(int currentPosition, @NonNull ListUjiOpnameAdapter.MenuViewHolder holder) {
        holder.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.btnCapture.getText().toString().equalsIgnoreCase("MULAI")) {
                    sendDataUji(currentPosition, holder, view);
                } else {
                    DialogConfirmation(context, "Konfirmasi", "Apakah anda yakin ?", currentPosition, holder);
                }
            }
        });
    }

    private void sendDataUji(int currentPosition, @NonNull ListUjiOpnameAdapter.MenuViewHolder holder, View view) {
        JsonObject obj1 = new JsonObject();
        binding2.loading.progressbarLoading.setVisibility(View.VISIBLE);
        if (holder.btnCapture.getText().toString().equalsIgnoreCase("MULAI")) {
            obj1.addProperty("UserSubmit", appPreferences.getNik());
            obj1.addProperty("ReffNoAktifitas", data.get(currentPosition).getKodeRequest());
            obj1.addProperty("KodeCabang", appPreferences.getKodeCabang());
//            obj1.addProperty("KodeCabang", "ID001211");
            obj1.addProperty("KodeAgunan", "");
            obj1.addProperty("Status", "");
            obj1.addProperty("Action", "MULAI");
        } else {
            obj1.addProperty("UserSubmit", appPreferences.getNik());
            obj1.addProperty("ReffNoAktifitas", data.get(currentPosition).getKodeRequest());
            obj1.addProperty("KodeCabang", appPreferences.getKodeCabang());
//            obj1.addProperty("KodeCabang", "ID001211");
            obj1.addProperty("KodeAgunan", "");
            obj1.addProperty("Status", "");
            obj1.addProperty("Action", "SELESAI");
        }
        ReqListGadai req = new ReqListGadai();
        req.setkchannel("Mobile");
        req.setdata(obj1);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().UpdateIsiLaci(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding2.loading.progressbarLoading.setVisibility(View.GONE);
//                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                        if (holder.btnCapture.getText().toString().equalsIgnoreCase("MULAI")) {
                            Intent intent = new Intent(context, ListBrankasActivity.class);
                            intent.putExtra("ReffNoAktifitas", data.get(currentPosition).getKodeRequest());
                            intent.putExtra("kodeCabang", data.get(currentPosition).getKodeCabang());
                            intent.putExtra("namaCabang", data.get(currentPosition).getNamaCabang());
                            context.startActivity(intent);
                            holder.btnCapture.setText("SELESAI");
                        } else {
                            holder.btnCapture.setText("MULAI");
                        }
//                        } else {
//                            AppUtil.notiferror(context, view.findViewById(android.R.id.content), response.body().getMessage());
//                        }

                    } else {
                        binding2.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(context, view.findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding2.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(context, view.findViewById(android.R.id.content), context.getString(R.string.txt_connection_failure));
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
                    List<ListOpname> filteredList = new ArrayList<>();
                    for (ListOpname row : data) {
                        if (row.getTanggalAksesBrankas().toLowerCase().contains(charString.toLowerCase())) {
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
                datafiltered = (ArrayList<ListOpname>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTglOpname;
        Button btnCapture;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvTglOpname = binding.tvTglOpname;
            btnCapture = binding.btnListOpname;
        }

    }


    public void DialogConfirmation(Context context, String header, String message, int currentPosition, ListUjiOpnameAdapter.MenuViewHolder holder) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_confirm, null);
        Button btn_send = (Button) view.findViewById(R.id.btn_send);
        Button btn_reject = (Button) view.findViewById(R.id.btn_reject);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        btn_send.setOnClickListener(click);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataUji(currentPosition, holder, view);
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void animateDialog(Dialog dialog) {
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppTheme_Slide;
    }
}
