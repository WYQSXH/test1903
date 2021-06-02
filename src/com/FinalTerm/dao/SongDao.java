package com.FinalTerm.dao;

import com.FinalTerm.entity.Songs;
import com.FinalTerm.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongDao {
    private JdbcUtil util=new JdbcUtil();
    PreparedStatement ps=null,ps1=null;

    //歌曲上传
    public int add(Songs song, HttpServletRequest request){
        //判断歌曲是否存在语句
        String sql1="select songName from t_song where songName=?";
        ps1=util.createStatement(sql1,request);
        //插入数据语句
        String sql="insert into t_song(songName,singer,songUrl,imgUrl)"+
                "values(?,?,?,?)";
        ps=util.createStatement(sql,request);
        ResultSet rs =null;
        int result=0;
        try {
            ps1.setString(1,song.getSongName());
            rs=ps1.executeQuery();
            if(rs.next()){
                //如果查询出有歌曲信息，则证明存在该歌曲
                result=2;
            }else {
                //否则不存在该歌曲
                ps.setString(1,song.getSongName());
                ps.setString(2,song.getSinger());
                ps.setString(3,song.getSongUrl());
                ps.setString(4,song.getImgUrl());
                result=ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return result;
    }

    //遍历
    public List<Songs> findAll(HttpServletRequest request){
        List<Songs> list =new ArrayList<Songs>();
        String sql="select * from t_song";
        this.ps1=this.util.createStatement(sql,request);
        ResultSet rs= null;
        try {
            rs = this.ps1.executeQuery();
            while(rs.next()){
                Songs s=new Songs();
                s.setId(rs.getInt(1));
                s.setSongName(rs.getString(2));
                s.setSinger(rs.getString(3));
                s.setSongUrl(rs.getString(4));
                s.setImgUrl(rs.getString(5));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return list;

    }

    //歌曲删除
    public int delete(String songId) {
        String sql="delete from t_song where _songId=?";
        PreparedStatement ps = util.createStatement(sql);
        int result =0;
        try {
            ps.setInt(1, Integer.valueOf(songId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }
    //查询要删除的歌曲和图片的文件名
    public Map<String,String> queryFileName(String songId,HttpServletRequest request){
        String sql="select * from t_song where _songId=?";
        PreparedStatement ps = util.createStatement(sql,request);
        ResultSet rs=null;
        Map<String,String> fileNameMap=new HashMap<String, String>();
        try {
            ps.setInt(1, Integer.valueOf(songId));
            rs = ps.executeQuery();
            while(rs.next()){
                String songUrl=rs.getString("songUrl");
                String imgUrl=rs.getString("imgUrl");
                fileNameMap.put("songFileName",songUrl.substring(9,songUrl.length()));
                fileNameMap.put("songImgName",imgUrl.substring(9,imgUrl.length()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return fileNameMap;
    }

    //通过关键词查询歌曲信息
    public List<Songs> querySongs(String keyword,HttpServletRequest request){
        List<Songs> list =new ArrayList<Songs>();
        ResultSet rs= null;
        String sql="select * from t_song where songName like ? or singer like ?";
        this.ps1=this.util.createStatement(sql,request);
        try {
            ps1.setString(1,"%"+keyword+"%");
            ps1.setString(2,"%"+keyword+"%");
            rs = this.ps1.executeQuery();
            while(rs.next()){
                Songs s=new Songs();
                s.setId(rs.getInt(1));
                s.setSongName(rs.getString(2));
                s.setSinger(rs.getString(3));
                s.setSongUrl(rs.getString(4));
                s.setImgUrl(rs.getString(5));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return list;
    }
}
