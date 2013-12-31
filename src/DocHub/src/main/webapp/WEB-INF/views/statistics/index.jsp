#parse("./template/header.jsp")

<div class="col-xs-12 col-md-9">
    <nav>
        <ol class="breadcrumb">
            <li><a href="../document/index.html"><span
                    class="glyphicon glyphicon-home"></span> 首页</a>
            </li>
            <li class="active">统计</li>
        </ol><!-- /.breadcrumb -->
    </nav>

    <div id="main-content-container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>统计</h3>
            </div><!-- /.panel-heading -->
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>用户名</th>
                        <th>文献数</th>
                        <th>评论数</th>
                        <th>附件数</th>
                    </tr>
                    </thead>
                    <tbody>
                    #foreach ($user in $userList)
                        <tr>
                            <td>$user.getId()</td>
                            <td>$user.getName()</td>
                            <td>$user.getDocuments().size()</td>
                            <td>$user.getComments().size()</td>
                            <td>$user.getAttachments().size()</td>
                        </tr>
                    #end
                    </tbody>
                </table>
            </div>
        </div><!-- /.panel -->

        <ul class="pagination">
            <li class="disabled"><a href="#">&laquo;</a></li>
            <li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">6</a></li>
            <li><a href="#">7</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul><!-- /.pagination -->

    </div><!-- /#main-content-container -->
</div><!-- /.col left -->

#parse("./template/right.jsp")
#parse("./template/footer.jsp")
