package com.xin.xiaoxinzone.UI.Main.HeadNews.Fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.view.RxToast;
import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.Entity.HeadNews;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Activity.HeadNewsWebActivity;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Adapter.HeadNewsAdapter;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Model.HeadNewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class TypeHeadNewsFragment extends BaseFragment implements HeadNewsAdapter.OnHeadNewsItemListener, HeadNewsModel.HeadNewsModelListener {

    private SmartRefreshLayout mLayoutRefresh;

    private RecyclerView mRecycleView;

    private HeadNewsAdapter mAdapter;

    private HeadNewsModel model = new HeadNewsModel();

    private List<HeadNews.Data> mData = new ArrayList<>();

    //Fragment的View加载完毕的标记
    private boolean isLoading = false;
    //Fragment对用户可见的标记
    private boolean isUIVisible = false;

    private final static String DEFAULT_TYPE = "top";

    private String type;//类型

    @Override
    protected void initData() {

        type = getArguments().getString("type");

    }

    @Override
    protected void initView() {

        mLayoutRefresh = (SmartRefreshLayout) view.findViewById(R.id.type_head_news_refresh_layout);

        mRecycleView = (RecyclerView) view.findViewById(R.id.type_head_news_recycle_view);

        mLayoutRefresh.setRefreshHeader(new ClassicsHeader(mContext));
        mLayoutRefresh.setHeaderHeight(60);

        mLayoutRefresh.setRefreshFooter(new ClassicsFooter(mContext));
        mLayoutRefresh.setFooterHeight(60);

        //设置不能上拉加载
        mLayoutRefresh.setEnableLoadmore(false);

        //设置布局样式
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        //设置分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //初始化adapter
        mAdapter = new HeadNewsAdapter(mContext, mData);
        //设置点击事件监听器
        mAdapter.setOnItemListener(this);
        //设置adapter
        mRecycleView.setAdapter(mAdapter);


    }

    @Override
    protected void initListener() {

        //下拉刷新
        mLayoutRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉加载
                model.loadHeadNewsData(type);
            }
        });
        //设置数据监听器
        model.setHeadNewsModelListener(this);
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
            if (!TextUtils.isEmpty(type) && !DEFAULT_TYPE.equals(type)){
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
            model.loadHeadNewsData(type);
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
        return R.layout.fragment_type_head_news;
    }

    @Override
    protected void onClickListener(View v) {

    }

    @Override
    public void onItemClick(int position) {

        //查看新闻详情
        HeadNewsWebActivity.startHeadNewsWebActivity(mContext,
                mData.get(position).getUrl(),
                mData.get(position).getTitle());

    }

    @Override
    public void onLoadHeadNewsDataSuccess(HeadNews headNews) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        //结束刷新
        mLayoutRefresh.finishRefresh();

        if (headNews == null) {
            return;
        }
        mData.clear();
        mData.addAll(headNews.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadHeadNewsDataEmpty() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        //结束刷新
        mLayoutRefresh.finishRefresh();
    }

    @Override
    public void onLoadHeadNewsDataFailed(String err_msg) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        //结束刷新
        mLayoutRefresh.finishRefresh();
        RxToast.info(mContext, err_msg).show();
    }
}
