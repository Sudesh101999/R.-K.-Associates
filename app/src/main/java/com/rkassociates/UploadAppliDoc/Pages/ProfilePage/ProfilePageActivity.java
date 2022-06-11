package com.rkassociates.UploadAppliDoc.Pages.ProfilePage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefApplicantInfo;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ProfileInterface;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ProfileResponse;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData.ReadProfileResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePageActivity extends AppCompatActivity {

    private ProgressDialog progressdialog;
    final Calendar myCalendar= Calendar.getInstance();
    private ImageView backIv,aplcExpandIv;
    private EditText dateEt,timeEt,executiveNameEt,aplcNameEt,residentEt,qualifEt,businessEt,
            businessEmpNameEt,designatinoEt,remarkEt;
    private RelativeLayout profilePageSubmitRL;
    private Spinner durationSp,commBusinesSp;
    private LinearLayout addMultiCoAplcLL,aplcLayoutLL,multiCoAplcLayout;
    private String exectiveIdStr;

    private String activityFor,addDataIdIntentStr;


    private String profileRemarkId,executiveId,applicantId,submissionDate,applicantName,residence,
            durationOfStay,qulification,businessServices,employeeName,designation,commencementOfBusiness,
            profileRemarkStatus,createdDate;

    private String coAplcExecutivNameStr,coAplcDateStr,coAplcNameStr,coAplcResidentStr,coAplcDurationOfStayStr,coAplcQualificationStr,
            coAplcBusinessServiceStr,coAplcBusinessEmployeeNameStr,coAplcDesignationStr,coAplcCommenOfBusinessStr,coAPlcRemarkStr;

    private ArrayList<String> coAplcExecutivNameArray = new ArrayList<>();
    private ArrayList<String> coAplcDateArray = new ArrayList<>();
    private ArrayList<String> coAplcNameArray = new ArrayList<>();
    private ArrayList<String> coAplcResidentArray = new ArrayList<>();
    private ArrayList<String> coAplcDurationOfStayArray = new ArrayList<>();
    private ArrayList<String> coAplcQualificationArray = new ArrayList<>();
    private ArrayList<String> coAplcBusinessServiceArray = new ArrayList<>();
    private ArrayList<String> coAplcBusinessEmployeeNameArray = new ArrayList<>();
    private ArrayList<String> coAplcDesignationArray = new ArrayList<>();
    private ArrayList<String> coAplcCommenOfBusinessArray = new ArrayList<>();
    private ArrayList<String> coAplcRemarkArray = new ArrayList<>();
    private String executiveNameSharePref,aplcNameIntentStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        initData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            addDataIdIntentStr= extras.getString("addDataIdIntentStr");
            //The key argument here must match that used in the other activity

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId",addDataIdIntentStr);
                aplcNameIntentStr = extras.getString("aplcNameIntentStr");
                aplcNameEt.setText(aplcNameIntentStr);
                getDataFromDatabase();
            }else if (activityFor.equals("newData")) {
                aplcNameIntentStr = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcName(getApplicationContext());
                aplcNameEt.setText(aplcNameIntentStr);
            }
        }

        setDateTime();

        clickOperation();

    }

    private void getDataFromDatabase() {

        progressdialog.show();

        ProfileInterface profileInterface = ApiClient.getRetrofitInstance().create(ProfileInterface.class);
        Call<ReadProfileResponse> call = profileInterface.readProfileData(addDataIdIntentStr,exectiveIdStr);
        call.enqueue(new Callback<ReadProfileResponse>() {
            @Override
            public void onResponse(Call<ReadProfileResponse> call, Response<ReadProfileResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Log.d("Profile Page Response",response.body().getMessage());

                        profileRemarkId=response.body().data.getProfileRemarkTable().getProfileRemarkId();
                        executiveId=response.body().data.getProfileRemarkTable().getExecutiveId();
                        applicantId=response.body().data.getProfileRemarkTable().getApplicantId();
                        submissionDate=response.body().data.getProfileRemarkTable().getSubmissionDate();
                        applicantName=response.body().data.getProfileRemarkTable().getApplicantName();
                        residence=response.body().data.getProfileRemarkTable().getResidence();
                        durationOfStay=response.body().data.getProfileRemarkTable().getDurationOfStay();
                        qulification=response.body().data.getProfileRemarkTable().getQulification();
                        businessServices=response.body().data.getProfileRemarkTable().getBusinessServices();
                        employeeName=response.body().data.getProfileRemarkTable().getEmployeeName();
                        designation=response.body().data.getProfileRemarkTable().getDesignation();
                        commencementOfBusiness=response.body().data.getProfileRemarkTable().getCommencementOfBusiness();
                        profileRemarkStatus=response.body().data.getProfileRemarkTable().getProfileRemarkStatus();
                        createdDate=response.body().data.getProfileRemarkTable().getCreatedDate();

                        Log.d("TAG", "setDataToTextview: "+durationOfStay);

                        Toast.makeText(ProfilePageActivity.this, "durationOfStay: ="+durationOfStay, Toast.LENGTH_SHORT).show();
                        setDataToTextview();
                    }else{
                        Log.d("Profile Page Data",response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<ReadProfileResponse> call, Throwable t) {
                Log.e("Profile OnFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void setDataToTextview() {


        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.
                createFromResource(ProfilePageActivity.this,
                        R.array.duration_stay_sp, android.R.layout.simple_spinner_item);

//        executiveNameEt.setText();
        aplcNameEt.setText(applicantName);
        residentEt.setText(residence);
        setSpinnerData(durationOfStay,durationAdapter,durationSp);
        qualifEt.setText(qulification);
        businessEt.setText(businessServices);
        businessEmpNameEt.setText(employeeName);
        designatinoEt.setText(designation);
        setSpinnerData(commencementOfBusiness,durationAdapter,commBusinesSp);
        remarkEt.setText(profileRemarkStatus);
    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition,true);
        }
    }

    private void clickOperation() {

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                dateEt.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(myCalendar.getTime()));
            }
        };

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        ProfilePageActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();

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
                mTimePicker = new TimePickerDialog(ProfilePageActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

//                        timeEt.setText( selectedHour + ":" + selectedMinute );
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        aplcExpandIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aplcLayoutLL.getVisibility() == View.VISIBLE) {
                    aplcLayoutLL.setVisibility(View.GONE);
                } else {
                    aplcLayoutLL.setVisibility(View.VISIBLE);
                }
            }
        });

        addMultiCoAplcLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewLayout();
            }
        });
        profilePageSubmitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void openNewLayout() {

        Log.d("clicking","Add_multiple_coaplc");
        final AlertDialog.Builder alert = new AlertDialog.Builder(ProfilePageActivity.this);
        View v = getLayoutInflater().inflate(R.layout.profile_page_multi_co_aplc, null, false);

        EditText executiveNameCoAplcEt,dateCoAplcEt,coAplcNameCoAplcEt,residentCoAplcEt,qualificationCoAplcEt,businessServiceCoAplcEt,
                businessEmployeeCoAplcEt,designationCoAplcEt,remarkCoAplcEt;
        Spinner durationOfStayCoAplcSp,commOfBusinessCoAplcSp;
        RelativeLayout doneLL,cancelLL;

        executiveNameCoAplcEt = v.findViewById(R.id.profile_multi_rem_execut_name_et);
        dateCoAplcEt = v.findViewById(R.id.profile_multi_rem_date_et);
        coAplcNameCoAplcEt = v.findViewById(R.id.profile_multi_rem_co_aplc_name_et);
        residentCoAplcEt = v.findViewById(R.id.profile_multi_rem_resedent_et);
        durationOfStayCoAplcSp = v.findViewById(R.id.profile_multi_rem_duration_yr_sp);
        qualificationCoAplcEt = v.findViewById(R.id.profile_multi_rem_qualification_et);
        businessServiceCoAplcEt = v.findViewById(R.id.profile_multi_rem_business_service_et);
        businessEmployeeCoAplcEt = v.findViewById(R.id.profile_multi_rem_business_emp_name_et);
        designationCoAplcEt = v.findViewById(R.id.profile_multi_rem_designation_et);
        commOfBusinessCoAplcSp = v.findViewById(R.id.profile_multi_rem_business_comme_business_emp_et);
        remarkCoAplcEt = v.findViewById(R.id.profile_multi_rem_remark_et);
        doneLL = v.findViewById(R.id.profile_multi_layout_multi_done);
        cancelLL = v.findViewById(R.id.profile_multi_layout_multi_cancel);

        executiveNameCoAplcEt.setText(executiveNameSharePref);
        dateCoAplcEt.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

        durationOfStayCoAplcSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (!(position == 0)) {
                    coAplcDurationOfStayStr = durationOfStayCoAplcSp.getSelectedItem().toString();
                    durationOfStayCoAplcSp.setFocusable(false);
                } else
                    durationOfStayCoAplcSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        commOfBusinessCoAplcSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    coAplcCommenOfBusinessStr = adapterView.getItemAtPosition(i).toString();
                    commOfBusinessCoAplcSp.setFocusable(false);
                } else
                    commOfBusinessCoAplcSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                dateCoAplcEt.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(myCalendar.getTime()));
            }
        };

        dateCoAplcEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        ProfilePageActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

//        multiCoApplicLayout.addView(alert);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancelLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        doneLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (executiveNameCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcExecutivNameStr = executiveNameCoAplcEt.getText().toString();

                }
                if (dateCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcDateStr = dateCoAplcEt.getText().toString();
                }
                if (coAplcNameCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcNameStr= coAplcNameCoAplcEt.getText().toString();
                }
                if (residentCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcResidentStr= residentCoAplcEt.getText().toString();
                }
                if (qualificationCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcQualificationStr= qualificationCoAplcEt.getText().toString();
                }
                if (businessServiceCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcBusinessServiceStr= businessServiceCoAplcEt.getText().toString();
                }
                if (businessEmployeeCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcBusinessEmployeeNameStr= businessEmployeeCoAplcEt.getText().toString();
                }
                if (designationCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcDesignationStr= designationCoAplcEt.getText().toString();
                }
                if (remarkCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAPlcRemarkStr= remarkCoAplcEt.getText().toString();
                }

                validateMultiData(coAplcExecutivNameStr,coAplcDateStr,coAplcNameStr,coAplcResidentStr,coAplcDurationOfStayStr,coAplcQualificationStr,
                        coAplcBusinessServiceStr,coAplcBusinessEmployeeNameStr,coAplcDesignationStr,coAplcCommenOfBusinessStr,coAPlcRemarkStr);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void validateMultiData(String coAplcExecutivNameStr, String coAplcDateStr, String coAplcNameStr,
                                   String coAplcResidentStr, String coAplcDurationOfStayStr, String coAplcQualificationStr,
                                   String coAplcBusinessServiceStr, String coAplcBusinessEmployeeNameStr,
                                   String coAplcDesignationStr, String coAplcCommenOfBusinessStr, String coAPlcRemarkStr) {

        coAplcExecutivNameArray.add(coAplcExecutivNameStr);
        coAplcDateArray.add(coAplcDateStr);
        coAplcNameArray.add(coAplcNameStr);
        coAplcResidentArray.add(coAplcResidentStr);
        coAplcDurationOfStayArray.add(coAplcDurationOfStayStr);
        coAplcQualificationArray.add(coAplcQualificationStr);
        coAplcBusinessServiceArray.add(coAplcBusinessServiceStr);
        coAplcBusinessEmployeeNameArray.add(coAplcBusinessEmployeeNameStr);
        coAplcDesignationArray.add(coAplcDesignationStr);
        coAplcCommenOfBusinessArray.add(coAplcCommenOfBusinessStr);
        coAplcRemarkArray.add(coAPlcRemarkStr);


        View setDatalayout = getLayoutInflater().inflate(R.layout.set_multi_co_aplc_data_profile_page, null, false);

        ImageView expandCompressIv, deleteIv, editIv;
        LinearLayout expandLL;
        TextView executiveNameTv,dateTv,coAplcNameTv,residentTv,durationOfStayTv,qualificationTv,businesServiceTv,
                businessEmployeeTv,designationTv,commOfBusinessTv,remarkTv;

        expandCompressIv = setDatalayout.findViewById(R.id.profile_co_aplc_data_expand_iv);
        deleteIv = setDatalayout.findViewById(R.id.profile_co_aplc_data_delete_iv);
        editIv = setDatalayout.findViewById(R.id.profile_co_aplc_data_edit_iv);

        expandLL = setDatalayout.findViewById(R.id.profile_co_aplc_data_expand_layout);

        executiveNameTv = setDatalayout.findViewById(R.id.profile_co_appli__name_et);
        dateTv = setDatalayout.findViewById(R.id.profile_co_appli_date_et);
        coAplcNameTv = setDatalayout.findViewById(R.id.profile_co_appli_aplc_name_et);
        residentTv = setDatalayout.findViewById(R.id.profile_co_appli_resident_et);
        durationOfStayTv = setDatalayout.findViewById(R.id.profile_co_appli_duration_et);
        qualificationTv = setDatalayout.findViewById(R.id.profile_co_appli_qualification_et);
        businesServiceTv = setDatalayout.findViewById(R.id.profile_co_appli_business_service_et);
        businessEmployeeTv = setDatalayout.findViewById(R.id.profile_co_appli_business_emp_name_et);
        designationTv = setDatalayout.findViewById(R.id.profile_co_appli_designation_et);
        commOfBusinessTv = setDatalayout.findViewById(R.id.profile_co_appli_comme_of_business_et);
        remarkTv = setDatalayout.findViewById(R.id.profile_co_appli_remark_et);

        executiveNameTv.setText(coAplcExecutivNameStr);
        dateTv.setText(coAplcDateStr);
        coAplcNameTv.setText(coAplcNameStr);
        residentTv.setText(coAplcResidentStr);
        durationOfStayTv.setText(coAplcDurationOfStayStr);
        qualificationTv.setText(coAplcQualificationStr);
        businesServiceTv.setText(coAplcBusinessServiceStr);
        businessEmployeeTv.setText(coAplcBusinessEmployeeNameStr);
        designationTv.setText(coAplcDesignationStr);
        commOfBusinessTv.setText(coAplcCommenOfBusinessStr);
        remarkTv.setText(coAPlcRemarkStr);

        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean editClicked = false;
                openAlertDialogForDeleteLayout(setDatalayout,coAplcExecutivNameStr,coAplcDateStr,coAplcNameStr,
                        coAplcResidentStr,coAplcDurationOfStayStr,coAplcQualificationStr,coAplcBusinessServiceStr,
                        coAplcBusinessEmployeeNameStr,coAplcDesignationStr,coAplcCommenOfBusinessStr,coAPlcRemarkStr,
                        editClicked);

            }
        });

        editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String executiveNameStr, dateStr,coApNameStr,residentStr,durationOfStayStr,qualificationStr,businessServiceStr,
                        businessEmployeeStr,designationStr,commeOfBusinessStr,remarkStr;

                executiveNameStr = executiveNameTv.getText().toString();
                dateStr = dateTv.getText().toString();
                coApNameStr = coAplcNameTv.getText().toString();
                residentStr = residentTv.getText().toString();
                durationOfStayStr = durationOfStayTv.getText().toString();
                qualificationStr = qualificationTv.getText().toString();
                businessServiceStr = businesServiceTv.getText().toString();
                businessEmployeeStr = businessEmployeeTv.getText().toString();
                designationStr = designationTv.getText().toString();
                commeOfBusinessStr = commOfBusinessTv.getText().toString();
                remarkStr = remarkTv.getText().toString();


                boolean editClicked = true;

                editInLayoutData(setDatalayout,executiveNameStr, dateStr,coApNameStr,residentStr,durationOfStayStr,qualificationStr,businessServiceStr,
                        businessEmployeeStr,designationStr,commeOfBusinessStr,remarkStr, editClicked);


            }
        });


        expandCompressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandLL.getVisibility() == View.VISIBLE)
                    expandLL.setVisibility(View.GONE);
                else
                    expandLL.setVisibility(View.VISIBLE);
            }
        });

        multiCoAplcLayout.addView(setDatalayout);

    }

    private void editInLayoutData(View setDatalayout, String executiveNameStr, String dateStr, String coApNameStr, String residentStr, String durationOfStayStr, String qualificationStr, String businessServiceStr, String businessEmployeeStr, String designationStr, String commeOfBusinessStr, String remarkStr, boolean editClicked) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(ProfilePageActivity.this);
        View v = getLayoutInflater().inflate(R.layout.profile_page_multi_co_aplc, null, false);
        EditText executiveNameCoAplcEt,dateCoAplcEt,coAplcNameCoAplcEt,residentCoAplcEt,qualificationCoAplcEt,businessServiceCoAplcEt,
                businessEmployeeCoAplcEt,designationCoAplcEt,remarkCoAplcEt;
        Spinner durationOfStayCoAplcSp,commOfBusinessCoAplcSp;
        RelativeLayout doneLL,cancelLL;
        executiveNameCoAplcEt = v.findViewById(R.id.profile_multi_rem_execut_name_et);
        dateCoAplcEt = v.findViewById(R.id.profile_multi_rem_date_et);
        coAplcNameCoAplcEt = v.findViewById(R.id.profile_multi_rem_co_aplc_name_et);
        residentCoAplcEt = v.findViewById(R.id.profile_multi_rem_resedent_et);
        durationOfStayCoAplcSp = v.findViewById(R.id.profile_multi_rem_duration_yr_sp);
        qualificationCoAplcEt = v.findViewById(R.id.profile_multi_rem_qualification_et);
        businessServiceCoAplcEt = v.findViewById(R.id.profile_multi_rem_business_service_et);
        businessEmployeeCoAplcEt = v.findViewById(R.id.profile_multi_rem_business_emp_name_et);
        designationCoAplcEt = v.findViewById(R.id.profile_multi_rem_designation_et);
        commOfBusinessCoAplcSp = v.findViewById(R.id.profile_multi_rem_business_comme_business_emp_et);
        remarkCoAplcEt = v.findViewById(R.id.profile_multi_rem_remark_et);
        doneLL = v.findViewById(R.id.profile_multi_layout_multi_done);
        cancelLL = v.findViewById(R.id.profile_multi_layout_multi_cancel);
        //////-0------------------------------------------------set all data
        executiveNameCoAplcEt.setText(executiveNameStr);
        dateCoAplcEt.setText(dateStr);
        coAplcNameCoAplcEt.setText(coApNameStr);
        residentCoAplcEt.setText(residentStr);
        //set spinner data
        ArrayAdapter durationOfStayCoAplcAdap = (ArrayAdapter) durationOfStayCoAplcSp.getAdapter();
        int durationOfStayCoAplcPosition = durationOfStayCoAplcAdap.getPosition(durationOfStayStr);
        durationOfStayCoAplcSp.setSelection(durationOfStayCoAplcPosition);
        //
        qualificationCoAplcEt.setText(qualificationStr);
        businessServiceCoAplcEt.setText(businessServiceStr);
        businessEmployeeCoAplcEt.setText(businessEmployeeStr);
        designationCoAplcEt.setText(designationStr);
        //set spinner data
        ArrayAdapter commOfBusinessCoAplcAdap = (ArrayAdapter) commOfBusinessCoAplcSp.getAdapter();
        int coApResidAdapPosition = commOfBusinessCoAplcAdap.getPosition(commeOfBusinessStr);
        commOfBusinessCoAplcSp.setSelection(coApResidAdapPosition);
        //
        remarkCoAplcEt.setText(remarkStr);


        durationOfStayCoAplcSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (!(position == 0)) {
                    coAplcDurationOfStayStr = durationOfStayCoAplcSp.getSelectedItem().toString();
                    durationOfStayCoAplcSp.setFocusable(false);
                } else
                    durationOfStayCoAplcSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        commOfBusinessCoAplcSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (!(position == 0)) {
                    coAplcCommenOfBusinessStr = commOfBusinessCoAplcSp.getSelectedItem().toString();
                    commOfBusinessCoAplcSp.setFocusable(false);
                } else
                    commOfBusinessCoAplcSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        multiCoApplicLayout.addView(alert);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancelLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        doneLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (executiveNameCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcExecutivNameStr = executiveNameCoAplcEt.getText().toString();

                }
                if (dateCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Date require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcDateStr = dateCoAplcEt.getText().toString();

                }
                if (coAplcNameCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Co Applicant name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcNameStr = coAplcNameCoAplcEt.getText().toString();

                }
                if (residentCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Resident require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcResidentStr = residentCoAplcEt.getText().toString();
                }
                if (qualificationCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Qualification require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcQualificationStr = qualificationCoAplcEt.getText().toString();
                }
                if (businessServiceCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Business/Service require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcBusinessServiceStr = businessServiceCoAplcEt.getText().toString();
                }
                if (businessEmployeeCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Business/Employee Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcBusinessEmployeeNameStr = businessEmployeeCoAplcEt.getText().toString();
                }
                if (designationCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Designation require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAplcDesignationStr = designationCoAplcEt.getText().toString();
                }
                if (remarkCoAplcEt.getText().toString().isEmpty()) {
                    Toast.makeText(ProfilePageActivity.this, "Co-Applicant Business/Employee Name require...!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    coAPlcRemarkStr = remarkCoAplcEt.getText().toString();
                }

                openAlertDialogForDeleteLayout(setDatalayout, executiveNameStr, dateStr,coApNameStr,residentStr,
                        durationOfStayStr,qualificationStr,businessServiceStr,
                        businessEmployeeStr,designationStr,commeOfBusinessStr,remarkStr, editClicked);

                validateMultiData(coAplcExecutivNameStr,coAplcDateStr,coAplcNameStr,coAplcResidentStr,
                        coAplcDurationOfStayStr,coAplcQualificationStr,coAplcBusinessServiceStr,
                        coAplcBusinessEmployeeNameStr,coAplcDesignationStr,coAplcCommenOfBusinessStr,coAPlcRemarkStr);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void openAlertDialogForDeleteLayout(View setDatalayout, String coAplcExecutivNameStr, String coAplcDateStr,
                                                String coAplcNameStr, String coAplcResidentStr, String coAplcDurationOfStayStr,
                                                String coAplcQualificationStr, String coAplcBusinessServiceStr,
                                                String coAplcBusinessEmployeeNameStr, String coAplcDesignationStr,
                                                String coAplcCommenOfBusinessStr, String coAPlcRemarkStr, boolean editClicked) {

        if (editClicked) {
            coAplcExecutivNameArray.remove(coAplcExecutivNameStr);
            coAplcDateArray.remove(coAplcDateStr);
            coAplcNameArray.remove(coAplcNameStr);
            coAplcResidentArray.remove(coAplcResidentStr);
            coAplcDurationOfStayArray.remove(coAplcDurationOfStayStr);
            coAplcQualificationArray.remove(coAplcQualificationStr);
            coAplcBusinessServiceArray.remove(coAplcBusinessServiceStr);
            coAplcBusinessEmployeeNameArray.remove(coAplcBusinessEmployeeNameStr);
            coAplcDesignationArray.remove(coAplcDesignationStr);
            coAplcCommenOfBusinessArray.remove(coAplcCommenOfBusinessStr);
            coAplcRemarkArray.remove(coAPlcRemarkStr);

            multiCoAplcLayout.removeView(setDatalayout);
            for (int i = 0; i < coAplcExecutivNameArray.size(); i++) {
                Log.e("openAlertDialogForDeleteLayout","Profile CoApplicnat: "+
                        "coAplc executive name: " + coAplcExecutivNameArray.get(i) +
                                "\ncoAplc date" + coAplcDateArray.get(i) +
                                "\nCoAplc Name: " + coAplcNameArray.get(i));

            }
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to submit?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    coAplcExecutivNameArray.remove(coAplcExecutivNameStr);
                    coAplcDateArray.remove(coAplcDateStr);
                    coAplcNameArray.remove(coAplcNameStr);
                    coAplcResidentArray.remove(coAplcResidentStr);
                    coAplcDurationOfStayArray.remove(coAplcDurationOfStayStr);
                    coAplcQualificationArray.remove(coAplcQualificationStr);
                    coAplcBusinessServiceArray.remove(coAplcBusinessServiceStr);
                    coAplcBusinessEmployeeNameArray.remove(coAplcBusinessEmployeeNameStr);
                    coAplcDesignationArray.remove(coAplcDesignationStr);
                    coAplcCommenOfBusinessArray.remove(coAplcCommenOfBusinessStr);
                    coAplcRemarkArray.remove(coAPlcRemarkStr);

                    multiCoAplcLayout.removeView(setDatalayout);
                    for (int i = 0; i < coAplcExecutivNameArray.size(); i++) {
                        Log.e("openAlertDialogForDeleteLayout","Profile CoApplicnat: "+
                                "coAplc executive name: " + coAplcExecutivNameArray.get(i) +
                                "\ncoAplc date" + coAplcDateArray.get(i) +
                                "\nCoAplc Name: " + coAplcNameArray.get(i));

                    }
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

    private void checkValidation() {
        ////////
        String aplcNameStr,residentStr,durationStr,qualiStr,businessStr,businessEmpNameStr,designationStr,
                commBusinesStr,remarkStr,dateTimeStr;

        if (aplcNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Applicant name getting empty...!!!");
            return;
        }else
            aplcNameStr=aplcNameEt.getText().toString();

        if (residentEt.getText().toString().isEmpty()) {
            snackBarMsg("Resident getting empty...!!!");
            return;
        } else {
            residentStr = residentEt.getText().toString();
        }
        if (durationSp.getSelectedItemPosition()==0) {
            snackBarMsg("Duration getting empty...!!!");
            return;
        }else {
            durationStr = durationSp.getSelectedItem().toString();
        }
        if (qualifEt.getText().toString().isEmpty()) {
            snackBarMsg("Qualification getting empty...!!!");
            return;
        }else {
            qualiStr = qualifEt.getText().toString();
        }
        if (businessEt.getText().toString().isEmpty()) {
            snackBarMsg("Business Service getting empty...!!!");
            return;
        }else {
            businessStr = businessEt.getText().toString();
        }
        if (businessEmpNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Business Or Employee name getting empty...!!!");
            return;
        }else {
            businessEmpNameStr = businessEmpNameEt.getText().toString();
        }
        if (designatinoEt.getText().toString().isEmpty()) {
            snackBarMsg("Designation getting empty...!!!");
            return;
        }else {
            designationStr = designatinoEt.getText().toString();
        }
        if (commBusinesSp.getSelectedItemPosition()==0) {
            snackBarMsg("Commencement of business/Employment getting empty...!!!");
            return;
        }else {
            commBusinesStr = commBusinesSp.getSelectedItem().toString();
        }
        if (remarkEt.getText().toString().isEmpty()) {
            snackBarMsg("Remark getting empty...!!!");
            return;
        }else {
            remarkStr = remarkEt.getText().toString();
        }

        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        dateTimeStr = dateEt.getText().toString()+" "+currentTime;//timeEt.getText().toString();

        uploadToDatabase(aplcNameStr,residentStr,durationStr,qualiStr,businessStr, businessEmpNameStr,
                designationStr,commBusinesStr,remarkStr,dateTimeStr);

    }
    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void uploadToDatabase(String aplcNameStr,String residentStr, String durationStr, String qualiStr, String businessStr,
                                  String businessEmpNameStr, String designationStr, String commBusinesStr, String remarkStr, String dateTimeStr) {

        progressdialog.show();

        ProfileInterface profileInterface = ApiClient.getRetrofitInstance().create(ProfileInterface.class);
        Call<ProfileResponse> call = profileInterface.profileDataInsert(exectiveIdStr,addDataIdIntentStr,aplcNameStr,residentStr,durationStr,qualiStr,businessStr,
                businessEmpNameStr,designationStr,commBusinesStr,remarkStr,dateTimeStr);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        Log.d("Profile Page Response",response.body().getMsg());
                        Toast.makeText(ProfilePageActivity.this, "Message: "+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        snackBarMsg("Message: "+response.body().getMsg());
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setProfileData(true);
                        ProfilePageActivity.super.onBackPressed();
                    }else{
                        Log.d("Profile Page Data",response.body().getMsg());
                    }
                }catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("Profile OnFailure",t.getMessage());
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
                ProfilePageActivity.super.onBackPressed();
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
        backIv = findViewById(R.id.profile_page_back_btn_iv);
        dateEt=findViewById(R.id.profile_rem_date_et);
        timeEt = findViewById(R.id.profile_rem_time_et);

        aplcExpandIv = findViewById(R.id.applicant_profile_expand_iv);
        addMultiCoAplcLL = findViewById(R.id.profile_layout_add_multi_co_aplc);
        aplcLayoutLL = findViewById(R.id.profile_applicant_linear);
        multiCoAplcLayout = findViewById(R.id.profile_page_add_multi_co_applicant);

        executiveNameEt=findViewById(R.id.profile_rem_execut_name_et);
        aplcNameEt = findViewById(R.id.profile_rem_aplc_name_et);
        residentEt = findViewById(R.id.profile_rem_resedent_et);
        durationSp = findViewById(R.id.profile_rem_duration_yr_sp);
        qualifEt = findViewById(R.id.profile_rem_qualification_et);
        businessEt = findViewById(R.id.profile_rem_business_service_et);
        businessEmpNameEt = findViewById(R.id.profile_rem_business_emp_name_et);
        designatinoEt = findViewById(R.id.profile_rem_designation_et);
        commBusinesSp = findViewById(R.id.profile_rem_business_comme_business_emp_et);
        remarkEt = findViewById(R.id.profile_rem_remark_et);

        profilePageSubmitRL=findViewById(R.id.layout_doc_profile_submit);
        executiveNameSharePref = SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext());
        exectiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        executiveNameEt.setText(executiveNameSharePref);

        progressdialog = new ProgressDialog(ProfilePageActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
    }

    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }
}