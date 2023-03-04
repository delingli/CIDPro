package com.example.cidpro.baseset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cidpro.LoginActivity;
import com.example.cidpro.R;
import com.example.cidpro.bean.Bank;

public class BaseSettingActivity extends AppCompatActivity {
    private TextView tv_bank;//银行名称
    private EditText money;//额度
    private SwitchCompat switchbotton;//是否通过
    private TextView save;
    private BankSetSelectDialog mBankSetSelectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_setting);
        tv_bank = findViewById(R.id.tv_bank);
        money = findViewById(R.id.et_money_value);
        switchbotton = findViewById(R.id.switchbotton);
        save = findViewById(R.id.tv_save);
        tv_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBankSetSelectDialog != null && mBankSetSelectDialog.isVisible()) {
                    mBankSetSelectDialog.dismiss();
                }
                mBankSetSelectDialog = new BankSetSelectDialog();
                mBankSetSelectDialog.show(getSupportFragmentManager(), "showDialog");
                mBankSetSelectDialog.addOnItemSeclectListener(new BankSetSelectDialog.OnItemSelectListener() {
                    @Override
                    public void onItemSclect(Bank bank) {
                        tv_bank.setText(bank.getValue());
                        mBankSetSelectDialog.dismiss();
                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSave(tv_bank.getText().toString(), money.getText().toString());
            }
        });
    }

    private void BtnSave(String bank, String money) {
        System.out.println("银行名称为：" + bank + ",额度为：" + money + ",是否通过：" + switchbotton.isChecked());
        Intent intent = new Intent(BaseSettingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}