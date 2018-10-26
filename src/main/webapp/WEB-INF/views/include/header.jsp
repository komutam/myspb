<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<c:set var="ctxpath" value="<%=request.getContextPath() %>"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<link rel="stylesheet" href="${ctxpath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${ctxpath}/resources/css/css.css">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="${ctxpath}/resources/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxpath}/resources/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${ctxpath}/resources/dist/css/skins/_all-skins.min.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${ctxpath}/resources/plugins/morris.js/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/jvectormap/jquery-jvectormap.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${ctxpath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<style type="text/css">
li{
	list-style:none;
}

	#main-header{
		width:100%;
		background:#333;
		margin:0 auto;
		padding:0;
		position:relative;
	}
	.main-container{
		width:1160px;
		/* height:100%;
		background:yellow; */
		margin:0 auto;
		text-align:center;
		margin-top:40px;
		position:relative;
	}
	
	body{
		box-sizing:border-box;
		background:#f2f2f2;
	}
	#main-footer{
		position:relative;
		background:#333;
		color:white;
		width:100%;
		line-height:50px;
		text-align:center;
		height:50px;
		margin:0;
		margin-top:50px;
		padding:0;
	}
	
	.headerBar{
		padding-right:5px;
		background-color:#a2b0c3;
		height:34px;
	}
	.menuBarUl{
		margin:0;
		padding:0;
		overflow:hidden;
		background-color:#303030;
		padding-left:80px;
	}
	.menuBarUl li{
		float:left;
	}
	.menuBarUl li a{
		display:block;
		color:white;
		text-align:center;
		padding:14px 16px;
		text-decoration:none;
	}
	.menuBarUl li a:hover{
		background-color:#111;
	}
	.logo{
		position:absolute;
	}
	.cart{
		position:absolute;
		right:5px;
	}
</style>

<title>shop test</title>
</head>
<body>
 <div id="wrap">
	<header id="main-header">
		<div class="logo">
			<a href="${ctxpath}">
				<img src="${ctxpath}/resources/img/shoplogo3.jpg" width="82" height="82">
			</a>
		</div>
		<div class="cart">
			<a href="${ctxpath}/cart/cartList?userid=${login.userid}">
				<img src="${ctxpath}/resources/img/cart.png" width="48" height="48">
			</a>
		</div>
		<div class="menuBar">
			<ul class="menuBarUl">
				<li><a class="active" href="${ctxpath}">Home</a></li>
				<li><a href="#news">News</a></li>
				<li><a href="${ctxpath}/product/productList">Product</a></li>
				<li><a href="#blog">Blog</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="${ctxpath}/board/listPage">FAQ</a></li>
			</ul>
		</div>
		<c:if test="${login != null}">
			<c:if test="${adminLogin == null}">
				<div class="headerBar">
					<input type="button" value="로그아웃" class="btn btn-primary pull-right" style="margin-left:5px;" onClick="location.href='/mysp/user/logout'">
					<input type="button" value="마이페이지" class="btn btn-primary pull-right">
				</div>
			</c:if>
		</c:if>
		<c:if test="${adminLogin != null}">
			<c:if test="${login == null}">
				<div class="headerBar">
					<input type="button" value="로그아웃" class="btn btn-primary pull-right" style="margin-left:5px;" onClick="location.href='/mysp/admin/adminLogout'">
					<input type="button" value="상품등록" class="btn btn-primary pull-right" onClick="location.href='/mysp/product/productWrite'">
				</div>
			</c:if>
		</c:if>
		<c:if test="${empty login and empty adminLogin}">
			<div class="headerBar">
				<input type="button" value="AdminLogin" class="btn btn-primary pull-right" style="margin-left:5px;" onClick="location.href='/mysp/admin/adminLogin'">
				<input type="button" value="JOIN" class="btn btn-primary pull-right" style="margin-left:5px;" onClick="location.href='/mysp/member/signup'">
				<input type="button" value="LOGIN" class="btn btn-primary pull-right" onClick="location.href='/mysp/user/login'">
			</div>
		</c:if>
	</header>
