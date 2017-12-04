package com.xin.xiaoxinzone.Entity;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class Joke {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String content;

        private String hashId;

        private int unixtime;

        private String updatetime;

        private String url;

        private boolean isImg;//是否是趣图

        public boolean isImg() {
            if (TextUtils.isEmpty(url)){
                isImg = false;
            }else {
                isImg = true;
            }
            return isImg;
        }

        public void setImg(boolean img) {
            isImg = img;
        }

        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }

        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setHashId(String hashId){
            this.hashId = hashId;
        }
        public String getHashId(){
            return this.hashId;
        }
        public void setUnixtime(int unixtime){
            this.unixtime = unixtime;
        }
        public int getUnixtime(){
            return this.unixtime;
        }
        public void setUpdatetime(String updatetime){
            this.updatetime = updatetime;
        }
        public String getUpdatetime(){
            return this.updatetime;
        }
    }

}
