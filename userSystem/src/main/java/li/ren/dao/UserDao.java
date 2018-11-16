package li.ren.dao;

import li.ren.bean.Page;
import li.ren.bean.User;
import li.ren.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库 访问层 持久层
 */
public class UserDao extends JDBCUtil {
    /**
     * 添加用户的方法
     * @param user
     * @return
     */
    public boolean register(User user){
        String sql = "insert into user value(?,0,?,?,?,?,?,0,?,?)";
        Object[] param={user.getUsername(),user.getPassword(),user.getRealname(),user.getEmail(),user.getRoleName(),user.getImages(),user.getAge(),user.getSex()};
        boolean b= this.executePreUpdate(sql,param);
        this.closeRes();
        return b;
    }

    /**
     * 用户名、密码
     * @param username 用户名
     * @param password 密码
     * @return  用户信息
     * 登陆方法
     */
    public User login(String username,String password){
        User user=null;
        String sql="select *from user where username=? and password=?";
        Object[] params={username,password};
        ResultSet rs=this.executePrepQuerySql(sql,params);
        try {
            if (rs.next()){
                int id = rs.getInt(2);
                String name=rs.getString(1);
                String pwd=rs.getString(3);
                String realname = rs.getString(4);
                String email = rs.getString(5);
                String role = rs.getString(6);
                String image = rs.getString(7);
                int age = rs.getInt(9);
                int sex = rs.getInt(10);
                user=new User(id,name,pwd,realname,email,role,image,age,sex);
            }
            else {
                return null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeRes();
        return user;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean delUser(String id) {
        String  sql = "delete from user where id = ?";
        String[] params={id};
        boolean b = executePreUpdate(sql, params);
        this.closeRes();
        return b;
    }

    /**
     * 批量删除用户
     * @param c
     * @return
     */
    public boolean delUserAll(String[] c) {
        String sql = "delete from user where id = ?";
        boolean b=false;
        for (int i = 0; i <c.length;i++)
        {
            if (c[i]!=null&&!c[i].equals("")){
                Object[] arr= {c[i]};
                b = executePreUpdate(sql, arr);
                this.closeRes();
            }
        }
        return b;
    }

    /**
     * 预修改用户数据
     * @param id
     * @return
     */
    public User proEditUser(String id) {
        String sql="select *from  user where id='"+id+"'; ";
        ResultSet resultSet = executeSelectSql(sql);
        User user=null;
        try {
            while (resultSet.next()){
                String username = resultSet.getString(1);
                int uid = resultSet.getInt(2);
                String password = resultSet.getString(3);
                String realname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String role = resultSet.getString(6);
                String img = resultSet.getString(7);
                int age = resultSet.getInt(9);
                int sex = resultSet.getInt(10);

                user = new User(uid,username,password,realname,email,role,img,age,sex);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeRes();
        }
        return  user;
    }
    /**
     * 搜索用户，用户名模糊查询
     * @return
     */
    public List<User> selectUser(String username, String sex,Page page,int minAge,int maxAge) {
        StringBuffer sql = new StringBuffer();
        List<User> userS=null;
        if (username==null||username.equals("")){
            username="";
        }
        sql.append("select * from user where username like '%" + username + "%' and  1=1 ");
        int sexnum = 0;

        if (sex != null && !sex.equals("")) {
            if (sex.equals("女")) {
                sexnum = 0;
            } else if (sex.equals("男")) {
                sexnum = 1;

            } else {
                sexnum = 2;
            }
            sql.append(" and sex = " + sexnum + " ");
        }
        if (minAge>=0 && maxAge>=0){
            sql.append(" and age between "+minAge+" and "+maxAge+" ");
        }

            sql.append(" limit "+page.getStart()+",4");
        /*需要 首和尾  需要查询的数据*/
        /*首-------page.getNowPage()**/
        /*尾-------*/
        ResultSet resultSet = executeSelectSql(sql.toString()+";");
        userS = new ArrayList();
            try {
                while (resultSet.next()) {
                    String username1 = resultSet.getString(1);
                    int id = resultSet.getInt(2);
                    String password = resultSet.getString(3);
                    String realname = resultSet.getString(4);
                    String email = resultSet.getString(5);
                    String  role = resultSet.getString(6);
                    String img = resultSet.getString(7);
                    int age = resultSet.getInt(9);
                    int sex1 = resultSet.getInt(10);
                    userS.add(new User(id, username1, password, realname, email, role, img,age,sex1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                this.closeRes();
            }

        return userS;

    }

    /**
     * 查询所有
     * @return
     */
    public List<User> findAll() {
        List<User> usermes = new ArrayList();
        String sql = "select *from user";
        ResultSet resultSet = executeSelectSql(sql);
        try {
            while (resultSet.next()){
                String username = resultSet.getString(1);
                int id = resultSet.getInt(2);
                String password = resultSet.getString(3);
                String realname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String role = resultSet.getString(6);
                String img = resultSet.getString(7);
                int age = resultSet.getInt(9);
                int sex = resultSet.getInt(10);


                usermes.add(new User(id,username,password,realname,email,role,img,age,sex));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeRes();
        }
        return usermes;
    }

    /**
     * 通过查询条件 搜索一共有几条数据
     * @param username
     * @param sex
     * @param minAge
     * @param maxAge
     * @return
     */
    public int  selectUserCount(String username, String sex,int minAge,int maxAge) {
        StringBuffer sql =new StringBuffer();
        if (username==null||username.equals("")){
            username="";
        }
        sql.append("select count(*) from user where username like '%"+username+"%' and  1=1 ");
        int sexnum=0;
        int count=0;

        if (sex!=null&&!sex.equals("")){
            if (sex.equals("女")){
                sexnum=0;
            }
            else if (sex.equals("男")){
                sexnum=1;
            }
            else {
                sexnum=2;
            }
            sql.append(" and sex = "+sexnum+" ");
        }
        if (minAge>=0 && maxAge>=0){
            sql.append(" and age between "+minAge+" and "+maxAge+"");
        }
        ResultSet resultSet = executeSelectSql(sql.toString());
        try {
            if (resultSet.next()) {
                 count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeRes();
        }
        return count;
    }

    /**
     *  用于新建用户的时候，查询数据库里是否有相同用户名的用户
     * @param username
     * @return
     */
    public User selectUserNODouble(String username) {
        String sql = "select *from user where username = ?";
        Object[] params = {username};
        User user=null;
        ResultSet resultSet =  executePrepQuerySql(sql, params);
        try {
            while (resultSet.next()){
                String uN = resultSet.getString(1);
                int uid = resultSet.getInt(2);
                String password = resultSet.getString(3);
                String realname = resultSet.getString(4);
                String email = resultSet.getString(5);
                String role = resultSet.getString(6);
                String img = resultSet.getString(7);
                int age = resultSet.getInt(9);
                int sex = resultSet.getInt(10);
                user = new User(uid,uN,password,realname,email,role,img,age,sex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeRes();
        }
            return user;
    }

    /**
     * 把选中的用户的用户信息导出excel
     * @param choose
     * @return
     * @throws SQLException
     */
    public List<User> outUser(String choose) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where id in  ("+choose+")";
        ResultSet resultSet = this.executeSelectSql(sql);
        while (resultSet.next()) {
            String uN = resultSet.getString(1);
            int uid = resultSet.getInt(2);
            String password = resultSet.getString(3);
            String realname = resultSet.getString(4);
            String email = resultSet.getString(5);
            String role = resultSet.getString(6);
            String img = resultSet.getString(7);
            int age = resultSet.getInt(9);
            int sex = resultSet.getInt(10);
            userList.add(new User(uid,uN,password,realname,email,role,img,age,sex));
            }
            this.closeRes();
        return userList;
    }

    /**
     * 通过是否选中check框，去数据区设置statu
     * @param username
     * @param checked
     * @return
     */
    public boolean setRemberChecked(String username, int checked) {
        String sql = "update user  set rember="+checked+" where username = '"+username+"'";
        boolean b = this.executeUpdateSql(sql);
        this.closeRes();
        return   b ;
    }

    /**
     * 通过username 查询user 所有信息， 返回一个user对象
     * @param username
     * @return
     * @throws SQLException
     */
    public User selectUerByUserName(String username) throws SQLException {
        String sql = "select *from user where username = '"+username+"'";
        ResultSet resultSet = this.executeSelectSql(sql);
        User user=null;
        if (resultSet.next()) {
            String uN = resultSet.getString(1);
            int uid = resultSet.getInt(2);
            String password = resultSet.getString(3);
            String realname = resultSet.getString(4);
            String email = resultSet.getString(5);
            String role = resultSet.getString(6);
            String img = resultSet.getString(7);
            int rember = resultSet.getInt(8);
            int age = resultSet.getInt(9);
            int sex = resultSet.getInt(10);

            user = new User(uid,uN,password,realname,email,role,img,age,sex);
            user.setRember(rember);
        }
        this.closeRes();
        return user;

    }
}
