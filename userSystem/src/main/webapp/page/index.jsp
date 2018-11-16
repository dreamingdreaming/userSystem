<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="li.ren.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>枣阳市第二人民医院信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/bui.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    function back(){
  		if(confirm("确认退出")){
  		    //session删除
    		window.location.href="/page/delSession";
  		}
	}
    </script>
</head>
<body>

<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear" style="float:left">
            <!--<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">信息采编系统</div></li>-->
			<li style=" color: #000;font-size: 25px;margin-top: 17px;margin-left: 20px;">用户管理系统</li>
		</ul>
        <%
            HttpSession session1 = request.getSession();
            User user = (User) session1.getAttribute("user");
        %>
		<div  style="float: right;color: #F15D0C;margin-top: 22px;margin-right: 21px;font-size: 18px;font-weight: 800;">欢迎您，<span class="dl-log-user"><%=user.getUsername()%></span><a href="javascript:void(0)" onclick="back()" id="out"title="退出系统" class="dl-log-quit">[退出]</a></div>
    </div>
	
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<script type="text/javascript" src="../assets/js/jquery-1.6.js"></script>
<script type="text/javascript" src="../assets/js/bui.js"></script>
<script type="text/javascript" src="../assets/js/common/main-min.js"></script>
<script type="text/javascript" src="../assets/js/config-min.js"></script>


<script>
    BUI.use('common/main',function(){
        var config = [
		{id:'1',menu:[
		
		{text:'快速通道',items:[
                <c:forEach var="menu" items="${menulist}">
                {id:'${menu.mid}',text:'${menu.mName}',href:'${menu.url}'},
                </c:forEach>
		]}
		
		]}
		
		];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
</body>
</html>