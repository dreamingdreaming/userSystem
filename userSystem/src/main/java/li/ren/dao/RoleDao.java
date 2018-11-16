package li.ren.dao;

import li.ren.bean.Role;
import li.ren.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 关于角色的查询
 *
 * @author renl
 * @date 2018-11-07
 */
public class RoleDao {
    private JDBCUtil jdbcUtil = new JDBCUtil();

    /**
     * 根据角色ID 查找角色名
     * @param rid
     * @return
     */
    public String selectRoleNameByRid(int rid) throws SQLException {
        String roleName = null;
        String sql = "select r_name from role where rid =?";
        Object[] param={rid};

        ResultSet resultSet = jdbcUtil.executePrepQuerySql(sql, param);
        if (resultSet.next()){
            roleName = resultSet.getString(1);
        }
        jdbcUtil.closeRes();
        return roleName;
    }

    /**
     * 根据角色名 进行模糊查询
     * @param rolename
     * @return
     * @throws SQLException
     */
    public List<Role> selectRoleNameByName(String rolename) throws SQLException {
        List<Role> roleList = new ArrayList<>();
        if (rolename==null){
            rolename="";
        }
        String sql = "select * from role where r_name like '%"+rolename+"%'";
        ResultSet resultSet = jdbcUtil.executeSelectSql(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            roleList.add(new Role(id,name));
        }
        jdbcUtil.closeRes();

        return roleList;
    }

    public boolean delRole(int rid) {
        boolean b = true;
        String sql = "delete from role where rid =?";
        Object[] param = {rid};
        b = jdbcUtil.executePreUpdate(sql, param);
        jdbcUtil.closeRes();

        if (b){
            String sql1 = "delete from role_menu where rid =?";
            Object[] params = {rid};
            b=jdbcUtil.executePreUpdate(sql1,params);
            jdbcUtil.closeRes();
        }
        return b;
    }

    /**
     * 批量删除角色
     * @param c
     * @return
     */
    public boolean delRoleAll(String[] c) {
        String sql = "delete from role where rid = ?";
        boolean b=false;
        for (int i = 0; i <c.length;i++)
        {
            if (c[i]!=null&&!c[i].equals("")){
                Object[] arr= {c[i]};
                b = jdbcUtil.executePreUpdate(sql, arr);
                jdbcUtil.closeRes();
                if (b){
                    String sql1 = "delete from role_menu where rid =?";
                    b = jdbcUtil.executePreUpdate(sql1, arr);
                    jdbcUtil.closeRes();
                }
            }
        }
        return b;
    }


    public boolean updatePower(String[] c, int rid) {
        String sql = "insert into role_menu value (?,?)";
        boolean b;
        String sql1 = "delete from role_menu where rid = ?";
        Object[] param = {rid};
        b = jdbcUtil.executePreUpdate(sql1,param);
        jdbcUtil.closeRes();

        for (int i = 0; i <c.length;i++)
        {
            if (c[i]!=null&&!c[i].equals("")){
                Object[] arr= {rid,c[i]};
                b = jdbcUtil.executePreUpdate(sql, arr);
            }
        }

        jdbcUtil.closeRes();
        return b;
    }

    public Role selectRoleNameIs(String rolename) throws SQLException {
        String sql = "select *from role where r_name = ?";
        Object[] param = {rolename};
        Role role = null;
        ResultSet resultSet = jdbcUtil.executePrepQuerySql(sql, param);
        if (resultSet.next()){
            int rid = resultSet.getInt(1);
            String r_name = resultSet.getString(2);
            role = new Role(rid,r_name);
        }
        jdbcUtil.closeRes();
        return role;
    }

    public boolean addRoleWithPower(String rolename, String[] c) throws SQLException {
        boolean flag  = false;
        int rid = 0;
        String sql = "insert into role value (0,'"+rolename+"')";
        flag = jdbcUtil.executeUpdateSql(sql);
        if (flag){
            String sql1 ="select rid from role where r_name = '"+rolename+"'";
            ResultSet resultSet = jdbcUtil.executeSelectSql(sql1);
            if (resultSet.next()){
                rid  = resultSet.getInt(1);
            }
            jdbcUtil.closeRes();
            if (rid==0){
                flag=false;
            }
            if (flag){
                String sql2 = "insert into role_menu value (?,?)";
                for (int i = 0; i <c.length;i++)
                {
                    if (c[i]!=null&&!c[i].equals("")){
                        Object[] arr= {rid,c[i]};
                        flag = jdbcUtil.executePreUpdate(sql2, arr);
                        jdbcUtil.closeRes();
                    }
                }

            }
        }
        return flag;
    }
}
