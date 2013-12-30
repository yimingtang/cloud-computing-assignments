#parse("./template/header.jsp")
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li><a href="../document/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a></li>
			<li class="active">管理</li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>管理员配置管理</h3>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<ol class="list-group" style="margin:0px 30px">
					<li class="list-group-item"><a href="user.html">用户管理</a></li>
					<li class="list-group-item"><a href="detailedcomment.html">详细评价内容</a>
					</li>
				</ol>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /#main-content-container -->
</div>
<!-- /.col left -->
#parse("./template/right.jsp") #parse("./template/footer.jsp")
