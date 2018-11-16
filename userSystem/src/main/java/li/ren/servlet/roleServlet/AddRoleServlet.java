package li.ren.servlet.roleServlet;

import li.ren.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/page/addRole")
public class AddRoleServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String rolename = request.getParameter("rolename");
        String choosenum = request.getParameter("choosenum");
        boolean b=false;
        String[] c = choosenum.split(",");
        try {
           b=roleDao.addRoleWithPower(rolename,c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (b){
            response.sendRedirect("/page/selectRoleNameServlet");
        }
        else {
            response.sendRedirect("/page/selectMenuAllServlet");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
