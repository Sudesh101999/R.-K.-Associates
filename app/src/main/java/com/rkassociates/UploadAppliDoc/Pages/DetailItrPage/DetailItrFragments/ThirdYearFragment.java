package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.DetailsitrInterface;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData.DetailsItrReadResponse;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData.DetailsOfItrTable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdYearFragment extends Fragment {

    private EditText itrGTIFEt,deducationEt,ntiEt,taxPaidEt,taxPayableEt,tdsEt,refundEt,incomeEt,perPanEt,
            returnFailledEt,returnShouldBeFilledEt,returnWereFilledEt,verificationEt,eFillingNoEt,dateOfFillingEt,
            taxChallanEt,bankNameEt,branchNameEt,acTypeEt,acNumberEt;
    private Spinner verifiedSp,originalSp;
    private String activityForStr, addDataIdIntentStr,executiveIdStr,yearStr;

    private ProgressBar progressBar;
    private RelativeLayout doneBtn;

    public ThirdYearFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_year, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        intData(view);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            activityForStr = extras.getString("activityFor");
            //The key argument here must match that used in the other activity

            if (activityForStr.equals("Pending")) {
                addDataIdIntentStr = extras.getString("addDataIdIntentStr");
                Log.d("1st Fragment: AddDataId", addDataIdIntentStr);
                getDataFromDatabase(addDataIdIntentStr,yearStr);
            }
        }
        setOperations();
    }

    private void getDataFromDatabase(String addDataIdIntentStr, String yearStr) {

        progressBar.setVisibility(View.VISIBLE);
        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<DetailsItrReadResponse> call = detailsitrInterface.detailsItrGetData(addDataIdIntentStr, executiveIdStr);

        call.enqueue(new Callback<DetailsItrReadResponse>() {
            @Override
            public void onResponse(Call<DetailsItrReadResponse> call, Response<DetailsItrReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        for (DetailsOfItrTable tableData: response.body().getResult().getDetailsOfItrTable()){
                            if (tableData.getAssessmentYear().equals(yearStr)) {
                                setData(
                                        tableData.getGti(),
                                        tableData.getDeduction(),
                                        tableData.getNti(),
                                        tableData.getTaxPaid(),
                                        tableData.getTaxPayable(),
                                        tableData.getTds(),
                                        tableData.getRefund(),
                                        tableData.getIncomeFromOtherSource(),
                                        tableData.getItWardasperpan(),
                                        tableData.getItWardReturnFilledIn(),
                                        tableData.getReturnShouldFilled(),
                                        tableData.getReturnWhereFilled(),
                                        tableData.getVerification(),
                                        tableData.geteFilling(),
                                        tableData.getDateOfFilling(),
                                        tableData.getVerified(),
                                        tableData.getTaxChallan(),
                                        tableData.getBankName(),
                                        tableData.getBranchName(),
                                        tableData.getAccountType(),
                                        tableData.getAccountNumber(),
                                        tableData.getOriginalSeen()
                                );
                            }
                        }


                    } else {
                        Toast.makeText(getContext(), "Message: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("ITR year 1 Exception", e.getMessage());
                }
                Log.d("ITR year 1 response", response.body().getMessage());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DetailsItrReadResponse> call, Throwable t) {
                Log.e("Year 1 onFailure", t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void setData(String gti, String deduction, String nti, String taxPaid, String taxPayable,
                         String tds, String refund, String incomeFromOtherSource, String itWardasperpan,
                         String itWardReturnFilledIn, String returnShouldFilled, String returnWhereFilled,
                         String verification, String geteFilling, String dateOfFilling, String verified,
                         String taxChallan, String bankName, String branchName, String accountType, String accountNumber, String originalSeen)
    {


        ArrayAdapter<CharSequence> yesNoAdapter = ArrayAdapter.
                createFromResource(getContext(),
                        R.array.yes_no_sp, android.R.layout.simple_spinner_item);

        itrGTIFEt.setText(gti);
        deducationEt.setText(deduction);
        ntiEt.setText(nti);
        taxPaidEt.setText(taxPaid);
        taxPayableEt.setText(taxPayable);
        tdsEt.setText(tds);
        refundEt.setText(refund);
        incomeEt.setText(incomeFromOtherSource);
        perPanEt.setText(itWardasperpan);
        returnFailledEt.setText(itWardReturnFilledIn);
        returnShouldBeFilledEt.setText(returnShouldFilled);
        returnWereFilledEt.setText(returnWhereFilled);
        verificationEt.setText(verification);
        eFillingNoEt.setText(geteFilling);
        dateOfFillingEt.setText(dateOfFilling);

        setSpinnerData(verified,yesNoAdapter,verifiedSp);
        taxChallanEt.setText(taxChallan);
        bankNameEt.setText(bankName);
        branchNameEt.setText(branchName);
        acTypeEt.setText(accountType);
        acNumberEt.setText(accountNumber);
        setSpinnerData(originalSeen,yesNoAdapter,originalSp);
    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition,true);
        }
    }


    final Calendar myCalendar= Calendar.getInstance();
    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        dateOfFillingEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void setOperations() {

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        dateOfFillingEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    private void validate() {
        String itrGTIFStr,deducationStr,ntiStr,taxPaidStr,taxPayableStr,tdsStr,refundStr,incomeStr,perPanStr,
                returnFailledStr,returnShouldBeFilledStr,returnWereFilledStr,verificationStr,eFillingNoStr,dateOfFillingStr,
                verifiedStr,taxChallanStr,bankNameStr,branchNameStr,acTypeStr,acNumberStr,originalStr;

        if (itrGTIFEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" GTI getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            itrGTIFStr=itrGTIFEt.getText().toString();

        if (deducationEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Deduction getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            deducationStr = deducationEt.getText().toString();

        if (ntiEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" NIT getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            ntiStr=ntiEt.getText().toString();

        if (taxPaidEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Tax PAID getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            taxPaidStr=taxPaidEt.getText().toString();

        if (taxPayableEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Tax payable getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            taxPayableStr=taxPayableEt.getText().toString();

        if (tdsEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" TDS getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            tdsStr=tdsEt.getText().toString();


        if (refundEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Refund getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            refundStr=refundEt.getText().toString();


        if (incomeEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Income From other source getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            incomeStr=incomeEt.getText().toString();

        if (perPanEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" It ward as per PAN getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            perPanStr=perPanEt.getText().toString();

        if (returnFailledEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" It ward return Filled in getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            returnFailledStr=returnFailledEt.getText().toString();

        if (returnShouldBeFilledEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Form # in which returns should be filled getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            returnShouldBeFilledStr=returnShouldBeFilledEt.getText().toString();

        if (returnWereFilledEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Form # in which return were filled getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            returnWereFilledStr=returnWereFilledEt.getText().toString();

        if (verificationEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Verification getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            verificationStr=verificationEt.getText().toString();

        if (eFillingNoEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" E-Filling/Account Number getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            eFillingNoStr=eFillingNoEt.getText().toString();

        if (dateOfFillingEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Date of Filling getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            dateOfFillingStr=dateOfFillingEt.getText().toString();

        if (verifiedSp.getSelectedItemPosition()==0) {
            Toast.makeText(getContext(), yearStr+" Verified getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            verifiedStr=verifiedSp.getSelectedItem().toString();

        if (taxChallanEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Tax challan getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            taxChallanStr=taxChallanEt.getText().toString();

        if (bankNameEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Bank Name getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            bankNameStr=bankNameEt.getText().toString();

        if (branchNameEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Branch Name getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            branchNameStr=branchNameEt.getText().toString();

        if (acTypeEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Account Type getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            acTypeStr=acTypeEt.getText().toString();

        if (acNumberEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), yearStr+" Account number getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            acNumberStr=acNumberEt.getText().toString();

        if (originalSp.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), yearStr+" Original Seen getting empty...!!!", Toast.LENGTH_SHORT).show();
            return;
        }else
            originalStr=originalSp.getSelectedItem().toString();

        saveData(itrGTIFStr,deducationStr,ntiStr,taxPaidStr,taxPayableStr,tdsStr,refundStr,incomeStr,perPanStr,
                returnFailledStr,returnShouldBeFilledStr,returnWereFilledStr,verificationStr,eFillingNoStr,dateOfFillingStr,
                verifiedStr,taxChallanStr,bankNameStr,branchNameStr,acTypeStr,acNumberStr,originalStr);
    }

    //set data year wise
    private void saveData(String itrGTIFStr, String deducationStr, String ntiStr, String taxPaidStr, String taxPayableStr, String tdsStr, String refundStr, String incomeStr, String perPanStr, String returnFailledStr, String returnShouldBeFilledStr, String returnWereFilledStr, String verificationStr, String eFillingNoStr, String dateOfFillingStr, String verifiedStr, String taxChallanStr, String bankNameStr, String branchNameStr, String acTypeStr, String acNumberStr, String originalStr){
        dataCollectionIf dataCollectionIf = (dataCollectionIf) getActivity();
        dataCollectionIf.thirdYear(
                itrGTIFStr,deducationStr,ntiStr,taxPaidStr,taxPayableStr,tdsStr,refundStr,incomeStr,perPanStr,
                returnFailledStr,returnShouldBeFilledStr,returnWereFilledStr,verificationStr,eFillingNoStr,dateOfFillingStr,
                verifiedStr,taxChallanStr,bankNameStr,branchNameStr,acTypeStr,acNumberStr,originalStr,true
        );
    }

    private void intData(View view) {

        itrGTIFEt=view.findViewById(R.id.itr_detail_f3_gti_et);
        deducationEt=view.findViewById(R.id.itr_detail_f3_deduction_et);
        ntiEt=view.findViewById(R.id.itr_detail_f3_nti_et);
        taxPaidEt=view.findViewById(R.id.itr_detail_f3_tax_paid_et);
        taxPayableEt=view.findViewById(R.id.itr_detail_f3_tax_payable_et);
        tdsEt=view.findViewById(R.id.itr_detail_f3_tds_et);
        refundEt=view.findViewById(R.id.itr_detail_f3_refunds_et);
        incomeEt=view.findViewById(R.id.itr_detail_f3_income_source_et);
        perPanEt=view.findViewById(R.id.itr_detail_f3_wardperpan_et);
        returnFailledEt=view.findViewById(R.id.itr_detail_f3_ward_returns_filled_et);
        returnShouldBeFilledEt=view.findViewById(R.id.itr_detail_f3_form_return_should_filled_et);
        returnWereFilledEt=view.findViewById(R.id.itr_detail_f3_form_return_were_failed_et);
        verificationEt=view.findViewById(R.id.itr_detail_f3_verification_et);
        eFillingNoEt=view.findViewById(R.id.itr_detail_f3_ack_no_et);
        dateOfFillingEt=view.findViewById(R.id.itr_detail_f3_date_filling_et);
        verifiedSp=view.findViewById(R.id.itr_detail_f3_verified_sp);
        taxChallanEt=view.findViewById(R.id.itr_detail_f3_challan_et);
        bankNameEt=view.findViewById(R.id.itr_detail_f3_bank_name_et);
        branchNameEt=view.findViewById(R.id.itr_detail_f3_branch_name_et);
        acTypeEt=view.findViewById(R.id.itr_detail_f3_ac_type_et);
        acNumberEt=view.findViewById(R.id.itr_detail_f3_ac_number_et);
        originalSp=view.findViewById(R.id.itr_detail_f3_original_seen_sp);
        progressBar = view.findViewById(R.id.progress_itr_details3);
        doneBtn = view.findViewById(R.id.fg3_done_btn);
        executiveIdStr = SharedPrefAuth.getInstance(getContext()).getValueOfUserId(getContext());
        yearStr = getArguments().getString("year");

        Log.d("Frg 3 intData","Year: "+yearStr);

    }
}