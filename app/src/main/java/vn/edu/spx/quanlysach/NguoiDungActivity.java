package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.spx.quanlysach.DAO.NguoiDungDAO;
import vn.edu.spx.quanlysach.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
    EditText edUser, edPass, edRepass, edPhone, edFullName;
    List<NguoiDung> list;
    NguoiDungDAO dao;
    Button btnAddUser;
    int id = -1;
    int a = -1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("Thêm người dùng");
        edUser = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        edRepass = findViewById(R.id.edRePassword);
        edPhone = findViewById(R.id.edPhone);
        edFullName = findViewById(R.id.edFullName);
        btnAddUser = findViewById(R.id.btnAddUser);
        dao = new NguoiDungDAO(getApplicationContext());
        list = new ArrayList<>();
        list = dao.getAllNguoiDung();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        a = intent.getIntExtra("a", 0);
        Log.d("contxet", "" + a + " " + id);
        if (a == 5) {
            btnAddUser.setText("Đổi mật khẩu");
            edPass.setHint("Mật khẩu mới");
            edRepass.setHint("Nhập lại mật khẩu mới");
            edPhone.setVisibility(View.GONE);
            edFullName.setVisibility(View.GONE);
        } else if (id == 2) {
            btnAddUser.setText("Cập nhật");
            edUser.setText(intent.getStringExtra("user"));
//            edPass.setText(intent.getStringExtra("pass"));
            edPhone.setText(intent.getStringExtra("phone"));
            edFullName.setText(intent.getStringExtra("fullname"));
        } else {
            btnAddUser.setText("Thêm");
            edPhone.setVisibility(View.VISIBLE);
            edFullName.setVisibility(View.VISIBLE);
            edPass.setHint("Mật khẩu");
            edRepass.setHint("Nhập lại mật khẩu");
        }
    }

    public void addUser(View view) {
        if (a == 5) {
            if (edUser.getText().toString().isEmpty() || edPass.getText().toString().isEmpty() || edRepass.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            } else if (!edPass.getText().toString().equals(edRepass.getText().toString())) {
                Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
            } else if (edPass.getText().length() < 3) {
                Toast.makeText(this, "Mật khẩu không dưới 3 ký tự", Toast.LENGTH_SHORT).show();
            } else {
                NguoiDung nd = new NguoiDung();
                changePass(nd);
            }
        } else if (id == 2) {
            if (edUser.getText().toString().isEmpty() || edPass.getText().toString().isEmpty() || edRepass.getText().toString().isEmpty() || edPhone.getText().toString().isEmpty() || edFullName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            } else if (!edPass.getText().toString().equals(edRepass.getText().toString())) {
                Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
            } else if (edPass.getText().length() < 3) {
                Toast.makeText(this, "Mật khẩu không dưới 3 ký tự", Toast.LENGTH_SHORT).show();
            }
            else if (edPhone.getText().length() < 10) {
                Toast.makeText(this, "Phone phải lớn hơn 10 ký tự", Toast.LENGTH_SHORT).show();
            }else {
                NguoiDung nd = new NguoiDung();
                nd.setUserName(edUser.getText().toString());
                nd.setPassword(edPass.getText().toString());
                nd.setPhone(edPhone.getText().toString());
                nd.setHoTen(edFullName.getText().toString());
                list.add(nd);
                if (dao.upDateND(nd) == true) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if (edUser.getText().toString().isEmpty() || edPass.getText().toString().isEmpty() || edRepass.getText().toString().isEmpty() || edPhone.getText().toString().isEmpty() || edFullName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            } else if (!edPass.getText().toString().equals(edRepass.getText().toString())) {
                Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
            } else if (edPass.getText().length() < 3) {
                Toast.makeText(this, "Mật khẩu không dưới 3 ký tự", Toast.LENGTH_SHORT).show();
            }
            else if (edPhone.getText().length() < 10) {
                Toast.makeText(this, "Phone phải lớn hơn 10 ký tự", Toast.LENGTH_SHORT).show();
            }else {
                NguoiDung nd = new NguoiDung();
                nd.setUserName(edUser.getText().toString());
                nd.setPassword(edPass.getText().toString());
                nd.setPhone(edPhone.getText().toString());
                nd.setHoTen(edFullName.getText().toString());
                list.add(nd);
                if (dao.insertNguoiDung(nd) == 1) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void changePass(NguoiDung nd) {
        String repass = edPass.getText().toString();
        nd.setPassword(repass);
        if (dao.changerPass(nd)) {
            Toast.makeText(getApplicationContext(), "Đổi thành công ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Đổi thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void showUsers(View view) {
        finish();
    }

    public void cancelND(View view) {
        edUser.setText("");
        edPass.setText("");
        edRepass.setText("");
        edPhone.setText("");
        edFullName.setText("");
    }
}