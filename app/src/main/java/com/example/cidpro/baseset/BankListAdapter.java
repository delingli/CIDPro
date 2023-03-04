package com.example.cidpro.baseset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cidpro.R;
import com.example.cidpro.bean.Bank;
import com.example.cidpro.bean.Data;

import java.util.Iterator;
import java.util.List;

public
class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.BankAdaptHolder> {
    private OnDeleteListener onDeleteListener;


    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener {
        void onDelete(Data data);
    }

    private OnitemClickListener onitemClickListener;

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(Bank data);
    }

    private List<Bank> list;

    public BankListAdapter(List<Bank> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BankAdaptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booton_sheet_layout, parent, false);
        BankAdaptHolder holder = new BankAdaptHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BankAdaptHolder holder, int position) {

        if (list.get(position) != null) {
            Bank data = list.get(position);
            holder.tv_bank.setText(data.getValue());
            holder.tv_bank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onitemClickListener) {
                        onitemClickListener.onItemClick(data);
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


    class BankAdaptHolder extends RecyclerView.ViewHolder {
        TextView tv_bank;

        public BankAdaptHolder(@NonNull View itemView) {
            super(itemView);
            tv_bank = itemView.findViewById(R.id.tv_bank);

        }
    }
}
