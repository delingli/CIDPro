package com.example.cidpro.accountlist;

import android.icu.util.Freezable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cidpro.R;
import com.example.cidpro.bean.Data;

import java.util.Iterator;
import java.util.List;

public
class AccountListAdapt extends RecyclerView.Adapter<AccountListAdapt.AccountListAdaptHolder> {
    private OnDeleteListener onDeleteListener;

    public void deleteItemById(int id) {
        if (list != null) {
            Iterator<Data> iterator = list.iterator();
            while (iterator.hasNext()) {
                Data next = iterator.next();
                if (next.getId() == id) {
                    iterator.remove();
                }

            }
            notifyDataSetChanged();
        }

    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener {
        void onDelete(Data data);
    }

    private OnEditeListener onEditeListener;

    public void setOnEditeListener(OnEditeListener onEditeListener) {
        this.onEditeListener = onEditeListener;
    }

    public interface OnEditeListener {
        void onEdite(Data data);
    }

    private List<Data> list;

    public AccountListAdapt(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AccountListAdaptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_list, parent, false);
        AccountListAdaptHolder holder = new AccountListAdaptHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListAdaptHolder holder, int position) {

        if (list.get(position) != null) {
            Data data = list.get(position);
            holder.tv_userName.setText(data.getUserName());
            holder.tv_loginName.setText(data.getLoginName());
            holder.tv_password.setText(data.getPassword());
            holder.tv_phone.setText(data.getPhone());
            holder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onDeleteListener) {
                        onDeleteListener.onDelete(data);
                    }

                }
            });
            holder.tv_edite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onEditeListener) {
                        onEditeListener.onEdite(data);
                    }


                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (null == list) {
            return 0;
        } else {
            return list.size();
        }
    }

    class AccountListAdaptHolder extends RecyclerView.ViewHolder {
        TextView tv_userName, tv_loginName, tv_password, tv_phone, tv_edite, tv_delete;

        public AccountListAdaptHolder(@NonNull View itemView) {
            super(itemView);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_loginName = itemView.findViewById(R.id.tv_loginName);
            tv_password = itemView.findViewById(R.id.tv_password);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_edite = itemView.findViewById(R.id.tv_edite);
            tv_delete = itemView.findViewById(R.id.tv_delete);

        }
    }
}
