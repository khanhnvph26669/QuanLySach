package vn.edu.spx.quanlysach.model;

import java.util.Date;

public class HoaDon {
    String maHoaDon,ngay;

    public HoaDon(String string, Date parse) {
    }

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String ngay) {
        this.maHoaDon = maHoaDon;
        this.ngay = ngay;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
