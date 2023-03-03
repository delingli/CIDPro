package com.example.cidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cidpro.util.BottomPopupDialog;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.FileProvider;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
private EditText name;//姓名
private EditText sfz;//身份证
private EditText xyk;//信用卡
private ImageView sz;//身份证正面
private ImageView sf;//身份证反面
private ImageView xz;//信用卡正面
private ImageView xf;//信用卡反面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        name=findViewById(R.id.et_name);
        sfz=findViewById(R.id.et_sfz);
        xyk=findViewById(R.id.et_xyk);
        sz=findViewById(R.id.iv_sz_photo);
        sf=findViewById(R.id.iv_sf_photo);
        xz=findViewById(R.id.iv_xz_photo);
        xf=findViewById(R.id.iv_xf_photo);
        sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupModifyPortraitDialog();
            }
        });
        sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupModifyPortraitDialog();
            }
        });
    }

    private static final int REQUEST_CAMERA_CODE = 0x0011;
    private static final int REQUEST_CROP_PHOTO_CODE = 0x0022;
    private static final int REQUEST_GALLERY_CODE = 0x0033;
    File   tempFile;
    Uri imageUri;
    private void popupModifyPortraitDialog() {
        final BottomPopupDialog dialog = new BottomPopupDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(BottomPopupDialog.FIRST_ITEM_CONTENT, R.string.take_a_picture);
        bundle.putInt(BottomPopupDialog.SECOND_ITEM_CONTENT, R.string.select_from_album);
        dialog.setArguments(bundle);
        dialog.addOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
            @Override
            public void onFirstItemClick() {
                dialog.dismiss();
                openCamera();
            }

            @Override
            public void onSecondItemClick() {
                dialog.dismiss();
                openGallery();
            }
        });
        //dialog.show(getSupportFragmentManager(), "PortraitDialog");
    }
    @SuppressLint("WrongConstant")
    private void openCamera() {
        //   final String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        // Storage permission are allowed.
                        gotoCamera();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        // Storage permission are not allowed.
                        if (AndPermission.hasAlwaysDeniedPermission(InformationActivity.this, Permission.Group.STORAGE)) {
                            //打开权限设置页
                            Toast.makeText(InformationActivity.this,"请开启权限",Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                        }
                    }
                })
                .start();
//        gotoCamera();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_GALLERY_CODE);
    }

    private void gotoCamera() {
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/images"), System.currentTimeMillis() + ".jpg");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Android7.0以上URI
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //添加这一句标识对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //如果是7.0及以上的系统使用时，通过FileProvider创建一个content类型的Uri

                imageUri = FileProvider.getUriForFile(this, "com.example.fatmeter.provider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
    }

    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println(dirPath);
        return dirPath;
    }
    //提交
    public void Btn_Save(View view) {
        System.out.println("姓名："+name+",身份证："+sfz+",信用卡："+xyk);
        //调后台接口
        Intent intent = new Intent(InformationActivity.this, InformationDetailActivity.class);
        startActivity(intent);
        finish();
    }
    //退出登录
    public void Btn_Exit(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void Btn_Back(View view) {
        Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}