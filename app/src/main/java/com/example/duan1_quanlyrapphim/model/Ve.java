package com.example.duan1_quanlyrapphim.model;

public class Ve {
    private int maVe;
    private int maTK;
    private int maPhim;
    private int trangThaiThanhToan;

    public Ve(int maVe, int maTK, int maPhim, int trangThaiThanhToan) {
        this.maVe = maVe;
        this.maTK = maTK;
        this.maPhim = maPhim;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Ve() {
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }
}
