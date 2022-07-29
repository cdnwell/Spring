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

</script>
</head>
<body>
<form action="update.do" method="post">
	<input type="text" name="id" value="${requestScope.dto.id}">
	<input type="password" name="passwd" value="${requestScope.dto.passwd}">
	<input type="text" name="name" value="${requestScope.dto.name}">
	<input type="text" name="age" value="${requestScope.dto.age}">
	<input type="radio" name="gender" value="M" 
	<c:if test="${requestScope.dto.gender == 'M' }">checked</c:if>> 남
	<input type="radio" name="gender" value="F"
	<c:if test="${requestScope.dto.gender == 'F' }">checked</c:if>> 여
	<input type="text" name="address" value="${requestScope.dto.address}">
	<button>수정</button>
</form>
</body>
</html>




