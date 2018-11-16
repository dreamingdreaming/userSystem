package li.ren.servlet.roleServlet;

import li.ren.bean.Role;
import li.ren.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/page/doubleRoleName")
public class RolnameDoubleeServlet extends HttpServlet {
    RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String rolename = request.getParameter("rolename");
        Role role = null;
        try {
            role = roleDao.selectRoleNameIs(rolename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String msg = "该角色已存在，不能再创建";
        if (role==null){
            msg = "该角色名可以使用";
        }
        response.getWriter().write(msg);
        response.getWriter().flush();

    }
}
