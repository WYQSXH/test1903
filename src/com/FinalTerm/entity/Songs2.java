package com.FinalTerm.entity;


public class Songs2 {
    private String songName=null;
    private String singer=null;
    private byte[] songUrl=null;
    private byte[] imgUrl=null;

    public Songs2(){

    }

    public Songs2(String songName, String singer, byte[] songUrl, byte[] imgUrl) {
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

    public byte[] getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(byte[] songUrl) {
        this.songUrl = songUrl;
    }

    public byte[] getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(byte[] imgUrl) {
        this.imgUrl = imgUrl;
    }
}
