<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

    <h2>장바구니 확인</h2>
    <c:choose>
        <c:when test="${map.count == 0}">
            장바구니가 비어있습니다.
        </c:when>
        <c:otherwise>
        <form name="form1" id="form1" method="post" action="cartList">
            <table border="1">
                <tr>
                    <th>상품명</th>
                    <th>단가</th>
                    <th>수량</th>
                    <th>금액</th>
                    <th>취소</th>
                </tr>
                <c:forEach var="row" items="${map.list}" varStatus="i">
                <tr>
                    <td>
                        ${row.proname}
                    </td>
                    <td style="width: 80px" align="right">
                        <fmt:formatNumber pattern="###,###,###" value="${row.proprice}"/>
                    </td>
                    <td>
                        <input type="number" style="width: 40px" name="amount" value="${row.amount}" min="1">
                        <input type="hidden" name="proid" value="${row.proid}">
                    </td>
                    <td style="width: 100px" align="right">
                        <fmt:formatNumber pattern="###,###,###" value="${row.money}"/>
                    </td>
                    <td>
                        <a href="${ctxpath}/cart/cartDelete?cartid=${row.cartid}">삭제</a>
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
            <button type="submit" id="btnUpdate">수정</button>
        </form>
        </c:otherwise>
    </c:choose>
    <input type="button" id="btnList" onClick="location.href='/mysp/product/productList'" value="상품목록"></button>

<%@include file="../include/footer.jsp"%>