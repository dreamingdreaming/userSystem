package li.ren.servlet.loginServlet;

import li.ren.bean.User;
import li.ren.dao.UserDao;
import li.ren.util.MD5Util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private MD5Util md5Util = new MD5Util();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String str = request.getParameter("password");
        boolean flag = true;
        /*
        * 判断是否被加密过，
        * 加密过，在登录的时候就不要再加密了
        *
        *
        * */
        if (str.length() == 6 ){
             str = md5Util.JM(str);
        }
        String checkMa = request.getParameter("checkMa");
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        String code = (String) session.getAttribute("code");
        if (username==null){
            username="";
        }
        List<User> userList;
        User user =userDao.login(username,str);

        if (user!=null&&code.equals(checkMa)){
            request.setAttribute("uN",user.getUsername());
            if (servletContext.getAttribute("userlist")==null){
                userList = new ArrayList<>();
            }
            else {
                userList =(List<User>)servletContext.getAttribute("userlist");
            }
            for (User user1 : userList) {
                if (user1.getUsername().equals(username)){
                    flag = false;
                }
            }
            if (flag){
                session.setAttribute("user",user);
                userList.add(user);
            }
            servletContext.setAttribute("userlist",userList);
            request.getRequestDispatcher("/page/showMenuToIndexServlet").forward(request,response);
        }
        else {
            response.sendRedirect("/login.jsp");
        }
    }
    }