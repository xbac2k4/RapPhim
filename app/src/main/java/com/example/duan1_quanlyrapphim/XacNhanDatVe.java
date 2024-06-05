package com.example.duan1_quanlyrapphim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_quanlyrapphim.adapter.AdapterGheDaChon;
import com.example.duan1_quanlyrapphim.adapter.AdapterKhungGio;
import com.example.duan1_quanlyrapphim.adapter.adapterNgayChieu;
import com.example.duan1_quanlyrapphim.adapter.adapterSoGhe;
import com.example.duan1_quanlyrapphim.dao.DAOLichChieu;
import com.example.duan1_quanlyrapphim.dao.DaoGheNgoi;
import com.example.duan1_quanlyrapphim.dao.DaoVe;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;
import com.example.duan1_quanlyrapphim.model.ChiTietVe;
import com.example.duan1_quanlyrapphim.model.LichChieu;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.example.duan1_quanlyrapphim.model.Ve;
import com.example.duan1_quanlyrapphim.model.soGhe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class XacNhanDatVe extends AppCompatActivity implements com.example.duan1_quanlyrapphim.adapter.adapterSoGhe.DataClickListener {
    Toolbar toolbar;
    ArrayList<soGhe> listSoGhe = new ArrayList<>();
    ArrayList<LichChieu> listLichChieu = new ArrayList<>();
    ArrayList<LichChieu> listKhungGio= new ArrayList<>();
    adapterSoGhe adapterSoGhe;
    adapterNgayChieu adapterNgayChieu;
    AdapterKhungGio adapterKhungGio;
    RecyclerView rcvSoGhe, rcvNgayChieu, rcvKhungGio;
    DAOLichChieu daoLichChieu;
    daoPhim daoPhim;
    DaoGheNgoi daoGheNgoi;
    DaoVe daoVe;
    TextView tvTenPhim, tvGiaVe;
    String maPhim, maTk;
    String Ngay, MaLichChieu;
    ArrayList<soGhe> listGheChon = new ArrayList<>();
    Spinner spnThanhToan;
    Button btnThanhToan;
    int checkThanhToan = 1;
    android.icu.text.SimpleDateFormat  sdf = new SimpleDateFormat("yyyy/MM/dd");
    int ngay, thang, nam;
    daoTaiKhoan daoTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_ve);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvGiaVe = findViewById(R.id.tvGiaVe);
        daoVe = new DaoVe(this);
        maTk = getIntent().getStringExtra("matk");
        //
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Xác Nhận Đặt Vé");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //
        daoTaiKhoan = new daoTaiKhoan(this);
        //
        daoLichChieu = new DAOLichChieu(this);
        daoPhim = new daoPhim(this);
        daoGheNgoi = new DaoGheNgoi(this);
        maPhim = getIntent().getStringExtra("maPhim");
        //
        listSoGhe = daoGheNgoi.selectAll("");
        rcvSoGhe = findViewById(R.id.rcvGheNgoi);
        adapterSoGhe = new adapterSoGhe(this, listSoGhe, this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        rcvSoGhe.setLayoutManager(gridLayoutManager);
        rcvSoGhe.setAdapter(adapterSoGhe);
        //
        rcvNgayChieu = findViewById(R.id.rcvNgayChieu);
        listLichChieu = daoLichChieu.selectAll(maPhim);
        tvTenPhim.setText(daoPhim.getTenPhim(maPhim));
        tvGiaVe.setText(daoPhim.getGiaVe(maPhim)+" VNĐ/Vé");
        adapterNgayChieu = new adapterNgayChieu(this, listLichChieu, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvNgayChieu.setLayoutManager(linearLayoutManager);
        rcvNgayChieu.setAdapter(adapterNgayChieu);
        //
        rcvKhungGio = findViewById(R.id.rcvKhungGio);
        listKhungGio = daoLichChieu.KhungGio(maPhim, "");
        adapterKhungGio = new AdapterKhungGio(this, listKhungGio, this, 0);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvKhungGio.setLayoutManager(linearLayoutManager1);
        rcvKhungGio.setAdapter(adapterKhungGio);
        //
        findViewById(R.id.btnDatVe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGheChon.size() > 0) {
                    OpenDialog_XacNhanDatVe(maPhim);
                } else {
                    Toast.makeText(XacNhanDatVe.this, "Vui lòng chọn lịch và ghế xem phim", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //
        spnThanhToan = findViewById(R.id.spnThanhToan);
        ArrayList<String> thanhToanArr = new ArrayList<>();
        thanhToanArr.add("Thanh Toán Online");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, thanhToanArr);
        spnThanhToan.setAdapter(adapter);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGheChon.size() > 0) {
                    OpenDialog_ThanhToan();
                } else {
                    Toast.makeText(XacNhanDatVe.this, "Vui lòng chọn vị trí để thanh toán", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void getKhungGio(String maPhim ,String ngay) {
        listKhungGio.clear();
        listKhungGio.addAll(daoLichChieu.KhungGio( maPhim, ngay));
        Ngay = ngay;
        adapterKhungGio = new AdapterKhungGio(this, listKhungGio, this, 0);
        rcvKhungGio.setAdapter(adapterKhungGio);
    }
    public void getSoGhe(String maLichChieu) {
        listSoGhe.clear();
        listSoGhe.addAll(daoGheNgoi.selectAll(maLichChieu));
        MaLichChieu = maLichChieu;
        adapterSoGhe.notifyDataSetChanged();
    }
    public void OpenDialog_XacNhanDatVe(String maPhim) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_xacnhandatve, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //
        TextView txtTenPhim = view.findViewById(R.id.txtTenPhim);
        TextView txtNgayChieu = view.findViewById(R.id.txtKhoiChieu);
        TextView txtPhong = view.findViewById(R.id.txtPhong);
        TextView txtGioChieu= view.findViewById(R.id.txtGioChieu);
//        TextView txtGhe = view.findViewById(R.id.txtGhe);
        TextView txtThanhToan = view.findViewById(R.id.txtThanhToan);
        TextView txtTongTien = view.findViewById(R.id.txtTongTien);

        RecyclerView txtGhe = view.findViewById(R.id.txtGhe);

        txtTenPhim.setText(daoPhim.getTenPhim(maPhim));
        txtNgayChieu.setText(Ngay);
        txtPhong.setText(daoLichChieu.getPhong(maPhim, MaLichChieu));
        AdapterGheDaChon adapterGheDaChon = new AdapterGheDaChon(this, listGheChon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        txtGhe.setLayoutManager(linearLayoutManager);
        txtGhe.setAdapter(adapterGheDaChon);
//        Toast.makeText(this, "" + daoLichChieu.getPhong(maPhim, MaLichChieu), Toast.LENGTH_SHORT).show();
        txtGioChieu.setText(daoLichChieu.getGioChieu(maPhim, MaLichChieu));
//        Toast.makeText(this, ""+daoLichChieu.getGioChieu(maPhim, MaLichChieu), Toast.LENGTH_SHORT).show();
//        txtGhe.setText(String.valueOf(daoGheNgoi.gheDaChon(MaLichChieu, 2)));
//        Toast.makeText(this, String.valueOf(daoGheNgoi.gheDaChon(MaLichChieu, 2)), Toast.LENGTH_SHORT).show();
        txtThanhToan.setText("Thanh toán online");
        txtTongTien.setText(String.valueOf(Integer.valueOf(daoPhim.getGiaVe(maPhim)) * listGheChon.size()));
        Calendar calendar = Calendar.getInstance();
        nam = calendar.get(Calendar.YEAR);
        thang = calendar.get(Calendar.MONTH);
        ngay = calendar.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
        String ngayMua = sdf.format(gregorianCalendar.getTime());
        view.findViewById(R.id.btnXacNhan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                int maVe;
                do {
                    maVe = new Random().nextInt(999999999);
                } while (daoVe.checkMaVe(String.valueOf(maVe)) == false);
//                Toast.makeText(XacNhanDatVe.this, "Mã vé: " + maVe, Toast.LENGTH_SHORT).show();

                if (daoVe.insertVe(new Ve( maVe, Integer.valueOf(maTk), Integer.valueOf(maPhim),checkThanhToan))) {
                    for (int i = 0; i<listGheChon.size(); i++) {
                        int maChiTietVe;
                        do {
                            maChiTietVe = new Random().nextInt(899999999)+100000000;
                        } while (daoVe.checkMaCT(String.valueOf(maChiTietVe)) == false);
                        if (daoVe.insertChiTietVe(new ChiTietVe( maChiTietVe, daoPhim.getTenPhim(maPhim), Integer.valueOf(daoPhim.getGiaVe(maPhim)), Ngay, daoLichChieu.getPhong(maPhim, MaLichChieu), daoLichChieu.getGioChieu(maPhim, MaLichChieu), listGheChon.get(i).getSoGhe(), checkThanhToan, ngayMua, maVe, Integer.valueOf(MaLichChieu), listGheChon.get(i).getMaGhe()))) {
//                            Toast.makeText(XacNhanDatVe.this, "Lần: " + i, Toast.LENGTH_SHORT).show();
                            if (daoGheNgoi.UpdateTT(listGheChon.get(i), 0) > 0) {
                                check = true;
                            }
                        }
                    }
                }
                if (check) {
                    dialog.dismiss();
                    Intent intent = new Intent(XacNhanDatVe.this, TrangChu_User.class);
                    intent.putExtra("matk", maTk);
                    startActivity(intent);
                    Toast.makeText(XacNhanDatVe.this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    Toast.makeText(XacNhanDatVe.this, "Đặt vé thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listGheChon.clear();
                if (checkThanhToan == 0) {
                    daoTaiKhoan.UpdateSoDu(maTk, daoTaiKhoan.getSoDu(maTk) + Integer.valueOf(daoPhim.getGiaVe(maPhim)) * listGheChon.size());
                }
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onDataClick(ArrayList<soGhe> listGheDaChon) {
        listGheChon = listGheDaChon;
    }
    public void OpenDialog_ThanhToan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("WARNING");
        builder.setMessage("Bạn có muốn thanh toán không?\nGiá vé: " + Integer.valueOf(daoPhim.getGiaVe(maPhim)) * listGheChon.size() + " VNĐ");
        builder.setPositiveButton("Thanh Toán", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                if (daoTaiKhoan.getSoDu(maTk) == 0) {
//                    Toast.makeText(XacNhanDatVe.this, "Số dư tài khoản của bạn đã hết", Toast.LENGTH_SHORT).show();
//                } else if (daoTaiKhoan.getSoDu(maTk) < Integer.valueOf(daoPhim.getGiaVe(maPhim)) * listGheChon.size()) {
//                    Toast.makeText(XacNhanDatVe.this, "Số dư tài khoản không đủ", Toast.LENGTH_SHORT).show();
//                } else {
                    checkThanhToan = 0;
                    daoTaiKhoan.UpdateSoDu(maTk, daoTaiKhoan.getSoDu(maTk) - Integer.valueOf(daoPhim.getGiaVe(maPhim)) * listGheChon.size());
                    Toast.makeText(XacNhanDatVe.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                    OpenDialog_XacNhanDatVe(maPhim);
//                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}