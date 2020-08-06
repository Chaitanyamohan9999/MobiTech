package com.example.mobitech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobitech.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

        Button etLogin;
        EditText etUsername, etPassword;
        TextView etRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etLogin = (Button) findViewById(R.id.etLogin);
        etRegister = (TextView) findViewById(R.id.etRegister);

        etLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.etLogin:


            break;

            case R.id.etRegister:
                startActivity(new Intent(this, Register.class));




                break;
    }
}
}