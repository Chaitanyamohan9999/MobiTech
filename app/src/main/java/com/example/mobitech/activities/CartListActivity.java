package com.example.mobitech.activities;

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


import com.example.mobitech.R;
import com.example.mobitech.models.ProductDo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This class deals with products added to cart
 */
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
        final ArrayList<ProductDo> cartProductDos = LoadProducts.cartProductDos;
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

    /**
     * This method is used to bind the backend data with front end product
     */
    private void bindData() {
        try {
            if (LoadProducts.cartProductDos!=null && LoadProducts.cartProductDos.size()>0){
                double totalPrice = 0;
                for (int i=0;i<LoadProducts.cartProductDos.size();i++){
                   // totalPrice = totalPrice + (LoadProducts.cartProductDos.get(i).quantity * LoadProducts.cartProductDos.get(i).price);
                    int price = Integer.parseInt(LoadProducts.cartProductDos.get(i).price);
                    int quantity = Integer.parseInt(LoadProducts.cartProductDos.get(i).quantity);
                    totalPrice = totalPrice + (quantity * price);

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

        /**
         * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
         * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
         * @param viewType The view type of the new View.
         * @return
         */
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

        /**
         *Called by RecyclerView to display the data at the specified position.
         * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position
         * @param position The position of the item within the adapter's data set.
         */
        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, final int position) {

            holder.tvItemName.setText(cartProductDos.get(position).productName);
            int price = Integer.parseInt(cartProductDos.get(position).price);
            int quantity = Integer.parseInt(cartProductDos.get(position).quantity);
            holder.tvItemPrice.setText(String.valueOf(quantity * price));
            holder.tvItemDescription.setText(String.valueOf(cartProductDos.get(position).description));
            holder.tvQty.setText(String.valueOf(cartProductDos.get(position).quantity));
//            if(cartProductDos.get(position).image != 0){
//                holder.ivItemImage.setImageResource(cartProductDos.get(position).image);
//            }
            Picasso.get().load(cartProductDos.get(position).image).into(holder.ivItemImage);

            holder.tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadProducts.cartProductDos.remove(cartProductDos.get(position));
                    refreshAdapter(LoadProducts.cartProductDos);
                }
            });
        }

        /**
         * This method returns the count of products in cart
         * @return No of products in cart
         */
        @Override
        public int getItemCount() {
            return cartProductDos != null ? cartProductDos.size() : 0;
        }
    }

    /**
     * This class holds the references of a cart product need to be set to recyclerview
     */
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

