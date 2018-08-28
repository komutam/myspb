<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<h2>상품 정보/삭제</h2>
<form id="form1" name="form1" enctype="multipart/form-data" method="POST">
    <table border="">
        <tr>
            <td>상품 이미지</td>
            <td>
                <img src="${ctxpath}/images/${vo.prourl}" height="300px" width="310px">
                <br>
                <input type="file" id="productPhoto" name="productPhoto">
            </td>
        </tr>
        <tr>
            <td>상품명</td>
            <td><input type="text" id="proname" name="proname" value="${vo.proname}"></td>
        </tr>
        <tr>
            <td>가격</td>
            <td><input type="number" id="proprice" name="proprice" value="${vo.proprice}"></td>
        </tr>
        <tr>
            <td>상품소개</td>
            <td><textarea id="proinfo" name="proinfo" rows="5" cols="60">${vo.proinfo}</textarea></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="hidden" name="proid" value="${vo.proid}">
                <input type="button" id="editBtn" value="수정">
                <input type="button" id="deleteBtn" value="삭제" onClick="location.href='/mysp/product/productDelete?proid=${vo.proid}'">
                <input type="button" id="listBtn" value="상품목록">    
            </td>
        </tr>
    </table>
</form>

<%@include file="../include/footer.jsp"%>

<script type="text/javascript">
	$(function(){
	    $("#editBtn").click(function(){
	        var proname = $("#proname").val();
	        var proprice = $("#proprice").val();
	        var proinfo = $("#proinfo").val();
	        // 상품 수정 폼 유효성 검사
	        if(proname == "") {
	            alert("상품명을 입력해주세요");
	            proname.foucs();
	        } else if (proprice == "") {
	            alert("상품 가격을 입력해주세요");
	            proprice.focus();
	        } else if (proinfo == "") {
	            alert("상품 설명을 입력해주세요");
	            proinfo.focus();
	        }
	        document.form1.action = "${ctxpath}/product/productUpdate";
	        document.form1.submit();
	    });
	    var formObj = $("form[role='form']");
		console.log(formObj);
	    // 상품 삭제 버튼 클릭이벤트
/*  	    $("#deleteBtn").click(function(){
	        // 상품 삭제 확인
	        if(confirm("상품을 삭제하시겠습니까?")){
	        	formObj.attr("action", "/mysp/product/productDelete");
	        	formObj.attr("method", "POST");
	        	formObj.submit();
	        }
	    });  */
	    // 상품 목록 버튼 클릭이벤트
	    $("#listBtn").click(function(){
	        location.href = "${ctxpath}/product/productList";
	    });
	});
</script>


