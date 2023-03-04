package com.example.cidpro.baseset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cidpro.R;
import com.example.cidpro.UrlUtil;
import com.example.cidpro.accountlist.AccountListActivitys;
import com.example.cidpro.bean.AccountData;
import com.example.cidpro.bean.AccountDataz;
import com.example.cidpro.bean.Bank;
import com.example.cidpro.callback.DialogCallback;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

public
class BankSetSelectDialog extends BottomSheetDialogFragment {

    private BankListAdapter mBankListAdapter;
    private OnItemSelectListener onItemSelectListener;

    public void addOnItemSeclectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public interface OnItemSelectListener {
        void onItemSclect(Bank bank);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.booton_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycleView = view.findViewById(R.id.recycleView);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        HttpParams params = new HttpParams();
        params.put("typeCode", "bank");


        OkGo.<AccountDataz>get(UrlUtil.INSTANCE.getSysDictionary()).tag(this)
                .params(params)
                .execute(new DialogCallback<AccountDataz>(getActivity()) {
                    @Override
                    public void onSuccess(Response<AccountDataz> response) {
                        if (response.body() != null && response.body().getResult().getCode() == 0) {
                            List<Bank> bankList = response.body().getResult().getData();
                            mBankListAdapter = new BankListAdapter(bankList);
                            recycleView.setAdapter(mBankListAdapter);
                            mBankListAdapter.setOnitemClickListener(new BankListAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(Bank data) {
                                    if (null != onItemSelectListener) {
                                        onItemSelectListener.onItemSclect(data);
                                    }

                                }
                            });
                        } else {

                        }
                    }
                });


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
