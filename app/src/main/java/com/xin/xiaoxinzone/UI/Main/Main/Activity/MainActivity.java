package com.xin.xiaoxinzone.UI.Main.Main.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Joke.Fragment.HomeJokeFragment;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Fragment.HomeHeadNewsFragment;
import com.xin.xiaoxinzone.UI.Main.Main.Model.MainModel;
import com.xin.xiaoxinzone.UI.Main.Me.HomeMeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.home_head_news_img)
    ImageView mImgHeadNews;
    @BindView(R.id.home_head_news_tv)
    TextView mTvHeadNews;
    @BindView(R.id.home_joke_img)
    ImageView mImgJoke;
    @BindView(R.id.home_joke_tv)
    TextView mTvJoke;
    @BindView(R.id.home_me_img)
    ImageView mImgMe;
    @BindView(R.id.home_me_tv)
    TextView mTvMe;

    private FragmentManager manager;

    private MainModel model = new MainModel();

    private List<Fragment> fragmentList = new ArrayList<>();

    private List<TextView> tvList = new ArrayList<>();

    private List<ImageView> imgList = new ArrayList<>();

    public static final int home_head_news_tag = 0;

    public static final int home_joke_tag = 1;

    public static final int home_me_tag = 2;


    @Override
    protected void initData() {

        fragmentList.add(new HomeHeadNewsFragment());
        fragmentList.add(new HomeJokeFragment());
        fragmentList.add(new HomeMeFragment());

        tvList.add(mTvHeadNews);
        tvList.add(mTvJoke);
        tvList.add(mTvMe);

        imgList.add(mImgHeadNews);
        imgList.add(mImgJoke);
        imgList.add(mImgMe);


    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatusStyle(TEXT_STATUS_BAR);
    }

    @Override
    protected void initListener() {

        manager = getSupportFragmentManager();
        //设置页面布局显示
        model.setShowFragment(manager, fragmentList, home_head_news_tag, true);
        //设置显示的页面的button样式
        model.setShowFragmentTextStyle(mContext,tvList,imgList,home_head_news_tag);

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onClickListener(View v) {

        switch (v.getId()) {
            case R.id.home_head_news:
                //设置页面布局显示
                model.setShowFragment(manager, fragmentList, home_head_news_tag, false);
                //设置显示的页面的button样式
                model.setShowFragmentTextStyle(mContext,tvList,imgList,home_head_news_tag);
                break;
            case R.id.home_joke:
                //设置页面布局显示
                model.setShowFragment(manager, fragmentList, home_joke_tag, false);
                //设置显示的页面的button样式
                model.setShowFragmentTextStyle(mContext,tvList,imgList,home_joke_tag);
                break;
            case R.id.home_me:
                //设置页面布局显示
                model.setShowFragment(manager, fragmentList, home_me_tag, false);
                //设置显示的页面的button样式
                model.setShowFragmentTextStyle(mContext,tvList,imgList,home_me_tag);
                break;

        }

    }


}
