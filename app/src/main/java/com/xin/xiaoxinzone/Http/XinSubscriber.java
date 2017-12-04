package com.xin.xiaoxinzone.Http;

import android.accounts.NetworkErrorException;

import com.xin.xiaoxinzone.Entity.Result;
import com.xin.xiaoxinzone.Params.Constant;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/11/15.
 */

public abstract class XinSubscriber<T> extends Subscriber<Result<T>> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof NullPointerException){
            onError("没有获取到想看的数据");
        }else if (e instanceof NetworkErrorException ||
                e instanceof RuntimeException){
            onError("网络离家出走了");
        }else {
            onError("获取数据失败");
        }

    }

    @Override
    public void onNext(Result<T> tResult) {

        if (Constant.LOAD_HEAD_NEWS_SUCCESS_CODE == tResult.getError_code()){
            onSuccess(tResult.getResult());
        }else {
            onError(tResult.getReason());
        }

    }


    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

}
