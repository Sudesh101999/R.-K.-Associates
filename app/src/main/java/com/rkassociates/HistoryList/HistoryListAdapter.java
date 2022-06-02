package com.rkassociates.HistoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rkassociates.HistoryList.ApiCalls.HistoryListResult;
import com.rkassociates.R;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder> {

    private Context context;
    private List<HistoryListResult> results;

    public HistoryListAdapter(Context context, List<HistoryListResult> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        HistoryListResult listResult = results.get(position);
        holder.idTv.setText(listResult.getAdd_data_id());
        holder.aplcNameTv.setText(listResult.getName());
        holder.bankNameTv.setText(listResult.getBank_name());
        holder.branchNameTv.setText(listResult.getBranch_name());

        holder.historyListLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked: "+listResult.getAdd_data_id(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder {
        private final CardView historyListLL;
        private final TextView idTv,aplcNameTv,bankNameTv,branchNameTv;
        public HistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            historyListLL = itemView.findViewById(R.id.pending_list_ll);
            idTv = itemView.findViewById(R.id.list_item_id_tv);
            aplcNameTv = itemView.findViewById(R.id.list_item_aplc_name_tv);
            bankNameTv = itemView.findViewById(R.id.list_item_history_bank_tv);
            branchNameTv = itemView.findViewById(R.id.list_item_history_branch_tv);
        }
    }
}
