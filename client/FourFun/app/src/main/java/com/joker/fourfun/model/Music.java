package com.joker.fourfun.model;

/**
 * Created by joker on 2016/12/18.
 */

public class Music {
    /**
     * result : [{"date":"2016-12-18","songName":"sarabande","imgUrl":"http://musicdata.baidu
     * .com/data2/pic/a5a2e67d6487b47b3e22fef8dc9cec03/117398934/117398934.jpg","songLink":"http://192
     * .168.97.2:8080/4Fun/MusicFile/sarabande.mp3","albumName":"Earthsongs","lrcLink":"http://musicdata
     * .baidu.com/data2/lrc/90520824de6603bc7ba49aa698f7af0f/264850036/264850036.lrc",
     * "albumId":"8558446","artistId":"2033605","artistName":"secret garden","queryId":"10301840"},
     * {"date":"2016-12-18","songName":"小步舞曲","imgUrl":"http://musicdata.baidu
     * .com/data2/pic/cc0891af88e76f765d2c92264e6408f3/120897185/120897185.jpg","songLink":"http://192
     * .168.97.2:8080/4Fun/MusicFile/小步舞曲.mp3","albumName":"","lrcLink":"","albumId":"0",
     * "artistId":"328653","artistName":"贝多芬","queryId":"1990049"},{"date":"2016-12-18","songName":"玉蝴蝶",
     * "imgUrl":"http://musicdata.baidu
     * .com/data2/pic/6771e20ec5d5c242b448eaf20ea6abc6/262032067/262032067.jpg","songLink":"http://192
     * .168.97.2:8080/4Fun/MusicFile/玉蝴蝶.mp3","albumName":"钢琴恋曲101","lrcLink":"http://musicdata.baidu
     * .com/data2/lrc/9cedf4d0524b08b5c30310c06f78bb81/266966725/266966725.lrc","albumId":"7311788",
     * "artistId":"36","artistName":"谢霆锋","queryId":"7316954"},{"date":"2016-12-18",
     * "songName":"原来只要共你活一天","imgUrl":"http://musicdata.baidu.com/data2/pic/117382137/117382137.jpg",
     * "songLink":"http://192.168.97.2:8080/4Fun/MusicFile/原来只要共你活一天.mp3","albumName":"张学友钢琴恋曲",
     * "lrcLink":"http://musicdata.baidu
     * .com/data2/lrc/1bad855b0dae307dd5cc206eb95ab532/288820154/288820154.lrc","albumId":"7312901",
     * "artistId":"317486","artistName":"纯音乐","queryId":"7317355"},{"date":"2016-12-18","songName":"纸飞机",
     * "imgUrl":"http://musicdata.baidu.com/data2/pic/117475047/117475047.jpg","songLink":"http://192.168
     * .97.2:8080/4Fun/MusicFile/纸飞机.mp3","albumName":"2002钢琴恋曲Piano Hits","lrcLink":"",
     * "albumId":"7311881","artistId":"317486","artistName":"纯音乐","queryId":"7319517"},
     * {"date":"2016-12-18","songName":"windancer","imgUrl":"http://musicdata.baidu
     * .com/data2/pic/70b8ffeef3c0453bba94efcddd3e09fa/115439088/115439088.jpg","songLink":"http://192
     * .168.97.2:8080/4Fun/MusicFile/windancer.mp3","albumName":"White Stones","lrcLink":"",
     * "albumId":"8034721","artistId":"2033605","artistName":"secret garden","queryId":"8035127"}]
     * error : false
     */
    /**
     * date : 2016-12-18
     * songName : sarabande
     * imgUrl : http://musicdata.baidu
     * .com/data2/pic/a5a2e67d6487b47b3e22fef8dc9cec03/117398934/117398934.jpg
     * songLink : http://192.168.97.2:8080/4Fun/MusicFile/sarabande.mp3
     * albumName : Earthsongs
     * lrcLink : http://musicdata.baidu
     * .com/data2/lrc/90520824de6603bc7ba49aa698f7af0f/264850036/264850036.lrc
     * albumId : 8558446
     * artistId : 2033605
     * artistName : secret garden
     * queryId : 10301840
     */

    private String date;
    private String songName;
    private String imgUrl;
    private String songLink;
    private String albumName;
    private String lrcLink;
    private String albumId;
    private String artistId;
    private String artistName;
    private String queryId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }
}
