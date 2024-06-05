package com.example.duan1_quanlyrapphim.model;

public class TheLoai {
    private int maTL;
    private String imgURL;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(String imgURL, String tenTheLoai) {
        this.imgURL = imgURL;
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai(int maTL, String imgURL, String tenTheLoai) {
        this.maTL = maTL;
        this.imgURL = imgURL;
        this.tenTheLoai = tenTheLoai;
    }

    public int getMaTL() {
        return maTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTenTL() {
        return tenTheLoai;
    }

    public void setTenTL(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
