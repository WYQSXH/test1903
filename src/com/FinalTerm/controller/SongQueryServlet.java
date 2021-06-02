package com.FinalTerm.controller;

import com.FinalTerm.dao.SongDao;
import com.FinalTerm.entity.Songs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @web /song/find
 */
public class SongQueryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String keyword=request.getParameter("textbooksearcg").trim();
        SongDao dao = new SongDao();
        if (keyword!="") {
            //1.调用dao推送查询命令得到查询的歌曲
            List<Songs> list = dao.querySongs(keyword, request);
            //2.将得到的试题添加到请求作用域作为共享对象数据
            request.setAttribute("songs", list);
            //3.请求转发向tomcat调用questions.jsp将结果与html标签写入响应体
            request.getRequestDispatcher("/main/index.jsp").forward(request, response);
        }else {
            //如果搜索歌曲为空则查询出所有的歌曲
            //1.调用dao推送查询命令得到查询的歌曲
            List<Songs> list = dao.findAll(request);
            //2.将得到的试题添加到请求作用域作为共享对象数据
            request.setAttribute("songs", list);
            //3.请求转发向tomcat调用questions.jsp将结果与html标签写入响应体
            request.getRequestDispatcher("/main/index.jsp").forward(request, response);
        }
    }
}
