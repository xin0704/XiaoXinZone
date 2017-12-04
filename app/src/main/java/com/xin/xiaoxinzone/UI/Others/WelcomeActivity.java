package com.xin.xiaoxinzone.UI.Others;

import android.content.Intent;
import android.view.View;

import com.vondear.rxtools.RxSPTool;
import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Main.Activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseFragmentActivity {

    private Timer timer;

    private TimerTask task;

    @Override
    protected void initData() {

        timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                if (!RxSPTool.getBoolean(mContext,"isFirst")){
                    //跳转到引导页面
                    Intent intent = new Intent(WelcomeActivity.this, SlideActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //跳转到首页
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };

    }

    @Override
    protected void initView() {
        //设置为图片沉浸
        setStatusStyle(PIC_STATUS_BAR);
    }

    @Override
    protected void initListener() {

        timer.schedule(task, 3 * 1000);

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onClickListener(View v) {

    }
}
