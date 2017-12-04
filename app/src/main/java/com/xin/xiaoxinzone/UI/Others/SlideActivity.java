package com.xin.xiaoxinzone.UI.Others;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vondear.rxtools.RxSPTool;
import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Main.Activity.MainActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SlideActivity extends BaseFragmentActivity {

    @BindView(R.id.slide_banner)
    Banner banner;
    @BindView(R.id.skip)
    TextView mTvSkip;

    private List<String> bannerList = new ArrayList<>();


    @Override
    protected void initData() {

        bannerList.add("http://test-sxd-moment-mission-image.oss-cn-hangzhou.aliyuncs.com/max/1510029573577934481.jpg");
        bannerList.add("http://test-sxd-moment-mission-image.oss-cn-hangzhou.aliyuncs.com/max/1510029573577934481.jpg");
        bannerList.add("http://test-sxd-moment-mission-image.oss-cn-hangzhou.aliyuncs.com/max/1510029573577934481.jpg");
        bannerList.add("http://test-sxd-moment-mission-image.oss-cn-hangzhou.aliyuncs.com/max/1510029573577934481.jpg");

    }

    @Override
    protected void initView() {
        //设置为图片沉浸
        setStatusStyle(PIC_STATUS_BAR);
    }

    @Override
    protected void initListener() {

        mTvSkip.setOnClickListener(this);

        //设置banner样式 只有点 没有标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //加载图片
                Glide.with(context).load(path).into(imageView);
            }
        });

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setImages(bannerList);
        banner.start();

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_slide;
    }

    @Override
    protected void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.skip:
                Intent intent = new Intent(SlideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                //设置不是第一次进入
                RxSPTool.putBoolean(mContext,"isFirst",true);
                break;
        }
    }
}
