package com.example.duan1_quanlyrapphim.model;

public class soGhe {
    private int maGhe;
    private int SoGhe;
    private int trangThai;
    private int maLichChieu;

    public soGhe() {
    }

    public soGhe(int soGhe, int trangThai, int maLichChieu) {
        SoGhe = soGhe;
        this.trangThai = trangThai;
        this.maLichChieu = maLichChieu;
    }

    public int getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(int maGhe) {
        this.maGhe = maGhe;
    }

    public int getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(int soGhe) {
        SoGhe = soGhe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(int maLichChieu) {
        this.maLichChieu = maLichChieu;
    }
}
