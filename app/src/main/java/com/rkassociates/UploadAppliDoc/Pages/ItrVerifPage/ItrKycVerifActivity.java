package com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefApplicantInfo;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.ReadData.ItrKycReadResponse;
import com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.itrKycVerifInterface;
import com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.itrResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItrKycVerifActivity extends AppCompatActivity {

    private ProgressDialog progressdialog;
    private String executiveId, aplcIdtr;
    private ImageView backIv;
    private CheckBox aplcAsPerItrCb,aplcAasPerForm16Cb;
    private EditText aplcItrFrom1Et, aplcItrFrom2Et, aplcItrFrom3Et, aplcItrTo1Et, aplcItrTo2Et, aplcItrTo3Et, aplcItrYearRemarkEt;
    private EditText aplcNameEt, aplcPanEt, aplcPanRemarkEt, aplcAadhaarEt, aplcAadhaarRemarkEt, aplcElectricEt,
            aplcElectricRemarkEt, aplcBankEt, aplcBankRemarkEt, aplcRemarkEt;

    private CheckBox coAplcAsPerItrCb,coAplcAasPerForm16Cb;
    private EditText coAplcItrFrom1Et, coAplcItrFrom2Et, coAplcItrFrom3Et, coAplcItrTo1Et, coAplcItrTo2Et, coAplcItrTo3Et, coAplcItrYearRemarkEt;
    private EditText coAplcNameEt, coAplcPanEt, coAplcPanRemarkEt, coAplcAadhaarEt, coAplcAadhaarRemarkEt,
            coAplcElectricEt, coAplcElectricRemarkEt, coAplcBankEt, coAplcBankRemarkEt, coAplcRemarkEt;

    private RelativeLayout itrKycSubmitRL;
    private String activityFor, addDataIdIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itr_kyc_verif);

        initData();

//        setSpinnerYear();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            addDataIdIntentStr = extras.getString("addDataIdIntentStr");
            //The key argument here must match that used in the other activity

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId", addDataIdIntentStr);
                getDataFromDatabase();
            } else if (activityFor.equals("newData")) {
                String aplcName = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcName(getApplicationContext());
                aplcNameEt.setText(aplcName);

                String aplcPan = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcPanCard(getApplicationContext());
                String aplcAadhar = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcAadharCard(getApplicationContext());
                String aplcElectric = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcElectricCard(getApplicationContext());

                aplcPanEt.setText(aplcPan);
                aplcAadhaarEt.setText(aplcAadhar);
                aplcElectricEt.setText(aplcElectric);

                String coAplcPan = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getCoAplcPanCard(getApplicationContext());
                String coAplcAadhar = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getCoAplcAadharCard(getApplicationContext());
                String coAplcElectric = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getCoAplcElectricCard(getApplicationContext());

                coAplcPanEt.setText(coAplcPan);
                coAplcAadhaarEt.setText(coAplcAadhar);
                coAplcElectricEt.setText(coAplcElectric);

            }
        }

        operations();
    }

    private void getDataFromDatabase() {


        progressdialog.show();

        itrKycVerifInterface itrKycVerifInterface = ApiClient.getRetrofitInstance().create(itrKycVerifInterface.class);
        Call<ItrKycReadResponse> call = itrKycVerifInterface.itrKycVefiRead(addDataIdIntentStr, executiveId);

        call.enqueue(new Callback<ItrKycReadResponse>() {
            @Override
            public void onResponse(Call<ItrKycReadResponse> call, Response<ItrKycReadResponse> response) {
                if (response.body().getStatus().equals("success")) {

                    snackBarMsg("Message: " + response.body().getMessage());

                    Toast.makeText(ItrKycVerifActivity.this, "Message: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("itr response Data", String.valueOf(response.body().getItrKycReadResult().getItrKycVerificationTable()));

                    setDataToView(
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getApplicantName(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getYear1(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getYear2(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getYear3(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getYearRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getPancardNumber(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getPancardRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getAadharcardNumber(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getAadharcardRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getElectricityBill(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getElectricityRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getBankName(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getBankRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getFinalRemark(),

                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantName(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantYear1(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantYear2(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantYear3(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantPancardNumber(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantPancardRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantAadharcardNumber(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantAadharcardRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantElectricityBill(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantElectricityRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantBankName(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantBankRemark(),
                            response.body().getItrKycReadResult().getItrKycVerificationTable().getCoApplicantFinalRemark()
                    );

                } else {
                    snackBarMsg("Msg: " + response.body().getMessage() + "\nStatus: " + response.body().getStatus());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<ItrKycReadResponse> call, Throwable t) {

                Log.e("itrKyc_insert", "OnFailure" + t.getMessage());
                progressdialog.dismiss();
            }
        });

    }

    private void setDataToView(String aplcNameStr, String year1, String year2, String year3, String yearRemark, String pancardNumber,
                               String pancardRemark, String aadharcardNumber, String aadharcardRemark,
                               String electricityBill, String electricityRemark, String bankStr, String bankRemarkStr, String finalRemark,
                               String coAplcNameStr, String coApplicantYear1, String coApplicantYear2, String coApplicantYear3,
                               String coApplicantPancardNumber, String coApplicantPancardRemark,
                               String coApplicantAadharcardNumber, String coApplicantAadharcardRemark,
                               String coApplicantElectricityBill, String coApplicantElectricityRemark,
                               String coApplicantBankName, String coApplicantBankRemark, String coApplicantFinalRemark) {


        Log.e("TAG", "setDataToView: " + year1);

        aplcNameEt.setText(aplcNameStr);

        String[] itemsYear1 = year1.split("-");
        aplcItrFrom1Et.setText(itemsYear1[0]);
        aplcItrTo1Et.setText(itemsYear1[1]);

        String[] itemsYear2 = year2.split("-");
        aplcItrFrom2Et.setText(itemsYear2[0]);
        aplcItrTo2Et.setText(itemsYear2[1]);

        String[] itemsYear3 = year3.split("-");
        aplcItrFrom3Et.setText(itemsYear3[0]);
        aplcItrTo3Et.setText(itemsYear3[1]);

        aplcItrYearRemarkEt.setText(yearRemark);
        aplcPanEt.setText(pancardNumber);
        aplcPanRemarkEt.setText(pancardRemark);
        aplcAadhaarEt.setText(aadharcardNumber);
        aplcAadhaarRemarkEt.setText(aadharcardRemark);
        aplcElectricEt.setText(electricityBill);
        aplcElectricRemarkEt.setText(electricityRemark);
        aplcBankEt.setText(bankStr);
        aplcBankRemarkEt.setText(bankRemarkStr);
        aplcRemarkEt.setText(finalRemark);

        coAplcNameEt.setText(coAplcNameStr);

        String[] itemsCoAplcYear1 = coApplicantYear1.split("-");
        coAplcItrFrom1Et.setText(itemsCoAplcYear1[0]);
        coAplcItrTo1Et.setText(itemsCoAplcYear1[1]);

        String[] itemsCoAplcYear2 = coApplicantYear2.split("-");
        coAplcItrFrom2Et.setText(itemsCoAplcYear2[0]);
        coAplcItrTo2Et.setText(itemsCoAplcYear2[1]);

        String[] itemsCoAplcYear3 = coApplicantYear3.split("-");
        coAplcItrFrom3Et.setText(itemsCoAplcYear3[0]);
        coAplcItrTo3Et.setText(itemsCoAplcYear3[1]);

        coAplcPanEt.setText(coApplicantPancardNumber);
        coAplcPanRemarkEt.setText(coApplicantPancardRemark);
        coAplcAadhaarEt.setText(coApplicantAadharcardNumber);
        coAplcAadhaarRemarkEt.setText(coApplicantAadharcardRemark);
        coAplcElectricEt.setText(coApplicantElectricityBill);
        coAplcElectricRemarkEt.setText(coApplicantElectricityRemark);
        coAplcBankEt.setText(coApplicantBankName);
        coAplcBankRemarkEt.setText(coApplicantBankRemark);
        coAplcRemarkEt.setText(coApplicantFinalRemark);

    }

    private void operations() {

        aplcAsPerItrCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    aplcAasPerForm16Cb.setChecked(false);
                }
            }
        });
        aplcAasPerForm16Cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    aplcAsPerItrCb.setChecked(false);
                }
            }
        });
        coAplcAsPerItrCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    coAplcAasPerForm16Cb.setChecked(false);
                }
            }
        });
        coAplcAasPerForm16Cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    coAplcAsPerItrCb.setChecked(false);
                }
            }
        });
        aplcItrFrom1Et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.equals("") && editable.length() > 3) {

                    double lastYear = Double.parseDouble(aplcItrFrom1Et.getText().toString());
                    double one = Double.parseDouble("1");


                    double forAplcTo1EtStr = lastYear + one;

                    double forAplcFrom2Et = lastYear - 1;
                    double forAplcTo2Et = lastYear;

                    double forAplcFrom3Et = forAplcFrom2Et - 1;
                    double forAplcTo3Et = forAplcFrom2Et;

//                    aplcItrFrom1Et.setText(Double.toString(dataStr);
                    aplcItrTo1Et.setText(String.format("%.0f", forAplcTo1EtStr));

                    aplcItrFrom2Et.setText(String.format("%.0f", forAplcFrom2Et));
                    aplcItrTo2Et.setText(String.format("%.0f", forAplcTo2Et));

                    aplcItrFrom3Et.setText(String.format("%.0f", forAplcFrom3Et));
                    aplcItrTo3Et.setText(String.format("%.0f", forAplcTo3Et));


                }
            }
        });

        coAplcItrFrom1Et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.equals("") && editable.length() > 3) {

                    double lastYear = Double.parseDouble(coAplcItrFrom1Et.getText().toString());
                    double one = Double.parseDouble("1");

                    double forAplcTo1EtStr = lastYear + one;

                    double forAplcFrom2Et = lastYear - 1;
                    double forAplcTo2Et = lastYear;

                    double forAplcFrom3Et = forAplcFrom2Et - 1;
                    double forAplcTo3Et = forAplcFrom2Et;

//                    aplcItrFrom1Et.setText(Double.toString(dataStr);
                    coAplcItrTo1Et.setText(String.format("%.0f", forAplcTo1EtStr));

                    coAplcItrFrom2Et.setText(String.format("%.0f", forAplcFrom2Et));
                    coAplcItrTo2Et.setText(String.format("%.0f", forAplcTo2Et));

                    coAplcItrFrom3Et.setText(String.format("%.0f", forAplcFrom3Et));
                    coAplcItrTo3Et.setText(String.format("%.0f", forAplcTo3Et));


                }
            }
        });


        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        itrKycSubmitRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDataForDatabase();

//                Toast.makeText(ItrKycVerifActivity.this, "Data Submit", Toast.LENGTH_SHORT).show();
//                ItrKycVerifActivity.super.onBackPressed();
            }
        });
    }

    private void setDataForDatabase() {
        String aplcNameStr, aplcItrYear1Str, aplcItrYear2Str, aplcItrYear3Str, aplcItrYearRemarkStr, aplcPanStr, aplcPanRemarkStr, aplcAadhaarStr, aplcAadhaarRemarkStr,
                aplcEletricStr, aplcEletricRemarkStr, aplcBankStr, aplcBankRemarkStr, aplcRemarkStr;

        String coAplcNameStr, coAplcItrYear1Str, coAplcItrYear2Str, coAplcItrYear3Str, coAplcItrYearRemarkStr, coAplcPanStr, coAplcPanRemarkStr, coAplcAadhaarStr, coAplcAadhaarRemarkStr,
                coAplcEletricStr, coAplcEletricRemarkStr, coAplcBankStr, coAplcBankRemarkStr, coAplcRemarkStr;

        if (aplcNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Applicant name getting empty...!!!");
            return;
        } else
            aplcNameStr = aplcNameEt.getText().toString();

        if (aplcItrFrom1Et.getText().toString().isEmpty()) {
            snackBarMsg("applicant Year getting empty...!!!");
            return;
        } else {
            aplcItrYear1Str = aplcItrFrom1Et.getText().toString() + "-" + aplcItrTo1Et.getText().toString();
            aplcItrYear2Str = aplcItrFrom2Et.getText().toString() + "-" + aplcItrTo2Et.getText().toString();
            aplcItrYear3Str = aplcItrFrom2Et.getText().toString() + "-" + aplcItrTo3Et.getText().toString();
        }

        aplcItrYearRemarkStr = aplcItrYearRemarkEt.getText().toString();

        if (aplcPanEt.getText().toString().isEmpty()) {
            snackBarMsg("Pan Card getting empty...!!!");
            return;
        } else
            aplcPanStr = aplcPanEt.getText().toString();

        aplcPanRemarkStr = aplcPanRemarkEt.getText().toString();

        if (aplcAadhaarEt.getText().toString().isEmpty()) {
            snackBarMsg("Aadhaar card getting empty...!!!");
            return;
        } else
            aplcAadhaarStr = aplcAadhaarEt.getText().toString();

        aplcAadhaarRemarkStr = aplcAadhaarRemarkEt.getText().toString();

        if (aplcElectricEt.getText().toString().isEmpty()) {
            snackBarMsg("Electric getting empty...!!!");
            return;
        } else
            aplcEletricStr = aplcElectricEt.getText().toString();

        aplcEletricRemarkStr = aplcElectricRemarkEt.getText().toString();

        if (aplcBankEt.getText().toString().isEmpty()) {
            snackBarMsg("Bank getting empty...!!!");
            return;
        } else
            aplcBankStr = aplcBankEt.getText().toString();
        aplcBankRemarkStr = aplcBankRemarkEt.getText().toString();
        aplcRemarkStr = aplcRemarkEt.getText().toString();


        if (coAplcNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Co-Applicant name getting empty...!!!");
            return;
        } else
            coAplcNameStr = coAplcNameEt.getText().toString();

        if (coAplcItrFrom1Et.getText().toString().isEmpty()) {
            snackBarMsg("Co-applicant year getting empty...!!!");
            return;
        } else {
            coAplcItrYear1Str = coAplcItrFrom1Et.getText().toString() + "-" + coAplcItrTo1Et.getText().toString();
            coAplcItrYear2Str = coAplcItrFrom2Et.getText().toString() + "-" + coAplcItrTo2Et.getText().toString();
            coAplcItrYear3Str = coAplcItrFrom3Et.getText().toString() + "-" + coAplcItrTo3Et.getText().toString();
        }
        coAplcItrYearRemarkStr = coAplcItrYearRemarkEt.getText().toString();

        if (coAplcPanEt.getText().toString().isEmpty()) {
            snackBarMsg("Co-Applicant Pan card getting empty...!!!");
            return;
        } else
            coAplcPanStr = coAplcPanEt.getText().toString();
        coAplcPanRemarkStr = coAplcPanRemarkEt.getText().toString();

        if (coAplcAadhaarEt.getText().toString().isEmpty()) {
            snackBarMsg("Co-Applicant Aadhaar number getting empty...!!!");
            return;
        } else
            coAplcAadhaarStr = coAplcAadhaarEt.getText().toString();
        coAplcAadhaarRemarkStr = coAplcAadhaarRemarkEt.getText().toString();

        if (coAplcElectricEt.getText().toString().isEmpty()) {
            snackBarMsg("Co-Applicant Electric getting empty...!!!");
            return;
        } else
            coAplcEletricStr = coAplcElectricEt.getText().toString();
        coAplcEletricRemarkStr = coAplcElectricRemarkEt.getText().toString();

        if (coAplcBankEt.getText().toString().isEmpty()) {

            snackBarMsg("Co-Applicant Bank getting empty...!!!");
            return;
        } else
            coAplcBankStr = coAplcBankEt.getText().toString();
        coAplcBankRemarkStr = coAplcBankRemarkEt.getText().toString();

        coAplcRemarkStr = coAplcRemarkEt.getText().toString();

        uploadToDatabase(aplcIdtr, aplcNameStr, aplcItrYear1Str, aplcItrYear2Str, aplcItrYear3Str, aplcItrYearRemarkStr, aplcPanStr, aplcPanRemarkStr,
                aplcAadhaarStr, aplcAadhaarRemarkStr, aplcEletricStr, aplcEletricRemarkStr, aplcBankStr, aplcBankRemarkStr, aplcRemarkStr,
                coAplcNameStr, coAplcItrYear1Str, coAplcItrYear2Str, coAplcItrYear3Str, coAplcItrYearRemarkStr, coAplcPanStr, coAplcPanRemarkStr,
                coAplcAadhaarStr, coAplcAadhaarRemarkStr, coAplcEletricStr, coAplcEletricRemarkStr, coAplcBankStr, coAplcBankRemarkStr, coAplcRemarkStr);

    }

    private void uploadToDatabase(String aplcIdStr, String aplcNameStr, String aplcItrYear1Str, String aplcItrYear2Str, String aplcItrYear3Str,
                                  String aplcItrYearRemarkStr, String aplcPanStr, String aplcPanRemarkStr, String aplcAadhaarStr,
                                  String aplcAadhaarRemarkStr, String aplcEletricStr, String aplcEletricRemarkStr, String aplcBankStr,
                                  String aplcBankRemarkStr, String aplcRemarkStr, String coAplcNameStr, String coAplcItrYear1Str,
                                  String coAplcItrYear2Str, String coAplcItrYear3Str, String coAplcItrYearRemarkStr, String coAplcPanStr,
                                  String coAplcPanRemarkStr, String coAplcAadhaarStr, String coAplcAadhaarRemarkStr,
                                  String coAplcEletricStr, String coAplcEletricRemarkStr, String coAplcBankStr,
                                  String coAplcBankRemarkStr, String coAplcRemarkStr) {

        Log.d("uploadToDatabase: ","coAplcItrYearRemarkStr: "+coAplcItrYearRemarkStr);

        progressdialog.show();

        itrKycVerifInterface itrKycVerifInterface = ApiClient.getRetrofitInstance().create(itrKycVerifInterface.class);
        Call<itrResponse> call = itrKycVerifInterface.itrKycVefiInsert(executiveId, addDataIdIntentStr, aplcNameStr, aplcItrYear1Str, aplcItrYear2Str, aplcItrYear3Str, aplcItrYearRemarkStr, aplcPanStr, aplcPanRemarkStr,
                aplcAadhaarStr, aplcAadhaarRemarkStr, aplcEletricStr, aplcEletricRemarkStr, aplcBankStr, aplcBankRemarkStr, aplcRemarkStr,
                coAplcNameStr, coAplcItrYear1Str, coAplcItrYear2Str, coAplcItrYear3Str, coAplcItrYearRemarkStr, coAplcPanStr, coAplcPanRemarkStr,
                coAplcAadhaarStr, coAplcAadhaarRemarkStr, coAplcEletricStr, coAplcEletricRemarkStr, coAplcBankStr, coAplcBankRemarkStr, coAplcRemarkStr);

        call.enqueue(new Callback<itrResponse>() {
            @Override
            public void onResponse(Call<itrResponse> call, Response<itrResponse> response) {
                if (response.body().status == 4) {
                    SharedPrefAuth.getInstance(getApplicationContext()).setAplcItrYears(
                            getApplicationContext(),
                            response.body().result.year1,
                            response.body().result.year2,
                            response.body().result.year3);

                    snackBarMsg("Message: " + response.body().msg);

                    Toast.makeText(ItrKycVerifActivity.this,
                            "Message: " + response.body().msg, Toast.LENGTH_SHORT).show();

                    Log.d("itr response Data", String.valueOf(response.body().result));

                    SharedPrefDocuComplete.getInstance(getApplicationContext()).setItrAndKycVerif(true);
                    ItrKycVerifActivity.super.onBackPressed();
                } else {
                    snackBarMsg("Msg: " + response.body().msg + "\nStatus: " + response.body().status);
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<itrResponse> call, Throwable t) {

                Log.e("itrKyc_insert", "OnFailure" + t.getMessage());
                progressdialog.dismiss();
            }
        });

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
                ItrKycVerifActivity.super.onBackPressed();
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


    private void setSpinnerYear() {

        ArrayList<String> years = new ArrayList<String>();
        String startNum = "--Select--";
        years.add(startNum);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        Collections.reverse(years);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

//        aplcItrFrom1Sp.setAdapter(adapter);
//        aplcItrFrom2Sp.setAdapter(adapter);
//        aplcItrFrom3Sp.setAdapter(adapter);

//        coAplcItrFrom1Sp.setAdapter(adapter);
//        coAplcItrFrom2Sp.setAdapter(adapter);
//        coAplcItrFrom3Sp.setAdapter(adapter);

    }

    private void initData() {
        backIv = findViewById(R.id.itr_kyc_page_back_btn_iv);

        aplcNameEt = findViewById(R.id.itr_kyc_aplc_client_name_et);
        aplcPanEt = findViewById(R.id.itr_kyc_aplc_pan_et);
        aplcPanRemarkEt = findViewById(R.id.itr_kyc_aplc_pan_remark_et);
        aplcAadhaarEt = findViewById(R.id.itr_kyc_aplc_aadhar_et);
        aplcAadhaarRemarkEt = findViewById(R.id.itr_kyc_aplc_aadhaar_remark_et);
        aplcElectricEt = findViewById(R.id.itr_kyc_aplc_electric_et);
        aplcElectricRemarkEt = findViewById(R.id.itr_kyc_aplc_electric_remark_et);
        aplcBankEt = findViewById(R.id.itr_kyc_aplc_bank_et);
        aplcBankRemarkEt = findViewById(R.id.itr_kyc_aplc_bank_remark_et);
        aplcRemarkEt = findViewById(R.id.itr_kyc_aplc_final_remark_et);

        aplcAsPerItrCb = findViewById(R.id.itr_kyc_aplc_as_per_itr_cb);
        aplcAasPerForm16Cb = findViewById(R.id.itr_kyc_aplc_as_per_form_16_cb);

        aplcItrFrom1Et = findViewById(R.id.itr_kyc_aplc_itr_from_1_sp);
        aplcItrTo1Et = findViewById(R.id.itr_kyc_aplc_itr_to_1_et);
        aplcItrFrom2Et = findViewById(R.id.itr_kyc_aplc_itr_from_2_sp);
        aplcItrTo2Et = findViewById(R.id.itr_kyc_aplc_itr_to_2_et);
        aplcItrFrom3Et = findViewById(R.id.itr_kyc_aplc_itr_from_3_sp);
        aplcItrTo3Et = findViewById(R.id.itr_kyc_aplc_itr_to_3_et);
        aplcItrYearRemarkEt = findViewById(R.id.itr_kyc_aplc_itr_remark_et);

        coAplcNameEt = findViewById(R.id.itr_kyc_co_aplc_client_name_et);
        coAplcPanEt = findViewById(R.id.itr_kyc_co_aplc_pan_et);
        coAplcPanRemarkEt = findViewById(R.id.itr_kyc_co_aplc_pan_remark_et);
        coAplcAadhaarEt = findViewById(R.id.itr_kyc_co_aplc_aadhar_et);
        coAplcAadhaarRemarkEt = findViewById(R.id.itr_kyc_co_aplc_aadhaar_remark_et);
        coAplcElectricEt = findViewById(R.id.itr_kyc_co_aplc_electric_et);
        coAplcElectricRemarkEt = findViewById(R.id.itr_kyc_co_aplc_electric_remark_et);
        coAplcBankEt = findViewById(R.id.itr_kyc_co_aplc_bank_et);
        coAplcBankRemarkEt = findViewById(R.id.itr_kyc_co_aplc_bank_remark_et);
        coAplcRemarkEt = findViewById(R.id.itr_kyc_co_aplc_final_remark_et);

        coAplcAsPerItrCb = findViewById(R.id.itr_kyc_co_aplc_as_per_itr_cb);
        coAplcAasPerForm16Cb = findViewById(R.id.itr_kyc_co_aplc_as_per_form_16_cb);

        coAplcItrFrom1Et = findViewById(R.id.itr_kyc_co_aplc_itr_from_1_sp);
        coAplcItrTo1Et = findViewById(R.id.itr_kyc_co_aplc_itr_to_1_et);
        coAplcItrFrom2Et = findViewById(R.id.itr_kyc_co_aplc_itr_from_2_sp);
        coAplcItrTo2Et = findViewById(R.id.itr_kyc_co_aplc_itr_to_2_et);
        coAplcItrFrom3Et = findViewById(R.id.itr_kyc_co_aplc_itr_from_3_sp);
        coAplcItrTo3Et = findViewById(R.id.itr_kyc_co_aplc_itr_to_3_et);
        coAplcItrYearRemarkEt = findViewById(R.id.itr_kyc_co_aplc_itr_remark_et);

        itrKycSubmitRL = findViewById(R.id.layout_itr_kyc_submit);


        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        progressdialog = new ProgressDialog(ItrKycVerifActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
    }

    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}