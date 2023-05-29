package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.HoaDonDAO;
import vn.edu.spx.quanlysach.model.HoaDon;

public class HoaDonActivity extends AppCompatActivity {
    EditText edMaHD, edNgayHD;
    List<HoaDon> list;
    HoaDonDAO dao;
    Button btnPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("Thêm hóa đơn");
        edMaHD = findViewById(R.id.edMaHoaDonHDCT);
        edNgayHD = findViewById(R.id.edNgayHoaDon);
        list = new ArrayList<>();
        dao = new HoaDonDAO(this);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HoaDonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edNgayHD.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void themHD(View view) {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(edMaHD.getText().toString());
        hd.setNgay(edNgayHD.getText().toString());
        list.add(hd);
        if (dao.insertHoaDon(hd) == 1) {
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
        }
    }

    public void showHD(View view) {
        Intent intent=new Intent(HoaDonActivity.this,ListHoaDonActivity.class);
        startActivity(intent);
    }
}