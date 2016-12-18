package com.joker.fourfun.model;

/**
 * Created by joker on 2016/11/29.
 */

public class ArticleOne {
    /**
     * result : [{"articleTitle":"一叶之吻","pubTime":"2016-12-08","articleUrl":"http://www.wufafuwu
     * .com//a/ONE_wenzhang/2016/1208/5416.html","article":"content","articleAuthor":"七堇年"}]
     * error : false
     */
    /**
     * articleTitle : 一叶之吻
     * pubTime : 2016-12-08
     * articleUrl : http://www.wufafuwu.com//a/ONE_wenzhang/2016/1208/5416.html
     * article : content
     * articleAuthor : 七堇年
     */

    private String articleTitle;
    private String pubTime;
    private String articleUrl;
    private String article;
    private String articleAuthor;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }
}
