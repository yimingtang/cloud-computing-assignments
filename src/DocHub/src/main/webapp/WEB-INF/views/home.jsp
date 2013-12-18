<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>DocHub</title>
    <meta name="description" content="DocHub is an online document management system.">
    <meta name="info" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/main.css">

    <script src="resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
  </head>
  <body>
    <!--[if lt IE 7]>
        <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->

    <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">DocHub</a>
        </div><!-- /.navbar-header -->

        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
            <li><a href="#statistic"><span class="glyphicon glyphicon-stats"></span> 统计</a></li>
            <li><a href="#about"><span class="glyphicon glyphicon-question-sign"></span> 帮助</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#profile"><span class="glyphicon glyphicon-user"></span> 小明</a></li>
            <li><a href="edit_document.html"><span class="glyphicon glyphicon-plus"></span> 文献录入</a></li>
            <li><a href="settings.html"><span class="glyphicon glyphicon-cog"></span> 账号设置</a></li>
            <li><a href="#logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <!-- Main content goes here -->
    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-md-9">
          <nav>
            <ol class="breadcrumb">
              <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
            </ol><!-- /.breadcrumb -->
          </nav>

          <div id="main-content-container">

            <div class="search-area">
              <div class="input-group">
                <input type="text" class="form-control">
                <div class="input-group-btn">
                  <button type="button" class="btn btn-default" tabindex="-1" data-toggle="modal" data-target="#advanced-search">
                    <span class="caret"></span>
                  </button>
                  <button type="button" class="btn btn-default" tabindex="-1"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                </div><!-- /.input-group-btn -->
              </div><!-- /.input-group -->

              <!-- Modal -->
              <div class="modal fade" id="advanced-search" tabindex="-1" role="dialog" aria-labelledby="advanced-search" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <h4 class="modal-title" id="advanced-search">高级搜索</h4>
                    </div>

                    <div class="modal-body">
                      <form class="form-horizontal" role="form">
                        <div class="form-group">
                          <label for="btn-doctype" class="col-sm-2 control-label">文献类型</label>
                          <div class="btn-group col-sm-10">
                            <button type="button" class="btn btn-default">全部类型</button>
                            <button type="button" id="btn-doctype" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                              <span class="caret"></span>
                              <span class="sr-only">Toggle Dropdown</span>
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
                            <input type="text" class="form-control" id="inputDateFrom" placeholder="起始">
                          </div>
                          <div class="col-sm-2">
                            <input type="text" class="form-control" id="inputDateTo" placeholder="结束">
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="inputTags" class="col-sm-2 control-label">标签</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputTags" placeholder="标签之间以英文逗号(,)分开">
                          </div>
                        </div>

                      </form>
                    </div><!-- /.modal-body -->

                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                      <button type="button" class="btn btn-primary">搜索</button>
                    </div><!-- /.modal-footer -->
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div><!-- /.modal -->

            </div><!-- /.search-area -->

            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="pull-left">文献</h3>
                <div class="pull-right">
                  <button type="button" class="btn btn-primary btn-sm">导入文献</button>
                  <div class="btn-group btn-group-sm">
                    <button type="button" class="btn btn-default">按相关性排序</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
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
              </div><!-- /.panel-heading -->

              <ul class="list-group document-list">
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算给图书馆管理带来挑战 [J]</a></h4>
                  <p class="info">胡小菁， 范并思 - 大学图书馆学报, 2009</p>
                  <p>摘要: 与云计算相关的网络技术或服务, 已经应用到了图书馆中. 云计算将给图书馆带来巨大好处, 也将给图书馆管理带来挑战. 图书馆面临的管理方面的挑战包括: 可替代性问题, 标准问题, 数据安全和保密问题, 知识产权问题. 为应对这些挑战, 图书馆学理论界应当研究: 云计算的 ...</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算: 系统实例与研究现状幸</a></h4>
                  <p class="info">陈康， 郑纬民 - Journal of Software, 2009</p>
                  <p>摘要: 针对云计算这样一个范畴综述了当前云计算所采用的技术, 剖析其背后的技术含义以及当前云计算参与企业所采用的云计算实现方案. 云计算包含两个方面的含义:一方面是底层构建的云计算平台基础设施, 是用来构造上层应用程序的基础; 另外一方面是 ...</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算及其关键技术</a></h4>
                  <p class="info">陈全， 邓倩妮 - 计算机应用, 2009</p>
                  <p>摘要: 云计算是一种新兴的计算模型, 它是在网格计算的基础上发展而来的o 介绍了云计算的发展历史和应用场景, 比较了现有的云计算的定义并给出了新的定义, 以谷歌的云计算技术为例, 总结了云计算的关键技术: 数据</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算与信息资源共享管理 [J]</a></h4>
                  <p class="info">钱文静， 邓仲华 - 图书与情报, 2009</p>
                  <p>摘要: 云计算自从被提出以来, 飞速发展, 目前已经有了一些可用的云计算服务. 云计算受广泛的推崇, 是因为它可利用最小化的客户端实现复杂高效的处理和存储的特点, 这给我们带来巨大的发挥空间. 若将云计算网络推广到信息资源共享管理中, 无疑会极大的 ...</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算技术在图书馆中的应用探讨 [J]</a></h4>
                  <p class="info">李永先， 栾旭伦， 李森森 - 江西图书馆学刊, 2009</p>
                  <p>摘要: 云计算技术以其强大的功能已在我国的一些商业领域得到了初步的应用. 在不久的将来, 云计算技术也可能在图书馆中得到大规模的普及应用. 在介绍云计算概念, 概括云计算技术特点的基础上, 探计了云计算技术在图书馆中的应用. 结合云计算技术在企业 ...</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算研究进展综述</a></h4>
                  <p class="info">张建勋， 古志民， 郑超 - 计算机应用研究, 2010</p>
                  <p>摘要: 对现有的云计算系统进行分析和总结, 以期为下一步的研究指明方向. 采用文献分析方法简述了云计算的定义, 特点和基本结构, 介绍了实现云计算的各项相关技术的研究现状, 包括虚拟化技术, Web 服务技术, 编程模型, 对已有的几个较有代表性的云计算系统 ...</p>
                </li>
                <li class="list-group-item document-list-item">
                  <h4><a href="#">云计算研究现状综述</a></h4>
                  <p class="info">李乔， 郑啸 - 计算机科学, 2011</p>
                  <p>摘要: 云计算能够给用户提供可靠的, 自定义的, 最大化资源利用的服务, 是一种崭新的分布式计算模式. 同时, 云计算和其他技术及理论的有机结合, 也是解决理论研究和实际应用的重要途径. 阐述了云计算的基本概念, 论述了云计算的优势和存在的问题, 比较了云计算不同于以往技术 ...</p>
                </li>
              </ul><!-- /.list-group document-list-->
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

        <div class="col-xs-12 col-md-3">
          <div class="panel panel-default">
            <div class="panel-body">
              <p>小明<small> (xiaoming)</small></p>
              <p>一般用户</p>
            </div>
          </div><!-- /.panel -->

          <div class="list-group">
              <a href="#" class="list-group-item">我导入的文献</a>
              <a href="#" class="list-group-item">评价过的文献</a>
              <a href="#" class="list-group-item"><span class="badge pull-right">3</span>草稿</a>
          </div><!-- /.list-group -->

          <div class="panel panel-default">
            <div class="panel-heading">热门标签</div>
            <div class="list-group">
              <a href="#" class="list-group-item">#云计算</a>
              <a href="#" class="list-group-item">#分布式</a>
              <a href="#" class="list-group-item">#大数据</a>
              <a href="#" class="list-group-item">#扯淡</a>
              <a href="#" class="list-group-item">#被过度炒作的概念</a>
              <a href="#" class="list-group-item">#NoSQL</a>
              <a href="#" class="list-group-item">#高大上</a>
              <a href="#" class="list-group-item">#屌炸天</a>
              <a href="#" class="list-group-item">#福利</a>
            </div><!-- /.list-group -->
          </div><!-- /.panel -->

        </div><!-- /.col right -->
      </div><!-- /.row -->

      <hr>

      <footer>
        <p>&copy; DocHub 2013</p>
      </footer>

    </div><!-- /.container -->

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="resources/js/vendor/jquery-1.10.2.min.js"><\/script>')</script>

    <script src="resources/js/vendor/bootstrap.min.js"></script>

    <script src="resources/js/plugins.js"></script>
    <script src="resources/js/main.js"></script>

    <script>
        var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
        (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
        g.src='//www.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g,s)}(document,'script'));
    </script>
  </body>
</html>
