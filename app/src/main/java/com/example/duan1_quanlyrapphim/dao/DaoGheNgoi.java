package com.example.duan1_quanlyrapphim.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.LichChieu;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.soGhe;

import java.util.ArrayList;

public class DaoGheNgoi {
    private final dbHelper dbHelper;

    public DaoGheNgoi(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<soGhe> selectAll(String maLichChieu) {
        ArrayList<soGhe> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM ghe INNER JOIN lichchieu ON ghe.malichchieu = lichchieu.malichchieu WHERE ghe.malichchieu=?", new String[]{maLichChieu});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    soGhe soGhe = new soGhe();
                    soGhe.setMaGhe(cursor.getInt(0));
                    soGhe.setSoGhe(cursor.getInt(1));
                    soGhe.setTrangThai(cursor.getInt(2));
                    soGhe.setMaLichChieu(cursor.getInt(3));
                    list.add(soGhe);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    int gheDaChon;
    @SuppressLint("Range")
    public int gheDaChon(String maLichChieu, int trangThai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT soghe FROM ghe INNER JOIN lichchieu ON ghe.malichchieu = lichchieu.malichchieu WHERE ghe.malichchieu=? AND ghe.trangthai=?", new String[]{maLichChieu,String.valueOf(trangThai)});
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    gheDaChon = cursor.getInt(cursor.getColumnIndex("soghe"));
//                    cursor.moveToNext();
//                }
//            }
            if (cursor.moveToFirst()) {
                gheDaChon = cursor.getInt(cursor.getColumnIndex("soghe"));
            }
            cursor.close();
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return gheDaChon;
    }
    public boolean insert(soGhe soGhe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soghe", soGhe.getSoGhe());
        values.put("trangthai", soGhe.getTrangThai());
        values.put("malichchieu", soGhe.getMaLichChieu());
        long row = db.insert("ghe", null, values);
        return row > 0;
    }
    public int UpdateTT(soGhe soGhe, int trangThai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai", trangThai);

        String[] index = new String[]{
                String.valueOf(soGhe.getMaGhe())
        };
        return db.update("ghe", values, "maghe=?", index);
    }
    @SuppressLint("Range")
    public ArrayList<soGhe> gheDaChon2(String maLichChieu, int trangThai) {
        ArrayList<soGhe> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM ghe INNER JOIN lichchieu ON ghe.malichchieu = lichchieu.malichchieu WHERE ghe.malichchieu=? AND ghe.trangthai=?", new String[]{maLichChieu,String.valueOf(trangThai)});
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    gheDaChon = cursor.getInt(cursor.getColumnIndex("soghe"));
//                    cursor.moveToNext();
//                }
//            }
            if (cursor.moveToFirst()) {
                soGhe soGhe = new soGhe();
                soGhe.setMaGhe(cursor.getInt(0));
                soGhe.setSoGhe(cursor.getInt(1));
                soGhe.setTrangThai(cursor.getInt(2));
                soGhe.setMaLichChieu(cursor.getInt(3));
                list.add(soGhe);
                cursor.moveToNext();
            }
            cursor.close();

        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
}
