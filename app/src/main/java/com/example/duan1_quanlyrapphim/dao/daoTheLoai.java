package com.example.duan1_quanlyrapphim.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.TaiKhoan;
import com.example.duan1_quanlyrapphim.model.TheLoai;

import java.util.ArrayList;

public class daoTheLoai {
    private final dbHelper dbHelper;
    SQLiteDatabase db;

    public daoTheLoai(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<TheLoai> selectAll() {
        ArrayList<TheLoai> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM theloai", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TheLoai theloai = new TheLoai();
                    theloai.setMaTL(cursor.getInt(0));
                    theloai.setImgURL(cursor.getString(1));
                    theloai.setTenTL(cursor.getString(2));
                    list.add(theloai);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(TheLoai tl) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imgtheloai", tl.getImgURL());
        values.put("tentheloai", tl.getTenTL());
        long row = db.insert("theloai", null, values);
        return (row > 0);
    }
    public boolean update(TheLoai tl) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matheloai", tl.getMaTL());
        values.put("imgtheloai", tl.getImgURL());
        values.put("tentheloai", tl.getTenTL());
        long row = db.update("theloai", values, "matheloai = ?", new String[]{String.valueOf(tl.getMaTL())});
        return (row > 0);
    }

    public long delete(int id) {
        db = dbHelper.getWritableDatabase();
        long check = db.delete("theloai", "matheloai=?", new String[]{String.valueOf(id)});
        return check;
    }
    int row;
    public int getMaLoai(String tenLoai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT matheloai FROM theloai WHERE theloai.tentheloai = ?", new String[] {tenLoai});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    row = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return row;
    }
}
