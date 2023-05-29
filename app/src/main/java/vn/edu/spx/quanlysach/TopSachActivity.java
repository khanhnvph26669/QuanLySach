package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.BookManagerDAO;
import vn.edu.spx.quanlysach.model.BookManager;

public class TopSachActivity extends AppCompatActivity {
    ListView lvTopSach;
    List<BookManager> list;
    BookManagerDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach);
        setTitle("Top 10 sách bán chạy");
        lvTopSach = findViewById(R.id.lvTop);
        dao = new BookManagerDAO(getApplicationContext());
        list=dao.getTop10();
        TopSachAdapter adapter = new TopSachAdapter(this, list);
        lvTopSach.setAdapter(adapter);
    }
}