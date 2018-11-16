package li.ren.servlet.roleServlet;

import li.ren.bean.Menu;
import li.ren.dao.MenuDao;
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

@WebServlet(value = "/page/editRoleMenuShowServlet")
public class EditRoleMenuShowServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDao();
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int rid = 0;
        String s=null;
        if (id!=null&&!id.equals("")){
            rid=Integer.parseInt(id);
        }
        try {
             s = roleDao.selectRoleNameByRid(rid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String menuname = "";
        List<Menu> menulist = new ArrayList<>();
        try {
            menulist = menuDao.selectMenu(menuname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("menulist",menulist);
        request.setAttribute("name",s);
        request.getRequestDispatcher("/page/Role/editRole.jsp").forward(request,response);
    }
}
