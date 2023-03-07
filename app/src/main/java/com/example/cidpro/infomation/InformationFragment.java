package com.example.cidpro.infomation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.example.cidpro.InformationDetailActivity;
import com.example.cidpro.R;
import com.example.cidpro.basic.BaseFragment;
import com.example.cidpro.util.BottomPopupDialog;
import com.example.cidpro.util.CameraUtils;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;

public
class InformationFragment extends BaseFragment {
    private EditText name;//姓名
    private EditText sfz;//身份证
    private EditText xyk;//信用卡
    private ImageView sz;//身份证正面
    private ImageView sf;//身份证反面
    private ImageView xz;//信用卡正面
    private ImageView xf;//信用卡反面
    private BottomPopupDialog mDialog;
    public static String SHOWDIALOG = "showDialog";

    @Override
    protected void initData() {

    }


    public void Btn_Exit() {
        SPUtils.getInstance().put(CameraUtils.TOKEN_KEY, "");
        getActivity().finish();
    }

    private ProgressDialog dialog;

    private void initDialog() {
        if(dialog==null){
            dialog = new ProgressDialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("请求网络中...");
        }

    }

    public void Btn_Save() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sfz.getText().toString())) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(xyk.getText().toString())) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        if (sz.getDrawable() == null) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        if (sf.getDrawable() == null) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        if (xz.getDrawable() == null) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        if (xf.getDrawable() == null) {
            Toast.makeText(getActivity(), "请输入正确数据", Toast.LENGTH_SHORT).show();

            return;
        }
        initDialog();
        //调后台接口
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("sfz", sfz.getText().toString());
                startActivity(intent);

            }
        }, 2000);


    }

    @Override
    protected void initView(View view) {
        name = view.findViewById(R.id.et_name);
        sfz = view.findViewById(R.id.et_sfz);
        xyk = view.findViewById(R.id.et_xyk);
        sz = view.findViewById(R.id.iv_sz_photo);
        sf = view.findViewById(R.id.iv_sf_photo);
        xz = view.findViewById(R.id.iv_xz_photo);
        xf = view.findViewById(R.id.iv_xf_photo);
        sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mDialog = new BottomPopupDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(BottomPopupDialog.FIRST_ITEM_CONTENT, R.string.take_a_picture);
                bundle.putInt(BottomPopupDialog.SECOND_ITEM_CONTENT, R.string.select_from_album);
                mDialog.setArguments(bundle);
                mDialog.addOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
                    @Override
                    public void onFirstItemClick() {
                        mDialog.dismiss();
                        showImg(sz, 0);

                    }

                    @Override
                    public void onSecondItemClick() {
                        mDialog.dismiss();
                        showImg(sz, 1);


                    }
                });
                mDialog.show(getChildFragmentManager(), SHOWDIALOG);
            }
        });
        sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mDialog = new BottomPopupDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(BottomPopupDialog.FIRST_ITEM_CONTENT, R.string.take_a_picture);
                bundle.putInt(BottomPopupDialog.SECOND_ITEM_CONTENT, R.string.select_from_album);
                mDialog.setArguments(bundle);
                mDialog.addOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
                    @Override
                    public void onFirstItemClick() {
                        mDialog.dismiss();
                        showImg(sf, 0);

                    }

                    @Override
                    public void onSecondItemClick() {
                        mDialog.dismiss();
                        showImg(sf, 1);


                    }
                });
                mDialog.show(getChildFragmentManager(), SHOWDIALOG);
            }
        });
        xz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mDialog = new BottomPopupDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(BottomPopupDialog.FIRST_ITEM_CONTENT, R.string.take_a_picture);
                bundle.putInt(BottomPopupDialog.SECOND_ITEM_CONTENT, R.string.select_from_album);
                mDialog.setArguments(bundle);
                mDialog.addOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
                    @Override
                    public void onFirstItemClick() {
                        mDialog.dismiss();
                        showImg(xz, 0);

                    }

                    @Override
                    public void onSecondItemClick() {
                        mDialog.dismiss();
                        showImg(xz, 1);


                    }
                });
                mDialog.show(getChildFragmentManager(), SHOWDIALOG);
            }
        });
        xf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mDialog = new BottomPopupDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(BottomPopupDialog.FIRST_ITEM_CONTENT, R.string.take_a_picture);
                bundle.putInt(BottomPopupDialog.SECOND_ITEM_CONTENT, R.string.select_from_album);
                mDialog.setArguments(bundle);
                mDialog.addOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
                    @Override
                    public void onFirstItemClick() {
                        mDialog.dismiss();
                        showImg(xf, 0);

                    }

                    @Override
                    public void onSecondItemClick() {
                        mDialog.dismiss();
                        showImg(xf, 1);


                    }
                });
                mDialog.show(getChildFragmentManager(), SHOWDIALOG);
            }
        });
        view.findViewById(R.id.tv_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn_Exit();
            }
        });
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn_Save();
            }
        });
    }

    private void showImg(ImageView isv, int type) {
        if (type == 0) {
            PictureSelector.create(getActivity())
                    .openCamera(SelectMimeType.ofImage())
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(ArrayList<LocalMedia> result) {
                            if (result.get(0) != null) {
                                LocalMedia localMedia = result.get(0);
                                Glide.with(getContext()).load(new File(localMedia.getRealPath())).into(isv);
                            }
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        } else {
            PictureSelector.create(getActivity())
                    .openSystemGallery(SelectMimeType.ofImage())
                    .forSystemResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(ArrayList<LocalMedia> result) {
                            if (result.get(0) != null) {
                                LocalMedia localMedia = result.get(0);
                                Glide.with(getContext()).load(new File(localMedia.getRealPath())).into(isv);
                            }

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        }

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_information;
    }
}
