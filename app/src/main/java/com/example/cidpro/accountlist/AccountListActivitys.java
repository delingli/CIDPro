package com.example.cidpro.accountlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cidpro.AccountAddEditeActivity;
import com.example.cidpro.LoginActivity;
import com.example.cidpro.R;
import com.example.cidpro.UrlUtil;
import com.example.cidpro.bean.AccountData;
import com.example.cidpro.bean.Data;
import com.example.cidpro.callback.DialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public
class AccountListActivitys extends AppCompatActivity {

    private RecyclerView mRececleView;
    private AccountListAdapt mAccountListAdapt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountlist);

        mRececleView = findViewById(R.id.rececleView);
        findViewById(R.id.tv_newadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountListActivitys.this, AccountAddEditeActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);

            }
        });
        mRececleView.setLayoutManager(new LinearLayoutManager(this));
        initData();


    }

    private void initData() {
        OkGo.<AccountData>post(UrlUtil.INSTANCE.getGETUSERLISTS()).tag(this).execute(new DialogCallback<AccountData>(this) {
            @Override
            public void onSuccess(Response<AccountData> response) {
                if (response.body() != null) {
                    AccountData accountData = response.body();
                    if (accountData.getResult().getCode() == 0) {
                        mAccountListAdapt = new AccountListAdapt(accountData.getResult().getData());
                        mRececleView.setAdapter(mAccountListAdapt);
                        mAccountListAdapt.setOnDeleteListener(new AccountListAdapt.OnDeleteListener() {
                            @Override
                            public void onDelete(Data data) {
                                HttpParams params = new HttpParams();
                                params.put("id", data.getId());
                                OkGo.<AccountData>get(UrlUtil.INSTANCE.getDeleteUser()).tag(this)
                                        .params(params)
                                        .execute(new DialogCallback<AccountData>(AccountListActivitys.this) {
                                            @Override
                                            public void onSuccess(Response<AccountData> response) {
                                                if (response.body() != null && response.body().getResult().getCode() == 0) {
                                                    Toast.makeText(AccountListActivitys.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                    mAccountListAdapt.deleteItemById(data.getId());

                                                } else {
                                                    Toast.makeText(AccountListActivitys.this, "删除失败", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                            }
                        });
                        mAccountListAdapt.setOnEditeListener(new AccountListAdapt.OnEditeListener() {
                            @Override
                            public void onEdite(Data data) {
                                Intent intent = new Intent(AccountListActivitys.this, AccountAddEditeActivity.class);
                                intent.putExtra("type", 2);
                                intent.putExtra("id", data.getId());
                                startActivity(intent);

                            }
                        });
                    }

                }
            }

            @Override
            public void onError(Response<AccountData> response) {
                super.onError(response);
                Log.e("APP", "请求失败");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
