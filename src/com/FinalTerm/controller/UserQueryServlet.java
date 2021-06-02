package com.FinalTerm.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserQueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        UserDao dao=new UserDao();
//        HttpSession session=request.getSession(false);
//        Users user= (Users) session.getAttribute("user");
//        Users user=dao.userQuery(loginName);
//        session.setAttribute("user",user);
//        request.getRequestDispatcher("/main/userQuery.jsp").forward(request,response);
    }

}
