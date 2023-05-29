package vn.edu.spx.quanlysach.model;

public class BookManager {
    String maSach, theLoai, tenSach, tacGia,NXB;
    int soLuong;
    int giaBIA;
    public BookManager() {
    }

    public BookManager(String maSach, String theLoai, String tenSach, String tacGia, String NXB, int giaBIA, int soLuong) {
        this.maSach = maSach;
        this.theLoai = theLoai;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.soLuong = soLuong;
        this.giaBIA = giaBIA;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBIA() {
        return giaBIA;
    }

    public void setGiaBIA(int giaBIA) {
        this.giaBIA = giaBIA;
    }
}
