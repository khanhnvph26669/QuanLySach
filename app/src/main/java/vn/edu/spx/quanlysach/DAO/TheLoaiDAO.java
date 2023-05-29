package vn.edu.spx.quanlysach.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.Database.SQLite;
import vn.edu.spx.quanlysach.model.TheLoai;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private SQLite dbHelper;
    public static final String TABLE_NAME = "tb_theloai";


    public TheLoaiDAO(Context context)
    {
        dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertTheLoai(TheLoai tl)
    {
        ContentValues values = new ContentValues();

        values.put("maTheLoai",tl.getMaTheLoai());
        values.put("tenTheLoai",tl.getTenTheLoai());
        values.put("viTri",tl.getViTri());
        values.put("moTa",tl.getMoTa());

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
    public List<TheLoai> getAllTheLoai()
    {
        List<TheLoai> lsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            TheLoai tl=new TheLoai();
            tl.setMaTheLoai(c.getString(0));
            tl.setTenTheLoai(c.getString(1));
            tl.setViTri(c.getInt(2));
            tl.setMoTa(c.getString(3));
            lsTheLoai.add(tl);
            c.moveToNext();
        }
        c.close();
        return lsTheLoai;
    }
    public List<String> getAllMaTheLoai()
    {
        List<String> lsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {

            String tl=c.getString(0);
            lsTheLoai.add(tl);
            c.moveToNext();
        }
        c.close();
        return lsTheLoai;
    }
    public int deleteTL(String tl) {
        if (db.delete(TABLE_NAME, "maTheLoai=?", new String[]{tl}) < 0) {
            return -1;
        }
        return 1;
    }
}
