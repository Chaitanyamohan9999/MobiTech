package com.example.mobitech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent =getIntent();
        tv =(TextView)findViewById(R.id.textView9);
        String msg =intent.getStringExtra("MESSAGE");
        tv.setText(msg);
    }
}
