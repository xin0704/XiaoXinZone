package com.xin.xiaoxinzone.UI.Main.Joke.Service;

import com.xin.xiaoxinzone.Entity.Joke;
import com.xin.xiaoxinzone.Entity.Result;
import com.xin.xiaoxinzone.Params.Constant;
import com.xin.xiaoxinzone.Params.Urls;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface JokeService {

    @GET(Urls.textJokeUrl + "?key=" + Constant.JU_HE_JOKE_KEY)
    Observable<Result<Joke>> getTextJoke(@Query("sort") String sort,
                                         @Query("time") String time,
                                         @Query("page") int page,
                                         @Query("pageSize") int pageSize);

    @GET(Urls.imgJokeUrl + "?key=" + Constant.JU_HE_JOKE_KEY)
    Observable<Result<Joke>> getPicJoke(@Query("sort") String sort,
                                        @Query("time") String time,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize);
}
