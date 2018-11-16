package li.ren.servlet.checkNum;

import li.ren.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * 验证码的创建和设置
 */
@WebServlet(value = "/checkMaServlet")
public class CheckMaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        OutputStream out = response.getOutputStream();

        Map<String,Object> map = ImageUtil.getImagePic();

        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg",out);

        session.setAttribute("code",map.get("code"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
