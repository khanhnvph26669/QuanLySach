package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void viewNguoiDung(View view) {
        Intent intent=new Intent(getApplicationContext(),ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewTheLoai(View view) {
        Intent intent=new Intent(getApplicationContext(),ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void viewListBookActivity(View view) {
        Intent intent=new Intent(getApplicationContext(),ListBookManagerActivity.class);
        startActivity(intent);
    }

    public void ViewListHoaDonActivity(View view) {
        Intent intent=new Intent(getApplicationContext(),ListHoaDonActivity.class);
        startActivity(intent);
    }

    public void ViewTopSach(View view) {
        Intent intent=new Intent(getApplicationContext(),TopSachActivity.class);
        startActivity(intent);
    }

    public void ViewThongKeActivity(View view) {
        Intent intent=new Intent(getApplicationContext(),ThongKeActivity.class);
        startActivity(intent);
    }

    public void viewHDCT(View view) {
        Intent intent=new Intent(getApplicationContext(), HoaDonChiTietActivity.class);
        startActivity(intent);
    }
}