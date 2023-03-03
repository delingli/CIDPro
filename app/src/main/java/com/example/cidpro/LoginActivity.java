package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintWriter;

public class LoginActivity extends AppCompatActivity {
private EditText username;
private EditText password;
private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        login=findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(username.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    private void Login(String name, String psd) {
        System.out.println("用户名为："+name+",密码为："+psd);
        Intent intent = new Intent(LoginActivity.this, BaseSettingActivity.class);
        startActivity(intent);
        finish();
    }

    public void Btn_ZC(View view) {
        Intent intent = new Intent(LoginActivity.this, InformationActivity.class);
        startActivity(intent);
        finish();
    }

    public void Btn_Set(View view) {
        Intent intent = new Intent(LoginActivity.this, BaseSettingActivity.class);
        startActivity(intent);
        finish();
    }
}