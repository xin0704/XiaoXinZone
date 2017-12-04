package com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vondear.rxtools.model.ModelSpider;
import com.vondear.rxtools.view.RxToast;
import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.Entity.Constellation;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Model.LoadConstellationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class WeekConstellationFragment extends BaseFragment implements LoadConstellationModel.LoadWeekConstellationListener {

    @BindView(R.id.constellation_week_layout)
    LinearLayout mLayoutContainer;
    @BindView(R.id.constellation_week_name)
    TextView mTvConsName;
    @BindView(R.id.constellation_week_job)
    TextView mTvJob;
    @BindView(R.id.constellation_week_love)
    TextView mTvLove;
    @BindView(R.id.constellation_week_money)
    TextView mTvMoney;
    @BindView(R.id.constellation_week_health)
    TextView mTvHealth;
    @BindView(R.id.constellation_week_work)
    TextView mTvWork;

    public static final String WEEK_TYPE = "week";

    public static final String NEXT_WEEK_TYPE = "nextweek";

    private LoadConstellationModel model = new LoadConstellationModel();

    private boolean isLoading = false; //Fragment的View加载完毕的标记

    private boolean isUIVisible = false;  //Fragment对用户可见的标记

    private String type;//类型

    private String CONS_NAME = "摩羯座";

    @Override
    protected void initData() {

        type = getArguments().getString("type");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        //设置数据监听器
        model.setLoadWeekConstellationListener(this);
        //加载数据
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            //类型不等于空 或者 不等于 头条（top）
            if (!TextUtils.isEmpty(type)) {
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
            model.loadWeekConstellation(type, CONS_NAME);
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
        return R.layout.fragment_constellation_week;
    }

    @Override
    protected void onClickListener(View v) {

    }

    @Override
    public void onLoadConstellationSuccess(Constellation.Week constellation) {

        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

        mLayoutContainer.setVisibility(View.VISIBLE);

        mTvConsName.setText(constellation.getName());
        mTvJob.setText(constellation.getJob());
        mTvLove.setText(constellation.getLove());
        mTvMoney.setText(constellation.getMoney());
        mTvHealth.setText(constellation.getHealth());
        mTvWork.setText(constellation.getWork());


    }

    @Override
    public void onLoadConstellationFailed(String err_msg) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        RxToast.info(mContext,err_msg).show();
    }
}
