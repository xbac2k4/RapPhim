package com.example.duan1_quanlyrapphim.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.KhungGio;
import com.example.duan1_quanlyrapphim.model.Phong;

import java.util.ArrayList;

public class DaoKhungGio {
    private final com.example.duan1_quanlyrapphim.database.dbHelper dbHelper;
    SQLiteDatabase db;

    public DaoKhungGio(Context context) {
        dbHelper = new dbHelper(context);
    }

    public ArrayList<KhungGio> selectAll() {
        ArrayList<KhungGio> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM khunggio";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                KhungGio khungGio = new KhungGio(
                        cursor.getInt(0),
                        cursor.getString(1));
                list.add(khungGio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    @SuppressLint("Range")
    public int getMaKhungGio(String gio) {
        db = dbHelper.getReadableDatabase();
        int ma = 0;
        String query = "SELECT makhunggio FROM khunggio WHERE khunggio = ?";


        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(gio)});
        if (cursor.moveToFirst()) {
            ma = cursor.getInt(cursor.getColumnIndex("makhunggio"));
        }

        cursor.close();
        db.close();
        return ma;
    }

    @SuppressLint("Range")
    public String getKhungGioByMa(int maKhungGio) {
        db = dbHelper.getReadableDatabase();
        String khungGio = null;
        String query = "SELECT khunggio FROM khunggio WHERE makhunggio = ?";


        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maKhungGio)});
        if (cursor.moveToFirst()){
            khungGio = cursor.getString(cursor.getColumnIndex("khunggio"));
        }

        cursor.close();
        db.close();

        return khungGio;
    }

}
