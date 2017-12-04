package com.xin.xiaoxinzone.UI.Main.Me.Constellation.Service;

import com.xin.xiaoxinzone.Entity.Constellation;
import com.xin.xiaoxinzone.Entity.Result;
import com.xin.xiaoxinzone.Params.Constant;
import com.xin.xiaoxinzone.Params.Urls;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface ConstellationService {

    @GET(Urls.constellationUrl + "?key=" + Constant.JU_HE_XING_ZUO_KEY)
    Observable<Constellation.Day> loadDayConstellation(@Query("type") String type,
                                                       @Query("consName") String consName);

    @GET(Urls.constellationUrl + "?key=" + Constant.JU_HE_XING_ZUO_KEY)
    Observable<Constellation.Week> loadWeekConstellation(@Query("type") String type,
                                                         @Query("consName") String consName);

    @GET(Urls.constellationUrl + "?key=" + Constant.JU_HE_XING_ZUO_KEY)
    Observable<Constellation.Month> loadMonthConstellation(@Query("type") String type,
                                                           @Query("consName") String consName);

    @GET(Urls.constellationUrl + "?key=" + Constant.JU_HE_XING_ZUO_KEY)
    Observable<Constellation.Year> loadYearConstellation(@Query("type") String type,
                                                         @Query("consName") String consName);
}
