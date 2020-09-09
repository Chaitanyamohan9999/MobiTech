package com.shopping.electronics.utils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.shopping.electronics.models.ProductDo;

import java.util.ArrayList;

/**
 * Utility class to Load products data from backend
 */
public class LoadProducts {
    private static ArrayList<ProductDo> mobilesList;
    private static ArrayList<ProductDo> laptopsList;
    private static ArrayList<ProductDo> tvsList;
    private static ArrayList<ProductDo> refrigeratorsList;
    private static ArrayList<ProductDo> musicList;
    private static ArrayList<ProductDo> gamingList;

    /**
     * getter for list of mobiles
     * @return Mobiles list
     */
    public static ArrayList<ProductDo> getMobilesList() {
        return mobilesList;
    }

    /**
     * Setter for list of mobiles
     * @param mobilesList List of mobiles
     */
    public static void setMobilesList(ArrayList<ProductDo> mobilesList) {
        LoadProducts.mobilesList = mobilesList;
    }

    /**
     * getter for list of laptops
     * @return Laptops list
     */
    public static ArrayList<ProductDo> getLaptopsList() {
        return laptopsList;
    }

    /**
     * Setter for list of laptops
     * @param laptopsList List of laptops
     */
    public static void setLaptopsList(ArrayList<ProductDo> laptopsList) {
        LoadProducts.laptopsList = laptopsList;
    }

    /**
     * getter for list of Television
     * @return List of Television
     */
    public static ArrayList<ProductDo> getTvsList() {
        return tvsList;
    }

    /**
     * Setter for list of Television
     * @param tvsList List of Television
     */
    public static void setTvsList(ArrayList<ProductDo> tvsList) {
        LoadProducts.tvsList = tvsList;
    }

    /**
     * getter for list of Refrigerator
     * @return List of Refrigerator
     */
    public static ArrayList<ProductDo> getRefrigeratorsList() {
        return refrigeratorsList;
    }

    /**
     * Setter for list of Refrigerator
     * @param refrigeratorsList List of Refrigerator
     */
    public static void setRefrigeratorsList(ArrayList<ProductDo> refrigeratorsList) {
        LoadProducts.refrigeratorsList = refrigeratorsList;
    }

    /**
     * getter for list of music
     * @return List of music
     */
    public static ArrayList<ProductDo> getMusicList() {
        return musicList;
    }

    /**
     * Setter for list of music
     * @param musicList List of music
     */
    public static void setMusicList(ArrayList<ProductDo> musicList) {
        LoadProducts.musicList = musicList;
    }

    /**
     * getter for list of gaming
     * @return List of gaming
     */
    public static ArrayList<ProductDo> getGamingList() {
        return gamingList;
    }

    /**
     * Setter for list of gaming
     * @param gamingList List of gaming
     */
    public static void setGamingList(ArrayList<ProductDo> gamingList) {
        LoadProducts.gamingList = gamingList;
    }

    public static ArrayList<ProductDo> loadProducts(String category) {
        if(category.equalsIgnoreCase("Mobiles")) {
            return getMobileProducts();
        }
        else if(category.equalsIgnoreCase("Laptops")) {
            return getLaptopProducts();
        }
        else if (category.equalsIgnoreCase("TVs")) {
            return getTvsProducts();
        }
        else if (category.equalsIgnoreCase("Refrigerators")) {
            return getRefrigeratorProducts();
        }
        else if (category.equalsIgnoreCase("Music")) {
            return getMusicProducts();
        }
        else if (category.equalsIgnoreCase("Gaming")) {
            return getGamingProducts();
        }

        return new ArrayList<>();
    }

    private static ArrayList<ProductDo> getMobileProducts() {

//        String mobIds[] = {"Mob1", "Mob2", "Mob3", "Mob4", "Mob5", "Mob6", "Mob7", "Mob8", "Mob9", "Mob10"};
//        String productNames[] = {"OnePlus 8 Pro.", "Xiaomi Note 9Pro", "Samsung M31", "Motorola 1Fusion +", "Vivo V6",
//                                "iPhone 11 Pro Max", "iPhone 11", " iPhone XR", "iPhone XS Max", "iPhone 8 Plus", "iPhone X"};
//        double prices[] = {350, 230, 270, 260, 300, 1500, 800, 700, 1100, 500, 900};
//        int images[] = {R.drawable.samsung_phone, R.drawable.samsung_phone, R.drawable.samsung_phone, R.drawable.samsung_phone,
//                        R.drawable.samsung_phone, R.drawable.iphone_xr, R.drawable.iphone_xr, R.drawable.iphone_xr,
//                        R.drawable.iphone_xr, R.drawable.iphone_xr};
//
        ArrayList<ProductDo> productDos = new ArrayList<>();
//        for (int i=0; i<10; i++){
//            ProductDo productDo = new ProductDo();
//            productDo.productId = mobIds[i];
//            productDo.productName = productNames[i];
//            productDo.productCategory = "Mobiles";
//            productDo.description = "This mobile is best Battery Life, Camera and at low price";
//            productDo.price = String.valueOf(prices[i]);
//            productDo.image = String.valueOf(images[i]);
//            productDo.isAddedToCart = false;
//            productDos.add(productDo);
//        }
        return productDos;
    }

    private static ArrayList<ProductDo> getLaptopProducts() {
        ArrayList<ProductDo> productDos = new ArrayList<>();
        // please add laptop products

        return productDos;
    }
    private static ArrayList<ProductDo> getTvsProducts() {
        ArrayList<ProductDo> productDos = new ArrayList<>();
        // please add TVs products

        return productDos;
    }
    private static ArrayList<ProductDo> getRefrigeratorProducts() {
        ArrayList<ProductDo> productDos = new ArrayList<>();
        // please add Refrigerator products

        return productDos;
    }
    private static ArrayList<ProductDo> getMusicProducts() {
        ArrayList<ProductDo> productDos = new ArrayList<>();
        // please add Music products

        return productDos;
    }
    private static ArrayList<ProductDo> getGamingProducts() {
        ArrayList<ProductDo> productDos = new ArrayList<>();
        // please add Gaming products

        return productDos;
    }

    public static ArrayList<ProductDo> cartProductDos = new ArrayList<>();
    public static ArrayList<ProductDo> getCartProducts() {

        return cartProductDos;
    }

    /**
     * Loads products and their details from backend
     */
    public static void loadData() {
        //mobile
        final ArrayList<ProductDo> productDos = new ArrayList<>();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Mobiles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.isAddedToCart = false;
                        productDo.quantity = "0";
                        productDos.add(productDo);
                    }
                setMobilesList(productDos);
            }
        });
        final ArrayList<ProductDo> productDosLaptops = new ArrayList<>();
        firebaseFirestore.collection("Laptops").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.quantity = "0";
                        productDo.isAddedToCart = false;
                        productDosLaptops.add(productDo);
                    }
                setLaptopsList(productDosLaptops);
            }
        });
        final ArrayList<ProductDo> productDosMusic = new ArrayList<>();
        firebaseFirestore.collection("Music").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.quantity = "0";
                        productDo.isAddedToCart = false;
                        productDosMusic.add(productDo);
                    }
                setMusicList(productDosMusic);
            }
        });
        final ArrayList<ProductDo> productDosTvs = new ArrayList<>();
        firebaseFirestore.collection("TVs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.quantity = "0";
                        productDo.isAddedToCart = false;
                        productDosTvs.add(productDo);
                    }
                setTvsList(productDosTvs);
            }
        });
        final ArrayList<ProductDo> productDosRefrigerators = new ArrayList<>();
        firebaseFirestore.collection("Refrigerators").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.quantity = "0";
                        productDo.isAddedToCart = false;
                        productDosRefrigerators.add(productDo);
                    }
                setRefrigeratorsList(productDosRefrigerators);
            }
        });
        final ArrayList<ProductDo> productDosGaming = new ArrayList<>();
        firebaseFirestore.collection("Gaming").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        ProductDo productDo = documentSnapshot.toObject(ProductDo.class);
                        productDo.productId = documentSnapshot.getId();
                        productDo.quantity = "0";
                        productDo.isAddedToCart = false;
                        productDosGaming.add(productDo);
                    }
                setGamingList(productDosGaming);
            }
        });
    }
}
