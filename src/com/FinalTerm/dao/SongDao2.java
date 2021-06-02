package com.FinalTerm.dao;

import com.FinalTerm.entity.Songs2;
import com.FinalTerm.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDao2 {
    private JdbcUtil util=new JdbcUtil();

    //歌曲上传
    public int add(Songs2 song, HttpServletRequest request){
        //判断歌曲是否存在语句
        String sql1="select songName from t_song where songName=?";
        PreparedStatement ps1=util.createStatement(sql1,request);
        //插入数据语句
        String sql="insert into t_song(songName,singer,songUrl,imgUrl)"+
                "values(?,?,?,?)";
        PreparedStatement ps=util.createStatement(sql,request);
        int result=0;
        try {
            ps1.setString(1,song.getSongName());
            ResultSet rs=ps1.executeQuery();
            if(rs.next()){
                //如果查询出有歌曲信息，则证明存在该歌曲
                result=0;
            }else {
                //否则不存在该用户
                ps.setString(1,song.getSongName());
                ps.setString(2,song.getSinger());
                /* 需要得到blob
                 * 1.我们有的是文件，目标是Blob
                 * 2.先把文件变成byte[]
                 * 3.再使用byte[]创建Blob
                 * https://blog.csdn.net/weixin_30675247/article/details/96695359?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control
                 * */
                Blob blobImage=new SerialBlob(song.getImgUrl());
                ps.setBlob(3,blobImage);
                Blob blobFile=new SerialBlob(song.getSongUrl());
                ps.setBlob(4,blobFile);
                result=ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
}
