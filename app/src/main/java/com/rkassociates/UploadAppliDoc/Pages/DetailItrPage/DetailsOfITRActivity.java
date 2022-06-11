package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.DetailsItrResponse;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.DetailsitrInterface;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear.ReadYearResponse;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments.FirstYearFragment;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments.SecondYearFragment;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments.ThirdYearFragment;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.DetailItrFragments.dataCollectionIf;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOfITRActivity extends AppCompatActivity implements dataCollectionIf {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText aplcNameEt;
    private RelativeLayout submitBtn;
    private CardView submitCard;
    private ImageView backIv;
    String year1Str, year2Str, year3Str, aplcId, executiveId,applicantNameStr;

    private String gtiY1Str, deductionY1Str, ntiY1Str, taxPaidY1Str, taxPayableY1Str, tdsY1Str, refundY1Str,
            incomeOtherSourceY1Str, perPanY1Str, returnsFailedY1Str, formShouldBeFilledY1Str, returnWereFilledY1Str,
            varificationY1Str, eFillingY1Str, dateOfFillingY1Str, verifiedY1Str, taxChallanY1Str, bankNameY1Str,branchNameY1Str,
            acTypeY1Str, acNumberY1Str, originalSeenY1Str;
    private boolean frg1DataCollected=false;

    private String gtiY2Str, deductionY2Str, ntiY2Str, taxPaidY2Str, taxPayableY2Str, tdsY2Str, refundY2Str,
            incomeOtherSourceY2Str, perPanY2Str, returnsFailedY2Str, formShouldBeFilledY2Str, returnWereFilledY2Str,
            varificationY2Str, eFillingY2Str, dateOfFillingY2Str, verifiedY2Str, taxChallanY2Str, bankNameY2Str,branchNameY2Str,
            acTypeY2Str, acNumberY2Str, originalSeenY2Str;
    private boolean frg2DataCollected=false;

    private String gtiY3Str, deductionY3Str, ntiY3Str, taxPaidY3Str, taxPayableY3Str, tdsY3Str, refundY3Str,
            incomeOtherSourceY3Str, perPanY3Str, returnsFailedY3Str, formShouldBeFilledY3Str, returnWereFilledY3Str,
            varificationY3Str, eFillingY3Str, dateOfFillingY3Str, verifiedY3Str, taxChallanY3Str, bankNameY3Str,branchNameY3Str,
            acTypeY3Str, acNumberY3Str, originalSeenY3Str;
    private boolean frg3DataCollected=false;

    private String activityFor, addDataIdIntentStr;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_itractivity);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            //The key argument here must match that used in the other activity
            addDataIdIntentStr = extras.getString("addDataIdIntentStr");

            if (activityFor.equals("Pending")) {
                Log.d("details_activity: AddDataId", addDataIdIntentStr);
//                getDataFromDatabase();
            }
        }
        initialiseViews();
        readYear();


        onclickOperation();

    }

    private void readYear(){
        progressBar.setVisibility(View.VISIBLE);
        Log.d("readYear: ","add_data_id: "+addDataIdIntentStr+"\nexecutive_id: "+executiveId);
        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<ReadYearResponse> call = detailsitrInterface.readYear(addDataIdIntentStr,executiveId);
        call.enqueue(new Callback<ReadYearResponse>() {
            @Override
            public void onResponse(Call<ReadYearResponse> call, Response<ReadYearResponse> response) {
                try {

                if (response.body().getStatus().equals("success")) {
                    year1Str=response.body().getData().getItr_kyc_verification_table_year().getYear1();
                    year2Str=response.body().getData().getItr_kyc_verification_table_year().getYear2();
                    year3Str=response.body().getData().getItr_kyc_verification_table_year().getYear3();
                    applicantNameStr = response.body().getData().getItr_kyc_verification_table_year().getApplicantName();
                    setUpTabLayout(year1Str,year2Str,year3Str,applicantNameStr);
                }else{
                    Toast.makeText(DetailsOfITRActivity.this, "Message: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                }catch (Exception e){
                    Log.e("onResponse","Exception: "+e.getMessage() );
                }
                Log.d("TAG", "onResponse: "+response.body().getMessage());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReadYearResponse> call, Throwable t) {
                Log.e("readYear", "onFailure: "+t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void onclickOperation() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (aplcNameEt.getText().toString().isEmpty()) {
                    snackBarMsg("Applicant Name getting empty...!!!");
                }else{
                    getFragmentDataToActivity();
                    Log.d("Year1", "onClick: "+gtiY1Str);
                    Log.d("Year2", "onClick: "+gtiY2Str);
                    Log.d("Year3", "onClick: "+gtiY3Str);
                }
            }
        });
    }

    private void getFragmentDataToActivity() {
        Log.d("TAG", "getFragmentDataToActivity: Year=> "+year1Str);

        progressBar.setVisibility(View.VISIBLE);
        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<DetailsItrResponse> call = detailsitrInterface.detailsItrInsertData(executiveId, addDataIdIntentStr, year1Str, gtiY1Str, deductionY1Str,
                ntiY1Str, taxPaidY1Str, taxPayableY1Str, tdsY1Str, refundY1Str, incomeOtherSourceY1Str, perPanY1Str,
                returnsFailedY1Str, formShouldBeFilledY1Str, returnWereFilledY1Str, varificationY1Str, eFillingY1Str,
                dateOfFillingY1Str, verifiedY1Str, taxChallanY1Str, bankNameY1Str,branchNameY1Str, acTypeY1Str, acNumberY1Str, originalSeenY1Str);

        call.enqueue(new Callback<DetailsItrResponse>() {
            @Override
            public void onResponse(Call<DetailsItrResponse> call, Response<DetailsItrResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        snackBarMsg("Data of year " + year1Str + " Inserted.");
                        insert2YearData();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }
                } catch (Exception e) {
                    Log.e("detailsItrInsertData","ITR year 1 Exception: "+e.getMessage());
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DetailsItrResponse> call, Throwable t) {
                Log.e("Year 1 onFailure", t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void insert2YearData() {


        Log.d("TAG", "getFragmentDataToActivity: Year=> "+year2Str);
        Log.d("TAG", "getFragmentDataToActivity: taxPayableY2Str=> "+taxPayableY2Str);
        progressBar.setVisibility(View.VISIBLE);

        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<DetailsItrResponse> call = detailsitrInterface.detailsItrInsertData(executiveId, addDataIdIntentStr, year2Str, gtiY2Str, deductionY2Str,
                ntiY2Str, taxPaidY2Str, taxPayableY2Str, tdsY2Str, refundY2Str, incomeOtherSourceY2Str, perPanY2Str,
                returnsFailedY2Str, formShouldBeFilledY2Str, returnWereFilledY2Str, varificationY2Str, eFillingY2Str,
                dateOfFillingY2Str, verifiedY2Str, taxChallanY2Str, bankNameY2Str,branchNameY2Str, acTypeY2Str, acNumberY2Str, originalSeenY2Str);

        call.enqueue(new Callback<DetailsItrResponse>() {
            @Override
            public void onResponse(Call<DetailsItrResponse> call, Response<DetailsItrResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        snackBarMsg("Data of year " + year2Str + " Inserted.");
                        insert3YearData();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }
                } catch (Exception e) {
                    Log.e("detailsItrInsertData","ITR year 2 Exception: "+e.getMessage());
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DetailsItrResponse> call, Throwable t) {
                Log.e("Year 2 onFailure", t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void insert3YearData() {

        Log.d("TAG", "getFragmentDataToActivity: Year=> "+year3Str);
        progressBar.setVisibility(View.VISIBLE);

        DetailsitrInterface detailsitrInterface = ApiClient.getRetrofitInstance().create(DetailsitrInterface.class);
        Call<DetailsItrResponse> call = detailsitrInterface.detailsItrInsertData(executiveId, addDataIdIntentStr, year3Str, gtiY3Str, deductionY3Str,
                ntiY3Str, taxPaidY3Str, taxPayableY3Str, tdsY3Str, refundY3Str, incomeOtherSourceY3Str, perPanY3Str,
                returnsFailedY3Str, formShouldBeFilledY3Str, returnWereFilledY3Str, varificationY3Str, eFillingY3Str,
                dateOfFillingY3Str, verifiedY3Str, taxChallanY3Str, bankNameY3Str,branchNameY3Str, acTypeY3Str, acNumberY3Str, originalSeenY3Str);

        call.enqueue(new Callback<DetailsItrResponse>() {
            @Override
            public void onResponse(Call<DetailsItrResponse> call, Response<DetailsItrResponse> response) {
                try {

                    if (response.body().getStatus() == 4) {
                        Toast.makeText(DetailsOfITRActivity.this, "Data of year " + year3Str + " Inserted.", Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setDetailsOfITR(true);
                        DetailsOfITRActivity.super.onBackPressed();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }
                } catch (Exception e) {
                    Log.e("ITR year 3 Exception", e.getMessage());
                }
                Log.d("ITR year 3 response", response.body().getMsg());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DetailsItrResponse> call, Throwable t) {
                Log.e("Year 3 onFailure", t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                DetailsOfITRActivity.super.onBackPressed();
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

    public void initialiseViews() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        aplcNameEt = findViewById(R.id.itr_detail_page_aplc_name_et);
        submitBtn = findViewById(R.id.submit_btn);
        submitCard = findViewById(R.id.card_layout_submit);

        backIv = findViewById(R.id.details_itr_page_back_btn_iv);

        progressBar = findViewById(R.id.details_of_itr_progress);

        aplcId = SharedPrefAuth.getInstance(getApplicationContext()).getAplcId(getApplicationContext());
        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

    }

    private void setUpTabLayout(String year1Str, String year2Str, String year3Str, String aplcName) {

//        Toast.makeText(DetailsOfITRActivity.this, "Year1: "+year1Str+"\nyear2: "+year2Str+"\nyear3: "+year3Str, Toast.LENGTH_SHORT).show();

        aplcNameEt.setText(aplcName);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), year1Str, year2Str, year3Str);
        adapter.add(new FirstYearFragment(), year1Str);
        adapter.add(new SecondYearFragment(), year2Str);
        adapter.add(new ThirdYearFragment(), year3Str);
        viewPager.setAdapter(adapter);
//        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.view_pager_gap));
        viewPager.setPageMarginDrawable(R.color.app_icon_shadow);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        Log.e("setUpTabLayout", "addDataIdIntentStr: " + addDataIdIntentStr + "\nactivityFor" + activityFor + "\nyear1" + year1Str);

/*
        Bundle bundle = new Bundle();
        bundle.putString("addDataIdIntentStr", addDataIdIntentStr);
        bundle.putString("activityFor", activityFor);
        bundle.putString("year", year1);

        FragmentManager manager = getSupportFragmentManager();
        FirstYearFragment myFragment1 = (FirstYearFragment) manager.findFragmentByTag("FirstYearFragment");
        Objects.requireNonNull(myFragment1).receiveMsg(addDataIdIntentStr,activityFor,year1);

        SecondYearFragment myFragment2 = (SecondYearFragment) manager.findFragmentByTag("SecondYearFragment");
        Objects.requireNonNull(myFragment2).receiveMsg(addDataIdIntentStr,activityFor,year2);

        ThirdYearFragment myFragment3 = (ThirdYearFragment) manager.findFragmentByTag("ThirdYearFragment");
        Objects.requireNonNull(myFragment3).receiveMsg(addDataIdIntentStr,activityFor,year3);
*/

    }

    @Override
    public void firstYear(String gtiY1, String deductionY1, String ntiY1, String taxPaidY1, String taxPayableY1,
                          String tdsY1, String refundY1, String incomeOtherSourceY1, String perPanY1,
                          String returnsFailedY1, String formShouldBeFilledY1, String returnWereFilledY1,
                          String varificationY1, String eFillingY1, String dateOfFillingY1, String verifiedY1,
                          String taxChallanY1, String bankNameY1,String branchNameY1, String acTypeY1,
                          String acNumberY1, String originalSeenY1,boolean isCollectedData) {

        Log.d("Details ITR activity", "dateOfFillingY1: "+dateOfFillingY1);
        gtiY1Str = gtiY1;
        deductionY1Str = deductionY1;
        ntiY1Str = ntiY1;
        taxPaidY1Str = taxPaidY1;
        taxPayableY1Str = taxPayableY1;
        tdsY1Str = tdsY1;
        refundY1Str = refundY1;
        incomeOtherSourceY1Str = incomeOtherSourceY1;
        perPanY1Str = perPanY1;
        returnsFailedY1Str = returnsFailedY1;
        formShouldBeFilledY1Str = formShouldBeFilledY1;
        returnWereFilledY1Str = returnWereFilledY1;
        varificationY1Str = varificationY1;
        eFillingY1Str = eFillingY1;
        dateOfFillingY1Str = dateOfFillingY1;
        verifiedY1Str = verifiedY1;
        taxChallanY1Str = taxChallanY1;
        bankNameY1Str = bankNameY1;
        branchNameY1Str = branchNameY1;
        acTypeY1Str = acTypeY1;
        acNumberY1Str = acNumberY1;
        originalSeenY1Str = originalSeenY1;
        frg1DataCollected = isCollectedData;

        Toast.makeText(getApplicationContext(), "Year "+year1Str+" Data collected.", Toast.LENGTH_SHORT).show();


        if (frg1DataCollected && frg2DataCollected && frg3DataCollected ){
            submitCard.setVisibility(View.VISIBLE);
        }else
            submitCard.setVisibility(View.GONE);
    }

    @Override
    public void secondYear(String gtiY2, String deductionY2, String ntiY2, String taxPaidY2, String taxPayableY2,
                         String tdsY2, String refundY2, String incomeOtherSourceY2, String perPanY2, String returnsFailedY2,
                         String formShouldBeFilledY2, String returnWereFilledY2, String varificationY2, String eFillingY2,
                         String dateOfFillingY2, String verifiedY2, String taxChallanY2, String bankNameY2,
                           String branchNameY2, String acTypeY2, String acNumberY2, String originalSeenY2, boolean isCollectedData) {
        Log.d("Details ITR activity", "dateOfFillingY2: "+dateOfFillingY2);
        gtiY2Str = gtiY2;
        deductionY2Str = deductionY2;
        ntiY2Str = ntiY2;
        taxPaidY2Str = taxPaidY2;
        taxPayableY2Str = taxPayableY2;
        tdsY2Str = tdsY2;
        refundY2Str = refundY2;
        incomeOtherSourceY2Str = incomeOtherSourceY2;
        perPanY2Str = perPanY2;
        returnsFailedY2Str = returnsFailedY2;
        formShouldBeFilledY2Str = formShouldBeFilledY2;
        returnWereFilledY2Str = returnWereFilledY2;
        varificationY2Str = varificationY2;
        eFillingY2Str = eFillingY2;
        dateOfFillingY2Str = dateOfFillingY2;
        verifiedY2Str = verifiedY2;
        taxChallanY2Str = taxChallanY2;
        bankNameY2Str = bankNameY2;
        branchNameY2Str = branchNameY2;
        acTypeY2Str = acTypeY2;
        acNumberY2Str = acNumberY2;
        originalSeenY2Str = originalSeenY2;
        frg2DataCollected = isCollectedData;


        Toast.makeText(getApplicationContext(), "Year "+year2Str+" Data collected.", Toast.LENGTH_SHORT).show();

        if (frg1DataCollected && frg2DataCollected && frg3DataCollected ){
            submitCard.setVisibility(View.VISIBLE);
        }else
            submitCard.setVisibility(View.GONE);
    }

    @Override
    public void thirdYear(String gtiY3, String deductionY3, String ntiY3, String taxPaidY3, String taxPayableY3,
                          String tdsY3, String refundY3, String incomeOtherSourceY3, String perPanY3, String returnsFailedY3,
                          String formShouldBeFilledY3, String returnWereFilledY3, String varificationY3, String eFillingY3,
                          String dateOfFillingY3, String verifiedY3, String taxChallanY3, String bankNameY3,String branchNameY3, String acTypeY3,
                          String acNumberY3, String originalSeenY3, boolean isCollectedData) {

        Log.d("Details ITR activity", "dateOfFillingY3: "+dateOfFillingY3);
        gtiY3Str = gtiY3;
        deductionY3Str = deductionY3;
        ntiY3Str = ntiY3;
        taxPaidY3Str = taxPaidY3;
        taxPayableY3Str = taxPayableY3;
        tdsY3Str = tdsY3;
        refundY3Str = refundY3;
        incomeOtherSourceY3Str = incomeOtherSourceY3;
        perPanY3Str = perPanY3;
        returnsFailedY3Str = returnsFailedY3;
        formShouldBeFilledY3Str = formShouldBeFilledY3;
        returnWereFilledY3Str = returnWereFilledY3;
        varificationY3Str = varificationY3;
        eFillingY3Str = eFillingY3;
        dateOfFillingY3Str = dateOfFillingY3;
        verifiedY3Str = verifiedY3;
        taxChallanY3Str = taxChallanY3;
        bankNameY3Str = bankNameY3;
        branchNameY3Str = branchNameY3;
        acTypeY3Str = acTypeY3;
        acNumberY3Str = acNumberY3;
        originalSeenY3Str = originalSeenY3;
        frg3DataCollected = isCollectedData;

        Toast.makeText(getApplicationContext(), "Year "+year3Str+" Data collected.", Toast.LENGTH_SHORT).show();

        if (frg1DataCollected && frg2DataCollected && frg3DataCollected ){
            submitCard.setVisibility(View.VISIBLE);
        }else
            submitCard.setVisibility(View.GONE);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();
        private String year1, year2, year3;

        public ViewPagerAdapter(@NonNull FragmentManager fm, String year1, String year2, String year3) {
            super(fm);
            this.year1 = year1;
            this.year2 = year2;
            this.year3 = year3;
        }

        void add(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
//            return fragmentList.get(position);
            Fragment fragment = null;

            Bundle bundle = new Bundle();

            switch (position) {
                case 0:
                    fragment = new FirstYearFragment();
                    bundle.putString("year", year1);
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    fragment = new SecondYearFragment();
                    bundle.putString("year", year2);
                    fragment.setArguments(bundle);
                    break;
                case 2:
                    fragment = new ThirdYearFragment();
                    bundle.putString("year", year3);
                    fragment.setArguments(bundle);
                    break;
                default:
                    fragment = null;
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

}