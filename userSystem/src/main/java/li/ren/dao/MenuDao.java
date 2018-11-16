package li.ren.dao;

import li.ren.bean.Menu;
import li.ren.bean.User;
import li.ren.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作菜单的sql
 *
 * @author renl
 * @date 2018-11-07
 */
public class MenuDao {
    private JDBCUtil jdbcUtil = new JDBCUtil();

    /**
     * 搜索查询菜单
     * @param menuname
     * @return
     * @throws SQLException
     */
    public List<Menu> selectMenu(String menuname) throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        if (menuname==null){
            menuname="";
        }
        String sql = "select * from menu where m_name like '%"+menuname+"%'";
        ResultSet resultSet = jdbcUtil.executeSelectSql(sql);
        while (resultSet.next()){
            int mid = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String url = resultSet.getString(3);
            menuList.add(new Menu(mid,name,url));
        }
        jdbcUtil.closeRes();
        return menuList;
    }

    /**
     * 修改菜单
     * @param menuname
     * @param mid
     * @return
     */
    public boolean upDateMenuName(String menuname,int mid) {
        String sql = "update  menu set m_name=? where mid =?";
        Object[] param ={menuname,mid};
        boolean b = jdbcUtil.executePreUpdate(sql,param);
        jdbcUtil.closeRes();
        return b;
    }

    /**
     * 删除菜单
     * @param mid
     * @return
     */
    public boolean delMenu(int mid) {
        String sql = "delete from menu where mid = ?";
        Object[] param = {mid};
        boolean b = jdbcUtil.executePreUpdate(sql, param);
        jdbcUtil.closeRes();
        return b;
    }

    public boolean addMenu(String menuname) {
        String sql = "insert into menu(m_name,url) value (?,?)";
        String url ="/page/ing.jsp";
        Object[] param = {menuname,url};
        boolean b = jdbcUtil.executePreUpdate(sql, param);
        jdbcUtil.closeRes();
        return false;
    }

    /**
     * 批量删除菜单
     * @param c
     * @return
     */
    public boolean delMenull(String[] c) {
        String sql = "delete from menu where mid = ?";
        boolean b=false;
        for (int i = 0; i <c.length;i++)
        {
            if (c[i]!=null&&!c[i].equals("")){
                Object[] arr= {c[i]};
                b = jdbcUtil.executePreUpdate(sql, arr);
            }
        }
        jdbcUtil.closeRes();
        return b;

    }

    public List<Menu> selectMenuByUser(User user) throws SQLException {

        String username = user.getUsername();
        String sql="SELECT mid,m_name,url from menu where mid in(SELECT mid from role_menu WHERE rid in (select rid from role where r_name = (SELECT role_name from user where username = ?)))";
        Object[] param ={username};
        List<Menu> menuList = new ArrayList<>();
        ResultSet resultSet = jdbcUtil.executePrepQuerySql(sql, param);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String mN = resultSet.getString(2);
            String url = resultSet.getString(3);
            menuList.add(new Menu(id,mN,url));
        }
        jdbcUtil.closeRes();
        return  menuList;
    }
}
