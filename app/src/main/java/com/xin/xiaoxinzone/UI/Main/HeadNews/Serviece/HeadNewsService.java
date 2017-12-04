package com.xin.xiaoxinzone.UI.Main.HeadNews.Serviece;

import com.xin.xiaoxinzone.Entity.HeadNews;
import com.xin.xiaoxinzone.Entity.Result;
import com.xin.xiaoxinzone.Params.Constant;
import com.xin.xiaoxinzone.Params.Urls;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Administrator on 2017/11/15.
 */

public interface HeadNewsService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST(Urls.headNewsUrl + "?key=" + Constant.JU_HE_HEAD_NEWS_KEY)
    Observable<Result<HeadNews>> getHeadNewsService(@Query("type") String type);
}
