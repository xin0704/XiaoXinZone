package com.xin.xiaoxinzone.UI.Main.Me.Constellation.Model;

import com.xin.xiaoxinzone.Entity.Constellation;
import com.xin.xiaoxinzone.Entity.Result;
import com.xin.xiaoxinzone.Http.ServiceFactory;
import com.xin.xiaoxinzone.Http.XinSubscriber;
import com.xin.xiaoxinzone.Params.Constant;
import com.xin.xiaoxinzone.Params.Urls;
import com.xin.xiaoxinzone.UI.Main.Me.Constellation.Service.ConstellationService;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadConstellationModel {

    private LoadDayConstellationListener dayListener;

    private LoadWeekConstellationListener weekListener;

    private LoadMonthConstellationListener monthListener;

    private LoadYearConstellationListener yearListener;

    public interface LoadDayConstellationListener {

        void onLoadConstellationSuccess(Constellation.Day constellation);

        void onLoadConstellationFailed(String err_msg);
    }

    public interface LoadWeekConstellationListener {

        void onLoadConstellationSuccess(Constellation.Week constellation);

        void onLoadConstellationFailed(String err_msg);
    }

    public interface LoadMonthConstellationListener {

        void onLoadConstellationSuccess(Constellation.Month constellation);

        void onLoadConstellationFailed(String err_msg);
    }

    public interface LoadYearConstellationListener {

        void onLoadConstellationSuccess(Constellation.Year constellation);

        void onLoadConstellationFailed(String err_msg);
    }

    public void setLoadDayConstellationListener(LoadDayConstellationListener listener) {
        this.dayListener = listener;
    }

    public void setLoadWeekConstellationListener(LoadWeekConstellationListener listener) {
        this.weekListener = listener;
    }

    public void setLoadMonthConstellationListener(LoadMonthConstellationListener listener) {
        this.monthListener = listener;
    }

    public void setLoadYearConstellationListener(LoadYearConstellationListener listener) {
        this.yearListener = listener;
    }

    /**
     * 加载星座运势数据
     *
     * @param type     加载的是今日的 还是明日的
     * @param consName 星座名字
     */
    public void loadDayConstellation(String type, String consName) {

        ServiceFactory.getInstance()
                .createService(Urls.baseConstellationUrl, ConstellationService.class)
                .loadDayConstellation(type, consName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Constellation.Day>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dayListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + e.toString());
                    }

                    @Override
                    public void onNext(Constellation.Day constellation) {
                        if (constellation == null) {
                            dayListener.onLoadConstellationFailed("暂无数据");
                        } else {

                            if (Constant.LOAD_HEAD_NEWS_SUCCESS_CODE == constellation.getError_code()) {

                                //设置综合指数
                                constellation.setAllLevel(Float.parseFloat(constellation.getAll().replace("%","")) * 0.1f);
                                //设置工作指数
                                constellation.setWorkLevel(Float.parseFloat(constellation.getWork().replace("%","")) * 0.1f);
                                //设置健康指数
                                constellation.setHealthLevel(Float.parseFloat(constellation.getHealth().replace("%","")) * 0.1f);
                                //设置爱情指数
                                constellation.setLoveLevel(Float.parseFloat(constellation.getLove().replace("%","")) * 0.1f);
                                //设置金钱指数
                                constellation.setMoneyLevel(Float.parseFloat(constellation.getMoney().replace("%","")) * 0.1f);

                                dayListener.onLoadConstellationSuccess(constellation);
                            } else {
                                dayListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + constellation.getReason());
                            }

                        }
                    }
                });


    }



    /**
     * 加载星座运势数据
     *
     * @param type     加载的是本周的 还是下周的
     * @param consName 星座名字
     */
    public void loadWeekConstellation(String type, String consName) {

        ServiceFactory.getInstance()
                .createService(Urls.baseConstellationUrl, ConstellationService.class)
                .loadWeekConstellation(type, consName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Constellation.Week>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        weekListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + e.toString());
                    }

                    @Override
                    public void onNext(Constellation.Week constellation) {
                        if (constellation == null) {
                            weekListener.onLoadConstellationFailed("暂无数据");
                        } else {

                            if (Constant.LOAD_HEAD_NEWS_SUCCESS_CODE == constellation.getError_code()) {

                                weekListener.onLoadConstellationSuccess(constellation);
                            } else {
                                weekListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + constellation.getReason());
                            }

                        }
                    }
                });


    }



    /**
     * 加载星座运势数据
     *
     * @param type     加载的是本周的 还是下周的
     * @param consName 星座名字
     */
    public void loadMonthConstellation(String type, String consName) {

        ServiceFactory.getInstance()
                .createService(Urls.baseConstellationUrl, ConstellationService.class)
                .loadMonthConstellation(type, consName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Constellation.Month>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        monthListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + e.toString());
                    }

                    @Override
                    public void onNext(Constellation.Month constellation) {
                        if (constellation == null) {
                            monthListener.onLoadConstellationFailed("暂无数据");
                        } else {

                            if (Constant.LOAD_HEAD_NEWS_SUCCESS_CODE == constellation.getError_code()) {

                                monthListener.onLoadConstellationSuccess(constellation);
                            } else {
                                monthListener.onLoadConstellationFailed("获取星座运势数据失败，原因：" + constellation.getReason());
                            }

                        }
                    }
                });

    }


}
