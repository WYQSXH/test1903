package com.FinalTerm.entity;


public class Songs {
    private int id;
    private String songName=null;
    private String singer=null;
    private String songUrl=null;
    private String imgUrl=null;

    public Songs(){

    }

    public Songs(int id, String songName, String singer, String songUrl, String imgUrl) {
        this.id = id;
        this.songName = songName;
        this.singer = singer;
        this.songUrl = songUrl;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songName='" + songName + '\'' +
                ", singer='" + singer + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
