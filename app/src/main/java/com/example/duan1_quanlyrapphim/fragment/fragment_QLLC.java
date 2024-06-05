package com.example.duan1_quanlyrapphim.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.adapter.AdapterLichChieu;
import com.example.duan1_quanlyrapphim.dao.DAOLichChieu;
import com.example.duan1_quanlyrapphim.dao.DaoGheNgoi;
import com.example.duan1_quanlyrapphim.dao.DaoKhungGio;
import com.example.duan1_quanlyrapphim.dao.DaoPhong;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.model.KhungGio;
import com.example.duan1_quanlyrapphim.model.LichChieu;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.Phong;
import com.example.duan1_quanlyrapphim.model.soGhe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class fragment_QLLC extends Fragment {
    RecyclerView rvcLichChieu;
    DAOLichChieu daoLichChieu;
    EditText edtSearch;
    AdapterLichChieu adapterLichChieu;
    ArrayList<LichChieu> list = new ArrayList<>();
    ImageButton btnThem;
    String ngay;
    Spinner spnTenPhim,spnPhong,spnKhungGio;
    EditText edtNgayChieu;
    Button btnHuy,btnAdd;

    DaoPhong daoPhong;
    DaoKhungGio daoKhungGio;
    daoPhim daoPhim;
    DaoGheNgoi daoGheNgoi;
    android.icu.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int day, month, year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql, container, false);
        rvcLichChieu = view.findViewById(R.id.rcvPhim);
        edtSearch = view.findViewById(R.id.edtSearch);
        btnThem = view.findViewById(R.id.btnThem);
        daoPhong = new DaoPhong(getContext());
        daoKhungGio = new DaoKhungGio(getContext());
        daoPhim = new daoPhim(getContext());
        daoGheNgoi = new DaoGheNgoi(getContext());
        daoLichChieu = new DAOLichChieu(getContext());
        list = daoLichChieu.selectAll();
        adapterLichChieu = new AdapterLichChieu(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvcLichChieu.setLayoutManager(linearLayoutManager);
        rvcLichChieu.setAdapter(adapterLichChieu);
        adapterLichChieu.notifyDataSetChanged();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handleSearch(s.toString());
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_lichchieu, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



                spnTenPhim = view.findViewById(R.id.spnTenPhim);
                spnPhong = view.findViewById(R.id.spnPhong);
                spnKhungGio = view.findViewById(R.id.spnKhungGio);
                edtNgayChieu = view.findViewById(R.id.txtNgayChieu);
                btnAdd = view.findViewById(R.id.btnThem);
                btnHuy = view.findViewById(R.id.btnHuy);

                LichChieu lichChieu = new LichChieu();

                spnTenPhim.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getTenPhimList()));
                spnTenPhim.setSelection(getTenPhimList().indexOf(lichChieu.getTenPhim()));

                spnPhong.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getPhongList()));
                spnPhong.setSelection(getPhongList().indexOf(daoPhong.laySoPhongBangMa(lichChieu.getMaPhong())));

                edtNgayChieu.setText(lichChieu.getNgayChieu());

                spnKhungGio.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getKhungGioList()));
                spnKhungGio.setSelection(getKhungGioList().indexOf(daoKhungGio.getKhungGioByMa(lichChieu.getMaKhungGio())));

                edtNgayChieu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chonNgay();
                    }
                });


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenPhim = spnTenPhim.getSelectedItem().toString();
                        String phong = spnPhong.getSelectedItem().toString();
                        String khungGio = spnKhungGio.getSelectedItem().toString();
                        String ngay = edtNgayChieu.getText().toString();

                        if(tenPhim.isEmpty()||phong.isEmpty()||khungGio.isEmpty()||ngay.isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int maLC;
                        do {
                            maLC = new Random().nextInt(999999999);
                        } while (daoLichChieu.checkMaLC(String.valueOf(maLC)) == false);
                        lichChieu.setMaLichChieu(maLC);
                        lichChieu.setMaPhong(daoPhong.layMaBangSoPhong(phong));
                        lichChieu.setNgayChieu(ngay);
                        lichChieu.setMaPhim(daoPhim.getMaPhim(tenPhim));
                        lichChieu.setMaKhungGio(daoKhungGio.getMaKhungGio(khungGio));
                        lichChieu.setKhungGio(khungGio);

                        Calendar calendar = Calendar.getInstance();
                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH);
                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        GregorianCalendar gregorianCalendar = new GregorianCalendar( year, month, day);
                        String ngayMua = sdf.format(gregorianCalendar.getTime());
                        try {
                            Date homNay = sdf.parse(ngayMua);
                            Date ngayNhapVao = sdf.parse(edtNgayChieu.getText().toString());
                            if (ngayNhapVao.before(homNay)) {
                                Toast.makeText(getContext(), "Ngày này đã qua vui lòng chọn ngày khác", Toast.LENGTH_SHORT).show();
                            } else if (daoLichChieu.checkLC(lichChieu.getNgayChieu(), String.valueOf(lichChieu.getMaPhong()), String.valueOf(lichChieu.getMaKhungGio()))) {
                                Toast.makeText(getContext(), "Lịch chiếu đã tồn tại", Toast.LENGTH_SHORT).show();
                            } else if (daoLichChieu.insert(lichChieu)){
                                for (int i = 1; i<=20;i++) {
                                    daoGheNgoi.insert(new soGhe( i, 1, lichChieu.getMaLichChieu()));
                                }
                                list.clear();
                                list.addAll(daoLichChieu.selectAll());
                                adapterLichChieu.notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Lỗi thêm", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
    private void handleSearch(String query) {
        ArrayList<LichChieu> listSearch = new ArrayList<>();
        for (LichChieu lichChieu : list) {
            if (lichChieu.getTenPhim().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(lichChieu.getMaLichChieu()).contains(query) ||
                    String.valueOf(lichChieu.getMaPhong()).contains(query)) {
                listSearch.add(lichChieu);
            }
        }
        adapterLichChieu = new AdapterLichChieu(getContext(), listSearch);
        rvcLichChieu.setAdapter(adapterLichChieu);
    }


    private ArrayList<String> getTenPhimList() {
        daoPhim dao = new daoPhim(getContext());
        ArrayList<Phim> list1 = dao.selectAll();
        ArrayList<String> tenTenPhimList = new ArrayList<>();

        for (Phim phim: list1){
            tenTenPhimList.add(phim.getTenPhim());
        }
        return tenTenPhimList;
    }

    private ArrayList<String> getPhongList() {
        DaoPhong dao = new DaoPhong(getContext());
        ArrayList<Phong> list1 = dao.selectAll();
        ArrayList<String> tenPhongList = new ArrayList<>();

        for (Phong phong: list1){
            tenPhongList.add(phong.getSoPhong());
        }
        return tenPhongList;
    }

    private ArrayList<String> getKhungGioList() {
        DaoKhungGio daoKhungGio = new DaoKhungGio(getContext());
        ArrayList<KhungGio> list1 = daoKhungGio.selectAll();
        ArrayList<String> tenKGList = new ArrayList<>();

        for (KhungGio khungGio: list1){
            tenKGList.add(khungGio.getKhungGio());
        }
        return tenKGList;
    }

    private void chonNgay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(getContext(), 0, date, year, month, day);
        d.show();
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int Year, int Month, int dayOfMonth) {
            day = dayOfMonth;
            month = Month;
            year = Year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( year, month, day);
            edtNgayChieu.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
}