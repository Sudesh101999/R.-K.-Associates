package com.rkassociates.HistoryDetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rkassociates.R;

public class HistoryDetailsActivity extends AppCompatActivity {

    private String addDataIdIntentStr,applicantNameIntentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            addDataIdIntentStr = extras.getString("addDataId");
            applicantNameIntentStr = extras.getString("ApplicantName");
        }
    }
}