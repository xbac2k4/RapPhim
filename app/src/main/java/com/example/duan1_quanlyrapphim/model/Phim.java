package com.example.duan1_quanlyrapphim.model;

public class Phim {
    private int maPhim;
    private String imgPhim;
    private String tenPhim;
    private String moTa;
    private int giaVe;
    private String khoiChieu;
    private int trangThai;
    private int maTheLoai;

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    private String tenTheLoai;

    public Phim() {
    }

    public Phim(String imgPhim, String tenPhim, String moTa, int giaVe, String khoiChieu, int trangThai, int maTheLoai) {
        this.imgPhim = imgPhim;
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.giaVe = giaVe;
        this.khoiChieu = khoiChieu;
        this.trangThai = trangThai;
        this.maTheLoai = maTheLoai;
    }

    public Phim(String imgPhim, String tenPhim) {
        this.imgPhim = imgPhim;
        this.tenPhim = tenPhim;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getImgPhim() {
        return imgPhim;
    }

    public void setImgPhim(String imgPhim) {
        this.imgPhim = imgPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }

    public String getKhoiChieu() {
        return khoiChieu;
    }

    public void setKhoiChieu(String khoiChieu) {
        this.khoiChieu = khoiChieu;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
}
