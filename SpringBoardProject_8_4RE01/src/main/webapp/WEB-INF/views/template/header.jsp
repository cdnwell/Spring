<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    header{
        width : 1200px;
        margin : 0 auto;
        margin-top : 20px;
        border: 1px solid gray;
        background-color: darkslateblue;
    
        
    }
    nav{
        height: 80px;
    }
    ul{
        text-align: center;
    }
    li{
        display: inline-block;
    }
    li > a{
        display: inline-block;
        width: 150px;
        text-decoration: none;
        color: white;
        padding: 30px;
    }
    li > a:hover{
        color: darksalmon;
        transition: 0.8s;
    }
    .login_info{
        float: right;
    }
    .header_img{
        width: 1200px;
        height: 300px;
        overflow: hidden;
    }
    .header_img > img{
        width: 1200px;
    }
</style>
<header>
    <div class="header_img">
        <img src="img/javier-miranda-Jn2EaLLYZfY-unsplash.jpg">
    </div>
        <c:if test="${sessionScope.login != null && sessionScope.id != null}">
        <nav>
            <ul>
                <li><a href="/">HOME</a></li>
                <li><a href="logout.do">로그아웃</a></li>
                <li><a href="#">회원정보수정</a></li>
            </ul>
        </nav>
        </c:if>
        <c:if test="${sessionScope.login == null && sessionScope.id == null}">
        <nav>
            <ul>
                <li><a href="/">HOME</a></li>
                <li><a href="loginView.do">로그인</a></li>
                <li><a href="#">회원가입</a></li>
            </ul>
        </nav>
        </c:if>
    <span class="login_info">${sessionScope.name}님이 로그인하셨습니다</span>
</header>


