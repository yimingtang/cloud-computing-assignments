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
			<form action="" class="login-form">
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
					<button type="submit" class="btn btn-primary login-button" >登录</button>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
		 Copyright © 2013南京大学软件学院 
	</div>
</body>
</html>