package li.ren.servlet.menuServlet;

import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/page/addMenu")
public class AddMenuServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("++++++++++++");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String menuname = request.getParameter("menuname");
        System.out.println(menuname);
        boolean b =menuDao.addMenu(menuname);
        System.out.println(b);
        response.sendRedirect("/page/findMenuServlet");
    }
}
