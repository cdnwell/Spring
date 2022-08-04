<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.container{
	width:300px;
	margin:0px auto;
	height: 600px;
	padding-top:200px;
	box-sizing: border-box;
}

fieldset{
	width : 327px;
	padding : 15px 0;
}

.login_fieldset{
	padding-left : 10px;
}

legend{
	padding : 0 10px;
}

.login_inf_box{
	float:left;
}

.login_box label{
	margin-left : 15px;
}

.login_btn{
	background-color : white;
	width: 50px;
	height : 45px;
	border : 1px solid #b7b7b7;
	border-radius : 5px;
	margin-top : 1px;
	margin-left : 10px;
}

.login_btn:hover{
	background-color : #b7b7b7;
	color : white;
}

.pass_span{
	padding-left : 16px;
}

.register_a{
	display : inline-block;
	margin-top : 3px;
	padding-left : 15px;
}
</style>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	
	<div class="container">
	<c:set var="flag" value="false"/>
	<c:if test="${null ne sessionScope.login }">
		<c:set var="flag" value="${sessionScope.login }"/>
	</c:if>
	<c:choose>
		<c:when test="${true eq flag }">
			<fieldset class="login_fieldset">
			<legend>로그인</legend>
			${sessionScope.name }님이 로그인 하셨습니다.
			<p>
			<a href="logout.do?loginMsg=fieldset">로그아웃</a> | <a href="member_update_view.do">정보수정</a>
			</p>
			</fieldset>
		</c:when>
		<c:otherwise>
			<fieldset>
				<legend>로그인</legend>
				<form method="post" action="login.do" class="login_box">
					<div class="login_inf_box">				
					<label for="id" >아이디 : </label><input type="text" name="id" id="id"><br>
					<label for="pass" >암호 </label> <span class="pass_span">:</span> <input type="password" name="pass" id="pass">
					</div>
					<button type="submit" class="login_btn">로그인</button>
					<a href="register.do" class="register_a">회원가입</a>
					
				</form>
			</fieldset>
		</c:otherwise>
	</c:choose>
	</div>
		
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>




