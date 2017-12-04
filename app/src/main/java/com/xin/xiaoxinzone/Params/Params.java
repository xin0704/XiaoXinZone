package com.xin.xiaoxinzone.Params;

import com.alibaba.fastjson.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/14.
 */

public class Params {

    /**
     * 获取新闻头条数据
     *
     * @param type
     * @return
     */
    public static RequestBody getHeadNewsData(String type) {

        JSONObject object = new JSONObject();

        object.put("type", type);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), object.toString());

        return requestBody;
    }

}
