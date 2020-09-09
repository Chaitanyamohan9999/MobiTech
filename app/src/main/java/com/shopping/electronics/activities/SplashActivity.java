package com.shopping.electronics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.shopping.electronics.*;

import androidx.annotation.Nullable;

/**
 * This class is used to show the splash screen
 */
public class SplashActivity extends CommonActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }
}
