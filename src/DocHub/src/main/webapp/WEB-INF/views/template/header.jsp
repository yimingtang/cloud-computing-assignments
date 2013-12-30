<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>DocHub</title>
<meta name="description"
	content="DocHub is an online document management system.">
<meta name="info" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/main.css">
<script
	src="../resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../resources/js/vendor/jquery-1.10.2.min.js"><\/script>')
</script>
</head>
<body>
	<!--[if lt IE 7]>
        <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->

	<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">DocHub</a>
			</div>
			<!-- /.navbar-header -->

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="../home/index.html"><span
							class="glyphicon glyphicon-home"></span> 首页</a></li>
					<li><a href="../statistics/index.html"><span
							class="glyphicon glyphicon-stats"></span> 统计</a></li>
					#if($userAccessContext.getPermissionLevel()==0)
					<li><a href="../admin/index.html"><span
							class="glyphicon glyphicon-wrench"></span> 管理</a></li>
					#end
					<li><a href="#about"><span
							class="glyphicon glyphicon-question-sign"></span> 帮助</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#profile"><span
							class="glyphicon glyphicon-user"></span>$userAccessContext.getName()</a>
					</li>
					<li><a href="../document/create.html"><span
							class="glyphicon glyphicon-plus"></span> 文献录入</a></li>
					<li><a href="../settings/index.html"><span
							class="glyphicon glyphicon-cog"></span> 账号设置</a></li>
					<li><a href="#logout"><span
							class="glyphicon glyphicon-log-out"></span> 登出</a></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- /.navbar -->
	
	<!-- Main content goes here -->
<div class="container">
	<div class="row">