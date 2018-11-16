<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: renl
  Date: 18-11-7
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function(){
                window.location.href="/page/Menu/addMenu.jsp";
            });
        });


        function checkall(){
            var alls=document.getElementsByName("check");
            var ch=document.getElementById("checkall");
            if(ch.checked){
                for(var i=0;i<alls.length;i++){
                    alls[i].checked=true;
                }
            }else{
                for(var i=0;i<alls.length;i++){
                    alls[i].checked=false;
                }
            }
        }
        function delAll(){
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
                    window.location.href = "/page/delMenuAll?choose=" + choose + "";
                }
            } else {
                alert("请选中要删除的项");
            }
        }
        function addmenu() {
            window.location.href="/page/addMenu";
        }
    </script>

</head>
<body>

<form class="form-inline definewidth m20" action="/page/findMenuServlet" method="get" >
    菜单名称：
    <%String mn = request.getParameter("menuname"); %>
    <input type="text" name="menuname" id="menuname"class="abc input-default" placeholder="" value="<c:if test="<%=mn!=null%>"><%= mn%></c:if>">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>菜单名称</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menuList}">
        <tr>
            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${menu.mid}"></td>
            <td>${menu.mName}</td>
            <td><a href="/page/Menu/editMenu.jsp?mid=${menu.mid}">编辑</a>&nbsp;&nbsp;&nbsp;<a href="/page/delMenu?mid=${menu.mid}">删除</a> </td>
        </tr>
    </c:forEach>
   </table>


<table class="table table-bordered table-hover definewidth m10" >
    <tr><th colspan="5"><div><button type="button" class="btn btn-success" id="newNav" onclick="addmenu()">添加菜单 </button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button></div></th></tr>
</table>
</body>
</html>