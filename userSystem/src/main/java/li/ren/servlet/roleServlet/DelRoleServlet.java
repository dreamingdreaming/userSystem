package li.ren.servlet.roleServlet;

import li.ren.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/page/delRoleServlet")
public class DelRoleServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int rid = 0;
        if (id!=null&&!id.equals("")){
            rid=Integer.parseInt(id);
        }
        boolean b  = roleDao.delRole(rid);

        response.sendRedirect("/page/selectRoleNameServlet");


    }
}
