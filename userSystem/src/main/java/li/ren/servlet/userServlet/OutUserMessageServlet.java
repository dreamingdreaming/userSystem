package li.ren.servlet.userServlet;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import li.ren.bean.User;
import li.ren.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 导出excel
 */
@WebServlet(value = "/page/outUserMessage")
public class OutUserMessageServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose =  request.getParameter("choose");
        List<User> userList = null;
        try {
            userList = userDao.outUser(choose);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workbook.createSheet("用户数据", 0);
        try {
            sheet.addCell(new Label(0,0,"编号"));
            sheet.addCell(new Label(1,0,"姓名"));
            sheet.addCell(new Label(2,0,"密码"));
            sheet.addCell(new Label(3,0,"真实姓名"));
            sheet.addCell(new Label(4,0,"电子邮件"));
            sheet.addCell(new Label(5,0,"职位"));
            for (int i = 0; i < userList.size() ; i++) {
                sheet.addCell(new Label(0,i+1,userList.get(i).getId()+""));
                sheet.addCell(new Label(1,i+1,userList.get(i).getUsername()));
                sheet.addCell(new Label(2,i+1,userList.get(i).getPassword()));
                sheet.addCell(new Label(3,i+1,userList.get(i).getRealname()));
                sheet.addCell(new Label(4,i+1,userList.get(i).getEmail()));
                sheet.addCell(new Label(5,i+1,userList.get(i).getRoleName()));

            }

            //设置文件下载的名称
            //将中文字符串 按照utf-8的编码规则进行 拆分为一个字节数组
            byte[] bytes = "用户信息.xlsx".getBytes("UTF-8");
            //按照国际编码 将字节数组重新排列
            String s= new String(bytes,"ISO-8859-1");
            //告诉服务器   下载的文件名称叫什么
            response.setHeader("Content-Disposition","attachment;filename="+s);
            workbook.write();
            workbook.close();

        } catch (WriteException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
