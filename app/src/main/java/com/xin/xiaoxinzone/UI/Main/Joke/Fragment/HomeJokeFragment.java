package com.xin.xiaoxinzone.UI.Main.Joke.Fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ta.utdid2.android.utils.SystemUtils;
import com.vondear.rxtools.view.RxToast;
import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.Base.BaseRecycleViewAdapter;
import com.xin.xiaoxinzone.Entity.Joke;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Joke.Adapter.JokeAdapter;
import com.xin.xiaoxinzone.UI.Main.Joke.Model.LoadJokeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HomeJokeFragment extends BaseFragment implements BaseRecycleViewAdapter.OnItemClickListener, LoadJokeModel.LoadJokeListener {

    private SmartRefreshLayout mRefreshLayout;

    private RecyclerView mRecycleView;

    private JokeAdapter mAdapter;

    private LoadJokeModel model = new LoadJokeModel();

    private List<Joke.Data> mData = new ArrayList<>();

    private List<Joke.Data> tempList = new ArrayList<>();

    private boolean isLoading = false;//是否加载过数据

    private int page = 1;//数据的页数

    private String TEXT_JOKE_TIME = System.currentTimeMillis() / 1000 + "";//获取笑话的默认时间

    private String IMG_JOKE_TIME = System.currentTimeMillis() / 1000 + "";//获取趣图的默认时间

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.joke_refresh_layout);

        mRefreshLayout.setHeaderHeight(60);
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));

        mRefreshLayout.setFooterHeight(60);
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));


        mRecycleView = (RecyclerView) view.findViewById(R.id.joke_recycle_view);
        //设置流式布局
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new JokeAdapter(mContext, mData);
        //设置item点击监听器
        mAdapter.setOnItemClickListener(this);
        //设置adapter
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉刷新
                model.loadTextJoke(System.currentTimeMillis() / 1000 + "");
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //上拉加载更多
                model.loadMoreTextJoke(TEXT_JOKE_TIME,page);
            }
        });

        //设置数据监听器
        model.setLoadJokeListener(this);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!isLoading) {
                loadingDialog.show();
                //加载文本笑话数据
                model.loadTextJoke(System.currentTimeMillis() / 1000 + "");
                isLoading = true;
            }
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_joke;
    }

    @Override
    public void onLoadTextJokeSuccess(Joke joke) {

        //加载文本笑话成功后   再去加载动图笑话数据
        model.loadPicJoke(System.currentTimeMillis() / 1000 + "");
        page = 1;

        tempList.clear();
        tempList.addAll(joke.getData());
        //设置最后一条数据的时间
        TEXT_JOKE_TIME = joke.getData().get(joke.getData().size() - 1).getUnixtime() + "";

    }

    @Override
    public void onLoadMoreTextJokeSuccess(Joke joke) {
        //加载更多文本笑话成功后   再去加载动图笑话数据
        model.loadMorePicJoke(IMG_JOKE_TIME,page);
        page += 1;
        tempList.clear();
        tempList.addAll(joke.getData());
        //设置最后一条数据的时间
        TEXT_JOKE_TIME = joke.getData().get(joke.getData().size() - 1).getUnixtime() + "";
    }

    @Override
    public void onLoadTextJokeFail(String err_msg) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        RxToast.info(mContext, err_msg).show();
    }

    @Override
    public void onLoadMoreTextJokeFail(String err_msg) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        RxToast.info(mContext, err_msg).show();
    }

    @Override
    public void onLoadPicJokeSuccess(Joke joke) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        //在此合并数据 到mData中 并刷新adapter
        tempList.addAll(joke.getData());
        //设置最后一条数据的时间
        IMG_JOKE_TIME = joke.getData().get(joke.getData().size() - 1).getUnixtime() + "";
        //处理数据
        dealData(false);
    }

    @Override
    public void onLoadMorePicJokeSuccess(Joke joke) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        //在此合并数据 到mData中 并刷新adapter
        tempList.addAll(joke.getData());
        IMG_JOKE_TIME = joke.getData().get(joke.getData().size() - 1).getUnixtime() + "";
        //处理数据
        dealData(true);
    }

    @Override
    public void onLoadPicJokeFail(String err_msg) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        //处理数据
        dealData(false);
    }

    @Override
    public void onLoadMorePicJokeFail(String err_msg) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        //处理数据
        dealData(true);
    }


    @Override
    protected void onClickListener(View v) {

    }

    @Override
    public void onItemClick(int position) {

//        RxToast.info(mContext, "点击了第" + position + "项").show();

    }

    /**
     * 开始混合 文本笑话 和 趣图 数据  如果是下拉刷新 需要清掉之前的数据
     *
     * @param isMore
     */
    private void dealData(boolean isMore) {
        if (!isMore) {
            //下拉刷新 先清掉之前的数据
            mData.clear();
        }

        //打乱顺序
        Collections.shuffle(tempList);
        mData.addAll(tempList);
        mAdapter.notifyDataSetChanged();

    }

}
