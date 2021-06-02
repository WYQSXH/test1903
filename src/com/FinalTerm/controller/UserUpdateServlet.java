package com.FinalTerm.controller;

import com.FinalTerm.dao.UserDao;
import com.FinalTerm.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession(false);
        String loginName= request.getParameter("loginName");
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String email=request.getParameter("email");

        int result=0;
        //声明一个输出流
        PrintWriter out=null;
        Users user=new Users(null,loginName,userName,password,sex,email);
        UserDao dao=new UserDao();
        result=dao.userUpdate(user);

        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result == 1){
            session.setAttribute("user",user);
            //弹出对话框参考：https://www.cnblogs.com/lingy/archive/2012/09/12/2681920.html
            out.print("<script>alert('用户修改成功');window.location.href='/Demo13Web/main/index.jsp'</script>");
        }else {
            out.print("<script>alert('用户修改失败');window.location.href='/Demo13Web/main/userQuery.jsp'</script>");
        }

    }
}
