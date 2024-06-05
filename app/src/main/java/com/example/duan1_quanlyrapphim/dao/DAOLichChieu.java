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
import com.example.duan1_quanlyrapphim.model.Phong;

import java.util.ArrayList;

public class DAOLichChieu {
    private final dbHelper dbHelper;
    SQLiteDatabase db;
    daoPhim daoPhim;
    DaoKhungGio daoKhungGio;

    public DAOLichChieu(Context context) {
        dbHelper = new dbHelper(context);
        daoPhim = new daoPhim(context);
        daoKhungGio = new DaoKhungGio(context);

    }

    public ArrayList<LichChieu> selectAll(String maPhim) {
        ArrayList<LichChieu> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM lichchieu " +
                    "INNER JOIN phong ON lichchieu.maphong = phong.maphong " +
                    "INNER JOIN khunggio ON lichchieu.makhunggio = khunggio.makhunggio " +
                    "INNER JOIN phim ON lichchieu.maphim = phim.maphim " +
                    "WHERE phim.maphim=?", new String[]{maPhim});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LichChieu lichChieu = new LichChieu();
                    lichChieu.setMaLichChieu(cursor.getInt(0));
                    lichChieu.setNgayChieu(cursor.getString(1));
                    lichChieu.setMaPhong(cursor.getInt(2));
                    lichChieu.setMaKhungGio(cursor.getInt(3));
                    lichChieu.setMaPhim(cursor.getInt(4));
                    lichChieu.setKhungGio(cursor.getString(8));
                    lichChieu.setTenPhim(cursor.getString(11));
                    list.add(lichChieu);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }

    public ArrayList<LichChieu> KhungGio(String maPhim, String ngay) {
        ArrayList<LichChieu> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT khunggio, malichchieu FROM lichchieu INNER JOIN phong ON lichchieu.maphong = phong.maphong INNER JOIN khunggio ON lichchieu.makhunggio = khunggio.makhunggio INNER JOIN phim ON lichchieu.maphim = phim.maphim WHERE phim.maphim=? AND lichchieu.ngaychieu=?", new String[]{maPhim, ngay});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LichChieu lichChieu = new LichChieu();
                    lichChieu.setKhungGio(cursor.getString(0));
                    lichChieu.setMaLichChieu(cursor.getInt(1));
                    list.add(lichChieu);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }

    String phong;

    public String getPhong(String maPhim, String maLichChieu) {
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT sophong FROM lichchieu INNER JOIN phong ON lichchieu.maphong = phong.maphong INNER JOIN khunggio ON lichchieu.makhunggio = khunggio.makhunggio INNER JOIN phim ON lichchieu.maphim = phim.maphim WHERE phim.maphim=? AND lichchieu.malichchieu=?", new String[]{maPhim, maLichChieu});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    phong = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return phong;
    }

    String gioChieu;

    public String getGioChieu(String maPhim, String maLichChieu) {
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT khunggio FROM lichchieu INNER JOIN phong ON lichchieu.maphong = phong.maphong INNER JOIN khunggio ON lichchieu.makhunggio = khunggio.makhunggio INNER JOIN phim ON lichchieu.maphim = phim.maphim WHERE phim.maphim=? AND lichchieu.malichchieu=?", new String[]{maPhim, maLichChieu});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    gioChieu = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return gioChieu;
    }

    public ArrayList<LichChieu> getData() {
        ArrayList<LichChieu> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM lichchieu " +
                    "INNER JOIN phong ON lichchieu.maphong = phong.maphong " +
                    "INNER JOIN khunggio ON lichchieu.makhunggio = khunggio.makhunggio " +
                    "INNER JOIN phim ON lichchieu.maphim = phim.maphim", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LichChieu lichChieu = new LichChieu();
                    lichChieu.setMaLichChieu(cursor.getInt(0));
                    lichChieu.setNgayChieu(cursor.getString(1));
                    lichChieu.setMaPhong(cursor.getInt(2));
                    lichChieu.setMaKhungGio(cursor.getInt(3));
                    lichChieu.setMaPhim(cursor.getInt(4));
                    lichChieu.setKhungGio(cursor.getString(8));
                    lichChieu.setTenPhim(cursor.getString(11));
                    list.add(lichChieu);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }

    public boolean insert(LichChieu lichChieu) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("malichchieu", lichChieu.getMaLichChieu());
        values.put("ngaychieu", lichChieu.getNgayChieu());
        values.put("maphong", lichChieu.getMaPhong());
        values.put("makhunggio", lichChieu.getMaKhungGio());
        values.put("maphim", lichChieu.getMaPhim());

        long row = db.insert("lichchieu", null, values);
        return row > 0;
    }

    public boolean update(LichChieu lichChieu) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("malichchieu", lichChieu.getMaLichChieu());
        values.put("ngaychieu", lichChieu.getNgayChieu());
        values.put("maphong", lichChieu.getMaPhong());
        values.put("makhunggio", lichChieu.getMaKhungGio());
        values.put("maphim", lichChieu.getMaPhim());

        long row = db.update("lichchieu", values, "malichchieu=?", new String[]{String.valueOf(lichChieu.getMaLichChieu())});
        return row > 0;
    }

    public boolean delete(int malichchieu) {
        db = dbHelper.getWritableDatabase();
        long check = db.delete("lichchieu", "malichchieu=?", new String[]{String.valueOf(malichchieu)});
        return check>0;
    }

    @SuppressLint("Range")
    public ArrayList<LichChieu> selectAll() {
        ArrayList<LichChieu> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM lichchieu";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            LichChieu lichChieu = new LichChieu();
            lichChieu.setMaLichChieu(cursor.getInt(cursor.getColumnIndex("malichchieu")));
            lichChieu.setNgayChieu(cursor.getString(cursor.getColumnIndex("ngaychieu")));
            lichChieu.setMaPhong(cursor.getInt(cursor.getColumnIndex("maphong")));
            lichChieu.setMaKhungGio(cursor.getInt(cursor.getColumnIndex("makhunggio")));
            lichChieu.setMaPhim(cursor.getInt(cursor.getColumnIndex("maphim")));
            lichChieu.setTenPhim(daoPhim.getTenPhim(cursor.getString(cursor.getColumnIndex("maphim"))));
            lichChieu.setKhungGio(daoKhungGio.getKhungGioByMa(cursor.getInt(cursor.getColumnIndex("makhunggio"))));
            list.add(lichChieu);
        }

        cursor.close();
        db.close();
        return list;
    }
    int rowMaLC;
    public boolean checkMaLC(String maLC) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT malichchieu FROM lichchieu WHERE lichchieu.malichchieu = ?", new String[] {String.valueOf(maLC)});
            rowMaLC = cursor.getCount();
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return (rowMaLC <= 0) ? true : false;
    }
    public boolean checkLC(String ngayChieu, String Phong, String khungGio) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM lichchieu WHERE lichchieu.ngaychieu=? AND lichchieu.maphong=? AND lichchieu.makhunggio=?", new String[] {ngayChieu, Phong, khungGio});
        long row = cursor.getCount();
        return (row > 0) ? true : false;
    }
}
