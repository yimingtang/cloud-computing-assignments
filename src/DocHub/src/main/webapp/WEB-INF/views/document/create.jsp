#parse("./template/header.jsp")
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li><a href="../document/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a>
			</li>
			<li class="active">新的文献</li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>新的文献</h3>
			</div>
			<!-- /.panel-heading -->

			<div class="panel-body">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="btn-doctype" class="col-sm-2 control-label">文献类型</label>
						<div class="btn-group col-sm-10">
							<button type="button" class="btn btn-default">未分类</button>
							<button type="button" id="btn-doctype"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span> <span class="sr-only">Toggle
									Dropdown</span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">未分类</a>
								</li>
								<li class="divider"></li>
								<li><a href="#">图书</a>
								</li>
								<li><a href="#">图书章节</a>
								</li>
								<li><a href="#">期刊</a>
								</li>
								<li><a href="#">会议</a>
								</li>
								<li><a href="#">学位论文</a>
								</li>
								<li><a href="#">技术报告</a>
								</li>
								<li><a href="#">在线资源</a>
								</li>
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
						<label for="inputAbstract" class="col-sm-2 control-label">摘要</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="inputAbstract" row="4"></textarea>
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
							<div class="input-group">
								<input type="text" class="form-control">
								<div class="input-group-btn">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown">
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right">
										<li><a href="#">软件学报</a>
										</li>
										<li><a href="#">计算机学报</a>
										</li>
										<li><a href="#">xxxx</a>
										</li>
									</ul>
								</div>
								<!-- /btn-group -->
							</div>
							<!-- /input-group -->
						</div>
					</div>
					<div class="form-group">
						<label for="inputPublishedDate" class="col-sm-2 control-label">发表年份</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="inputPublishedDate"
								placeholder="e.g. 2013">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPageFrom" class="col-sm-2 control-label">页码</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="inputPageFrom"
								placeholder="起始">
						</div>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="inputPageTo"
								placeholder="结束">
						</div>
					</div>
					<div class="form-group">
						<label for="inputURL" class="col-sm-2 control-label">URL</label>
						<div class="col-sm-10">
							<input type="url" class="form-control" id="inputURL">
						</div>
					</div>
					<div class="form-group">
						<label for="inputTags" class="col-sm-2 control-label">标签</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputTags"
								placeholder="标签之间以英文逗号(,)分开">
						</div>
					</div>
					<div class="form-group">
						<label for="inputReference" class="col-sm-2 control-label">引用关系</label>
						<div class="col-sm-1">
							<button type="button" id="inputReference"
								class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-plus"></span> 添加
							</button>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAttachment" class="col-sm-2 control-label">附件</label>
						<div class="col-sm-1">
							<button type="button" id="inputAttachment"
								class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-plus"></span> 添加
							</button>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-offset-9 col-sm-1">
							<button type="button" class="btn btn-default">取消</button>
						</div>
						<div class="col-sm-1">
							<button type="submit" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
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