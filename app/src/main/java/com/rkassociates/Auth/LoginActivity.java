package com.rkassociates.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.rkassociates.Common.ApiClient;
import com.rkassociates.MainActivity;
import com.rkassociates.R;
import com.rkassociates.SharedPref.SharedPrefAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout usernameTl, passTl;
    private CardView loginCard;
    private TextView loginTv;
    private RelativeLayout loginLayout;
    private Spinner userTypeSp;

    private RelativeLayout progressLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();

        loginCard.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        loginLayout.setOnClickListener(this);

    }

    private void validation() {
        String usernameStr = usernameTl.getEditText().getText().toString();
        String passStr = passTl.getEditText().getText().toString();

        if (usernameStr.isEmpty()) {
            usernameTl.setError("Username is getting empty...!");
            usernameTl.setFocusable(true);
            snackBarMsg("Username is getting empty...!");
            return;
        } else {
            usernameTl.setError(null);
            usernameTl.setFocusable(false);
        }
        if (passStr.isEmpty()) {
            passTl.setError("Password is getting empty...!");
            passTl.setFocusable(true);
            snackBarMsg("Password is getting empty...!");
            return;
        } else {
            passTl.setError(null);
            passTl.setFocusable(false);
        }
        insertDataToDatabase(usernameStr, passStr);
    }

    //Login data to sharePref
    private void insertDataToDatabase(String usernameStr, String passStr) {
        progressBar.setVisibility(View.VISIBLE);

        loginInterface loginInterface = ApiClient.getRetrofitInstance().create(loginInterface.class);
        Call<loginResponseModel> call = loginInterface.newLogin(usernameStr, passStr);
        call.enqueue(new Callback<loginResponseModel>() {
            @Override
            public void onResponse(Call<loginResponseModel> call, Response<loginResponseModel> response) {
                try {

                    if (response.body().getStatus() == 1) {
                        Log.d("data", String.valueOf(response.body().ResultObject.email));

                        Toast.makeText(LoginActivity.this, "Message: " + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        String name, userId, email, phone_number;

                        name = response.body().ResultObject.name;
                        userId = response.body().ResultObject.id;
                        email = response.body().ResultObject.email;
                        phone_number = response.body().ResultObject.phone_number;

                        SharedPrefAuth.getInstance(getApplicationContext()).userLogin(name, userId, email, phone_number);

                        SharedPrefAuth.getInstance(getApplicationContext()).setLoginStatus(LoginActivity.this, 1);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.e("response", String.valueOf(response.body().getMsg()));
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onResponse: "+e.getMessage());
                    Toast.makeText(LoginActivity.this, "Invalid email or password...!!!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<loginResponseModel> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                progressBar.setVisibility(View.GONE);

                Toast.makeText(LoginActivity.this, "Invalid email or password...!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void snackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void init() {
        loginCard = findViewById(R.id.login_card);
        loginTv = findViewById(R.id.login_tv);
        loginLayout = findViewById(R.id.login_relative);

        usernameTl = findViewById(R.id.login_et_layout_username);
        passTl = findViewById(R.id.login_et_layout_password);
        progressLayout = findViewById(R.id.log_progress_layout);
        progressBar = findViewById(R.id.log_progress_bar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_card:
            case R.id.login_relative:
            case R.id.login_tv:
                validation();
                break;

        }
    }
}