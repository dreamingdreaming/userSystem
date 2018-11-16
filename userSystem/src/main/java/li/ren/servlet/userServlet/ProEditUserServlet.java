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
 * 用户信息预修改
 */
@WebServlet(value = "/page/proEditUser")
public class ProEditUserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        User user = userDao.proEditUser(id);
        if (user!=null){
            request.setAttribute("user",user);
            request.getRequestDispatcher("/page/User/editUser.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/page/selectUser");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
