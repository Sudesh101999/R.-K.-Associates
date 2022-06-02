package com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.BusinessVerifInterface;
import com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.BusinessVerifResponse;
import com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.ReadData.BusinessVerifReadResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessVerifActivity extends AppCompatActivity {

    private ProgressDialog progressdialog;
    private ArrayList<String> empNum;
    private Spinner noOfEmployeeSp,organisationTypeSp,natureOfBusinessSp,permisesBusinessSp,commonBusinessSp;
    private ImageView backIv;
    private EditText executiveNameEt,dateEt,timeEt,nameBusinessEt,nameOfOwnerEt,
            addressEt,personMetEt,designationEt,officeNoEt,headOfDeptEt,businessProvidedEt,businessAcctEt,remarkEt;
    private RelativeLayout submitRL;

    final Calendar myCalendar= Calendar.getInstance();

    private String activityFor,addDataIdIntentStr,executiveIdStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_verif);

        initData();


        setSpinnerNumber();

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



        setOperation();
    }

    private void getDataFromDatabase() {

        progressdialog.setMessage("Getting Business Verification Page...");
        progressdialog.show();
        //insert to database
        BusinessVerifInterface businessVerifInterface = ApiClient.getRetrofitInstance().create(BusinessVerifInterface.class);
        Call<BusinessVerifReadResponse> call = businessVerifInterface.
                businessVerifReadData(executiveIdStr,addDataIdIntentStr);

        call.enqueue(new Callback<BusinessVerifReadResponse>() {
            @Override
            public void onResponse(Call<BusinessVerifReadResponse> call, Response<BusinessVerifReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Toast.makeText(BusinessVerifActivity.this, "Message: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        setDataToTv(
                                response.body().getResult().getBusinessVerificationTable().getApplicantName(),
                                response.body().getResult().getBusinessVerificationTable().getNameOfBusinessEmployer(),
                                response.body().getResult().getBusinessVerificationTable().getOrganisationType(),
                                response.body().getResult().getBusinessVerificationTable().getNameOfOwner(),
                                response.body().getResult().getBusinessVerificationTable().getNatureOfBusiness(),
                                response.body().getResult().getBusinessVerificationTable().getBusinessAddress(),
                                response.body().getResult().getBusinessVerificationTable().getPremisesBusinessDetails(),
                                response.body().getResult().getBusinessVerificationTable().getPersonMet(),
                                response.body().getResult().getBusinessVerificationTable().getDesignation(),
                                response.body().getResult().getBusinessVerificationTable().getOfficeNumber(),
                                response.body().getResult().getBusinessVerificationTable().getCommencementOfBusiness(),
                                response.body().getResult().getBusinessVerificationTable().getNumberOfEmployees(),
                                response.body().getResult().getBusinessVerificationTable().getBusinessReference(),
                                response.body().getResult().getBusinessVerificationTable().getBusinessProvidedDocs(),
                                response.body().getResult().getBusinessVerificationTable().getBusinessAcct(),
                                response.body().getResult().getBusinessVerificationTable().getRemark()
                        );

                        Log.e("TAG", "Data: "+response.body().getResult().getBusinessVerificationTable().getNameOfBusinessEmployer());
                    }else{
                        Toast.makeText(BusinessVerifActivity.this, "Message: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("BusinessVerif","Status: "+response.body().getStatus());
                    }
                }catch (Exception e){
                    Log.e("BusinessVerifException",e.getMessage());
                    snackBarMsg("Exception: "+e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<BusinessVerifReadResponse> call, Throwable t) {
                Log.e("BusinessVerifPage","OnFailure: "+t.getMessage());
                snackBarMsg("ObFaulure: "+t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void setDataToTv(String aplcNameStr,String nameOfBusinessEmployer, String organisationType, String nameOfOwner,
                             String natureOfBusiness, String businessAddress, String premisesBusinessDetails,
                             String personMet, String designation, String officeNumber, String commencementOfBusiness,
                             String numberOfEmployees, String businessReference, String businessProvidedDocs,
                             String businessAcct, String remark) {

        ArrayAdapter<CharSequence> natureOfBusinessAdapter = ArrayAdapter.
                createFromResource(BusinessVerifActivity.this,
                        R.array.nature_of_business_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> BusinessDetailsAdapter = ArrayAdapter.
                createFromResource(BusinessVerifActivity.this,
                        R.array.premises_busin_details_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> commencementOfAdapter = ArrayAdapter.
                createFromResource(BusinessVerifActivity.this,
                        R.array.commen_of_business_sp, android.R.layout.simple_spinner_item);


        nameBusinessEt.setText(nameOfBusinessEmployer);
        organisationTypeSp.setSelection(((ArrayAdapter<String>)organisationTypeSp.getAdapter()).getPosition(organisationType));
//        organisationTypeSp.setSelection(getIndex(organisationTypeSp, organisationType));
//        setSpinnerData(organisationType,R.array.organisation_type_sp,organisationTypeSp);
        nameOfOwnerEt.setText(nameOfOwner);
        setSpinnerData(natureOfBusiness,natureOfBusinessAdapter,natureOfBusinessSp);
        Log.e("TAG", "natureOfBusinessSp: "+natureOfBusiness);
        addressEt.setText(businessAddress);
        setSpinnerData(premisesBusinessDetails,BusinessDetailsAdapter,permisesBusinessSp);
        personMetEt.setText(personMet);
        designationEt.setText(designation);
        officeNoEt.setText(officeNumber);
        setSpinnerData(commencementOfBusiness,commencementOfAdapter,commonBusinessSp);
//        setSpinnerData();
        noOfEmployeeSp.setSelection(empNum.indexOf(numberOfEmployees));
        headOfDeptEt.setText(businessReference);
        businessProvidedEt.setText(businessProvidedDocs);
        businessAcctEt.setText(businessAcct);
        remarkEt.setText(remark);

        Log.d("Business data"," business Acct:=> "+businessAcct);

    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition,true);
        }
    }
    private void setOperation() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BusinessVerifActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        timeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BusinessVerifActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String am_pm = "";
                        Calendar datetime = Calendar.getInstance();
                        datetime.set(Calendar.HOUR_OF_DAY, selectedHour);
                        datetime.set(Calendar.MINUTE, selectedMinute);

                        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                            am_pm = "AM";
                        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                            am_pm = "PM";

                        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";
                        timeEt.setText( strHrsToShow+":"+datetime.get(Calendar.MINUTE)+" "+am_pm );
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        submitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkValidation();
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }

    private void checkValidation() {
        String aplcIdStr,dateTimeStr,nameOfBusinessStr,organisatinoTypeStr,nameOfOwnerStr,
                natureOfBusinessStr,addressStr,permisesBusinessStr,personMetStr,designationStr,officeNoStr,
                commenOfBusinessStr,numberOfEmpStr,headOfDeptStr,businessProvidedDocStr,businessAcctStr,remarkStr;

        aplcIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getAplcId(getApplicationContext());

        if (nameBusinessEt.getText().toString().isEmpty()) {
            snackBarMsg("Name of business/Employer getting empty...!!!");
            return;
        }else {
            nameOfBusinessStr = nameBusinessEt.getText().toString();
        }
        if (organisationTypeSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Organisation Type getting empty...!!!");
            return;
        }else {
            organisatinoTypeStr = organisationTypeSp.getSelectedItem().toString();
        }
        if (nameOfOwnerEt.getText().toString().isEmpty()) {
            snackBarMsg("Name of owner/Partner/HR getting empty...!!!");
            return;
        }else {
            nameOfOwnerStr = nameOfOwnerEt.getText().toString();
        }
        if (natureOfBusinessSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Nature Of Business getting empty...!!!");
            return;
        }else {
            natureOfBusinessStr = natureOfBusinessSp.getSelectedItem().toString();
        }

        if (addressEt.getText().toString().isEmpty()) {
            snackBarMsg("Address getting empty...!!!");
            return;
        }else {
            addressStr = addressEt.getText().toString();
        }
        if (permisesBusinessSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Premises Business getting empty...!!!");
            return;
        }else {
            permisesBusinessStr = permisesBusinessSp.getSelectedItem().toString();
        }
        if (personMetEt.getText().toString().isEmpty()) {
            snackBarMsg("Person Met getting empty...!!!");
            return;
        }else {
            personMetStr = personMetEt.getText().toString();
        }
        if (designationEt.getText().toString().isEmpty()) {
            snackBarMsg("Designatin getting empty...!!!");
            return;
        }else {
            designationStr = designationEt.getText().toString();
        }
        if (officeNoEt.getText().toString().isEmpty()) {
            snackBarMsg("Office No. getting empty...!!!");
            return;
        }else {
            officeNoStr = officeNoEt.getText().toString();
        }
        if (commonBusinessSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Common Business getting empty...!!!");
            return;
        }else {
            commenOfBusinessStr = commonBusinessSp.getSelectedItem().toString();
        }
        if (noOfEmployeeSp.getSelectedItem().toString().isEmpty()) {
            snackBarMsg("Number of employees getting empty...!!!");
            return;
        }else {
            numberOfEmpStr = noOfEmployeeSp.getSelectedItem().toString();
        }
        if (headOfDeptEt.getText().toString().isEmpty()) {
            snackBarMsg("Head of Dept/Reference getting empty...!!!");
            return;
        }else {
            headOfDeptStr = headOfDeptEt.getText().toString();
        }
        if (businessProvidedEt.getText().toString().isEmpty()) {
            snackBarMsg("Business/Employment provided docs getting empty...!!!");
            return;
        }else {
            businessProvidedDocStr = businessProvidedEt.getText().toString();
        }
        if (businessAcctEt.getText().toString().isEmpty()) {
            snackBarMsg("Business Acct getting empty...!!!");
            return;
        }else {
            businessAcctStr = businessAcctEt.getText().toString();
        }
        if (remarkEt.getText().toString().isEmpty()) {
            snackBarMsg("Remark getting empty...!!!");
            return;
        }else {
            remarkStr = remarkEt.getText().toString();
        }

        dateTimeStr = dateEt.getText().toString()+" "+timeEt.getText().toString();

        insertToDataBase(nameOfBusinessStr,organisatinoTypeStr,nameOfOwnerStr,
                natureOfBusinessStr,addressStr,permisesBusinessStr,personMetStr,designationStr,officeNoStr,
                commenOfBusinessStr,numberOfEmpStr,headOfDeptStr,businessProvidedDocStr,businessAcctStr,remarkStr,dateTimeStr);


    }

    private void insertToDataBase(String nameOfBusinessStr,
                                  String organisatinoTypeStr, String nameOfOwnerStr, String natureOfBusinessStr, 
                                  String addressStr, String permisesBusinessStr, String personMetStr, 
                                  String designationStr, String officeNoStr, String commenOfBusinessStr, 
                                  String numberOfEmpStr, String headOfDeptStr, String businessProvidedDocStr, 
                                  String businessAcctStr, String remarkStr, String dateTimeStr) {

        Log.e("testing data", "businessAcctStr: "+businessAcctStr+"\nexecutiveIdStr: "+executiveIdStr+"\naddDataIdIntentStr: "+addDataIdIntentStr );
        progressdialog.setMessage("Inserting Business Verification Page...");
        progressdialog.show();
        Log.e("TAG", "insertToDataBase: ");
        //insert to database
        BusinessVerifInterface businessVerifInterface = ApiClient.getRetrofitInstance().create(BusinessVerifInterface.class);
        Call<BusinessVerifResponse> call = businessVerifInterface.
                businessVerifInsertData(executiveIdStr,addDataIdIntentStr,nameOfBusinessStr,organisatinoTypeStr,nameOfOwnerStr,
                        natureOfBusinessStr,addressStr,permisesBusinessStr,personMetStr,designationStr,officeNoStr,
                        commenOfBusinessStr,numberOfEmpStr,headOfDeptStr,businessProvidedDocStr,businessAcctStr,remarkStr,dateTimeStr);
        
        call.enqueue(new Callback<BusinessVerifResponse>() {
            @Override
            public void onResponse(Call<BusinessVerifResponse> call, Response<BusinessVerifResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        Toast.makeText(BusinessVerifActivity.this, "Message: "+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setBusiness(true);
                        BusinessVerifActivity.super.onBackPressed();
                    }else{
                        Toast.makeText(BusinessVerifActivity.this, "Message: "+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.e("BusinessVerif","Status: "+response.body().getStatus());
                    }
                }catch (Exception e){
                    Log.e("BusinessVerifException",e.getMessage());
                    snackBarMsg("Exception: "+e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<BusinessVerifResponse> call, Throwable t) {
                Log.e("BusinessVerifPage","OnFailure: "+t.getMessage());
                snackBarMsg("ObFaulure: "+t.getMessage());
                progressdialog.dismiss();
            }
        });

    }

    private void initData() {
        executiveNameEt=findViewById(R.id.business_execute_name_et);
        dateEt=findViewById(R.id.business_date_et);
        timeEt=findViewById(R.id.business_time_et);
        nameBusinessEt=findViewById(R.id.business_business_et);
        nameOfOwnerEt=findViewById(R.id.business_owner_et);
        addressEt=findViewById(R.id.business_address_et);
        personMetEt=findViewById(R.id.business_Persone_Met_et);
        designationEt=findViewById(R.id.business_designation_et);
        officeNoEt=findViewById(R.id.business_office_no_et);
        headOfDeptEt=findViewById(R.id.business_head_of_dept_et);
        businessProvidedEt=findViewById(R.id.business_business_empl_provided_et);
        businessAcctEt=findViewById(R.id.business_business_acct_et);
        remarkEt=findViewById(R.id.business_remark_et);

        noOfEmployeeSp=findViewById(R.id.business_no_of_emp_sp);
        organisationTypeSp = findViewById(R.id.business_organisation_type_sp);
        natureOfBusinessSp=findViewById(R.id.business_nature_of_busin_sp);
        permisesBusinessSp=findViewById(R.id.business_premises_busi_sp);
        commonBusinessSp=findViewById(R.id.business_commen_business_sp);
        backIv = findViewById(R.id.business_page_back_btn_iv);

        submitRL = findViewById(R.id.layout_doc_business_submit);

        executiveNameEt.setText(SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext()));
        executiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        progressdialog = new ProgressDialog(BusinessVerifActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);

        setDateTime();
    }

    private void setSpinnerNumber() {

        empNum = new ArrayList<String>();
        int lastDate=1000;
        String startNum = "--Select--";
        empNum.add(startNum);
        for (int i = 1; i <= lastDate; i++) {
            empNum.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, empNum);

        noOfEmployeeSp.setAdapter(adapter);
    }
    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
                BusinessVerifActivity.super.onBackPressed();
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