<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	.btn_submit , .btn_back{
		display : inline-block;
		width : 207px;
		box-sizing : border-box;
		margin : 2px;
	}
	
	#button_box{
		text-align : center;
	}	
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function(){
		var count = 3;
		$(".btn_back").click(function(){
			history.back();
		});
		$("#plus").click(function(){
			if(count == 5) return;
			count++;
			$("form").append("<p><input type='file' name='file"+count+"'></p>");
		});
		$("#minus").click(function(){
			if(count == 1) return;
			count--;
			$("form").children("p").last().remove();
		});
		$(".btn_submit").click(function(){
			$("form").submit();
		});
	});
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<section>
		<!-- 첨부 파일 때문에 get방식으로 보내지 못하여 post로 보낸다. get방식으로는 4mb~8mb 정도만 보냄 -->
		<!-- post는 파일 크기 무제한으로 전송 가능하다. -->
		<form action="boardWrite.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="writer" class="id" value="${sessionScope.dto.id }">
			<input type="text" name="title" placeholder="제목을 입력하세요" class="title">
			<textarea name="content" class="content" placeholder="내용을 입력하세요"></textarea>
			<div class="buttons">
			</div>
			<p><input type="file" name="file1">
			<!-- 파일을 추가하는 경우와 빼는 경우 -->
				<button type="button" id="plus">+</button>
				<button type="button" id="minus">-</button>
			</p>
			<p><input type="file" name="file2"></p>
			<p><input type="file" name="file3"></p>
		</form>
		<div id="button_box">
		<button class="btn_submit">글쓰기</button><button
			 type="button" class="btn_back">뒤로가기</button>
		</div>
	</section>
</body>
</html>