package com.joker.fourfun.model;

/**
 * Created by joker on 2016/11/27.
 */

public class Zhihu {

    /**
     * result : [{"img":"https://pic4.zhimg.com/v2-9727fa19bc357dde5b0ab9d0eee9e657_xxdpi.jpg",
     * "text":"Ishan @seefromthesky"}]
     * error : false
     * img : https://pic4.zhimg.com/v2-9727fa19bc357dde5b0ab9d0eee9e657_xxdpi.jpg
     * text : Ishan @seefromthesky
     */
    private String img;
    private String text;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
