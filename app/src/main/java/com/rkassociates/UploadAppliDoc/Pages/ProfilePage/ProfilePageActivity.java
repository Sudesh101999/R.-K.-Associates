package com.rkassociates.UploadAppliDoc.Pages.ProfilePage;

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
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ProfileInterface;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ProfileResponse;
import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData.ReadProfileResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePageActivity extends AppCompatActivity {

    private ProgressDialog progressdialog;
    final Calendar myCalendar= Calendar.getInstance();
    private ImageView backIv;
    private EditText dateEt,timeEt,executiveNameEt,aplcNameEt,residentEt,qualifEt,businessEt,
            businessEmpNameEt,designatinoEt,remarkEt;
    private RelativeLayout profilePageSubmitRL;
    private Spinner durationSp,commBusinesSp;
    private String exectiveIdStr;

    private String activityFor,addDataIdIntentStr;


    private String profileRemarkId,executiveId,applicantId,submissionDate,applicantName,residence,
            durationOfStay,qulification,businessServices,employeeName,designation,commencementOfBusiness,
            profileRemarkStatus,createdDate;

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
                getDataFromDatabase();
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
                updateLabel();
            }
        };

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ProfilePageActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        profilePageSubmitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        ////////
        String aplcNameStr,residentStr,durationStr,qualiStr,businessStr,businessEmpNameStr,designationStr,commBusinesStr,remarkStr,dateTimeStr;

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

        dateTimeStr = dateEt.getText().toString()+" "+timeEt.getText().toString();

        uploadToDatabase(aplcNameStr,residentStr,durationStr,qualiStr,businessStr, businessEmpNameStr,designationStr,commBusinesStr,remarkStr,dateTimeStr);

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

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void initData() {
        backIv = findViewById(R.id.profile_page_back_btn_iv);
        dateEt=findViewById(R.id.profile_rem_date_et);
        timeEt = findViewById(R.id.profile_rem_time_et);

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

        exectiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        executiveNameEt.setText(SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext()));


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