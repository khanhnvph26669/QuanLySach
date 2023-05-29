package vn.edu.spx.quanlysach.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.Database.SQLite;
import vn.edu.spx.quanlysach.model.BookManager;

public class BookManagerDAO {
    private SQLiteDatabase db;
    private SQLite dbHelper;
    public static final String TABLE_NAME = "tb_bookmanager";


    public BookManagerDAO(Context context)
    {
        dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertBook(BookManager bm)
    {
        ContentValues values = new ContentValues();

        values.put("maSach",bm.getMaSach());
        values.put("maTheLoai",bm.getTheLoai());
        values.put("tenSach",bm.getTenSach());
        values.put("tacGia",bm.getTacGia());
        values.put("NXB",bm.getNXB());
        values.put("giaBia",bm.getGiaBIA());
        values.put("soLuong",bm.getSoLuong());
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
    public List<BookManager> getAllBook()
    {
        List<BookManager> lsBook = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            BookManager bm=new BookManager();
            bm.setMaSach(c.getString(0));
            bm.setTheLoai(c.getString(1));
            bm.setTenSach(c.getString(2));
            bm.setTacGia(c.getString(3));
            bm.setNXB(c.getString(4));
            bm.setGiaBIA(c.getInt(5));
            bm.setSoLuong(c.getInt(6));

            lsBook.add(bm);
            c.moveToNext();
        }
        c.close();
        return lsBook;
    }
    public int deleteBM(String nd) {
        if (db.delete(TABLE_NAME, "maSach=?", new String[]{nd}) < 0) {
            return -1;
        }
        return 1;
    }
    public List<BookManager> getTop10(){
        List<BookManager> listS = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from tb_bookmanager ORDER by soLuong DESC LIMIT 10 ",null);
        if (c.getCount()>0){
            c.moveToFirst();
            do {
                listS.add(new BookManager(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5),c.getInt(6)));
            }while (c.moveToNext());
        }
        return listS;
    }
    public BookManager getSachByID(String maSach){
        BookManager s = null;
    //WHERE clause
        String selection = "masach=?";
    //WHERE clause arguments
        String[] selectionArgs = {maSach};
        Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new BookManager();
            s.setMaSach(c.getString(0));
            s.setTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBIA(c.getInt(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
}
