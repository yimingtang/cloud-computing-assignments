#parse("./template/header.jsp")
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li><a href="../document/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a>
			</li>
			<li class="active"></li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 style="display:inline-block">文献</h3>
				<a href ="edit.html?docId=$document.getId()" class="btn btn-primary" style="display:inline-block;float:right">编辑</a>
			</div>
			<!-- /.panel-heading -->

			<div class="panel-body">
				<div class="document-info-section">
					<h4>基本信息</h4>
					<ul>
						<li>标题：$document.getTitle()</li>
						<li>作者：$document.getAuthor()</li>
						<li>摘要：$document.getAbstract_()</li>
						<li>关键字：$document.getKeywords()</li>
						<li>出版年份：$year</li>
						<li>出版社：$document.getPublisher()</li>
						<li>页码：$document.getPages()</li>
						<li>URL：<a href="$document.getUrl()">$document.getUrl()</a>
						</li>
						<!-- <li>doi：10.3969/j.issn.1002-137X.2011.04.006</li> -->
					</ul>
				</div>
				<div class="document-info-section">
					<h4>附件</h4>
					<ul>
						<li>附件1</li>
						<li>附件2</li>
						<li>附件3</li>
					</ul>
				</div>
				<div class="document-info-section">
					<h4>引用</h4>
					<ul>
						<li>引用1</li>
						<li>引用2</li>
						<li>引用3</li>
					</ul>
				</div>
				<div class="document-info-section">
					<h4>评价</h4>
					<ul>
						<li>评价1</li>
						<li>评价2</li>
						<li>评价3</li>
					</ul>
					<div class="row">
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" tabindex="-1"
								data-toggle="modal" data-target="#comment-form">发表评论</button>
						</div>
					</div>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="comment-form" tabindex="-1"
					role="dialog" aria-labelledby="comment-form" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="comment-form">发表评论</h4>
							</div>

							<div class="modal-body">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label for="btn-comment-type" class="col-sm-2 control-label">评论类型</label>
										<div class="btn-group col-sm-10">
											<button type="button" class="btn btn-default">全部类型</button>
											<button type="button" id="btn-comment-type"
												class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												<span class="caret"></span> <span class="sr-only">Toggle
													Dropdown</span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">长评论</a>
												</li>
												<li><a href="#">短评论</a>
												</li>
											</ul>
										</div>
									</div>
									<div class="form-group">
										<label for="inputContent" class="col-sm-2 control-label">内容</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputContent"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputProblem" class="col-sm-2 control-label">要解决的问题</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputProblem"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputIdea" class="col-sm-2 control-label">主要思路</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputIdea"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputExperiment" class="col-sm-2 control-label">实验结果</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputExperiment"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputContribution" class="col-sm-2 control-label">贡献</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputContribution"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputImprovement" class="col-sm-2 control-label">改进</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="inputImprovement"></textarea>
										</div>
									</div>
								</form>
							</div>
							<!-- /.modal-body -->

							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">放弃</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">存草稿</button>
								<button type="submit" class="btn btn-primary">发表</button>
							</div>
							<!-- /.modal-footer -->
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /#main-content-container -->
</div>
<!-- /.col left -->
#parse("./template/right.jsp")
#parse("./template/footer.jsp")