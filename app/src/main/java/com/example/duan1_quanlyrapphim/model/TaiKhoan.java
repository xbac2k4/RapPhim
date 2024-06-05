package com.example.duan1_quanlyrapphim.model;

public class TaiKhoan {
    private int maTK;
    private String tenNguoiDung;
    private String email;
    private String matKhau;
    private int gioiTinh;
    private String ngaySinh;
    private String soDienThoai;
    private int vaiTro;
    private int soDu;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenNguoiDung, String email, String matKhau, int vaiTro) {
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public TaiKhoan(String tenNguoiDung, String email, String matKhau, int gioiTinh, String ngaySinh, String soDienThoai, int soDu, int vaiTro) {
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.soDu = soDu;
        this.vaiTro = vaiTro;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }
}
