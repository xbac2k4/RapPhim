package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.ChiTietPhim;
import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.XacNhanDatVe;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTimKiemPhim_user extends RecyclerView.Adapter<adapterTimKiemPhim_user.ViewHolder> {
    private final Context context;
    private final ArrayList<Phim> list;
    private String matk;

    public adapterTimKiemPhim_user(Context context, ArrayList<Phim> list, String matk) {
        this.context = context;
        this.list = list;
        this.matk = matk;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_timkiem_phim, null);
        return new adapterTimKiemPhim_user.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImgPhim()).into(holder.imgPhim);
        holder.tvTenPhim.setText(list.get(position).getTenPhim());
        holder.tvTheLoai.setText(list.get(position).getTenTheLoai());
        holder.tvGiaVe.setText(String.valueOf(list.get(position).getGiaVe()));
        holder.tvGioChieu.setText(list.get(position).getKhoiChieu());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietPhim.class);
                intent.putExtra("maPhim", String.valueOf(list.get(position).getMaPhim()));
                intent.putExtra("matk", matk);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhim, tvTheLoai, tvGiaVe, tvGioChieu;
        ImageView imgPhim;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim = itemView.findViewById(R.id.imgAnhPhim);
            tvTenPhim = itemView.findViewById(R.id.txtPhim);
            tvTheLoai = itemView.findViewById(R.id.txtTheLoai);
            tvGiaVe = itemView.findViewById(R.id.txtGiaVe);
            tvGioChieu = itemView.findViewById(R.id.txtGioChieu);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
