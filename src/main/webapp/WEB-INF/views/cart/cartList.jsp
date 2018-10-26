<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<style>
	.cartListContainer{
		width:830px;
		margin:0 auto;
		text-align:center;
	}
	th{
		text-align:center;
	}
	.cartTable{
		margin-bottom:5px;
	}
</style>

<div class="cartListContainer">
    <h2>장바구니 확인</h2>
    <c:choose>
        <c:when test="${map.count == 0}">
            장바구니가 비어있습니다.
        </c:when>
        <c:otherwise>
        <form name="form1" id="form1" method="POST" action="${ctxpath}/cart/modifyCart">
           	<input type="hidden" name="userid" value="${login.userid}">
            <table border="1" class="cartTable">
                <tr>
                    <th style="width:550px">상품명</th>
                    <th style="width:80px">단가</th>
                    <th>수량</th>
                    <th style="width:100px">금액</th>
                    <th style="width:60px">취소</th>
                </tr>
                <c:forEach var="row" items="${map.list}" varStatus="i">
                <tr>
                    <td>
                        ${row.proname}
                    </td>
                    <td>
                        <fmt:formatNumber pattern="###,###,###" value="${row.proprice}"/>
                    </td>
                    <td>
                        <input type="number" style="width: 40px" name="amount" value="${row.amount}" min="1">
                        <input type="hidden" name="proid" value="${row.proid}">
                    </td>
                    <td>
                        <fmt:formatNumber pattern="###,###,###" value="${row.money}"/>
                    </td>
                    <td>
                        <a href="${ctxpath}/cart/deleteCart?cartid=${row.cartid}">삭제</a>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" align="right">
                        장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}"/><br>
                        배송료 : ${map.fee}<br>
                        전체 주문금액  :<fmt:formatNumber pattern="###,###,###" value="${map.allSum}"/>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="count" value="${map.count}">
            <button type="submit" id="btnUpdate" class="btn btn-success pull-left">수정</button>
        	<input type="button" id="btnList" class="btn btn-info pull-right" onClick="location.href='/mysp/product/productList'" value="상품목록">
        </form>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="../include/footer.jsp"%>