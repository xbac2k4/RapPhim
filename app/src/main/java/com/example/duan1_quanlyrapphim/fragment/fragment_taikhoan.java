package com.example.duan1_quanlyrapphim.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_quanlyrapphim.Dangnhap;
import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.ThongTinTaiKhoan;
import com.example.duan1_quanlyrapphim.TrangChu_User;
import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class fragment_taikhoan extends Fragment {
    TrangChu_User trangChuUser;
    String matk;
    daoTaiKhoan daoTaiKhoan;
    String tenNguoiDung;
    TextView tenHienThi, tvSoDu;
    TextInputEditText txtMatKhauCu, txtMatKhauMoi, txtMatKhauXN;
    String matKhauCu, matKhauMoi, matKhauXacNhan;
    public fragment_taikhoan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);
        daoTaiKhoan = new daoTaiKhoan(getContext());
        trangChuUser = (TrangChu_User) getActivity();
        matk = trangChuUser.getMaTK();
        tenNguoiDung = daoTaiKhoan.getTen(matk);
        tenHienThi = view.findViewById(R.id.tvTenHienThi);
        tenHienThi.setText(tenNguoiDung);
        tvSoDu = view.findViewById(R.id.tvSoDu);
        tvSoDu.setText("Số dư: " + daoTaiKhoan.getSoDu(matk) + " VNĐ");
        view.findViewById(R.id.tvThongTinTK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongTinTaiKhoan.class);
                intent.putExtra("matk", matk);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.tvDangXuat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_DangXuat();
            }
        });
        view.findViewById(R.id.tvDoiMatKhau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_DoiMK();
            }
        });
        tvSoDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_SoDu();
            }
        });
        return view;
    }
    public void openDialog_DoiMK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_doimatkhau, null);
        builder.setView(view);
        Dialog dialog = builder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        txtMatKhauCu = view.findViewById(R.id.txtMatKhauCu);
        txtMatKhauMoi = view.findViewById(R.id.txtMatKhauMoi);
        txtMatKhauXN = view.findViewById(R.id.txtMatKhauXacNhan);
        view.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matKhauCu = txtMatKhauCu.getText().toString().trim();
                matKhauMoi = txtMatKhauMoi.getText().toString().trim();
                matKhauXacNhan = txtMatKhauXN.getText().toString().trim();

                if (matKhauCu.isEmpty() || matKhauMoi.isEmpty() || matKhauXacNhan.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (daoTaiKhoan.checkMK(matk, matKhauCu)) {
                        if (matKhauMoi.equals(matKhauXacNhan)) {
                            dialog.dismiss();
                            openDialog_XacNhan();
                        } else {
                            Toast.makeText(getContext(), "Mật khẩu mới không khớp nhau", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void OpenDialog_DangXuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.warning);
        builder.setTitle("WARNING");
        builder.setMessage("Bạn có muốn đăng xuất không ?");
        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getContext(), Dangnhap.class));
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
    public void loadDL() {
        tenNguoiDung = daoTaiKhoan.getTen(matk);
    }
    @Override
    public void onResume() {
        super.onResume();
        loadDL();
        tenHienThi.setText(tenNguoiDung);
    }
    public void openDialog_XacNhan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning");
        builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (daoTaiKhoan.doiMatKhau( matk, matKhauMoi)) {
                    daoTaiKhoan.selectAll();
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
    private void OpenDialog_SoDu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_themsodu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextInputEditText ed_SoDu;
        Button btnAdd, btnHuy;

        ed_SoDu = view.findViewById(R.id.ed_SoDu);
        btnAdd = view.findViewById(R.id.btnXacNhan);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDu = ed_SoDu.getText().toString().trim();

                if (soDu.isEmpty()) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.valueOf(soDu) > 5000000) {
                    Toast.makeText(getContext(), "Vượt giới hạn", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (daoTaiKhoan.UpdateSoDu(matk, daoTaiKhoan.getSoDu(matk) + Integer.valueOf(soDu)) > 0) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    tvSoDu.setText("Số dư: " + daoTaiKhoan.getSoDu(matk) + " VNĐ");
                    dialog.dismiss();
                } else {
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