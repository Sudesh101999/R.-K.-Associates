package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.CurrentResidVerifResponse;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData.CurrentResidVerifReadResponse;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.currentResidVerifInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentResidenceVerifActivity extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();

    private ProgressDialog progressdialog;
    private String executiveIdStr,aplcIdStr;
    private ImageView backIv;
    private EditText aplcNameEt,mobileNuEt,emailIdEt,dateOfBEt,qualificationEt,maritalEt,aadhaarEt,relationEt,addressDetailEt;
    private Spinner genderSp,familyMemberSp,earningMembersSp,childrenSp,billSp;
    private RelativeLayout resiVerifSubmitRL;

    private String activityFor,addDataIdIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_residence_verif);

        initData();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            //The key argument here must match that used in the other activity
            addDataIdIntentStr= extras.getString("addDataIdIntentStr");

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId",addDataIdIntentStr);
                getDataFromDatabase();
            }
        }
//        setSpinner();
        clickOperation();
    }

    private void getDataFromDatabase() {

        progressdialog.show();

        currentResidVerifInterface currentResidVerifInterface = ApiClient.getRetrofitInstance().create(currentResidVerifInterface.class);
        Call<CurrentResidVerifReadResponse> call = currentResidVerifInterface.getCurrentResidVefi(addDataIdIntentStr,executiveIdStr);
        call.enqueue(new Callback<CurrentResidVerifReadResponse>() {
            @Override
            public void onResponse(Call<CurrentResidVerifReadResponse> call, Response<CurrentResidVerifReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Log.d("Data",String.valueOf(response.body().getData()));
                        setDataToText(
                                response.body().getData().getCurrentResidenceVerificationTable().getApplicantName(),
                                response.body().getData().getCurrentResidenceVerificationTable().getMobileNumber(),
                                response.body().getData().getCurrentResidenceVerificationTable().getEmail(),
                                response.body().getData().getCurrentResidenceVerificationTable().getdOB(),
                                response.body().getData().getCurrentResidenceVerificationTable().getGender(),
                                response.body().getData().getCurrentResidenceVerificationTable().getQualification(),
                                response.body().getData().getCurrentResidenceVerificationTable().getMaritalStatus(),
                                response.body().getData().getCurrentResidenceVerificationTable().getAdharcardNumber(),
                                response.body().getData().getCurrentResidenceVerificationTable().getUtilityBill(),
                                response.body().getData().getCurrentResidenceVerificationTable().getApplicationRelation(),
                                response.body().getData().getCurrentResidenceVerificationTable().getFamilyMambers(),
                                response.body().getData().getCurrentResidenceVerificationTable().getEarningMembers(),
                                response.body().getData().getCurrentResidenceVerificationTable().getChildren(),
                                response.body().getData().getCurrentResidenceVerificationTable().getCurrentAddress()
                        );
                    }else{
                        snackBarMsg("Message: "+response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e("Current residence Verification","Exce: "+e.getMessage());
                    snackBarMsg("Exception: "+e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<CurrentResidVerifReadResponse> call, Throwable t) {

                snackBarMsg("onFailure: "+t.getMessage());
                Log.e("onFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });


    }

    private void setDataToText(String aplcNameStr, String mobileNumber, String email, String dobStr, String gender,
                               String qualification, String maritalStatus, String adharcardNumber,
                               String utilityBill, String applicationRelation, String familyMambers,
                               String earningMembers, String children, String currentAddress) {

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.
                createFromResource(CurrentResidenceVerifActivity.this,
                        R.array.gender_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> famitMemberAdapter = ArrayAdapter.
                createFromResource(CurrentResidenceVerifActivity.this,
                        R.array.members_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> billAdapter = ArrayAdapter.
                createFromResource(CurrentResidenceVerifActivity.this,
                        R.array.utility_bill_sp, android.R.layout.simple_spinner_item);

        Log.d( "setDataToText: ","familyMambers: "+familyMambers+"\nearningMembers: "+earningMembers+"\nchildren: "+children);

        aplcNameEt.setText(aplcNameStr);
        mobileNuEt.setText(mobileNumber);
        emailIdEt.setText(email);
        dateOfBEt.setText(dobStr);
        setSpinnerData(gender,genderAdapter,genderSp);
        qualificationEt.setText(qualification);
        maritalEt.setText(maritalStatus);
        aadhaarEt.setText(adharcardNumber);
        setSpinnerData(utilityBill,billAdapter,billSp);
        relationEt.setText(applicationRelation);
        setSpinnerData(familyMambers,famitMemberAdapter,familyMemberSp);
        setSpinnerData(earningMembers,famitMemberAdapter,earningMembersSp);
        setSpinnerData(children,famitMemberAdapter,childrenSp);
        addressDetailEt.setText(currentAddress);

    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition,true);
        }
    }

    private void setSpinner() {
        ArrayList<String> fiftyNum = new ArrayList<String>();
        String startNum = "--Select--";
        fiftyNum.add(startNum);
        int fiftyInt = 50;
        for (int i = 1; i <= fiftyInt; i++) {
            fiftyNum.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapterNum = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fiftyNum);
        familyMemberSp.setAdapter(adapterNum);
        earningMembersSp.setAdapter(adapterNum);
        childrenSp.setAdapter(adapterNum);
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        dateOfBEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void clickOperation() {
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        dateOfBEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        CurrentResidenceVerifActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });
        resiVerifSubmitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        String aplcNameStr,mobileStr,emailStr,dobStr,genderStr,qualifStr,maritalStr,aadhaarCardStr,
                utilityBillStr,aplcRelationStr,familyMemberStr,earningMemberStr,childrenStr,addressDetailStr;

        if (aplcNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Applicant Name getting empty...!!!");
            return;
        }else
            aplcNameStr=aplcNameEt.getText().toString();

        if (mobileNuEt.getText().toString().isEmpty()) {
            snackBarMsg("Mobile number getting empty...!!!");
            return;
        }else
            mobileStr=mobileNuEt.getText().toString();

        if (emailIdEt.getText().toString().isEmpty()) {
            snackBarMsg("Email getting empty...!!!");
            return;
        }else
            emailStr=emailIdEt.getText().toString();

        if (dateOfBEt.getText().toString().isEmpty()) {
            snackBarMsg("Date Of birth getting empty...!!!");
            return;
        }else
            dobStr=dateOfBEt.getText().toString();

        if (genderSp.getSelectedItemPosition()==0) {
            snackBarMsg("Select Gender...!!!");
            return;
        }else
            genderStr=genderSp.getSelectedItem().toString();

        if (qualificationEt.getText().toString().isEmpty()) {
            snackBarMsg("Qualification getting empty...!!!");
            return;
        }else
            qualifStr=qualificationEt.getText().toString();

        if (maritalEt.getText().toString().isEmpty()) {
            snackBarMsg("Marital getting empty...!!!");
            return;
        }else
            maritalStr=maritalEt.getText().toString();

        if (aadhaarEt.getText().toString().isEmpty()) {
            snackBarMsg("Aadhaar card number getting empty...!!!");
            return;
        }else
            aadhaarCardStr=aadhaarEt.getText().toString();

        if (billSp.getSelectedItemPosition()==0) {
            snackBarMsg("Billing getting empty...!!!");
            return;
        }else
            utilityBillStr=billSp.getSelectedItem().toString();

        if (relationEt.getText().toString().isEmpty()) {
            snackBarMsg("Relation getting empty...!!!");
            return;
        }else
            aplcRelationStr=relationEt.getText().toString();

        if (familyMemberSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Family member getting empty...!!!");
            return;
        }else
            familyMemberStr=familyMemberSp.getSelectedItem().toString();

        if (earningMembersSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Earning member getting empty...!!!");
            return;
        }else
            earningMemberStr=earningMembersSp.getSelectedItem().toString();

        if (childrenSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Select children...!!!");
            return;
        }else
            childrenStr=childrenSp.getSelectedItem().toString();

        if (addressDetailEt.getText().toString().isEmpty()) {
            snackBarMsg("Address details getting empty...!!!");
            return;
        }else
            addressDetailStr=addressDetailEt.getText().toString();

        insertToDatabase(executiveIdStr,aplcNameStr,mobileStr,emailStr,dobStr,genderStr,qualifStr,maritalStr,aadhaarCardStr,
                utilityBillStr,aplcRelationStr,familyMemberStr,earningMemberStr,childrenStr,addressDetailStr);

    }

    private void insertToDatabase(String executIdStr, String aplcName,  String mobileStr, String emailStr,
                                  String dobStr, String genderStr, String qualifStr, String maritalStr,
                                  String aadhaarCardStr, String utilityBillStr, String aplcRelationStr,
                                  String familyMemberStr, String earningMemberStr, String childrenStr, String addressDetailStr) {

        progressdialog.show();

        currentResidVerifInterface currentResidVerifInterface = ApiClient.getRetrofitInstance().create(currentResidVerifInterface.class);
        Call<CurrentResidVerifResponse> call = currentResidVerifInterface.currentResidVefiInsert(executIdStr,addDataIdIntentStr,aplcName,mobileStr,emailStr,dobStr,genderStr,qualifStr,maritalStr,aadhaarCardStr,
                utilityBillStr,aplcRelationStr,familyMemberStr,earningMemberStr,childrenStr,addressDetailStr);
        call.enqueue(new Callback<CurrentResidVerifResponse>() {
            @Override
            public void onResponse(Call<CurrentResidVerifResponse> call, Response<CurrentResidVerifResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        Log.d("Data",String.valueOf(response.body().getResult()));
                        Toast.makeText(CurrentResidenceVerifActivity.this, "Message: "+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setCurrentResidence(true);
                        CurrentResidenceVerifActivity.super.onBackPressed();
                    }else{
                        Log.e("Status", String.valueOf(response.body().getStatus()));
                    }
                }catch (Exception e){
                    Log.e("Current residence Verification","Exce: "+e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<CurrentResidVerifResponse> call, Throwable t) {

                Log.e("onFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)   {
            openAlertDialog();
        }
        return true;
    }
    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                CurrentResidenceVerifActivity.super.onBackPressed();
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

    private void initData() {

        backIv = findViewById(R.id.ref_verif_back_btn_iv);
        aplcNameEt=findViewById(R.id.residence_verif_page_execut_name_et);
        mobileNuEt=findViewById(R.id.residence_verif_page_mobile_no_et);
        emailIdEt=findViewById(R.id.residence_verif_page_e_mail_et);
        dateOfBEt=findViewById(R.id.residence_verif_page_dob_et);
        genderSp=findViewById(R.id.residence_verif_page_gender_sp);
        qualificationEt=findViewById(R.id.residence_verif_page_qualification_et);
        maritalEt=findViewById(R.id.residence_verif_page_marital_status_et);
        aadhaarEt=findViewById(R.id.residence_verif_page_aadhaar_no_et);
        billSp=findViewById(R.id.residence_verif_page_utility_bill_et);
        relationEt=findViewById(R.id.residence_verif_page_applc_relation_et);
        familyMemberSp=findViewById(R.id.residence_verif_page_family_member_et);
        earningMembersSp=findViewById(R.id.residence_verif_page_earning_members_et);
        childrenSp=findViewById(R.id.residence_verif_page_children_et);
        addressDetailEt=findViewById(R.id.residence_verif_page_add_family_details_et);

        resiVerifSubmitRL=findViewById(R.id.layout_doc_current_residence_verif_submit);

        aplcIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getAplcId(getApplicationContext());
        executiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
//        aplcNameEt.setText(SharedPrefAuth.getInstance(getApplicationContext()).getAplcName(getApplicationContext()));

        progressdialog = new ProgressDialog(CurrentResidenceVerifActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
    }

    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}