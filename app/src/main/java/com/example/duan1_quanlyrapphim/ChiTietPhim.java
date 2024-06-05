package com.example.duan1_quanlyrapphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietPhim extends AppCompatActivity {
    ImageView imgAnhPhim;
    TextView tvTenPhim, tvTenTheLoai, tvKhoiChieu, tvGiaVe, tvMoTa;
    Button btnDatVe;
    ArrayList<Phim> list = new ArrayList<>();
    daoPhim daoPhim;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);
        imgAnhPhim = findViewById(R.id.imgAnhPhim);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTenTheLoai = findViewById(R.id.tvTenTheLoai);
        tvKhoiChieu = findViewById(R.id.tvKhoiChieu);
        tvGiaVe = findViewById(R.id.tvGiaVe);
        tvMoTa = findViewById(R.id.tvMoTa);
        btnDatVe = findViewById(R.id.btnDatVe);
        //
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi Tiết Phim");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //
        String matk = getIntent().getStringExtra("matk");
        String maPhim = getIntent().getStringExtra("maPhim");
        daoPhim = new daoPhim(this);
        list = daoPhim.selectAllPhim(maPhim);

        Picasso.get().load(list.get(0).getImgPhim()).into(imgAnhPhim);
        tvTenPhim.setText(list.get(0).getTenPhim());
        tvTenTheLoai.setText(list.get(0).getTenTheLoai());
        tvKhoiChieu.setText(list.get(0).getKhoiChieu());
        tvGiaVe.setText(list.get(0).getGiaVe() + " VNĐ");
        tvMoTa.setText(list.get(0).getMoTa());
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietPhim.this, XacNhanDatVe.class);
                intent.putExtra("matk", matk);
                intent.putExtra("maPhim", maPhim);
                startActivity(intent);
            }
        });
    }
}