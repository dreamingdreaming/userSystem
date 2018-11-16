package li.ren.servlet.roleServlet;

import li.ren.bean.Menu;
import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/page/selectMenuAllServlet")
public class SelectMenuAllServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> menuList = new ArrayList<>();
        try {
            menuList = menuDao.selectMenu("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("menuList",menuList);
        request.getRequestDispatcher("/page/Role/addRole.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
