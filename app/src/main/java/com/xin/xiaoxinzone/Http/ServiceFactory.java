package com.xin.xiaoxinzone.Http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ServiceFactory {

    private static ServiceFactory factory;

    private Retrofit retrofit;

    public static ServiceFactory getInstance(){

        if (factory == null){
            synchronized (ServiceFactory.class){
                if (factory == null){
                    factory = new ServiceFactory();
                }
            }
        }

        return factory;
    }

    public <T> T createService(String baseUrl,Class<T> clz){

        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

      return retrofit.create(clz);

    }


}
