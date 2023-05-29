package vn.edu.spx.quanlysach.model;

public class HoaDonChiTiet {
    int maHDCT, soLuong;
    String maHoaDOn;
    String maSach;
    int gia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int soLuong, String maHoaDOn, String maSach,int gia) {
        this.soLuong = soLuong;
        this.maHoaDOn = maHoaDOn;
        this.maSach = maSach;
        this.gia=gia;
    }

    public HoaDonChiTiet(int maHDCT, int soLuong, String maHoaDOn, String maSach, int gia) {
        this.maHDCT = maHDCT;
        this.soLuong = soLuong;
        this.maHoaDOn = maHoaDOn;
        this.maSach = maSach;
        this.gia = gia;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaHoaDOn() {
        return maHoaDOn;
    }

    public void setMaHoaDOn(String maHoaDOn) {
        this.maHoaDOn = maHoaDOn;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
