<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<div id="list-container" class="table-responsive" style="width:1000px; margin:0 auto; margin-top:20px;">
 <form action="writePage" method="POST" onsubmit="return checkBoard()" name="writeForm">
	<div class="boxBody-write">
		<div class="form-group">
			<label for="userName">User</label>
			<input type="text" class="form-control" name="writer" value="${login.userid}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" class="form-control" name="title" placeholder="제목을 입력해 주세요">
		</div>
		<div class="form-group">
			<label for="title">Content</label>
			<textarea class="form-control" name="content" placeholder="Content Enter..." rows="10" style="resize:none;"></textarea>
		</div>
	</div>
	<div class="boxFooter">
		<input type="reset" class="btn btn-danger" value="취소" onClick="location.href='/mysp/board/listPage'">
		<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
	</div>
 </form>
	
</div>

<%@include file="../include/footer.jsp"%>
<script type="text/javascript">
	function checkBoard(){
		if(document.writeForm.writer.value == ""){
			alert("이름을 입력해주세요");
			document.writeForm.writer.focus();
			return false;
		}
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