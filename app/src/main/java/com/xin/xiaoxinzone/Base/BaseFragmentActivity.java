package com.xin.xiaoxinzone.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/17.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    protected RxTitle mTvTitle;

    protected ImmersionBar mImmersionBar;

    protected RxDialogLoading rxDialogLoading;

    protected BaseFragmentActivity mContext;

    protected final int PIC_STATUS_BAR = 1;//图片沉浸式状态栏

    protected final int TEXT_STATUS_BAR = 2;//文字actionbar沉浸式状态栏

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract int setContentView();

    protected abstract void onClickListener(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        RxBarTool.setNoTitle(this);
        setContentView(setContentView());

        RxBarTool.setTransparentStatusBar(this);
        ButterKnife.bind(this);

        //设置沉浸式状态栏
        initImmersionBar();

        rxDialogLoading = new RxDialogLoading(this);
        rxDialogLoading.setLoadingText("小xin正在疯狂加载中...");

        initData();
        initView();
        initListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        //带标题的沉浸式状态栏
        mImmersionBar.init();
    }

    /**
     * 设置沉浸样式
     *
     * @param style
     */
    protected void setStatusStyle(int style) {
        if (style == PIC_STATUS_BAR) {
            //图片沉浸式状态栏不做任何设置
            mImmersionBar.init();
        } else if (style == TEXT_STATUS_BAR) {
            mImmersionBar.statusBarView(R.id.top_view)
                    .statusBarColor(R.color.color_1c84d1)
                    .init();
        }
    }

    @Override
    public void onClick(View v) {
        onClickListener(v);
    }


}
