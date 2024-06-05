package com.example.duan1_quanlyrapphim.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_quanlyrapphim.R;
import com.example.duan1_quanlyrapphim.dao.daoTheLoai;
import com.example.duan1_quanlyrapphim.model.TheLoai;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class AdapterTheLoai_Admin extends BaseAdapter {
    private List<TheLoai> list;
    private Context context;
    private daoTheLoai daoTheLoai;

    public AdapterTheLoai_Admin(List<TheLoai> list, Context context) {
        this.list = list;
        this.context = context;
        daoTheLoai = new daoTheLoai(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_theloai,parent,false);
            holder = new ViewHolder();
            holder.imgAnhTheLoai = convertView.findViewById(R.id.imgAnhTheLoai);
            holder.tvTenLoai = convertView.findViewById(R.id.txtTenLoai);
            holder.btnEdit = convertView.findViewById(R.id.btnEdit);
            holder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        TheLoai theLoai = list.get(position);
        holder.tvTenLoai.setText(theLoai.getTenTL());
        Picasso
                .get()
                .load(theLoai.getImgURL())
                .error(R.drawable.loihinhanh)
                .into(holder.imgAnhTheLoai);
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
                        if (daoTheLoai.delete(theLoai.getMaTL()) > 0) {
                            list.clear();
                            list.addAll(daoTheLoai.selectAll());
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
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.item_update_theloai, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextInputEditText ed_URL_TheLoai,ed_TenTL;
                Button btnSua, btnHuy;

                ed_URL_TheLoai = view.findViewById(R.id.ed_URL_TheLoai);
                ed_TenTL = view.findViewById(R.id.ed_TenTL);
                btnSua = view.findViewById(R.id.btnSua);
                btnHuy = view.findViewById(R.id.btnHuy);

                ed_URL_TheLoai.setText(theLoai.getImgURL());
                ed_TenTL.setText(theLoai.getTenTL());
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = ed_URL_TheLoai.getText().toString();
                        String tenTL = ed_TenTL.getText().toString();

                        if(url.isEmpty()||tenTL.isEmpty()){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        theLoai.setImgURL(url);
                        theLoai.setTenTL(tenTL);

                        if (daoTheLoai.update(theLoai)){
                            list.clear();
                            list.addAll(daoTheLoai.selectAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
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
        });


        return convertView;
    }
    static class ViewHolder{
        ImageView imgAnhTheLoai;
        TextView tvTenLoai;
        ImageView btnEdit, btnDelete;
    }
}
