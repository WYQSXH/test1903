package com.FinalTerm.controller;


import com.FinalTerm.dao.SongDao;
import com.FinalTerm.entity.Songs;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author wangyongqi
 * @date 2021/5/18 14:48
 */
public class SongAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //歌曲名，歌手，歌曲地址，歌曲图片地址
        String song=null,singer=null,songUrl=null,imgUrl=null;
        // 验证请求是否满足要求（post 请求 / enctype 是否以multipart打头
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 如果不满足要求就立即结束对该请求的处理
        if (!isMultipart) {
            return;
        }
        try{
            // FileItem 是表单中的每一个元素的封装
            // 创建一个 FileItem 的工厂类
            FileItemFactory factory = new DiskFileItemFactory();
            // 创建一个文件上传处理器（装饰设计模式）
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                //判断文件类型
                if(item.isFormField()){
                    //判断该表单项是否是普通类型
                    // 文本类型
                    String filename = item.getFieldName();
                    if (filename.equals("songName")) {
                        //如果是歌曲名称
                        song = item.getString("UTF-8");
                    }else if(filename.equals("singer")){
                        //如果是歌手
                        singer = item.getString("UTF-8");
                    }
                }else{
                    // 文件类型
                    // 获取文件后缀名,用于判断是歌曲类型还是图片类型
                    String songType = item.getName().substring(item.getName().lastIndexOf("."));
                    String imgType = item.getName().substring(item.getName().lastIndexOf("."));
                    if (".ogg".equals(songType) || ".flac".equals(songType) || ".mp3".equals(songType) || ".qmcflac".equals(songType)){
                        // 给文件重新命名防止重复
                        String songName = UUID.randomUUID() + songType;
                        String path="D:\\作业\\大二\\JSP & Servlet\\JSP\\IDEA_JSP\\Demo13_期末项目\\web\\music";
                        // 将上传的文件临时保存到指定目录
                        item.write(new File(path, songName));
                        // 把服务器中的路径添加到数据库中
                        songUrl = "../music/" + songName;

                    }else if(".jpg".equals(imgType) || ".png".equals(imgType) || ".gif".equals(imgType) || ".jpeg".equals(imgType)){
                        // 给文件重新命名防止重复
                        String imgName = UUID.randomUUID() + imgType;
                        String path="D:\\作业\\大二\\JSP & Servlet\\JSP\\IDEA_JSP\\Demo13_期末项目\\web\\music";
                        // 将上传的文件临时保存到指定目录
                        item.write(new File(path, imgName));
                        // 把服务器中的路径添加到数据库中
                        imgUrl = "../music/" + imgName;

                        // 将路径保存到数据库
                        Songs s=new Songs();
                        s.setSongName(song);
                        s.setSinger(singer);
                        s.setSongUrl(songUrl);
                        s.setImgUrl(imgUrl);
                        SongDao dao=new SongDao();
                        int result=dao.add(s,request);
                        if(result == 1){
                            response.getWriter().write("添加成功,下次登录时即可查看");
                        }else if(result == 2){
                            response.getWriter().write("添加失败，该歌曲已存在");
                        }else {
                            response.getWriter().write("添加失败，出错了");
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}