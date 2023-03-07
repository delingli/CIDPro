package com.example.cidpro.infomation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cidpro.R;

public
class ImfomationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomationz);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_condinear, new InformationFragment(), "InformationFragment").commit();
    }
}
