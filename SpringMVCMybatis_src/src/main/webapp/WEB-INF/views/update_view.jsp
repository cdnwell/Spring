<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.do">
		<!-- 회원정보 등록 -->
		<input type="text" name="id" placeholder="아이디" value="${requestScope.dto.id }"> 
		<input type="password" name="passwd" placeholder="비밀번호" value="${requestScope.dto.passwd }"> 
		<input type="text" name="name" placeholder="이름" value="${requestScope.dto.name }"> 
		<input type="text" name="age" placeholder="나이" value="${requestScope.dto.age }"> 
		<input type="radio" name="gender" value="M" 
		<c:if test="${requestScope.dto.gender == 'M' }">
		checked
		</c:if>>남
		<input type="radio" name="gender" value="F"
		<c:if test="${requestScope.dto.gender == 'F' }">
		checked
		</c:if>
		>여 
		<input type="text" name="address" placeholder="주소" value="${requestScope.dto.address }">
		<button>수정</button>
	</form>
</body>
</html>