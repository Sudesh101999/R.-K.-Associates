package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondYearFragment extends Fragment {

    private EditText itrGTIFEt,deducationEt,NtiEt,taxPaidEt,taxPayableEt,tdsEt,refundEt,incomeEt,perPanEt,
            returnFailledEt,returnShouldBeFilledEt,returnWereFilledEt,verificationEt,eFillingNoEt,dateOfFillingEt,
            taxChallanEt,bankNameEt,acTypeEt,acNumberEt;
    private Spinner verifiedSp,originalSp;
    private String activityForStr, addDataIdIntentStr,executiveIdStr,yearStr;

    private dataCollectionIf dataCollectionIf;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataCollectionIf= (dataCollectionIf) context;
    }

    public SecondYearFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_year, container, false);
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

        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<DetailsItrReadResponse> call = detailsitrInterface.detailsItrGetData(addDataIdIntentStr, executiveIdStr);

        call.enqueue(new Callback<DetailsItrReadResponse>() {
            @Override
            public void onResponse(Call<DetailsItrReadResponse> call, Response<DetailsItrReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("4")) {
                        if (response.body().getResult().getDetailsOfItrTable().getAssessmentYear().equals(yearStr)) {
                            setData(
                                    response.body().getResult().getDetailsOfItrTable().getGti(),
                                    response.body().getResult().getDetailsOfItrTable().getDeduction(),
                                    response.body().getResult().getDetailsOfItrTable().getNti(),
                                    response.body().getResult().getDetailsOfItrTable().getTaxPaid(),
                                    response.body().getResult().getDetailsOfItrTable().getTaxPayable(),
                                    response.body().getResult().getDetailsOfItrTable().getTds(),
                                    response.body().getResult().getDetailsOfItrTable().getRefund(),
                                    response.body().getResult().getDetailsOfItrTable().getIncomeFromOtherSource(),
                                    response.body().getResult().getDetailsOfItrTable().getItWardasperpan(),
                                    response.body().getResult().getDetailsOfItrTable().getItWardReturnFilledIn(),
                                    response.body().getResult().getDetailsOfItrTable().getReturnShouldFilled(),
                                    response.body().getResult().getDetailsOfItrTable().getReturnWhereFilled(),
                                    response.body().getResult().getDetailsOfItrTable().getVerification(),
                                    response.body().getResult().getDetailsOfItrTable().geteFilling(),
                                    response.body().getResult().getDetailsOfItrTable().getDateOfFilling(),
                                    response.body().getResult().getDetailsOfItrTable().getVerified(),
                                    response.body().getResult().getDetailsOfItrTable().getTaxChallan(),
                                    response.body().getResult().getDetailsOfItrTable().getBankName(),
                                    response.body().getResult().getDetailsOfItrTable().getAccountType(),
                                    response.body().getResult().getDetailsOfItrTable().getAccountNumber(),
                                    response.body().getResult().getDetailsOfItrTable().getOriginalSeen()
                            );
                        }

                    } else {
                        Toast.makeText(getContext(), "Message: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("ITR year 1 Exception", e.getMessage());
                }
                Log.d("ITR year 1 response", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<DetailsItrReadResponse> call, Throwable t) {
                Log.e("Year 1 onFailure", t.getMessage());
            }
        });
    }


    private void setData(String gti, String deduction, String nti, String taxPaid, String taxPayable,
                         String tds, String refund, String incomeFromOtherSource, String itWardasperpan,
                         String itWardReturnFilledIn, String returnShouldFilled, String returnWhereFilled,
                         String verification, String geteFilling, String dateOfFilling, String verified,
                         String taxChallan, String bankName, String accountType, String accountNumber, String originalSeen)
    {


        ArrayAdapter<CharSequence> yesNoAdapter = ArrayAdapter.
                createFromResource(getContext(),
                        R.array.yes_no_sp, android.R.layout.simple_spinner_item);

        itrGTIFEt.setText(gti);
        deducationEt.setText(deduction);
        NtiEt.setText(nti);
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

        originalSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!(originalSp.getSelectedItemPosition() ==0)){
                    saveData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //set data year wise
    private void saveData(){
        dataCollectionIf dataCollectionIf = (dataCollectionIf) getActivity();
        dataCollectionIf.sendYear(
                itrGTIFEt.getText().toString(),
                deducationEt.getText().toString(),
                NtiEt.getText().toString(),
                taxPaidEt.getText().toString(),
                taxPayableEt.getText().toString(),
                tdsEt.getText().toString(),
                refundEt.getText().toString(),
                incomeEt.getText().toString(),
                perPanEt.getText().toString(),
                returnFailledEt.getText().toString(),
                returnShouldBeFilledEt.getText().toString(),
                returnWereFilledEt.getText().toString(),
                verificationEt.getText().toString(),
                eFillingNoEt.getText().toString(),
                dateOfFillingEt.getText().toString(),
                verifiedSp.getSelectedItem().toString(),
                taxChallanEt.getText().toString(),
                bankNameEt.getText().toString(),
                acTypeEt.getText().toString(),
                acNumberEt.getText().toString(),
                originalSp.getSelectedItem().toString()
        );
       /*
        dataCollectionIf.thirdYear(

        );*/
    }

    private void intData(View view) {

        itrGTIFEt=view.findViewById(R.id.itr_detail_f2_gti_et);
        deducationEt=view.findViewById(R.id.itr_detail_f2_deduction_et);
        NtiEt=view.findViewById(R.id.itr_detail_f2_nti_et);
        taxPaidEt=view.findViewById(R.id.itr_detail_f2_tax_paid_et);
        taxPayableEt=view.findViewById(R.id.itr_detail_f2_tax_payable_et);
        tdsEt=view.findViewById(R.id.itr_detail_f2_tds_et);
        refundEt=view.findViewById(R.id.itr_detail_f2_refunds_et);
        incomeEt=view.findViewById(R.id.itr_detail_f2_income_source_et);
        perPanEt=view.findViewById(R.id.itr_detail_f2_wardperpan_et);
        returnFailledEt=view.findViewById(R.id.itr_detail_f2_ward_returns_filled_et);
        returnShouldBeFilledEt=view.findViewById(R.id.itr_detail_f2_form_return_should_filled_et);
        returnWereFilledEt=view.findViewById(R.id.itr_detail_f2_form_return_were_failed_et);
        verificationEt=view.findViewById(R.id.itr_detail_f2_verification_et);
        eFillingNoEt=view.findViewById(R.id.itr_detail_f2_ack_no_et);
        dateOfFillingEt=view.findViewById(R.id.itr_detail_f2_date_filling_et);
        verifiedSp=view.findViewById(R.id.itr_detail_f2_verified_sp);
        taxChallanEt=view.findViewById(R.id.itr_detail_f2_challan_et);
        bankNameEt=view.findViewById(R.id.itr_detail_f2_bank_name_et);
        acTypeEt=view.findViewById(R.id.itr_detail_f2_ac_type_et);
        acNumberEt=view.findViewById(R.id.itr_detail_f2_ac_number_et);
        originalSp=view.findViewById(R.id.itr_detail_f2_original_seen_sp);
        executiveIdStr = SharedPrefAuth.getInstance(getContext()).getValueOfUserId(getContext());

        yearStr = getArguments().getString("year");
    }
}