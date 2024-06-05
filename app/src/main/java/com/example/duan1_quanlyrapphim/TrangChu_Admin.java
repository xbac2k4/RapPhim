package com.example.duan1_quanlyrapphim;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duan1_quanlyrapphim.fragment.Fragment_DoanhThu;
import com.example.duan1_quanlyrapphim.fragment.fragment_QLLC;
import com.example.duan1_quanlyrapphim.fragment.fragment_QLP;
import com.example.duan1_quanlyrapphim.fragment.fragment_ql_taikhoan;
import com.example.duan1_quanlyrapphim.fragment.fragment_ql_ve;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class TrangChu_Admin extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_admin);
        // set hiển thị frgTrangChu
        Fragment fragmentQLTL = new quanLyTheLoai();
        replaceFrg(fragmentQLTL);
        //
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.nav_bottom_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thể Loại");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menunav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_the_loai) {
                    fragment = new quanLyTheLoai();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_phim) {
                    fragment = new fragment_QLP();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_lich_chieu) {
                    fragment = new fragment_QLLC();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_ve_xem) {
                    fragment = new fragment_ql_ve();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_tai_khoan) {
                    fragment = new fragment_ql_taikhoan();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.item_doanhthu) {
                    fragment = new Fragment_DoanhThu();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_dangxuat) {
                    OpenDialog_DangXuat();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_the_loai) {
                    fragment = new quanLyTheLoai();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_phim) {
                    fragment = new fragment_QLP();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_lich_chieu) {
                    fragment = new fragment_QLLC();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_ve_xem) {
                    fragment = new fragment_ql_ve();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_tai_khoan) {
                    fragment = new fragment_ql_taikhoan();
                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                }
                return false;
            }
        });
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, frg).commit();
    }

    public void OpenDialog_DangXuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("WARNING");
        builder.setMessage("Bạn có muốn đăng xuất không ?");
        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(TrangChu_Admin.this, Dangnhap.class));
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
