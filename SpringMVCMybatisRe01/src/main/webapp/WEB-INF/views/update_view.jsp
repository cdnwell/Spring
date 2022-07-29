<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	form{
		text-align : center;
	}
</style>
</head>
<body>
	<form action="update.do" method="post">
		<input type="text" name="id" placeholder="아이디" value="${requestScope.dto.id }">
		<input type="password" name="passwd" placeholder="패스워드" value="${requestScope.dto.passwd }">
		<input type="text" name="name" placeholder="이름" value="${requestScope.dto.name }">
		<input type="text" name="age" placeholder="나이" value="${requestScope.dto.age }">
		<input type="radio" value="M" name="gender"
			<c:if test="${requestScope.dto.gender == 'M' }">
				checked
			</c:if>
		> 남
		<input type="radio" value="F" name="gender"
			<c:if test="${requestScope.dto.gender == 'F' }">
				checked
			</c:if>
		> 여
		<input type="text" name="address" placeholder="주소" value="${requestScope.dto.address }">
		<button>회원정보등록</button>
	</form>
</body>
</html>