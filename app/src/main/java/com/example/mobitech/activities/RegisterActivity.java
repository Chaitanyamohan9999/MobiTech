package com.example.mobitech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mobitech.R;
import com.example.mobitech.models.UserDo;
import com.example.mobitech.utils.AppConstants;


/**
 * This class deals with registration related activities
 */
public class RegisterActivity extends CommonActivity {

    private EditText etName, etEmail, etPhone, etCountry, etCity, etState, etZipCode, etPassword, etRenterPassword;
    private RadioGroup rgGender;
    private RadioButton rbFemale, rbMale;
    private TextView tvLogin, tvRegister;
    private String gender = "";


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.register_layout);
        initialiseControls();
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbFemale){
                    gender = "Female";
                }
                else if(checkedId == R.id.rbMale){
                    gender = "Male";
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter name");
                }
                else if(etEmail.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter email");
                }
                else if(etPhone.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter phone number");
                }
                else if(etPhone.getText().toString().trim().length() !=10){
                    showErrorMessage("Please enter valid phone number");
                }
                else if(!isValidEmail(etEmail.getText().toString().trim())){
                    showErrorMessage("Please enter email");
                }
                else if(etCountry.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter country");
                }
                else if(etCity.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter city");
                }
                else if(etState.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter province");
                }
                else if(etZipCode.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter zipcode");
                }
                else if(gender.equalsIgnoreCase("")){
                    showErrorMessage("Please select gender");
                }
                else if(etPassword.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter password");
                }
                else if(etPassword.getText().toString().length() < 6){
                    showErrorMessage("Please enter password minimum 6 characters");
                }
                else if(etRenterPassword.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please Re-enter password");
                }
                else if(!etPassword.getText().toString().equalsIgnoreCase(etRenterPassword.getText().toString())){
                    showErrorMessage("Please enter password and Re-enter password are same");
                }
                else {
                    if(isNetworkConnectionAvailable(RegisterActivity.this)){
                        doRegistration();
                    }
                    else {
                        showErrorMessage(getResources().getString(R.string.network_error));
                    }
                }
            }
        });
    }

    /**
     * Obtaining references of views
     */
    private void initialiseControls(){
        rgGender                            = findViewById(R.id.rgGender);
        rbFemale                            = findViewById(R.id.rbFemale);
        rbMale                              = findViewById(R.id.rbMale);

        etName                              = findViewById(R.id.etName);
        etEmail                             = findViewById(R.id.etEmail);
        etPhone                             = findViewById(R.id.etPhone);
        etCountry                           = findViewById(R.id.etCountry);
        etState                             = findViewById(R.id.etState);
        etCity                              = findViewById(R.id.etCity);
        etZipCode                           = findViewById(R.id.etZipCode);
        etPassword                          = findViewById(R.id.etPassword);
        etRenterPassword                    = findViewById(R.id.etRenterPassword);

        tvRegister                          = findViewById(R.id.tvRegister);
        tvLogin                             = findViewById(R.id.tvLogin);
    }

    /**
     * This method registers a user to local store application data
     */
    private void doRegistration(){
        UserDo userDo = new UserDo();
        userDo.name  = etName.getText().toString().trim();
        userDo.email = etEmail.getText().toString().trim();
        userDo.phone = etPhone.getText().toString().trim();
        userDo.country = etCountry.getText().toString().trim();
        userDo.city = etCity.getText().toString().trim();
        userDo.state = etState.getText().toString().trim();
        userDo.zipCode = etZipCode.getText().toString().trim();
        userDo.password = etPassword.getText().toString().trim();
        userDo.gender = gender;
        saveObject(AppConstants.userFile, userDo);
        showErrorMessage("You have successfully registered!");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
