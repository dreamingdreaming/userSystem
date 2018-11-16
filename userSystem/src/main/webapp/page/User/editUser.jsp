

<%@ page import="li.ren.bean.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../../Css/style.css" />
    <script type="text/javascript" src="../../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../../Js/ckform.js"></script>
    <script type="text/javascript" src="../../Js/common.js"></script>

 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>

<%
    User user = (User) request.getAttribute("user");
%>

<form action="/page/editUser" method="post" class="definewidth m20" enctype="multipart/form-data">
<input type="hidden" name="id" value="<%= user.getId() %>" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft"><font color="red">*</font>登录名</td>
            <td><%= user.getUsername()%></td>
        </tr>
        <tr>
            <td class="tableleft"><font color="red">*</font>密码</td>
            <td><input type="password" name="password" value="<%= user.getPassword() %>"/></td>
        </tr>
        <tr>
            <td class="tableleft"><font color="red">*</font>真实姓名</td>
            <td><input type="text" name="realname" value="<%= user.getRealname() %>" /></td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="email" value="<%=user.getEmail()  %>"/></td>
        </tr>
        <tr>
            <td class="tableleft">头像</td>
            <td><input type="file" name="image"/></td>
        </tr>
        <tr>
            <td class="tableleft"><font color="red">*</font>角色</td>
            <td>
                <select name="role">
                    <option value="">--请选择--
                        <c:forEach var="role" items="${roleList}">
                    <option value="${role.rName}">${role.rName}
                        </c:forEach>
                </select>
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">更新</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="/page/User/index.jsp";
		 });
    });
</script>