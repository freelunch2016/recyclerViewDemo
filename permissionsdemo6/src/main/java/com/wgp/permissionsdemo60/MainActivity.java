package com.wgp.permissionsdemo60;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_permission_status)
    TextView tv_permission_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_request_permission)
    public void onClick(){
        requestPermission();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //第一次请求权限时，用户如果拒绝,下一次请求shouldShowRequestPermissionRationale()返回true
            //向用户解释为什么需要这个权限
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                new AlertDialog.Builder(this).setMessage("申请相机权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //申请相机权限
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
                            }
                        }).show();
            }else{
                //申请相机权限
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
            }
        }else{
            tv_permission_status.setTextColor(Color.GREEN);
            tv_permission_status.setText("相机权限已申请");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            tv_permission_status.setTextColor(Color.GREEN);
            tv_permission_status.setText("相机权限已申请");
            if (grantResults[0] ==PackageManager.PERMISSION_GRANTED) {
                //
                Toast.makeText(this,"打开相机了",Toast.LENGTH_SHORT).show();
            }
        }else{
            //用户勾选了不再询问
            //提示用户手动打开权限
            //当多次（两次或两次以上）请求操作时，会有不再提示的选择框，如果用户选择了不再提示，
            // shouldShowRequestPermissionRationale为false，在此判断中提示用户权限已被禁止，需要在应用管理中自行打开
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                Toast.makeText(this,"相机权限已被禁止,请手动打开",Toast.LENGTH_SHORT).show();

            }
        }
    }
}


/**
 * shouldShowRequestPermissionRationale()说明

 shouldShowRequestPermissionRationale() 默认返回 false。

 第一次请求权限时，如果用户拒绝了，再次请求时 shouldShowRequestPermissionRationale() 返回 true。

 多次请求权限（超过一次），用户如果选择了不再提醒并拒绝，shouldShowRequestPermissionRationale() 返回 false。

 设备的策略禁止当前应用获取这个权限的授权，shouldShowRequestPermissionRationale() 返回 false。
 */



































































