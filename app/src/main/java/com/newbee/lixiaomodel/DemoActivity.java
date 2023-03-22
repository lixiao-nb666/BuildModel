package com.newbee.lixiaomodel;

import android.Manifest;

import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.activity.welcome.BaseWelcomeActivity;
import com.lixiao.build.mybase.activity.welcome.bean.WelcomeInfoBean;
import com.nrmyw.launcher.R;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends BaseWelcomeActivity {

    @Override
    public int getWelcomeLayoutId() {
        return 0;
    }

    @Override
    public void initWelcomeView() {

    }

    @Override
    public void initWelcomeData() {

    }

    @Override
    public void initWelcomeControl() {

    }

    @Override
    public WelcomeInfoBean getWelcomeInfoBean() {
        WelcomeInfoBean welcomeInfoBean=new WelcomeInfoBean();
        List<String> permissions=new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        welcomeInfoBean.setPermissionList(permissions);
        welcomeInfoBean.setNeedFilePermission(true);
        return welcomeInfoBean;
    }

    @Override
    public void userNoPermission() {

    }

    @Override
    public void userGetAllPermission() {
        showToast("get all permission");
        finish();
    }
}
