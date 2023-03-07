package com.example.cidpro.baseset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.example.cidpro.LoginActivity;
import com.example.cidpro.R;
import com.example.cidpro.bean.Bank;
import com.example.cidpro.util.CameraUtils;

public class BaseSettingActivity extends AppCompatActivity {
    private TextView tv_bank;//银行名称
    private EditText money;//额度
    private SwitchCompat switchbotton;//是否通过
    private TextView save;
    private BankSetSelectDialog mBankSetSelectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
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
                if (money.getText().toString().isEmpty()) {
                    Toast.makeText(BaseSettingActivity.this, "请输入银行卡额度", Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtils.getInstance().put(CameraUtils.SELECT_BANK, tv_bank.getText().toString());
                SPUtils.getInstance().put(CameraUtils.SELECT_BANK_MONEY, money.getText().toString());
                SPUtils.getInstance().put(CameraUtils.BANK_MONEY_SHENHE_KEY, switchbotton.isChecked());
                finish();
            }
        });
        tv_bank.setText(SPUtils.getInstance().getString(CameraUtils.SELECT_BANK, "中国工商银行"));
        money.setText(SPUtils.getInstance().getString(CameraUtils.SELECT_BANK_MONEY, "20000"));
        switchbotton.setChecked(SPUtils.getInstance().getBoolean(CameraUtils.BANK_MONEY_SHENHE_KEY, true));

        switchbotton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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