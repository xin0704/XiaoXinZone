package com.xin.xiaoxinzone.UI.Main.Joke.Model;

import com.xin.xiaoxinzone.Entity.HeadNews;
import com.xin.xiaoxinzone.Entity.Joke;
import com.xin.xiaoxinzone.Http.ServiceFactory;
import com.xin.xiaoxinzone.Http.XinSubscriber;
import com.xin.xiaoxinzone.Params.Urls;
import com.xin.xiaoxinzone.UI.Main.Joke.Service.JokeService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/15.
 */

public class LoadJokeModel {

    private LoadJokeListener listener;

    private final int TEXT_JOKE_SIZE = 20;

    private final int IMG_JOKE_SIZE = 10;

    private final String SORT_TYPE_DESC = "desc";//desc:指定时间之前发布的

    private final String SORT_TYPE_ASC = "asc";//asc:指定时间之后发布的


    public interface LoadJokeListener {

        void onLoadTextJokeSuccess(Joke joke);

        void onLoadMoreTextJokeSuccess(Joke joke);

        void onLoadTextJokeFail(String err_msg);

        void onLoadMoreTextJokeFail(String err_msg);

        void onLoadPicJokeSuccess(Joke joke);

        void onLoadMorePicJokeSuccess(Joke joke);

        void onLoadPicJokeFail(String err_msg);

        void onLoadMorePicJokeFail(String err_msg);
    }

    public void setLoadJokeListener(LoadJokeListener listener) {
        this.listener = listener;
    }

    /**
     * 加载文本笑话
     */
    public void loadTextJoke(String time) {

        ServiceFactory.getInstance()
                .createService(Urls.baseJokeUrl, JokeService.class)
                .getTextJoke(SORT_TYPE_DESC,time,1, TEXT_JOKE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XinSubscriber<Joke>() {
                    @Override
                    public void onSuccess(Joke joke) {
                        if (joke != null) {
                            listener.onLoadTextJokeSuccess(joke);
                        } else {
                            listener.onLoadTextJokeFail("暂无搞笑段子");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        listener.onLoadTextJokeFail(msg);
                    }
                });

    }

    public void loadPicJoke(String time) {

        ServiceFactory.getInstance()
                .createService(Urls.baseJokeUrl, JokeService.class)
                .getPicJoke(SORT_TYPE_DESC,time,1, IMG_JOKE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XinSubscriber<Joke>() {
                    @Override
                    public void onSuccess(Joke joke) {
                        if (joke != null) {
                            listener.onLoadPicJokeSuccess(joke);
                        } else {
                            listener.onLoadPicJokeFail("暂无趣图");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        listener.onLoadPicJokeFail("获取趣图失败，原因：" + msg);
                    }
                });

    }

    /**
     * 加载更多搞笑段子
     * @param page
     */
    public void loadMoreTextJoke(String time,int page) {
        //如果page小于1的话 就去加载第一页数据
        if (page <= 1) {
            loadTextJoke(time);
            return;
        }


        ServiceFactory.getInstance()
                .createService(Urls.baseJokeUrl, JokeService.class)
                .getTextJoke(SORT_TYPE_DESC,time,page, TEXT_JOKE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XinSubscriber<Joke>() {
                    @Override
                    public void onSuccess(Joke joke) {
                        if (joke != null) {
                            listener.onLoadMoreTextJokeSuccess(joke);
                        } else {
                            listener.onLoadMoreTextJokeFail("暂无更多搞笑段子");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        listener.onLoadTextJokeFail("获取搞笑段子失败，原因：" + msg);
                    }
                });

    }

    /**
     * 加载更多趣图
     * @param page
     */
    public void loadMorePicJoke(String time,int page) {

        //如果page小于1的话 就去加载第一页数据
        if (page <= 1) {
            loadPicJoke(time);
            return;
        }

        ServiceFactory.getInstance()
                .createService(Urls.baseJokeUrl, JokeService.class)
                .getPicJoke(SORT_TYPE_DESC,time,page, IMG_JOKE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XinSubscriber<Joke>() {
                    @Override
                    public void onSuccess(Joke joke) {
                        if (joke != null) {
                            listener.onLoadMorePicJokeSuccess(joke);
                        } else {
                            listener.onLoadMorePicJokeFail("暂无更多趣图");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        listener.onLoadPicJokeFail("获取趣图失败，原因：" + msg);
                    }
                });
    }

}
