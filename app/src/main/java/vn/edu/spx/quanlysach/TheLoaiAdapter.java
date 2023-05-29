package vn.edu.spx.quanlysach;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.spx.quanlysach.DAO.TheLoaiDAO;
import vn.edu.spx.quanlysach.model.TheLoai;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> lsTheLoai;
    Context context;
    LayoutInflater inflater;
    TheLoaiDAO dao;

    public TheLoaiAdapter(Context context, List<TheLoai> lsTheLoai) {
        super();
        this.context = context;
        this.lsTheLoai = lsTheLoai;
        this.inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return lsTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        dao=new TheLoaiDAO(context);
        TheLoai tl = lsTheLoai.get(position);
        if (view == null) {
            inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_adapter_tl, null);
        }
        TextView txtMaTL, txtTenTL, txtViTri, txtMoTa;
        ImageButton btnXoaTL;
        btnXoaTL = view.findViewById(R.id.igBTNcancelTL);
        txtMaTL = view.findViewById(R.id.txtMaTL);
        txtTenTL = view.findViewById(R.id.txtTenTL);
        txtViTri = view.findViewById(R.id.txtViTri);
        txtMoTa = view.findViewById(R.id.txtMoTa);
        txtMaTL.setText(tl.getMaTheLoai());
        txtTenTL.setText(tl.getTenTheLoai());
        txtViTri.setText(""+tl.getViTri());
        txtMoTa.setText(tl.getMoTa());
        btnXoaTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteTL(tl.getMaTheLoai());
                lsTheLoai.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                lsTheLoai.clear();
                lsTheLoai = dao.getAllTheLoai();
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
