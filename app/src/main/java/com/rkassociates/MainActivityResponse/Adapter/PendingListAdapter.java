package com.rkassociates.MainActivityResponse.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rkassociates.MainActivityResponse.ApiCalls.PendingListResult;
import com.rkassociates.R;
import com.rkassociates.UploadAppliDoc.DocumentationActivity;

import java.util.List;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.PendingListViewHolder> {

    private List<PendingListResult> list;
    private final Context context;

    public PendingListAdapter(List<PendingListResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingListAdapter.PendingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PendingListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pending_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingListAdapter.PendingListViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(holder.pendingListLL.getContext(), android.R.anim.slide_in_left);

        PendingListResult model = list.get(position);
        holder.idTv.setText(model.getAddDataId());
        holder.aplcNameTc.setText(model.getName());
        holder.pendingTv.setText(TextUtils.join(", ", list.get(position).getPending()));

        holder.pendingListLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked Id: "+model.getAddDataId(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, DocumentationActivity.class);
                i.putExtra("activityFor","Pending");
                i.putExtra("addDataId",model.getAddDataId());
                i.putExtra("aplcName",model.getName());
                i.putExtra("pendingList",TextUtils.join(", ", list.get(position).getPending()));
                context.startActivity(i);
            }
        });

        holder.pendingListLL.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PendingListViewHolder extends RecyclerView.ViewHolder {
        private final CardView pendingListLL;
        private final TextView idTv,aplcNameTc,pendingTv;
        public PendingListViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingListLL = itemView.findViewById(R.id.pending_list_ll);
            idTv = itemView.findViewById(R.id.list_item_id_tv);
            aplcNameTc = itemView.findViewById(R.id.list_item_aplc_name_tv);
            pendingTv = itemView.findViewById(R.id.list_item_pending_tv);
        }
    }
}
