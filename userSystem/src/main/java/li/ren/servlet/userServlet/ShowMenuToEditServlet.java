package li.ren.servlet.userServlet;

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

@WebServlet(value = "/page/showMenuToEditServlet")
public class ShowMenuToEditServlet extends HttpServlet {
    private RoleDao roleDao =new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = roleDao.selectRoleNameByName("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("roleList",roleList);
        for (Role role : roleList) {
        }

        request.getRequestDispatcher("/page/proEditUser").forward(request,response);
    }
}
