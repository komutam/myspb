<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
	.proWriteContainer{
		width:900px;
		margin:0 auto;
		text-align:center;
	}
</style>

<%@include file="../include/header.jsp"%>

<div class="proWriteContainer">
<h2 >상품 등록</h2>
<form action="productInsert" method="POST" id="proInsertForm" name="proInsertForm" enctype="multipart/form-data" onsubmit="return checkProInsert()">
    <table border="1">
        <tr>
            <td width="100px" align="center">상품명</td>
            <td width="800px"><input type="text" name="proname" style="width:100%"></td>
        </tr>
        <tr>
            <td align="center">가격</td>
            <td><input type="text" name="proprice" style="width:100%"></td>
        </tr>
        <tr>
            <td align="center">상품설명</td>
            <td height="200px"><textarea rows="10" cols="106" name="proinfo"></textarea></td>
        </tr>
        <tr>
            <td align="center">상품이미지</td>
            <td><input type="file" name="productPhoto" id="productPhoto"></td>
        </tr>
<!--         <tr>
            <td colspan="2" align="center">
                <input type="submit" value="등록" id="addBtn">
                <input type="button" value="목록" id="listBtn">
            </td>
        </tr> -->
    </table>
    <div class="formBox">
    	<input type="submit" value="등록" id="addBtn" class="btn btn-success">
        <input type="button" value="목록" id="listBtn" class="btn btn-info">
    </div>
</form>
</div>

<%@include file="../include/footer.jsp"%>

<script type="text/javascript">
 	function checkProInsert(){
		if(document.proInsertForm.proname.value == ""){
			alert("상품명을 입력하세요");
			document.proInsertForm.proname.focus();
			return false;
		}
		if(document.proInsertForm.proprice.value == ""){
			alert("가격을 입력해주세요");
			document.proInsertForm.proprice.focus();
			return false;
		}
		if(document.proInsertForm.proinfo.value == ""){
			alert("상품명을 입력하세요");
			document.proInsertForm.proinfo.focus();
			return false;
		}
 		if(document.proInsertForm.productPhoto.value == ""){
			alert("상품 사진을 등록해 주세요");
			document.proInsertForm.productPhoto.focus();
			return false;
		}
	}
	
	$(document).ready(function(){
/* 	    // 상품 등록 유효성검사
	    $("#addBtn").click(function(){
	        var proname = $("#proname").val();
	        var proprice = $("#proprice").val();
	        var proinfo = $("#proinfo").val();
	        var productPhoto = $("#productPhoto").val();

	        if(proname == "") {
	            alert("상품명을 입력해주세요");
	            proname.foucs();
	        } else if (productPrice == "") {
	            alert("상품 가격을 입력해주세요");
	            proprice.focus();
	        } else if (proinfo == "") {
	            alert("상품 설명을 입력해주세요");
	            proinfo.focus();
	        } else if (productPhoto == "") {
	            alert("상품 사진을 입력해주세요");
	            productPhoto.focus();
	        }
	        // 상품 정보 전송
	        document.form1.action = "${ctxpath}/product/productAdd";
	        document.form1.submit();
	    }); */
	    // 상품 목록이동
	    $("#listBtn").click(function(){
	        location.href='${ctxpath}/product/productList';
	    });
	});
</script>