package li.ren.servlet.sessionServlet;

import li.ren.bean.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 用户---下线
 */
@WebServlet(value = "/page/delSession")
public class DelSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁session对象
        ServletContext servletContext = getServletContext();
        List<User> userList = (List<User>) servletContext.getAttribute("userlist");
        User user = (User) session.getAttribute("user");
        User testUser=null;
        /*ArrayList 在遍历的时候是不能移除集合里的元素的*/
        for (User user1 : userList) {
            if (user1.getUsername().equals(user.getUsername())){
                testUser=user1;
            }
        }
        if (testUser!=null){
            session.invalidate();
        }
        response.sendRedirect("/login.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
