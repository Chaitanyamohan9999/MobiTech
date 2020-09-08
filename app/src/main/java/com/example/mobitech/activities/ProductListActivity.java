package com.example.mobitech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.shopping.electronics.*;
import com.shopping.electronics.ProductListAdapter;
import com.shopping.electronics.models.ProductDo;
import com.shopping.electronics.utils.CartChangeListener;
import com.shopping.electronics.utils.LoadProducts;

import java.util.ArrayList;

/**
 * This class deals with available products and their details
 */
public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rvItems;
    private String selectedCategory = "";
    private TextView tvCategory, tvItemCount;
    private ImageView ivBack;
    private Button btnCart;
    private ArrayList<ProductDo> productDos;
    private ProductListAdapter itemListAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);
        if(getIntent().hasExtra("Category")){
            selectedCategory = getIntent().getStringExtra("Category");
        }
        rvItems         = findViewById(R.id.rvItems);
        tvCategory      = findViewById(R.id.tvCategory);
        tvItemCount     = findViewById(R.id.tvItemCount);
        ivBack          = findViewById(R.id.ivBack);
        btnCart         = findViewById(R.id.btnCart);
        firebaseFirestore = FirebaseFirestore.getInstance();
        rvItems.setLayoutManager(new LinearLayoutManager(ProductListActivity.this, LinearLayoutManager.VERTICAL, false));
        tvCategory.setText(selectedCategory);
        tvItemCount.setText("0");
//        firebaseFirestore.collection(selectedCategory).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful())
//                {
//                    ArrayList<ProductDo> productDos = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
//                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
//                        productDo.productId = documentSnapshot.getId();
//                        productDos.add(productDo);
//                    }
//                    itemListAdapter = new ProductListAdapter(ProductListActivity.this, productDos, cartChangeListener);
//                    rvItems.setAdapter(itemListAdapter);
//                }
//            }
//        });
        //productDos = LoadProducts.loadProducts(selectedCategory);
        if (selectedCategory.equals("Mobiles"))
            productDos = LoadProducts.getMobilesList();
        else if (selectedCategory.equals("Laptops"))
            productDos = LoadProducts.getLaptopsList();
        else if (selectedCategory.equals("TVs"))
            productDos = LoadProducts.getTvsList();
        else if (selectedCategory.equals("Music"))
            productDos = LoadProducts.getMusicList();
        else if (selectedCategory.equals("Refrigerators"))
            productDos = LoadProducts.getRefrigeratorsList();
        else if (selectedCategory.equals("Gaming"))
            productDos = LoadProducts.getGamingList();

        itemListAdapter = new ProductListAdapter(ProductListActivity.this, productDos, cartChangeListener);
        rvItems.setAdapter(itemListAdapter);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemListAdapter!=null){
                    ArrayList<ProductDo> productDos = itemListAdapter.getSelectedProducts();
                    Log.e("Cart Products ", "Size : "+productDos.size());
                    if(productDos!=null && productDos.size()>0) {
                        Intent intent = new Intent(ProductListActivity.this, CartListActivity.class);
                        intent.putExtra("CartItems", productDos);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ProductListActivity.this, "Your cart is empty, please add products to cart", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /**
     * When activity start getting visible to user then this method will be called.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called when activity is no longer visible to the user.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * This is an indicator that the activity became active and ready to receive input. It is on top of an activity stack and visible to user.
     */
    @Override
    protected void onResume() {
        super.onResume();
        tvItemCount.setText(""+LoadProducts.cartProductDos.size());
    }

    /**
     * Implementing an interface {@link CartChangeListener}
     */
    private CartChangeListener cartChangeListener = new CartChangeListener() {
        @Override
        public void cartChange() {
            tvItemCount.setText(""+LoadProducts.cartProductDos.size());
        }
    };
}
