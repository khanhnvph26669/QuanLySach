package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.spx.quanlysach.DAO.TheLoaiDAO;
import vn.edu.spx.quanlysach.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    EditText edMaTL, edTenTL, edViTri, edMoTa;
    ArrayList<TheLoai> list ;
    TheLoaiDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("Thêm thể loại");
        edMaTL = (EditText) findViewById(R.id.edMaTheLoai);
        edTenTL = (EditText) findViewById(R.id.edTenTheLoai);
        edViTri = (EditText) findViewById(R.id.edViTri);
        edMoTa = (EditText) findViewById(R.id.edMoTa);
        dao=new TheLoaiDAO(getApplicationContext());
        list= new ArrayList<>();
    }

    public void addTheLoai(View view) {
        if (edMaTL.getText().toString().isEmpty() || edTenTL.getText().toString().isEmpty() || edViTri.getText().toString().isEmpty() || edMoTa.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            TheLoai tl = new TheLoai();
            tl.setMaTheLoai(edMaTL.getText().toString());
            tl.setTenTheLoai(edTenTL.getText().toString());
            tl.setViTri(Integer.parseInt(edViTri.getText().toString()));
            tl.setMoTa(edMoTa.getText().toString());
            list.add(tl);
            if (dao.insertTheLoai(tl) == 1) {
                Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showTheLoai(View view) {
//        Intent intent=new Intent(TheLoaiActivity.this,ListTheLoaiActivity.class);
//        startActivity(intent);
        finish();
    }

    public void cancelTL(View view) {
        edMaTL.setText("");
        edTenTL.setText("");
        edViTri.setText("");
        edMoTa.setText("");
    }
}