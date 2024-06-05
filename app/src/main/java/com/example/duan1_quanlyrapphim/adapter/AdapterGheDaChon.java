package com.example.duan1_quanlyrapphim.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.model.soGhe;

import java.util.ArrayList;

public class AdapterGheDaChon extends RecyclerView.Adapter<AdapterGheDaChon.ViewHolder> {
    private Context context;
    private ArrayList<soGhe> list;

    public AdapterGheDaChon(Context context, ArrayList<soGhe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_soghedachon, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSoGhe.setText("" + list.get(position).getSoGhe());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoGhe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoGhe = itemView.findViewById(R.id.tvSoGhe);
        }
    }
}
