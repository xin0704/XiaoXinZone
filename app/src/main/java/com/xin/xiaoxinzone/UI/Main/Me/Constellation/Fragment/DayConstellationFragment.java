package com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.model.ModelSpider;
import com.vondear.rxtools.view.RxCobwebView;
import com.vondear.rxtools.view.RxToast;
import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.Entity.Constellation;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Model.LoadConstellationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/11/16.
 */

public class DayConstellationFragment extends BaseFragment implements LoadConstellationModel.LoadDayConstellationListener {

    private LinearLayout mLayoutContainer;

    private TextView mTvConsName;

    private TextView mTvLuckyColor;

    private TextView mTvLuckyNum;

    private TextView mTvFriendName;//速配星座

    private TextView mTvDesc;//概述

    private RxCobwebView mCobwebView;

    private LoadConstellationModel model = new LoadConstellationModel();

    private List<ModelSpider> spiderList = new ArrayList<>();//蛛网数据

    //Fragment的View加载完毕的标记
    private boolean isLoading = false;
    //Fragment对用户可见的标记
    private boolean isUIVisible = false;

    private String type;//类型

    private String CONS_NAME = "摩羯座";

    public static final String TODAY_TYPE = "today";

    public static final String TOMORROW_TYPE = "tomorrow";

    private String[] spiderStrs = new String[]{"工作", "爱情", "财运", "健康", "综合"};


    @Override
    protected void initData() {

        type = getArguments().getString("type");

        for (int i = 0; i < spiderStrs.length; i++) {
            ModelSpider spider = new ModelSpider(spiderStrs[i], 0);
            spiderList.add(spider);
        }


    }

    @Override
    protected void initView() {

        mCobwebView = (RxCobwebView) view.findViewById(R.id.constellation_day_cob_web_view);

        mLayoutContainer = (LinearLayout) view.findViewById(R.id.constellation_day_layout);

        mTvConsName = (TextView) view.findViewById(R.id.constellation_day_name);
        mTvLuckyColor = (TextView) view.findViewById(R.id.constellation_day_lucky_color);
        mTvLuckyNum = (TextView) view.findViewById(R.id.constellation_day_lucky_num);
        mTvFriendName = (TextView) view.findViewById(R.id.constellation_day_friend);
        mTvDesc = (TextView) view.findViewById(R.id.constellation_day_desc);

    }

    @Override
    protected void initListener() {

        //设置数据监听器
        model.setLoadDayConstellationListener(this);
        //加载数据
        lazyLoad();
    }


    //setUserVisibleHint和lazyLoad两个方法是为了去除viewPager+fragment的懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            //类型不等于空 或者 不等于 头条（top）
            if (!TextUtils.isEmpty(type) && !TODAY_TYPE.equals(type)) {
                lazyLoad();
            }
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (!isLoading && isUIVisible) {
            //开始加载数据
            model.loadDayConstellation(type, CONS_NAME);
            if (loadingDialog != null) {
                loadingDialog.show();
            }
            //数据加载完毕,恢复标记,防止重复加载
            isLoading = true;
            isUIVisible = false;
        }
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_constellation_day;
    }


    @Override
    public void onLoadConstellationSuccess(Constellation.Day constellation) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        //设置布局显示
        mLayoutContainer.setVisibility(View.VISIBLE);
        //设置工作的指数
        spiderList.get(0).setSpiderLevel(constellation.getWorkLevel());
        //设置工作的指数
        spiderList.get(1).setSpiderLevel(constellation.getLoveLevel());
        //设置工作的指数
        spiderList.get(2).setSpiderLevel(constellation.getMoneyLevel());
        //设置工作的指数
        spiderList.get(3).setSpiderLevel(constellation.getHealthLevel());
        //设置工作的指数
        spiderList.get(4).setSpiderLevel(constellation.getAllLevel());

        //设置蛛网数据
        mCobwebView.setSpiderList(spiderList);

        //设置星座名
        mTvConsName.setText(constellation.getName());
        //设置幸运色
        mTvLuckyColor.setText(constellation.getColor());
        //设置幸运数字
        mTvLuckyNum.setText(constellation.getNumber() + "");
        //设置速配星座
        mTvFriendName.setText(constellation.getQFriend());
        //设置概述
        mTvDesc.setText(constellation.getSummary());

    }

    @Override
    public void onLoadConstellationFailed(String err_msg) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        RxToast.info(mContext, err_msg).show();
    }

    @Override
    protected void onClickListener(View v) {

    }
}
