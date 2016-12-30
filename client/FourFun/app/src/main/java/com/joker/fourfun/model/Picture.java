package com.joker.fourfun.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joker on 2016/11/29.
 */

public class Picture implements Parcelable {
    /**
     * result : [{"picUrl":"/uploads/160903/161128/1-16112Q62024415.jpg","picDate":"27 Nov 2016  ",
     * "pubTime":"发布时间：2016-11-28 16:08","authorWork":"Grand-Central-Terminal",
     * "picDescription":"等待中有欢乐，有焦虑，有\u201c无可奈何\u201d与\u201c迫不及待\u201d，等待中包含人间百味。"},
     * {"picUrl":"/uploads/150129/161126/1-16112622162Y20.jpg","picDate":"26 Nov 2016  ",
     * "pubTime":"发布时间：2016-11-26 22:16","authorWork":"路过德黑兰",
     * "picDescription
     * ":"很多时候，人们内心所认为的可能并不是事实的真相，但还是宁愿去按照自己的思维逻辑来认识这个世界，认识自己的生活，到最后，可能我们就是带着这份误解，过完了此生。"},
     * {"picUrl":"/uploads/150129/161126/1-16112622154TC.jpg","picDate":"25 Nov 2016  ",
     * "pubTime":"发布时间：2016-11-26 22:15","authorWork":"探索",
     * "picDescription":"一流的情人永远不必殉陨，永远不会失恋，因为\u201c我爱你，与你何涉\u201d。"}]
     * error : false
     */
    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
    /**
     * picUrl : /uploads/160903/161128/1-16112Q62024415.jpg
     * picDate : 27 Nov 2016
     * VOL:VOL.1514
     * pubTime : 发布时间：2016-11-28 16:08
     * authorWork : Grand-Central-Terminal
     * picDescription : 等待中有欢乐，有焦虑，有“无可奈何”与“迫不及待”，等待中包含人间百味。
     */

    private String VOL;
    private String picUrl;
    private String picDate;
    private String pubTime;
    private String authorWork;
    private String picDescription;

    public Picture() {
    }

    protected Picture(Parcel in) {
        this.VOL = in.readString();
        this.picUrl = in.readString();
        this.picDate = in.readString();
        this.pubTime = in.readString();
        this.authorWork = in.readString();
        this.picDescription = in.readString();
    }

    public String getVOL() {
        return VOL;
    }

    public void setVOL(String VOL) {
        this.VOL = VOL;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicDate() {
        return picDate;
    }

    public void setPicDate(String picDate) {
        this.picDate = picDate;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getAuthorWork() {
        return authorWork;
    }

    public void setAuthorWork(String authorWork) {
        this.authorWork = authorWork;
    }

    public String getPicDescription() {
        return picDescription;
    }

    public void setPicDescription(String picDescription) {
        this.picDescription = picDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.VOL);
        dest.writeString(this.picUrl);
        dest.writeString(this.picDate);
        dest.writeString(this.pubTime);
        dest.writeString(this.authorWork);
        dest.writeString(this.picDescription);
    }
}
