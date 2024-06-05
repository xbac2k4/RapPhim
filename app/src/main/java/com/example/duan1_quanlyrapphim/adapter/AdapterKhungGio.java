package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.XacNhanDatVe;
import com.example.duan1_quanlyrapphim.model.LichChieu;

import java.util.ArrayList;

public class AdapterKhungGio extends RecyclerView.Adapter<AdapterKhungGio.ViewHolder>{
    private final Context context;
    private final ArrayList<LichChieu> list;
    private final XacNhanDatVe xacNhanDatVe;
    private int check;

    public AdapterKhungGio(Context context, ArrayList<LichChieu> list, Activity activity, int check) {
        this.context = context;
        this.list = list;
        xacNhanDatVe  = (XacNhanDatVe) activity;
        this.check = check;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ngaychieu, null);
        return new ViewHolder(view);
    }
    int viTri = 0;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvNgayChieu.setText(String.valueOf(list.get(position).getKhungGio()));
        holder.layout.setBackground(new ColorDrawable(Color.parseColor("#25000000")));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 0) {
                    holder.layout.setBackground(new ColorDrawable(Color.parseColor("#52DF13")));
//                    viTri = list.get(position).getMaLichChieu();
                    check = 1;
                    xacNhanDatVe.getSoGhe(String.valueOf(list.get(position).getMaLichChieu()));
//                    Toast.makeText(context, "Khung giờ: " + list.get(position).getMaKhungGio(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, String.valueOf(viTri), Toast.LENGTH_SHORT).show();
                } else if (check != 0) {
                    holder.layout.setBackground(new ColorDrawable(Color.parseColor("#25000000")));
//                    viTri = 0;
                    check = 0;
                    xacNhanDatVe.getSoGhe("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayChieu;
        CardView cardView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayChieu = itemView.findViewById(R.id.tvNgayChieu);
            cardView = itemView.findViewById(R.id.card_view);
            layout = itemView.findViewById(R.id.line1);
        }
    }
}
