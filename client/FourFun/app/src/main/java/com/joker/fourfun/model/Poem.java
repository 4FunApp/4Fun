package com.joker.fourfun.model;

/**
 * Created by joker on 2016/12/9.
 */

public class Poem {
    /**
     * result : [{"poemVoice":"http://www.zgshige.com/upload/resources/audio/2016/12/08/129765.mp3",
     * "poemDate":"2016-12-08","poemContent":"一日将息，你工分满了十个 偷下闲： 你爬上半山腰，让光头 　沐浴即将入崦的红太阳 这是你的专利时刻
     * 满意的，你摸下鼻头的肉痣 　连新松也答应你 为后来者指引前来的山路 坐转而站，于是你想 　择日不如撞日 这电流般的念头一过 我们便在多年以后之字般上升 　而你早叉好腰，穷尽 　你的千里目：
     * 丙申的春风为我们开路 杂草齐人高，醉得一一俯卧 　哦，是的 万不可不说的是相聚的沉默 我们与你用心眺望 　白云无尽时啊 仿佛翻滚涌动间穿越生死 而天色忽已冥，我们一侧身 向着卯酉向的尘世
     * 　一颗松果般下山去","poemTitle":"登黄斗坡","poemAuthor":"叶飙"}]
     * error : false
     */
    /**
     * poemVoice : http://www.zgshige.com/upload/resources/audio/2016/12/08/129765.mp3
     * poemDate : 2016-12-08
     * poemContent : 一日将息，你工分满了十个 偷下闲： 你爬上半山腰，让光头 　沐浴即将入崦的红太阳 这是你的专利时刻 满意的，你摸下鼻头的肉痣 　连新松也答应你
     * 为后来者指引前来的山路 坐转而站，于是你想 　择日不如撞日 这电流般的念头一过 我们便在多年以后之字般上升 　而你早叉好腰，穷尽 　你的千里目： 丙申的春风为我们开路
     * 杂草齐人高，醉得一一俯卧 　哦，是的 万不可不说的是相聚的沉默 我们与你用心眺望 　白云无尽时啊 仿佛翻滚涌动间穿越生死 而天色忽已冥，我们一侧身 向着卯酉向的尘世 　一颗松果般下山去
     * poemTitle : 登黄斗坡
     * poemAuthor : 叶飙
     */

    private String poemVoice;
    private String poemDate;
    private String poemContent;
    private String poemTitle;
    private String poemAuthor;

    public String getPoemVoice() {
        return poemVoice;
    }

    public void setPoemVoice(String poemVoice) {
        this.poemVoice = poemVoice;
    }

    public String getPoemDate() {
        return poemDate;
    }

    public void setPoemDate(String poemDate) {
        this.poemDate = poemDate;
    }

    public String getPoemContent() {
        return poemContent;
    }

    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent;
    }

    public String getPoemTitle() {
        return poemTitle;
    }

    public void setPoemTitle(String poemTitle) {
        this.poemTitle = poemTitle;
    }

    public String getPoemAuthor() {
        return poemAuthor;
    }

    public void setPoemAuthor(String poemAuthor) {
        this.poemAuthor = poemAuthor;
    }
}
