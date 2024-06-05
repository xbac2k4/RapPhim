package com.example.duan1_quanlyrapphim.fragment;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.dao.DaoVe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fragment_DoanhThu extends Fragment {

    EditText txtTuNgay, txtDenNgay;
    TextView lblDoanhThu;
    ImageButton btnTuNgay, btnDenNgay;
    Button btnDoanhThu;
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy/MM/dd");
    int ngay, thang, nam;
//    BarChart barChart;
//    ArrayList<BarEntry> listBarEntry;
//    ArrayList<String> labelName;
    public Fragment_DoanhThu() {
        // Required empty public constructor
    }
    DaoVe daoVe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__doanh_thu, container, false);
        //
        txtTuNgay = view.findViewById(R.id.txtTuNgay);
        txtDenNgay = view.findViewById(R.id.txtDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        lblDoanhThu = view.findViewById(R.id.lblDoanhThu);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        daoVe = new DaoVe(getContext());
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();
                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateTuNgay, nam, thang, ngay);
                d.show();
            }
        });
        txtTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();

                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateTuNgay, nam, thang, ngay);
                d.show();
            }
        });
        txtDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();

                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateDenNgay, nam, thang, ngay);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();

                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateDenNgay, nam, thang, ngay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = txtTuNgay.getText().toString().trim();
                String denNgay = txtDenNgay.getText().toString().trim();
                if (tuNgay.isEmpty() || denNgay.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                } else {
                    lblDoanhThu.setText("Doanh Thu: " + daoVe.getDoanhThu(tuNgay, denNgay) + " VNĐ");
                }
            }
        });
        //
        return view;
    }
    DatePickerDialog.OnDateSetListener dateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtTuNgay.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener dateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtDenNgay.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
}