package com.example.duan1_quanlyrapphim.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TheLoai;

import java.util.ArrayList;

public class daoPhim {
    private final dbHelper dbHelper;

    public daoPhim(Context context) {
        dbHelper = new dbHelper(context);
    }

    public ArrayList<Phim> selectAll() {
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM phim INNER JOIN theloai ON phim.matheloai=theloai.matheloai WHERE phim.trangthai=0", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Phim phim = new Phim();
                    phim.setMaPhim(cursor.getInt(0));
                    phim.setImgPhim(cursor.getString(1));
                    phim.setTenPhim(cursor.getString(2));
                    phim.setMoTa(cursor.getString(3));
                    phim.setGiaVe(cursor.getInt(4));
                    phim.setKhoiChieu(cursor.getString(5));
                    phim.setTrangThai(cursor.getInt(6));
                    phim.setMaTheLoai(cursor.getInt(7));
                    phim.setTenTheLoai(cursor.getString(10));
                    list.add(phim);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }

    public ArrayList<Phim> selectPhimTheoTheLoai(String maTheLoai) {
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM phim INNER JOIN theloai ON phim.matheloai=theloai.matheloai WHERE phim.matheloai=? AND phim.trangthai=0", new String[]{String.valueOf(maTheLoai)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Phim phim = new Phim();
                    phim.setMaPhim(cursor.getInt(0));
                    phim.setImgPhim(cursor.getString(1));
                    phim.setTenPhim(cursor.getString(2));
                    phim.setMoTa(cursor.getString(3));
                    phim.setGiaVe(cursor.getInt(4));
                    phim.setKhoiChieu(cursor.getString(5));
                    phim.setTrangThai(cursor.getInt(6));
                    phim.setMaTheLoai(cursor.getInt(7));
                    phim.setTenTheLoai(cursor.getString(10));
                    list.add(phim);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }

    public boolean insert(Phim phim) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maphim", phim.getMaPhim());
        values.put("imgphim", phim.getImgPhim());
        values.put("tenphim", phim.getTenPhim());
        values.put("mota", phim.getMoTa());
        values.put("giave", phim.getGiaVe());
        values.put("khoichieu", phim.getKhoiChieu());
        values.put("trangthai", phim.getTrangThai());
        values.put("matheloai", phim.getMaTheLoai());
        long row = db.insert("phim", null, values);
        return (row > 0);
    }

    public boolean update(Phim phim) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maphim", phim.getMaPhim());
        values.put("imgphim", phim.getImgPhim());
        values.put("tenphim", phim.getTenPhim());
        values.put("mota", phim.getMoTa());
        values.put("giave", phim.getGiaVe());
        values.put("khoichieu", phim.getKhoiChieu());
        values.put("matheloai", phim.getMaTheLoai());
        long row = db.update("phim", values, "maphim = ?", new String[]{String.valueOf(phim.getMaPhim())});
        return (row > 0);
    }

    String tenLoai;

    public String getTenTheLoai(String maTheLoai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT tentheloai FROM phim INNER JOIN theloai ON phim.matheloai=theloai.matheloai WHERE phim.matheloai=?", new String[]{String.valueOf(maTheLoai)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tenLoai = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tenLoai;
    }

    public int DeleteRow(Phim phim) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai", 1);

        String[] index = new String[]{
                String.valueOf(phim.getMaPhim())

        };
        return db.update("phim", values, "maphim=?", index);
    }

    String tenPhim;

    public String getTenPhim(String maPhim) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT tenphim FROM phim WHERE phim.maphim=?", new String[]{String.valueOf(maPhim)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tenPhim = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tenPhim;
    }

    public int getMaPhim(String tenPhim) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int ma = 0;
        try {
            Cursor cursor = db.rawQuery("SELECT maphim FROM phim WHERE phim.tenphim=?", new String[]{tenPhim});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ma = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return ma;
    }

    String giaVe;

    public String getGiaVe(String maPhim) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT giave FROM phim WHERE phim.maphim=?", new String[]{String.valueOf(maPhim)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    giaVe = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return giaVe;
    }
    public ArrayList<Phim> selectAllPhim(String maPHim) {
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM phim INNER JOIN theloai ON phim.matheloai=theloai.matheloai WHERE phim.trangthai=0 AND phim.maphim=?", new String[]{maPHim});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Phim phim = new Phim();
                    phim.setMaPhim(cursor.getInt(0));
                    phim.setImgPhim(cursor.getString(1));
                    phim.setTenPhim(cursor.getString(2));
                    phim.setMoTa(cursor.getString(3));
                    phim.setGiaVe(cursor.getInt(4));
                    phim.setKhoiChieu(cursor.getString(5));
                    phim.setTrangThai(cursor.getInt(6));
                    phim.setMaTheLoai(cursor.getInt(7));
                    phim.setTenTheLoai(cursor.getString(10));
                    list.add(phim);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
}
