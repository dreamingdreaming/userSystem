package li.ren.servlet.menuServlet;

import li.ren.bean.Menu;
import li.ren.bean.User;
import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/page/showMenuToIndexServlet")
public class showMenuToIndexServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Menu> menulist = new ArrayList<>();
        try {
            menulist=menuDao.selectMenuByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("menulist",menulist);
        request.getRequestDispatcher("/page/index.jsp").forward(request,response);

    }
}
