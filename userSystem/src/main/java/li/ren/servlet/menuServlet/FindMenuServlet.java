package li.ren.servlet.menuServlet;

import li.ren.bean.Menu;
import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/page/findMenuServlet")
public class FindMenuServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String menuname = request.getParameter("menuname");
        List<Menu> menuList = null;
        try {
            menuList = menuDao.selectMenu(menuname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("menuList",menuList);
        request.getRequestDispatcher("/page/Menu/index.jsp").forward(request,response);

    }
}
