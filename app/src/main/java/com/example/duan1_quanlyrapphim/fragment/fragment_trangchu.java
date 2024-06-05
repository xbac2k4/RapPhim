package com.example.duan1_quanlyrapphim.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.TrangChu_User;
import com.example.duan1_quanlyrapphim.adapter.adapterPhim_user;
import com.example.duan1_quanlyrapphim.adapter.adapterTheLoai_user;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.dao.daoTheLoai;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TheLoai;

import java.util.ArrayList;

public class fragment_trangchu extends Fragment {
    ArrayList<SlideModel> imageList = new ArrayList<>();
    ArrayList<TheLoai> listTL = new ArrayList<>();
    ArrayList<Phim> listPhim = new ArrayList<>();
    RecyclerView rcvTheLoai;
    RecyclerView rcvPhim;
    adapterTheLoai_user adapterTheLoai;
    adapterPhim_user adapterPhim;
    daoTheLoai daoTheLoai;
    daoPhim daoPhim;
    TrangChu_User trangChuUser;
    public fragment_trangchu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        trangChuUser = (TrangChu_User) getActivity();
        String matk = trangChuUser.getMaTK();
        // slide show
        imageList.add(new SlideModel("https://i.ytimg.com/vi/fVWlCV9_n7w/maxresdefault.jpg", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.ytimg.com/vi/2K8EpM-piDw/maxresdefault.jpg", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.ytimg.com/vi/Gah3ahVcCWQ/maxresdefault.jpg", "And people do that.", ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        // the loai
        daoTheLoai = new daoTheLoai(getContext());
        listTL = daoTheLoai.selectAll();
        rcvTheLoai = view.findViewById(R.id.rcvTheLoai);
        adapterTheLoai = new adapterTheLoai_user(getContext(), listTL, matk);
        LinearLayoutManager layoutManagerTL = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvTheLoai.setLayoutManager(layoutManagerTL);
        rcvTheLoai.setAdapter(adapterTheLoai);
        // tat ca phim
        daoPhim = new daoPhim(getContext());
        listPhim = daoPhim.selectAll();
        rcvPhim = view.findViewById(R.id.rcvTatCaPhim);
        adapterPhim = new adapterPhim_user(getContext(), listPhim, matk);
        GridLayoutManager layoutManagerPhim = new GridLayoutManager(getContext(), 3);
        rcvPhim.setLayoutManager(layoutManagerPhim);
        rcvPhim.setAdapter(adapterPhim);
        //
        return view;
    }
}