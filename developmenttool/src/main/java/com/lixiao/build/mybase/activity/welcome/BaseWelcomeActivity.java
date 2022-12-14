package com.lixiao.build.mybase.activity.welcome;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.activity.welcome.bean.WelcomeInfoBean;
import com.lixiao.build.mybase.activity.welcome.dialog.WelcomeDialog;
import com.lixiao.build.mybase.appliction.MyApplicationFile;
import com.lixiao.developmenttool.R;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseWelcomeActivity extends BaseCompatActivity {

    public abstract int getWelcomeLayoutId();
    public abstract void initWelcomeView();
    public abstract void initWelcomeData();
    public abstract void initWelcomeControl();
    public abstract WelcomeInfoBean getWelcomeInfoBean();
    public abstract void userNoPermission();
    public abstract void userGetAllPermission();

    private boolean isCanSend=true;
    private void canSendUserGetAllPermission(){
        if(isCanSend){
            isCanSend=false;
            userGetAllPermission();
        }
    }

    private WelcomeInfoBean welcomeInfoBean;

    @Override
    public int getViewLayoutRsId() {
        return getWelcomeLayoutId();
    }

    @Override
    public void initView() {
        initWelcomeView();
    }

    @Override
    public void initData() {
        welcomeInfoBean=getWelcomeInfoBean();
        initWelcomeData();
        if(null==welcomeInfoBean){
            welcomeInfoBean=new WelcomeInfoBean();
        }
    }

    @Override
    public void initControl() {
        initWelcomeControl();
    }




        @Override
    public void closeActivity() {
    }

    @Override
    public void viewIsShow() {
        initPermissions();
    }

    @Override
    public void viewIsPause() {
    }

    @Override
    public void changeConfig() {
    }


    //????????????
    // ??????????????????
    private List<String> permissions;

    private int getPermissionsActivityType = 52013;
    @TargetApi(Build.VERSION_CODES.M)
    private void initPermissions() {
        // ???????????????????????????????????? 23 ?????????????????????????????????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initNeedPermissions();
            if (checkPermissions()) {
                canSendUserGetAllPermission();
            } else {
                showDialogTipUserRequestPermission();
            }
        } else {
            canSendUserGetAllPermission();
        }
    }



    private void initNeedPermissions() {
        permissions = welcomeInfoBean.getPermissionList();
    }

    //????????????????????????
    private boolean checkPermissions() {
        for (int i = 0; i < permissions.size(); i++) {
            if (!checkPermission(permissions.get(i))) {
                LG.i(tag,"checkPermissions:false-"+permissions.get(i));
                return false;
            }
        }
        return true;
    }

    //??????????????????????????????
    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPermission(String permission) {
        // ?????????????????????????????????
        int a = this.checkSelfPermission(permission);
        LG.i(tag,"checkSelfPermission???"+a+"-"+permission);
        // ?????????????????? ?????? GRANTED---??????  DINIED---??????
        if (a == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }





    private WelcomeDialog welcomeDialog;
    private WelcomeDialog.Click welcomeDialogClick=new WelcomeDialog.Click() {


        @Override
        public void userCanToCheck() {
            startRequestPermission();
        }

        @Override
        public void userToSet() {
            goToAppSetting();
        }

        @Override
        public void userCancel() {
            userNoPermission();
        }
    };
    // ???????????????????????????????????????
    private void showDialogTipUserRequestPermission() {
        if(!isCanSend){
            return;
        }

        if(null==welcomeDialog){
            welcomeDialog=new WelcomeDialog(welcomeDialogClick);
        }
        welcomeDialog.show(this);
    }

    // ????????????????????????
    private void startRequestPermission() {
        String[] strs = new String[permissions.size()];
        strs = permissions.toArray(strs);
        ActivityCompat.requestPermissions(this, strs, getPermissionsActivityType);
    }

    // ???????????? ?????? ???????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == getPermissionsActivityType) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                boolean canStar = true;
                for (int i = 0; i < grantResults.length; i++) {
                    canStar = (grantResults[i] == PackageManager.PERMISSION_GRANTED);
                }
                if (canStar) {
                    canSendUserGetAllPermission();
                } else {
                    showDialogTipUserRequestPermission();
                }
            }
        }
    }



    // ????????????????????????????????????
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 123);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // ?????????????????????????????????
                if (checkPermissions()) {
                    // ?????????????????????????????????????????????????????????
                    canSendUserGetAllPermission();
                } else {
                    showDialogTipUserRequestPermission();
                }
            }
        }
    }

}
