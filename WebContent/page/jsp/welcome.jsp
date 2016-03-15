<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>系统管理后台</title>

<link href="./page/static/css/bootstrap.min.css" rel="stylesheet">
<link href="page/static/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="./page/static/css/animate.css" rel="stylesheet">
<link href="./page/static/css/style.css" rel="stylesheet">

<!-- Sweet alert -->
<script src="./page/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<link href="./page/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link href="./page/static/css/animate.css" rel="stylesheet">
</head>
<style>
.sweet-overlay {
	background-color: #E6E6E6 !important;
	-moz-opacity: 0.7 !important;
	opacity: .70 !important;
	filter: alpha(opacity = 70) !important;
}
</style>
<body>
	<div style="height: 100%; width: 100%; background-color: #E4E4E4">
		<div id="top"
			style="position: absolute; top: 0px; left: 250px; width: 84%; height: 15%; background-color: #E4E4E4">
			<table width="100%" height="100%">
				<tr>
					<td>
						<img src="page/static/img/goIcon.png" width="100" height="100"/>
					</td>
					<td width="100%"
						style="color: #1AB394; font-size: 46px; vertical-align: middle; font-family: '微软雅黑'; letter-spacing: 5px;"><strong style="margin-left:20px;">欢迎使用绿橙后台管理系统</strong></td>
				</tr>
			</table>
			<div
				style="position: absolute; bottom: 5px; right: 30px; font-size: 14px; font-weight: 700"
				id="log_out">
				<a href="#"><i class="fa fa-sign-out"></i> 注销用户</a>
			</div>

		</div>
		<div id="wrapper" style="background-color: #313131; overflow: hidden;">
			<nav class="navbar-default navbar-static-side"
				style="overflow:hidden;" role="navigation">
			<div id="caidan" class="sidebar-collapse"
				style="background-color: #2F4050">

				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element" style="text-align: center;">
							<span> <img alt="image" class="img-circle"
								src="page/static/img/user.png" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear" style="margin-top: 10px;"> <span
									class="block m-t-xs"><strong class="font-bold">
											${nickName}</strong></span>
							</span>
							</a>
						</div>
						<div class="logo-element">绿橙</div>
					</li>

					<c:forEach items="${menufather}" var="mf" varStatus="status">
						<li><a href="#"> <i class="fa fa-th-large"></i> <span
								id="${mf.menuId }" class="nav-label">${mf.menuName}</span><span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<c:forEach items="${mf.item}" var="ms" varStatus="status2">
									<li><a href="${ms.itemUrl }" target="right">${ms.itemName}</a></li>
								</c:forEach>
							</ul>
					</c:forEach>

				</ul>
			</div>
			</nav>
		</div>


		<div id="right"
			style="position: absolute; top: 15%; left: 250px; height: 82%; right: 30px">
			<iframe name="right" id="right" height="100%" width="100%"
				frameborder="0" src="system.action?method=systemUserManage"></iframe>
		</div>

	</div>
	<!-- Mainly scripts -->
	<script src="./page/static/js/jquery-2.1.1.js"></script>
	<script src="./page/static/js/bootstrap.min.js"></script>
	<script src="./page/static/js/jquery.metisMenu.js"></script>
	<script src="./page/static/js/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="./page/static/js/inspinia.js"></script>
	<script src="./page/static/js/pace.min.js"></script>


	<script>
		$(document)
				.ready(
						function() {
							var winH = $(window).height();
							document.getElementById("caidan").style.height = winH
									+ "px";

							$('#log_out')
									.click(
											function() {
												swal(
														{
															title : "",
															text: "确认注销当前用户?",
															type : "warning",
															showCancelButton : true,
															confirmButtonColor : "#DD6B55",
															confirmButtonText : "确定",
															cancelButtonText : "取消",
															closeOnConfirm : false
														},
														function() {

															location.href = "/EmujeesuPayWeb/login.action?method=logOut";
														});
											});

						});
		window.onresize = function() {
			var winH = $(window).height();
			document.getElementById("caidan").style.height = winH + "px";
		}
	</script>

</body>
</html>