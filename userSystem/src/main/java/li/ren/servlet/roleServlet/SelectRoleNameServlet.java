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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/page/selectRoleNameServlet")
public class SelectRoleNameServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String rolename = request.getParameter("rolename");
        List<Role> roleList  = new ArrayList<>();
        try {
            roleList = roleDao.selectRoleNameByName(rolename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("/page/Role/index.jsp").forward(request,response);
    }
}
