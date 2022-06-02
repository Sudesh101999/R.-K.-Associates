package com.rkassociates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rkassociates.Auth.LoginActivity;
import com.rkassociates.SharedPref.SharedPrefAuth;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {

    private ImageView image;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        image = findViewById(R.id.img);
        image.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_indefinetly));

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                if(SharedPrefAuth.getInstance(getApplicationContext()).checkLoginStatus(SplashActivity.this)==0) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                finish();
            }
        }, 3500);
    }
}