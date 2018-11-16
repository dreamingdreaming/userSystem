package li.ren.util;

import java.sql.*;

/**
 * 执行sql语句的工具类
 */
public class JDBCUtil {
    private DruidUtil druidUtil = new DruidUtil();
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private PreparedStatement ps;
    //获取连接
    //自动提交事务
    /**
     * 基于自动提交事务的连接
     */
    private void getConn() {
        try {
            connection =druidUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("自动获取连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //手动提交事务
    /**
     * 非自动提交事务的连接
     */
    private void getNotAutoConnection() {
        getConn();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("手动获取连接失败");
        }
    }
    private  void  createnotAutoStatement(String sql){
        getNotAutoConnection();
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建手动连接通道失败");
        }
    }
    /**
     * 创建通道
     */
    private void createStatement() {
        getConn();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建通道失败");
        }
    }
    /**
     * 创建prestatement
     */

    private void createpreStatement(String sql) {
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建prestatement失败");
        }
    }


    /**
     * 执行sql修改语句
     *
     * @param sql
     * @return
     */
    public boolean executeUpdateSql(String sql) {
        createStatement();
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行修改sql语句失败");
        }
        return false;
    }

    //关闭资源

    /**
     * 释放资源
     */
    public void closeRes() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (rs!=null)
            {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行sql查询语句,返回ResultSet
     */
    public ResultSet executeSelectSql(String sql) {
        createStatement();
        try {
            rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行查询sql语句失败");
        }
        return null;
    }

    /**
     * 创建预状态通道
     */
    private void createPrepStatement(String sql) {
        getConn();
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建预状态通道失败！");
        }
    }


    /**
     * @param sql
     * @param param
     * @return
     */
    public ResultSet executePrepQuerySql(String sql, Object[] param) {
        createPrepStatement(sql);
        bandle(param);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行sql失败");
        }
        return rs;
    }

    public boolean executePreUpdate(String sql, Object[] param) {
        createPrepStatement(sql);
        bandle(param);
        int i = 0;
        try {
            i = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("预状态修改失败!");
        }
        if (i > 0) {
            System.out.println("修改成功");
            return true;
        } else {
            return false;
        }
    }

    private void createStatement2() {
        getNotAutoConnection();
        try {
            connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建状态通道失败");
        }
    }


    public boolean executeBatchSql(String[] sqls) {
        createStatement2();
        int[] ints = null;
        try {
            for (int i = 0; i < sqls.length; i++) {
                statement.addBatch(sqls[i]);
            }
            ints = statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (ints != null) {
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] < 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }




    public boolean executeBatchSql2(String sql, Object[][] params) {
        createnotAutoStatement(sql);
        int[] ints = null;
        if (params != null) {
            try {
                for (int i = 0; i < params.length; i++) {
                    Object[] param = params[i];
                    bandle(param);
                    ps.addBatch();
                }
                ints = ps.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (ints != null) {
                for (int i = 0; i < ints.length; i++) {
                    if (ints[i] < 0) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }


    /**
     * 绑定参数
     * @param param
     */
    private void  bandle(Object[] param){
        if (param!=null){
            try {
                for (int i=0;i<param.length;i++)
                {
                    ps.setObject(i+1,param[i]);
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }



}


