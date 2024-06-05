package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.dao.daoTheLoai;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterPhim_admin extends RecyclerView.Adapter<adapterPhim_admin.ViewHolder> {
    private final Context context;
    private final ArrayList<Phim> list;
    EditText txtAnhPhim, txtTenPhim, txtMoTa, txtGiaVe, txtKhoiChieu;
    TextView tvTieuDe;
    Spinner spnLoaiPhim;
    com.example.duan1_quanlyrapphim.dao.daoTheLoai daoTheLoai;
    daoPhim daoPhim;
    int maTheLoai;
    Phim phim;

    public adapterPhim_admin(Context context, ArrayList<Phim> list) {
        this.context = context;
        this.list = list;
        daoTheLoai = new daoTheLoai(context);
        daoPhim = new daoPhim(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phim_admin, null);
        return new adapterPhim_admin.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso
                .get()
                .load(list.get(position).getImgPhim())
                .error(R.drawable.loihinhanh)
                .into(holder.imgPhim);
        holder.tvTenPhim.setText(list.get(position).getTenPhim());
        holder.tvMoTa.setText(list.get(position).getMoTa());
        holder.tvTheLoai.setText(list.get(position).getTenTheLoai());
        holder.tvGiaVe.setText(String.valueOf(list.get(position).getGiaVe()));
        holder.tvGioChieu.setText(list.get(position).getKhoiChieu());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phim = list.get(position);
                OpenDialog_Update();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phim = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Warning");
                builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoPhim.DeleteRow(phim);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(daoPhim.selectAll());
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
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhim, tvMoTa, tvTheLoai, tvGiaVe, tvGioChieu;
        ImageView imgPhim, btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim = itemView.findViewById(R.id.imgAnhPhim);
            tvTenPhim = itemView.findViewById(R.id.txtPhim);
            tvMoTa = itemView.findViewById(R.id.txtMoTa);
            tvTheLoai = itemView.findViewById(R.id.txtTheLoai);
            tvGiaVe = itemView.findViewById(R.id.txtGiaVe);
            tvGioChieu = itemView.findViewById(R.id.txtGioChieu);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    public void OpenDialog_Update() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phim, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        tvTieuDe = view.findViewById(R.id.tvThemPhim);
        tvTieuDe.setText("Update");
        txtAnhPhim = view.findViewById(R.id.txtAnhPhim);
        txtTenPhim = view.findViewById(R.id.txtTenPhim);
        txtMoTa = view.findViewById(R.id.txtMoTa);
        txtGiaVe = view.findViewById(R.id.txtGiaVe);
        txtKhoiChieu = view.findViewById(R.id.txtKhoiChieu);
        spnLoaiPhim = view.findViewById(R.id.spnLoaiPhim);
        ArrayList<TheLoai> listL = new ArrayList<>();
        listL = daoTheLoai.selectAll();
        ArrayList<String> theLoaiArr = new ArrayList<>();
        for (TheLoai x: listL) {
            theLoaiArr.add(x.getTenTL());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, theLoaiArr);
        spnLoaiPhim.setAdapter(adapter);
        spnLoaiPhim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = daoTheLoai.getMaLoai(String.valueOf(theLoaiArr.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for (int i = 0; i < theLoaiArr.size(); i++) {
            if (theLoaiArr.get(i).toString().equals(phim.getTenTheLoai())) {
                spnLoaiPhim.setSelection(i);
            }
        }
        txtAnhPhim.setText(phim.getImgPhim());
        txtTenPhim.setText(phim.getTenPhim());
        txtMoTa.setText(phim.getMoTa());
        txtGiaVe.setText(String.valueOf(phim.getGiaVe()));
        txtKhoiChieu.setText(phim.getKhoiChieu());
        Button btnThem = view.findViewById(R.id.btnThem);
        btnThem.setText("Update");
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anhPhim = txtAnhPhim.getText().toString();
                String tenPhim = txtTenPhim.getText().toString().trim();
                String moTa = txtMoTa.getText().toString().trim();
                String giaVe = txtGiaVe.getText().toString().trim();
                String khoiChieu = txtKhoiChieu.getText().toString().trim();
                //
                phim.setImgPhim(anhPhim);
                phim.setTenPhim(tenPhim);
                phim.setMoTa(moTa);
                phim.setGiaVe(Integer.valueOf(giaVe));
                phim.setKhoiChieu(khoiChieu);
                phim.setMaTheLoai(maTheLoai);
                //
                if (daoPhim.update(phim)) {
                    list.clear();
                    list.addAll(daoPhim.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
