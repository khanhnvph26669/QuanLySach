package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.BookManagerDAO;
import vn.edu.spx.quanlysach.DAO.HoaDonChiTietDAO;
import vn.edu.spx.quanlysach.DAO.HoaDonDAO;
import vn.edu.spx.quanlysach.model.BookManager;
import vn.edu.spx.quanlysach.model.HoaDon;
import vn.edu.spx.quanlysach.model.HoaDonChiTiet;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    BookManagerDAO sachDAO;
    HoaDonDAO hdDAO;
    List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    List<BookManager> dsBM = new ArrayList<>();
    List<HoaDon> dsHD = new ArrayList<>();
    ListView lvCart;
    HDCTadapter adapter;
    double thanhTien = 0;
    int a=-1;
    int b=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        //Chi tiết hóa đơn
        edMaSach =  findViewById(R.id.edMaSachHDCT);
        edMaHoaDon =  findViewById(R.id.edMaHoaDonHDCT);
        edSoLuong =  findViewById(R.id.edSoLuongMua);
        lvCart =  findViewById(R.id.lvCart);
        tvThanhTien =  findViewById(R.id.tvThanhTien);

        sachDAO = new BookManagerDAO(getApplicationContext());
        dsBM=sachDAO.getAllBook();
        hdDAO=new HoaDonDAO(getApplicationContext());
        dsHD=hdDAO.getAllHoaDon();
        hoaDonChiTietDAO=new HoaDonChiTietDAO(getApplicationContext());
        dsHDCT=hoaDonChiTietDAO.getAllHDCT();
        adapter = new HDCTadapter(dsHDCT,hoaDonChiTietDAO, getApplicationContext());
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }

    public void ADDHoaDonCHITIET(View view) {
        for (int j=0;j<dsHD.size();j++)
        {
            if(edMaHoaDon.getText().toString().equals(dsHD.get(j).getMaHoaDon()))
            {
                b=1;
            }
        }

        for (int i = 0; i < dsBM.size(); i++) {
            BookManager hd = dsBM.get(i);
            if (hd.getMaSach().equals(edMaSach.getText().toString())) {
                a=i;
            }
        }
        if (edMaHoaDon.getText().toString().isEmpty() || edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty()) {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }else if (b==1) {
            HoaDonChiTiet ct = new HoaDonChiTiet(Integer.parseInt(edSoLuong.getText().toString()), edMaHoaDon.getText().toString(), edMaSach.getText().toString(), dsBM.get(a).getGiaBIA());
            hoaDonChiTietDAO.inserHoaDonChiTiet(ct);

            hoaDonChiTietDAO = new HoaDonChiTietDAO(getApplicationContext());
            dsHDCT = hoaDonChiTietDAO.getAllHDCT();
            adapter = new HDCTadapter(dsHDCT, hoaDonChiTietDAO, getApplicationContext());
            lvCart.setAdapter(adapter);
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        dsHDCT = hoaDonChiTietDAO.getAllHDCT();
    //tinh tien
        thanhTien = 0;
        try {
            for (int i=0;i<dsHDCT.size();i++) {
                thanhTien = thanhTien + (dsHDCT.get(i).getGia()) * (dsHDCT.get(i).getSoLuong());
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}