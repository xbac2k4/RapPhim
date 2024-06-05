package com.example.duan1_quanlyrapphim.model;

public class KhungGio {
    private int maKhungGio;
    private String khungGio;

    public KhungGio(int maKhungGio, String khungGio) {
        this.maKhungGio = maKhungGio;
        this.khungGio = khungGio;
    }

    public KhungGio() {
    }

    public int getMaKhungGio() {
        return maKhungGio;
    }

    public void setMaKhungGio(int maKhungGio) {
        this.maKhungGio = maKhungGio;
    }

    public String getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(String khungGio) {
        this.khungGio = khungGio;
    }
}
