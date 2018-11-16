package li.ren.servlet.menuServlet;

import li.ren.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/page/updateMenuName")
public class updateMenuNameServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String menuname = request.getParameter("menuname");
        String id = request.getParameter("mid");
        int mid = 0;
        if (id!=null&&!id.equals("")){
            mid = Integer.parseInt(id);
        }

        boolean flag = menuDao.upDateMenuName(menuname,mid);
        response.sendRedirect("/page/findMenuServlet");
    }
}
