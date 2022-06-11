package com.rkassociates.HistoryList;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.HistoryList.ApiCalls.HistoryListInterface;
import com.rkassociates.HistoryList.ApiCalls.HistoryListResponse;
import com.rkassociates.HistoryList.ApiCalls.HistoryListResult;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryListPageActivity extends AppCompatActivity {


    private ProgressDialog progressdialog;
    private HistoryListAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView emptyIv,backIv;

    String executiveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list_page);

        initData();
        getListOfCompletedData();

    }

    private void initData() {

        recyclerView = findViewById(R.id.history_list_recycler);
        backIv = findViewById(R.id.history_page_back_btn_iv);
        emptyIv = findViewById(R.id.empty_recycler_iv);

        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        progressdialog = new ProgressDialog(HistoryListPageActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);


    }

    private void getListOfCompletedData() {

        Log.d("getListOfCompletedData","executive_id: "+executiveId);

        progressdialog.show();
        HistoryListInterface historyListInterface = ApiClient.getRetrofitInstance().create(HistoryListInterface.class);
        Call<HistoryListResponse> call = historyListInterface.getHistoryResponse(executiveId);
        call.enqueue(new Callback<HistoryListResponse>() {
            @Override
            public void onResponse(Call<HistoryListResponse> call, Response<HistoryListResponse> response) {

                try {
                    Log.d("getHistoryResponse", "onResponse: "+response.body());
                    if (response.body().getStatus().equals("success")) {
//                        list
                        List<HistoryListResult> list = response.body().getData();
                        Collections.reverse(list);
                        Log.d("Array", "onResponse: "+ Arrays.toString(list.toArray()));
                        adapter = new HistoryListAdapter(HistoryListPageActivity.this,list);
                        recyclerView.setAdapter(adapter);
/*
                        if (list.size() == 0) {
                            emptyIv.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }else{
                            emptyIv.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }*/


                        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onChanged() {
                                super.onChanged();
                                checkEmpty();
                            }

                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);
                                checkEmpty();
                            }

                            @Override
                            public void onItemRangeRemoved(int positionStart, int itemCount) {
                                super.onItemRangeRemoved(positionStart, itemCount);
                                checkEmpty();
                            }

                            void checkEmpty() {
                                emptyIv.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
                                recyclerView.setVisibility(adapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
                            }
                        });
                    }else{
                        snackBarMsg("Message: "+response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e("History List","Exception: "+e.getMessage() );
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<HistoryListResponse> call, Throwable t) {

                Log.e("History List", "onFailure: "+t.getMessage());
                progressdialog.dismiss();
            }
        });

    }

    private void snackBarMsg(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

}