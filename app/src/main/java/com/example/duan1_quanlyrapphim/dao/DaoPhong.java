package com.example.duan1_quanlyrapphim.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.Phong;

import java.util.ArrayList;

public class DaoPhong {
    dbHelper dbHelper;
    SQLiteDatabase db;

    public DaoPhong(Context context) {
        this.dbHelper = new dbHelper(context);
    }

    public ArrayList<Phong> selectAll() {
        ArrayList<Phong> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM phong";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Phong phong = new Phong();
                phong.setMaPhong(cursor.getInt(0));
                phong.setSoPhong(cursor.getString(1));
                list.add(phong);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    @SuppressLint("Range")
    public String laySoPhongBangMa(int maPhong) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT sophong FROM phong WHERE maphong = ?";
        String sophong = null;

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maPhong)});

        if (cursor.moveToFirst()) {
            sophong = cursor.getString(cursor.getColumnIndex("sophong"));
        }

        cursor.close();
        db.close();

        return sophong;
    }

    @SuppressLint("Range")
    public int layMaBangSoPhong(String soPhong) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT maphong FROM phong WHERE sophong = ?";
        int id = 0;

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(soPhong)});

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("maphong"));
        }

        cursor.close();
        db.close();

        return id;
    }

}
