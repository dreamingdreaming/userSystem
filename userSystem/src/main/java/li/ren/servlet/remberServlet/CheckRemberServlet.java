package li.ren.servlet.remberServlet;

import li.ren.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 确认哪些用户选中了 记住密码
 * 设置 记住密码 的状态
 */
@WebServlet(value = "/checkedrember")
public class CheckRemberServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String checked = request.getParameter("checked");
        int check = Integer.parseInt(checked);
        boolean isOK =  userDao.setRemberChecked(username,check);
        response.getWriter().print(isOK);
    }
}
