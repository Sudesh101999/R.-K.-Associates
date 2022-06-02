package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.CoAplcResult.coAplcResponse;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData.RegisteredResponseRead;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.registerInterface;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.registeredResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredPageActivity extends AppCompatActivity {

    private String executiveId;
    private ProgressDialog progressdialog;
    private LinearLayout addMultiCoApplic;
    private RelativeLayout submitRL;
    private LinearLayout multiCoApplicLayout, applicantLL;
    private ImageView backIv, aplcExpandIv;
    private String applicantNameStr, aplcIdStr, executiveIdStr;

    //dialog data
    String multiCoAplcDialogNameStr = "", multiCoAplcDialogpanStr = "", multiCoAplcDialogaadhaarStr = "", multiCoAplcDialogelectricStr = "", multiCoAplcDialogdrivingStr = "",
            multiCoAplcDialogresidVerifStr = "", multiCoAplcDialogoffiBusiStr = "", multiCoAplcDialogtpcResiStr = "", multiCoAplcDialogtcpWorkStr = "", multiCoAplcDialogitrVerifStr = "",
            multiCoAplcDialogtdsCertifStr = "", multiCoAplcDialogbankVerifStr = "", multiCoAplcDialogchangePropVerifStr = "";

    private ArrayList<String> NameArray = new ArrayList<>();
    private ArrayList<String> residVerifArray = new ArrayList<>();
    private ArrayList<String> officeArray = new ArrayList<>();
    private ArrayList<String> tpcResidArray = new ArrayList<>();
    private ArrayList<String> tpcWorkArray = new ArrayList<>();
    private ArrayList<String> itrVerifArray = new ArrayList<>();
    private ArrayList<String> tdsCertifArray = new ArrayList<>();
    private ArrayList<String> bankStatemArray = new ArrayList<>();
    private ArrayList<String> changePropArray = new ArrayList<>();
    private ArrayList<String> panArray = new ArrayList<>();
    private ArrayList<String> aadhaarArray = new ArrayList<>();
    private ArrayList<String> electricArray = new ArrayList<>();
    private ArrayList<String> drivingArray = new ArrayList<>();
    private int coApplicantid = 0;

    //applicant
    private EditText aplcNameEt, aplcPanEt, aplcAadhaarEt, aplcElectricEt, aplcDrivingEt;
    private Spinner aplcResidenceSp, aplcOfficeSp, aplcTpcResidenceSp, aplcTcpWorkSp, aplcItrVerifSp,
            aplcTdsCertifSp, aplcBankStamentSp, aplcChangePropSp;

    String aplcNameStr = "", aplcResidenceStr = "", aplcOfficeStr = "", aplcTpcResidenceStr = "", aplcTcpWorkStr = "", aplcItrVerifStr = "",
            aplcTdsCertifStr = "", aplcBankStamentStr = "", aplcChangePropStr = "", aplcPanStr = "", aplcAadhaarStr = "", aplcElectricStr = "", aplcDrivingStr = "";


    private String activityFor = "";
    private String addDataIdIntentStr, aplcNameIntentStr, pendingListStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);


        initData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            addDataIdIntentStr = extras.getString("addDataIdIntentStr");
            //The key argument here must match that used in the other activity

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId", addDataIdIntentStr);
                getDataFromDatabase();
            }
        }

        Log.e("userInfo", "applicantNameStr: " + applicantNameStr + "\naplcIdStr: " + executiveIdStr);

        onclickOperation();

    }

    private void getDataFromDatabase() {
        progressdialog.show();

        registerInterface anInterface = ApiClient.getRetrofitInstance().create(registerInterface.class);
        Call<RegisteredResponseRead> call = anInterface.RegisteredApplicantCall(addDataIdIntentStr,executiveIdStr);
        call.enqueue(new Callback<RegisteredResponseRead>() {
            @Override
            public void onResponse(Call<RegisteredResponseRead> call, Response<RegisteredResponseRead> response) {
                try {
                    if (response.body().getStatus().equals("4")) {
                        Log.d("Data aplc loaded", response.body().getMessage());

                        Log.e("required Data",
                                "Applicant Name: " + response.body().getData().getApplicant().getApplicantName()
                                        + "\nco-Applicant name: " + response.body().getData().getCoApplicant().size()
                                        + "\nExecutive Id: " + response.body().getData().getApplicant().getExecutiveId());

                        snackBarMsg("Message: " + response.body().getMessage());

                        setReadedData(
                                response.body().getData().getApplicant().getApplicantName(),
                                response.body().getData().getApplicant().getResidenceVerification(),
                                response.body().getData().getApplicant().getBusinessVerification(),
                                response.body().getData().getApplicant().getResidence(),
                                response.body().getData().getApplicant().getWork(),
                                response.body().getData().getApplicant().getiTRVerification(),
                                response.body().getData().getApplicant().gettDSCertificateVerification(),
                                response.body().getData().getApplicant().getBankStatementVerification(),
                                response.body().getData().getApplicant().getChangePropertyVerification(),
                                response.body().getData().getApplicant().getPanNumber(),
                                response.body().getData().getApplicant().getAadhaarNumber(),
                                response.body().getData().getApplicant().getElectricityBill(),
                                response.body().getData().getApplicant().getDrivingLicense()
                        );

                    } else {
                        progressdialog.dismiss();
                        Log.e("Upload aplc error", response.body().getMessage());
                    }
                    Log.e("Aplc status", String.valueOf(response.body().getStatus()));
                } catch (Exception e) {
                    progressdialog.dismiss();
                    Log.e("Aplc upload", " data exception: " + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<RegisteredResponseRead> call, Throwable t) {
                Log.e("Aplc onFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void setReadedData(String applicantName, String residenceVerification, String businessVerification,
                               String residence, String work, String getiTRVerification, String gettDSCertificateVerification,
                               String bankStatementVerification, String changePropertyVerification, String panNumber,
                               String aadhaarNumber, String electricityBill, String drivingLicense) {


        ArrayAdapter<CharSequence> yes_no_spAdapter = ArrayAdapter.
                createFromResource(RegisteredPageActivity.this,
                        R.array.yes_no_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> work_sp_spAdapter = ArrayAdapter.
                createFromResource(RegisteredPageActivity.this,
                        R.array.work_sp, android.R.layout.simple_spinner_item);

        aplcNameEt.setText(applicantName);
        setSpinnerData(residenceVerification, yes_no_spAdapter, aplcResidenceSp);
        setSpinnerData(businessVerification, yes_no_spAdapter, aplcOfficeSp);
        setSpinnerData(residence, yes_no_spAdapter, aplcResidenceSp);
        setSpinnerData(work, work_sp_spAdapter, aplcTcpWorkSp);
        setSpinnerData(getiTRVerification, yes_no_spAdapter, aplcItrVerifSp);
        setSpinnerData(gettDSCertificateVerification, yes_no_spAdapter, aplcTdsCertifSp);
        setSpinnerData(bankStatementVerification, yes_no_spAdapter, aplcBankStamentSp);
        setSpinnerData(changePropertyVerification, yes_no_spAdapter, aplcChangePropSp);
        aplcPanEt.setText(panNumber);
        aplcAadhaarEt.setText(aadhaarNumber);
        aplcElectricEt.setText(electricityBill);
        aplcDrivingEt.setText(drivingLicense);
    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition,true);
        }
    }

    private void onclickOperation() {

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisteredPageActivity.super.onBackPressed();
            }
        });
        aplcExpandIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applicantLL.getVisibility() == View.VISIBLE) {
                    applicantLL.setVisibility(View.GONE);
                } else {
                    applicantLL.setVisibility(View.VISIBLE);
                }
            }
        });

        addMultiCoApplic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewLayout();
            }
        });
        int maxLength = 12;
        aplcAadhaarEt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

        submitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coApplicantid == 0) {
                    Toast.makeText(RegisteredPageActivity.this, "Enter Co-Applicant Data", Toast.LENGTH_SHORT).show();
                } else {
                    openAlertDialogForSubmit();
                }
            }
        });
    }

    private void openNewLayout() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(RegisteredPageActivity.this);
        View v = getLayoutInflater().inflate(R.layout.registered_page_multi_applicant, null, false);
        EditText multiAplcName, panEt, aadhaarEt, electricityEt, drivingEt;
        Spinner residVerifSp, offiBusiSp, tpcResiSp, tcpWorkSp, itrVerif, tdsCertif, bankVerif, changePropVerifSp;
        RelativeLayout canceLL, doneLL, progressLL;

        progressLL = v.findViewById(R.id.multi_co_aplc_progress_layout);
        multiAplcName = v.findViewById(R.id.regis_multi_appli_client_name_et);
        residVerifSp = v.findViewById(R.id.regis_multi_appli_residence_verif_sp);
        offiBusiSp = v.findViewById(R.id.regis_multi_appli_office_business_verif_sp);
        tpcResiSp = v.findViewById(R.id.regis_multi_appli_tpc_residence_sp);
        tcpWorkSp = v.findViewById(R.id.regis_multi_appli_work_sp);
        itrVerif = v.findViewById(R.id.regis_multi_appli_itr_verif_sp);
        tdsCertif = v.findViewById(R.id.regis_multi_appli_payslip_sp);
        bankVerif = v.findViewById(R.id.regis_multi_appli_bank_statement_verif_sp);
        changePropVerifSp = v.findViewById(R.id.regis_multi_appli_change_prop_verif);
        panEt = v.findViewById(R.id.regis_multi_appli_pan_et);
        aadhaarEt = v.findViewById(R.id.regis_multi_appli_aadhar_et);
        electricityEt = v.findViewById(R.id.regis_multi_appli_electricity_et);
        drivingEt = v.findViewById(R.id.regis_multi_appli_driving_lic_et);
        canceLL = v.findViewById(R.id.layout_multi_cancel);
        doneLL = v.findViewById(R.id.layout_multi_done);

        multiAplcName.setText(SharedPrefAuth.getInstance(getApplicationContext()).getCoAplcName(getApplicationContext()));

        residVerifSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (!(position == 0)) {
                    multiCoAplcDialogresidVerifStr = residVerifSp.getSelectedItem().toString();
                    residVerifSp.setFocusable(false);
                } else
                    residVerifSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        offiBusiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogoffiBusiStr = adapterView.getItemAtPosition(i).toString();
                    offiBusiSp.setFocusable(false);
                } else
                    offiBusiSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tpcResiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtpcResiStr = adapterView.getItemAtPosition(i).toString();
                    tpcResiSp.setFocusable(false);
                } else
                    tpcResiSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tcpWorkSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtcpWorkStr = adapterView.getItemAtPosition(i).toString();
                    tcpWorkSp.setFocusable(false);
                } else
                    tcpWorkSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        itrVerif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogitrVerifStr = adapterView.getItemAtPosition(i).toString();
                    itrVerif.setFocusable(false);
                } else
                    itrVerif.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tdsCertif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtdsCertifStr = adapterView.getItemAtPosition(i).toString();
                    tdsCertif.setFocusable(false);
                } else
                    tdsCertif.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bankVerif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogbankVerifStr = adapterView.getItemAtPosition(i).toString();
                    bankVerif.setFocusable(false);
                } else
                    bankVerif.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        changePropVerifSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogchangePropVerifStr = adapterView.getItemAtPosition(i).toString();
                    changePropVerifSp.setFocusable(false);
                } else
                    changePropVerifSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        multiCoApplicLayout.addView(alert);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        canceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        doneLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (multiAplcName.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant Name require...!");

                    return;
                } else {
                    multiCoAplcDialogNameStr = multiAplcName.getText().toString();

                }
                if (panEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant PAN card number require...!");
                    return;
                } else {
                    multiCoAplcDialogpanStr = panEt.getText().toString();

                }

                if (aadhaarEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant Aadhaar number require...!");
                    return;
                } else {
                    multiCoAplcDialogaadhaarStr = aadhaarEt.getText().toString();

                }

                if (electricityEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant electricity number require...!");
                    return;
                } else {
                    multiCoAplcDialogelectricStr = electricityEt.getText().toString();
                }

                if (drivingEt.getText().toString().isEmpty()) {
                    multiCoAplcDialogdrivingStr = "";
                } else
                    multiCoAplcDialogdrivingStr = drivingEt.getText().toString();

                progressLL.setVisibility(View.VISIBLE);
                validateMultiData(multiCoAplcDialogNameStr, multiCoAplcDialogpanStr, multiCoAplcDialogaadhaarStr, multiCoAplcDialogelectricStr, multiCoAplcDialogdrivingStr,
                        multiCoAplcDialogresidVerifStr, multiCoAplcDialogoffiBusiStr, multiCoAplcDialogtpcResiStr, multiCoAplcDialogtcpWorkStr, multiCoAplcDialogitrVerifStr,
                        multiCoAplcDialogtdsCertifStr, multiCoAplcDialogbankVerifStr, multiCoAplcDialogchangePropVerifStr);
                alertDialog.dismiss();

            }
        });
        alertDialog.show();
    }

    private void validateMultiData(String multiCoAplcNameStr, String panStr, String aadhaarStr, String electricStr,
                                   String drivingStr, String residVerifStr, String offiBusiStr, String tpcResiStr,
                                   String tpcWorkStr, String itrVerifStr, String tdsCertifStr, String bankVerifStr,
                                   String changePropVerifStr) {

        Log.d("dialogData", "Name: " + multiCoAplcNameStr + "\nPan: " + panStr + "\nAadhaar: " + aadhaarStr +
                "\nElectric: " + electricStr + "\nDriving:" + drivingStr + "\nResidVerif: " + residVerifStr +
                "\nOffiBusi: " + offiBusiStr + "\ntpcResi: " + tpcResiStr + "\ntcpWork: " + tpcWorkStr + "\nitrVerif: " + itrVerifStr +
                "\ntdsCertif: " + tdsCertifStr + "\nChangePropVerif: " + changePropVerifStr);

        coApplicantid = coApplicantid + 1;
        NameArray.add(multiCoAplcNameStr);
        residVerifArray.add(residVerifStr);
        officeArray.add(offiBusiStr);
        tpcResidArray.add(tpcResiStr);
        tpcWorkArray.add(tpcWorkStr);
        itrVerifArray.add(itrVerifStr);
        tdsCertifArray.add(tdsCertifStr);
        bankStatemArray.add(bankVerifStr);
        changePropArray.add(changePropVerifStr);
        panArray.add(panStr);
        aadhaarArray.add(aadhaarStr);
        electricArray.add(electricStr);
        drivingArray.add(drivingStr);


        View setDatalayout = getLayoutInflater().inflate(R.layout.set_multi_co_applic_data, null, false);

        ImageView expandCompressIv, deleteIv, editIv;
        LinearLayout expandLL;
        TextView nameTv, residenceVerifTv, officeBusiVerifTv, tpcResidVerifTv, tpcWorkTv, itrVerifTv, tdsCertifTv, bankStateVerifTv, changeProperTv,
                panTv, aadhaarTv, electricTv, drivTv;

        deleteIv = setDatalayout.findViewById(R.id.co_applicant_data_delete_iv);
        editIv = setDatalayout.findViewById(R.id.co_applicant_data_edit_iv);
        expandCompressIv = setDatalayout.findViewById(R.id.co_applicant_data_expand_iv);
        expandLL = setDatalayout.findViewById(R.id.co_applicant_data_expand_layout);
        nameTv = setDatalayout.findViewById(R.id.regis_co_appli_data_client_name_et);
        residenceVerifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_residence_verif_sp);
        officeBusiVerifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_office_business_verif_sp);
        tpcResidVerifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_tpc_residence_sp);
        tpcWorkTv = setDatalayout.findViewById(R.id.regis_co_appli_data_work_sp);
        itrVerifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_itr_verif_sp);
        tdsCertifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_payslip_sp);
        bankStateVerifTv = setDatalayout.findViewById(R.id.regis_co_appli_data_bank_statement_verif_sp);
        changeProperTv = setDatalayout.findViewById(R.id.regis_co_appli_data_change_prop_verif);
        panTv = setDatalayout.findViewById(R.id.regis_co_appli_data_pan_et);
        aadhaarTv = setDatalayout.findViewById(R.id.regis_co_appli_data_aadhar_et);
        electricTv = setDatalayout.findViewById(R.id.regis_co_appli_data_electricity_et);
        drivTv = setDatalayout.findViewById(R.id.regis_co_appli_data_driving_lic_et);


        nameTv.setText(multiCoAplcNameStr);
        residenceVerifTv.setText(residVerifStr);
        officeBusiVerifTv.setText(offiBusiStr);
        tpcResidVerifTv.setText(tpcResiStr);
        tpcWorkTv.setText(tpcWorkStr);
        itrVerifTv.setText(itrVerifStr);
        tdsCertifTv.setText(tdsCertifStr);
        bankStateVerifTv.setText(bankVerifStr);
        changeProperTv.setText(changePropVerifStr);
        panTv.setText(panStr);
        aadhaarTv.setText(aadhaarStr);
        electricTv.setText(electricStr);
        drivTv.setText(drivingStr);

        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean editClicked = false;
                openAlertDialogForDeleteLayout(setDatalayout, multiCoAplcNameStr, residVerifStr, offiBusiStr,
                        tpcResiStr, tpcWorkStr, itrVerifStr, tdsCertifStr, bankVerifStr, changePropVerifStr,
                        panStr, aadhaarStr, electricStr, drivingStr, editClicked);

            }
        });

        editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coApName = "", coApResidence = "", coApOffice = "", cpAltpcResid = "", tpcWork = "", itrVerif = "", tdsCertify = "",
                        bankState = "", changeProper = "", pan = "", aadhaar = "", electric = "", drive = "";

                coApName = nameTv.getText().toString();
                coApResidence = residenceVerifTv.getText().toString();
                coApOffice = officeBusiVerifTv.getText().toString();
                cpAltpcResid = tpcResidVerifTv.getText().toString();
                tpcWork = tpcWorkTv.getText().toString();
                itrVerif = itrVerifTv.getText().toString();
                tdsCertify = tdsCertifTv.getText().toString();
                bankState = bankStateVerifTv.getText().toString();
                changeProper = changeProperTv.getText().toString();
                pan = panTv.getText().toString();
                aadhaar = aadhaarTv.getText().toString();
                electric = electricTv.getText().toString();
                drive = drivTv.getText().toString();


                boolean editClicked = true;
                openAlertDialogForDeleteLayout(setDatalayout, multiCoAplcNameStr, residVerifStr, offiBusiStr,
                        tpcResiStr, tpcWorkStr, itrVerifStr, tdsCertifStr, bankVerifStr, changePropVerifStr,
                        panStr, aadhaarStr, electricStr, drivingStr, editClicked);

                editInLayoutData(coApName, coApResidence, coApOffice, cpAltpcResid, tpcWork, itrVerif, tdsCertify,
                        bankState, changeProper, pan, aadhaar, electric, drive);


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

        multiCoApplicLayout.addView(setDatalayout);

    }

    private void editInLayoutData(String coApName, String coApResidence, String coApOffice, String cpAltpcResid,
                                  String tpcWork, String itrVerif, String tdsCertify, String bankState,
                                  String changeProper, String pan, String aadhaar, String electric, String drive) {


        final AlertDialog.Builder alert = new AlertDialog.Builder(RegisteredPageActivity.this);
        View v = getLayoutInflater().inflate(R.layout.registered_page_multi_applicant, null, false);
        EditText multiAplcName, panEt, aadhaarEt, electricityEt, drivingEt;
        Spinner residVerifSp, offiBusiSp, tpcResiSp, tcpWorkSp, itrVerifSp, tdsCertif, bankVerif, changePropVerifSp;
        RelativeLayout canceLL, doneLL, progressLL;

        progressLL = v.findViewById(R.id.multi_co_aplc_progress_layout);
        multiAplcName = v.findViewById(R.id.regis_multi_appli_client_name_et);
        residVerifSp = v.findViewById(R.id.regis_multi_appli_residence_verif_sp);
        offiBusiSp = v.findViewById(R.id.regis_multi_appli_office_business_verif_sp);
        tpcResiSp = v.findViewById(R.id.regis_multi_appli_tpc_residence_sp);
        tcpWorkSp = v.findViewById(R.id.regis_multi_appli_work_sp);
        itrVerifSp = v.findViewById(R.id.regis_multi_appli_itr_verif_sp);
        tdsCertif = v.findViewById(R.id.regis_multi_appli_payslip_sp);
        bankVerif = v.findViewById(R.id.regis_multi_appli_bank_statement_verif_sp);
        changePropVerifSp = v.findViewById(R.id.regis_multi_appli_change_prop_verif);
        panEt = v.findViewById(R.id.regis_multi_appli_pan_et);
        aadhaarEt = v.findViewById(R.id.regis_multi_appli_aadhar_et);
        electricityEt = v.findViewById(R.id.regis_multi_appli_electricity_et);
        drivingEt = v.findViewById(R.id.regis_multi_appli_driving_lic_et);
        canceLL = v.findViewById(R.id.layout_multi_cancel);
        doneLL = v.findViewById(R.id.layout_multi_done);


        //////-0------------------------------------------------set all data
        multiAplcName.setText(coApName);
        //add spinner valur
        //////////residence
        ArrayAdapter coApResidAdap = (ArrayAdapter) residVerifSp.getAdapter();
        int coApResidAdapPosition = coApResidAdap.getPosition(coApResidence);
        residVerifSp.setSelection(coApResidAdapPosition);
        //////office
        ArrayAdapter coApOfficeAdap = (ArrayAdapter) offiBusiSp.getAdapter();
        int coApOfficeAdapPosition = coApOfficeAdap.getPosition(coApOffice);
        offiBusiSp.setSelection(coApOfficeAdapPosition);
        //////tcp residenc
        ArrayAdapter coApTpcResiAdap = (ArrayAdapter) tpcResiSp.getAdapter();
        int coApTpcResiAdapPosition = coApTpcResiAdap.getPosition(cpAltpcResid);
        tpcResiSp.setSelection(coApTpcResiAdapPosition);
        //////tcp work
        ArrayAdapter coApTpcWorkAdap = (ArrayAdapter) tcpWorkSp.getAdapter();
        int coApTpcWorkAdapPosition = coApTpcWorkAdap.getPosition(tpcWork);
        tcpWorkSp.setSelection(coApTpcWorkAdapPosition);
        //////tcp itr verify
        ArrayAdapter coApItrVerifAdap = (ArrayAdapter) itrVerifSp.getAdapter();
        int coApItrVerifAdapPosition = coApItrVerifAdap.getPosition(itrVerif);
        itrVerifSp.setSelection(coApItrVerifAdapPosition);
        //////tds certificates
        ArrayAdapter coApTdsCertifAdap = (ArrayAdapter) tdsCertif.getAdapter();
        int coApTdsCertifAdapPosition = coApTdsCertifAdap.getPosition(tdsCertify);
        tdsCertif.setSelection(coApTdsCertifAdapPosition);
        //////bank statement
        ArrayAdapter bankVerifAdap = (ArrayAdapter) bankVerif.getAdapter();
        int bankVerifAdapPosition = bankVerifAdap.getPosition(bankState);
        bankVerif.setSelection(bankVerifAdapPosition);
        //////chagne Property
        ArrayAdapter chagnePropertyAdap = (ArrayAdapter) changePropVerifSp.getAdapter();
        int chagnePropertyAdapPosition = chagnePropertyAdap.getPosition(changeProper);
        changePropVerifSp.setSelection(chagnePropertyAdapPosition);
        //add spinner value
        ////
        panEt.setText(pan);
        aadhaarEt.setText(aadhaar);
        electricityEt.setText(electric);
        drivingEt.setText(drive);
        //////-0-------------------------------------------set all data


        residVerifSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (!(position == 0)) {
                    multiCoAplcDialogresidVerifStr = residVerifSp.getSelectedItem().toString();
                    residVerifSp.setFocusable(false);
                } else
                    residVerifSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        offiBusiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogoffiBusiStr = adapterView.getItemAtPosition(i).toString();
                    offiBusiSp.setFocusable(false);
                } else
                    offiBusiSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tpcResiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtpcResiStr = adapterView.getItemAtPosition(i).toString();
                    tpcResiSp.setFocusable(false);
                } else
                    tpcResiSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tcpWorkSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtcpWorkStr = adapterView.getItemAtPosition(i).toString();
                    tcpWorkSp.setFocusable(false);
                } else
                    tcpWorkSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        itrVerifSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogitrVerifStr = adapterView.getItemAtPosition(i).toString();
                    itrVerifSp.setFocusable(false);
                } else
                    itrVerifSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tdsCertif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogtdsCertifStr = adapterView.getItemAtPosition(i).toString();
                    tdsCertif.setFocusable(false);
                } else
                    tdsCertif.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bankVerif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogbankVerifStr = adapterView.getItemAtPosition(i).toString();
                    bankVerif.setFocusable(false);
                } else
                    bankVerif.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        changePropVerifSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(adapterView.getSelectedItemPosition() == 0)) {
                    multiCoAplcDialogchangePropVerifStr = adapterView.getItemAtPosition(i).toString();
                    changePropVerifSp.setFocusable(false);
                } else
                    changePropVerifSp.setFocusable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        multiCoApplicLayout.addView(alert);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        canceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        doneLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (multiAplcName.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant Name require...!");
                    return;
                } else {
                    multiCoAplcDialogNameStr = multiAplcName.getText().toString();

                }
                if (panEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant PAN card number require...!");

                    return;
                } else {
                    multiCoAplcDialogpanStr = panEt.getText().toString();

                }

                if (aadhaarEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant Aadhaar number require...!");

                    return;
                } else {
                    multiCoAplcDialogaadhaarStr = aadhaarEt.getText().toString();

                }

                if (electricityEt.getText().toString().isEmpty()) {
                    snackBarMsg("Co-Applicant electricity number require...!");

                    return;
                } else {
                    multiCoAplcDialogelectricStr = electricityEt.getText().toString();
                }

                if (drivingEt.getText().toString().isEmpty()) {
                    multiCoAplcDialogdrivingStr = "";
                } else
                    multiCoAplcDialogdrivingStr = drivingEt.getText().toString();


                progressLL.setVisibility(View.VISIBLE);
                validateMultiData(multiCoAplcDialogNameStr, multiCoAplcDialogpanStr, multiCoAplcDialogaadhaarStr, multiCoAplcDialogelectricStr, multiCoAplcDialogdrivingStr,
                        multiCoAplcDialogresidVerifStr, multiCoAplcDialogoffiBusiStr, multiCoAplcDialogtpcResiStr, multiCoAplcDialogtcpWorkStr, multiCoAplcDialogitrVerifStr,
                        multiCoAplcDialogtdsCertifStr, multiCoAplcDialogbankVerifStr, multiCoAplcDialogchangePropVerifStr);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void initData() {
        backIv = findViewById(R.id.registered_doc_back_btn_iv);
        applicantLL = findViewById(R.id.applicant_linear);
        addMultiCoApplic = findViewById(R.id.layout_regis_add);

        aplcExpandIv = findViewById(R.id.applicant_expand_iv);

        multiCoApplicLayout = findViewById(R.id.registered_page_add_multi_applicant);

        //aplc
        aplcNameEt = findViewById(R.id.regis_aplc_client_name_et);
        aplcResidenceSp = findViewById(R.id.regis_aplc_residence_verif_sp);
        aplcOfficeSp = findViewById(R.id.regis_aplc_office_business_verif_sp);
        aplcTpcResidenceSp = findViewById(R.id.regis_aplc_tpc_residence_sp);
        aplcTcpWorkSp = findViewById(R.id.regis_aplc_work_sp);
        aplcItrVerifSp = findViewById(R.id.regis_aplc_itr_verif_sp);
        aplcTdsCertifSp = findViewById(R.id.regis_aplc_payslip_sp);
        aplcBankStamentSp = findViewById(R.id.regis_aplc_bank_statement_verif_sp);
        aplcChangePropSp = findViewById(R.id.regis_aplc_change_prop_verif);
        aplcPanEt = findViewById(R.id.regis_aplc_pan_et);
        aplcAadhaarEt = findViewById(R.id.regis_aplc_aadhar_et);
        aplcElectricEt = findViewById(R.id.regis_aplc_electricity_et);
        aplcDrivingEt = findViewById(R.id.regis_aplc_driving_lic_et);
        //aplc


        submitRL = findViewById(R.id.layout_regis_submit);

        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        progressdialog = new ProgressDialog(RegisteredPageActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);

    }

    private void openAlertDialogForDeleteLayout(View setDatalayout, String multiCoAplcNameStr, String residVerifStr,
                                                String offiBusiStr, String tpcResiStr, String tpcWorkStr, String itrVerifStr,
                                                String tdsCertifStr, String bankVerifStr, String changePropVerifStr,
                                                String panStr, String aadhaarStr, String electricStr, String drivingStr, boolean editClicked) {

        if (editClicked) {
            NameArray.remove(multiCoAplcNameStr);
            residVerifArray.remove(residVerifStr);
            officeArray.remove(offiBusiStr);
            tpcResidArray.remove(tpcResiStr);
            tpcWorkArray.remove(tpcWorkStr);
            itrVerifArray.remove(itrVerifStr);
            tdsCertifArray.remove(tdsCertifStr);
            bankStatemArray.remove(bankVerifStr);
            changePropArray.remove(changePropVerifStr);
            panArray.remove(panStr);
            aadhaarArray.remove(aadhaarStr);
            electricArray.remove(electricStr);
            drivingArray.remove(drivingStr);

            multiCoApplicLayout.removeView(setDatalayout);
            for (int i = 0; i < NameArray.size(); i++) {
                Log.e("Register CoApplicnat",
                        "coAplcName: " + NameArray.get(i) +
                                "\nResiname" + residVerifArray.get(i) +
                                "\nOffice: " + officeArray.get(i));

            }
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to submit?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    NameArray.remove(multiCoAplcNameStr);
                    residVerifArray.remove(residVerifStr);
                    officeArray.remove(offiBusiStr);
                    tpcResidArray.remove(tpcResiStr);
                    tpcWorkArray.remove(tpcWorkStr);
                    itrVerifArray.remove(itrVerifStr);
                    tdsCertifArray.remove(tdsCertifStr);
                    bankStatemArray.remove(bankVerifStr);
                    changePropArray.remove(changePropVerifStr);
                    panArray.remove(panStr);
                    aadhaarArray.remove(aadhaarStr);
                    electricArray.remove(electricStr);
                    drivingArray.remove(drivingStr);

                    multiCoApplicLayout.removeView(setDatalayout);
                    for (int i = 0; i < NameArray.size(); i++) {
                        Log.e("Register CoApplicnat",
                                "coAplcName: " + NameArray.get(i) +
                                        "\nResiname" + residVerifArray.get(i) +
                                        "\nOffice: " + officeArray.get(i));

                    }

//                SharedPrefDocuComplete.getInstance(getApplicationContext()).setRegistereduserData(true);
//                finish();
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

    private void openAlertDialogForSubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to submit?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                ApplicantDataToInsert();
//                SharedPrefDocuComplete.getInstance(getApplicationContext()).setRegistereduserData(true);
//                finish();
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

    private void Co_AplicantDataToInsert(String coAplcName, String coAplcResidence, String coAplcOffice,
                                         String coAplcTPCResidence, String coAplcWork, String coAplcItrVerif,
                                         String coAplcTdsVerif, String coAplcBankVerif, String coAplcChageProp,
                                         String coAplcPan, String coAplcAadhaar, String coAplcElectric, String coAplcDriving) {


//        progressdialog.show();

        registerInterface anInterface = ApiClient.getRetrofitInstance().create(registerInterface.class);
        Call<coAplcResponse> call = anInterface.RegisteredCoApplicantCall(addDataIdIntentStr, executiveId, coAplcName, coAplcResidence,
                coAplcOffice, coAplcTPCResidence, coAplcWork, coAplcItrVerif, coAplcTdsVerif, coAplcBankVerif,
                coAplcChageProp, coAplcPan, coAplcAadhaar, coAplcElectric, coAplcDriving);
        call.enqueue(new Callback<coAplcResponse>() {
            @Override
            public void onResponse(Call<coAplcResponse> call, Response<coAplcResponse> response) {
                try {
                    if (response.body().status == 4) {
                        Log.d("coApplicant Data", response.body().result.co_applicant_name);
                        snackBarMsg("Message: " + response.body().msg);

                        Toast.makeText(RegisteredPageActivity.this,
                                "Message: " + response.body().msg, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("Upload coAplc error", response.body().msg);
                    }
                } catch (Exception e) {

                    Log.e("coAplc exception", "" + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<coAplcResponse> call, Throwable t) {
                Log.e("coAplc onFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });
    }


    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void ApplicantDataToInsert() {

        if (aplcNameEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Applicant Name Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcNameStr = aplcNameEt.getText().toString();


        if (aplcResidenceSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Residence Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcResidenceStr = aplcResidenceSp.getSelectedItem().toString();

        if (aplcOfficeSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Office/Business Verif is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcOfficeStr = aplcOfficeSp.getSelectedItem().toString();

        if (aplcTpcResidenceSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "TCP Residence Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcTpcResidenceStr = aplcTpcResidenceSp.getSelectedItem().toString();

        if (aplcTcpWorkSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "TCP Work Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcTcpWorkStr = aplcTcpWorkSp.getSelectedItem().toString();

        if (aplcItrVerifSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "ITR Verification Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcItrVerifStr = aplcItrVerifSp.getSelectedItem().toString();

        if (aplcTdsCertifSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "TDS certificate Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcTdsCertifStr = aplcTdsCertifSp.getSelectedItem().toString();

        if (aplcBankStamentSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Bank Statement Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcBankStamentStr = aplcBankStamentSp.getSelectedItem().toString();

        if (aplcChangePropSp.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Change Property Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcChangePropStr = aplcChangePropSp.getSelectedItem().toString();

        if (aplcPanEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Pan card Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcPanStr = aplcPanEt.getText().toString();

        if (aplcAadhaarEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Aadhaar card Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcAadhaarStr = aplcAadhaarEt.getText().toString();

        if (aplcElectricEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Electric Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcElectricStr = aplcElectricEt.getText().toString();

        if (aplcDrivingEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Driving Field is Empty!", Toast.LENGTH_SHORT).show();
            return;
        } else
            aplcDrivingStr = aplcDrivingEt.getText().toString();


        aplcantDataToDatabase(aplcNameStr, aplcResidenceStr, aplcOfficeStr, aplcTpcResidenceStr, aplcTcpWorkStr,
                aplcItrVerifStr, aplcTdsCertifStr, aplcBankStamentStr, aplcChangePropStr, aplcPanStr, aplcAadhaarStr,
                aplcElectricStr, aplcDrivingStr);

    }

    private void aplcantDataToDatabase(String aplcNameStr, String aplcResidenceStr, String aplcOfficeStr,
                                       String aplcTpcResidenceStr, String aplcTcpWorkStr, String aplcItrVerifStr,
                                       String aplcTdsCertifStr, String aplcBankStamentStr, String aplcChangePropStr,
                                       String aplcPanStr, String aplcAadhaarStr, String aplcElectricStr, String aplcDrivingStr) {
        Log.d("Aplc Data", aplcNameStr + "\n" + aplcResidenceStr + "\n" + aplcOfficeStr + "\n" + aplcTpcResidenceStr + "\n" + aplcTcpWorkStr + "\n" +
                aplcItrVerifStr + "\n" + aplcTdsCertifStr + "\n" + aplcBankStamentStr + "\n" + aplcChangePropStr + "\n" + aplcPanStr + "\n" + aplcAadhaarStr + "\n" +
                aplcElectricStr + "\n" + aplcDrivingStr);


        progressdialog.show();

        registerInterface anInterface = ApiClient.getRetrofitInstance().create(registerInterface.class);
        Call<registeredResponse> call = anInterface.RegisteredApplicantCall(addDataIdIntentStr, executiveId, aplcNameStr, aplcResidenceStr, aplcOfficeStr,
                aplcTpcResidenceStr, aplcTcpWorkStr, aplcItrVerifStr, aplcTdsCertifStr, aplcBankStamentStr, aplcChangePropStr,
                aplcPanStr, aplcAadhaarStr, aplcElectricStr, aplcDrivingStr);
        call.enqueue(new Callback<registeredResponse>() {
            @Override
            public void onResponse(Call<registeredResponse> call, Response<registeredResponse> response) {
                try {
                    if (response.body().status == 4) {
                        Log.d("Data aplc loaded", response.body().result.applicant_name);

                        Log.e("required Data",
                                "Applicant Name: " + response.body().result.applicant_name
                                        + "\nApplicant Id: " + response.body().applicant_id
                                        + "\nExecutive Id: " + executiveId);

                        SharedPrefAuth.getInstance(getApplicationContext()).setAplcId(getApplicationContext(),
                                response.body().applicant_id);

                        snackBarMsg("Message: " + response.body().msg);

                        int count = 0;
                        //upload data of coApplicant
                        for (int i = 0; i < NameArray.size(); i++) {
                            Log.e("Register CoApplicnat",
                                    "coAplcName: " + NameArray.get(i) +
                                            "\nResiname: " + residVerifArray.get(i) +
                                            "\nOffice: " + officeArray.get(i));

                            Co_AplicantDataToInsert(
                                    NameArray.get(i),
                                    residVerifArray.get(i),
                                    officeArray.get(i),
                                    tpcResidArray.get(i),
                                    tpcWorkArray.get(i),
                                    itrVerifArray.get(i),
                                    tdsCertifArray.get(i),
                                    bankStatemArray.get(i),
                                    changePropArray.get(i),
                                    panArray.get(i),
                                    aadhaarArray.get(i),
                                    electricArray.get(i),
                                    drivingArray.get(i)
                            );
                            count++;
                        }
                        if (NameArray.size() == count) {
                            SharedPrefDocuComplete.getInstance(getApplicationContext()).setRegistereduserData(true);
                            RegisteredPageActivity.this.onBackPressed();
                        }
                    } else {
                        progressdialog.dismiss();
                        Log.e("Upload aplc error", response.body().msg);
                    }
                    Log.e("Aplc status", String.valueOf(response.body().status));
                } catch (Exception e) {
                    progressdialog.dismiss();
                    Log.e("Aplc upload", " data exception: " + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<registeredResponse> call, Throwable t) {
                Log.e("Aplc onFailure", t.getMessage());
                progressdialog.dismiss();
            }
        });
    }


}