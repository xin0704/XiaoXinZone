package com.xin.xiaoxinzone.UI.Main.Me.Constellation.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.vondear.rxtools.view.RxTitle;
import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Adapter.ExamplePagerAdapter;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment.DayConstellationFragment;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment.MonthConstellationFragment;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment.WeekConstellationFragment;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Fragment.YearConstellationFragment;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class ConstellationActivity extends BaseFragmentActivity {

    private ViewPager mViewPager;

    private MagicIndicator mMagicIndicator;

    private CommonNavigator mCommonNavigator;

    private ExamplePagerAdapter mExamplePagerAdapter;

    private static final String[] CHANNELS = new String[]{"今天", "明天", "本周", "下周", "本月"};

    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void initData() {

        //今日运势
        DayConstellationFragment todayFragment = new DayConstellationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", DayConstellationFragment.TODAY_TYPE);
        todayFragment.setArguments(bundle);

        //明日运势
        DayConstellationFragment tomorrowFragment = new DayConstellationFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", DayConstellationFragment.TOMORROW_TYPE);
        tomorrowFragment.setArguments(bundle2);

        //本周运势
        WeekConstellationFragment weekFragment = new WeekConstellationFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("type", WeekConstellationFragment.WEEK_TYPE);
        weekFragment.setArguments(bundle3);

        //下周运势
        WeekConstellationFragment nextWeekFragment = new WeekConstellationFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("type", WeekConstellationFragment.NEXT_WEEK_TYPE);
        nextWeekFragment.setArguments(bundle4);

        //本月运势
        MonthConstellationFragment monthFragment = new MonthConstellationFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putString("type", MonthConstellationFragment.MONTH_TYPE);
        monthFragment.setArguments(bundle5);

        fragmentList.add(todayFragment);
        fragmentList.add(tomorrowFragment);
        fragmentList.add(weekFragment);
        fragmentList.add(nextWeekFragment);
        fragmentList.add(monthFragment);

    }

    @Override
    protected void initView() {

        //设置沉浸式状态
        setStatusStyle(TEXT_STATUS_BAR);

        mTvTitle = (RxTitle) findViewById(R.id.rx_title);
        mTvTitle.setTitle("星座运势");
        //显示返回按钮
        mTvTitle.setLeftIconVisibility(true);
        //设置按钮点击返回
        mTvTitle.setLeftFinish(mContext);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mMagicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);

        //初始化viewpager适配器
        mExamplePagerAdapter = new ExamplePagerAdapter(getSupportFragmentManager(), fragmentList);
        //设置viewpager适配器
        mViewPager.setAdapter(mExamplePagerAdapter);
        //初始化指示器
        mCommonNavigator = new CommonNavigator(mContext);
        //是否显示滑动多项跳过动画
        mCommonNavigator.setSkimOver(true);
        //设置适配屏幕
        mCommonNavigator.setAdjustMode(true);
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
        return R.layout.activity_constellation;
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
            view.setTextColor(ResourceTools.getColor(mContext, R.color.color_343435));
            view.setClipColor(ResourceTools.getColor(mContext, R.color.color_1c84d1));
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
