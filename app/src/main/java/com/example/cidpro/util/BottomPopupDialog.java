package com.example.cidpro.util;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cidpro.R;

public class BottomPopupDialog extends DialogFragment {

    private Button btnFirst;
    private Button btnSecond;
    private Button btnCancel;

    public static String FIRST_ITEM_CONTENT = "first_item_content";
    public static String SECOND_ITEM_CONTENT = "second_item_content";

    public interface OnItemClickListener {
        void onFirstItemClick();

        void onSecondItemClick();
    }

    private OnItemClickListener onItemClickListener;

    public BottomPopupDialog() {
        super();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_popup, null);
        //getDialog().requestWindowFeature(R.style.PopupDialog);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnFirst = view.findViewById(R.id.btn_first);
        btnSecond = view.findViewById(R.id.btn_second);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onFirstItemClick();
                }
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onSecondItemClick();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new IllegalArgumentException("Please enter the item content");
        }
        int firstContent = bundle.getInt(FIRST_ITEM_CONTENT);
        int secondContent = bundle.getInt(SECOND_ITEM_CONTENT);

        btnFirst.setText(firstContent);
        btnSecond.setText(secondContent);
        btnCancel.setText("取消");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initParams();
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.popup_menu_anim_style);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(null);
        }
        setCancelable(true);
    }
}
