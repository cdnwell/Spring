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
	업로드한 사람 : ${requestScope.writer }<br>
	<c:forEach var="file" items="${requestScope.list }" >
		<a href="fileDown.do?file=${file }">${file }</a><br>
	</c:forEach>
</body>
</html>






