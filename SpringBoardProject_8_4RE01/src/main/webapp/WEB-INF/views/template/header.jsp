<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/wave.css">
<style>
* {
	margin: 0;
	padding: 0;
}

header {
	width: 1200px;
	margin: 0 auto;
	margin-top: 20px;
	border: 1px solid gray;
	position: relative;
	text-align: center;
	width: 1200px;
	height: 400px;
	background-image: url(img/javier-miranda-Jn2EaLLYZfY-unsplash.jpg);
	background-size: 1200px;
}

nav {
	height: 80px;
	position: absolute;
	left: 288px;
	top: 320px;
}

ul {
	text-align: center;
}

li {
	display: inline-block;
}

li>a {
	display: inline-block;
	width: 150px;
	text-decoration: none;
	color: white;
	padding: 30px;
}

li>a:hover {
	color: darksalmon;
	transition: 0.8s;
}

.login_info {
	display: inline-block;
	position: absolute;
	color: white;
	bottom: 3px;
	right: 7px;
}
</style>
<header>
	<div>
		<svg class="waves" xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28"
			preserveAspectRatio="none" shape-rendering="auto">
            <defs>
            <path id="gentle-wave"
				d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
            </defs>
            <g class="parallax">
            <use xlink:href="#gentle-wave" x="48" y="0"
				fill="rgba(0,0,0,0.7" />
            <use xlink:href="#gentle-wave" x="48" y="3"
				fill="rgba(0,0,0,0.5)" />
            <use xlink:href="#gentle-wave" x="48" y="5"
				fill="rgba(0,0,0,0.3)" />
            <use xlink:href="#gentle-wave" x="48" y="7"
				fill="rgba(0,0,0,0.3)" />
            </g>
        </svg>

		<c:if test="${sessionScope.login != null && sessionScope.id != null}">
			<nav>
				<ul>
					<li><a href="/">HOME</a></li>
					<li><a href="logout.do">로그아웃</a></li>
					<li><a href="#">회원정보수정</a></li>
				</ul>
			</nav>
		</c:if>
		<c:if test="${sessionScope.login == null || sessionScope.id == null}">
			<nav>
				<ul>
					<li><a href="/">HOME</a></li>
					<li><a href="loginView.do">로그인</a></li>
					<li><a href="#">회원가입</a></li>
				</ul>
			</nav>
		</c:if>
		<c:if test="${sessionScope.login != null && sessionScope.id != null }">
			<span class="login_info">${sessionScope.name}님 로그인</span>
		</c:if>
	</div>
	
</header>


