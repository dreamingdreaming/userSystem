package li.ren.servlet.menuServlet;

import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/page/delMenuAll")
public class DelMenuAllServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose =  request.getParameter("choose");
        String[] c = choose.split(",");
        boolean b =menuDao.delMenull(c);
        response.sendRedirect("/page/findMenuServlet");
    }
}
