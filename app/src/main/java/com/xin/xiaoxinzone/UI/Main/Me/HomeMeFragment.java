package com.xin.xiaoxinzone.UI.Main.Me;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.activity.ActivityScanerCode;
import com.xin.xiaoxinzone.Base.BaseFragment;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Me.Calender.CalenderActivity;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Activity.ConstellationActivity;
import com.xin.xiaoxinzone.Utils.ResourceTools;
import com.xin.xiaoxinzone.Widget.ArcHeaderView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HomeMeFragment extends BaseFragment {

    @BindView(R.id.fragment_me_arc_head_view)
    ArcHeaderView mArcHeadView;//弧形头布局

    @BindView(R.id.me_to_xing_zuo_yun_shi)
    LinearLayout mLayoutToXingZuoYunShi;//去查看星座运势

    @BindView(R.id.me_to_calender)
    LinearLayout mLayoutToCalender;//去查看日历

    @BindView(R.id.me_to_qr_code)
    LinearLayout mLayoutToQrCode;//去查看二维码


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mArcHeadView.setColor(ResourceTools.getColor(mContext, R.color.color_1c84d1),
                ResourceTools.getColor(mContext, R.color.color_a6c9f4));

    }

    @Override
    protected void initListener() {

        mLayoutToXingZuoYunShi.setOnClickListener(this);
        mLayoutToCalender.setOnClickListener(this);
        mLayoutToQrCode.setOnClickListener(this);

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_me;
    }

    @Override
    protected void onClickListener(View v) {

        switch (v.getId()) {
            case R.id.me_to_xing_zuo_yun_shi:
                //查看星座运势
                RxActivityTool.skipActivity(mContext, ConstellationActivity.class);
                break;

            case R.id.me_to_calender:
                //查看日历
                RxActivityTool.skipActivity(mContext, CalenderActivity.class);
                break;

            case R.id.me_to_qr_code:
                //二维码
                RxActivityTool.skipActivity(mContext, ActivityScanerCode.class);
                break;
        }

    }
}
