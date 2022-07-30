<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
	form{
		width : 422px;
		margin : 20px auto;
		display : flex;
		flex-direction : column;
	}
	
	.title{
		margin-top : 20px;
		width : 400px;
		height : 20px;
		padding : 10px;
	}
	
	.content{
		margin-top : 5px;
		width : 402px;
		padding : 10px;
		height : 400px;
	}
	
	button{
		display : inline-block;
		width : 207px;
		box-sizing : border-box;
		margin : 2px;
	}
	
</style>
<script>
	window.onload = function(){
		var btnBack = document.querySelector(".btn_back");	
		btnBack.onclick = function(){
			history.back();
		};
	};
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<c:if test="${sessionScope.dto.id != requestScope.board.writer }">
		<script>
			location.href= "main.do";
		</script>
	</c:if>
	<section>
		<!-- 첨부 파일 때문에 get방식으로 보내지 못하여 post로 보낸다. get방식으로는 4mb~8mb 정도만 보냄 -->
		<!-- post는 파일 크기 무제한으로 전송 가능하다. -->
		<h2>게시글 수정</h2>
		<form action="boardUpdate.do" method="post">
			<input type="text" name="title" class="title" 
				value="${requestScope.board.title }" placeholder="제목을 입력하세요"
			>
			<textarea name="content" class="content" placeholder="내용을 입력하세요">${requestScope.board.content }
			</textarea>
			<div class="buttons">
			<button>수정하기</button><button
			 type="button" class="btn_back">뒤로가기</button>
			</div>
			<input type="hidden" name="bno" value="${requestScope.board.bno }">
		</form>
	</section>
</body>
</html>