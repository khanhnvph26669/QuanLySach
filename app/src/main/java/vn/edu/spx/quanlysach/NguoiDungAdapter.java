package vn.edu.spx.quanlysach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.spx.quanlysach.DAO.NguoiDungDAO;
import vn.edu.spx.quanlysach.model.NguoiDung;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> lsNguoiDung;
    Context context;
    LayoutInflater inflater;
    NguoiDungDAO dao;

    public NguoiDungAdapter(Context context, List<NguoiDung> lsNguoiDung) {
        super();
        this.context = context;
        this.lsNguoiDung = lsNguoiDung;
        this.inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return lsNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        NguoiDung nd = lsNguoiDung.get(position);
        dao = new NguoiDungDAO(context);
        if (view == null) {
            view = inflater.inflate(R.layout.item_adapter, null);
        }
        TextView txtUser,  txtFullName;
        ImageButton btnXoaTL;
        btnXoaTL = view.findViewById(R.id.igBTNcancel);
        txtUser = view.findViewById(R.id.txtUser);
        txtFullName = view.findViewById(R.id.txtFullName);
        txtUser.setText("User: "+nd.getUserName());
        txtFullName.setText("FullName: "+nd.getHoTen());
        btnXoaTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteND(nd.getUserName());
                lsNguoiDung.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                lsNguoiDung.clear();
                lsNguoiDung = dao.getAllNguoiDung();
                notifyDataSetChanged();
            }
        });
        txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem(nd);
            }
        });
        txtFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem(nd);
            }
        });
        return view;
    }
    public void clickItem(NguoiDung nd){
        Intent intent=new Intent(context,NguoiDungActivity.class);
        intent.putExtra("id",2);
        intent.putExtra("user",nd.getUserName());
        intent.putExtra("pass",nd.getPassword());
        intent.putExtra("phone",nd.getPhone());
        intent.putExtra("fullname",nd.getHoTen());
        context.startActivity(intent);
    }
}
