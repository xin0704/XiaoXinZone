package com.xin.xiaoxinzone.UI.Main.HeadNews.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Adapter.ExamplePagerAdapter;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HomeHeadNewsFragment extends BaseFragment {


    private ViewPager mViewPager;

    private MagicIndicator mMagicIndicator;

    private CommonNavigator mCommonNavigator;

    private ExamplePagerAdapter mExamplePagerAdapter;

    private static final String[] CHANNELS = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

    private static final String[] CHANNELS_TYPE = new String[]{"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};

    private List<Fragment> fragmentList = new ArrayList<>();



    @Override
    protected void initData() {

        for (int i = 0; i < CHANNELS.length; i++) {

            TypeHeadNewsFragment fragment = new TypeHeadNewsFragment();
            Bundle b = new Bundle();
            b.putString("type",CHANNELS_TYPE[i]);
            fragment.setArguments(b);
            fragmentList.add(fragment);
        }

    }

    @Override
    protected void initView() {

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        mMagicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);

        //初始化viewpager适配器
        mExamplePagerAdapter = new ExamplePagerAdapter(getChildFragmentManager(),fragmentList);
        //设置viewpager适配器
        mViewPager.setAdapter(mExamplePagerAdapter);
        //初始化指示器
        mCommonNavigator = new CommonNavigator(mContext);
        //是否显示滑动多项跳过动画
        mCommonNavigator.setSkimOver(true);
        //设置指示器adapter
        mCommonNavigator.setAdapter(adapter);
        //绑定指示器
        mMagicIndicator.setNavigator(mCommonNavigator);
        //绑定viewpager
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_head_news;
    }

    @Override
    protected void onClickListener(View v) {

    }

    /**
     * 指示器适配器
     */
    private CommonNavigatorAdapter adapter = new CommonNavigatorAdapter() {
        @Override
        public int getCount() {
            return CHANNELS.length;
        }

        @Override
        public IPagerTitleView getTitleView(Context context, final int index) {
            ClipPagerTitleView view = new ClipPagerTitleView(context);
            view.setText(CHANNELS[index]);
            view.setTextColor(ResourceTools.getColor(mContext,R.color.color_343435));
            view.setClipColor(ResourceTools.getColor(mContext,R.color.color_1c84d1));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(index);
                }
            });
            return view;
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            return null;
        }
    };

}
