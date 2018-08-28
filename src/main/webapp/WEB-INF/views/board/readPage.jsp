<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<%@include file="../include/header.jsp"%>

<div id="read-container" class="table-responsive" style="width:1000px; margin:0 auto; margin-top:20px;">
 <form role='form' method='post' action="modify">
	<input type="hidden" name='bno' value='${dto.bno }'>
	<input type="hidden" name='page' value='${cri.page }'>
	<input type="hidden" name='perPageNum' value='${cri.perPageNum }'>
 </form>
 
 <div class="readBox">
	<div class="boxBody-write">
		<div class="form-group">
			<label for="userName">User</label>
			<input type="text" class="form-control" name="writer" value="${dto.writer }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" class="form-control" name="title" value="${dto.title }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title">Content</label>
			<textarea class="form-control" name="content" rows="10" style="resize:none;" readonly="readonly">${dto.content }</textarea>
		</div>
	</div>
	<div class="read-boxFooter">
		<input type="button" id="modifyBtn" class="btn btn-warning" value="수정">
		<input type="button" id="delBtn" class="btn btn-danger" value="삭제">
		<input type="button" id="listBtn" class="btn btn-primary" value="목록">
	</div>
 </div>
</div>
<div class="addReply-Container" style="width:1000px; margin:0 auto; margin-top:20px;">
	
	<div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">ADD NEW REPLY</h3>
		</div>
		<div class="box-body">
			<label for="exampleInputEmail1">Writer</label>
			<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
				<label for="exampleInputEmail1">Reply Text</label>
			<textarea class="form-control" placeholder="REPLY TEXT" id="newReplyText" rows="3" style="resize:none;"></textarea>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="button" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
		</div>
	</div>
	
	<!-- The time line -->
		<ul class="timeline">
			<!-- timeline time label -->
			<li class="time-label" id="repliesDiv"></li>
		</ul>

		<div class='text-center'>
			<ul id="pagination" class="pagination pagination-sm no-margin ">

			</ul>
		</div>
		
		<!-- Modal -->
		<div id="modifyModal" class="modal modal-primary fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body" data-rno><textarea id="replyText" class="form-control" rows="3" style="resize:none;"></textarea></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
						<button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rno={{rNo}}>
			<i class="fa fa-comments bg-blue"></i>
			<div class="timeline-item">
				<span class="time">
					<i class="fa fa-clock-o"></i>{{prettifyDate regDate}}
				</span>
				<h3 class="timeline-header"><strong>{{rNo}}</strong> -{{replyer}}</h3>
				<div class="timeline-body"><pre>{{replyText}}</pre></div>
				<div class="timeline-footer">
					<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
				</div>
			</div>
		</li>
	{{/each}}
</script>
 	
</div>
<%@include file="../include/footer.jsp"%>

<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		var hours = dateObj.getHours();
		var minutes = dateObj.getMinutes();
		var seconds = dateObj.getSeconds();
		return year + "/" + month + "/" + date + " - " + hours + ":" + minutes + ":" + seconds;
	});
	
	var printData = function(replyArr, target, templateObject){
		var template = Handlebars.compile(templateObject.html()); 
		
		var html = template(replyArr); // 새로운 HTML element 작성
		$(".replyLi").remove(); //기존의 HTML element 삭제
		target.after(html); //작성된 새 HTML element를 추가
	}
	//---------------------------------------목록 처리 메소드
	var bNo = ${dto.bno};
	var replyPage = 1;
	function getPage(pageInfo){
		$.getJSON(pageInfo, function(data){
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));

			$("#modifyModal").modal('hide');
		});
	}

	//---------------------------------페이징 처리 메소드
	function printPaging(pageMaker, target){
		var str = "";
		
		if(pageMaker.prev){
			str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
		}
		
		for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){				
				var strClass= pageMaker.cri.page == i?'class=active':'';
			  str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next){
			str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
		}
		//$('.pagination').html(str);
		target.html(str);
	}
//해당하는 id값에 댓글목록 나오게하기
$(function(){
	$("#repliesDiv").html(function(){
		if ($(".timeline li").length > 1) {
			return;
		}
		getPage("/mysp/replies/" + bNo + "/" + replyPage);
	});
});
//해당페이지 누루면 넘어감
$(function(){
	$(".pagination").on("click", "li a", function(event){
		event.preventDefault();
		replyPage = $(this).attr("href");
		getPage("/mysp/replies/"+bNo+"/"+replyPage);
	});
});
</script>
<script>
//댓글등록
$(function(){
	$("#replyAddBtn").click(function(){
		 
		 var replyerObj = $("#newReplyWriter");
		 var replytextObj = $("#newReplyText");
		 var replyer = replyerObj.val();
		 var replyText = replytextObj.val();
//		 var replyText = replytextObj.val().replace(/\n/g, "<br>");
		  $.ajax({
				type : 'post',
				url : '/mysp/replies',
				headers : { 
				      "Content-Type" : "application/json",
				      "X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bNo : bNo,
					replyer : replyer,
					replyText : replyText
				}),
				success : function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						replyPage = 1;
						getPage("/mysp/replies/" + bNo + "/" + replyPage);
						replyerObj.val("");
						replytextObj.val("");
					}
			}});
	});
});
</script>

<script type="text/javascript">
$(function(){//모달 이벤트 처리
	$(".timeline").on("click", ".replyLi", function(event){
		var reply = $(this);
		$("#replyText").val(reply.find('.timeline-body').text());
		$(".modal-title").html(reply.attr("data-rno"));
	});
});

//댓글 수정
$("#replyModBtn").click(function(){
	  
	  var rNo = $(".modal-title").html();
	  var replyText = $("#replyText").val();
	  
	  $.ajax({
			type:'put',
			url:'/mysp/replies/'+rNo,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "PUT" },
			data:JSON.stringify({replyText:replyText}), 
			dataType:'text', 
			success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCESS'){
					alert("수정 되었습니다.");
					getPage("/mysp/replies/"+bNo+"/"+replyPage );
				}
		}});
});

//댓글 삭제
$("#replyDelBtn").click(function(){

	  var rNo = $(".modal-title").html();
	  var replyText = $("#replyText").val();
	  
	  $.ajax({
			type:'delete',
			url:'/mysp/replies/'+rNo,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "DELETE" },
			dataType:'text', 
			success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCESS'){
					alert("삭제 되었습니다.");
					getPage("/mysp/replies/"+bNo+"/"+replyPage );
				}
		}});
});
</script>






<script type="text/javascript">
	$(function(){
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		$("#modifyBtn").click(function(){
			formObj.attr("action", "/mysp/board/modifyPage");
			formObj.attr("method", "GET");
			formObj.submit();
		});
		$("#delBtn").click(function(){
			formObj.attr("action", "/mysp/board/delete");
			formObj.submit();
		});
		$("#listBtn").click(function(){
			self.location = "/mysp/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
		});
	});
</script>
