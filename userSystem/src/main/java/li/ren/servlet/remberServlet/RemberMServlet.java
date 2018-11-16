package li.ren.servlet.remberServlet;
import li.ren.bean.User;
import li.ren.dao.UserDao;
import li.ren.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 记住密码的实现
 */
@WebServlet(value = "/remberMServlet")
public class RemberMServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private MD5Util md5Util = new MD5Util();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user=null;

        try {
            user = userDao.selectUerByUserName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write(user.getPassword()+","+user.getRember());
        response.getWriter().flush();
    }
}
