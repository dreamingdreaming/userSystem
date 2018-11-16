package li.ren.servlet.roleServlet;

import li.ren.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/page/delRoleAllServlet")
public class DelRoleAllServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose =  request.getParameter("choose");
        System.out.println(choose);
        boolean b = false;
        String[] c = choose.split(",");
        b =roleDao.delRoleAll(c);

        response.sendRedirect("/page/selectRoleNameServlet");
    }
}
