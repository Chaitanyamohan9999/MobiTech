package com.example.mobitech;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobitech.models.ProductDo;
import com.google.firebase.firestore.FirebaseFirestore;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This is a adapter class for products list and recycler view
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ItemHolder> {

    private Context context;
    private ArrayList<ProductDo> productDos;
    private CartChangeListener cartChangeListener;
    FirebaseFirestore firebaseFirestore;

    public ProductListAdapter(@NonNull Context context, @NonNull ArrayList<ProductDo> productDos, CartChangeListener cartChangeListener) {
        this.context = context;
        this.productDos = productDos;
        this.cartChangeListener = cartChangeListener;
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
    private void updateQuantity(String quantity, int position){
        //String docId = productDos.get(position).productId;
        //firebaseFirestore.collection("Mobiles").document(docId).set(productDos.get(position));
    }
    private void loadCartProducts(){
        ArrayList<ProductDo> cartProducts = LoadProducts.getCartProducts();
        if(cartProducts != null && cartProducts.size()>0){
            for (int i=0; i<productDos.size(); i++){
                for (int j=0; j<cartProducts.size(); j++) {
                    if(productDos.get(i).productId.equalsIgnoreCase(cartProducts.get(j).productId)){
                        productDos.get(i).isAddedToCart = true;
                        productDos.get(i).quantity = cartProducts.get(j).quantity;
                    }
                }
            }
        }
    }
    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return Itemholder instance
     */
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item_cell, parent, false);
        return new ItemHolder(view);
    }

    /**
     *Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {

        holder.tvItemName.setText(productDos.get(position).productName);
        holder.tvItemPrice.setText(String.valueOf(productDos.get(position).price));
        holder.tvItemDescription.setText(String.valueOf(productDos.get(position).description));
        Picasso.get().load(productDos.get(position).image).into(holder.ivItemImage);
        holder.cbAddCart.setChecked(productDos.get(position).isAddedToCart);
        holder.tvQty.setText(""+productDos.get(position).quantity);
//        if (holder.cbAddCart.isChecked()){
//            if(!LoadProducts.cartProductDos.contains(productDos.get(position))) {
//                LoadProducts.cartProductDos.add(productDos.get(position));
//            }
//        }
//        else {
//            if(LoadProducts.cartProductDos.contains(productDos.get(position))){
//                LoadProducts.cartProductDos.remove(productDos.get(position));
//            }
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewItemDialog(productDos.get(position));
            }
        });
        holder.cbAddCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int qty = Integer.parseInt(holder.tvQty.getText().toString());
                if (holder.cbAddCart.isChecked()){
                    if(qty == 0){
                        holder.tvQty.setText("1");
                    }
                    productDos.get(position).quantity =holder.tvQty.getText().toString();
                    updateQuantity(productDos.get(position).quantity,position);
                    productDos.get(position).isAddedToCart = b;
                    LoadProducts.cartProductDos.remove(productDos.get(position));
                    LoadProducts.cartProductDos.add(productDos.get(position));
                }
                else {
                    holder.tvQty.setText("0");
                    productDos.get(position).quantity = holder.tvQty.getText().toString();
                    updateQuantity(productDos.get(position).quantity,position);
                    productDos.get(position).isAddedToCart = b;
                    LoadProducts.cartProductDos.remove(productDos.get(position));
                }
                cartChangeListener.cartChange();
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.tvQty.getText().toString().equalsIgnoreCase("")){
                    int qty = Integer.parseInt(holder.tvQty.getText().toString());
                    if(qty > 1){
                        qty = qty - 1;
                        holder.tvQty.setText(""+qty);
                        productDos.get(position).quantity = ""+qty;
                        updateQuantity(productDos.get(position).quantity,position);
                        productDos.get(position).isAddedToCart = true;
                        holder.cbAddCart.setChecked(true);
                        LoadProducts.cartProductDos.remove(productDos.get(position));
                        LoadProducts.cartProductDos.add(productDos.get(position));
                    }
                    else {
                        productDos.get(position).quantity = ""+0;
                        updateQuantity(productDos.get(position).quantity,position);
                        productDos.get(position).isAddedToCart = false;
                        holder.tvQty.setText("0");
                        holder.cbAddCart.setChecked(false);
                        LoadProducts.cartProductDos.remove(productDos.get(position));
                    }
                }
                cartChangeListener.cartChange();
            }
        });
        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.tvQty.getText().toString().equalsIgnoreCase("")){
                    int qty = Integer.parseInt(holder.tvQty.getText().toString());
                    if(qty < 100) {
                        qty = qty + 1;
                        holder.tvQty.setText("" + qty);

                        productDos.get(position).quantity = ""+qty;
                        updateQuantity(productDos.get(position).quantity,position);
                        productDos.get(position).isAddedToCart = true;
                        holder.cbAddCart.setChecked(true);
                        LoadProducts.cartProductDos.remove(productDos.get(position));
                        LoadProducts.cartProductDos.add(productDos.get(position));
                    }
                    else {
                        Toast.makeText(context, "You cannot add more than "+qty+" products", Toast.LENGTH_SHORT).show();
                    }
                }
                cartChangeListener.cartChange();
            }
        });

    }

    /**
     * This method returns the count of products
     * @return No of products
     */
    @Override
    public int getItemCount() {
        return productDos != null?productDos.size() : 0;
    }

    /**
     * This method returns items added to cart
     * @return Cart items
     */
    public ArrayList<ProductDo> getSelectedProducts() {

        return LoadProducts.cartProductDos;
    }

    /**
     * This class holds the references of a product need to be set to recyclerview
     */
    public static class ItemHolder extends RecyclerView.ViewHolder{
        private ImageView ivItemImage;
        private TextView tvItemName, tvItemDescription, tvItemPrice, tvMinus, tvQty, tvPlus;
        private CheckBox cbAddCart;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ivItemImage                 = itemView.findViewById(R.id.ivItemImage);
            tvItemName                  = itemView.findViewById(R.id.tvItemName);
            tvItemDescription           = itemView.findViewById(R.id.tvItemDescription);
            tvItemPrice                 = itemView.findViewById(R.id.tvItemPrice);
            tvMinus                     = itemView.findViewById(R.id.tvMinus);
            tvQty                       = itemView.findViewById(R.id.tvQty);
            tvPlus                      = itemView.findViewById(R.id.tvPlus);
            cbAddCart                   = itemView.findViewById(R.id.cbAddCart);
        }
    }

    /**
     * This method is used to let user select quantity to be added to caert
     * @param customList
     */
    public void viewItemDialog(final CustomList customList){
        Log.e("MainActivity","viewitemdaialog function");
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.addtocart_dialog);
        dialog.setCancelable(false);
        final TextView noOfItems = (TextView)dialog.findViewById(R.id.tv_itemcount);
        final ImageView itemImageView=(ImageView)dialog.findViewById(R.id.dialog_image);
        TextView priceTView=(TextView)dialog.findViewById(R.id.tv_price);
        TextView nameTView = (TextView)dialog.findViewById(R.id.tv_itemname);
        Button plusBtn = (Button)dialog.findViewById(R.id.plus_btn);
        Button addItemBtn = (Button)dialog.findViewById(R.id.addToCart_btn);
        Button cancelBtn = (Button)dialog.findViewById(R.id.cancel_btn);
        nameTView.setText(customList.name);
        itemImageView.setImageBitmap(customList.image);
        priceTView.setText("Price :"+String.valueOf(customList.price));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ic= noOfItems.getText().toString();
                int itemCount=Integer.parseInt(ic);
                noOfItems.setText(String.valueOf(++itemCount));
            }
        });
        Button minusBtn = (Button)dialog.findViewById(R.id.minus_btn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ic= noOfItems.getText().toString();
                int itemCount=Integer.parseInt(ic);
                if(itemCount>0) {
                    noOfItems.setText(String.valueOf(--itemCount));
                }
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ic= noOfItems.getText().toString();
                int itemQuantity=Integer.parseInt(ic);
                if(itemQuantity>0){
//                    customList.add(new OrderList(selectedItemName,selectedItemprice,itemQuantity,selectedItemImage));
                    dialog.dismiss();
                }else{
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
