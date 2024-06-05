package com.example.duan1_quanlyrapphim.model;

public class LichChieu {
    private int maLichChieu;
    private int maPhong;
    private int maKhungGio;
    private int maPhim;
    private String ngayChieu;
    private String tenPhim;
    private String khungGio;

    public LichChieu() {
    }

    public LichChieu(int maPhong, int maKhungGio, int maPhim, String ngayChieu) {
        this.maPhong = maPhong;
        this.maKhungGio = maKhungGio;
        this.maPhim = maPhim;
        this.ngayChieu = ngayChieu;
    }

    public LichChieu(int maPhong, int maKhungGio, int maPhim, String ngayChieu, String tenPhim, String khungGio) {
        this.maPhong = maPhong;
        this.maKhungGio = maKhungGio;
        this.maPhim = maPhim;
        this.ngayChieu = ngayChieu;
        this.tenPhim = tenPhim;
        this.khungGio = khungGio;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(String khungGio) {
        this.khungGio = khungGio;
    }

    public int getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(int maLichChieu) {
        this.maLichChieu = maLichChieu;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaKhungGio() {
        return maKhungGio;
    }

    public void setMaKhungGio(int maKhungGio) {
        this.maKhungGio = maKhungGio;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }
}
