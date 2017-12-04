package com.xin.xiaoxinzone.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.vondear.rxtools.view.dialog.RxDialogShapeLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/19.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public Activity mContext;

    private Unbinder unbinder;

    protected View view;

    public RxDialogLoading loadingDialog;

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract int setContentView();

    protected abstract void onClickListener(View v);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(setContentView(), null);

            unbinder = ButterKnife.bind(this, view);

            mContext = getActivity();

            loadingDialog = new RxDialogLoading(mContext);
            loadingDialog.setLoadingText("小xin正在疯狂加载中...");

            initData();
            initView();
            initListener();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onClick(View v) {
        onClickListener(v);
    }

}
