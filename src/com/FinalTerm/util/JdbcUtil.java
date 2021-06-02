package com.FinalTerm.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

// JdbcUtil obj = new JdbcUtil();  obj.getCon()
// JdbcUtil obj = new JdbcUtil();  obj.createStatement();
// JdbcUtil.getCon();
public class JdbcUtil {

    final String URL="jdbc:mysql://localhost:3306/online_exam?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    final String USERNAME="root";
    final String PASSWORD="3619";
    PreparedStatement ps= null;
    //Connection创建和销毁最耗费时间,这里方法重载getCon()优化了创建Connection的方法
    Connection con = null;

//程序设计中应遵循【开闭原则】，尽量不要在源代码上修改，可以使用方法重载的方式
//-------方法重载⏬ -------通过全局作用域对象得到Connection-----------start
    /**
     * 重载当前JdbcUtil中的getCon()方法，提升创建Connection效率
     */
    public Connection getCon(HttpServletRequest request){
        //1.拿到全局作用域对象
        ServletContext application=request.getServletContext();
        //2.从全局作用域对象得到存放Connection的Map
        Map map= (Map) application.getAttribute("key1");
        //3.从Map中得到一个处于空闲状态的(value=true)的Connection
        // 以下原理参考：https://blog.csdn.net/u013506626/article/details/31002795
        // 将map中所有的键存入到Set集合，因为set具备迭代器，
        // 所以使用迭代方式取出所有的键，再根据get()方法，获取每一个键对应的值
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            con= (Connection) it.next();
            //取出当前key的value值，Connection地址是Map的key，value为true表示当前con为空闲状态
            boolean flag = (boolean) map.get(con);
            if (flag){
                //如果当前con取出的value为true，表示空闲，则停止循环，否则一直遍历，直到找到空闲的connection或者直到遍历结束
                //找到后，将当前Connection对应value值修改为false，表示占用
                map.put(con,false);
                break;
            }
        }
        //找到空闲的connection后，返回
        return con;
    }

    /**
     * 重载当前JdbcUtil中的createStatement(String sql)方法
     */
    public PreparedStatement createStatement(String sql,HttpServletRequest request){
        try {
            ps=getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * 重载close()，重载后不再销毁Connection，
     *    而是将改Connection的占用状态（false）改为空闲状态（true）
     */
    public void close(HttpServletRequest request){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application=request.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        map.put(con,true);

    }
//-------方法重载⏫ -------通过全局作用域对象得到Connection-----------end


    //静态代码块，类初始化的时候执行一次，执行完成便销毁
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //封装数据库连接对象
    public Connection getCon(){

        try {
            con = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //封装数据库操作对象
    public PreparedStatement createStatement(String sql){
        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // 销毁 ps与con
    public void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //销毁 ps,con,rs
    public  void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
