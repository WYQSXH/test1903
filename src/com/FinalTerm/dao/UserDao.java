package com.FinalTerm.dao;

import com.FinalTerm.entity.Users;
import com.FinalTerm.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO类都是进行数据操作的类，是对于数据库中的数据做增删改查等操作的代码。
 * @author wangyongqi 2021/5/10
 */
public class UserDao {
    private JdbcUtil util=new JdbcUtil();
//程序设计中应遵循【开闭原则】，尽量不要在源代码上修改，可以使用方法重载的方式
//-------方法重载⏬ -------通过全局作用域对象得到Connection-----------start
    /**
     * 重写UserDao类下的add(Users user)方法
     */
    //用户注册
    public int add(Users user, HttpServletRequest request){
        //判断用户是否存在语句
        String sql1="select loginName from t_users where loginName=?";
        PreparedStatement ps1=util.createStatement(sql1,request);
        //插入数据语句
        String sql="insert into t_users(loginName,userName,password,sex,email)"+
                "values(?,?,?,?,?)";
        PreparedStatement ps=util.createStatement(sql,request);
        int result=0;
        try {
            ps1.setString(1,user.getLoginName());
            ResultSet rs=ps1.executeQuery();
            if(rs.next()){
                //如果查询出有用户信息，则证明存在该用户
                result=0;
            }else {
                //否则不存在该用户
                ps.setString(1,user.getLoginName());
                ps.setString(2,user.getUserName());
                ps.setString(3,user.getPassword());
                ps.setString(4,user.getSex());
                ps.setString(5,user.getEmail());
                result=ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
//-------方法重载⏫ -------通过全局作用域对象得到Connection-----------end

    //JDBC规范中，Connection创建与销毁最浪费时间
    //用户注册
    public int add(Users user){
        String sql="insert into users(loginName,userName,password,sex,email)"+
                "values(?,?,?,?,?)";
        PreparedStatement ps=util.createStatement(sql);
        int result=0;
        try {
            ps.setString(1,user.getLoginName());
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getSex());
            ps.setString(5,user.getEmail());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }

    //登录验证
    public int login(String loginName, String password){
        String sq1="select count(*) from t_users where loginName=? and password=?";
        PreparedStatement ps=util.createStatement(sq1);
        ResultSet rs=null;
        int result=0;
        try {
            ps.setString(1,loginName);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //用户信息查询
    public Users userQuery(String loginName){
        String login = null,name = null,password = null,sex = null,email = null;
        String sql="select * from t_users where loginName=?";
        PreparedStatement ps=util.createStatement(sql);
        ResultSet rs=null;
        Users user=null;
        try {
            ps.setString(1,loginName);
            rs=ps.executeQuery();
            while (rs.next()){
                login=rs.getString("loginName");
                name=rs.getString("userName");
                password=rs.getString("password");
                sex=rs.getString("sex");
                email=rs.getString("email");
            }
            user=new Users(null,login,name,password,sex,email);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return user;
    }
    //用户信息修改
    public int userUpdate(Users user){
        String sql="UPDATE t_users SET userName=?,password=?,sex=?,email=? WHERE loginName=?;";
        PreparedStatement ps=util.createStatement(sql);
        int result=0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getLoginName());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }

    //通过登录账号获取用户名
    public String getUserName(String loginName){
        String sq1="select userName from t_users where loginName=?";
        PreparedStatement ps=util.createStatement(sq1);
        ResultSet rs=null;
        String result="";
        try {
            ps.setString(1,loginName);
            rs=ps.executeQuery();
            while (rs.next()){
                result = rs.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

}
