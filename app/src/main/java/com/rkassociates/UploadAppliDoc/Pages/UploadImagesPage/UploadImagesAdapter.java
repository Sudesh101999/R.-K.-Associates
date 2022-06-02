package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.rkassociates.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class UploadImagesAdapter extends RecyclerView.Adapter<UploadImagesAdapter.UploadImagesViewHolder>{
    private Context context;
    private ArrayList<Uri> uri;

    public UploadImagesAdapter(Context context, ArrayList<Uri> uri) {
        this.context = context;
        this.uri = uri;
    }

    @NonNull
    @Override
    public UploadImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_iv, parent, false);
        return new UploadImagesViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadImagesViewHolder holder, int position) {

        File file = new File(uri.get(position).getPath());
        Picasso.get().load(file)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImageRecyclerView);
    }

    @Override
    public int getItemCount() {
        return uri.size();
    }

    public class UploadImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageRecyclerView,deleteIv;
        UploadImagesAdapter adapter;
        public UploadImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageRecyclerView = itemView.findViewById(R.id.recycler_iv);
            deleteIv = itemView.findViewById(R.id.imageView_delete);

            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAlertDialog(adapter,getAdapterPosition());
                }
            });
        }
        public UploadImagesViewHolder linkAdapter(UploadImagesAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }

    private void openAlertDialog(UploadImagesAdapter adapter, int adapterPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                adapter.uri.remove(adapterPosition);
                adapter.notifyItemRemoved(adapterPosition);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
