<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function(){
		//호출시 화면 로드 되자마자 ajax하나를 쓰겠다.
		//데이터는 없으므로 안쓰기
		$.ajax({
			url : "all.do",
			//get, post는 상관이 없으므로 안써줘도 단다.
			dataType : "json",
			success : function(r){
				var tag = "";
				//code 값이 200이면 정상, 500이면 비정상
				//200이면 코드 실행, 아니면 코드 실행하지 말기
				if(r.code == 200){
					var arr = r.result;
					for(i=0;i<arr.length;i++){
						tag += "<p>";
						tag += "<span>"+arr[i].eno+"</span>";
						tag += "<span>"+arr[i].name+"</span>";
						tag += "<span>"+arr[i].department+"</span>";
						tag += "<span>"+arr[i].position+"</span>";
						tag += "</p>";
					}
				} else {
					tag = r.message;
				}
				$(".container").html(tag);
			} 
		});
		
		//----------all do와 search do 두 개 만듦----------//
		
		$(".btn").click(function(){
			var d = $("#frm").serialize();
			$.ajax({
				url : "search.do",
				data : d,
				//get, post는 상관이 없으므로 안써줘도 단다.
				dataType : "json",
				success : function(r){
					var tag = "";
					//code 값이 200이면 정상, 500이면 비정상
					//200이면 코드 실행, 아니면 코드 실행하지 말기
					if(r.code == 200){
						var arr = r.result;
						for(i=0;i<arr.length;i++){
							tag += "<p>";
							tag += "<span>"+arr[i].eno+"</span>";
							tag += "<span>"+arr[i].name+"</span>";
							tag += "<span>"+arr[i].department+"</span>";
							tag += "<span>"+arr[i].position+"</span>";
							tag += "</p>";
						}
					} else {
						tag = r.message;
					}
					$(".container").html(tag);
				} 
			});
		});
	});
	
</script>
</head>
<body>
	<form id="frm">
		<select name="kind">
			<option value="name">이름</option>
			<option value="department">부서</option>
			<option value="position">직급</option>
		</select>
		<input type="text" name="search"><button type="button" class="btn">검색</button>
	</form>
	<hr>
	<!-- 결과값 넣을 부분 -->
	<div class="container"></div>
</body>
</html>