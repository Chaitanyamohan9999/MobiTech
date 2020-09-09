package com.shopping.electronics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.electronics.models.UserDo;
import com.shopping.electronics.utils.AppConstants;
import com.shopping.electronics.*;
import com.shopping.electronics.utils.LoadProducts;

import androidx.annotation.Nullable;

/**
 * This class deals with user Login activity
 */
public class LoginActivity extends CommonActivity {

    private EditText etEmail, etPassword;
    private TextView tvForgotPwd, tvRegister, tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        tvForgotPwd             = findViewById(R.id.tvForgotPwd);
        tvRegister                = findViewById(R.id.tvRegister);
        tvLogin                 = findViewById(R.id.tvLogin);
        etEmail                 = findViewById(R.id.etEmail);
        etPassword              = findViewById(R.id.etPassword);

//        etEmail.setText("king@gmail.com");
//        etPassword.setText("123456");
        tvForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(etEmail.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(etPassword.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(etPassword.getText().toString().trim().length() < 6){
                    Toast.makeText(LoginActivity.this, "Please enter password minimum 6 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(isNetworkConnectionAvailable(LoginActivity.this)){
                        doLogin();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /**
     * This method is used to login to application
     */
    private void doLogin() {
        Object object = getObject(AppConstants.userFile);
        if (object instanceof UserDo) {
            UserDo userDo = (UserDo) object;
            if (userDo.email.equalsIgnoreCase(etEmail.getText().toString().trim())
                    && userDo.password.equals(etPassword.getText().toString().trim())) {
                LoadProducts.loadData();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                showErrorMessage("Invalid user name or password");
            }
        }
        else {
            showErrorMessage("The entered email and password are not exist");
        }
    }
}
