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

import vn.edu.spx.quanlysach.DAO.TheLoaiDAO;
import vn.edu.spx.quanlysach.model.TheLoai;

public class ListTheLoaiActivity extends AppCompatActivity {
    Intent intent;
    ListView listView;
    TheLoaiDAO dao;
    List<TheLoai> list=new ArrayList<>();
    TheLoaiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        setTitle("Danh sách");
        listView=findViewById(R.id.lvTheLoai);
        dao=new TheLoaiDAO(getApplicationContext());
        list=dao.getAllTheLoai();
        adapter=new TheLoaiAdapter(this,list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tl,menu);
        MenuItem menuItem = menu.findItem(R.id.addTL);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addTL:
                intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list = dao.getAllTheLoai();
        adapter=new TheLoaiAdapter(this,list);
        listView.setAdapter(adapter);
    }
}