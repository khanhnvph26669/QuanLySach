package vn.edu.spx.quanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.NguoiDungDAO;
import vn.edu.spx.quanlysach.model.NguoiDung;

public class ListNguoiDungActivity extends AppCompatActivity {
    Intent intent;
    ListView listView;
    NguoiDungDAO dao;
    List<NguoiDung> list = new ArrayList<>();
    NguoiDungAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        setTitle("Danh sách");

        listView = findViewById(R.id.lvNguoiDung);
        dao = new NguoiDungDAO(getApplicationContext());
        list = dao.getAllNguoiDung();
        adapter = new NguoiDungAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("zzzzz", "onItemClick: ");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.add);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                intent = new Intent(ListNguoiDungActivity.this, NguoiDungActivity.class);
                startActivity(intent);
                break;
            case R.id.changePass:
                intent = new Intent(ListNguoiDungActivity.this, NguoiDungActivity.class);
                intent.putExtra("a", 5);
                startActivity(intent);
                break;
            case R.id.logOut:
                Toast.makeText(getBaseContext(), "logout", Toast.LENGTH_LONG).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = dao.getAllNguoiDung();
        adapter = new NguoiDungAdapter(this, list);
        listView.setAdapter(adapter);
    }
}