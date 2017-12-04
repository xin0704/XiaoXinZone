package com.xin.xiaoxinzone.UI.Main.Me.Calender;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.vondear.rxtools.view.RxTitle;
import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/17.
 */

public class CalenderActivity extends BaseFragmentActivity implements DayViewDecorator {

    @BindView(R.id.rx_title)
    RxTitle mTvTitle;
    @BindView(R.id.calendarView)
    MaterialCalendarView materialCalendarView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatusStyle(TEXT_STATUS_BAR);

        mTvTitle.setTitle("日历");
        mTvTitle.setLeftIconVisibility(true);
        mTvTitle.setLeftFinish(mContext);

        materialCalendarView.addDecorator(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_calender;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return CalendarDay.today().equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        //设置选择背景
        view.setBackgroundDrawable(ResourceTools.getDrawable(mContext, R.drawable.today_circle_background));
    }

    @Override
    protected void onClickListener(View v) {

    }
}
