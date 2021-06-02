package com.FinalTerm.controller;

import com.FinalTerm.dao.UserDao;
import com.FinalTerm.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangyongqi
 * @date 2021/5/10
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String loginName,userName,password,sex,email;
        UserDao dao=new UserDao();
        Users user=null;
        //声明一个结果变量
        int result=0;
        //声明一个输出流
        PrintWriter out=null;
        // 1.【调用请求对象】读取【请求头】里的参数信息，得到用户信息
        loginName=request.getParameter("loginName");
        userName=request.getParameter("userName");
        password=request.getParameter("password");
        sex=request.getParameter("sex");
        email=request.getParameter("email");
        // 2.【调用UserDao】将用户信息填充到INSERT命令中，借助JDBC规范发送到数据库服务器中
        user=new Users(null,loginName,userName,password,sex,email);
        result=dao.add(user,request);
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result == 1){
            //弹出对话框参考：https://www.cnblogs.com/lingy/archive/2012/09/12/2681920.html
            out.print("<script>alert('用户注册成功');window.location.href='/Demo13Web/main/login.html'</script>");
        }else {
            out.print("<script>alert('用户注册失败,该账号已存在');window.location.href='/Demo13Web/main/register.html'</script>");
        }
    }
}
