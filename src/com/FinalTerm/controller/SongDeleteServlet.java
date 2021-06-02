package com.FinalTerm.controller;

import com.FinalTerm.dao.SongDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SongDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String songId;
        SongDao dao = new SongDao();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        int result = 0;
        //1.调用请求对象读取请求头参数信息，得到试题编号
        songId = request.getParameter("songId");

        //删除保存在本地的临时文件
        File deleteFile=new File("D:\\作业\\大二\\JSP & Servlet\\JSP\\IDEA_JSP\\Demo13_期末项目\\web\\music");
        Map<String,String> fileNameMap=dao.queryFileName(songId,request);
        String songFileName=fileNameMap.get("songFileName");
        String songImgName=fileNameMap.get("songImgName");
        new SongDeleteServlet().delete(deleteFile,songFileName,songImgName);

        //2.调用Dao对象将删除命令推送到数据库服务器
        result = dao.delete(songId);
        //3.调用info.jsp将处理结果写入到响应体
        if(result ==1){
            out.print("<script>alert('歌曲删除成功');window.location.href='/Demo13Web/song/query'</script>");
        }else{
            out.print("<script>alert('歌曲删除成功');window.location.href='/Demo13Web/song/query'</script>");
        }

    }

    private void delete(File file,String songFileName,String songImgName) {
        //isDirectory()是检查一个对象是否是文件夹
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            //步骤① 取得目录以后，遍历其下面所有的文件夹和文件。
            //碰到文件时，判断其名称是否为你要删除的音乐文件或者图片文件，如果是-删除，如果不是-继续遍历，
            //碰到文件夹(目录)时，调用本身从（步骤①）开始遍历该目录，完成后继续遍历。
            //这个需要用到递归
            for (File file2 : files) {
                delete(file2,songFileName,songImgName);
            }
        } else {
            String name = file.getName();
            if (songFileName.equals(name) || songImgName.equals(name)) {
                System.out.println(file.getAbsolutePath());
                file.delete();
            }
        }
    }
}
