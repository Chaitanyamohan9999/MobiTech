package com.example.mobitech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobitech.*;
import com.example.mobitech.utils.LoadProducts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * This class deals with the Dashboard screen
 */
public class DashboardActivity extends AppCompatActivity {

    private LinearLayout llMobiles, llLaptops, llTvs, llRefrigerators, llMusic, llGaming;
    private TextView tvItemCount;
    private ImageView ivProfile;
    private FloatingActionButton fabSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        tvItemCount     = (TextView)findViewById(R.id.tvItemCount);
        ivProfile       = findViewById(R.id.ivProfile);
        fabSupport      = findViewById(R.id.fabSupport);
        llMobiles                   = findViewById(R.id.llMobiles);
        llLaptops                   = findViewById(R.id.llLaptops);
        llTvs                       = findViewById(R.id.llTvs);
        llRefrigerators                      = findViewById(R.id.llRefrigerators);
        llMusic                     = findViewById(R.id.llMusic);
        llGaming                    = findViewById(R.id.llGaming);
        tvItemCount.setText("0");

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        fabSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });
        llMobiles.setOnClickListener(onClickListener);
        llLaptops.setOnClickListener(onClickListener);
        llTvs.setOnClickListener(onClickListener);
        llRefrigerators.setOnClickListener(onClickListener);
        llMusic.setOnClickListener(onClickListener);
        llGaming.setOnClickListener(onClickListener);
    }

    /**
     * This method is invoked on click of any view
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DashboardActivity.this, ProductListActivity.class);
            switch (view.getId()){
                case R.id.llMobiles:
                    intent.putExtra("Category", "Mobiles");
                    break;
                case R.id.llLaptops:
                    intent.putExtra("Category", "Laptops");
                    break;
                case R.id.llTvs:
                    intent.putExtra("Category", "TVs");
                    break;
                case R.id.llRefrigerators:
                    intent.putExtra("Category", "Refrigerators");
                    break;
                case R.id.llMusic:
                    intent.putExtra("Category", "Music");
                    break;
                case R.id.llGaming:
                    intent.putExtra("Category", "Gaming");
                    break;
            }
            startActivity(intent);
        }
    };

    /**
     * This method is invoked when activity resumes from background to foreground.
     */
    @Override
    protected void onResume() {
        super.onResume();
        tvItemCount.setText(""+ LoadProducts.cartProductDos.size());
    }
}
