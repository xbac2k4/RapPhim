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
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class adapterPhim_user extends RecyclerView.Adapter<adapterPhim_user.ViewHolder> {
    private final Context context;
    private final ArrayList<Phim> list;
    private String matk;

    public adapterPhim_user(Context context, ArrayList<Phim> list, String matk) {
        this.context = context;
        this.list = list;
        this.matk = matk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_theloai_user, null);
        return new adapterPhim_user.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenPhim.setTextSize(10);
        holder.tvTenPhim.setText(list.get(position).getTenPhim());
        Picasso.get().load(list.get(position).getImgPhim()).into(holder.imgPhim);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietPhim.class);
                intent.putExtra("matk", matk);
                intent.putExtra("maPhim", String.valueOf(list.get(position).getMaPhim()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhim;
        ImageView imgPhim;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhim = itemView.findViewById(R.id.tvTenTheLoai);
            imgPhim = itemView.findViewById(R.id.imgTheLoai);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
