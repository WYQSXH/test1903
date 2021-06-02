package com.FinalTerm.listener;

import com.FinalTerm.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wangyongqi
 * 在导入驱动包遇到的 坑
 *      1.需要在WEB-INF下创建lib文件夹，并在里面放入mysql驱动包
 *      2.右键--> Mark Director as --> Generated Sources Root
 *          这样做是吧lib文件夹设置成了 resources【资源文件夹】
 *          https://blog.csdn.net/weixin_40040107/article/details/90699250
 */
public class OneListener implements ServletContextListener {

    /**
     * 在Tomcat启动的时候，预先创建20个Connection
     *      在dao.UserDao.add()方法执行时，
     *      将事先创建好的Connection交给add()方法
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil util=new JdbcUtil();
        Map map=new HashMap();
        for (int i = 0; i < 20; i++) {
            Connection con=util.getCon();
            System.out.println("在Http服务器启动时，创建了Connection"+con);
            //所有的Connection用Map集合存放
            //key是每个Connection的内存地址，
            // value是 true表示这个通道处于空闲状态，false表示这个通道正在被使用
            map.put(con,true);
        }
        //为了在Http服务器运行期间，一直都可以使用这20个Connection，所以
        //将map交给【全局作用域对象】
        ServletContext application=sce.getServletContext();
        application.setAttribute("key1",map);

    }

    /**
     * 在Tomcat启动的时候，销毁20个Connection
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application=sce.getServletContext();
        //取出application中key为“key1”的元素，即存放Connection的map
        Map map= (Map) application.getAttribute("key1");
        //以下原理参考：https://blog.csdn.net/u013506626/article/details/31002795
        // 将map中所有的键存入到Set集合，因为set具备迭代器，
        // 所以使用迭代方式取出所有的键，再根据get()方法，获取每一个键对应的值
        Iterator iterator=map.keySet().iterator();
        //遍历迭代器
        while (iterator.hasNext()){
            //这里Map中的key就是每个Connection的地址，所以不用再取出value，直接将key赋值给Connection
            Connection con= (Connection) iterator.next();
            if (con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Connection————"+con+"已被销毁");
            }
        }
    }
}
