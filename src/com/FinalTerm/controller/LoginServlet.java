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

/**
 * @author wangyongqi 2021/5/10
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String loginName,password;
        UserDao dao=new UserDao();
        //声明一个结果变量
        int result=0;
        //声明一个输出流
        PrintWriter out=null;

        // 1.【调用请求对象】读取【请求头】里的参数信息，得到用户信息
        loginName=request.getParameter("loginName");
        password=request.getParameter("password");
        //2.调用Dao将查询验证信息推送到数据库服务器上
        result = dao.login(loginName, password);
        //3.调用响应对象，根据验证结果将不同资源文件地址写入到响应头，交给浏览器
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result == 1){
            //在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户申请一个HttpSession
            HttpSession session=request.getSession();
            Users user=dao.userQuery(loginName);
            session.setAttribute("user",user);
//            session.setAttribute("loginName",loginName);
//            session.setAttribute("userName",userName);
            //弹出对话框参考：https://www.cnblogs.com/lingy/archive/2012/09/12/2681920.html
            out.print("<script>alert('用户登录成功');window.location.href='/Demo13Web/main/index.jsp'</script>");
        }else {
            out.print("<script>alert('用户登录失败,账号或密码错误');window.location.href='/Demo13Web/main/login.html'</script>");
        }
    }
}
