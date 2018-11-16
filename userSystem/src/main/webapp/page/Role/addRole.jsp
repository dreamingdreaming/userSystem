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
<form action="/page/addRole" method="post" class="definewidth m20" onsubmit="return checkrolename();">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden"  name="choosenum" id="choosenum" value="" >
        <tr>
            <td width="10%" class="tableleft"><font color="red">*</font>角色名称</td>
            <td><input type="text" name="rolename" id="rolename" onchange="check()"/><span id="rolesp"></span></td>
        </tr>
        <tr>
            <td class="tableleft"><font color="red">*</font>权限</td>
            <td>
                <c:forEach var="menu" items="${menuList}">
                    <ul><label class='checkbox inline'><input type='checkbox' name="check" id="check" value='${menu.mid}'/>${menu.mName}</label></ul>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="/page/selectRoleNameServlet";
		 });
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

    function checkrolename() {
        return check();
    }
    function check() {
        var rolename = document.getElementById("rolename").value;
        var rolesp = document.getElementById("rolesp");
        var isOK = true;
        if (rolename == '' || rolename ==null) {
            rolesp.innerHTML = "<font color='red'>角色名不能为空</font>"
            isOK = false;
        }
        else {
            // $.get("/page/doubleRoleName?rolename=" + rolename,function (data) {
            //     if (data.indexOf("存在") > 0){
            //         rolesp.innerHTML = "<font color='red'>角色名已存在</font>"
            //         isOK = false;
            //     }
            // });

            $.ajax({
                url:"/page/doubleRoleName?rolename=" + rolename,
                async:false,
                success:function (data) {
                    if (data.indexOf("存在") > 0){
                        rolesp.innerHTML = "<font color='red'>角色名已存在</font>"
                        isOK = false;
                    }
                }
            })
        }
        if (isOK){
            namesp.innerHTML = "<font color='green'>√</font>"
        }

        return isOK;
    }

</script>