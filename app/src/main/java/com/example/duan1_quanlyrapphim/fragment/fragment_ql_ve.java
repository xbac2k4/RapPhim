package com.example.duan1_quanlyrapphim.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_quanlyrapphim.ChiTietPhim;
import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.adapter.AdapterChiTietVe;
import com.example.duan1_quanlyrapphim.dao.DaoVe;
import com.example.duan1_quanlyrapphim.model.ChiTietVe;

import java.util.ArrayList;

public class fragment_ql_ve extends Fragment {
    RecyclerView rcvVe;
    ArrayList<ChiTietVe> list = new ArrayList<>();
    DaoVe daoVe;
    AdapterChiTietVe adapterChiTietVe;
    public fragment_ql_ve() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql, container, false);
        rcvVe = view.findViewById(R.id.rcvPhim);
        daoVe = new DaoVe(getContext());
        list = daoVe.selectAll_admin();
        adapterChiTietVe = new AdapterChiTietVe(getContext(), list, "admin");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvVe.setLayoutManager(linearLayoutManager);
        rcvVe.setAdapter(adapterChiTietVe);
        adapterChiTietVe.notifyDataSetChanged();
        view.findViewById(R.id.btnThem).setVisibility(View.INVISIBLE);
        return view;
    }
}