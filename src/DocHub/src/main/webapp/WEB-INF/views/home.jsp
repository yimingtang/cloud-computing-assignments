#parse("./template/header.jsp")
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li class="active"><a href="#"><span
					class="glyphicon glyphicon-home"></span> 首页</a></li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="search-area">
			<div class="input-group">
				<input type="text" class="form-control">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default" tabindex="-1"
						data-toggle="modal" data-target="#advanced-search">
						<span class="caret"></span>
					</button>
					<button type="button" class="btn btn-default" tabindex="-1">
						<span class="glyphicon glyphicon-search"></span> 搜索
					</button>
				</div>
				<!-- /.input-group-btn -->
			</div>
			<!-- /.input-group -->

			<!-- Modal -->
			<div class="modal fade" id="advanced-search" tabindex="-1"
				role="dialog" aria-labelledby="advanced-search" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="advanced-search">高级搜索</h4>
						</div>

						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="btn-doctype" class="col-sm-2 control-label">文献类型</label>
									<div class="btn-group col-sm-10">
										<button type="button" class="btn btn-default">全部类型</button>
										<button type="button" id="btn-doctype"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown">
											<span class="caret"></span> <span class="sr-only">Toggle
												Dropdown</span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">所有分类</a></li>
											<li class="divider"></li>
											<li><a href="#">图书</a></li>
											<li><a href="#">图书章节</a></li>
											<li><a href="#">期刊</a></li>
											<li><a href="#">会议</a></li>
											<li><a href="#">学位论文</a></li>
											<li><a href="#">技术报告</a></li>
											<li><a href="#">在线资源</a></li>
											<li><a href="#">未分类</a></li>
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label for="inputTitle" class="col-sm-2 control-label">标题</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputTitle">
									</div>
								</div>
								<div class="form-group">
									<label for="inputAuthor" class="col-sm-2 control-label">作者</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputAuthor">
									</div>
								</div>
								<div class="form-group">
									<label for="inputKeyword" class="col-sm-2 control-label">关键字</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputKeyword">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPublisher" class="col-sm-2 control-label">出版单位</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputPublisher">
									</div>
								</div>
								<div class="form-group">
									<label for="inputDateFrom" class="col-sm-2 control-label">发表时间</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="inputDateFrom"
											placeholder="起始">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="inputDateTo"
											placeholder="结束">
									</div>
								</div>
								<div class="form-group">
									<label for="inputTags" class="col-sm-2 control-label">标签</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputTags"
											placeholder="标签之间以英文逗号(,)分开">
									</div>
								</div>

							</form>
						</div>
						<!-- /.modal-body -->

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary">搜索</button>
						</div>
						<!-- /.modal-footer -->
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

		</div>
		<!-- /.search-area -->

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="pull-left">文献</h3>
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm">导入文献</button>
					<div class="btn-group btn-group-sm">
						<button type="button" class="btn btn-default">按相关性排序</button>
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">按相关性排序</a></li>
							<li><a href="#">按时间排序</a></li>
							<li><a href="#">按评论数量</a></li>
							<li><a href="#">按评分排序</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- /.panel-heading -->

			<ul class="list-group document-list">
			#foreach($document in $allDocumentList)
				<li class="list-group-item document-list-item">
					<h4>
						<a href="../document/show.html?docId=$document.getId()">$document.getTitle()</a>
					</h4>
					<p class="info">$document.getAuthor() - $document.getPublisher(), $document.getYear()</p>
					<p>$document.getAbstract_()</p>
				</li>
			#end
			<!-- /.list-group document-list-->
		</div>
		<!-- /.panel -->

		<ul class="pagination">
			<li class="disabled"><a href="#">&laquo;</a></li>
			<li class="active"><a href="#">1<span class="sr-only">(current)</span>
			</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">6</a></li>
			<li><a href="#">7</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
		<!-- /.pagination -->

	</div>
	<!-- /#main-content-container -->
</div>
<!-- /.col left -->
#parse("./template/right.jsp")
#parse("./template/footer.jsp")
