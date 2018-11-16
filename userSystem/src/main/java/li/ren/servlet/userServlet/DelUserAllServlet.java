package li.ren.servlet.userServlet;

import li.ren.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 批量删除用户
 */
@WebServlet(value = "/page/delUserAll")
public class DelUserAllServlet extends HttpServlet {
    private UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose =  request.getParameter("choose");
        System.out.println(choose);
        String[] c = choose.split(",");
        userDao.delUserAll(c);
        response.sendRedirect("/page/selectUser");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
