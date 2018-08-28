<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<div id="modify-container" class="table-responsive" style="width:1000px; margin:0 auto; margin-top:20px;">
 <form action="modifyPage" method="POST" onsubmit="return checkBoard()" name="modifyForm">
	<input type="hidden" name='page' value='${cri.page }'>
	<input type="hidden" name='perPageNum' value='${cri.perPageNum }'>
	<div class="boxBody-modify">
		<div class="form-group">
			<input type="text" class="form-control" name="bno" value="${myBoardVO.bno }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="userName">User</label>
			<input type="text" class="form-control" name="writer" value="${myBoardVO.writer }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" class="form-control" name="title" value="${myBoardVO.title }">
		</div>
		<div class="form-group">
			<label for="title">Content</label>
			<textarea class="form-control" name="content"  rows="10" style="resize:none;">${myBoardVO.content }</textarea>
		</div>
	</div>
	<div class="boxFooter">
		<input type="button" class="btn btn-danger" value="취소" onClick="location.href='/mysp/board/readPage?page=${cri.page}&perPageNum=${cri.perPageNum}&bno=${myBoardVO.bno}'">
		<input type="submit" class="btn btn-primary pull-right" value="수정">
	</div>
 </form>
	
</div>

<%@include file="../include/footer.jsp"%>
<script type="text/javascript">
	function checkBoard(){
		if(document.writeForm.title.value == ""){
			alert("제목을 입력해주세요");
			document.writeForm.title.focus();
			return false;
		}
		if(document.writeForm.content.value == ""){
			alert("내용을 입력해주세요");
			document.writeForm.content.focus();
			return false;
		}
	}
</script>