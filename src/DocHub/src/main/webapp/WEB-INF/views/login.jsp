<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/login.css">
	<script src="resources/js/vendor/jquery-1.10.2.min.js"></script>
	<script src="resources/js/vendor/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script>
	<script>
		function OnLogin(){
			var username=$("[name='username']").val();
			var password=$("[name='password']").val();
			$.ajax({
				url:"login.aj",
				type:"POST",
				dataType:"json",
				data:{username:username,password:password},
				success: function(data){
					var flag=data.flag;
					console.log(data.flag);
					if(flag=="notexist"){
						new Toast({message:"用户不存在！"}).show();
					}else if(flag=="wrong"){
						new Toast({message:"密码错误！"}).show();
					}else if(flag=="success"){
						new Toast({message:"登录成功！"}).show();
						$("#dochub-lg-form").submit();
					}else{
					}
				},
				error: function(data){
					console.log("error");
				}
			});
		}
	</script>
</head>
<body>
	<div class="header">
		<div class="logo-wrapper">
			<div class="logo">
				<img src="resources/images/login_logo.png"/>文献管理系统</div>
		</div>
	</div>
	<div class="content">
		<div id="login-box">
			<div class="login-title">
				<span>DocHub</span>
			</div>
			<form action="index.html" method="get" id="dochub-lg-form" class="login-form">
				<span class="login-label">&nbsp;>>用户登录</span>
				<div class="input-group">
					<span class="glyphicon glyphicon-user input-group-addon"></span>
					<input name="username" type="text" class="form-control" placeholder="用户名"/>
				</div>
				<div class="input-group">
					<span class="glyphicon glyphicon-lock input-group-addon"></span>
					<input name="password" type="password" class="form-control" placeholder="密码"/>
				</div>
				<div class="form-group login-bottom">
					<input type="checkbox">
					<span>记住我</span>
					<input type="button"  class="btn btn-primary login-button" onclick="OnLogin()" value="登录"/>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
		 Copyright © 2013南京大学软件学院 
	</div>
</body>
</html>