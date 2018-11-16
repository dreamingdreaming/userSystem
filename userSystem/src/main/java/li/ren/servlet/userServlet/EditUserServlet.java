package li.ren.servlet.userServlet;

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
 * 修改用户信息
 */
@WebServlet(value = "/page/editUser")
public class EditUserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private MD5Util md5Util = new MD5Util();
    private UploadUtil uploadUtil = new UploadUtil();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, String> map = uploadUtil.upload(request);

        String password = map.get("password");
        String pwd = md5Util.JM(password);
        String realname = map.get("realname");
        String email = map.get("email");
        String role = map.get("role");
        String filename = map.get("fileName");
        String id = map.get("id");
        //存入数据库里图片的名称
        String sql = "update user set password=?," +
                "realname=?,email=? ,role_name =? ,images=? where id = ?";
        String[] params={pwd,realname,email,role,filename,id};
        boolean b = userDao.executePreUpdate(sql, params);
        userDao.closeRes();
        response.sendRedirect("/page/selectUser");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
