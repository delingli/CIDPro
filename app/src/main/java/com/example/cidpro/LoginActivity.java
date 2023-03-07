package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.example.cidpro.accountlist.AccountListAdapt;
import com.example.cidpro.accountlist.MainActivity;
import com.example.cidpro.baseset.BaseSettingActivity;
import com.example.cidpro.bean.AccountData;
import com.example.cidpro.bean.Data;
import com.example.cidpro.bean.LoginData;
import com.example.cidpro.callback.DialogCallback;
import com.example.cidpro.infomation.ImfomationActivity;
import com.example.cidpro.util.CameraUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView login;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入登陆名", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpParams  params=new HttpParams();
                params.put("userName",username.getText().toString().trim());
                params.put("password",password.getText().toString().trim());
             String ss="http://106.52.91.56:8080/api/Home/Login?userName=admin&password=666666";
                OkGo.<LoginData>get(UrlUtil.INSTANCE.getLogin()).tag(LoginActivity.this)
                        .params(params)
                        .execute(new DialogCallback<LoginData>(LoginActivity.this) {
                    @Override
                    public void onSuccess(Response<LoginData> response) {
                        if (response.body() != null) {
                            LoginData accountData = response.body();
                            if (accountData.getResult().getCode() == 0) {
                                if (accountData.getResult().getData() != null && !TextUtils.isEmpty(accountData.getResult().getData().getToken())) {
                                    SPUtils.getInstance().put(CameraUtils.TOKEN_KEY, accountData.getResult().getData().getToken());
                                    Intent intent = null;
                                    if (accountData.getResult().getData().getUserType() == 1) {
                                        //管理员
                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                        SPUtils.getInstance().put(CameraUtils.ISADMIN, true);

                                    } else {
                                        intent = new Intent(LoginActivity.this, ImfomationActivity.class);
                                        SPUtils.getInstance().put(CameraUtils.ISADMIN, false);
                                    }
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, accountData.getResult().getMsg(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    }

                    @Override
                    public void onError(Response<LoginData> response) {
                        super.onError(response);
                        Log.e("APP", "请求失败");
                    }
                });


            }
        });

        if (!TextUtils.isEmpty(SPUtils.getInstance().getString(CameraUtils.TOKEN_KEY, null))) {
            if (SPUtils.getInstance().getBoolean(CameraUtils.ISADMIN)) {
                //管理员
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(LoginActivity.this, ImfomationActivity.class);
                startActivity(intent);
            }
            finish();
        }

    }

    private void Login(String name, String psd) {
        System.out.println("用户名为：" + name + ",密码为：" + psd);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Btn_ZC(View view) {
//        Intent intent = new Intent(LoginActivity.this, InformationActivity.class);
//        startActivity(intent);
        finish();
    }

    public void Btn_Set(View view) {
        Intent intent = new Intent(LoginActivity.this, BaseSettingActivity.class);
        startActivity(intent);
    }
}