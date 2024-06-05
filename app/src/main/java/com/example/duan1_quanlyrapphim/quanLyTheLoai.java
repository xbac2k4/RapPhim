package com.example.duan1_quanlyrapphim;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_quanlyrapphim.adapter.AdapterTheLoai_Admin;
import com.example.duan1_quanlyrapphim.dao.daoTheLoai;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class quanLyTheLoai extends Fragment {
    private List<TheLoai> list = new ArrayList<>();
    daoTheLoai daoTheLoai;
    ImageButton btnThem;
    ListView listView;
    EditText edtSearch;
    AdapterTheLoai_Admin adapter;


    public quanLyTheLoai() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quan_ly_danh_sach,container,false);
        
        daoTheLoai = new daoTheLoai(getContext());
        list = daoTheLoai.selectAll();
//        list.add(new TheLoai("https://i.ytimg.com/vi/2K8EpM-piDw/maxresdefault.jpg","Hành động"));
//        list.add(new TheLoai("https://photo2.tinhte.vn/data/attachment-files/2021/07/5557920_CV.jpg","Trinh thám"));
        listView = v.findViewById(R.id.listTL);
        btnThem = v.findViewById(R.id.btnThem);
        edtSearch = v.findViewById(R.id.edtSearch);

        
        adapter = new AdapterTheLoai_Admin(list,getContext());
        listView.setAdapter(adapter);

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
                dialogThem();
            }
        });

        return v;
    }
    private void handleSearch(String query) {
        List<TheLoai> listSearch = new ArrayList<>();
        for (TheLoai theloai : list) {
            if (theloai.getTenTL().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(theloai);
            }
        }
        adapter = new AdapterTheLoai_Admin(listSearch,getContext());
        listView.setAdapter(adapter);
    }
    private void dialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.item_add_theloai, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextInputLayout textInputIMGTL = view.findViewById(R.id.textInputIMGTL);
        TextInputLayout textInputTL = view.findViewById(R.id.textInputTL);
        TextInputEditText ed_URL_TheLoai,ed_TenTL;
        Button btnAdd, btnHuy;

        ed_URL_TheLoai = view.findViewById(R.id.ed_URL_TheLoai);
        ed_TenTL = view.findViewById(R.id.ed_TenTL);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ed_URL_TheLoai.getText().toString().trim();
                String tenTL = ed_TenTL.getText().toString().trim();

                if(url.isEmpty()||tenTL.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                TheLoai theLoai = new TheLoai(url,tenTL);

                if (daoTheLoai.insert(theLoai)) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(daoTheLoai.selectAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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

}