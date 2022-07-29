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
		$("#btn_search").click(function(){
			var d = "kind="+$("#kind").val()+"&search="+$("#search").val();
			$.ajax({
				url:"search.do",
				data:d,
				dataType:"json",
				type:"get",
				success:function(r){
					var tag = "";
					for(i=0;i<r.length;i++){
						tag += "<tr>";
						tag += "<td>" +r[i].id+ "</td>";
						tag += "<td>" +r[i].passwd+ "</td>";
						tag += "<td>" +r[i].name+ "</td>";
						tag += "<td>" +r[i].age+ "</td>";
						tag += "<td>" +r[i].gender+ "</td>";
						tag += "<td>" +r[i].address+ "</td>";
						tag += "</tr>";
					}
					$("tbody").html(tag);
				}
			});
		});
	});
</script>
</head>
<body>
<h1>회원관리</h1>
<hr>
<form action="register.do">
	<!-- 회원정보 등록 폼 -->
	<input type="text" name="id">
	<input type="password" name="passwd">
	<input type="text" name="name">
	<input type="text" name="age">
	<input type="radio" name="gender" value="M"> 남
	<input type="radio" name="gender" value="F"> 여
	<input type="text" name="address">
	<button>추가</button>
</form>
<div>
	<select id="kind">
		<option value="id">아이디</option>
		<option value="name">이름</option>
		<option value="address">주소</option>
	</select>
	<input type="text" id="search">
	<button id="btn_search">검색</button>
</div>
<table>
<thead>
<tr>
	<th>아이디</th>
	<th>암호</th>
	<th>이름</th>
	<th>나이</th>
	<th>성별</th>
	<th>주소</th>
	<th>비고</th>
</tr>
</thead>
<tbody>
<c:forEach var="member" items="${requestScope.list }">
	<tr>
		<td>${member.id }</td>
		<td>${member.passwd }</td>
		<td>${member.name }</td>
		<td>${member.age }</td>
		<td>${member.gender }</td>
		<td>${member.address }</td>
		<td><a href="delete.do?id=${member.id }">삭제</a> / 
		<a href="updateView.do?id=${member.id }">수정</a></td>
	</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>









