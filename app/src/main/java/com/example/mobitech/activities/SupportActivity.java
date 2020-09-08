package com.example.mobitech.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobitech.R;


/**
 * This class deals with support related activities
 */
public class SupportActivity extends CommonActivity {

    private TextView tvSubmit;
    private ImageView ivBack;
    private EditText etName, etPhone, etComments;
    private TextView tvSupportPhone;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.support_layout);

        ivBack                  = findViewById(R.id.ivBack);
        etName                  = findViewById(R.id.etName);
        tvSubmit                = findViewById(R.id.tvSubmit);
        etPhone                 = findViewById(R.id.etPhone);
        etComments              = findViewById(R.id.etComments);
        tvSupportPhone          = findViewById(R.id.tvSupportPhone);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSupportPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvSupportPhone.getText().toString().trim().equalsIgnoreCase("")){
                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + tvSupportPhone.getText().toString().trim()));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter your name");
                }
                else if(etPhone.getText().toString().trim().length() != 10){
                    showErrorMessage("Please valid phone number to call back");
                }
                else if(etComments.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter comments");
                }
                else {
                    postCommentsForSupport();
                }
            }
        });
    }

    /**
     * This method is used to post comments and return back
     */
    private void postCommentsForSupport() {
        finish();
    }
}
