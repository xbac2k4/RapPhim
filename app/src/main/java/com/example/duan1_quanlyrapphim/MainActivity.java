package com.example.duan1_quanlyrapphim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img_mhc1 = findViewById(R.id.img_mhc1);
        TextView tv_mhc = findViewById(R.id.tv_mhc);
        LinearLayout layout_mhc2 = findViewById(R.id.layout_mhc2);
        ImageView img_mhc2 = findViewById(R.id.img_mhc2);
        TextView tv_mhc1 = findViewById(R.id.tv_mhc1);
        TextView tv_mhc2 = findViewById(R.id.tv_mhc2);
        LinearLayout layout_mhc3 = findViewById(R.id.layout_mhc3);

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.animation1);
        img_mhc1.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.animation2);
        tv_mhc.startAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.animation3);
        layout_mhc2.startAnimation(animation3);

        Animation animation4 = AnimationUtils.loadAnimation(this,R.anim.animation4);
        img_mhc2.startAnimation(animation4);
        tv_mhc1.startAnimation(animation4);
        tv_mhc2.startAnimation(animation4);

        Animation animation5 = AnimationUtils.loadAnimation(this,R.anim.animation5);
        layout_mhc3.startAnimation(animation5);

        img_mhc1.setVisibility(View.INVISIBLE);
        tv_mhc.setVisibility(View.INVISIBLE);

        findViewById(R.id.btn_login).setOnClickListener(v ->{
            Intent intent = new Intent(this, Dangnhap.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        });

        findViewById(R.id.btn_signup).setOnClickListener(v ->{
            Intent intent = new Intent(this, Dangky.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        });
    }
}