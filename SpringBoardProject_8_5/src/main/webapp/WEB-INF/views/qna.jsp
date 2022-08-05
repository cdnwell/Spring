<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
.div_box {
	width: 570px;
	margin: 0 auto;
	margin-top: 100px;
}

.content_box {
	display: flex;
	flex-direction: column;
	float: left;
	width: 400px;
}

.content_box>textarea {
	margin-top: -12px;
	border: 1px solid #b7b7b7;
	border-radius: 5px;
	padding: 10px;
}

.input_content {
	height: 10px;
	border: 1px solid #b7b7b7;
	border-radius: 5px;
	padding: 10px;
}

.request_btn {
	margin-left: 15px;
	width: 150px;
	height: 170px;
	border: none;
	background-color: cornflowerblue;
	color: white;
	border-radius: 7px;
}

.myButton {
	box-shadow: 3px 4px 0px 0px #1564ad;
	background:linear-gradient(to bottom, #79bbff 5%, #378de5 100%);
	background-color:#79bbff;
	border-radius:5px;
	border:1px solid #337bc4;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:16px;
	font-weight:bold;
	padding:12px 30px;
	text-decoration:none;
	text-shadow:0px 1px 0px #528ecc;
}
.myButton:hover {
	background:linear-gradient(to bottom, #378de5 5%, #79bbff 100%);
	background-color:#378de5;
}
.myButton:active {
	position:relative;
	top:1px;
}
.qna_title ul{
	list-style-type : none;
	font-size : 0px;
	padding : 0px;
}
.qna_title li{
	display : inline-block;
	font-size : 16px;
	margin-right : 50px;
}
#qna_list{
	width : 800px;
	margin : 0 auto;
}
#btn_more{
	width : 800px;
	margin : 0 auto;
	border : none;
	border-radius: 5px;
	height : 50px;
	font-size : 18px;
	display : block;
	margin : 0 auto;
}
</style>
<!-- jquery ui는 jquery 기준으로 만들어졌기 때문에 jquery 아래에 붙여넣기 해야 한다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script>
    $(function(){
        var input = $("input[name=title]");
        var text = $("textarea[name=content]");
        $( "#qna_list" ).accordion();
        
        $(".div_box").submit(function(r){
        	var result = true;
            if(input.val().length > 33){
                alert('제목의 글자수는 33자를 초과하면 안됩니다.');
                result = false;
            } else if (input.val().length == 0){
            	alert('문의 제목을 입력해주세요.');
            	result = false;
            } else if (text.val().length > 1333){
                alert('문의 내용의 글자수는 1333글자를 초과하면 안됩니다.');
                result = false;
            } else if (text.val().length == 0){
            	alert('문의 내용을 입력해주세요');
            	result = false;
            }
            return result;
        });
       
        var page = 1;	//페이지 번호
        $("#btn_more").click(function(){
        	page++;
        	$.ajax({
        		url : "nextQnaList.do",
        		data : "page="+page,
        		dataType : "json",
        		success : function(r){
        			if(r.nextPage == 0){
        				$("#btn_more").off("click").text("더 이상 불러올 내용이 없습니다.");
        			}
        			var arr = r.list;
        			var tag = "";
        			for(i=0;i<arr.length;i++){
        				tag += "<h3 class='qna_title'><ul>";
        				tag += "<li>제목 : "+arr[i].title+"</li>";
        				tag += "<li>작성자 : "+arr[i].writer+"</li>";
        				tag += "<li>작성일 : "+arr[i].wdate+"</li>";
        				switch(arr[i].status){
        				case "0":
        					tag += "<li>안읽음</li>";
        					break;
        				case "1":
        					tag += "<li>읽음</li>";
        					break;
        				case "2":
        					tag += "<li>답변와료</li>";
        					break;
        				}
        				tag += "</ul></h3><div>";
        				tag += "<p class='qna_content'>문의 내용 : ${dto.content }</p><hr>";
        				tag += "<p class='qna_response'>답변 내용 : ${dto.response }</p></div>";
        			}
       				$("#qna_list").append(tag);
       				$("#qna_list").accordion("refresh");
        		}
        	});
        });
    });
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<form class="div_box" action="sendQnA.do">
		<div class="content_box">
			<input type="text" name="title" class="input_content"
				placeholder="문의글 제목 입력하세요"><br>
			<textarea name="content" cols="30" rows="7" placeholder="내용을 입력해주세요"></textarea>
		</div>
		<button class="request_btn myButton">문의하기</button>
	</form>
	<hr>
	<div id="qna_list">
	<!-- 문의 답변 목록 -->
	<c:forEach var="dto" items="${requestScope.list }">
		<h3 class="qna_title">
			<ul>
				<li>제목 : ${dto.title }</li>
				<li>작성자 : ${dto.writer }</li>
				<li>작성일 : ${dto.wdate }</li>
				<li>
				<c:choose>
					<c:when test="${dto.status == 0 }">안읽음</c:when>
					<c:when test="${dto.status == 1 }">읽음</c:when>
					<c:otherwise>답변완료</c:otherwise>
				</c:choose>
				</li>
			</ul> 
		</h3>
		<div>
		<p class="qna_content">문의 내용 : ${dto.content }</p>
		<hr>
		<p class="qna_response">답변 내용 : ${dto.response }</p>
		</div>
	</c:forEach>
	<button id="btn_more">더보기</button>
	</div>
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>