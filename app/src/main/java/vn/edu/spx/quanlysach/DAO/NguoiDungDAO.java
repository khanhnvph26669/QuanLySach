package vn.edu.spx.quanlysach.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.Database.SQLite;
import vn.edu.spx.quanlysach.model.NguoiDung;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private SQLite dbHelper;
    public static final String TABLE_NAME = "tb_nguoidung";


    public NguoiDungDAO(Context context)
    {
        dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertNguoiDung(NguoiDung nd)
    {
        ContentValues values = new ContentValues();

        values.put("userName",nd.getUserName());
        values.put("passWord",nd.getPassword());
        values.put("phone",nd.getPhone());
        values.put("fullName",nd.getHoTen());
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
    public List<NguoiDung> getAllNguoiDung()
    {
        List<NguoiDung> lsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            NguoiDung nd = new NguoiDung();
            nd.setUserName(c.getString(0));
            nd.setPassword(c.getString(1));
            nd.setPhone(c.getString(2));
            nd.setHoTen(c.getString(3));
            lsNguoiDung.add(nd);
            c.moveToNext();
        }
        c.close();
        return lsNguoiDung;
    }
    public int deleteND(String nd) {
        if (db.delete(TABLE_NAME, "userName=?", new String[]{nd}) < 0) {
            return -1;
        }
        return 1;
    }
    public boolean changerPass(NguoiDung t) {
        ContentValues values = new ContentValues();
        values.put("passWord", t.getPassword());
        long check = db.update(TABLE_NAME, values, "userName=?", new String[]{String.valueOf(t.getUserName())});
        if (check == -1)
            return false;
        return true;
    }
    public boolean upDateND(NguoiDung t) {
        ContentValues values = new ContentValues();
        values.put("userName",t.getUserName());
        values.put("passWord",t.getPassword());
        values.put("phone",t.getPhone());
        values.put("fullName",t.getHoTen());
        long check = db.update(TABLE_NAME, values, "userName=?", new String[]{String.valueOf(t.getUserName())});
        if (check == -1)
            return false;
        return true;
    }
    public int checkLogin(String username, String password){
        int result = db.delete(TABLE_NAME,"userName=? AND passWord=?",new

                String[]{username,password});
        if (result == 0)
            return -1;
        return 1;
    }
}
