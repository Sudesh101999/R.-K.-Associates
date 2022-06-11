package com.rkassociates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rkassociates.AdminClass.AdminPage;
import com.rkassociates.Auth.LoginActivity;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.DocPicker.DocPickerActivity;
import com.rkassociates.FileUtils.FileUtils;
import com.rkassociates.HistoryList.HistoryListPageActivity;
import com.rkassociates.MainActivityResponse.Adapter.PendingListAdapter;
import com.rkassociates.MainActivityResponse.ApiCalls.PendingListInterface;
import com.rkassociates.MainActivityResponse.ApiCalls.PendingListResponse;
import com.rkassociates.MainActivityResponse.ApiCalls.PendingListResult;
import com.rkassociates.MainActivityResponse.MainActivityModel;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private CircleImageView profileIv;
    private TextView nameTv;
    private LinearLayout docAdderLL, historyLL, adminLL;
    private SearchView searchView;
    private CardView docPickerCard, docVeriCard;
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivity";
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    File file;
    File compressedImageFile;

    StorageReference storageReference;

    String userId ;
    //assets verif
//    private ProgressDialog progressdialog;

    private ImageView logoutIv,emptyIv;
    private ProgressBar progressBar;

    private RecyclerView historyListRecycler;
    private PendingListAdapter adapter;
    private List<MainActivityModel> model = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        getDataFromDatabase();

        // SetOnRefreshListener on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getDataFromDatabase();
            }
        });

//        String url = SharedPrefAuth.getInstance(getApplicationContext()).getProfileImage(getApplicationContext());
//        Glide.with(getApplicationContext()).load(url).into(profileIv);

        onclickOperation();

        checkUpdate();
    }

    private void onclickOperation() {
        profileIv.setOnClickListener(MainActivity.this);
        docPickerCard.setOnClickListener(MainActivity.this);

        docAdderLL.setOnClickListener(MainActivity.this);
        historyLL.setOnClickListener(MainActivity.this);
        adminLL.setOnClickListener(MainActivity.this);

        logoutIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutDialog();
            }
        });
    }

    private void logoutDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPrefAuth.getInstance(getApplicationContext()).setLoginStatus(MainActivity.this,0);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        //finish();
                    }
                }).show();
    }
    private void initData() {
        logoutIv = findViewById(R.id.logout_iv);
        emptyIv = findViewById(R.id.empty_recycler_main_iv);
        profileIv = findViewById(R.id.imageView);
        nameTv = findViewById(R.id.user_name_tv);
        docPickerCard = findViewById(R.id.card_doc_add_data);
        docAdderLL = findViewById(R.id.layout_doc_add_data);
        historyLL = findViewById(R.id.layout_doc_history);
        adminLL = findViewById(R.id.layout_admin);
        docVeriCard = findViewById(R.id.card_history);
        // Getting reference of swipeRefreshLayout and recyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.main_pending_recycler);

        storageReference = FirebaseStorage.getInstance().getReference();

        historyListRecycler = findViewById(R.id.history_list_recycler);

        progressBar = findViewById(R.id.pending_progress_bar);

//        progressdialog = new ProgressDialog(MainActivity.this);
//        progressdialog.setMessage("Please Wait....");
//        progressdialog.setCanceledOnTouchOutside(false);

        userId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        StorageReference profileRef = storageReference.child(userId + ".jpg");
        Log.e( "initData: ","UserId: "+userId);
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).placeholder(R.drawable.progress_animation).into(profileIv);
            }
        });

        String executiveName = SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext());
        nameTv.setText(executiveName);
    }

    private boolean checkPermissions() {

        int permissionState3 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        return
                permissionState3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale3 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (shouldProvideRationale3) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    },
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                if (checkPermissions()) {
                    showAll(view);
                } else {
                    requestPermissions();
                }
                break;
            case R.id.layout_doc_add_data:
                startActivity(new Intent(MainActivity.this, DocPickerActivity.class));
//                finish();
                break;
            case R.id.card_admin:
            case R.id.layout_admin:
            case R.id.layout_admin_tv:
                startActivity(new Intent(MainActivity.this, AdminPage.class));
                break;
            case R.id.layout_doc_history:
                startActivity(new Intent(MainActivity.this, HistoryListPageActivity.class));
                break;

        }
    }

    public void showAll(View view) {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageUri = data.getData();
//            profileIv.setImageURI(imageUri);

            try {
                file = new File(Objects.requireNonNull(FileUtils.getPath(imageUri, getApplicationContext())));
                compressedImageFile = new Compressor(this).compressToFile(file);
//                uploadImageToServer();

                Uri selectedImage = data.getData();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;//returning null for below statement
                Bitmap bitmap = BitmapFactory.decodeFile(selectedImage.toString(), options);
                saveImage(bitmap);

                uploadToFirebase(selectedImage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error in uploading image", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadToFirebase(Uri selectedImage) {
        //upload image to firebase storage

        StorageReference fileRef = storageReference.child(userId + ".jpg");
        fileRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileIv);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void saveImage(Bitmap bitmap) {
        OutputStream output;
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath()
                + "/Rk/profile");
        dir.mkdirs();

        // Create a name for the saved image
        File file = new File(dir, "rk_profile" + ".jpg");
        try {

            output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private void getDataFromDatabase() {
        progressBar.setVisibility(View.VISIBLE);
        PendingListInterface paindingListInterface = ApiClient.getRetrofitInstance().create(PendingListInterface.class);
        Call<PendingListResponse> call = paindingListInterface.historyData(userId);
        call.enqueue(new Callback<PendingListResponse>() {
            @Override
            public void onResponse(Call<PendingListResponse> call, Response<PendingListResponse> response) {
                try {
                    if (response.body().status.equals("success") && response.body().getData().size() > 0){
                        List<PendingListResult> list = response.body().getData();
                        Collections.reverse(list);
                        adapter = new PendingListAdapter(list,MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }else{
                        snackBarMsg("message: "+response.body().getMessage());
                    }

                }catch (Exception e){
                    snackBarMsg("Exception: "+e.getMessage());
                }
                progressBar.setVisibility(View.GONE);

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
            }

            @Override
            public void onFailure(Call<PendingListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                snackBarMsg("OnFailure: "+t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });


    }
    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void checkUpdate() {
        // Creates instance of the manager.
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        Log.i("HomeAct app update", "" + appUpdateInfoTask);
// Checks whether the platform allows the specified type of update,
// and checks the update priority.
        appUpdateInfoTask.addOnSuccessListener(new com.google.android.play.core.tasks.OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                Log.i("HomeAct app update", "" + result);
                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // Request an immediate update.
                    Log.d("Home ACt", "Update available");
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                result,
                                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                AppUpdateType.IMMEDIATE,
                                // The current activity making the update request.
                                MainActivity.this,
                                // Include a request code to later monitor this update request.
                                143);
                    } catch (Exception e) {
                        Log.e("error in update", e.getMessage());
                    }
                } else
                    Log.i("Home Act", "No update found!");
            }
        });


    }

}