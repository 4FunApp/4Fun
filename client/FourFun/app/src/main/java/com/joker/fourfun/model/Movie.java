package com.joker.fourfun.model;

import java.util.List;

/**
 * Created by joker on 2016/12/18.
 */

public class Movie {
    /**
     * result : [{"date":"2016-12-18","actor":[{"actorRole":"土方岁","actorName":"北野武 Kitano Takeshi",
     * "movieName":"御法度 Taboo"},{"actorRole":"加纳惣三","actorName":"松田龙平 Ryuhei Matsuda","movieName":"御法度
     * Taboo"},{"actorRole":"冲田总","actorName":"武田真治 Takeda Shinji","movieName":"御法度 Taboo"}],
     * "country":"日本 法国 英国","director":"大岛渚","playWriter":"大岛渚 司马辽太郎","pageUrl":"http://movie.mtime
     * .com/13830/","pic":"http://img31.mtime.cn/mt/2014/02/22/232532.77724748_270X405X4.jpg",
     * "briefIntro":"1865
     * 年夏的京都，法度森严的新选组正在遴选新队员，其中，下级武士田代彪蔵和美少年加纳惣三郎的剑术出类拔萃被选中入队。初入队，加纳就受命处决破坏法度的队员，加纳出色地完成了这个任务。不久，队中有人传说，加纳和田代有关系，同..","movieName":"御法度 Taboo","moreInfo":"http://movie.mtime.com/13830/plots.html","mark":"7.6"},{"date":"2016-12-18","actor":[{"actorRole":"SSgt. Matt E.","actorName":"乔什·哈奈特 Josh Hartnett","movieName":"黑鹰坠落 Black Hawk Down"},{"actorRole":"Spec. John G.","actorName":"伊万·麦克格雷格 Ewan McGregor","movieName":"黑鹰坠落 Black Hawk Down"},{"actorRole":"Capt. Mike S.","actorName":"詹森·艾萨克 Jason Isaacs","movieName":"黑鹰坠落 Black Hawk Down"}],"country":"美国","director":"雷德利·斯科特","playWriter":"Mark Bowden Ken Nolan","pageUrl":"http://movie.mtime.com/10801/","pic":"http://img31.mtime.cn/mt/2014/02/22/224725.76562418_270X405X4.jpg","briefIntro":"　　本片改编自一个真实的故事，故事发生在1993年10月3日，地点是在政局动荡的非洲国家索马里的首都：摩加迪沙。由美国陆军三角洲部队和游骑兵部队的120名特种精英组成的特别行动小组受命深入摩加迪沙完成双重使命：为当地群众提供人道..","movieName":"黑鹰坠落 Black Hawk Down","moreInfo":"http://movie.mtime.com/10801/plots.html","mark":"8.2"},{"date":"2016-12-18","actor":[{"actorRole":"Monc","actorName":"克林特·伊斯特伍德 Clint Eastwood","movieName":"黄昏双镖客 For a Few Dollars More"},{"actorRole":"El Indi","actorName":"吉昂·马利亚·沃隆特","movieName":"黄昏双镖客 For a Few Dollars More"},{"actorRole":"Col. Douglas.","actorName":"李·范·克里夫 Lee Van Cleef","movieName":"黄昏双镖客 For a Few Dollars More"}],"country":"意大利 西班牙 西德 摩纳哥","director":"赛尔乔·莱昂内","playWriter":"Fulvio Morsella 赛尔乔·莱昂内 ","pageUrl":"http://movie.mtime.com/17029/","pic":"http://img31.mtime.cn/mt/2014/02/23/000838.97426330_270X405X4.jpg","briefIntro":"　　伊斯特伍德继续扮演独来独往的无名枪手，意图缉拿变态狂的坏蛋印第奥换取奖金，不料半途杀出另一名厉害的枪手莫蒂默也有意捉拿印第奥，两人之间明争暗斗却谁也不能得手。最后两人只好合作对付坏蛋，事成后却发展莫蒂默根本不是为了..","movieName":"黄昏双镖客 For a Few Dollars More","moreInfo":"http://movie.mtime.com/17029/plots.html","mark":"8.4"},{"date":"2016-12-18","actor":[{"actorRole":"Thoma","actorName":"戴维·海明斯 David Hemmings","movieName":"放大 Blow-Up"},{"actorRole":"Jan","actorName":"瓦妮莎·雷德格瑞夫 Vanessa Redg..","movieName":"放大 Blow-Up"},{"actorRole":"Patrici","actorName":"莎拉·米尔斯 Sarah Miles","movieName":"放大 Blow-Up"}],"country":"英国 意大利","director":"米开朗基罗·安东尼奥尼","playWriter":"Julio Cortázar 托尼·诺格拉 ","pageUrl":"http://movie.mtime.com/15464/","pic":"http://img21.mtime.cn/mt/2011/03/14/180954.46921906_270X405X4.jpg","briefIntro":"摄影师托马斯在伦敦的\u2014家公园，偷拍了一组关于情人约会的照片。后来，照片上的那位女子拼命地想要得到这些底片，引起托马斯的怀疑。托马斯把照片不断放大，最后相信自己在照片上看到了\u2014具尸体和\u2014个拿着枪的人，他似乎发现了\u2014起谋杀..","movieName":"放大 Blow-Up","moreInfo":"http://movie.mtime.com/15464/plots.html","mark":"8.3"}]
     * error : false
     */
    /**
     * date : 2016-12-18
     * actor : [{"actorRole":"土方岁","actorName":"北野武 Kitano Takeshi","movieName":"御法度 Taboo"},
     * {"actorRole":"加纳惣三","actorName":"松田龙平 Ryuhei Matsuda","movieName":"御法度 Taboo"},
     * {"actorRole":"冲田总","actorName":"武田真治 Takeda Shinji","movieName":"御法度 Taboo"}]
     * country : 日本 法国 英国
     * director : 大岛渚
     * playWriter : 大岛渚 司马辽太郎
     * pageUrl : http://movie.mtime.com/13830/
     * pic : http://img31.mtime.cn/mt/2014/02/22/232532.77724748_270X405X4.jpg
     * briefIntro :
     * 1865年夏的京都，法度森严的新选组正在遴选新队员，其中，下级武士田代彪蔵和美少年加纳惣三郎的剑术出类拔萃被选中入队。初入队，加纳就受命处决破坏法度的队员，加纳出色地完成了这个任务。不久，队中有人传说，加纳和田代有关系，同..
     * movieName : 御法度 Taboo
     * moreInfo : http://movie.mtime.com/13830/plots.html
     * mark : 7.6
     */

    private String date;
    private String country;
    private String director;
    private String playWriter;
    private String pageUrl;
    private String pic;
    private String briefIntro;
    private String movieName;
    private String moreInfo;
    private String mark;
    private List<ActorBean> actor;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlayWriter() {
        return playWriter;
    }

    public void setPlayWriter(String playWriter) {
        this.playWriter = playWriter;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public List<ActorBean> getActor() {
        return actor;
    }

    public void setActor(List<ActorBean> actor) {
        this.actor = actor;
    }

    public static class ActorBean {
        /**
         * actorRole : 土方岁
         * actorName : 北野武 Kitano Takeshi
         * movieName : 御法度 Taboo
         */

        private String actorRole;
        private String actorName;
        private String movieName;

        public String getActorRole() {
            return actorRole;
        }

        public void setActorRole(String actorRole) {
            this.actorRole = actorRole;
        }

        public String getActorName() {
            return actorName;
        }

        public void setActorName(String actorName) {
            this.actorName = actorName;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }
    }

}