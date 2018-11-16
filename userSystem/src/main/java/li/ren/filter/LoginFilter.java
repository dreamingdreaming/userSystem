package li.ren.filter;

import li.ren.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//需要配置 web.xml---------告诉服务器有这个过滤器，并且告诉服务器 这个 过滤器的位置
public class LoginFilter implements Filter {
    public LoginFilter(){

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            //放行
            filterChain.doFilter(servletRequest,servletResponse);

        }else{
            response.sendRedirect("/login.jsp");
        }
    }

    @Override
    public void destroy() {
        //销毁

    }
}
