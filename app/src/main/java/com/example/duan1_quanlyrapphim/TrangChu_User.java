package com.example.duan1_quanlyrapphim;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duan1_quanlyrapphim.fragment.fragment_taikhoan;
import com.example.duan1_quanlyrapphim.fragment.fragment_trangchu;
import com.example.duan1_quanlyrapphim.fragment.fragment_vecuatoi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TrangChu_User extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_user);
        // set hiển thị frgTrangChu
        fragment_trangchu fragmentTrangchu = new fragment_trangchu();
        replaceFrg(fragmentTrangchu);
        //
        //
        bottomNavigationView = findViewById(R.id.nav_bottom_bar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_trang_chu) {
                    fragment = new fragment_trangchu();
                    replaceFrg(fragment);
//                    Toast.makeText(TrangChu_User.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_lich_su) {
                    fragment = new fragment_vecuatoi();
                    replaceFrg(fragment);
//                    Toast.makeText(TrangChu_User.this, "Vé của tôi", Toast.LENGTH_SHORT).show();
                }  else if (item.getItemId() == R.id.nav_tai_khoan) {
                    fragment = new fragment_taikhoan();
                    replaceFrg(fragment);
//                    Toast.makeText(TrangChu_User.this, "Tài khoản", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, frg).commit();
    }
    public String getMaTK() {
        return getIntent().getStringExtra("matk");
    }
}