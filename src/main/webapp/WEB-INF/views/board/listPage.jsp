<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<script>
	var result = '${msg}';
	if (result == "success")
		alert('처리가 완료되었습니다.');
</script>

<h1 align="center"><b>FAQ</b></h1>
<div id="listbox">
	<div class="box-header with-border">
		<h3 class="box-title">List All Page</h3>
	</div>
	<div class="list-container col-md-12 table-responsive">	
		<table class="table table-bordered table-hover">
			<tr>
				<th class="list-num">글번호</th>
				<th class="list-title">글제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th class="list-viewcnt">조회수</th>
			</tr>
			<c:forEach var="myboardVO" items="${list }">
				<tr>
					<td class="list-num">${myboardVO.bno }</td>
					<td><a href="<c:url value='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${myboardVO.bno}'/>">${myboardVO.title }<strong>[${myboardVO.replyCnt }]</strong></a></td>
					<td>${myboardVO.writer }</td>
					<td>
						<fmt:parseDate var="regdate1" value='${myboardVO.regedate }' pattern="yyyy-MM-dd HH:mm"/>
						<fmt:formatDate value="${regdate1 }" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td class="badges"><span class="badge">${myboardVO.viewcnt }</span></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${pageMaker.totalCount == 0}">
			<div style="text-align:center;">
				<span><strong>글을 입력해주세요</strong></span>
			</div>	
		</c:if>
	</div>
	
	<div class="box-footer">
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a href="${pageMaker.startPage - 1}" tabindex="-1">&laquo;</a></li>
				</c:if>
				<c:if test="${not pageMaker.prev }">
				</c:if>
				
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
					<li <c:out value="${pageMaker.cri.page == idx? 'class=active page-item':'class=page-item'}"/>>
						<a href="${idx }" class="page-link">${idx }</a>
					</li>		
				</c:forEach>
				
				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<li class="page-item"><a href="${pageMaker.endPage +1 }" class="page-link">&raquo;</a></li>
				</c:if>
				<c:if test="${not pageMaker.next || pageMaker.endPage <= 0 }">	
				</c:if>
			</ul>
		</div>
	</div>
	<div class="homeBtn">
		<input type="button" class="btn btn-primary pull-left" value="홈으로" onClick="location.href='/mysp'">
	</div>
	<div class="writeButton">
		<input type="button" class="btn btn-primary pull-right" value="글쓰기" onClick="location.href='/mysp/board/writePage'">
	</div>
</div>
<%@include file="../include/footer.jsp"%>

<!-- 자바스크립트를 이용한 페이징 처리로 페이지 값과 글갯수를 가지고 리스트에서 페이지를 넘어갈 수 있다. -->
 <form id="jobForm">
	<input type="hidden" name="page" value="${pageMaker.cri.page}">
	<input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>
<script>
	$(".pagination li a").on(
			"click",
			function(event) {

				event.preventDefault();

				var targetPage = $(this).attr("href");

				var jobForm = $("#jobForm");
				jobForm.find("[name='page']").val(targetPage);
				jobForm.attr("action", "<c:url value='/board/listPage' />")
						.attr("method", "get");
				jobForm.submit();
			});
</script> 