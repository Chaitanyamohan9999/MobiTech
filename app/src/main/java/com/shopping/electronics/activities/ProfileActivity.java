package com.shopping.electronics.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.electronics.utils.AppConstants;
import com.shopping.electronics.utils.CircleImageView;
import com.shopping.electronics.models.UserDo;
import com.shopping.electronics.*;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * This class deals with profile related activities
 */
public class ProfileActivity extends CommonActivity {

    private static final String TAG = "ProfileActivity";
    private CircleImageView civProfile;
    private EditText etName, etEmail, etContactNumber, etCity, etState, etCountry;
    private ImageView ivClose, ivEdit;
    private TextView tvUpdate;
    private Uri imageUri;
    private UserDo userDo;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.profile_layout);
        initialiseControls();
        etName.setEnabled(false);
        etEmail.setEnabled(false);
        etContactNumber.setEnabled(false);
        etCity.setEnabled(false);
        etState.setEnabled(false);
        etCountry.setEnabled(false);
        tvUpdate.setVisibility(View.GONE);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bindData();
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setEnabled(true);
                etEmail.setEnabled(true);
                etContactNumber.setEnabled(true);
                etCity.setEnabled(true);
                etState.setEnabled(true);
                etCountry.setEnabled(true);
                etName.setBackgroundResource(R.drawable.edit_text_bg);
                etEmail.setBackgroundResource(R.drawable.edit_text_bg);
                etContactNumber.setBackgroundResource(R.drawable.edit_text_bg);
                etCity.setBackgroundResource(R.drawable.edit_text_bg);
                etState.setBackgroundResource(R.drawable.edit_text_bg);
                etCountry.setBackgroundResource(R.drawable.edit_text_bg);
                tvUpdate.setVisibility(View.VISIBLE);
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter name");
                }
                else if(etEmail.getText().toString().equalsIgnoreCase("")){
                    showErrorMessage("Please enter email");
                }
                else if(!isValidEmail(etEmail.getText().toString().trim())){
                    showErrorMessage("Please enter valid email");
                }
                else if(etContactNumber.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please enter contact number");
                }
                else if(etCity.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please enter city");
                }
                else if(etState.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please enter province");
                }
                else if(etCountry.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorMessage("Please enter country");
                }
                else {
                    userDo.name   = etName.getText().toString().trim();
                    userDo.phone  = etContactNumber.getText().toString().trim();
                    userDo.city   = etCity.getText().toString().trim();
                    userDo.state  = etState.getText().toString().trim();
                    userDo.country  = etCountry.getText().toString().trim();
                    userDo.country  = etCountry.getText().toString().trim();
                    saveObject(AppConstants.userFile, userDo);
                    finish();
                }
            }
        });
        civProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if(!hasPermissions(ProfileActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSIONS, 1210);
                }
                else {
                    selectProfilePic(110);
                }
            }
        });
    }

    private void initialiseControls(){
        civProfile              = findViewById(R.id.civProfile);
        ivClose                 = findViewById(R.id.ivClose);
        ivEdit                  = findViewById(R.id.ivEdit);
        etName                  = findViewById(R.id.etName);
        etEmail                 = findViewById(R.id.etEmail);
        etContactNumber         = findViewById(R.id.etContactNumber);
        etCity                  = findViewById(R.id.etCity);
        etState                 = findViewById(R.id.etState);
        etCountry               = findViewById(R.id.etCountry);
        tvUpdate                = findViewById(R.id.tvUpdate);

    }

    /**
     * Checks for permissions if granted or denied
     * @param context the application context
     * @param permissions Permissions to be checked if granted or denied
     * @return boolean value as true if permission granted.
     */
    private boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Callback for the result from requesting permissions. This method is invoked for every call on ActivityCompat.requestPermissions(android.app.Activity, String[], int).
     * @param requestCode The request code passed in ActivityCompat.requestPermissions(android.app.Activity, String[], int)
     * @param permissions The requested permissions. Never null.
     * @param grantResults he grant results for the corresponding permissions which is either PackageManager.PERMISSION_GRANTED or PackageManager.PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == 1210 || requestCode == 1211|| requestCode == 1212) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permissions", "Granted");
                selectProfilePic(requestCode);
            }
            else {
                Log.e("Permission", "Denied");
                String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if(!hasPermissions(ProfileActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSIONS, 201);
                }
            }
        }
    }

    /**
     * This method is called to choose or take a profile picture
     * @param requestCode
     */
    private void selectProfilePic(int requestCode){
        String userId = userDo.name;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imagePath = "Profile_"+userId+"_" + System.currentTimeMillis() + ".png";
        File file = new File(Environment.getExternalStorageDirectory(), imagePath);
        imageUri = Uri.fromFile(file);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        Intent chooser = Intent.createChooser(galleryIntent, "Select an option");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
        startActivityForResult(chooser, requestCode);
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with, the resultCode it returned, and any additional data from it.
     * @param requestCode The integer request code originally supplied to startActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 110 && resultCode == RESULT_OK){
            if(data == null){// from camer
                civProfile.setImageURI(imageUri);
            }
            else {// from file storage
                imageUri = data.getData();
                civProfile.setImageURI(imageUri);
            }
        }
    }


    /**
     * This method is used to bind the backend data with front end product
     */
    private void bindData(){
        try {
            userDo = (UserDo) getObject(AppConstants.userFile);
            etName.setText(userDo.name);
            etEmail.setText(userDo.email);
            etContactNumber.setText(userDo.phone);
            etCity.setText(userDo.city);
            etState.setText(userDo.state);
            etCountry.setText(userDo.country);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
