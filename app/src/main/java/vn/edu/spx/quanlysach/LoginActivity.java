package vn.edu.spx.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.edu.spx.quanlysach.DAO.NguoiDungDAO;
import vn.edu.spx.quanlysach.model.NguoiDung;

public class LoginActivity extends AppCompatActivity {
    EditText acount,pass;
    NguoiDungDAO dao;
    CheckBox chkRememberPass;
    List<NguoiDung> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        acount=findViewById(R.id.edacount);
        pass=findViewById(R.id.edPa);
        chkRememberPass=findViewById(R.id.chkRememberPass);
        dao=new NguoiDungDAO(getApplicationContext());
        list=dao.getAllNguoiDung();
    }

    public void checkLogin(View view) {
        if (acount.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Tài khoản hoặc mật khẩu bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            for (int i=0;i<list.size();i++){
                if(acount.getText().toString().equals(list.get(i).getUserName()) && pass.getText().toString().equals(list.get(i).getPassword())){
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(acount.getText().toString(), pass.getText().toString(), chkRememberPass.isChecked());
                    Intent intent = new Intent(LoginActivity.this, SplashScreenActivity.class);
                    startActivity(intent);
                    break;
                }
                else {
                    Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }

    public void dangKy(View view) {
        Intent intent = new Intent(LoginActivity.this,NguoiDungActivity.class);
        startActivity(intent);
    }

    public void changePass(View view) {
        Intent intent = new Intent(LoginActivity.this,NguoiDungActivity.class);
        intent.putExtra("a",5);
        startActivity(intent);
    }
}