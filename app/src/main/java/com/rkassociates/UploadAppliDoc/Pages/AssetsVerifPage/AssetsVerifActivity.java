package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls.AssetsVerifInterface;
import com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls.AssetsVerifResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetsVerifActivity extends AppCompatActivity {

    //assets verif
    private ProgressDialog progressdialog;
    private ImageView assetsBackIv;
    private EditText aplcNameEt, personMetEt, phoneEt, docOfOwnerShipEt, moneyPaymentEt, modeOfPaymentEt,
            agreementEt, stampDutyEt, registrationEt, indexEt, panEt;
    private Spinner productTypeSp, entryInSellerAccSp, anyOtherBankChargeSp, nocIssuedSt;
    private CardView homeTypeLayout, carTypeLayout, mortgageTypeLayout;
    //assets verif

    //Home Loan
    private ImageView compressHomeIv;
    private LinearLayout homeLL;
    final Calendar myCalendar = Calendar.getInstance();
    private CheckBox homeLayoutCB;
    ArrayList<String> floorsArray = new ArrayList<String>();
    ArrayList<String> durationArray = new ArrayList<String>();
    private Spinner propertStateSp, typeUnitSp, accessibilitySp, addresConfimedSp, dimensionHhkSp, noOfFloorsSp, durationOfStaySp,
            societyNameBoardSp, doorNamePlateSp, utilityBillSp, localitySp;
    private CheckBox interpaintedCb, interCleanCb, interCarpetCb, interSofaCb, interCurtainCb, interShowcaseCb;
    private CheckBox exterGardenCb, exterElevatorCb, exterCarParkingCb, exterSecurityCb, exterSwimmingCb, exterintercomCb;
    private EditText dimensionEt, homeRemark;
    private ArrayList<String> interiorArray = new ArrayList<>();
    private ArrayList<String> exteriorArray = new ArrayList<>();
    //Home Loan

    //car loan
    private EditText carLAddVisitedEt, carLPersonMetEt;
    //car loan

    //mortgage loan
    private Spinner mortgageTypeSp;
    private LinearLayout loanLayout, machineryLayout, stockLayout;
    private EditText landAreaEt, landOwnershipEt, machineryTypeEt, stockRawMaterialEt, stockFinishWoodEt;
    //mortgage loan

    private EditText dateEt, timeEt;
    private RelativeLayout assetsRlSubmit;

    String executiveId, aplcId;
    private String activityFor,addDataIdIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_verif);

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

        setChecBoxVal();
        operations();
    }

    private void getDataFromDatabase() {
//pending

    }

    private void setChecBoxVal() {
        interpaintedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interpaintedCb.isChecked()) {
                    interiorArray.add(interpaintedCb.getText().toString());
                } else {
                    interiorArray.remove(interpaintedCb.getText().toString());
                }
            }
        });
        interCleanCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interCleanCb.isChecked()) {
                    interiorArray.add(interCleanCb.getText().toString());
                } else {
                    interiorArray.remove(interCleanCb.getText().toString());
                }
            }
        });
        interCarpetCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interCarpetCb.isChecked()) {
                    interiorArray.add(interCarpetCb.getText().toString());
                } else {
                    interiorArray.remove(interCarpetCb.getText().toString());
                }
            }
        });
        interSofaCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interSofaCb.isChecked()) {
                    interiorArray.add(interSofaCb.getText().toString());
                } else {
                    interiorArray.remove(interSofaCb.getText().toString());
                }
            }
        });
        interCurtainCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interCurtainCb.isChecked()) {
                    interiorArray.add(interCurtainCb.getText().toString());
                } else {
                    interiorArray.remove(interCurtainCb.getText().toString());
                }
            }
        });
        interShowcaseCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interShowcaseCb.isChecked()) {
                    interiorArray.add(interShowcaseCb.getText().toString());
                } else {
                    interiorArray.remove(interShowcaseCb.getText().toString());
                }
            }
        });
        exterGardenCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterGardenCb.isChecked()) {
                    exteriorArray.add(exterGardenCb.getText().toString());
                } else {
                    exteriorArray.remove(exterGardenCb.getText().toString());
                }
            }
        });
        exterElevatorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterElevatorCb.isChecked()) {
                    exteriorArray.add(exterElevatorCb.getText().toString());
                } else {
                    exteriorArray.remove(exterElevatorCb.getText().toString());
                }
            }
        });
        exterCarParkingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterCarParkingCb.isChecked()) {
                    exteriorArray.add(exterCarParkingCb.getText().toString());
                } else {
                    exteriorArray.remove(exterCarParkingCb.getText().toString());
                }
            }
        });
        exterSecurityCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterSecurityCb.isChecked()) {
                    exteriorArray.add(exterSecurityCb.getText().toString());
                } else {
                    exteriorArray.remove(exterSecurityCb.getText().toString());
                }
            }
        });
        exterSwimmingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterSwimmingCb.isChecked()) {
                    exteriorArray.add(exterSwimmingCb.getText().toString());
                } else {
                    exteriorArray.remove(exterSwimmingCb.getText().toString());
                }
            }
        });
        exterintercomCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exterintercomCb.isChecked()) {
                    exteriorArray.add(exterintercomCb.getText().toString());
                } else {
                    exteriorArray.remove(exterintercomCb.getText().toString());
                }
            }
        });
    }


    private void operations() {

        assetsBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        homeLayoutCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!compoundButton.isChecked()) {
                    dimensionEt.setText("");
                    dimensionEt.setHint("sq.ft.");
                    String selectVal = "--Select--";
                    setSpinnerData(R.array.property_status_sp, propertStateSp, selectVal);
                    setSpinnerData(R.array.type_of_unit_sp, typeUnitSp, selectVal);
                    setSpinnerData(R.array.accessibility_sp, accessibilitySp, selectVal);
                    setSpinnerData(R.array.home_loan_yes_no_sp, addresConfimedSp, selectVal);
                    setSpinnerData(R.array.dimension_sp, dimensionHhkSp, selectVal);
                    //no. Of floors, duration of Stay painding
                    setSpinnerData(R.array.home_loan_yes_no_sp, societyNameBoardSp, selectVal);
                    setSpinnerData(R.array.home_loan_yes_no_sp, doorNamePlateSp, selectVal);
                    setSpinnerData(R.array.home_loan_utility_bill_sp, utilityBillSp, selectVal);
                    setSpinnerData(R.array.home_loan_locality_sp, localitySp, selectVal);

                    interpaintedCb.setChecked(false);
                    interCleanCb.setChecked(false);
                    interCarpetCb.setChecked(false);
                    interSofaCb.setChecked(false);
                    interCurtainCb.setChecked(false);
                    interShowcaseCb.setChecked(false);

                    exterGardenCb.setChecked(false);
                    exterElevatorCb.setChecked(false);
                    exterCarParkingCb.setChecked(false);
                    exterSecurityCb.setChecked(false);
                    exterSwimmingCb.setChecked(false);
                    exterintercomCb.setChecked(false);
                    homeRemark.setText("");
                }
            }
        });

        compressHomeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeLL.getVisibility() == View.VISIBLE) {
                    homeLL.setVisibility(View.GONE);
                } else {
                    homeLL.setVisibility(View.VISIBLE);
                }
            }
        });

        productTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (productTypeSp.getSelectedItemPosition() == 1) {
                    homeLayoutCB.setChecked(true);
                    setSpinnerYear();
                    setDateTime();
                } else {
                    homeLayoutCB.setChecked(false);
                }

                if (productTypeSp.getSelectedItemPosition() == 2) {
                    carTypeLayout.setVisibility(View.VISIBLE);
                    homeTypeLayout.setVisibility(View.GONE);
                } else {
                    homeTypeLayout.setVisibility(View.VISIBLE);
                    carTypeLayout.setVisibility(View.GONE);
                }

                if (productTypeSp.getSelectedItemPosition() == 3) {
                    mortgageTypeLayout.setVisibility(View.VISIBLE);
                } else {
                    mortgageTypeLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mortgageTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mortgageTypeSp.getSelectedItemPosition() == 1) {
                    loanLayout.setVisibility(View.VISIBLE);
                } else {
                    loanLayout.setVisibility(View.GONE);
                }
                if (mortgageTypeSp.getSelectedItemPosition() == 2) {
                    machineryLayout.setVisibility(View.VISIBLE);
                } else {
                    machineryLayout.setVisibility(View.GONE);
                }
                if (mortgageTypeSp.getSelectedItemPosition() == 3) {
                    stockLayout.setVisibility(View.VISIBLE);
                } else {
                    stockLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                new DatePickerDialog(AssetsVerifActivity.this,
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
                mTimePicker = new TimePickerDialog(AssetsVerifActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        assetsRlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        ///validation
        String aplcNameStr = "",personMetStr = "", phoneStr = "", docOfOwnershipStr = "", moneyPaymentStr = "", entryInSellerAccStr = "", anyOtherBankChargeStr = "",
                nocIssuedStr = "", agreementStr = "", stampDutyStr = "", registrationStr = "", indexStr = "", panStr = "";
        String productTypeStr = "";
        String propertyStatusStr = "", typeOfUnitStr = "", accessStr = "", addressStr = "", dimensionSqStr = "", dimensionFitStr = "", noOfFloorsStr = "",
                durationOfStayStr = "", societyNameStr = "", doorNamePlateStr = "", utilityBillsStr = "", localityStr = "", interiorStr = "", exteriorsStr = "",
                homeRemarkStr = "";
        String carLoAddressVisitedStr = "", carLoPersonStr = "";
        String mortgageTypeStr = "";
        String mortLandAreaStr = "", mortLandOwnershipStr = "";
        String mortMachineryTypeOfMachineryStr = "";
        String mortStockRawMaterialStr = "", mortStockFinishWoodStr = "";
        String dateStr = "", timeStr = "";

        aplcNameStr = aplcNameEt.getText().toString();
        personMetStr = personMetEt.getText().toString();
        phoneStr = phoneEt.getText().toString();
        docOfOwnershipStr = docOfOwnerShipEt.getText().toString();
        moneyPaymentStr = moneyPaymentEt.getText().toString();
        entryInSellerAccStr = entryInSellerAccSp.getSelectedItem().toString();
        anyOtherBankChargeStr = anyOtherBankChargeSp.getSelectedItem().toString();
        nocIssuedStr = nocIssuedSt.getSelectedItem().toString();
        agreementStr = agreementEt.getText().toString();
        stampDutyStr = stampDutyEt.getText().toString();
        registrationStr = registrationEt.getText().toString();
        indexStr = indexEt.getText().toString();
        panStr = panEt.getText().toString();

        productTypeStr = productTypeSp.getSelectedItem().toString();

        if (homeLayoutCB.isChecked()) {
            if (propertStateSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Home details Property status...!!!");
                return;
            } else
                propertyStatusStr = propertStateSp.getSelectedItem().toString();

            if (typeUnitSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Home details Type of Unit..!!!");
                return;
            } else
                typeOfUnitStr = typeUnitSp.getSelectedItem().toString();

            if (accessibilitySp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Home details Accessibility...!!!");
                return;
            } else
                accessStr = accessibilitySp.getSelectedItem().toString();

            if (addresConfimedSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Home details Address confirmed...!!!");
                return;
            } else
                addressStr = addresConfimedSp.getSelectedItem().toString();

            if (dimensionEt.getText().toString().isEmpty() || dimensionHhkSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Home details Dimension...!!!");
                return;
            } else {
                dimensionSqStr = dimensionEt.getText().toString();
                dimensionFitStr = dimensionHhkSp.getSelectedItem().toString();
            }

            if (noOfFloorsSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select No. of floors...!!!");
                return;
            } else
                noOfFloorsStr = noOfFloorsSp.getSelectedItem().toString();

            if (durationOfStaySp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Duration of stay...!!!");
                return;
            } else
                durationOfStayStr = durationOfStaySp.getSelectedItem().toString();

            if (societyNameBoardSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select society name board...!!!");
                return;
            } else
                societyNameStr = societyNameBoardSp.getSelectedItem().toString();

            if (doorNamePlateSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select door name plate...!!!");
                return;
            } else
                doorNamePlateStr = doorNamePlateSp.getSelectedItem().toString();

            if (utilityBillSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select Utility Bills...!!!");
                return;
            } else
                utilityBillsStr = utilityBillSp.getSelectedItem().toString();

            if (localitySp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select locality...!!!");
                return;
            } else
                localityStr = localitySp.getSelectedItem().toString();


            if (interiorArray.size() == 0) {
                snackBarMsg("Select Interiors...!!!");
                return;
            } else {
                String[] interiorsList = Arrays.copyOf(interiorArray.toArray(), interiorArray.size(), String[].class);
                interiorStr = Arrays.toString(interiorsList);
            }
            if (exteriorArray.size() == 0) {
                snackBarMsg("Select Exterior...!!!");
                return;
            } else {
                String[] exteriorList = Arrays.copyOf(exteriorArray.toArray(), exteriorArray.size(), String[].class);
                exteriorsStr = Arrays.toString(exteriorList);
            }

            homeRemarkStr = homeRemark.getText().toString();

        } else {
            propertyStatusStr = "--";
            typeOfUnitStr = "--";
            accessStr = "--";
            addressStr = "--";
            dimensionSqStr = "--";
            dimensionFitStr = "--";
            noOfFloorsStr = "--";
            durationOfStayStr = "--";
            societyNameStr = "--";
            doorNamePlateStr = "--";
            utilityBillsStr = "--";
            localityStr = "--";
            interiorStr = "--";
            exteriorsStr = "--";
            homeRemarkStr = "--";
        }

        if (productTypeSp.getSelectedItemPosition() == 2) {
            if (carLAddVisitedEt.getText().toString().isEmpty()) {
                snackBarMsg("Car Loan Address visited is getting empty...!!!");
                return;
            } else
                carLoAddressVisitedStr = carLAddVisitedEt.getText().toString();

            if (carLPersonMetEt.getText().toString().isEmpty()) {
                snackBarMsg("Car Loan person met is getting empty...!!!");
                return;
            } else
                carLoPersonStr = carLPersonMetEt.getText().toString();
        } else {
            carLoAddressVisitedStr = "";
            carLoPersonStr = "";
        }

        if (productTypeSp.getSelectedItemPosition() == 3) {
            if (mortgageTypeSp.getSelectedItemPosition() == 0) {
                snackBarMsg("Select mortgage type...!!!");
                return;
            } else
                mortgageTypeStr = mortgageTypeSp.getSelectedItem().toString();

            if (mortgageTypeSp.getSelectedItemPosition() == 1 && landAreaEt.getText().toString().isEmpty()) {
                snackBarMsg("Area of mortage is getting empty...!!!");
                return;
            } else
                mortLandAreaStr = landAreaEt.getText().toString();

            if (mortgageTypeSp.getSelectedItemPosition() == 1 && landOwnershipEt.getText().toString().isEmpty()) {
                snackBarMsg("Ownership of the land");
                return;
            } else
                mortLandOwnershipStr = landOwnershipEt.getText().toString();

            if (mortgageTypeSp.getSelectedItemPosition() == 2 && machineryTypeEt.getText().toString().isEmpty()) {
                snackBarMsg("Type of machinery getting empty...!!!");
                return;
            } else
                mortMachineryTypeOfMachineryStr = machineryTypeEt.getText().toString();

            if (mortgageTypeSp.getSelectedItemPosition() == 3 && stockRawMaterialEt.getText().toString().isEmpty()) {
                snackBarMsg("Raw material getting empty...!!!");
                return;
            } else
                mortStockRawMaterialStr = stockRawMaterialEt.getText().toString();

            if (mortgageTypeSp.getSelectedItemPosition() == 3 && stockFinishWoodEt.getText().toString().isEmpty()) {
                snackBarMsg("Finish wood getting empty...!!!");
                return;
            } else
                mortStockFinishWoodStr = stockFinishWoodEt.getText().toString();

        } else {
            mortgageTypeStr = "";
            mortLandAreaStr = "";
            mortLandOwnershipStr = "";
        }

        dateStr = dateEt.getText().toString();
        timeStr = timeEt.getText().toString();

        if (productTypeSp.getSelectedItemPosition() == 2) {
            insertToDatabase(aplcNameStr,personMetStr, phoneStr, docOfOwnershipStr, moneyPaymentStr, entryInSellerAccStr,
                    anyOtherBankChargeStr, nocIssuedStr, agreementStr, stampDutyStr, registrationStr, indexStr, panStr,
                    carLoAddressVisitedStr, carLoPersonStr, mortgageTypeStr, mortLandAreaStr, mortLandOwnershipStr,
                    mortMachineryTypeOfMachineryStr, mortStockRawMaterialStr, mortStockFinishWoodStr, dateStr, timeStr);
        } else
            insertToDatabaseWithHome(aplcNameStr,personMetStr, phoneStr, docOfOwnershipStr, moneyPaymentStr, entryInSellerAccStr, anyOtherBankChargeStr,
                    nocIssuedStr, agreementStr, stampDutyStr, registrationStr, indexStr, panStr, productTypeStr, propertyStatusStr, typeOfUnitStr, accessStr, addressStr, dimensionSqStr, dimensionFitStr, noOfFloorsStr,
                    durationOfStayStr, societyNameStr, doorNamePlateStr, utilityBillsStr, localityStr, interiorStr, exteriorsStr,
                    homeRemarkStr, carLoAddressVisitedStr, carLoPersonStr, mortgageTypeStr, mortLandAreaStr, mortLandOwnershipStr,
                    mortMachineryTypeOfMachineryStr, mortStockRawMaterialStr, mortStockFinishWoodStr, dateStr, timeStr);
    }

    private void insertToDatabaseWithHome(String aplcNameStr,String personMetStr, String phoneStr, String docOfOwnershipStr, String moneyPaymentStr, String entryInSellerAccStr, String anyOtherBankChargeStr, String nocIssuedStr, String agreementStr, String stampDutyStr, String registrationStr, String indexStr, String panStr, String productTypeStr, String propertyStatusStr, String typeOfUnitStr, String accessStr, String addressStr, String dimensionSqStr, String dimensionFitStr, String noOfFloorsStr, String durationOfStayStr, String societyNameStr, String doorNamePlateStr, String utilityBillsStr, String localityStr, String interiorStr, String exteriorsStr, String homeRemarkStr, String carLoAddressVisitedStr, String carLoPersonStr, String mortgageTypeStr, String mortLandAreaStr, String mortLandOwnershipStr, String mortMachineryTypeOfMachineryStr, String mortStockRawMaterialStr, String mortStockFinishWoodStr, String dateStr, String timeStr) {

        progressdialog.show();

        AssetsVerifInterface assetsVerifInterface = ApiClient.getRetrofitInstance().create(AssetsVerifInterface.class);

        Call<AssetsVerifResponse> call = assetsVerifInterface.insertAssetsVerifWithHome(executiveId, addDataIdIntentStr,aplcNameStr, personMetStr, phoneStr, docOfOwnershipStr, moneyPaymentStr, entryInSellerAccStr, anyOtherBankChargeStr,
                nocIssuedStr, agreementStr, stampDutyStr, registrationStr, indexStr, panStr, productTypeStr, propertyStatusStr, typeOfUnitStr, accessStr, addressStr, dimensionSqStr, dimensionFitStr, noOfFloorsStr,
                durationOfStayStr, societyNameStr, doorNamePlateStr, utilityBillsStr, localityStr, interiorStr, exteriorsStr,
                homeRemarkStr, carLoAddressVisitedStr, carLoPersonStr, mortgageTypeStr, mortLandAreaStr, mortLandOwnershipStr,
                mortMachineryTypeOfMachineryStr, mortStockRawMaterialStr, mortStockFinishWoodStr, dateStr, timeStr);

        call.enqueue(new Callback<AssetsVerifResponse>() {
            @Override
            public void onResponse(Call<AssetsVerifResponse> call, Response<AssetsVerifResponse> response) {

                try {
                    if (response.body().getStatus() == 4) {
                        Toast.makeText(AssetsVerifActivity.this, "Message: " + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setAssetsVerif(true);
                        AssetsVerifActivity.super.onBackPressed();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }

                    Log.d("InsertDataWithHome", "onResponse: " + response.body().getResult());
                } catch (Exception e) {
                    Log.e("onResponse", "Exception: " + e.getMessage());
                }
                progressdialog.dismiss();

            }

            @Override
            public void onFailure(Call<AssetsVerifResponse> call, Throwable t) {

                progressdialog.dismiss();
                Log.e("InsertDataWithHome", "onFailure: " + t.getMessage());
            }
        });
    }

    private void insertToDatabase(String aplcNameStr,String personMetStr, String phoneStr, String docOfOwnershipStr, String moneyPaymentStr,
                                  String entryInSellerAccStr, String anyOtherBankChargeStr, String nocIssuedStr,
                                  String agreementStr, String stampDutyStr, String registrationStr, String indexStr,
                                  String panStr, String carLoAddressVisitedStr, String carLoPersonStr, String mortgageTypeStr,
                                  String mortLandAreaStr, String mortLandOwnershipStr, String mortMachineryTypeOfMachineryStr,
                                  String mortStockRawMaterialStr, String mortStockFinishWoodStr, String dateStr, String timeStr) {

        progressdialog.show();

        AssetsVerifInterface assetsVerifInterface = ApiClient.getRetrofitInstance().create(AssetsVerifInterface.class);

        Call<AssetsVerifResponse> call = assetsVerifInterface.insertAssetsVerif(executiveId, addDataIdIntentStr,aplcNameStr,
                personMetStr, phoneStr, docOfOwnershipStr, moneyPaymentStr, entryInSellerAccStr, anyOtherBankChargeStr,
                nocIssuedStr, agreementStr, stampDutyStr, registrationStr, indexStr, panStr, carLoAddressVisitedStr,
                carLoPersonStr, mortgageTypeStr, mortLandAreaStr, mortLandOwnershipStr,
                mortMachineryTypeOfMachineryStr, mortStockRawMaterialStr, mortStockFinishWoodStr, dateStr, timeStr);

        call.enqueue(new Callback<AssetsVerifResponse>() {
            @Override
            public void onResponse(Call<AssetsVerifResponse> call, Response<AssetsVerifResponse> response) {

                try {

                    if (response.body().getStatus() == 4) {
                        Toast.makeText(AssetsVerifActivity.this, "Message: " + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setAssetsVerif(true);
                        AssetsVerifActivity.super.onBackPressed();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }

                    Log.d("InsertDataWithHome", "onResponse: " + response.body().getResult());
                } catch (Exception e) {
                    Log.e("onResponse", "Exception: " + e.getMessage());
                }
                progressdialog.dismiss();

            }

            @Override
            public void onFailure(Call<AssetsVerifResponse> call, Throwable t) {

                progressdialog.dismiss();
                Log.e("InsertDataWithHome", "onFailure: " + t.getMessage());
            }
        });
    }

    private void setSpinnerData(int arrauVal, Spinner propertStateSp, String selectVal) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrauVal, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertStateSp.setAdapter(adapter);
        if (selectVal != null) {
            int spinnerPosition = adapter.getPosition(selectVal);
            propertStateSp.setSelection(spinnerPosition);
        }
    }

    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }

    private void setSpinnerYear() {

        String startNum = "--Select--";
        floorsArray.add(startNum);
        floorsArray.add("NA");
        floorsArray.add("Ground Floor");

        durationArray.add(startNum);
        int fiftyInt = 50;
        for (int i = 1; i <= fiftyInt; i++) {
            floorsArray.add(Integer.toString(i) + " Floors");
        }
        for (int i = 1; i <= fiftyInt; i++) {
            durationArray.add(Integer.toString(i) + " year");
        }
        ArrayAdapter<String> adapterFloor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, floorsArray);
        ArrayAdapter<String> adapterDuration = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, durationArray);

        noOfFloorsSp.setAdapter(adapterFloor);
        durationOfStaySp.setAdapter(adapterDuration);

    }

    private void initData() {
        assetsBackIv = findViewById(R.id.assets_page_back_btn_iv);
        aplcNameEt = findViewById(R.id.assets_aplc_name_et);
        personMetEt = findViewById(R.id.assets_aplc_person_et);
        phoneEt = findViewById(R.id.assets_aplc_phone_et);
        docOfOwnerShipEt = findViewById(R.id.assets_aplc_doc_ownership_et);
        moneyPaymentEt = findViewById(R.id.assets_aplc_doc_money_payment_et);
        modeOfPaymentEt = findViewById(R.id.assets_aplc_doc_mode_payment_et);
        entryInSellerAccSp = findViewById(R.id.assets_aplc_doc_seller_acc_verified_sp);
        anyOtherBankChargeSp = findViewById(R.id.assets_aplc_doc_other_bank_charge_sp);
        nocIssuedSt = findViewById(R.id.assets_aplc_doc_noc_issued_sp);
        agreementEt = findViewById(R.id.assets_home_type_aplc_agreement_value_et);
        stampDutyEt = findViewById(R.id.assets_home_type_aplc_stamp_duty_et);
        registrationEt = findViewById(R.id.assets_home_type_aplc_registration_et);
        indexEt = findViewById(R.id.assets_home_type_aplc_index_ii_et);
        panEt = findViewById(R.id.assets_home_type_aplc_pan_et);


        productTypeSp = findViewById(R.id.assets_product_type_sp);

        //product type home
        homeTypeLayout = findViewById(R.id.assets_home_type_layout);
        homeLayoutCB = findViewById(R.id.assets_home_check_box);

        propertStateSp = findViewById(R.id.assets_home_type_property_status_sp);
        typeUnitSp = findViewById(R.id.assets_home_type_type_unit_sp);
        accessibilitySp = findViewById(R.id.assets_home_type_accessibility_sp);
        addresConfimedSp = findViewById(R.id.assets_home_type_add_confirmed_sp);
        dimensionEt = findViewById(R.id.assets_home_type_dimension_sqft_et);
        dimensionHhkSp = findViewById(R.id.assets_home_type_dimension_sp);
        noOfFloorsSp = findViewById(R.id.assets_home_type_no_of_floors_sp);
        durationOfStaySp = findViewById(R.id.assets_home_type_duration_of_stay_sp);
        societyNameBoardSp = findViewById(R.id.assets_home_type_society_name_sp);
        doorNamePlateSp = findViewById(R.id.assets_home_type_door_name_plate_sp);
        utilityBillSp = findViewById(R.id.assets_home_type_utility_bills_sp);
        localitySp = findViewById(R.id.assets_home_type_prop_locality_sp);

        interpaintedCb = findViewById(R.id.assets_home_type_painted_cb);
        interCleanCb = findViewById(R.id.assets_home_type_clean_cb);
        interCarpetCb = findViewById(R.id.assets_home_type_carpet_cb);
        interSofaCb = findViewById(R.id.assets_home_type_sofa_cb);
        interCurtainCb = findViewById(R.id.assets_home_type_curtain_cb);
        interShowcaseCb = findViewById(R.id.assets_home_type_showcase_cb);

        exterGardenCb = findViewById(R.id.assets_home_type_garden_cb);
        exterElevatorCb = findViewById(R.id.assets_home_type_elevator_cb);
        exterCarParkingCb = findViewById(R.id.assets_home_type_car_parking_cb);
        exterSecurityCb = findViewById(R.id.assets_home_type_security_cb);
        exterSwimmingCb = findViewById(R.id.assets_home_type_swimming_cb);
        exterintercomCb = findViewById(R.id.assets_home_type_intercom_cb);

        homeRemark = findViewById(R.id.assets_home_type_aplc_remark_et);


        noOfFloorsSp = findViewById(R.id.assets_home_type_no_of_floors_sp);
        durationOfStaySp = findViewById(R.id.assets_home_type_duration_of_stay_sp);
        dateEt = findViewById(R.id.assets_home_type_date_et);
        timeEt = findViewById(R.id.assets_home_type_time_et);
        //product type home
        homeLL = findViewById(R.id.assets_home_linear_layout);
        compressHomeIv = findViewById(R.id.assets_home_layout_iv);
        //product type car
        carTypeLayout = findViewById(R.id.assets_car_type_layout);
        //product type car

        //product type mortgage
        mortgageTypeLayout = findViewById(R.id.assets_mortgage_type_layout);
        mortgageTypeSp = findViewById(R.id.assets_product_type_mortgage_sp);
        loanLayout = findViewById(R.id.layout_mortgage_type_land);
        machineryLayout = findViewById(R.id.layout_mortgage_type_machinery);
        stockLayout = findViewById(R.id.layout_mortgage_type_stock);
        landAreaEt = findViewById(R.id.assets_aplc_mortgage_land_area_et);
        landOwnershipEt = findViewById(R.id.assets_aplc_mortgage_land_ownership_et);
        machineryTypeEt = findViewById(R.id.assets_aplc_mortgage_machinery_type_et);
        stockRawMaterialEt = findViewById(R.id.assets_aplc_mortgage_stock_raw_material_et);
        stockFinishWoodEt = findViewById(R.id.assets_aplc_mortgage_stock_finish_wood_et);
        //product type mortgage

        assetsRlSubmit = findViewById(R.id.layout_assets_home_type_submit);

        setDateTime();
        setSpinnerYear();

//        aplcId = SharedPrefAuth.getInstance(getApplicationContext()).getAplcId(getApplicationContext());
        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        progressdialog = new ProgressDialog(AssetsVerifActivity.this);
        progressdialog.setMessage("Please Wait....");
    }

    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                AssetsVerifActivity.super.onBackPressed();
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