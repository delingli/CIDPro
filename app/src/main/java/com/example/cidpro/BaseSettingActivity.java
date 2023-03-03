package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BaseSettingActivity extends AppCompatActivity {
private EditText bank_name;//银行名称
private EditText money;//额度
private EditText cv;//是否通过
private TextView save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_setting);
        bank_name=findViewById(R.id.et_bank);
        money=findViewById(R.id.et_money_value);
        cv=findViewById(R.id.et_ct);
        save=findViewById(R.id.tv_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSave(bank_name.getText().toString(),money.getText().toString(),cv.getText().toString());
            }
        });
    }
    private void BtnSave(String bank, String money, String cv) {
        System.out.println("银行名称为："+bank+",额度为："+money+",是否通过："+cv);
        Intent intent = new Intent(BaseSettingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}