package li.ren.listener;


import li.ren.bean.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * session监听器
 */
public class SessiondelListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session销毁了");
        HttpSession session = httpSessionEvent.getSession();
        //销毁session对象
        ServletContext servletContext = session.getServletContext();
        User delUser = null;
        List<User> userList = (List<User>) servletContext.getAttribute("userlist");
        User user = (User) session.getAttribute("user");
        //从列表中删除该用户信息
        userList.remove(user);
    }
}
