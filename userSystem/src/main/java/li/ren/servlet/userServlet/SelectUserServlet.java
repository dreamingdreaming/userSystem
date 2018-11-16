package li.ren.servlet.userServlet;

import li.ren.bean.Page;
import li.ren.bean.User;
import li.ren.dao.RoleDao;
import li.ren.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 按需求搜索用户信息
 */
@WebServlet(value = "/page/selectUser")
public class SelectUserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private RoleDao roleDao = new RoleDao();
    private Page page=new Page();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String sex = request.getParameter("sex");
        String username = request.getParameter("username");
        String min  = request.getParameter("minAge");
        String max = request.getParameter("maxAge");
        int maxAge = 0;
        int minAge = 0;
        if (min!=null&&!min.equals("")){
             minAge = Integer.parseInt(min);
        }
        else {
            minAge = -1;
        }
        if (max!=null&&!min.equals("")){
            maxAge = Integer.parseInt(max);
        }
        else {
            maxAge = -1;
        }
        //如果username为空，查询所有
        List<User> userS = new ArrayList();
        //分页
        //pages 是 count （数据条数）
        int pages = userDao.selectUserCount(username,sex,minAge,maxAge);
        //设置count
        page.setCount(pages);
        String nowPage = request.getParameter("nowPage");
        if (nowPage!=null){
            int nowpage = Integer.parseInt(nowPage);
            page.setNowPage(nowpage);
        }
        userS = userDao.selectUser(username,sex,page,minAge,maxAge);

        request.setAttribute("pages",page);
        request.setAttribute("users",userS);
        request.getRequestDispatcher("/page/User/index.jsp").forward(request,response);
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
