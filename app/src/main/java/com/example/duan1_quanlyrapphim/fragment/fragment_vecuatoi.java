package com.example.duan1_quanlyrapphim.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.ThongTinTaiKhoan;
import com.example.duan1_quanlyrapphim.TrangChu_User;
import com.example.duan1_quanlyrapphim.adapter.AdapterChiTietVe;
import com.example.duan1_quanlyrapphim.adapter.adapterPhim_admin;
import com.example.duan1_quanlyrapphim.dao.DaoVe;
import com.example.duan1_quanlyrapphim.model.ChiTietVe;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.Ve;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class fragment_vecuatoi extends Fragment {
    TrangChu_User trangChuUser;
    RecyclerView rcvVeCuaToi;
    TextView tvChuaCoVe;
    DaoVe daoVe;
    AdapterChiTietVe adapterChiTietVe;
    ArrayList<ChiTietVe> list = new ArrayList<>();
    Spinner spnSapXep;
    public fragment_vecuatoi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vecuatoi, container, false);
        trangChuUser = (TrangChu_User) getActivity();
        String matk = trangChuUser.getMaTK();
        tvChuaCoVe = view.findViewById(R.id.tvChuaCove);
        rcvVeCuaToi = view.findViewById(R.id.rcvVeCuaToi);
        daoVe = new DaoVe(getContext());
        list = daoVe.selectAll(matk);
        adapterChiTietVe = new AdapterChiTietVe(getContext(), list, "user");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvVeCuaToi.setLayoutManager(linearLayoutManager);
        rcvVeCuaToi.setAdapter(adapterChiTietVe);
        if (list.size() == 0) {
            tvChuaCoVe.setVisibility(View.VISIBLE);
        } else {
            tvChuaCoVe.setVisibility(View.INVISIBLE);
        }
        adapterChiTietVe.notifyDataSetChanged();
        //
        spnSapXep = view.findViewById(R.id.spnSapXep);
        ArrayList<String> SXArr = new ArrayList<>();
        SXArr.add("      -- Tất Cả --");
        SXArr.add("Chưa Thanh Toán");
        SXArr.add("Đã Thanh Toán");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, SXArr);
        spnSapXep.setAdapter(adapter);
        spnSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    adapterChiTietVe = new AdapterChiTietVe(getContext(), list, "user");
                    rcvVeCuaToi.setAdapter(adapterChiTietVe);
                } else if (position == 1) {
                    handleSearch(1);
                } else {
                    handleSearch(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.btnF5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDL();
            }
        });
        return view;
    }
    public void handleSearch(int query) {
        ArrayList<ChiTietVe> listSearch = new ArrayList<>();
        listSearch.clear();
        for (ChiTietVe chiTietVe : list) {
            if (chiTietVe.getTtThanhToan() == query) {
                listSearch.add(chiTietVe);
            }
        }
        adapterChiTietVe = new AdapterChiTietVe(getContext(), listSearch, "user");
        rcvVeCuaToi.setAdapter(adapterChiTietVe);
    }
    public void loadDL() {
        if (spnSapXep.getSelectedItemPosition() == 0) {
            adapterChiTietVe = new AdapterChiTietVe(getContext(), list, "user");
            rcvVeCuaToi.setAdapter(adapterChiTietVe);
        } else if (spnSapXep.getSelectedItemPosition() == 1) {
            handleSearch(1);
        } else if (spnSapXep.getSelectedItemPosition() == 2) {
            handleSearch(0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterChiTietVe.notifyDataSetChanged();
    }
}