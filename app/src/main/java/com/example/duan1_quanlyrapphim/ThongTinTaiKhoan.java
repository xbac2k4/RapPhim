package com.example.duan1_quanlyrapphim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;
import com.example.duan1_quanlyrapphim.fragment.fragment_taikhoan;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TaiKhoan;
import com.example.duan1_quanlyrapphim.model.TheLoai;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class ThongTinTaiKhoan extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<TaiKhoan> list = new ArrayList<>();
    daoTaiKhoan daoTaiKhoan;
    TextView tvTen, tvGioiTinh, tvNgaySinh, tvEmail, tvSDT;
    String matk;
    EditText txtTen, txtNgaySinh, txtEmail, txtSDT;
    Spinner spnGioiTinh;
    int gioiTinh;
    android.icu.text.SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
    int ngay, thang, nam;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        //
        matk = getIntent().getStringExtra("matk");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông Tin Tài Khoản");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //
        tvTen = findViewById(R.id.tvTen);
        tvGioiTinh = findViewById(R.id.tvGioiTinh);
        tvNgaySinh = findViewById(R.id.tvNgaySinh);
        tvEmail = findViewById(R.id.tvEmail);
        tvSDT = findViewById(R.id.tvSDT);
        daoTaiKhoan = new daoTaiKhoan(this);
        list = daoTaiKhoan.selectAll_MaTK(matk);
        taiKhoan = list.get(0);
        // set text
        tvTen.setText(list.get(0).getTenNguoiDung());
        if (list.get(0).getGioiTinh() == 0) {
            tvGioiTinh.setText("Nam");
        } else if (list.get(0).getGioiTinh() == 1) {
            tvGioiTinh.setText("Nữ");
        } else if (list.get(0).getGioiTinh() == 2){
            tvGioiTinh.setText("Không xác định");
        }
        if (list.get(0).getNgaySinh().isEmpty()) {
            tvNgaySinh.setText("Chưa cập nhật");
            tvNgaySinh.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvNgaySinh.setText(list.get(0).getNgaySinh());
//            tvNgaySinh.setTextColor(Color.parseColor("#FF000000"));
        }
        if (list.get(0).getSoDienThoai().isEmpty()) {
            tvSDT.setText("Chưa cập nhật");
            tvSDT.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvSDT.setText(list.get(0).getSoDienThoai());
//            tvSDT.setTextColor(Color.parseColor(" #FF000000"));
        }
        tvEmail.setText(list.get(0).getEmail());

    }
    public void OpenDialog_Update() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_update_tttk, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        txtTen = view.findViewById(R.id.txtTen);
        txtNgaySinh = view.findViewById(R.id.txtNgaySinh);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtSDT = view.findViewById(R.id.txtSDT);
        spnGioiTinh = view.findViewById(R.id.spnGioiTinh);

        ArrayList<String> GioiTinhArr = new ArrayList<>();
        GioiTinhArr.add("Không xác định");
        GioiTinhArr.add("Nam");
        GioiTinhArr.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, GioiTinhArr);
        spnGioiTinh.setAdapter(adapter);
        spnGioiTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    gioiTinh = 2;
                } else if (position == 1) {
                    gioiTinh = 0;
                } else if (position == 2) {
                    gioiTinh = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(ThongTinTaiKhoan.this, 0, date, year, month, day);
                d.show();
            }
        });
        txtTen.setText(taiKhoan.getTenNguoiDung());
        txtEmail.setText(taiKhoan.getEmail());
        txtSDT.setText(taiKhoan.getSoDienThoai());
        txtNgaySinh.setText(taiKhoan.getNgaySinh());
        if (taiKhoan.getGioiTinh() == 0) {
            spnGioiTinh.setSelection(1);
        } else if (taiKhoan.getGioiTinh() == 1) {
            spnGioiTinh.setSelection(2);
        } else if (taiKhoan.getGioiTinh() == 2) {
            spnGioiTinh.setSelection(0);
        }
        view.findViewById(R.id.btnCapNhat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtTen.getText().toString().trim();
                String ngaySinh = txtNgaySinh.getText().toString().trim();
                String sdt = txtSDT.getText().toString().trim();
                taiKhoan.setTenNguoiDung(ten);
                taiKhoan.setSoDienThoai(sdt);
                taiKhoan.setGioiTinh(gioiTinh);
                Calendar lich = Calendar.getInstance();
                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
                String ngayMua = sdf.format(gregorianCalendar.getTime());
                try {
                    Date homNay = sdf.parse(ngayMua);
                    Date ngayNhapVao = sdf.parse(ngaySinh);
                    if (ngayNhapVao.after(homNay)) {
                        Toast.makeText(ThongTinTaiKhoan.this, "Ngày này vẫn chưa đến", Toast.LENGTH_SHORT).show();
                        return;
                    }  else {
                        taiKhoan.setNgaySinh(ngaySinh);
                    }
                    if (daoTaiKhoan.update(taiKhoan)) {
                        list.clear();
                        list.addAll(daoTaiKhoan.selectAll_MaTK(matk));
                        dialog.dismiss();
                        SetText();
                        Toast.makeText(ThongTinTaiKhoan.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ThongTinTaiKhoan.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        });
        view.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtNgaySinh.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontext, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnCapNhat) {
            OpenDialog_Update();
        }
        return super.onOptionsItemSelected(item);
    }
    private void SetText() {
        // set text
        tvTen.setText(list.get(0).getTenNguoiDung());
        if (list.get(0).getGioiTinh() == 0) {
            tvGioiTinh.setText("Nam");
        } else if (list.get(0).getGioiTinh() == 1) {
            tvGioiTinh.setText("Nữ");
        } else if (list.get(0).getGioiTinh() == 2){
            tvGioiTinh.setText("Không xác định");
        }
        tvEmail.setText(list.get(0).getEmail());
        if (list.get(0).getNgaySinh().isEmpty()) {
            tvNgaySinh.setText("Chưa cập nhật");
            tvNgaySinh.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvNgaySinh.setText(list.get(0).getNgaySinh());
            tvNgaySinh.setTextColor(Color.parseColor("#FF000000"));
        }
        if (list.get(0).getSoDienThoai().isEmpty()) {
            tvSDT.setText("Chưa cập nhật");
            tvSDT.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvSDT.setText(list.get(0).getSoDienThoai());
            tvSDT.setTextColor(Color.parseColor("#FF000000"));
        }
    }
}