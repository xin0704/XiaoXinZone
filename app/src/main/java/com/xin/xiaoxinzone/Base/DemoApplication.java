package com.xin.xiaoxinzone.Base;

import android.app.Application;

import com.vondear.rxtools.RxTool;

/**
 * Created by Administrator on 2017/10/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
    }
}
