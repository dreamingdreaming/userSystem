package li.ren.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接池
 *
 */
public class DruidUtil {
    private static  final DruidDataSource  druidDataSource= new DruidDataSource() ;
    static String  url = "jdbc:mysql://localhost:3306/jsptest";
    static String user = "root";
    static String password = "123";
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        //加载驱动
        //静态块的特点：随着类的加载而加载，并且只加载一次
        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        /*设置数据池 最大连接数*/
        druidDataSource.setMaxActive(20);
        return  druidDataSource.getConnection();
    }

}
