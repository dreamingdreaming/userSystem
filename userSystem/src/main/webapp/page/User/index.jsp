<%@ page import="java.util.List" %>
<%@ page import="li.ren.bean.User" %>
<%@ page import="li.ren.bean.Page" %><%--
  Created by IntelliJ IDEA.
  User: renl
  Date: 18-10-22
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../../Css/style.css"/>
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
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "/page/showMenuServlet";
            });
        });


        function checkall() {
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            if (ch.checked) {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = true;
                }
            } else {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = false;
                }
            }
        }

        function delAll() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            var choose = "";
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked){
                    ids.push(alls[i].value);
                    choose = choose + alls[i].value + ",";
                }

            }
            choose = choose.substring(0,choose.length-1);
            if (ids.length > 0) {
                if (confirm("确认删除?")) {
                    window.location.href = "/page/delUserAll?choose=" + choose + "";
                }
            } else {
                alert("请选中要删除的项");
            }
        }
        function outUser() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            var choose = "";
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked){
                    ids.push(alls[i].value);
                    choose = choose + alls[i].value + ",";
                }
            }
            choose = choose.substring(0,choose.length-1);
            if (ids.length > 0) {
                if (confirm("确认导出?")) {
                    window.location.href = "/page/outUserMessage?choose=" + choose + "";
                }
                }
            else {
                alert("请选中要导出用户的项");
            }

            
        }
        function test(nowPage) {
            /*获得表单*/
            var formpage = document.getElementById("formpage");

            /*添加一个input元素*/
            var  element = document.createElement("input");
            /*类型 --隐藏*/
            element.type='hidden';
            /*name 为nowPage----- 提交后requset获得的就是这个name的值*/
            element.name='nowPage';
            /*把现在的值传到servlet处理后， page 设置新的nowPage值*/
            element.value=nowPage;
            //将input框添加到表单中
            formpage.appendChild(element);
            //提交表单  ， 把现在所有页面的数据，提交
            formpage.submit();
        }
    </script>
</head>
<body>
<%--数据回显--%>
<%
    String username = request.getParameter("username");
    String sex = request.getParameter("sex");
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
%>
<form class="form-inline definewidth m20" action="/page/selectUser" method="get" id="formpage">
        用户名称：
        <input type="text" name="username" id="username" class="abc input-default" placeholder=""
               value="<c:if test="<%= username!=null%>"><%= username%></c:if>"
        >&nbsp;&nbsp;
        性别：
        <input type="text" name="sex" id="sex" class="abc input-default" placeholder=""
               value="<c:if test="<%= sex!=null%>"><%= sex%></c:if>">&nbsp;&nbsp;
        年龄：
        <input type="text" name="minAge" id="minAge" class="abc input-default" placeholder=""
               value="<c:if test="<%= minAge!=-1%>"><%= minAge%></c:if>">-<input type="text" name="maxAge" id="maxAge" class="abc input-default" placeholder=""
                                       value="<c:if test="<%= maxAge!=-1%>"><%= maxAge%></c:if>">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>用户账户</th>
        <th>真实姓名</th>
        <th>角色</th>
        <th>头像</th>
        <th>年龄</th>
        <th>性别</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
        <c:forEach var="user" items="${users}">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${user.id}"></td>
            <td>
                    ${user.username}
            </td>
            <td>
                    ${user.realname}
            </td>
            <td>
               ${user.roleName}
            </td>

            <td><img height="60px" width="60px" src="/upload/${user.images}">
                <a href="/page/download?filename=${user.images}">下载</a>
            </td>
            <td>
                ${user.age}
            </td>
            <td>
                <c:if test="${user.sex==0}">女</c:if>
                <c:if test="${user.sex==1}">男</c:if>
                <c:if test="${user.sex!=0&&user.sex!=1}"></c:if>
            </td>
            <td>
                <a href="/page/showMenuToEditServlet?id=${user.id}">编辑</a>&nbsp;&nbsp;&nbsp;<a
                    href="/page/delUser?id=${user.id}">删除</a>
            </td>
        </tr>

        </c:forEach>
</table>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <th colspan="5">
            <div class="inline pull-right page">
                ${pages.count}条记录 ${pages.nowPage}/${pages.totalPage}页
                    <c:if test="${pages.nowPage==1}">首页</c:if>
                    <c:if test="${pages.nowPage > 1}"> <a href="#" onclick="test(1)">首页</a></c:if>

                    <c:if test="${pages.nowPage>1}">
                        <a href='#' onclick="test(${pages.nowPage-1})"> 上一页</a>
                    </c:if>
                    <c:if test="${pages.nowPage==1}">上一页</c:if>
                    <c:if test="${pages.nowPage < pages.totalPage}">
                        <a href='#' onclick="test(${pages.nowPage+1})">下一页</a></c:if>
                    <c:if test="${pages.nowPage==pages.totalPage}">下一页</c:if>

                    <c:if test="${pages.nowPage==pages.totalPage}">尾页</c:if>
                    <c:if test="${pages.nowPage < pages.totalPage}"> <a href="#" onclick="test(${pages.totalPage})">尾页</a></c:if>
            </div>
            <div>
                <button type="button" class="btn btn-success" id="newNav">添加用户</button>&nbsp;&nbsp;&nbsp;<button
                    type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中
            </button>
                <button type="button" class="btn btn-success" onclick="outUser();">导出用户信息</button>
            </div>
        </th>
    </tr>
</table>
</form>
</body>
</html>