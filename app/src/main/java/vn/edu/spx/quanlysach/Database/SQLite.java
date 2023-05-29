package vn.edu.spx.quanlysach.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context) {
        super(context, "db_QuanLy",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_nguoidung = "CREATE TABLE tb_nguoidung(userName TEXT PRIMARY KEY,passWord TEXT,phone TEXT,fullName TEXT); ";
        String sqlTheLoai = "CREATE TABLE tb_theloai(maTheLoai TEXT PRIMARY KEY,tenTheLoai TEXT,viTri INTEGER,moTa TEXT); ";
        String sqlBook = "CREATE TABLE tb_bookmanager(maSach TEXT PRIMARY KEY,maTheLoai TEXT,tenSach TEXT,tacGia TEXT,NXB TEXT, giaBia INTEGER,soLuong INTEGER); ";
        String sqlHoaDon = "CREATE TABLE tb_hoadon(maHoaDon TEXT PRIMARY KEY,ngay TEXT); ";
        String sql_HDCT ="CREATE TABLE tb_HoaDonChiTiet (maHDCT INTEGER PRIMARY KEY AUTOINCREMENT,soLuong INTEGER, maHoaDon TEXT , maSach text  ,gia INTEGER);";
        db.execSQL(sql_nguoidung);
        db.execSQL(sqlTheLoai);
        db.execSQL(sqlBook);
        db.execSQL(sqlHoaDon);
        db.execSQL(sql_HDCT);
        String inSertND1="INSERT INTO tb_nguoidung(userName,passWord,phone,fullName) values ('admin','123456','0123456789','Nguyễn Văn Khanh')";
        String inSertND2="INSERT INTO tb_nguoidung(userName,passWord,phone,fullName) values ('quanei','123456','0123456789','Nguyễn Văn Khanh')";
        String inSertTL1="INSERT INTO tb_theloai(maTheLoai,tenTheLoai,viTri,moTa) values ('TL01','CNTT',101,'Sách')";
        String inSertTL2="INSERT INTO tb_theloai(maTheLoai,tenTheLoai,viTri,moTa) values ('TL02','Maketing',102,'Sách')";
        String inSertBM1="INSERT INTO tb_bookmanager(maSach,maTheLoai,tenSach,tacGia,NXB,giaBia,soLuong) values ('SACH01','TL01','Học C++','Nguyễn Văn Khanh','PMQ',10000,2000)";
        String inSertBM2="INSERT INTO tb_bookmanager(maSach,maTheLoai,tenSach,tacGia,NXB,giaBia,soLuong) values ('SACH02','TL02','Học Thiết Kế','Nguyễn Văn Khanh','PMQ',20000,3000)";
        String inSertHoaDon1="INSERT INTO tb_hoadon(maHoaDon,ngay) values ('HD01','20/10/2022')";
        String inSertHoaDon2="INSERT INTO tb_hoadon(maHoaDon,ngay) values ('HD02','01/10/2022')";
        String inSertHoaDon3="INSERT INTO tb_hoadon(maHoaDon,ngay) values ('HD03','01/01/2022')";
        String inSertHDCT1="INSERT INTO tb_HoaDonChiTiet(soLuong,maHoaDon,maSach,gia) values (10,'HD01','SACH01',10000)";
        String inSertHDCT2="INSERT INTO tb_HoaDonChiTiet(soLuong,maHoaDon,maSach,gia) values (20,'HD02','SACH02',20000)";
        String inSertHDCT3="INSERT INTO tb_HoaDonChiTiet(soLuong,maHoaDon,maSach,gia) values (30,'HD03','SACH02',20000)";
        db.execSQL(inSertND1);
        db.execSQL(inSertND2);
        db.execSQL(inSertTL1);
        db.execSQL(inSertTL2);
        db.execSQL(inSertBM1);
        db.execSQL(inSertBM2);
        db.execSQL(inSertHoaDon1);
        db.execSQL(inSertHoaDon2);
        db.execSQL(inSertHoaDon3);
        db.execSQL(inSertHDCT1);
        db.execSQL(inSertHDCT2);
        db.execSQL(inSertHDCT3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_nguoidung");
        db.execSQL("DROP TABLE IF EXISTS tb_theloai");
        db.execSQL("DROP TABLE IF EXISTS tb_bookmanager");
        db.execSQL("DROP TABLE IF EXISTS tb_hoadon");
        db.execSQL("DROP TABLE IF EXISTS tb_HoaDonChiTiet");
    }
}
