package vn.edu.spx.quanlysach.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.Database.SQLite;
import vn.edu.spx.quanlysach.model.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private SQLite dbHelper;
    public static final String TABLE_NAME = "tb_hoadon";


    public HoaDonDAO(Context context)
    {
        dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDon(HoaDon hd)
    {
        ContentValues values = new ContentValues();

        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("ngay",hd.getNgay());

        try {
            if(db.insert(TABLE_NAME,null,values)==-1)
            {
                return -1;//insert that bai
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }
    public List<HoaDon> getAllHoaDon()
    {
        List<HoaDon> lsHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(c.getString(0));
            hd.setNgay(c.getString(1));
            lsHoaDon.add(hd);
            c.moveToNext();
        }
        c.close();
        return lsHoaDon;
    }
    public int deleteHD(String hd) {
        if (db.delete(TABLE_NAME, "maHoaDon=?", new String[]{hd}) < 0) {
            return -1;
        }
        return 1;
    }
}
