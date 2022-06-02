package com.rkassociates.DocPicker;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.DocPicker.ApiCalling.addDataResponse;
import com.rkassociates.DocPicker.ApiCalling.bankBranchSpinner;
import com.rkassociates.DocPicker.ApiCalling.docPickerInterface;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.UploadAppliDoc.DocumentationActivity;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocPickerActivity extends AppCompatActivity implements View.OnClickListener {

    final Calendar myCalendar = Calendar.getInstance();
    private SearchableSpinner bankNameSp, branchNameSp;
    private Spinner productType;
    private EditText executiveNameEt, referenceEt, aplcNameEt, coAplcNameEt, loanAmountEt, dateEt, timeEt;
    private ProgressDialog progressdialog;
    Button saveBtn, cancelBtn;
    String loanTypeStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_picker);

        initData();

        getDataOfSpinner();

        setDateTime();
        onclickFunction();

    }

    private void getDataOfSpinner() {
        progressdialog.show();

        ArrayList<String> bankNameList = new ArrayList<String>();
        ArrayList<String> branchNameList = new ArrayList<String>();

        docPickerInterface anInterface = ApiClient.getRetrofitInstance().create(docPickerInterface.class);
        Call<bankBranchSpinner> call = anInterface.spinnerCallApi();
        call.enqueue(new Callback<bankBranchSpinner>() {
            @Override
            public void onResponse(Call<bankBranchSpinner> call, Response<bankBranchSpinner> response) {
                try {
                    if (response.body().isStatus()) {
                        Log.d("Data Bank loaded", response.body().banks + "\nbranch" + response.body().branches);
//                        Toast.makeText(DocPickerActivity.this, "Aplc name" + response.body().banks, Toast.LENGTH_SHORT).show();

                        //                        ...........bank name.........
                        String bankStart = "--Select--";
                        bankNameList.add(bankStart);
                        for (int i = 0; i < response.body().getBanks().size(); i++) {
                            bankNameList.add(response.body().getBanks().get(i));
                        }

                        ArrayAdapter<String> banknameAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, bankNameList);
                        bankNameSp.setAdapter(banknameAdapter);

//                        ............branch name
                        String branchStart = "--Select--";
                        branchNameList.add(branchStart);
                        for (int i = 0; i < response.body().branches.size(); i++) {
                            branchNameList.add(response.body().getBranches().get(i));
                        }
                        ArrayAdapter<String> branchNameAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, branchNameList);
                        branchNameSp.setAdapter(branchNameAdapter);
//                        .............


                        //upload data of coApplicant
                        for (int i = 0; i < response.body().banks.size(); i++) {

                            Log.e("Banks and Branches",
                                    "bank: " + response.body().getBanks().get(i) +
                                            "\nBranch: " + response.body().getBranches().get(i) + "\n"

                            );

                        }
                    } else {
                        Log.e("Upload spinner error", response.body().banks.toString());
                        Log.e("Upload spinner msg", response.body().message);
//                        regProgressBar.setVisibility(View.GONE);
                    }
                    Log.e("spinner status", String.valueOf(response.body().isStatus()));
                } catch (Exception e) {
                    Log.e("spinner upload", " data exception: " + Log.getStackTraceString(e));
//                    regProgressBar.setVisibility(View.GONE);
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<bankBranchSpinner> call, Throwable t) {
                Log.e("spinner msg onFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void onclickFunction() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DocPickerActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                mTimePicker = new TimePickerDialog(DocPickerActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

                        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";

                        timeEt.setText(strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm);

//                        timeEt.setText( selectedHour + ":" + selectedMinute );
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        bankNameSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveBtn.setOnClickListener(DocPickerActivity.this);
        cancelBtn.setOnClickListener(DocPickerActivity.this);

    }

    private void initData() {

        bankNameSp = findViewById(R.id.profile_bank_name_sp);
        executiveNameEt = findViewById(R.id.emp_name_et);
        branchNameSp = findViewById(R.id.profile_bank_branch_sp);
        productType = findViewById(R.id.loan_product_type_sp);
        referenceEt = findViewById(R.id.profile_reference_et);
        aplcNameEt = findViewById(R.id.profile_applicant_name_et);
        coAplcNameEt = findViewById(R.id.profile_co_applicant_name_et);
        loanAmountEt = findViewById(R.id.profile_loan_amount_et);
        dateEt = findViewById(R.id.profile_date_et);
        timeEt = findViewById(R.id.profile_time_et);

        saveBtn = findViewById(R.id.save_doc_picker_btn);
        cancelBtn = findViewById(R.id.cancel_doc_picker_btn);

        executiveNameEt.setText(SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext()));

        progressdialog = new ProgressDialog(DocPickerActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_doc_picker_btn:

                insertToDatabase();
//                Intent intent = new Intent(DocPickerActivity.this, DocumentationActivity.class);
//                intent.putExtra("activityFor","newData");
//                startActivity(intent);
//                finish();
                break;
            case R.id.cancel_doc_picker_btn:
                DocPickerActivity.super.onBackPressed();
                break;
        }
    }

    private void insertToDatabase() {
        progressdialog.show();
        String executiveStr, bankStr, branchStr, referenceStr, aplcNameStr, coAplcNameStr, productTypeStr, loanAnountStr, dateTimeStr;

        executiveStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        bankStr = bankNameSp.getSelectedItem().toString();
        branchStr = branchNameSp.getSelectedItem().toString();
        referenceStr = referenceEt.getText().toString();
        aplcNameStr = aplcNameEt.getText().toString();
        coAplcNameStr = coAplcNameEt.getText().toString();
        productTypeStr = productType.getSelectedItem().toString();
        loanAnountStr = loanAmountEt.getText().toString();
        dateTimeStr = dateEt.getText().toString() + " " + timeEt.getText().toString();


        docPickerInterface anInterface = ApiClient.getRetrofitInstance().create(docPickerInterface.class);
        Call<addDataResponse> call = anInterface.insertAddData(executiveStr, bankStr, branchStr, referenceStr,
                aplcNameStr, coAplcNameStr, productTypeStr, loanAnountStr, dateTimeStr);
        call.enqueue(new Callback<addDataResponse>() {
            @Override
            public void onResponse(Call<addDataResponse> call, Response<addDataResponse> response) {
                try {
                    if (Objects.requireNonNull(response.body()).status == 4) {
                        Log.e("Required Data",
                                "add_data_id: "+String.valueOf(response.body().add_data_id)
                                        +"\nApplicant name: "+response.body().result.applicantName
                                        +"\nCoApplicant Name"+response.body().result.coApplicantName
                                        +"\nExecutive Id: "+response.body().result.executiveId);

                        Toast.makeText(DocPickerActivity.this, response.body().msg, Toast.LENGTH_SHORT).show();

                        snackBarMsg(response.body().msg);

                        SharedPrefAuth.getInstance(getApplicationContext()).setAplcName(
                                DocPickerActivity.this,
                                response.body().result.applicantName);
                        SharedPrefAuth.getInstance(getApplicationContext()).setCoAplcName(
                                DocPickerActivity.this,
                                response.body().result.coApplicantName);
                        SharedPrefAuth.getInstance(getApplicationContext()).setAddDataPageId(
                                DocPickerActivity.this,
                                String.valueOf(response.body().add_data_id));

                        Intent intent = new Intent(DocPickerActivity.this, DocumentationActivity.class);
                        intent.putExtra("activityFor","newData");
                        intent.putExtra("addDataId",response.body().add_data_id);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("Add data page error", response.body().msg);
                    }
                    Log.e("Add data page status", String.valueOf(response.body().status));
                } catch (Exception e) {
                    Log.e("Add data page", " data exception: " + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<addDataResponse> call, Throwable t) {
                Log.e("Add data page onFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });

    }
    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}