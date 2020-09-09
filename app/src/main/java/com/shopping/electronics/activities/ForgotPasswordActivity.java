package com.shopping.electronics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shopping.electronics.utils.AppConstants;
import com.shopping.electronics.*;
import com.shopping.electronics.models.UserDo;

/**
 * This class deals with password reset
 */
public class ForgotPasswordActivity extends CommonActivity {

    private EditText etEmail, etPassword, etRenterPassword;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.forgot_password_layout);

        TextView tvLogin                 = findViewById(R.id.tvLogin);
        TextView tvCancel                = findViewById(R.id.tvCancel);
        etEmail                 = findViewById(R.id.etEmail);
        etPassword              = findViewById(R.id.etPassword);
        etRenterPassword        = findViewById(R.id.etRenterPassword);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter email");
                }
                else if(!isValidEmail(etEmail.getText().toString().trim())){
                    showErrorMessage("Please enter valid email");
                }
                else if(etPassword.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please enter password");
                }
                else if(etPassword.getText().toString().trim().length() < 6){
                    showErrorMessage("Please enter password minimum 6 characters");
                }
                else if(etRenterPassword.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please re-enter password");
                }
                else if(etRenterPassword.getText().toString().trim().length() < 6){
                    showErrorMessage("Please re-enter password minimum 6 characters");
                }
                else if(!etPassword.getText().toString().trim().equalsIgnoreCase(etRenterPassword.getText().toString().trim())){
                    showErrorMessage("Please password and re-enter password are same");
                }
                else {
                    if(isNetworkConnectionAvailable(ForgotPasswordActivity.this)){
                        setNewPassword();
                    }
                    else {
                        showErrorMessage(getString(R.string.network_error));
                    }
                }
            }
        });
    }

    /**
     * This method is used to set a new password
     */
    private void setNewPassword() {
        UserDo userDo = (UserDo) getObject(AppConstants.userFile);
        if(!userDo.email.equalsIgnoreCase(etEmail.getText().toString().trim())){
            showErrorMessage("Invalid email id");
        }
        else {
            userDo.password = etPassword.getText().toString().trim();
            saveObject(AppConstants.userFile, userDo);
            Intent intent = new Intent(ForgotPasswordActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
