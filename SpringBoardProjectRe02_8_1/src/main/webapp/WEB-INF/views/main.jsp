<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        section{
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
        }
        tr:last-child{
        	text-align : center;
        }
        td, th{
        	padding : 10px 20px;
        }
    </style>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
    <section>
        <table>
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>좋아요</th>
                <th>싫어요</th>
            </tr>
            <c:forEach var="b" items="${requestScope.list}">
                <tr>
                    <td>${b.bno}</td>
                    <td><a href="boardView.do?bno=${b.bno }">${b.title}</a></td>
                    <td>${b.writer}</td>
                    <td>${b.bDate}</td>
                    <td>${b.bCount}</td>
                    <td>${b.bLike}</td>
                    <td>${b.bHate}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                    <c:if test="${requestScope.paging.previousPageGroup}">◁</c:if>
                    <c:forEach var="m" begin="${requestScope.paging.startPageOfPageGroup}"
                    end="${requestScope.paging.endPageOfPageGroup}">
                        ${m }
                    </c:forEach>
                    <c:if test="${requestScope.paging.nextPageGroup}">▷</c:if>
                </td>
            </tr>
        </table>
    </section>
    <jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>