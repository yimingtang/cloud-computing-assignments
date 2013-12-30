<div class="col-xs-12 col-md-3">
	<div class="panel panel-default">
		<div class="panel-body">
			<p>
				$userAccessContext.getName()<small>
					($userAccessContext.getUserName())</small>
			</p>
			#set($type=$userAccessContext.getPermissionLevel()) #if($type == 0)
			<p>管理员</p>
			#else
			<p>普通用户</p>
			#end
		</div>
	</div>
	<!-- /.panel -->

	<div class="list-group">
		<a href="../document/alldocument.html" class="list-group-item">所有文献</a>
		<a href="../document/mydocument.html" class="list-group-item">我导入的文献</a>
		<a href="../document/mycommentdocument.html" class="list-group-item">评价过的文献</a> <a href="#"
			class="list-group-item"><span class="badge pull-right">3</span>草稿</a>
	</div>
	<!-- /.list-group -->

	<div class="panel panel-default">
		<div class="panel-heading">热门标签</div>
		<div class="list-group">
			<a href="#" class="list-group-item">#云计算</a> <a href="#"
				class="list-group-item">#分布式</a> <a href="#" class="list-group-item">#大数据</a>
			<a href="#" class="list-group-item">#扯淡</a> <a href="#"
				class="list-group-item">#被过度炒作的概念</a> <a href="#"
				class="list-group-item">#NoSQL</a> <a href="#"
				class="list-group-item">#高大上</a> <a href="#" class="list-group-item">#屌炸天</a>
			<a href="#" class="list-group-item">#福利</a>
		</div>
		<!-- /.list-group -->
	</div>
	<!-- /.panel -->

</div>
<!-- /.col right -->
