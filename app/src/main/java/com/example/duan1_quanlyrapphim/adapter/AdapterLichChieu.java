package com.example.duan1_quanlyrapphim.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.dao.DAOLichChieu;
import com.example.duan1_quanlyrapphim.dao.DaoKhungGio;
import com.example.duan1_quanlyrapphim.dao.DaoPhong;
import com.example.duan1_quanlyrapphim.dao.daoPhim;
import com.example.duan1_quanlyrapphim.model.KhungGio;
import com.example.duan1_quanlyrapphim.model.LichChieu;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.Phong;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdapterLichChieu extends RecyclerView.Adapter<AdapterLichChieu.ViewHolder> {
    private final Context context;
    private final ArrayList<LichChieu> list;
    private DAOLichChieu daoLichChieu;
    String ngay;
    Spinner spnTenPhim,spnPhong,spnKhungGio;
    EditText edtNgayChieu;
    Button btnHuy,btnSua;
    TextView tvSuaLC;
    DaoPhong daoPhong;
    daoPhim daoPhim;
    DaoKhungGio daoKhungGio;
    android.icu.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int day, month, year;
    public AdapterLichChieu(Context context, ArrayList<LichChieu> list) {
        this.context = context;
        this.list = list;
        daoLichChieu = new DAOLichChieu(context);
        daoPhong = new DaoPhong(context);
        daoPhim = new daoPhim(context);
        daoKhungGio = new DaoKhungGio(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichchieu, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LichChieu lichChieu = list.get(position);

        holder.tvMaLichChieu.setText("Mã: " + lichChieu.getMaLichChieu());
        holder.tvTenPhim.setText(daoPhim.getTenPhim(String.valueOf(lichChieu.getMaPhim())));
        holder.tvPhong.setText("Phòng: " + lichChieu.getMaPhong());
        holder.tvNgayChieu.setText(lichChieu.getNgayChieu());
        holder.tvKhungGio.setText(daoKhungGio.getKhungGioByMa(lichChieu.getMaKhungGio()));


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa thể loại phim");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (daoLichChieu.delete(lichChieu.getMaLichChieu())) {
                            list.clear();
                            list.addAll(daoLichChieu.selectAll());
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút No
                    }
                });
                builder.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_lichchieu, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                spnTenPhim = view.findViewById(R.id.spnTenPhim);
                spnPhong = view.findViewById(R.id.spnPhong);
                spnKhungGio = view.findViewById(R.id.spnKhungGio);
                edtNgayChieu = view.findViewById(R.id.txtNgayChieu);
                btnSua = view.findViewById(R.id.btnThem);
                btnHuy = view.findViewById(R.id.btnHuy);
                tvSuaLC = view.findViewById(R.id.tvThemLC);

                tvSuaLC.setText("Cập nhật lịch chiếu");
                btnSua.setText("Cập nhật");

                spnTenPhim.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenPhimList()));
                spnTenPhim.setSelection(getTenPhimList().indexOf(lichChieu.getTenPhim()));

                spnPhong.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getPhongList()));
                spnPhong.setSelection(getPhongList().indexOf(daoPhong.laySoPhongBangMa(lichChieu.getMaPhong())));

                edtNgayChieu.setText(lichChieu.getNgayChieu());

                spnKhungGio.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getKhungGioList()));
                spnKhungGio.setSelection(getKhungGioList().indexOf(daoKhungGio.getKhungGioByMa(lichChieu.getMaKhungGio())));

                edtNgayChieu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chonNgay();
                    }
                });



                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenPhim = spnTenPhim.getSelectedItem().toString();
                        String phong = spnPhong.getSelectedItem().toString();
                        String khungGio = spnKhungGio.getSelectedItem().toString();
                        String ngay = edtNgayChieu.getText().toString();


                        if(tenPhim.isEmpty()||phong.isEmpty()||khungGio.isEmpty()||ngay.isEmpty()){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        lichChieu.setMaPhong(daoPhong.layMaBangSoPhong(phong));
                        lichChieu.setNgayChieu(ngay);
                        lichChieu.setMaPhim(daoPhim.getMaPhim(tenPhim));
                        lichChieu.setMaKhungGio(daoKhungGio.getMaKhungGio(khungGio));
                        lichChieu.setKhungGio(khungGio);
                        //
                        Calendar calendar = Calendar.getInstance();
                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH);
                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        GregorianCalendar gregorianCalendar = new GregorianCalendar( year, month, day);
                        String ngayMua = sdf.format(gregorianCalendar.getTime());
                        try {
                            Date homNay = sdf.parse(ngayMua);
                            Date ngayNhapVao = sdf.parse(edtNgayChieu.getText().toString());
                            if (ngayNhapVao.before(homNay)) {
                                Toast.makeText(context, "Ngày này đã qua vui lòng chọn ngày khác", Toast.LENGTH_SHORT).show();
                            } else if (daoLichChieu.update(lichChieu)){
                                list.clear();
                                list.addAll(daoLichChieu.selectAll());
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        //
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private ArrayList<String> getTenPhimList() {
        daoPhim dao = new daoPhim(context);
        ArrayList<Phim> list1 = dao.selectAll();
        ArrayList<String> tenTenPhimList = new ArrayList<>();

        for (Phim phim: list1){
            tenTenPhimList.add(phim.getTenPhim());
        }
        return tenTenPhimList;
    }

    private ArrayList<String> getPhongList() {
        DaoPhong dao = new DaoPhong(context);
        ArrayList<Phong> list1 = dao.selectAll();
        ArrayList<String> tenPhongList = new ArrayList<>();

        for (Phong phong: list1){
            tenPhongList.add(phong.getSoPhong());
        }
        return tenPhongList;
    }

    private ArrayList<String> getKhungGioList() {
        DaoKhungGio daoKhungGio = new DaoKhungGio(context);
        ArrayList<KhungGio> list1 = daoKhungGio.selectAll();
        ArrayList<String> tenKGList = new ArrayList<>();

        for (KhungGio khungGio: list1){
            tenKGList.add(khungGio.getKhungGio());
        }
        return tenKGList;
    }

    private void chonNgay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(context, 0, date, year, month, day);
        d.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLichChieu, tvTenPhim, tvPhong, tvNgayChieu, tvKhungGio;
        ImageView btnEdit, btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLichChieu = itemView.findViewById(R.id.tvMaLichChieu);
            tvTenPhim = itemView.findViewById(R.id.txtTenPhim);
            tvNgayChieu = itemView.findViewById(R.id.txtNgayChieu);
            tvPhong = itemView.findViewById(R.id.txtPhong);
            tvKhungGio = itemView.findViewById(R.id.txtKhungGio);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int Year, int Month, int dayOfMonth) {
            day = dayOfMonth;
            month = Month;
            year = Year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( year, month, day);
            edtNgayChieu.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
}
