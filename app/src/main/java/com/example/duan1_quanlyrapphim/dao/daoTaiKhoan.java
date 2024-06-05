package com.example.duan1_quanlyrapphim.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_quanlyrapphim.database.dbHelper;
import com.example.duan1_quanlyrapphim.model.Phim;
import com.example.duan1_quanlyrapphim.model.TaiKhoan;

import java.util.ArrayList;

public class daoTaiKhoan {
    private final dbHelper dbHelper;

    public daoTaiKhoan(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<TaiKhoan> selectAll() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM account WHERE account.vaitro=1", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setMaTK(cursor.getInt(0));
                    taiKhoan.setTenNguoiDung(cursor.getString(1));
                    taiKhoan.setEmail(cursor.getString(2));
                    taiKhoan.setMatKhau(cursor.getString(3));
                    taiKhoan.setGioiTinh(cursor.getInt(4));
                    taiKhoan.setNgaySinh(cursor.getString(5));
                    taiKhoan.setSoDienThoai(cursor.getString(6));
                    taiKhoan.setSoDu(cursor.getInt(7));
                    taiKhoan.setVaiTro(cursor.getInt(8));
                    list.add(taiKhoan);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(TaiKhoan tk) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tennguoidung", tk.getTenNguoiDung());
        values.put("email", tk.getEmail());
        values.put("matkhau", tk.getMatKhau());
        values.put("ngaysinh", tk.getNgaySinh());
        values.put("gioitinh", tk.getGioiTinh());
        values.put("sodienthoai", tk.getSoDienThoai());;
        values.put("sodu", tk.getSoDu());
        values.put("vaitro", tk.getVaiTro());
        long row = db.insert("account", null, values);
        return (row > 0);
    }
    public boolean update(TaiKhoan tk) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tennguoidung", tk.getTenNguoiDung());
        values.put("email", tk.getEmail());
        values.put("matkhau", tk.getMatKhau());
        values.put("ngaysinh", tk.getNgaySinh());
        values.put("gioitinh", tk.getGioiTinh());
        values.put("sodienthoai", tk.getSoDienThoai());
        values.put("sodu", tk.getSoDu());
        values.put("vaitro", tk.getVaiTro());
        long row = db.update("account", values, "matk = ?", new String[]{String.valueOf(tk.getMaTK())});
        return (row > 0);
    }
    public boolean checklogin(String username, String password, int vaiTro) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM account WHERE email=? and matkhau=? and vaitro=?", new String[] {username, password, String.valueOf(vaiTro)});
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }
    public boolean checkEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM account WHERE email = ?", new String[] {email});
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }
    public int getMaTK(String username, int vaiTro) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int matk = 0;
        try {
            Cursor cursor = db.rawQuery("SELECT matk FROM account WHERE email=? and vaitro=?", new String[] {username, String.valueOf(vaiTro)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    matk = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return matk;
    }
    public String getTen(String matk) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String tennguoidung = null;
        try {
            Cursor cursor = db.rawQuery("SELECT tennguoidung FROM account WHERE matk=?", new String[] {matk});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tennguoidung = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tennguoidung;
    }
    public ArrayList<TaiKhoan> selectAll_MaTK(String matk) {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM account WHERE account.vaitro=1 AND account.matk=?", new String[]{matk});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setMaTK(cursor.getInt(0));
                    taiKhoan.setTenNguoiDung(cursor.getString(1));
                    taiKhoan.setEmail(cursor.getString(2));
                    taiKhoan.setMatKhau(cursor.getString(3));
                    taiKhoan.setGioiTinh(cursor.getInt(4));
                    taiKhoan.setNgaySinh(cursor.getString(5));
                    taiKhoan.setSoDienThoai(cursor.getString(6));
                    taiKhoan.setSoDu(cursor.getInt(7));
                    taiKhoan.setVaiTro(cursor.getInt(8));
                    list.add(taiKhoan);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean doiMatKhau(String maTK, String matKhau) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", matKhau);
        long row = db.update("account", values, "matk = ?", new String[]{String.valueOf(maTK)});
        return (row > 0);
    }
    public boolean checkMK(String matk, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM account WHERE matk=? and matkhau=?", new String[] {matk, password});
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }
    public int UpdateVaiTro(TaiKhoan taiKhoan, int vaiTro) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("vaitro", vaiTro);

        String[] index = new String[]{
                String.valueOf(taiKhoan.getMaTK())

        };
        return db.update("account", values, "matk=?", index);
    }
    public ArrayList<TaiKhoan> selectAll_admin() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM account WHERE account.vaitro!=0", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setMaTK(cursor.getInt(0));
                    taiKhoan.setTenNguoiDung(cursor.getString(1));
                    taiKhoan.setEmail(cursor.getString(2));
                    taiKhoan.setMatKhau(cursor.getString(3));
                    taiKhoan.setGioiTinh(cursor.getInt(4));
                    taiKhoan.setNgaySinh(cursor.getString(5));
                    taiKhoan.setSoDienThoai(cursor.getString(6));
                    taiKhoan.setSoDu(cursor.getInt(7));
                    taiKhoan.setVaiTro(cursor.getInt(8));
                    list.add(taiKhoan);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public int getSoDu(String matk) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int sodu = 0;
        try {
            Cursor cursor = db.rawQuery("SELECT sodu FROM account WHERE account.matk=?", new String[]{matk});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    sodu = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return sodu;
    }
    public int UpdateSoDu(String matk, int soDu) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("sodu", soDu);
        return db.update("account", values, "matk=?", new String[]{matk});
    }
}
