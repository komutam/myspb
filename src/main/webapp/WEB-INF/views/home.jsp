<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
	a{
		text-decoration:none;
	}
	.proPage1{
		width:350px;
		height:513px;
		position:relative;
	}
	.proPage1Img img{
		width:100%;
	}
	.proPage1ImgContent{
		width:100%;
		background:#e7330e;
		position:absolute;
		bottom:0;
		padding:23px;
	}
	.wa{
		margin-top:5px;
		bottom:0;
	}
	.page1Price{
		position:absolute;
		left:10px;
		top:10px;
		font-size:20px;
	}
	.proPage2{
		width:380px;
		height:513px;
		position:absolute;
		top:0;
		left:375px;
	}
	.proPage2Mk{
		width:380px;
		height:513px;
		
		position:relative;
	}
	.proPage2Img{
		width:180px;
		height:160px;
	}
	.proPage2ImgContent{
		width:175px;
		height:160px;
		background:#091642;
		position:absolute;
		top:0;
		right:0;
		padding:85px 15px 15px 15px;
	}
	.wa2{
		position:absolute;
		top:40px;
		left:183px;
	}
	.proPage2Img2{
		width:180px;
		height:160px;
		position:absolute;
		top:176px;
	}
	.proPage2Img3{
		width:180px;
		height:160px;
		position:absolute;
		bottom:0;
	}
	.proPage2ImgContent2{
		width:175px;
		height:160px;
		background:#0b56de;
		position:absolute;
		top:176px;
		right:0;
		padding:85px 15px 15px 15px;
	}
	.wa3{
		position:absolute;
		top:215px;
		left:183px;
	}
	.page2Price{
		position:absolute;
		left:10px;
		top:186px;
		font-size:20px;
		z-index:5;
	}
	.proPage2ImgContent3{
		width:175px;
		height:160px;
		background:#e7330e;
		position:absolute;
		bottom:0;
		right:0;
		padding:85px 15px 15px 15px;
	}
	.wa4{
		position:absolute;
		bottom:42px;
		left:183px;
	}
	.page2Price2{
		position:absolute;
		left:10px;
		bottom:120px;
		font-size:20px;
		z-index:5;
	}

	.proPage3{
		width:380px;
		height:513px;
		position:absolute;
		top:0;
		right:0;
	}
	.proPage3Mk{
		width:380px;
		height:513px;
		
		position:relative;
	}
	.proPage3ImgContent{
		width:175px;
		height:160px;
		background:#e7330e;
		position:absolute;
		top:0;
		right:0;
		padding:85px 15px 15px 15px;
	}
	.proPage3ImgContent2{
		width:175px;
		height:160px;
		background:#e7330e;
		position:absolute;
		top:176px;
		right:0;
		padding:85px 15px 15px 15px;
	}
	.proPage3ImgContent3{
		width:175px;
		height:160px;
		background:#8fb021;
		position:absolute;
		bottom:0;
		right:0;
		padding:85px 15px 15px 15px;
	}
</style>

<%@include file="include/header.jsp"%>

<script>
	var result = '${msg}';
	if (result == "success")
		alert('가입이 완료되었습니다.');
</script>

<div class="main-container">
	<div class="proPage1">
		<div class="page1Price" style="color:white;">
			<b>$13.46</b>
		</div>
		<div class="proPage1Img">
			<a href="${ctxpath}/product/productInfo/24"><img src="${ctxpath}/images/b10.jpg"></a>
		</div>
		<img src="${ctxpath}/resources/img/wa.png" class="wa">
		<div class="proPage1ImgContent" align="left">
			<a href="${ctxpath}/product/productInfo/24">
				<b><p style="color:#a01700; font-size:14px;">THIS IS A HOT SALE PRODUCT</p></b>
				<b><p style="color:white; font-size:38px;">Skrillex Ticket</p></b>
				<b><p style="color:#a01700; font-size:20px;">CLUB GRID LIMITED EDITION</p></b>
			</a>
		</div>
	</div>
	<div class="proPage2">
		<div class="proPage2Mk">
			<div class="page1Price" style="color:white;">
				<b>$210.95</b>
			</div>
			<div class="proPage2Img">
				<a href="${ctxpath}/product/productInfo/23"><img src="${ctxpath}/images/b09.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa2.png" class="wa2">
			<div class="proPage2ImgContent" align="left">
				<a href="${ctxpath}/product/productInfo/23">
					<b><p style="color:white; font-size:15px;">UK England Manchester Rider Wool Mustang</p></b>
				</a>
			</div>					
			
			<div class="page2Price" style="color:black;">
				<b>$8.08</b>
			</div>
			<div class="proPage2Img2">
				<a href="${ctxpath}/product/productInfo/25"><img src="${ctxpath}/images/b11.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa3.png" class="wa3">
			<div class="proPage2ImgContent2" align="left">
				<a href="${ctxpath}/product/productInfo/23">
					<b><p style="color:white; font-size:15px;">Exclusive Limited Time Offer!</p></b>
				</a>
			</div>
			
			<div class="page2Price2" style="color:white;">
				<b>$4.49</b>
			</div>
			<div class="proPage2Img3">
				<a href="${ctxpath}/product/productInfo/15"><img src="${ctxpath}/images/b01.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa4.png" class="wa4">
			<div class="proPage2ImgContent3" align="left">
				<a href="${ctxpath}/product/productInfo/15">
					<b><p style="color:white; font-size:15px;">Shiba Cafe Latte~ Money-off Coupons</p></b>
				</a>
			</div>
		</div>
	</div>
	
<!-- ----- -->	
	
	<div class="proPage3">
		<div class="proPage3Mk">
			<div class="page1Price" style="color:white;">
				<b>$1.08</b>
			</div>
			<div class="proPage2Img">
				<a href="${ctxpath}/product/productInfo/19"><img src="${ctxpath}/images/b05.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa4.png" class="wa2">
			<div class="proPage3ImgContent" align="left">
				<a href="${ctxpath}/product/productInfo/19">
					<b><p style="color:white; font-size:15px;">House for Pets<br>This limited offer ends on July 31</p></b>
				</a>
			</div>		
			
			<div class="page2Price" style="color:white;">
				<b>$610.41</b>
			</div>
			<div class="proPage2Img2">
				<a href="${ctxpath}/product/productInfo/20"><img src="${ctxpath}/images/b06.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa4.png" class="wa3">
			<div class="proPage3ImgContent2" align="left">
				<a href="${ctxpath}/product/productInfo/20">
					<b><p style="color:white; font-size:15px;">LIMITED EDITION<br>Valentino MoonPhase</p></b>
				</a>
			</div>
			
			<div class="page2Price2" style="color:white;">
				<b>$4.04</b>
			</div>
			<div class="proPage2Img3">
				<a href="${ctxpath}/product/productInfo/18"><img src="${ctxpath}/images/b04.jpg" width="100%" height="100%"></a>
			</div>
			<img src="${ctxpath}/resources/img/wa5.png" class="wa4">
			<div class="proPage3ImgContent3" align="left">
				<a href="${ctxpath}/product/productInfo/15">
					<b><p style="color:white; font-size:15px;">HOT ITEM!<br>The Legendary Fork!</p></b>
				</a>
			</div>
		</div>		
	</div>
	<div class="proPage4">
	
	</div>
</div>



<%@include file="include/footer.jsp"%>