package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.TrangChu_User;
import com.example.duan1_quanlyrapphim.XacNhanDatVe;
import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;
import com.example.duan1_quanlyrapphim.model.ChiTietVe;
import com.example.duan1_quanlyrapphim.model.TaiKhoan;
import com.example.duan1_quanlyrapphim.model.Ve;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Adapter_ql_taikhoan extends RecyclerView.Adapter<Adapter_ql_taikhoan.ViewHolperQLTK> {

    private ArrayList<TaiKhoan> listTK;
    private Context context;
    TextView tvTen, tvGioiTinh, tvNgaySinh, tvEmail, tvSDT, tvChan;
    Button btnHuy, btnChan;
    daoTaiKhoan daoTaiKhoan;

    public Adapter_ql_taikhoan(ArrayList<TaiKhoan> listTK, Context context) {
        this.listTK = listTK;
        this.context = context;
        daoTaiKhoan = new daoTaiKhoan(context);
    }

    @NonNull
    @Override
    public ViewHolperQLTK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ql_dskh, null);
        return new Adapter_ql_taikhoan.ViewHolperQLTK(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolperQLTK holder, @SuppressLint("RecyclerView") int position) {
        TaiKhoan taiKhoan = listTK.get(position);
            holder.name_ql_dskh.setText("Họ tên: "+taiKhoan.getTenNguoiDung());
            holder.email_ql_dskh.setText("Email: "+taiKhoan.getEmail());
        if (listTK.get(position).getVaiTro() == 1) {
            holder.imgChan.setVisibility(View.INVISIBLE);
        } else if (listTK.get(position).getVaiTro() == 3) {
            holder.imgChan.setVisibility(View.VISIBLE);
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenDialog_TTKH(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listTK.size();
    }

    public static class ViewHolperQLTK extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name_ql_dskh, email_ql_dskh;
        ImageView imgChan;
        public ViewHolperQLTK(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            name_ql_dskh = itemView.findViewById(R.id.name_ql_dskh);
            email_ql_dskh = itemView.findViewById(R.id.email_ql_dskh);
            imgChan = itemView.findViewById(R.id.imgChan);
        }
    }
    public void OpenDialog_TTKH(int position) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ttkhchitiet, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        tvTen = view.findViewById(R.id.tvTen);
        tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvSDT = view.findViewById(R.id.tvSDT);
        tvChan = view.findViewById(R.id.tvChan);
        btnChan = view.findViewById(R.id.btnChan);
        btnHuy = view.findViewById(R.id.btnHuy);

        loadDl(position);
        btnChan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listTK.get(position).getVaiTro() == 1) {
                    daoTaiKhoan.UpdateVaiTro(listTK.get(position), 3);
                    Toast.makeText(context, "Đã chặn", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    notifyDataSetChanged();
                } else if (listTK.get(position).getVaiTro() == 3) {
                    daoTaiKhoan.UpdateVaiTro(listTK.get(position), 1);
                    Toast.makeText(context, "Đã bỏ chặn", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    notifyDataSetChanged();
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
    private void loadDl(int position) {
        tvTen.setText(listTK.get(position).getTenNguoiDung());
        if (listTK.get(position).getGioiTinh() == 0) {
            tvGioiTinh.setText("Nam");
        } else if (listTK.get(position).getGioiTinh() == 1) {
            tvGioiTinh.setText("Nữ");
        } else if (listTK.get(position).getGioiTinh() == 2){
            tvGioiTinh.setText("Không xác định");
        }
        if (listTK.get(position).getNgaySinh().isEmpty()) {
            tvNgaySinh.setText("Chưa cập nhật");
            tvNgaySinh.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvNgaySinh.setText(listTK.get(0).getNgaySinh());
//            tvNgaySinh.setTextColor(Color.parseColor(" #000000"));

        }
        if (listTK.get(position).getSoDienThoai().isEmpty()) {
            tvSDT.setText("Chưa cập nhật");
            tvSDT.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tvSDT.setText(listTK.get(position).getSoDienThoai());
//            tvSDT.setTextColor(Color.parseColor(" #000000"));
        }
        tvEmail.setText(listTK.get(position).getEmail());
        if (listTK.get(position).getVaiTro() != 3) {
            tvChan.setVisibility(View.INVISIBLE);
        }
        if (listTK.get(position).getVaiTro() == 1) {
            btnChan.setText("Chặn");
        } else if (listTK.get(position).getVaiTro() == 3) {
            btnChan.setText("Bỏ chặn");
        }
    }
}
