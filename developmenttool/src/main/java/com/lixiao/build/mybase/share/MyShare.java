package com.lixiao.build.mybase.share;

import android.text.TextUtils;

import com.lixiao.build.mybase.appliction.BaseApplication;
import com.newbee.data_sava_lib.share.BaseShare;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/15 0015 15:49
 */
public class MyShare extends BaseShare {
    private static MyShare myShare;
    private MyShare(){
        super(BaseApplication.getContext());
    }

    public static MyShare getInstance(){
        if(null==myShare){
            synchronized (MyShare.class){
                if(null==myShare){
                    myShare=new MyShare();
                }
            }
        }
        return myShare;
    }




}
