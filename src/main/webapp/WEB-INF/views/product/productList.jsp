<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
	.proListContainer{
		width:800px;
		margin:0 auto;
		text-align:center;
		position:relative;
	}
	.bbb{
		position:absolute;
		left:0;
		top:0;
	}
</style>

<%@include file="../include/header.jsp"%>

<div class="proListContainer">
    <h2>상품목록</h2>
    <c:if test="${adminLogin.adminid != null}">
    	<input type="button" value="상품등록" onClick="location.href='/mysp/product/productWrite'" class="btn btn-success bbb"><br>
	</c:if>
    <table border="1">
        <tr>
            <td width="55" align="center"><b>상품ID</b></td>
            <td align="center"><b>상품이미지</b></td>
            <td width="535" align="center"><b>상품명</b></td>
            <td width="90" align="center"><b>가격</b></td>
        </tr>
        <c:forEach var="row" items="${list}">
        <tr>
            <td align="center">
                ${row.proid}
            </td>
            <td>
                <a href="${ctxpath}/product/productInfo/${row.proid}">
                    <img src="${ctxpath}/images/${row.prourl}" width="120ox" height="110px">
                </a>
            </td>
            <td align="left" style="padding-left:10px;">
                <a href="${ctxpath}/product/productInfo/${row.proid}">${row.proname}</a>
                <c:if test="${adminLogin.adminid != null}">
                	<a href="${ctxpath}/product/productEdit/${row.proid}">[상품편집]</a>
            	</c:if>
            </td>
            <td align="center">
            	<!-- JSTL fmt태그를 사용하여 숫자 포맷을 변경해주었다. 상품가격의 가독성을 높이기 위해 숫자3자리마다 콤마(,)를 찍어주도록 처리 -->
                <fmt:formatNumber value="${row.proprice}" pattern="###,###,###"/><p>won</p>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

<%@include file="../include/footer.jsp"%>