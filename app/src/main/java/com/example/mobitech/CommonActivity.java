package com.example.mobitech;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class CommonActivity extends AppCompatActivity {

    protected boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnectionAvailable = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if (activeNetworkInfo != null) {
                isNetworkConnectionAvailable = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isNetworkConnectionAvailable;
    }
    protected void showErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    protected boolean isValidEmail(String email) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    protected void saveObject(String fileName, Object object) {
        try {
//            String path = Environment.getDownloadCacheDirectory()+"/"+getString(R.string.app_name)+"/";
            FileOutputStream fileOut = openFileOutput(fileName, MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            System.out.println("The Object is saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CommonActivity", e.getMessage());
        }
    }

    protected Object getObject(String fileName) {
        Object object = null;
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("CommonActivity", e.getMessage());
        }
        return object;
    }
}
