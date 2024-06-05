package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class adapterNgayChieu extends RecyclerView.Adapter<adapterNgayChieu.ViewHolder> {
    private final Context context;
    private final ArrayList<LichChieu> list;
    private final XacNhanDatVe xacNhanDatVe;
    android.icu.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int day, month, year;

    public adapterNgayChieu(Context context, ArrayList<LichChieu> list, Activity activity) {
        this.context = context;
        this.list = list;
        xacNhanDatVe = (XacNhanDatVe) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ngaychieu, null);
        return new adapterNgayChieu.ViewHolder(view);
    }
    int check = 0;
    int viTri = 0;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvNgayChieu.setText(String.valueOf(list.get(position).getNgayChieu()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                GregorianCalendar gregorianCalendar = new GregorianCalendar( year, month, day);
                String ngayMua = sdf.format(gregorianCalendar.getTime());
                try {
                    Date homNay = sdf.parse(ngayMua);
                    Date ngayNhapVao = sdf.parse(list.get(position).getNgayChieu());
                    if (ngayNhapVao.before(homNay)) {
                        Toast.makeText(context, "Xin lỗi lịch này đã ngừng chiếu", Toast.LENGTH_SHORT).show();
                    } else if (check == 0 && viTri == 0) {
                        holder.layout.setBackground(new ColorDrawable(Color.parseColor("#52DF13")));
                        check++;
                        viTri = list.get(position).getMaLichChieu();
                        xacNhanDatVe.getKhungGio(String.valueOf(list.get(position).getMaPhim()), String.valueOf(list.get(position).getNgayChieu()));
                    } else if (check != 0 && viTri == list.get(position).getMaLichChieu()) {
                        holder.layout.setBackground(new ColorDrawable(Color.parseColor("#25000000")));
                        check = 0;
                        viTri = 0;
                        xacNhanDatVe.getKhungGio(String.valueOf(list.get(position).getMaPhim()), "");
                        xacNhanDatVe.getSoGhe("");
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
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
