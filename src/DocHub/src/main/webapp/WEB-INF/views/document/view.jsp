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
				<a href="edit.html?docId=$document.getId()" class="btn btn-primary"
					style="display:inline-block;float:right">编辑</a>
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
					<h3>
						<strong>评价</strong>
					</h3>
					#set($level=0)
					<ul class="list-unstyled comment-list">
						<li class="panel panel-default">
							<div class="panel-heading">
								<label class="comment-title">王菲(wangfei)<span> in
										2013-10-9</span> </label> <label style="float:right"> #$level楼</label>
							</div>
							<div class="panel-body">
								<p>Some default panel content here. Nulla vitae elit libero,
									a pharetra augue. Aenean lacinia bibendum nulla sed
									consectetur. Aenean eu leo quam. Pellentesque ornare sem
									lacinia quam venenatis vestibulum. Nullam id dolor id nibh
									ultricies vehicula ut id elit.</p>
							</div>
						</li>

						<li class="panel panel-default">
							<div class="panel-heading">
								<label class="comment-title">王菲(wangfei)<span> in
										2013-10-9</span> </label> <label style="float:right"> #$level楼</label>
							</div>
							<div class="panel-body">
								<p>Some default panel content here. Nulla vitae elit libero,
									a pharetra augue. Aenean lacinia bibendum nulla sed
									consectetur. Aenean eu leo quam. Pellentesque ornare sem
									lacinia quam venenatis vestibulum. Nullam id dolor id nibh
									ultricies vehicula ut id elit.</p>
							</div>
						</li>
						<li class="panel panel-default">
							<div class="panel-heading">
								<label class="comment-title">王菲(wangfei)<span> in
										2013-10-9</span> </label> <label style="float:right"> #$level楼</label>
							</div>
							<div class="panel-body">
								<p>Some default panel content here. Nulla vitae elit libero,
									a pharetra augue. Aenean lacinia bibendum nulla sed
									consectetur. Aenean eu leo quam. Pellentesque ornare sem
									lacinia quam venenatis vestibulum. Nullam id dolor id nibh
									ultricies vehicula ut id elit.</p>
							</div>
						</li>
					</ul>
					<div class="row">
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" >发表评论</button>
						</div>
						<!-- Nav tabs -->
						<ul class="nav nav-tabs">
							<li class="active"><a href="#simplecomment"
								data-toggle="tab">简要评论</a></li>
							<li><a href="#detailedcomment" data-toggle="tab">详细评论</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<form action="simplecomment.html" method="post" role="form"
								class="tab-pane active" id="simplecomment">
								<div style="visibility:hidden"><input id="simplecomment-docid" value="$document.getId()"/></div>
								<div class="form-group">
									<textarea class="form-control" rows="3"
										name="simplecomment-content"></textarea>
								</div>
								<div class="form-group comment-button">
									<button type="button" class="btn btn-success">存为草稿</button>
									<button type="submit" class="btn btn-primary">评价</button>
								</div>
							</form>
							<form action="detailedcomment.html" method="post" role="form"
								class="tab-pane form-horizontal" id="detailedcomment">
								<div style="visibility:hidden"><input id="detailedcomment-docid" value="$document.getId()"/></div>
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
								<div class="form-group comment-button" style="margin-right: 0px">
									<button type="button" class="btn btn-success">存草稿</button>
									<button type="submit" class="btn btn-primary">发表</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /#main-content-container -->
</div>
<!-- /.col left -->
#parse("./template/right.jsp") #parse("./template/footer.jsp")
