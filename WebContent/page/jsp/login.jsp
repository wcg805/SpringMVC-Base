<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./page/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="./page/static/css/animate.css"  rel="stylesheet">
    <link href="./page/static/css/style.css" rel="stylesheet">
<title>登陆界面</title>
<script src="./page/static/js/jquery-2.1.1.js"></script>
<script src="./page/static/js/bootstrap.min.js"></script>

</head>
<body>
 <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>
				<img src="page/static/img/goIcon.png" width="240">
<!--                 <h1 class="logo-name"><nobr>绿橙</nobr></h1> -->

            </div>
            <h3>绿橙后台管理系统登陆</h3>
            <form class="m-t" role="form" action="login.action?method=toWelcomePage" method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="请输入用户名" required="">
                </div>
                <div class="form-group">
                    <input name=password type="password" class="form-control" placeholder="请输入密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

            </form>
            <p class="m-t"> <small>版权所有： 绿橙无忧（北京）科技有限公司&copy; 2016</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->

</body>
</html>
