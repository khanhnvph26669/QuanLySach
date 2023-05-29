package vn.edu.spx.quanlysach.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.Database.SQLite;
import vn.edu.spx.quanlysach.model.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private SQLite dbHelper;
    public static final String TABLE_NAME = "tb_HoaDonChiTiet";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserHoaDonChiTiet(HoaDonChiTiet hd) {
        ContentValues values = new ContentValues();

        values.put("soLuong", hd.getSoLuong());
        values.put("mahoadon", hd.getMaHoaDOn());
        values.put("maSach", hd.getMaSach());
        values.put("gia",hd.getGia());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("zzzzz", ex.toString());
        }
        return 1;
    }
    public int deleteHoaDonChiTietByID(String maHDCT){
        int result = db.delete(TABLE_NAME,"maHDCT=?",new String[]{maHDCT});
        if (result == 0)
            return -1;
        return 1;
    }
    //getAll
    public List<HoaDonChiTiet> getAllHDCT() {
        List<HoaDonChiTiet> lsHDCT = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null,
                null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            HoaDonChiTiet bm = new HoaDonChiTiet();
            bm.setMaHDCT(c.getInt(0));
            bm.setSoLuong(c.getInt(1));
            bm.setMaHoaDOn(c.getString(2));
            bm.setMaSach(c.getString(3));
            bm.setGia(c.getInt(4));
            lsHDCT.add(bm);
            c.moveToNext();
        }
        c.close();
        return lsHDCT;
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(tb_bookmanager.giaBia * tb_HoaDonChiTiet.soLuong) as 'tongtien' FROM tb_hoadon INNER JOIN tb_HoaDonChiTiet on tb_hoadon.maHoaDon = tb_HoaDonChiTiet.maHoaDon  INNER JOIN tb_bookmanager on tb_HoaDonChiTiet.maSach = tb_bookmanager.maSach where tb_hoadon.ngay = date('now') GROUP BY tb+HoaDonChiTiet.maSach);";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(tb_bookmanager.giaBia * tb_HoaDonChiTiet.soLuong) as 'tongtien' " + "FROM tb_hoadon INNER JOIN tb_HoaDonChiTiet on tb_hoadon.maHoaDon = tb_HoaDonChiTiet.maHoaDon INNER JOIN tb_bookmanager on tb_HoaDonChiTiet.maSach = tb_bookmanager.maSach where strftime('%m',tb_hoadon.ngay) = strftime('%m','now') GROUP BY tb_HoaDonChiTiet.maSach)tmp";

        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(tb_bookmanager.giaBia * tb_HoaDonChiTiet.soLuong) as 'tongtien' FROM tb_hoadon INNER JOIN tb_HoaDonChiTiet on tb_hoadon.maHoaDon = tb_HoaDonChiTiet.maHoaDon INNER JOIN tb_bookmanager on tb_HoaDonChiTiet.maSach = tb_bookmanager.maSach where strftime('%Y',tb_hoadon.ngay) = strftime('%Y','now') GROUP BY tb_HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
}
