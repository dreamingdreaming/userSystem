<%@ page import="li.ren.bean.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: renl
  Date: 18-10-26
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线用户列表</title>
</head>
<body>
<%
    ServletContext servletContext = request.getServletContext();
    List<User> userlist =(List<User>) servletContext.getAttribute("userlist");


%>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>用户账户</th>
        <th>真实姓名</th>
        <th>角色</th>
        <th>头像</th>
    </tr>
    </thead>
    <%! String role = null;  %>
    <%
        for (User user : userlist) {
    %>
    <tr>
        <td style="vertical-align:middle;"><input type="checkbox" name="check" value="<%=user.getId() %>"></td>
        <td><%= user.getUsername() %>
        </td>
        <td>
            <%= user.getRealname()  %>
        </td>
        <td><%= user.getRoleName() %>
        </td>
        <td><img height="60px" width="60px" src="/upload/<%= user.getImages()%>">
            <a href="/page/download?filename=<%= user.getImages() %>">下载</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
