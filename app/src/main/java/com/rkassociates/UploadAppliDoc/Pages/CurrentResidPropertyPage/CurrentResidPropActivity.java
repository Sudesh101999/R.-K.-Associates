package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefApplicantInfo;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.CRPInterface;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.CRPResponse;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.ReadData.CRPReadResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentResidPropActivity extends AppCompatActivity {

    private ProgressDialog progressdialog;
    final Calendar myCalendar = Calendar.getInstance();
    private ImageView currentResidPropBackIv;
    private RelativeLayout currentResidPropSubmit;
    private EditText aplcNameEt, dimentionEt, remarkEt, dateEt, timeEt;
    private Spinner propertStateSp, typeUnitSp, accessibilitySp, addresConfimedSp, dimentionFitSp, noOfFloorsSp, durationOfStaySp,
            societyNameBoardSp, doorNamePlateSp, utilityBillsSP, localitySp;
    private CheckBox interiorsPaintedCb, interiorsCleanCb, interiorsCarpetCb, interiorsSofaCb, interiorsCurtainCb, interiorsShowcaseCb;
    private CheckBox exteriorsGardenCb, exteriorsElevatorCb, exteriorsCarParkingCb, exteriorsSecurityCb, exteriorsSwimmingCb, exteriorsIntercomCb;
    private String classOfLocalityStr = "";

    private ArrayList<String> interiorArray = new ArrayList<>();
    private ArrayList<String> exteriorArray = new ArrayList<>();
    ArrayList<String> floorsArray, durationArray;
    String executiveIdStr;

    private String activityFor, addDataIdIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_resid_prop);

        initData();

        setDateTime();
//        setSpinnerYear();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityFor = extras.getString("activityFor");
            //The key argument here must match that used in the other activity
            addDataIdIntentStr = extras.getString("addDataIdIntentStr");

            if (activityFor.equals("Pending")) {
                Log.d("AddDataId", addDataIdIntentStr);
                String aplcNameIntentStr = extras.getString("aplcNameIntentStr");
                aplcNameEt.setText(aplcNameIntentStr);
                getDataFromDatabase();
            } else if (activityFor.equals("newData")) {
                String aplcName = SharedPrefApplicantInfo.getInstance(getApplicationContext()).getAplcName(getApplicationContext());
                aplcNameEt.setText(aplcName);
            }
        }

        //checkbox operation
        setChecBoxVal();

        setOnclickOperation();
    }

    private void getDataFromDatabase() {

        progressdialog.show();

        CRPInterface crpInterface = ApiClient.getRetrofitInstance().create(CRPInterface.class);
        Call<CRPReadResponse> call = crpInterface.CRPReadData(addDataIdIntentStr, executiveIdStr);

        call.enqueue(new Callback<CRPReadResponse>() {
            @Override
            public void onResponse(Call<CRPReadResponse> call, Response<CRPReadResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Toast.makeText(CurrentResidPropActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        setDataToText(
                                response.body().getData().getCurrentResidencePropertyTable().getApplicantName(),
                                response.body().getData().getCurrentResidencePropertyTable().getPropertyStatus(),
                                response.body().getData().getCurrentResidencePropertyTable().getTypeOfUnit(),
                                response.body().getData().getCurrentResidencePropertyTable().getAccessibility(),
                                response.body().getData().getCurrentResidencePropertyTable().getAddressConfirmed(),
                                response.body().getData().getCurrentResidencePropertyTable().getDimenension(),
                                response.body().getData().getCurrentResidencePropertyTable().getNumberOfFloors(),
                                response.body().getData().getCurrentResidencePropertyTable().getDurationOfStay(),
                                response.body().getData().getCurrentResidencePropertyTable().getSocietyNameBoard(),
                                response.body().getData().getCurrentResidencePropertyTable().getDoorNamePlate(),
                                response.body().getData().getCurrentResidencePropertyTable().getUtilityBills(),
                                response.body().getData().getCurrentResidencePropertyTable().getClassOfLocality(),
                                response.body().getData().getCurrentResidencePropertyTable().getInteriors(),
                                response.body().getData().getCurrentResidencePropertyTable().getExteriors(),
                                response.body().getData().getCurrentResidencePropertyTable().getRemark()
                        );
                    } else {
                        snackBarMsg("get Message: " + response.body().getMessage());
                    }
                    Log.d("get CurrentResidProp", "Status: " + response.body().getStatus());

                } catch (Exception e) {
                    Log.e("get CurrentResidProp", "Exception: " + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<CRPReadResponse> call, Throwable t) {
                progressdialog.dismiss();
                snackBarMsg("Message: " + t.getMessage());
                Log.e("Current Residence prop", t.getMessage());
            }
        });
    }

    private void setDataToText(String aplcNameStr, String propertyStatus, String typeOfUnit, String accessibility, String addressConfirmed,
                               String dimenension, String numberOfFloors, String durationOfStay, String societyNameBoard,
                               String doorNamePlate, String utilityBills, String classOfLocality, String interiors,
                               String exteriors, String remark) {

        ArrayAdapter<CharSequence> propertyStatusAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.property_status_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> accessibilityAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.accessibility_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> yesNoAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.yes_no_sp, android.R.layout.simple_spinner_item);
        //dimension
        String[] separatedDimension = dimenension.split("\\s+");
        String dimension1 = separatedDimension[0];
        String dimension2 = separatedDimension[1] + " " + separatedDimension[2];
        Log.d("separatedDimension: ", "Dimension: " + dimension1 + "BHK=> " + dimension2);
        ArrayAdapter<CharSequence> dimensionAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.dimension_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> noOfFloorAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.num_floor_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.duration_stay_sp, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> uilityAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.utility_bill_sp, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> localityAdapter = ArrayAdapter.
                createFromResource(CurrentResidPropActivity.this,
                        R.array.locality_sp, android.R.layout.simple_spinner_item);

//        aplcNameEt.setText(aplcNameStr);
        remarkEt.setText(remark);
        setSpinnerData(propertyStatus, propertyStatusAdapter, propertStateSp);
        setSpinnerData(typeOfUnit, propertyStatusAdapter, typeUnitSp);
        setSpinnerData(accessibility, accessibilityAdapter, accessibilitySp);
        setSpinnerData(addressConfirmed, yesNoAdapter, addresConfimedSp);
        dimentionEt.setText(dimension1);
        setSpinnerData(dimension2, dimensionAdapter, dimentionFitSp);
        setSpinnerData(societyNameBoard, yesNoAdapter, societyNameBoardSp);
        setSpinnerData(doorNamePlate, yesNoAdapter, doorNamePlateSp);
        setSpinnerData(numberOfFloors, noOfFloorAdapter, noOfFloorsSp);
        setSpinnerData(durationOfStay, durationAdapter, durationOfStaySp);
        setSpinnerData(utilityBills, uilityAdapter, utilityBillsSP);
        setSpinnerData(classOfLocality, localityAdapter, localitySp);

        String[] interiorsList = interiors.split("[,]", 0);
        Log.d("interiorsList: ", Arrays.toString(interiorsList));
        for (String s : interiorsList) {
            if (s.equals(" Painted")) {
                interiorsPaintedCb.setChecked(true);
            } else if (s.equals(" Clean")) {
                interiorsCleanCb.setChecked(true);
            } else if (s.equals(" Carpet")) {
                interiorsCarpetCb.setChecked(true);
            } else if (s.equals(" Sofa")) {
                interiorsSofaCb.setChecked(true);
            } else if (s.equals(" Curtain")) {
                interiorsCurtainCb.setChecked(true);
            } else if (s.equals(" Showcase")) {
                interiorsShowcaseCb.setChecked(true);
            }
        }

        String[] exteriorList = exteriors.split("[,]", 0);
        for (String s : exteriorList) {
            if (s.equals(" Garden")) {
                exteriorsGardenCb.setChecked(true);
            } else if (s.equals(" Elevator")) {
                exteriorsElevatorCb.setChecked(true);
            } else if (s.equals(" Car parking")) {
                exteriorsCarParkingCb.setChecked(true);
            } else if (s.equals(" Security")) {
                exteriorsSecurityCb.setChecked(true);
            } else if (s.equals(" Swimming Pool")) {
                exteriorsSwimmingCb.setChecked(true);
            } else if (s.equals(" Intercom")) {
                exteriorsIntercomCb.setChecked(true);
            }
        }


    }

    private void setSpinnerData(String compareValue, ArrayAdapter<CharSequence> adapter, Spinner mSpinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition, true);
        }
    }

    private void setChecBoxVal() {
        interiorsPaintedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsPaintedCb.isChecked()) {
                    interiorArray.add(interiorsPaintedCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsPaintedCb.getText().toString());
                }
            }
        });
        interiorsCleanCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsCleanCb.isChecked()) {
                    interiorArray.add(interiorsCleanCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsCleanCb.getText().toString());
                }
            }
        });
        interiorsCarpetCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsCarpetCb.isChecked()) {
                    interiorArray.add(interiorsCarpetCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsCarpetCb.getText().toString());
                }
            }
        });
        interiorsSofaCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsSofaCb.isChecked()) {
                    interiorArray.add(interiorsSofaCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsSofaCb.getText().toString());
                }
            }
        });
        interiorsCurtainCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsCurtainCb.isChecked()) {
                    interiorArray.add(interiorsCurtainCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsCurtainCb.getText().toString());
                }
            }
        });
        interiorsShowcaseCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (interiorsShowcaseCb.isChecked()) {
                    interiorArray.add(interiorsShowcaseCb.getText().toString());
                } else {
                    interiorArray.remove(interiorsShowcaseCb.getText().toString());
                }
            }
        });
        exteriorsGardenCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsGardenCb.isChecked()) {
                    exteriorArray.add(exteriorsGardenCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsGardenCb.getText().toString());
                }
            }
        });
        exteriorsElevatorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsElevatorCb.isChecked()) {
                    exteriorArray.add(exteriorsElevatorCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsElevatorCb.getText().toString());
                }
            }
        });
        exteriorsCarParkingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsCarParkingCb.isChecked()) {
                    exteriorArray.add(exteriorsCarParkingCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsCarParkingCb.getText().toString());
                }
            }
        });
        exteriorsSecurityCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsSecurityCb.isChecked()) {
                    exteriorArray.add(exteriorsSecurityCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsSecurityCb.getText().toString());
                }
            }
        });
        exteriorsSwimmingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsSwimmingCb.isChecked()) {
                    exteriorArray.add(exteriorsSwimmingCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsSwimmingCb.getText().toString());
                }
            }
        });
        exteriorsIntercomCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (exteriorsIntercomCb.isChecked()) {
                    exteriorArray.add(exteriorsIntercomCb.getText().toString());
                } else {
                    exteriorArray.remove(exteriorsIntercomCb.getText().toString());
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void setOnclickOperation() {
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
                new DatePickerDialog(CurrentResidPropActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                mTimePicker = new TimePickerDialog(CurrentResidPropActivity.this, new TimePickerDialog.OnTimeSetListener() {
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


        durationOfStaySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        currentResidPropSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }


    private void setSpinnerYear() {

        floorsArray = new ArrayList<>();
        durationArray = new ArrayList<>();
        String startNum = "--Select--";
        floorsArray.add(startNum);
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

    private void checkValidation() {
        /*String propertyStatusStr,typeOfUnitStr,accessiblityStr,addConfirmStr,dimensionStr,noOfFloorStr,
                durationStr,soclietyNameStr,doorNameStr,utilityBillStr,localityStr,propertyInteriorsStr,dateStr,timeStr;*/

        String aplcName = "", propertyStatusStr = "", typeOfUnitStr = "", accessiblityStr = "",
                addConfirmStr = "", dimensionStr = "", noOfFloorStr = "", durationStr = "", soclietyNameStr = "",
                doorNameStr = "", utilityBillStr = "", localityStr = "", propertyInteriorsStr = "", propertyExteriorsStr = "",
                dateTimeStr = "", remarkStr = "";

        if (aplcNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Applicant name getting empty...!!!");
            return;
        } else
            aplcName = aplcNameEt.getText().toString();

        if (propertStateSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Select status...!!!");
            return;
        } else
            propertyStatusStr = propertStateSp.getSelectedItem().toString();

        if (typeUnitSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Select Type of Unit...!!!");
            return;
        } else
            typeOfUnitStr = typeUnitSp.getSelectedItem().toString();

        if (accessibilitySp.getSelectedItemPosition() == 0) {
            snackBarMsg("Select Accessibility...!!!");
            return;
        } else
            accessiblityStr = accessibilitySp.getSelectedItem().toString();

        if (addresConfimedSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Address confirmed...!!!");
            return;
        } else
            addConfirmStr = addresConfimedSp.getSelectedItem().toString();

        if (dimentionFitSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Dimension...!!!");
            return;
        } else
            dimensionStr = dimentionEt.getText().toString() + " " + dimentionFitSp.getSelectedItem().toString();

        if (noOfFloorsSp.getSelectedItemPosition() == 0) {
            snackBarMsg("No. Of Floors...!!!");
            return;
        } else
            noOfFloorStr = noOfFloorsSp.getSelectedItem().toString();

        if (durationOfStaySp.getSelectedItemPosition() == 0) {
            snackBarMsg("Duration Of Stay...!!!");
            return;
        } else
            durationStr = durationOfStaySp.getSelectedItem().toString();

        if (societyNameBoardSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Society Name board...!!!");
            return;
        } else
            soclietyNameStr = societyNameBoardSp.getSelectedItem().toString();

        if (doorNamePlateSp.getSelectedItemPosition() == 0) {
            snackBarMsg("Door Name Plate...!!!");
            return;
        } else
            doorNameStr = doorNamePlateSp.getSelectedItem().toString();

        if (utilityBillsSP.getSelectedItemPosition() == 0) {
            snackBarMsg("Utility Bills...!!!");
            return;
        } else
            utilityBillStr = utilityBillsSP.getSelectedItem().toString();

        if (localitySp.getSelectedItemPosition() == 0) {
            snackBarMsg("Locality...!!!");
            return;
        } else
            localityStr = localitySp.getSelectedItem().toString();

        if (interiorArray.size() == 0) {
            snackBarMsg("Select Interiors...!!!");
            return;
        } else {
            interiorArray.add(0, " ");
            interiorArray.add(interiorArray.size(), " ");
            String[] interiorsList = Arrays.copyOf(interiorArray.toArray(), interiorArray.size(), String[].class);
            propertyInteriorsStr = Arrays.toString(interiorsList);
        }

        if (exteriorArray.size() == 0) {
            snackBarMsg("Select Exterior...!!!");
            return;
        } else {
            exteriorArray.add(0, " ");
            exteriorArray.add(exteriorArray.size(), " ");
            String[] exteriorList = Arrays.copyOf(exteriorArray.toArray(), exteriorArray.size(), String[].class);
            propertyExteriorsStr = Arrays.toString(exteriorList);
        }

        remarkStr = remarkEt.getText().toString();
        dateTimeStr = dateEt.getText().toString() + " " + timeEt.getText().toString();
//        Toast.makeText(this, "propertyInteriorsStr: "+propertyInteriorsStr, Toast.LENGTH_SHORT).show();

        Log.d("checkValidation:", " dimensionStr: " + dimensionStr);

        submitData(executiveIdStr, aplcName, propertyStatusStr, typeOfUnitStr, accessiblityStr,
                addConfirmStr, dimensionStr, noOfFloorStr, durationStr, soclietyNameStr,
                doorNameStr, utilityBillStr, localityStr, propertyInteriorsStr, propertyExteriorsStr,
                dateTimeStr, remarkStr);


    }


    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void submitData(String executiveIdStr, String aplcName, String propertyStatusStr, String typeOfUnitStr, String accessiblityStr, String addConfirmStr, String dimensionStr, String noOfFloorStr, String durationStr, String soclietyNameStr, String doorNameStr, String utilityBillStr, String localityStr, String propertyInteriorsStr, String propertyExteriorsStr, String dateTimeStr, String remarkStr) {

        progressdialog.show();

        /*Toast.makeText(this, propertyStatusStr + "\n" + typeOfUnitStr + "\n" + accessiblityStr
                + "\n" + addConfirmStr + "\n" + dimensionStr + "\n" + noOfFloorStr + "\n" + durationStr
                + "\n" + soclietyNameStr + "\n" + doorNameStr + "\n" + utilityBillStr + "\n" + localityStr
                + "\n" + propertyInteriorsStr + "\n" + dateTimeStr, Toast.LENGTH_SHORT).show();*/

        CRPInterface crpInterface = ApiClient.getRetrofitInstance().create(CRPInterface.class);
        Call<CRPResponse> call = crpInterface.CRPInsertData(executiveIdStr, addDataIdIntentStr, propertyStatusStr, typeOfUnitStr, accessiblityStr,
                addConfirmStr, dimensionStr, noOfFloorStr, durationStr, soclietyNameStr,
                doorNameStr, utilityBillStr, localityStr, propertyInteriorsStr, propertyExteriorsStr, remarkStr,
                dateTimeStr);

        call.enqueue(new Callback<CRPResponse>() {
            @Override
            public void onResponse(Call<CRPResponse> call, Response<CRPResponse> response) {
                try {
                    if (response.body().getStatus() == 4) {
                        Toast.makeText(CurrentResidPropActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setCurrentResidProp(true);
                        CurrentResidPropActivity.super.onBackPressed();
                    } else {
                        snackBarMsg("Message: " + response.body().getMsg());
                    }
                    Log.d("CurrentResidProp", "Status: " + response.body().getStatus());
                } catch (Exception e) {
                    Log.e("CurrentResidProp", "Exception: " + e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<CRPResponse> call, Throwable t) {
                progressdialog.dismiss();
                Log.e("Current Residence prop", t.getMessage());
            }
        });
    }

    private void initData() {
        currentResidPropBackIv = findViewById(R.id.current_resid_property_page_back_btn_iv);
        aplcNameEt = findViewById(R.id.current_resi_property_aplc_name_et);
        propertStateSp = findViewById(R.id.current_resi_prop_property_status_sp);
        typeUnitSp = findViewById(R.id.current_resi_prop_type_unit_sp);
        accessibilitySp = findViewById(R.id.current_resi_prop_accessibility_sp);
        addresConfimedSp = findViewById(R.id.current_resi_prop_add_confirmed_sp);
        dimentionEt = findViewById(R.id.current_resi_prop_dimension_et);
        dimentionFitSp = findViewById(R.id.current_resi_prop_dimension_sp);
        noOfFloorsSp = findViewById(R.id.current_resi_prop_no_of_floors_sp);
        durationOfStaySp = findViewById(R.id.current_resi_prop_duration_of_stay_sp);
        societyNameBoardSp = findViewById(R.id.current_resi_prop_society_name_sp);
        doorNamePlateSp = findViewById(R.id.current_resi_prop_door_name_plate_sp);
        utilityBillsSP = findViewById(R.id.current_resi_prop_utility_bills_sp);
        localitySp = findViewById(R.id.current_resi_prop_locality_sp);

        interiorsPaintedCb = findViewById(R.id.current_resi_prop_interior_painted_cb);
        interiorsCleanCb = findViewById(R.id.current_resi_prop_interior_clean_cb);
        interiorsCarpetCb = findViewById(R.id.current_resi_prop_interior_carpet_cb);
        interiorsSofaCb = findViewById(R.id.current_resi_prop_interior_sofa_cb);
        interiorsCurtainCb = findViewById(R.id.current_resi_prop_interior_curtain_cb);
        interiorsShowcaseCb = findViewById(R.id.current_resi_prop_interior_showcase_cb);

        exteriorsGardenCb = findViewById(R.id.current_resi_prop_exteriors_garden_cb);
        exteriorsElevatorCb = findViewById(R.id.current_resi_prop_exteriors_elevator_cb);
        exteriorsCarParkingCb = findViewById(R.id.current_resi_prop_exteriors_car_parking_cb);
        exteriorsSecurityCb = findViewById(R.id.current_resi_prop_exteriors_security_cb);
        exteriorsSwimmingCb = findViewById(R.id.current_resi_prop_exteriors_swimming_poa_cb);
        exteriorsIntercomCb = findViewById(R.id.current_resi_prop_exteriors_intercom_cb);

        remarkEt = findViewById(R.id.current_resi_prop_aplc_remark_et);
        dateEt = findViewById(R.id.current_resi_prop_date_et);
        timeEt = findViewById(R.id.current_resi_prop_time_et);

        currentResidPropSubmit = findViewById(R.id.layout_curre_resid_prop_submit);

        progressdialog = new ProgressDialog(CurrentResidPropActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);

//        aplcNameEt.setText(SharedPrefAuth.getInstance(getApplicationContext()).getAplcName(getApplicationContext()));

        executiveIdStr = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

    }

    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }
}