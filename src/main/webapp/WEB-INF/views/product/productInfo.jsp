<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
	.proInfoContainer{
		width:830px;
		margin:0 auto;
		text-align:center;
	}
	.proInfoContainer > h2{
		margin-bottom:30px;
	}
</style>

<%@include file="../include/header.jsp"%>
<div class="proInfoContainer">
    <h2>상품 상세정보</h2>
    <table>
        <tr>
            <td width="350" height="320">
                <img src="${ctxpath}/images/${vo.prourl}" width="100%" height="100%">
            </td>
            <td>
                <table border="1" style="height:320px; width:480px;">
                    <tr align="center">
                        <td width="100">상품명</td>
                        <td>${vo.proname}</td>
                    </tr>
                    <tr align="center">
                        <td>가격</td>
                        <td><fmt:formatNumber value="${vo.proprice}" pattern="###,###,###"/></td>
                    </tr>
                    <tr align="center" style="height:30px;">
                        <td colspan="2" style="padding-top:15px;">
                            <form name="form1" method="post" action="${ctxpath}/cart/insertCart">
                                <input type="hidden" name="proid" value="${vo.proid}">
                                <input type="hidden" name="userid" value="${login.userid}">
                                <select name="amount">
                                    <c:forEach begin="1" end="10" var="i">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>&nbsp;개
                                <input type="submit" value="Add to cart"> <a href="${ctxpath}/product/productList">상품목록</a>
                            </form>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>상품소개</td>
                        <td><textarea name="proinfo" style="width:100%; height:100%; resize:none;">${vo.proinfo}</textarea></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<%@include file="../include/footer.jsp"%>