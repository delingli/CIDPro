package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.example.cidpro.util.CameraUtils;
import com.lzy.okgo.OkGo;

public class InformationDetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView sfz;
    private TextView check_state;
    private TextView tv_check_information;
    private TextView tv_money,tv_desc;

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
        setContentView(R.layout.activity_information_detail);
//        mTvMemberBirth.setText(mUser.birthday.substring(0,10));
//        mTvMemberHeight.setText(String.valueOf(mUser.height));
//        mTvMemberWeight.setText(String.valueOf(mUser.weight));
        name = findViewById(R.id.tv_name);
        tv_desc = findViewById(R.id.tv_desc);
        sfz = findViewById(R.id.tv_sfz);
        if (getIntent() != null) {
            name.setText(getIntent().getStringExtra("name"));
            sfz.setText(getIntent().getStringExtra("sfz"));
        }
        tv_check_information = findViewById(R.id.tv_check_information);
        tv_money = findViewById(R.id.tv_money);
        check_state = findViewById(R.id.tv_check_state);
        String bank = SPUtils.getInstance().getString(CameraUtils.SELECT_BANK, "中国工商银行");

        if (SPUtils.getInstance().getBoolean(CameraUtils.BANK_MONEY_SHENHE_KEY, true)) {
            check_state.setText("审核通过");
            tv_check_information.setText("风险客户");
            tv_money.setTextSize(38f);

            tv_money.setText(SPUtils.getInstance().getString(CameraUtils.SELECT_BANK_MONEY, "20000"));

        } else {
            check_state.setText("已初审");
            tv_check_information.setText("拒绝");
            tv_money.setTextSize(12f);

            tv_money.setText("尊敬的客户您好，经过评估您申请得" + bank + "信用卡综合评分未达标,敬请谅解,您可登陆" + bank + "APP了解更多信息.");

        }
        tv_desc.setText(bank+"信用卡额度初审内部专用");

    }

    public void Btn_Back(View view) {

        finish();
    }
}