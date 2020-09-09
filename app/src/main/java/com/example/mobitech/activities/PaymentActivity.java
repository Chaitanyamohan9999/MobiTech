package com.example.mobitech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mobitech.*;
import com.example.mobitech.models.ProductDo;
import com.example.mobitech.models.UserDo;
import com.example.mobitech.utils.AppConstants;
import com.example.mobitech.utils.LoadProducts;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * This class deals with the payment related details to proceed to place an order
 */
public class PaymentActivity extends CommonActivity {

    private ImageView ivBack;
    private Button btnProceed;
    private EditText etName, etCardNumber, etExpiryDate, etCVV;
    FirebaseFirestore firebaseFirestore;
    private UserDo userDo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        ivBack          = findViewById(R.id.ivBack);
        btnProceed      = findViewById(R.id.btnProceed);
        etName          = findViewById(R.id.etName);
        etCardNumber    = findViewById(R.id.etCardNumber);
        etExpiryDate    = findViewById(R.id.etExpiryDate);
        etCVV           = findViewById(R.id.etCVV);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String errorMessage = validateCard();
                if (!errorMessage.equalsIgnoreCase("")){
                    Toast.makeText(PaymentActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }
                else {
                    userDo = (UserDo) getObject(AppConstants.userFile);
                    String id = userDo.userId;
                    for(ProductDo productDo : LoadProducts.cartProductDos) {
                        //firebaseFirestore.collection("Orders").document(id).collection("order")
                        firebaseFirestore.collection("Orders")
                                .add(productDo)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(PaymentActivity.this, "Your order has been placed successfully!", Toast.LENGTH_LONG).show();
                                LoadProducts.cartProductDos.clear();
                                Intent intent = new Intent(PaymentActivity.this, DashboardActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * This method is used to validate card details provided by user
     * @return string value as error message if the card is not valid else empty string is returned if card is valid.
     */
    private String validateCard() {
        String errorMsg = "";
        if (etName.getText().toString().trim().isEmpty()) {
            errorMsg = "Please Enter Name";
        }
        else if (etCardNumber.getText().toString().trim().isEmpty()) {
            errorMsg = "Please Enter CardNumber";
        }
        else if (etExpiryDate.getText().toString().trim().isEmpty()) {
            errorMsg = "Please Enter Date";
        }
        else if (etCVV.getText().toString().trim().isEmpty()) {
            errorMsg = "Please Enter CVV";
        }
        return errorMsg;
    }
}
