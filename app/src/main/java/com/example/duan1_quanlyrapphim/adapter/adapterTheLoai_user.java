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

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.TimKiemTheLoai;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTheLoai_user extends RecyclerView.Adapter<adapterTheLoai_user.ViewHolder>{
    private final Context context;
    private final ArrayList<TheLoai> list;
    private String matk;

    public adapterTheLoai_user(Context context, ArrayList<TheLoai> list, String matk) {
        this.context = context;
        this.list = list;
        this.matk = matk;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_theloai_user, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenTheLoai.setText(list.get(position).getTenTL());
        Picasso.get().load(list.get(position).getImgURL()).into(holder.imgTheLoai);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TimKiemTheLoai.class);
                intent.putExtra("theLoai", String.valueOf(list.get(position).getMaTL()));
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
        TextView tvTenTheLoai;
        ImageView imgTheLoai;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTheLoai = itemView.findViewById(R.id.tvTenTheLoai);
            imgTheLoai = itemView.findViewById(R.id.imgTheLoai);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
