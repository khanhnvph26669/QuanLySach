package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.HoaDonChiTietDAO;
import vn.edu.spx.quanlysach.DAO.HoaDonDAO;
import vn.edu.spx.quanlysach.model.HoaDon;
import vn.edu.spx.quanlysach.model.HoaDonChiTiet;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvHomNay, tvThangNay, tvNamNay;
    private static final String DATE_FORMAT_day = "dd/MM/yyyy";
    private static final String DATE_FORMAT_month = "MM";
    private static final String DATE_FORMAT_year = "yyyy";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvHomNay = findViewById(R.id.tvHomNay);
        tvThangNay = findViewById(R.id.tvThangNay);
        tvNamNay = findViewById(R.id.tvNamNay);
        Date date = new Date();
        String ngay = getDateString(date, DATE_FORMAT_day);
        String thang = getDateString(date, DATE_FORMAT_month);
        String nam = getDateString(date, DATE_FORMAT_year);
        String regMonth="^[0-9]{2}+/"+thang+"+/+"+nam+"$";
        String regYear="^[0-9]{2}+/+[0-9]{2}+/+"+nam+"$";
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietDAO.getAllHDCT();
        List<HoaDon> hoaDonList = hoaDonDAO.getAllHoaDon();
        List<String> listD = new ArrayList<>();
        for (int i = 0; i < hoaDonList.size(); i++) {
            if(ngay.equals(hoaDonList.get(i).getNgay()))
            {
                listD.add(hoaDonList.get(i).getMaHoaDon());
            }
        }
        int tienHomNay=0;
        for (int i=0;i<hoaDonChiTietList.size();i++){
            for (int j=0;j<listD.size();j++){
                if (listD.get(j).equals(hoaDonChiTietList.get(i).getMaHoaDOn())){
                    tienHomNay=tienHomNay+(hoaDonChiTietList.get(i).getGia())*(hoaDonChiTietList.get(i).getSoLuong());
                }
            }
        }
        List<String> listM = new ArrayList<>();
        for (int i = 0; i < hoaDonList.size(); i++) {
            if(hoaDonList.get(i).getNgay().matches(regMonth))
            {
                listM.add(hoaDonList.get(i).getMaHoaDon());
            }
        }
        int tienThangNay=0;
        for (int i=0;i<hoaDonChiTietList.size();i++){
            for (int j=0;j<listM.size();j++){
                if (listM.get(j).equals(hoaDonChiTietList.get(i).getMaHoaDOn())){
                    tienThangNay=tienThangNay+(hoaDonChiTietList.get(i).getGia())*(hoaDonChiTietList.get(i).getSoLuong());
                }
            }
        }
        List<String> listY = new ArrayList<>();
        for (int i = 0; i < hoaDonList.size(); i++) {
            if(hoaDonList.get(i).getNgay().matches(regYear))
            {
                listY.add(hoaDonList.get(i).getMaHoaDon());
            }
        }
        int tienNamNay=0;
        for (int i=0;i<hoaDonChiTietList.size();i++){
            for (int j=0;j<listY.size();j++){
                if (listY.get(j).equals(hoaDonChiTietList.get(i).getMaHoaDOn())){
                    tienNamNay=tienNamNay+(hoaDonChiTietList.get(i).getGia())*(hoaDonChiTietList.get(i).getSoLuong());
                }
            }
        }
        tvHomNay.setText("Hôm nay: " + tienHomNay+" vnd");
        tvThangNay.setText("Tháng này: " + tienThangNay+" vnd");
        tvNamNay.setText("Năm này: " + tienNamNay+" vnd");
    }

    public static String getDateString(Date date, String fomat) {
        SimpleDateFormat format = new SimpleDateFormat(fomat);
        return format.format(date);
    }
}