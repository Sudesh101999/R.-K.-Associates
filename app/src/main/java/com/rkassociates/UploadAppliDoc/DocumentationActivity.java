package com.rkassociates.UploadAppliDoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.AssetsVerifActivity;
import com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.BusinessVerifActivity;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.CurrentResidPropActivity;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.CurrentResidenceVerifActivity;
import com.rkassociates.UploadAppliDoc.Pages.DealerCarActivity;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailsOfITRActivity;
import com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ItrKycVerifActivity;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ProfilePageActivity;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.RegisteredPageActivity;
import com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.TeleVerifOfReferActivity;
import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.UploadImagesActivity;


public class DocumentationActivity extends AppCompatActivity {

    CardView dealerCard;

    private ImageView backIv;
    private TextView empName;

    RelativeLayout registeredUserRL,profileRL,businessRL,itrKycVerifRL,currentResidVeriRL,teleVerifRL,currentResidPropRL,
            detailsOfItrRL,uploadImgRL,assestRL,dealarCarRL,submitRL;

    String loanTypeStr,empIdStr,clientIdStr;
    boolean registeredUserDon,profilePageDone,businessPageDone,itrKycVerifPageDone,currentResidVerifPageDone
            ,teleVerifOfRefefPageDone,currentResidencePropPageDone,detailsOfItrPageDone,uploadImgPageDone,
            assetsVerifPageDone,dealarPageDone;

    private String activityFor="";
    private String addDataIdIntentStr,aplcNameIntentStr,pendingListStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doc);


        getDataFromSharedPref();
        initData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            addDataIdIntentStr = extras.getString("addDataId");
            //The key argument here must match that used in the other activity

            if (activityFor.equals("Pending")) {
                aplcNameIntentStr= extras.getString("aplcName");
                pendingListStr= extras.getString("pendingList");
                Log.d("pendingList",pendingListStr);
                setCompletedLayoutborder(pendingListStr);
            }
        }

        checkCompletedPages();

//        if (loanTypeStr.equals("Car Loan"))
//            dealerCard.setVisibility(View.VISIBLE);
//        else
//            dealerCard.setVisibility(View.GONE);

        onClickOperation();

    }

    private void setCompletedLayoutborder(String pendingListStr) {
        String[] items = pendingListStr.split(", ");
        for (String item : items)
        {
            Log.d("TAG", "setCompletedLayoutborder: "+item);
            if (item.equals("User Registration Co-Applicanat Pending") || item.equals("User Registration Pending")) {
                changeBackgroundWithRed(registeredUserRL);
            }
//            else
//                changeBackground(registeredUserRL);
            //profile Page
            if (item.equals("Profile Remark Pending")) {
                changeBackgroundWithRed(profileRL);
            }
//            else
//                changeBackground(profileRL);
            //business verif page
            if (item.equals("Business Verification Pending")) {
                changeBackgroundWithRed(businessRL);
            }
//            else
//                changeBackground(businessRL);
            //itr and kyc verif page
            if (item.equals("ITR KYC Verification Pending")) {
                changeBackgroundWithRed(itrKycVerifRL);
            }
//            else
//                changeBackground(itrKycVerifRL);
            //current residence verif page
            if (item.equals("Current Residence Property Verification Pending")) {
                changeBackgroundWithRed(currentResidVeriRL);
            }
//            else
//                changeBackground(currentResidVeriRL);
            //tele verifi of reference
            if (item.equals("Current tele_verification_reference_table  Pending")) {
                changeBackgroundWithRed(teleVerifRL);
            }
//            else
//                changeBackground(teleVerifRL);
            //current residence property
            if (item.equals("Current Residence Property Pending")) {
                changeBackgroundWithRed(currentResidPropRL);
            }
//            else
//                changeBackground(currentResidPropRL);
            //details of itr
            if (item.equals("")) {
                changeBackgroundWithRed(detailsOfItrRL);
            }
//            else
//                changeBackground(detailsOfItrRL);
            //assets verification
            if (item.equals("Assets Verification Pending")) {
                changeBackgroundWithRed(assestRL);
            }
//            else
//                changeBackground(assestRL);
            //upload image
            if (item.equals("Upload Image Pending")) {
                changeBackgroundWithRed(uploadImgRL);
            }
//            else
//                changeBackground(uploadImgRL);

        }
    }

    private void onClickOperation() {

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open alert dialog
                openAlertDialog();
            }
        });
        registeredUserRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, RegisteredPageActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        profileRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, ProfilePageActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        businessRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, BusinessVerifActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        itrKycVerifRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, ItrKycVerifActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        currentResidVeriRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, CurrentResidenceVerifActivity.class);
                intentToAnotherActivity(intent);
            }
        });

        teleVerifRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, TeleVerifOfReferActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        currentResidPropRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, CurrentResidPropActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        detailsOfItrRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, DetailsOfITRActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        assestRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, AssetsVerifActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        uploadImgRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, UploadImagesActivity.class);
                intentToAnotherActivity(intent);
            }
        });
        dealarCarRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentationActivity.this, DealerCarActivity.class);
                intentToAnotherActivity(intent);
            }
        });

        submitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefDocuComplete.getInstance(getApplicationContext()).clearDocData();
                SharedPrefAuth.getInstance(getApplicationContext()).clearDocAuthData();
                DocumentationActivity.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromSharedPref();
        checkCompletedPages();
    }

    private void intentToAnotherActivity(Intent intent) {
        intent.putExtra("activityFor",activityFor);
        intent.putExtra("addDataIdIntentStr",addDataIdIntentStr);
        intent.putExtra("aplcNameIntentStr",aplcNameIntentStr);
        startActivity(intent);
    }

    private void checkCompletedPages() {
        //registered Page
        if (registeredUserDon){
            changeBackground(registeredUserRL);
        }
        //profile Page
        if (profilePageDone) {
            changeBackground(profileRL);
        }
        //business verif page
        if (businessPageDone) {
            changeBackground(businessRL);
        }
        //itr and kyc verif page
        if (itrKycVerifPageDone) {
            changeBackground(itrKycVerifRL);
        }
        //current residence verif page
        if (currentResidVerifPageDone) {
            changeBackground(currentResidVeriRL);
        }
        //tele verifi of reference
        if (teleVerifOfRefefPageDone) {
            changeBackground(teleVerifRL);
        }
        //current residence property
        if (currentResidencePropPageDone) {
            changeBackground(currentResidPropRL);
        }
        //details of itr
        if (detailsOfItrPageDone) {
            changeBackground(detailsOfItrRL);
        }
        //assets verification
        if (assetsVerifPageDone) {
            changeBackground(assestRL);
        }
        //upload image
        if (uploadImgPageDone) {
            changeBackground(uploadImgRL);
        }
        //Dealer car page
        if (dealarPageDone) {
            changeBackground(dealarCarRL);
        }
    }

    private void changeBackground(RelativeLayout rl) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            rl.setBackgroundDrawable(ContextCompat.getDrawable(DocumentationActivity.this, R.drawable.applicant_activitis_bg) );
        } else {
            rl.setBackground(ContextCompat.getDrawable(DocumentationActivity.this, R.drawable.applicant_activitis_bg));
        }
    }
    private void changeBackgroundWithRed(RelativeLayout rl) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            rl.setBackgroundDrawable(ContextCompat.getDrawable(DocumentationActivity.this, R.drawable.applc_list_layout_design) );
        } else {
            rl.setBackground(ContextCompat.getDrawable(DocumentationActivity.this, R.drawable.applc_list_layout_design));
        }
    }

    private void getDataFromSharedPref() {

        if (activityFor.equals("newData")) {
            registeredUserDon= SharedPrefDocuComplete.getInstance(getApplicationContext()).getRegistereduserData(getApplicationContext());
            profilePageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getProfileData(getApplicationContext());
            businessPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getBusiness(getApplicationContext());
            itrKycVerifPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getItrAndKycVerif(getApplicationContext());
            currentResidVerifPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getCurrentResidenceVerif(getApplicationContext());
            detailsOfItrPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getDetailsOfItr(getApplicationContext());
            assetsVerifPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getAssetsVerif(getApplicationContext());
            uploadImgPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getUploadImage(getApplicationContext());
            dealarPageDone=SharedPrefDocuComplete.getInstance(getApplicationContext()).getDealerCar(getApplicationContext());
        }

    }

    private void initData() {
        backIv = findViewById(R.id.upload_doc_back_btn_iv);
        empName = findViewById(R.id.upload_doc_user_name_tv);
        registeredUserRL = findViewById(R.id.layout_doc_registered_user);
        profileRL=findViewById(R.id.layout_doc_profile);
        businessRL=findViewById(R.id.layout_doc_business);
        itrKycVerifRL=findViewById(R.id.layout_doc_itr_kyc);
        currentResidVeriRL=findViewById(R.id.layout_doc_curr_residence_veri);
        teleVerifRL=findViewById(R.id.layout_doc_tele_verfi_refer);
        currentResidPropRL=findViewById(R.id.layout_doc_curr_residence_prop);
        detailsOfItrRL=findViewById(R.id.layout_doc_details_of_itr);
        uploadImgRL=findViewById(R.id.layout_doc_upload_img);
        assestRL = findViewById(R.id.layout_doc_assets_veri);
        dealarCarRL = findViewById(R.id.layout_doc_dealer_car);
        dealerCard = findViewById(R.id.card_doc_dealer_car);

        submitRL = findViewById(R.id.layout_doc_submit);
    }

    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                SharedPrefDocuComplete.getInstance(getApplicationContext()).clearDocData();
                SharedPrefAuth.getInstance(getApplicationContext()).clearDocAuthData();
                DocumentationActivity.super.onBackPressed();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //open alert dialog
        openAlertDialog();
    }

}