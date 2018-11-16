package li.ren.servlet.userServlet;

import li.ren.bean.User;
import li.ren.dao.MenuDao;
import li.ren.dao.UserDao;
import li.ren.util.MD5Util;
import li.ren.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 添加用户
 */
@WebServlet(value = "/page/addUser")
public class AddUserServlet extends HttpServlet {
    private UploadUtil uploadUtil = new UploadUtil();
    private UserDao userDao = new UserDao();
    private MenuDao menuDao = new MenuDao();
    private MD5Util md5Util = new MD5Util();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //必须 通过Map  获得非file类型参数
        Map<String, String> map = uploadUtil.upload(request);
        String username =map.get("username");
        String str = map.get("password");
        String password = md5Util.JM(str);
        String realname = map.get("realname");
        String email = map.get("email");
        String role = map.get("role");
        String age = map.get("age");
        String sex = map.get("sex");
        int sexint;
        int ageint = Integer.parseInt(age);
        //存入数据库里图片的名称
        if (sex.equals("女")){
            sexint = 0;
        }
        else if (sex.equals("男")){
            sexint = 1;
        }
        else {
            sexint=2;
        }
        String fileName = map.get("fileName");

        if ((username != null && !username.equals("")) && (password != null && !password.equals("")) && (realname != null && !realname.equals("")) &&
                (email != null && !email.equals(""))
                        ){
            boolean b = userDao.register(new User(0, username, password, realname, email, role, fileName,ageint,sexint));

        }
        response.sendRedirect("/page/selectUser");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
