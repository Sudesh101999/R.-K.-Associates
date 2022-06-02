package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.MultiImageInterface;
import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.MultiImageResponse;
import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData.UploadImageReadResponse;
import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData.UploadImageTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImagesActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 12;
    private static final int SLECT_IMAGE_CODE = 11;
    private ProgressDialog progressdialog;
    private ImageView emptyRecyclerIv, uploadBackIv;
    private Button selectImageBtn, submitBtn;
    ArrayList<Uri> uriFile = new ArrayList<>();
    RecyclerView recyclerView;
    UploadImagesAdapter adapter;
    private String aplcIdStr, executiveIdStr;

    private String activityFor,addDataIdIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);

        initData();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            addDataIdIntentStr= extras.getString("addDataIdIntentStr");
            //The key argument here must match that used in the other activity

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId",addDataIdIntentStr);
                getDataFromDatabase();
            }
        }

        uploadBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pictures: "), SLECT_IMAGE_CODE);
                } else {
                    requestPermissionsPage();
                }

//                selectImage(RecyclerImagesListActivity.this);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToDatabase();
                Log.d("TAG", "onClick: imageSize" + uriFile.size());
            }
        });

        adapter = new UploadImagesAdapter(UploadImagesActivity.this, uriFile);
        recyclerView.setAdapter(adapter);

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
                emptyRecyclerIv.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
                recyclerView.setVisibility(adapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            }
        });

    }

    private void getDataFromDatabase() {

        progressdialog.show();

        MultiImageInterface multiImageInterface = ApiClient.getRetrofitInstance().create(MultiImageInterface.class);
        Call<UploadImageReadResponse> call = multiImageInterface.readImagesUploaded(addDataIdIntentStr,executiveIdStr);
        call.enqueue(new Callback<UploadImageReadResponse>() {
            @Override
            public void onResponse(Call<UploadImageReadResponse> call, Response<UploadImageReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Log.d("Profile Page Response",response.body().getMessage());

                        for (UploadImageTable i : response.body().getData().getUploadImageTable()){
                            uriFile.add(Uri.parse(i.getPicture()));
                        }
                        adapter.notifyDataSetChanged();

                    }else{
                        Log.d("Profile Page Data",response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<UploadImageReadResponse> call, Throwable t) {
                Log.e("Profile OnFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private boolean checkPermissions() {

        int permissionState3 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        int permissionState5 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionState6 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        return
                permissionState3 == PackageManager.PERMISSION_GRANTED &&
                        permissionState5 == PackageManager.PERMISSION_GRANTED &&
                        permissionState6 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionsPage() {

        boolean shouldProvideRationale3 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE);

        boolean shouldProvideRationale5 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean shouldProvideRationale6 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA);

        // Provide an additional rationale to the img_user. This would happen if the img_user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale3 || shouldProvideRationale5 || shouldProvideRationale6) {
            ActivityCompat.requestPermissions(UploadImagesActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {

            ActivityCompat.requestPermissions(UploadImagesActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    },
                    PERMISSION_REQUEST_CODE);
        }

    }


    private void initData() {

        uploadBackIv = findViewById(R.id.images_page_back_btn_iv);
        emptyRecyclerIv = findViewById(R.id.empty_recycler_iv);
        selectImageBtn = findViewById(R.id.img_select_btn);
        submitBtn = findViewById(R.id.img_submit_btn);
        recyclerView = findViewById(R.id.image_recycler);

        aplcIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getAplcId(getApplicationContext());
        executiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        progressdialog = new ProgressDialog(UploadImagesActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == SLECT_IMAGE_CODE) {
                if (resultCode == RESULT_OK && data != null) {
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        for (int i = 0; i < count; i++) {
                            Log.e("TAG", "onActivityResult: " + "Images selected");
                            Uri image = data.getClipData().getItemAt(i).getUri();
                            String imagePath = FileUtil.getPath(UploadImagesActivity.this, image);
                            uriFile.add(Uri.parse(imagePath));
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Uri uriImg = data.getData();
                        String imagePath = FileUtil.getPath(UploadImagesActivity.this, uriImg);
                        uriFile.add(Uri.parse(imagePath));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }

    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {

        File file = new File(fileUri.getPath());
        Log.i("here is error", file.getAbsolutePath());
        // create RequestBody instance from file

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);


    }

    private void insertToDatabase() {
        progressdialog.show();
        List<MultipartBody.Part> list = new ArrayList<>();
        for (Uri uri : uriFile) {
            Log.i("picture[]", uri.getPath());
            list.add(prepareFilePart("picture[]", uri));
        }
        RequestBody addDataId = RequestBody.create(MediaType.parse("text/plain"), addDataIdIntentStr);
        RequestBody executiveId = RequestBody.create(MediaType.parse("text/plain"), executiveIdStr);

        MultiImageInterface multiImageInterface = ApiClient.getRetrofitInstance().create(MultiImageInterface.class);
        Call<ArrayList<MultiImageResponse>> call = multiImageInterface.uploadNewsFeedImages(executiveId,addDataId, list);
        call.enqueue(new Callback<ArrayList<MultiImageResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<MultiImageResponse>> call, Response<ArrayList<MultiImageResponse>> response) {
                try {
                    if (response.body().size() > 0) {
                        Log.d("response", "Data inserted");
                        Toast.makeText(UploadImagesActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setUploadImage(true);
                        UploadImagesActivity.super.onBackPressed();
                    } else {
                        snackBarMsg("getting some error...!!!");
                    }
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
                progressdialog.dismiss();
            }
            @Override
            public void onFailure(Call<ArrayList<MultiImageResponse>> call, Throwable t) {
                Log.e("OnFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                UploadImagesActivity.super.onBackPressed();
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