package vn.edu.spx.quanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.HoaDonDAO;
import vn.edu.spx.quanlysach.model.HoaDon;

public class ListHoaDonActivity extends AppCompatActivity {
    ListView lv;
    HoaDonDAO dao;
    List<HoaDon> list;
    HoaDonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("Danh sách");
        lv=findViewById(R.id.lvHoaDon);

        dao = new HoaDonDAO(this);
        list=new ArrayList<>();
        list = dao.getAllHoaDon();
        adapter = new HoaDonAdapter(this, list);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tl, menu);
        MenuItem menuItem = menu.findItem(R.id.addTL);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addTL:
                Intent intent = new Intent(ListHoaDonActivity.this, HoaDonActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list = dao.getAllHoaDon();
        adapter=new HoaDonAdapter(this,list);
        lv.setAdapter(adapter);
    }
}