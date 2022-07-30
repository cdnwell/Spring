<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
nav {
	width: 1200px;
	margin: 0 auto;
	
	height : 57px;
	padding : 4px;
}

nav>ul {
	display: flex;
	flex-direction: row;
	justify-content: center;
	list-style-type: none;
	text-align : center;
}

nav>ul>li {
	width: 200px;	
}

li > a:link, li > a:visited{
	color : black;
	font-weight : bold;
	text-decoration : none;
}
</style>
<c:if test="${sessionScope.dto == null}">
	<c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.dto == null or sessionScope.dto.gradeNo == 1 }">
	<script type="text/javascript">
		alert('권한이 없습니다.');
		location.href = "logout.do";
	</script>
</c:if>
<c:if test="${sessionScope.dto.gradeNo == 7 }">
	<nav>
		<ul>
			<li><a href="main.do">홈</a></li>
			<li><a href="#">회원목록</a></li>
			<li><a href="#">회원추가</a></li>
		</ul>
	</nav>
</c:if>