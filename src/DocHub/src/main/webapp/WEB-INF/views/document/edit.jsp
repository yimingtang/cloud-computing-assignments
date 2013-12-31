#parse("./template/header.jsp")
<script src="../resources/js/ajaxfileupload.js">
	
</script>
<script type="text/javascript">
	if (window.File && window.FileReader && window.FileList && window.Blob) {
		// Great success! All the File APIs are supported.
	} else {
		alert('The File APIs are not fully supported in this browser.');
	}

	/* 	$(function() {
	 $("#inputfileupload").change(function() {
	 onUploadFile();
	 });
	 });
	 */
	var OnChooseDocumentType = function(node) {
		$("#inputDocType").val(node.innerText);
	}

	function EditDocument() {
		new Toast({
			message : "已提交,切勿重复提交!"
		}).show();
		var inputDocId = $("#inputDocId").val();
		var inputDocType = $("#inputDocType").val();
		var inputTitle = $("#inputTitle").val();
		var inputAuthor = $("#inputAuthor").val();
		var inputAbstract = $("#inputAbstract")[0].value;
		var inputKeyword = $("#inputKeyword").val();
		var inputPublisher = $("#inputPublisher").val();
		var inputPublishedDate = $("#inputPublishedDate").val();
		var inputPages = $("#inputPages").val();
		var inputURL = $("#inputURL").val();
		var inputTags = $("#inputTags").val();
		$.ajax({
			url : "editDocument.aj",
			dataType : "json",
			type : "post",
			data : {
				DocId : inputDocId,
				DocType : inputDocType,
				Title : inputTitle,
				Author : inputAuthor,
				Abstract : inputAbstract,
				Keyword : inputKeyword,
				Publisher : inputPublisher,
				PublishDate : inputPublishedDate,
				Pages : inputPages,
				URL : inputURL,
				Tags : inputTags
			},
			success : function(data) {
				new Toast({
					message : "添加成功！"
				}).show();
			},
			error : function(data) {
				new Toast({
					message : "添加失败"
				}).show();
				if (data.status == 403) {
					window.location.reload();
				}
			}
		});
	}

	function onUploadFile() {
		var selectfile = $("#inputfileupload")[0].value;
		console.log(selectfile);
		if (selectfile == "") {
			new Toast({
				message : "请选择文件！"
			}).show();
			return;
		}
		$
				.ajaxFileUpload({
					url : "addattachment.aj",
					data : {
						docId : $("#inputDocId").val()
					},
					secureuri : false,
					fileElementId : "inputfileupload",
					dataType : 'json',
					success : function(data, status) {
						var flag = data.flag;
						if (flag == "new") {
							var id = data.attachmentid;
							var name = data.name;
							var url = data.url;
							$("#attachments")
									.append(
											"<li id="+id+"><a href='../"+url+"' target='view_window'>"+name+"</a><span class='glyphicon glyphicon-remove' onclick=onDeleteAttachment(this)></span> <span class='glyphicon glyphicon-circle-arrow-down' onclick=onDownloadAttachment(this)></span></li>");
						}else if(flag=="update"){
							new Toast({
							message : "已经更新附件！"
						}).show();
						}
					},
					error : function(data, status, e) {
						new Toast({
							message : "添加失败!"
						}).show();
						if (data.status == 403) {
							window.location.reload();
						}
						new Toast({
							message : "文件过大！不能超过100M！"
						}).show();
					}
				});
	}

	function onDeleteAttachment(node) {
		var attachmentid = node.parentNode.id;
		$.ajax({
			url : "deleteAttachment.aj",
			dataType : "json",
			type : "post",
			data : {
				attachmentId : attachmentid
			},
			success : function(data) {
				new Toast({
					message : "删除成功！"
				}).show();
				node.parentNode.parentNode.removeChild(node.parentNode);
			},
			error : function(data) {
				new Toast({
					message : "删除失败!"
				}).show();
				if (data.status == 403) {
					window.location.reload();
				}
			}
		});
	}
	
	function onDownloadAttachment(node){
		var url = "./downloadAttachment.aj?attachmentId="+node.parentNode.id;
		var method='post';
		jQuery(
				'<form action="' + url + '" method="' + (method || 'post')
						+ '"></form>').appendTo('body').submit()
				.remove();
	}
</script>
<div class="col-xs-12 col-md-9">
	<nav>
		<ol class="breadcrumb">
			<li><a href="../document/index.html"><span
					class="glyphicon glyphicon-home"></span> 首页</a></li>
			<li class="active">编辑文献</li>
		</ol>
		<!-- /.breadcrumb -->
	</nav>

	<div id="main-content-container">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>编辑文献</h3>
			</div>
			<!-- /.panel-heading -->

			<div class="panel-body" id="panel-body">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div style="visibility:hidden">
							<input id="inputDocId" value="$document.getId()" />
						</div>
						<label for="btn-doctype" class="col-sm-2 control-label">文献类型</label>
						<div class="btn-group col-sm-10">
							<input type="button" id="inputDocType" class="btn btn-default"
								value="$document.getDocumentType().getTypeName()">
							<button type="button" id="btn-doctype"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span> <span class="sr-only">Toggle
									Dropdown</span>
							</button>
							<ul class="dropdown-menu" role="menu">
								#foreach( $documentType in $documentTypeList)
								<li><a href="#" onclick="OnChooseDocumentType(this)">$documentType.getTypeName()</a>
								</li> #if($documentType.getId()==0)
								<li class="divider"></li> #end #end
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label for="inputTitle" class="col-sm-2 control-label">标题</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputTitle"
								placeholder="not null" value="$document.getTitle()">
						</div>
					</div>
					<div class="form-group">
						<label for="inputAuthor" class="col-sm-2 control-label">作者</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAuthor"
								value="$document.getAuthor()">
						</div>
					</div>
					<div class="form-group">
						<label for="inputAbstract" class="col-sm-2 control-label">摘要</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="inputAbstract" row="4"
								placeholder="not null" value="$document.getAbstract_()"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputKeyword" class="col-sm-2 control-label">关键字</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputKeyword"
								placeholder="not null" value="$document.getKeywords()">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPublisher" class="col-sm-2 control-label">出版单位</label>
						<div class="col-sm-10">
							<div class="input-group">
								<input type="text" class="form-control" id="inputPublisher"
									value="$document.getPublisher()">
								<div class="input-group-btn">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown">
										<span class="caret"></span>
									</button>
									<!-- <ul class="dropdown-menu pull-right">
										<li><a href="#">软件学报</a>
										</li>
										<li><a href="#">计算机学报</a>
										</li>
										<li><a href="#">xxxx</a>
										</li>
									</ul> -->
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
								placeholder="eg: 2013" value="$year">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPageFrom" class="col-sm-2 control-label">页码</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="inputPages"
								placeholder="eg:3~7" value="$document.getPages()">
						</div>
					</div>
					<div class="form-group">
						<label for="inputURL" class="col-sm-2 control-label">URL</label>
						<div class="col-sm-10">
							<input type="url" class="form-control" id="inputURL"
								value="$document.getUrl()">
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
						<div class="col-sm-7 control-label" id="inputAttachment">
							<ul id="attachments" class="list-unstyled">
								#foreach($attachment in $document.getAttachments())
								<!-- <li><a href="downloadAttachment.html?attachmentId=$attachment.getId()" target="view_window">$attachment.getName()</a>
									<span class="glyphicon glyphicon-remove" onclick=onDeleteAttachment($attachment.getId())></span></li> -->
								<li id=$attachment.getId()><a href="../$attachment.getUrl()" target="view_window">$attachment.getName()</a>
									<span class="glyphicon glyphicon-remove"
									onclick=onDeleteAttachment(this)> </span><span class="glyphicon glyphicon-circle-arrow-down"
									onclick=onDownloadAttachment(this)></span>
								</li> #end
							</ul>
							<div class="input-group">
								<input type="file" class="form-control" title="选择文件"
									id="inputfileupload" name="inputfileupload"> <span
									class="input-group-btn">
									<button class="btn btn-primary" type="button"
										onclick=onUploadFile()>
										<span class="glyphicon glyphicon-upload"></span>上传
									</button> </span>
							</div>
						</div>

						<!-- <div class="col-sm-2"
							style="cursor:pointer;margin-left:15px;padding-left:0px;width:109px;position:relative">
							<input type="file" class="btn btn-primary" title="选择文件"
								id="inputfileupload" name="inputfileupload"
								style="cursor:pointer;position:absolute;opacity: 0;width:100%">
							<span class="btn btn-primary"> <span
								class="glyphicon glyphicon-upload"></span> 添加&上传 </span>
						</div> -->
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-offset-9 col-sm-1">
							<button type="button" class="btn btn-default">取消</button>
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-primary"
								onclick=EditDocument()>修改</button>
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
#parse("./template/right.jsp") #parse("./template/footer.jsp")
