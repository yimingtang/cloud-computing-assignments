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
    <link rel="stylesheet" href="resources/css/login.css">
    <script src="resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="resources/js/vendor/jquery-1.10.2.min.js"><\/script>')</script>
    <script>
      function OnLogin(){
        var username=$("[name='username']").val();
        var password=$("[name='password']").val();
        $.ajax({
          url:"login.aj",
          type:"POST",
          dataType:"json",
          data:{username:username,password:password},
          success: function(data){
          	
            var flag=data.flag;
            console.log(data.flag);
            if(flag=="notexist"){
              new Toast({message:"用户不存在！"}).show();
            }else if(flag=="wrong"){
              new Toast({message:"密码错误！"}).show();
            }else if(flag=="success"){
              new Toast({message:"登录成功！"}).show();
              $("#dochub-lg-form").submit();
            }else{
            }
          },
          error: function(data){
            if(data.status==403){
            	window.location.reload();
            }
          }
        });
      }
      
    </script>
  </head>
  <body>
    <!--[if lt IE 7]>
        <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="header">
          <div class="logo-wrapper">
            <div class="logo">
              <img src="resources/images/login_logo.png"/>文献管理系统</div>
          </div>
        </div>
      </div>
    </nav>

    <div class="content">
      <div id="login-box">
        <div class="login-title">
          <span>DocHub</span>
        </div>
        <form action="home/index.html" method="post" id="dochub-lg-form" class="login-form">
          <span class="login-label">&nbsp;>>用户登录</span>
          <div class="input-group">
            <span class="glyphicon glyphicon-user input-group-addon"></span>
            <input name="username" type="text" class="form-control" placeholder="用户名"/>
          </div>
          <div class="input-group">
            <span class="glyphicon glyphicon-lock input-group-addon"></span>
            <input name="password" type="password" class="form-control" placeholder="密码"/>
          </div>
          <div class="form-group login-bottom">
            <input type="checkbox">
            <span>记住我</span>
            <input type="button"  id="login-submit" class="btn btn-primary login-button" onclick="OnLogin()" value="登录"/>
          </div>
        </form>
      </div>
    </div>

    <div class="footer">
     <p>&copy; DocHub 2013</p>
    </div>

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
