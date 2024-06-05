package com.example.duan1_quanlyrapphim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_quanlyrapphim.dao.daoTaiKhoan;

public class Dangnhap extends AppCompatActivity {
    private EditText ed_email, ed_pass;
    private TextView tv_forgetpass, tv_signup;
    daoTaiKhoan daoTaiKhoan;
    private CheckBox chkNhoMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        ed_email = findViewById(R.id.edt_email);
        ed_pass = findViewById(R.id.edt_pass);
        tv_signup = findViewById(R.id.tv_signup);
        chkNhoMatKhau = findViewById(R.id.chk_nho_mat_khau);
        daoTaiKhoan = new daoTaiKhoan(this);
        chkMatKhau();
        tv_signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, Dangky.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                if (daoTaiKhoan.checklogin(email, pass, 1)) {
                    Intent intent = new Intent(Dangnhap.this, TrangChu_User.class);
                    luuMatKhau(email, pass, chkNhoMatKhau.isChecked());
                    intent.putExtra("matk", String.valueOf(daoTaiKhoan.getMaTK(email, 1)));
                    startActivity(intent);
                    Toast.makeText(Dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else if (daoTaiKhoan.checklogin(email, pass, 0)) {
                    Intent intent = new Intent(Dangnhap.this, TrangChu_Admin.class);
                    luuMatKhau(email, pass, chkNhoMatKhau.isChecked());
                    startActivity(intent);
                    Toast.makeText(Dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }  else if (daoTaiKhoan.checklogin(email, pass, 3)) {
                    Toast.makeText(Dangnhap.this, "Tài khoản đã bị khóa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Dangnhap.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void luuMatKhau(String user, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("LuuTaiKhoan.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("User", user);
            editor.putString("Pass", pass);
            editor.putBoolean("Luu", status);
        }
        editor.commit();
    }
    public void chkMatKhau() {
        SharedPreferences sharedPreferences = getSharedPreferences("LuuTaiKhoan.txt", MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("Luu", false);
        if (chk) {
            ed_email.setText(sharedPreferences.getString("User", ""));
            ed_pass.setText(sharedPreferences.getString("Pass", ""));
            chkNhoMatKhau.setChecked(true);
        }
    }
}