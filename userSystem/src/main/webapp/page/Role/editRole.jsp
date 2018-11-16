<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script type="text/javascript" src="../../Js/jquery-1.8.2.min.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
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

<form action="/page/updateRolePowerServlet?id=<%=request.getParameter("id")%>" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover definewidth m10">
    <input type="hidden"  name="choosenum" id="choosenum" value="" >
    <tr>
        <td width="10%" class="tableleft"><font color="red">*</font>角色名称</td>
        <td><input type="text" name="title" readonly="true" value="<%=request.getAttribute("name")%>"/></td>
    </tr>

    <tr>
        <td class="tableleft"><font color="red">*</font>权限</td>
        <td>
            <ul>
                <label class='checkbox inline'>
                    <input type="checkbox" id="checkall">全选
                </label>
            </ul>
            <c:forEach var="menu" items="${menulist}">
                <ul><label class='checkbox inline'><input type='checkbox' id="check" name ="check" value='${menu.mid}'/>${menu.mName}</label></ul>
            </c:forEach>
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
<script>
    $(function () {
        $("#checkall").change(function () {
            var choosenum = document.getElementById("choosenum");
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            var choose = choosenum.value="";
           if (ch.checked) {
               for (var i = 0; i < alls.length; i++) {
                   choose = choose + alls[i].value + ",";
                   alls[i].checked = true;
               }
           } else {
               for (var i = 0; i < alls.length; i++) {
                   alls[i].checked = false;
                   choose="";
               }
           }
            choose = choose.substring(0,choose.length-1);
            choosenum.value=choose;
        });
        });
        $("#backid").click(function () {
           window.location.href ="/page/selectRoleNameServlet";
        });
       $("input[name^='check']").change(function () {
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
           var choosenum = document.getElementById("choosenum");
           choosenum.value=choose;
       });
</script>
</body>
</html>
