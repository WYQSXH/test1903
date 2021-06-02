package com.FinalTerm.controller;

import com.FinalTerm.dao.SongDao;
import com.FinalTerm.entity.Songs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SongQueryAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongDao dao=new SongDao();
        //1.调用dao推送查询命令得到所有的试题
        List<Songs> list=dao.findAll(request);
        //2.将得到的试题添加到请求作用域作为共享对象数据
        request.setAttribute("songs",list);
        //3.请求转发向tomcat调用questions.jsp将结果与html标签写入响应体
        request.getRequestDispatcher("/main/songQueryAll.jsp").forward(request,response);
    }
}
