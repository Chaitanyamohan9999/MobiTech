package com.example.mobitech;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopping.electronics.utils.LoadProducts;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView rvCartProducts;
    private ImageView ivBack;
    private TextView tvTotalPrice, tvDeliveryFee, tvTotalAmount;
    private Button tvPlaceOrder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_layout);
        ivBack                  = findViewById(R.id.ivBack);
        tvTotalPrice            = findViewById(R.id.tvTotalPrice);
        tvTotalAmount           = findViewById(R.id.tvTotalAmount);
        tvDeliveryFee           = findViewById(R.id.tvDeliveryFee);
        tvPlaceOrder            = findViewById(R.id.tvPlaceOrder);
        rvCartProducts          = findViewById(R.id.rvCartProducts);
        rvCartProducts.setLayoutManager(new LinearLayoutManager(CartListActivity.this, LinearLayoutManager.VERTICAL, false));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ArrayList<ProductDo> cartProductDos = LoadProducts.cartProductDos;
        CartItemAdapter cartItemAdapter = new CartItemAdapter(CartListActivity.this, cartProductDos);
        rvCartProducts.setAdapter(cartItemAdapter);
        bindData();
        tvPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindData() {
        try {
            if (LoadProducts.cartProductDos!=null && LoadProducts.cartProductDos.size()>0){
                double totalPrice = 0;
                for (int i=0;i<LoadProducts.cartProductDos.size();i++){
                    totalPrice = totalPrice + (LoadProducts.cartProductDos.get(i).quantity * LoadProducts.cartProductDos.get(i).price);
                }
                tvTotalPrice.setText("$"+totalPrice);
                tvDeliveryFee.setText("$10");
                tvTotalAmount.setText("$"+(totalPrice+10));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("CartScreen", e.getMessage());
        }
    }
    private class CartItemAdapter extends RecyclerView.Adapter<CartHolder> {

        private Context context;
        private ArrayList<ProductDo> cartProductDos;

        public CartItemAdapter(Context context, ArrayList<ProductDo> cartProductDos) {
            this.context = context;
            this.cartProductDos = cartProductDos;
        }

        @NonNull
        @Override
        public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(context).inflate(R.layout.cart_item_cell, parent, false);
            return new CartHolder(convertView);
        }

        private void refreshAdapter(ArrayList<ProductDo> cartProductDos){
            this.cartProductDos = cartProductDos;
            bindData();
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, final int position) {

            holder.tvItemName.setText(cartProductDos.get(position).productName);
            holder.tvItemPrice.setText(String.valueOf(cartProductDos.get(position).quantity * cartProductDos.get(position).price));
            holder.tvItemDescription.setText(String.valueOf(cartProductDos.get(position).description));
            holder.tvQty.setText(String.valueOf(cartProductDos.get(position).quantity));
            if(cartProductDos.get(position).image != 0){
                holder.ivItemImage.setImageResource(cartProductDos.get(position).image);
            }
            holder.tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadProducts.cartProductDos.remove(cartProductDos.get(position));
                    refreshAdapter(LoadProducts.cartProductDos);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cartProductDos != null ? cartProductDos.size() : 0;
        }
    }

        public class CartHolder extends RecyclerView.ViewHolder {

            private ImageView ivItemImage;
            private TextView tvItemName, tvItemDescription, tvItemPrice, tvQty, tvRemove;
            public CartHolder(@NonNull View itemView) {
                super(itemView);
                ivItemImage                 = itemView.findViewById(R.id.ivItemImage);
                tvItemName                  = itemView.findViewById(R.id.tvItemName);
                tvItemDescription           = itemView.findViewById(R.id.tvItemDescription);
                tvItemPrice                 = itemView.findViewById(R.id.tvItemPrice);
                tvQty                       = itemView.findViewById(R.id.tvQty);
                tvRemove                    = itemView.findViewById(R.id.tvRemove);
            }
        }
    }

