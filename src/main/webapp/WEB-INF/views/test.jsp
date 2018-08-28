<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script> --%>
<script>
var bNo=10;
function getAllList(){//댓글목록
$.getJSON("/mysp/replies/all/"+bNo, function(data){
	//console.log(data.length);
	var str="";
	
	$(data).each(function(){
		str += "<li data-rno='"+this.rNo+"' class='replyLi'>" + this.rNo + ":" + this.replyText + "<button>MOD</button></li>";
	});
	$("#replies").html(str);
});
}

//---------------------------------
//  페이지 들어오자마자  목록 미리보이게
//---------------------------------
$(function(){
	$("#replies").html(function(){
		//getAllList();
		getPageList(replyPage);
	});
});
//---------------------------------
//---------------------------------

//--------------------
//댓글등록
$(function(){
	$("#replyAddBtn").click(function(){
		var replyer = $("#newReplyWriter").val();
		var replyText = $("#newReplyText").val();
		
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
				if(result == 'SUCCESS'){
					alert("등록 되었습니다.");
					//getAllList();
					getPageList(replyPage);
				}
			}
		});
	});
});

//-------------------------------------------------------
//경고창을 통한 목록에 대한 클릭 이벤트
 $(function(){
	$("#replies").on("click", ".replyLi button", function() {
		var reply = $(this).parent();
		var rNo = reply.attr("data-rno");
		var replyText = reply.text();
		 
		$(".modal-title").html(rNo);
		$("#replyText").val(replyText);
		$("#modDiv").show("slow");
		$('.overlay').fadeToggle(700);
	});
	//바깥 클릭시 사라지기
    $('.overlay').click(function () {
        $('#modDiv').hide('slow');
        $(this).fadeOut(700);
    });
   
}); 

//--------------------------------------------------------
//삭제 호출하기
//화면에 보이는 <div>에서 DELETE 버튼을 선택하면 삭제가 되게 처리합니다. 삭제는 HTTP의 DELETE 방식으로 동작하도록 설계해야만 합니다.	
$(function(){
	$("#replyDelBtn").click(function() {

		var rNo = $(".modal-title").html();
		var replyText = $("#replyTEext").val();

		$.ajax({
			type : 'delete',
			url : '/mysp/replies/' + rNo,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					$(".overlay").fadeOut("slow");
					//getAllList();
					getPageList(replyPage);
				}
			}
		});
	});
});

//-------------------------------------------------
//수정작업 처리
$(function(){
	$("#replyModBtn").click(function(){
		var rNo = $(".modal-title").html();
		var replyText = $("#replyText").val();
		
		$.ajax({
			type : 'put',
			url : '/mysp/replies/' + rNo,
			headers: {
				 "Content-Type": "application/json",
				 "X-HTTP-Method-Override": "PUT" 
			},
			data:JSON.stringify({replyText:replyText}),
			dataType:'text',
			success :function(result){
				console.log("result: "+result);
				if(result == 'SUCCESS'){
					alert("수정되었습니다.");
					$("#modDiv").hide("slow");
					$(".overlay").fadeOut("slow");

					//getAllList();
					getPageList(replyPage);
				}else{
					alert("수정실패");
				}
			}
		});
	});
});

</script>

<style type="text/css">
	#modDiv {
	width: 300px;
	height: 100px;
	background-color: ivory;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

.overlay{
	position: fixed;
	background: black;
	opacity: 0.5;
	display: none;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	z-index: 3;
}
.pagination {
  width: 100%;
}

.pagination li{
  list-style: none;
  float: left; 
  padding: 3px; 
  border: 1px solid blue;
  margin:3px;  
}

.pagination li a{
  margin: 3px;
  text-decoration: none;  
}
</style>
</head>
<body>
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replyText'>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id='closeBtn'>Close</button>
		</div>
	</div>
	<div class="overlay"></div>
 <h2>Ajax TEST Page</h2>
 <div>
 	<div>
 		Replyer <input name="replyer" id="newReplyWriter" type="text">
	 </div>
	 <div>
	 	Reply Text <input type="text" name="replyText" id="newReplyText">
	 </div>
	 <button id="replyAddBtn">ADD REPLY</button>
 </div>
 
 <ul id="replies"></ul>
 <ul class='pagination'></ul>
 
 
 
 
 <script>
 //댓글 페이징처리 함수
/*
getPageList() 함수는 페이지 번호를 입력받고, jQuery의 getJSON()을 이용해서 가져온 데이터를 처리합니다.
서버에서 전송된 데이터 중 댓글 목록인 list 데이터를 이용해서 댓글 내용을 표시하고, 
페이징 처리를 위해 만들어진 pageMaker 데이터를 이용해서 printPageing()을 호출합니다.
JSP가 처음 동작하면 1페이지의 댓글을 가져오도록 코드를 수정합니다. 
*/

var bNo = 10;
function getPageList(page){
		
	  $.getJSON("/mysp/replies/"+bNo+"/"+page , function(data){
		  console.log(data.list.length);
		  
		  var str ="";
		  
		  $(data.list).each(function(){
			  str+= "<li data-rno='"+this.rNo+"' class='replyLi'>" 
			  +this.rNo+":"+ this.replyText+
			  "<button>MOD</button></li>";
		  });
		  
		  $("#replies").html(str);
		 printPaging(data.pageMaker);  
	  });
 }		
	
	  
	function printPaging(pageMaker){
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
		$('.pagination').html(str);				
	}
	
var replyPage = 1;
	$(function(){
		$(".pagination").on("click", "li a", function(event){
			event.preventDefault();
			replyPage = $(this).attr("href");
			getPageList(replyPage);
			
		});
	});
	
	
 </script>
 
 
 
 
 
 
 
 
 
 
 
 
</body>
</html>