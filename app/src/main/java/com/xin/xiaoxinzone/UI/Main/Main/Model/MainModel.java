package com.xin.xiaoxinzone.UI.Main.Main.Model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;

import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.UI.Main.Main.Activity.MainActivity;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class MainModel {

    /**
     * @param manager
     * @param fragmentList
     * @param showTag
     * @param needAdd
     */
    public void setShowFragment(FragmentManager manager, List<Fragment> fragmentList, int showTag, boolean needAdd) {

        FragmentTransaction transaction = manager.beginTransaction();

        for (int i = 0; i < fragmentList.size(); i++) {
            //判断是否需要添加fragment
            if (needAdd) {
                //添加fragment
                transaction.add(R.id.home_container, fragmentList.get(i));
            }
            //判断显示 和 隐藏
            if (i == showTag) {
                transaction.show(fragmentList.get(i));
            } else {
                transaction.hide(fragmentList.get(i));
            }
        }
        transaction.commitAllowingStateLoss();

    }

    /**
     * @param tvList
     * @param imgList
     * @param showTag
     */
    public void setShowFragmentTextStyle(Context context, List<TextView> tvList, List<ImageView> imgList, int showTag) {

        if (tvList.size() != imgList.size()) {
            return;
        }

        //设置字体颜色   设置选择的button背景图片
        for (int i = 0; i < tvList.size(); i++) {
            //设置字体颜色
            if (i == showTag) {
                tvList.get(i).setTextColor(ResourceTools.getColor(context, R.color.color_1c84d1));
            } else {
                tvList.get(i).setTextColor(ResourceTools.getColor(context, R.color.color_202122));
            }

        }

        //设置选择的button背景图片
        if (showTag == MainActivity.home_head_news_tag) {
            //头条选中
            imgList.get(MainActivity.home_head_news_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.blue_head_news));
            imgList.get(MainActivity.home_joke_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_discovery));
            imgList.get(MainActivity.home_me_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_me));

        } else if (showTag == MainActivity.home_joke_tag) {
            //发现选中
            imgList.get(MainActivity.home_head_news_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_head_news));
            imgList.get(MainActivity.home_joke_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.blue_discovery));
            imgList.get(MainActivity.home_me_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_me));

        } else if (showTag == MainActivity.home_me_tag) {
            //我的选中
            imgList.get(MainActivity.home_head_news_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_head_news));
            imgList.get(MainActivity.home_joke_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.gray_discovery));
            imgList.get(MainActivity.home_me_tag).setImageDrawable(ResourceTools.getDrawable(context, R.mipmap.blue_me));
        }


    }

}
