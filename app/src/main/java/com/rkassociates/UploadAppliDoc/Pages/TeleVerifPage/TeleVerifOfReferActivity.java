package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;
import com.rkassociates.SharedPref.SharedPrefDocuComplete;
import com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.ReadData.TeleVerifResponse;
import com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.teleInterface;
import com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.teleVerifResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeleVerifOfReferActivity extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();
    private ImageView backIv;
    private ProgressDialog progressdialog;
    private EditText teleNameEt,telePhoneEt,teleCallingByEt,dateEt,timeEt,teleConversationEt;
    private EditText teleName2Et,telePhone2Et,teleCallingBy2Et,teleConversation2Et;
    private RelativeLayout teleSubmitLL;
    private String executiveNameStr;
    private String activityFor,addDataIdIntentStr,executiveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_verif_of_refer_page);

        initData();

        setDateTime();

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

        onclickOperation();
    }

    private void getDataFromDatabase() {
        Log.e("getDataFromDatabase", "addDataIdIntentStr: "+addDataIdIntentStr+"\nexecutiveId: "+executiveId);

        progressdialog.show();

        teleInterface profileInterface = ApiClient.getRetrofitInstance().create(teleInterface.class);
        Call<TeleVerifResponse> call = profileInterface.readTeleVerifData(addDataIdIntentStr,executiveId);
        call.enqueue(new Callback<TeleVerifResponse>() {
            @Override
            public void onResponse(Call<TeleVerifResponse> call, Response<TeleVerifResponse> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        Log.d("Profile Page Response",response.body().getMessage());

                        setDataToTextview(
                                response.body().getData().getProfileRemarkTable().getRefrenceName(),
                                response.body().getData().getProfileRemarkTable().getPhoneNumber(),
                                response.body().getData().getProfileRemarkTable().getTelecallingBy(),
                                response.body().getData().getProfileRemarkTable().getConversation(),
                                response.body().getData().getProfileRemarkTable().getRefrenceName1(),
                                response.body().getData().getProfileRemarkTable().getPhoneNumber1(),
                                response.body().getData().getProfileRemarkTable().getTelecallingBy1(),
                                response.body().getData().getProfileRemarkTable().getConversation1()
                        );
                    }else{
                        Log.d("Profile Page Data",response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<TeleVerifResponse> call, Throwable t) {
                Log.e("Profile OnFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });
    }

    private void setDataToTextview(String refrenceName, String phoneNumber, String telecallingBy,
                                   String conversation, String refrenceName1, String phoneNumber1,
                                   String telecallingBy1, String conversation1) {

        teleNameEt.setText(refrenceName);
        telePhoneEt.setText(phoneNumber);
        teleCallingByEt.setText(telecallingBy);
        teleConversationEt.setText(conversation);

        teleName2Et.setText(refrenceName1);
        telePhone2Et.setText(phoneNumber1);
        teleCallingBy2Et.setText(telecallingBy1);
        teleConversation2Et.setText(conversation1);

    }


    private void setDateTime() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        dateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void onclickOperation() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });
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
                new DatePickerDialog(TeleVerifOfReferActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                mTimePicker = new TimePickerDialog(TeleVerifOfReferActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        teleSubmitLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateDate();
            }
        });

    }

    private void snackBarMsg(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void validateDate() {
        String teleNameStr,telePhoneStr,teleCallingByStr,dateTime1Str,teleConversationStr;
        String teleName2Str,telePhone2Str,teleCallingBy2Str,dateTime2Str,teleConversation2Str;

        if (teleNameEt.getText().toString().isEmpty()) {
            snackBarMsg("Executive name getting empty...!!!");
            return;
        }else {
            teleNameStr = teleNameEt.getText().toString();
        }
        if (telePhoneEt.getText().toString().isEmpty()) {
            snackBarMsg("Phone getting empty...!!!");
            return;
        }else {
            telePhoneStr = telePhoneEt.getText().toString();
        }
        if (teleCallingByEt.getText().toString().isEmpty()) {
            snackBarMsg("Tele Calling by is getting empty...!!!");
            return;
        }else {
            teleCallingByStr = teleCallingByEt.getText().toString();
        }
        if (teleConversationEt.getText().toString().isEmpty()) {
            snackBarMsg("Conversation getting empty...!!!");
            return;
        }else {
            teleConversationStr = teleConversationEt.getText().toString();
        }
        teleName2Str=teleName2Et.getText().toString();
        telePhone2Str=telePhone2Et.getText().toString();
        teleCallingBy2Str=teleCallingBy2Et.getText().toString();
        teleConversation2Str=teleConversation2Et.getText().toString();
        dateTime1Str=dateEt.getText().toString()+" "+timeEt.getText().toString();



        insertToDatabase(teleNameStr,telePhoneStr,teleCallingByStr,dateTime1Str,teleConversationStr,
                teleName2Str,telePhone2Str,teleCallingBy2Str,teleConversation2Str);
    }

    private void insertToDatabase(String teleNameStr, String telePhoneStr, String teleCallingByStr,
                                  String dateTime1Str, String teleConversationStr, String teleName2Str,
                                  String telePhone2Str, String teleCallingBy2Str,  String teleConversation2Str) {

        progressdialog.show();

        teleInterface anInterface = ApiClient.getRetrofitInstance().create(teleInterface.class);
        Call<teleVerifResponse> call = anInterface.insertTeleVerifData(executiveId,addDataIdIntentStr,teleNameStr,telePhoneStr,teleCallingByStr,dateTime1Str,teleConversationStr,
                teleName2Str,telePhone2Str,teleCallingBy2Str,teleConversation2Str);
        call.enqueue(new Callback<teleVerifResponse>() {
            @Override
            public void onResponse(Call<teleVerifResponse> call, Response<teleVerifResponse> response) {
                try {
                    if (response.body().getStatus()==4){
                        Log.d("Data aplc loaded", String.valueOf(response.body().getResult()));

                        Toast.makeText(TeleVerifOfReferActivity.this, "Message: "+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPrefDocuComplete.getInstance(getApplicationContext()).setTeleVerif(true);
                        TeleVerifOfReferActivity.super.onBackPressed();

                    }else{
                        Log.e("TeleVerif error",response.body().getMsg());
                    }
                    Log.e("TeleVerif status", response.body().getStatus() + "\n" + response.body().getMsg());
                }catch (Exception e){
                    Log.e("TeleVerif"," data exception: "+e.getMessage());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<teleVerifResponse> call, Throwable t) {
                Log.e("TeleVerif onFailure",t.getMessage());
                progressdialog.dismiss();
            }
        });

    }

    private void initData() {
        backIv = findViewById(R.id.tele_verif_page_back_btn_iv);
        teleNameEt=findViewById(R.id.tele_verif_name_et);
        telePhoneEt=findViewById(R.id.tele_verif_phone_et);
        teleCallingByEt=findViewById(R.id.tele_verif_telecalling_by_et);
        dateEt=findViewById(R.id.telecalling_by_date_et);
        timeEt=findViewById(R.id.telecalling_by_time_et);
        teleConversationEt=findViewById(R.id.telecalling_by_conversation_et);
        teleName2Et=findViewById(R.id.tele_verif_name_2_et);
        telePhone2Et=findViewById(R.id.tele_verif_phone_2_et);
        teleCallingBy2Et=findViewById(R.id.tele_verif_telecalling_by_2_et);
        teleConversation2Et=findViewById(R.id.telecalling_by_conversation_2_et);

        teleSubmitLL=findViewById(R.id.layout_tele_verif_submit);

        executiveNameStr = SharedPrefAuth.getInstance(getApplicationContext()).getUserName(getApplicationContext());
        executiveId = SharedPrefAuth.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        teleCallingByEt.setText(executiveNameStr);
        teleCallingBy2Et.setText(executiveNameStr);

        progressdialog = new ProgressDialog(TeleVerifOfReferActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
    }
    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                TeleVerifOfReferActivity.super.onBackPressed();
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