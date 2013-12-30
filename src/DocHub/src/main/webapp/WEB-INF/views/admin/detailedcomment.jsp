#parse("./template/header.jsp")
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li><a href="../document/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a>
			</li>
			<li><a href="../admin/index.html">管理</a>
			</li>
			<li class="active">详细评价内容管理</li>
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
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>名称</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						#foreach($commentPropertyType in $commentPropertyList)
						<tr>
							<td>$commentPropertyType.getId()</td>
							<td>$commentPropertyType.getName()</td>
							<td><a class="btn btn-danger btn-sm" href="deletecommentproperty.html?commentpropetyid=$commentPropertyType.getId()"> <span
									class="glyphicon glyphicon-minus"></span> 删除 </a>
							</td>
						</tr>
						#end
						<tr>
							<td></td>
							<form action="addcommentproperty.html" method="post">
							<td><input type="text" class="form-control" name="commentproperty"
								placeholder="add new">
							</td>
							<td><button type="submit" class="btn btn-success btn-sm"
									onclick=onEditUser($user.getId())>
									<span class="glyphicon glyphicon-plus"></span> 添加
								</button>
							</td>
							</form>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /#main-content-container -->
</div>
<!-- /.col left -->
#parse("./template/right.jsp") #parse("./template/footer.jsp")
