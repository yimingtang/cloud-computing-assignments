#parse("./template/header.jsp")
<script type="text/javascript">
	function OnChooseDocumentType(node) {
		$("[name='searchdoctype']").val(node.innerText);
	}
	
	function onDeleteDocument(docid){
		$.ajax({
			url : "../document/deletedocument.aj",
			dataType : "json",
			type : "post",
			data : {
				docId : docid,
			},
			success : function(data) {
				new Toast({
					message : "删除成功！"
				}).show();
				window.location.reload();
			},
			error : function(data) {
				new Toast({
					message : "删除失败"
				}).show();
				if (data.status == 403) {
					window.location.reload();
				}
			}
		});
	}
</script>
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li class="active"><a href="../home/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a>
			</li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="search-area">
			<form action="../document/fuzzysearch.html" method="post" role="form">
				<div class="input-group">
					<input type="text" name="fuzzyword" class="form-control">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default" tabindex="-1"
							data-toggle="modal" data-target="#advanced-search">
							<span class="caret"></span>
						</button>
						<button type="submit" class="btn btn-default" tabindex="-1">
							<span class="glyphicon glyphicon-search"></span> 搜索
						</button>
					</div>
				</div>
				<!-- /.input-group-btn -->
			</form>
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
							<form action="../document/accuratesearch.html" method="post"
								class="form-horizontal" role="form">
								<div class="form-group">
									<label for="btn-doctype" class="col-sm-2 control-label">文献类型</label>
									<div class="btn-group col-sm-10">
										<input type="text" class="btn btn-default"
											name="searchdoctype" value="全部类型">
										<button type="button" id="btn-doctype"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown">
											<span class="caret"></span> <span class="sr-only">Toggle
												Dropdown</span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">全部类型</a>
											</li>
											<li class="divider"></li> #foreach($docType in
											$documentTypeList)
											<li><a href="javascript:void(0)"
												onclick="OnChooseDocumentType(this)">$docType.getTypeName()</a>
											</li> #end
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label for="inputTitle" class="col-sm-2 control-label">标题</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchtitle">
									</div>
								</div>
								<div class="form-group">
									<label for="inputAuthor" class="col-sm-2 control-label">作者</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchauthor">
									</div>
								</div>

								<div class="form-group">
									<label for="inputAbstract" class="col-sm-2 control-label">摘要</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchabstract">
									</div>
								</div>

								<div class="form-group">
									<label for="inputKeyword" class="col-sm-2 control-label">关键字</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchkeyword">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPublisher" class="col-sm-2 control-label">出版单位</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchpublisher">
									</div>
								</div>
								<div class="form-group">
									<label for="inputUrl" class="col-sm-2 control-label">URL</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="searchurl">
									</div>
								</div>
								<div class="form-group">
									<label for="inputDateFrom" class="col-sm-2 control-label">发表时间</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" name="searchdatefrom"
											placeholder="1970">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control" name="searchdateend"
											placeholder="2013">
									</div>
								</div>
								<div class="form-group">
									<label for="inputTags" class="col-sm-2 control-label">标签</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputTags"
											placeholder="标签之间以英文逗号(,)分开">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="submit" class="btn btn-primary">搜索</button>
								</div>
							</form>
						</div>
						<!-- /.modal-body -->


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
					<a href="../document/create.html" class="btn btn-primary btn-sm">导入文献</a>
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

			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>标题</th>
							<th>上传用户</th>
							<th>上传时间</th>
							<th>更新时间</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						#set($level=0)
						#foreach($document in $allDocumentList)
						#set($level=$level+1)
						<tr>
							<td>$level</td>
							<td><a href="../document/show.html?docId=$document.getId()">$document.getTitle()</a></td>
							<td>$document.getUser().getName()</td>
							<td>$dateformat.format($document.getCreatedAt())</td>
							<td>$dateformat.format($document.getUpdatedAt())</td>
							<td><a class="btn btn-primary btn-sm" style="margin-right:5px" href="../document/edit.html?docId=$document.getId()"> 编辑 </a><a class="btn btn-danger btn-sm" href="javascript:void(0)" onclick=onDeleteDocument($document.getId())>删除</a></td>
						</tr>
						#end
					</tbody>
				</table>
			</div>
			<!-- /.panel-body -->
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
#parse("./template/right.jsp") #parse("./template/footer.jsp")
