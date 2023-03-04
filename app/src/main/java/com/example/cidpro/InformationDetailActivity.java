package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cidpro.infomation.InformationActivity;

public class InformationDetailActivity extends AppCompatActivity {
private EditText name;
private EditText sfz;
private EditText check_state;
private EditText check_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
//        mTvMemberBirth.setText(mUser.birthday.substring(0,10));
//        mTvMemberHeight.setText(String.valueOf(mUser.height));
//        mTvMemberWeight.setText(String.valueOf(mUser.weight));
        name=findViewById(R.id.et_name);
        sfz=findViewById(R.id.et_sfz);
        check_state=findViewById(R.id.et_check_state);
        check_information=findViewById(R.id.et_check_information);
        name.setText("小曼");
        sfz.setText("1794297405729480242");
        check_state.setText("已审核通过");
        check_information.setText("工商银行审核通过");
    }

    public void Btn_Back(View view) {
        Intent intent = new Intent(InformationDetailActivity.this, InformationActivity.class);
        startActivity(intent);
        finish();
    }
}