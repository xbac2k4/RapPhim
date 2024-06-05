package com.example.duan1_quanlyrapphim.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.adapter.AdapterTheLoai_Admin;
import com.example.duan1_quanlyrapphim.adapter.Adapter_ql_taikhoan;
import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;
import com.example.duan1_quanlyrapphim.model.TaiKhoan;
import com.example.duan1_quanlyrapphim.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class fragment_ql_taikhoan extends Fragment {

    ArrayList<TaiKhoan> listTk;
    RecyclerView rc_ql_dskh;
    daoTaiKhoan daotaikhoan;
    EditText edtSearch;
    Adapter_ql_taikhoan adapter_ql;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ql_taikhoan, container, false);
        rc_ql_dskh = v.findViewById(R.id.rc_ql_dskh);
        edtSearch = v.findViewById(R.id.edtSearch);

        daotaikhoan = new daoTaiKhoan(getContext());
        listTk = daotaikhoan.selectAll_admin();
        adapter_ql = new Adapter_ql_taikhoan(listTk, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        rc_ql_dskh.setLayoutManager(manager);
        rc_ql_dskh.setAdapter(adapter_ql);

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
        return v;

    }

    private void handleSearch(String query) {
        ArrayList<TaiKhoan> listSearch = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTk) {
            if (taiKhoan.getTenNguoiDung().toLowerCase().contains(query.toLowerCase()) || taiKhoan.getEmail().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(taiKhoan);
            }
        }
        adapter_ql = new Adapter_ql_taikhoan(listSearch, getContext());
        rc_ql_dskh.setAdapter(adapter_ql);
    }

}
