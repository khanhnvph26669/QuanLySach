package vn.edu.spx.quanlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.HoaDonChiTietDAO;
import vn.edu.spx.quanlysach.model.HoaDonChiTiet;

public class HDCTadapter extends BaseAdapter {
    List<HoaDonChiTiet> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    HoaDonChiTietDAO dao;

    public HDCTadapter(List<HoaDonChiTiet> list, HoaDonChiTietDAO dao, Context context) {
        this.list = list;
        this.context = context;
        this.dao = dao;
        this.inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_hdct, null);
        }
        TextView maSach, soLuong, giaBia, thanhTien;
        ImageView delete = convertView.findViewById(R.id.deleteHDCT);
        maSach = convertView.findViewById(R.id.itemHDCTMaSach);
        soLuong = convertView.findViewById(R.id.itemHDCTSoLuong);
        giaBia = convertView.findViewById(R.id.itemHDCTGiaBia);
        thanhTien = convertView.findViewById(R.id.itemHDCTThanhTien);
        HoaDonChiTiet entry = list.get(position);
        maSach.setText("Mã sách: " + entry.getMaSach());
        soLuong.setText("Số lượng: " + entry.getSoLuong());
        giaBia.setText("Giá bìa: " + entry.getGia() + " vnd");
        thanhTien.setText("Thành tiền:" + ((entry.getSoLuong()) * (entry.getGia())) + " vnd");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteHoaDonChiTietByID(String.valueOf(entry.getMaHDCT()));
                list.remove(position);
                list.clear();
                list = dao.getAllHDCT();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
