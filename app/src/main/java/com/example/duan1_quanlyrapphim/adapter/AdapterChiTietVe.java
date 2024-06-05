package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.dao.DaoVe;
import com.example.duan1_quanlyrapphim.fragment.fragment_vecuatoi;
import com.example.duan1_quanlyrapphim.model.ChiTietVe;

import java.util.ArrayList;

public class AdapterChiTietVe extends RecyclerView.Adapter<AdapterChiTietVe.ViewHolder> {
    private final Context context;
    private final ArrayList<ChiTietVe> list;
    private String matk;
    private DaoVe daoVe;
    private fragment_vecuatoi fragment_vecuatoi;

    public AdapterChiTietVe(Context context, ArrayList<ChiTietVe> list, String matk) {
        this.context = context;
        this.list = list;
        this.matk = matk;
        daoVe = new DaoVe(context);
    }
    public AdapterChiTietVe(Context context, ArrayList<ChiTietVe> list, String matk, Fragment fragment) {
        this.context = context;
        this.list = list;
        this.matk = matk;
        fragment_vecuatoi = (com.example.duan1_quanlyrapphim.fragment.fragment_vecuatoi) fragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichsu_admin, null);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaChiTietVe.setText(String.valueOf(list.get(position).getMaVeChiTiet()));
        holder.tvEmail.setText(String.valueOf(list.get(position).getEmail()));
        holder.tvTenPhim.setText(String.valueOf(list.get(position).getTenPhim()));
        holder.tvKhoiChieu.setText(String.valueOf(list.get(position).getNgayChieu()));
        holder.tvPhongChieu.setText(String.valueOf(list.get(position).getPhongChieu()));
        holder.tvGioChieu.setText(String.valueOf(list.get(position).getGioChieu()));
        holder.tvGheDaChon.setText(String.valueOf(list.get(position).getGheDaChon()));
        holder.tvTongTien.setText(String.valueOf(list.get(position).getGiaVe()));
        holder.tvNgayDat.setText(list.get(position).getNgayMua());
        if (list.get(position).getTtThanhToan() == 0) {
            holder.tvThanhToan.setText("Đã thanh toán");
            holder.tvThanhToan.setTextColor(Color.parseColor("#52DF13"));
        } else if (list.get(position).getTtThanhToan() == 1) {
            holder.tvThanhToan.setTextColor(Color.parseColor("#FD0000"));
            holder.tvThanhToan.setText("Chưa thanh toán");
            if (matk.equals("user")) {
                holder.cardViewVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenDialog_ThanhToan(list, position);
                    }
                });
            }
        }
//        if (matk.equals("admin")) {
//            holder.btnHuyVe.setVisibility(View.INVISIBLE);
//        } else if (matk.equals(1)) {
//            holder.btnHuyVe.setVisibility(View.VISIBLE);
//        }
//        holder.btnHuyVe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (daoVe.delete(list.get(position).getMaVeChiTiet()) == 1) {
//                    list.clear();
//                    list.addAll(daoVe.selectAll(matk));
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "Đã Hủy Vé", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaChiTietVe, tvEmail, tvTenPhim, tvKhoiChieu, tvPhongChieu, tvGioChieu, tvGheDaChon, tvTongTien, tvThanhToan, tvNgayDat;
        CardView cardViewVe;
//        Button btnHuyVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaChiTietVe = itemView.findViewById(R.id.txtMa);
            tvEmail = itemView.findViewById(R.id.txtEmail);
            tvTenPhim = itemView.findViewById(R.id.txtTenPhim);
            tvKhoiChieu = itemView.findViewById(R.id.txtKhoiChieu);
            tvPhongChieu = itemView.findViewById(R.id.txtPhong);
            tvGioChieu = itemView.findViewById(R.id.txtGioChieu);
            tvGheDaChon = itemView.findViewById(R.id.txtGhe);
            tvTongTien = itemView.findViewById(R.id.txtTongTien);
            tvThanhToan = itemView.findViewById(R.id.tvThanhToan);
            tvNgayDat = itemView.findViewById(R.id.txtNgayDat);
            cardViewVe = itemView.findViewById(R.id.cardViewVe);
//            btnHuyVe = itemView.findViewById(R.id.btnHuyVe);
        }
    }
    public void OpenDialog_ThanhToan(ArrayList<ChiTietVe> list, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("WARNING");
        builder.setMessage("Bạn có muốn thanh toán không?\nGiá vé: " + list.get(position).getGiaVe() + " VNĐ");
        builder.setPositiveButton("Thanh Toán", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.get(position).setTtThanhToan(0);
                daoVe.UpdateTT(list.get(position));
                Toast.makeText(context, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
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
}
