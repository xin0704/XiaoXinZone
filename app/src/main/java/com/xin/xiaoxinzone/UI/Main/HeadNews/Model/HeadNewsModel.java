package com.xin.xiaoxinzone.UI.Main.HeadNews.Model;

import com.xin.xiaoxinzone.Entity.HeadNews;
import com.xin.xiaoxinzone.Http.ServiceFactory;
import com.xin.xiaoxinzone.Http.XinSubscriber;
import com.xin.xiaoxinzone.Params.Urls;
import com.xin.xiaoxinzone.UI.Main.HeadNews.Serviece.HeadNewsService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HeadNewsModel {

    private HeadNewsModelListener listener;

    public interface HeadNewsModelListener {

        void onLoadHeadNewsDataSuccess(HeadNews headNews);

        void onLoadHeadNewsDataEmpty();

        void onLoadHeadNewsDataFailed(String err_msg);

    }

    public void setHeadNewsModelListener(HeadNewsModelListener listener) {
        this.listener = listener;
    }

    /**
     * @param type 分类
     */
    public void loadHeadNewsData(String type) {

        ServiceFactory.getInstance()
                .createService(Urls.baseHeadNewsUrl, HeadNewsService.class)
                .getHeadNewsService(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XinSubscriber<HeadNews>() {
                    @Override
                    public void onSuccess(HeadNews headNews) {
                        if (headNews != null) {
                            listener.onLoadHeadNewsDataSuccess(headNews);
                        } else {
                            listener.onLoadHeadNewsDataEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        listener.onLoadHeadNewsDataFailed(msg);
                    }
                });

    }
}
