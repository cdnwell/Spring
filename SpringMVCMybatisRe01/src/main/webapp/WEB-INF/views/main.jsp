<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
<style type="text/css">
	.container{
		display : flex;
		flex-direction : column;
		align-items: center;
	}
	table{
		border-collapse : collapse;
	}
	td,th{
		padding : 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원정보 관리 시스템</h1>
		<form action="register.do" method="post">
			<input type="text" name="id" placeholder="아이디">
			<input type="password" name="passwd" placeholder="패스워드">
			<input type="text" name="name" placeholder="이름">
			<input type="text" name="age" placeholder="나이">
			<input type="radio" value="M" name="gender"> 남
			<input type="radio" value="F" name="gender"> 여
			<input type="text" name="address" placeholder="주소">
			<button>회원정보등록</button>
		</form>
		<hr>
		<table>
			<c:forEach var="m" items="${requestScope.list }">
			<tr>
				<td>${m.id }</td>
				<td>${m.passwd }</td>
				<td>${m.name }</td>
				<td>${m.age }</td>
				<td>${m.gender }</td>
				<td>${m.address }</td>
				<td>
					<a href="delete.do?id=${m.id }">삭제</a> /
					<a href="updateView.do?id=${m.id }">수정</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>