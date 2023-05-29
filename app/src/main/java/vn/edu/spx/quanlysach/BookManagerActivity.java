package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.BookManagerDAO;
import vn.edu.spx.quanlysach.DAO.TheLoaiDAO;
import vn.edu.spx.quanlysach.model.BookManager;
import vn.edu.spx.quanlysach.model.TheLoai;

public class BookManagerActivity extends AppCompatActivity {
    EditText edMaSach, edTenSach, edTacGia, edSoLuong,NXB,giaBia;
    Spinner spinnerTL;
    List<BookManager> list ;
    BookManagerDAO dao;
    List<String> listMaTL;
    TheLoaiDAO dao1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        setTitle("Thêm Book");

        edMaSach =  findViewById(R.id.edMaSachHDCT);
        edTenSach =  findViewById(R.id.edTenSach);
        edTacGia =  findViewById(R.id.edTacGia);
        edSoLuong =  findViewById(R.id.edSoLuong);
        NXB =  findViewById(R.id.edNXB);
        giaBia =  findViewById(R.id.edGiaBia);
        spinnerTL=findViewById(R.id.spinnerTheLoai);
        dao=new BookManagerDAO(getApplicationContext());
        dao1=new TheLoaiDAO(getApplicationContext());
        list= new ArrayList<>();
        list=dao.getAllBook();
        listMaTL=dao1.getAllMaTheLoai();

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listMaTL);
        spinnerTL.setAdapter(dataAdapter1);
    }

    public void addSach(View view) {
        if (edMaSach.getText().toString().isEmpty() || spinnerTL.getSelectedItem().toString().isEmpty() || edTenSach.getText().toString().isEmpty() || edTacGia.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            BookManager bm = new BookManager();
            bm.setMaSach(edMaSach.getText().toString());
            bm.setTheLoai(spinnerTL.getSelectedItem().toString());
            bm.setTenSach(edTenSach.getText().toString());
            bm.setTacGia(edTacGia.getText().toString());
            bm.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
            bm.setNXB(NXB.getText().toString());
            bm.setGiaBIA(Integer.parseInt(giaBia.getText().toString()));
            list.add(bm);
            if (dao.insertBook(bm) == 1) {
                Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cancelSach(View view) {
    }

    public void showSach(View view) {
        Intent intent=new Intent(BookManagerActivity.this,ListBookManagerActivity.class);
        startActivity(intent);
    }
}