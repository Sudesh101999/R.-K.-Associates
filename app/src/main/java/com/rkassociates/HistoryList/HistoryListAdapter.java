package com.rkassociates.HistoryList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rkassociates.HistoryDetails.HistoryDetailsActivity;
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
//        HistoryListResult listResult = results.get(position);
        holder.idTv.setText(results.get(position).getAddDataId());
        holder.aplcNameTv.setText(results.get(position).getName());
        holder.bankNameTv.setText(results.get(position).getBankName());
        holder.branchNameTv.setText(results.get(position).getBranchName());

        holder.historyListLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked: "+results.get(position).getAddDataId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.viewDetailsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked: "+results.get(position).getAddDataId()+" for view details.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, HistoryDetailsActivity.class);
                intent.putExtra("addDataId",results.get(position).getAddDataId());
                intent.putExtra("ApplicantName",results.get(position).getName());
                context.startActivities(new Intent[]{intent});
            }
        });

        holder.sendMailIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked: "+results.get(position).getAddDataId()+" for generate mail.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder {
        private final CardView historyListLL;
        private final ImageView viewDetailsIv,sendMailIv;
        private final TextView idTv,aplcNameTv,bankNameTv,branchNameTv;
        public HistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            historyListLL = itemView.findViewById(R.id.history_list_ll);
            idTv = itemView.findViewById(R.id.list_item_history_aplc_id_tv);
            viewDetailsIv = itemView.findViewById(R.id.list_item_history_view_details_iv);
            sendMailIv = itemView.findViewById(R.id.list_item_history_mail_details_iv);
            aplcNameTv = itemView.findViewById(R.id.list_item_history_aplc_name_tv);
            bankNameTv = itemView.findViewById(R.id.list_item_history_bank_tv);
            branchNameTv = itemView.findViewById(R.id.list_item_history_branch_tv);
        }
    }
}
