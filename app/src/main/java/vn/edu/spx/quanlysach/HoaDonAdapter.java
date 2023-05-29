package vn.edu.spx.quanlysach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.spx.quanlysach.DAO.HoaDonDAO;
import vn.edu.spx.quanlysach.model.HoaDon;

public class HoaDonAdapter extends BaseAdapter {
    List<HoaDon> lsHD;
    Context context;
    LayoutInflater inflater;
    HoaDonDAO dao;

    public HoaDonAdapter(Context context, List<HoaDon> lsHD) {
        super();
        this.context = context;
        this.lsHD = lsHD;
        this.inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return lsHD.size();
    }

    @Override
    public Object getItem(int position) {
        return lsHD.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HoaDon hd = lsHD.get(position);
        dao = new HoaDonDAO(context);
        if (view == null) {
            inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_book, null);
        }
        TextView txtMaHD, txtNgay;
        ImageButton btnXoaBM;
        ImageView igBook;
        btnXoaBM = view.findViewById(R.id.igBtnCancelBook);
        igBook=view.findViewById(R.id.igBook);
        txtMaHD = view.findViewById(R.id.txtItemTenSach);
        txtNgay = view.findViewById(R.id.txtItemSoLuong);
        txtMaHD.setText("Mã HD: "+hd.getMaHoaDon());
        txtNgay.setText("Ngày: "+hd.getNgay());
        btnXoaBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteHD(hd.getMaHoaDon());
                lsHD.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                lsHD.clear();
                lsHD = dao.getAllHoaDon();
                notifyDataSetChanged();
            }
        });
        txtMaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoHDCT(lsHD.get(position).getMaHoaDon());
            }
        });
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoHDCT(lsHD.get(position).getMaHoaDon());
            }
        });
        igBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoHDCT(lsHD.get(position).getMaHoaDon());
            }
        });
        return view;
    }
    public void taoHDCT(String ms){
        Intent intent = new Intent(context,HoaDonChiTietActivity.class);
        Bundle b = new Bundle();
        b.putString("MAHOADON",ms);
        intent.putExtras(b);
        context.startActivity(intent);
    }
}
