package li.ren.servlet.userServlet;

import li.ren.bean.User;
import li.ren.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从数据库中判断 是否重名用户名
 */
@WebServlet(value = "/page/doubleUserName")
public class DoubleNameServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type:text/html","UTF-8");
        String username = request.getParameter("username");
        User users = userDao.selectUserNODouble(username);
        String msg = "该用户名已存在，不能再创建";
        if (users==null){
            msg = "该用户名可以使用";
        }
        response.getWriter().write(msg);
        response.getWriter().flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
