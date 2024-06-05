package com.example.duan1_quanlyrapphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duan1_quanlyrapphim.adapter.adapterPhim_user;
import com.example.duan1_quanlyrapphim.adapter.adapterTimKiemPhim_user;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.model.Phim;

import java.util.ArrayList;

public class TimKiemTheLoai extends AppCompatActivity {
    ArrayList<Phim> listPhim = new ArrayList<>();
    daoPhim daoPhim;
    adapterTimKiemPhim_user adapterPhim;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_the_loai);
        //
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //
        daoPhim = new daoPhim(this);
        String maTheLoai = getIntent().getExtras().getString("theLoai");
        String matk = getIntent().getStringExtra("matk");
        getSupportActionBar().setTitle("Thể Loại " + daoPhim.getTenTheLoai(maTheLoai));
        listPhim = daoPhim.selectPhimTheoTheLoai(maTheLoai);
        recyclerView = findViewById(R.id.rcvTheLoai);
        adapterPhim = new adapterTimKiemPhim_user(this, listPhim, matk);
//        GridLayoutManager layoutManagerPhim = new GridLayoutManager(this, 3);
        LinearLayoutManager layoutManagerPhim = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerPhim);
        recyclerView.setAdapter(adapterPhim);
    }
}