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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div class="container">
		<h3 class="qna_title">
			<ul>
				<li>제목 : ${requestScope.dto.title }</li>
				<li>작성자 : ${requestScope.dto.writer }</li>
				<li>작성일 : ${requestScope.dto.wdate }</li>
				<c:choose>
					<c:when test="${requestScope.dto.status == 0 }">안읽음</c:when>
					<c:when test="${requestScope.dto.status == 1 }">읽음</c:when>
					<c:otherwise>답변완료</c:otherwise>
				</c:choose>
			</ul>
		</h3>
		<div>
			<p>문의내용</p>
			<p class="qna_content">${requestScope.dto.content }</p>
		</div>
		<hr>
		<div class="qna_form">
			<form action="answer.do" method="post">
				<input type="hidden" name="qno" value="${requestScope.dto.qno }">
				<table>
					<tr>
						<td>답변을 한 관리자</td>
						<td>${requestScope.aAdmin }</td>
					</tr>
					<tr>
						<td>답변일 : </td>
						<td>${requestScope.aDate }</td>
					</tr>
					<tr>
						<td>
							답변 내용
						</td>
						<td>
							${requestScope.dto.response }
						</td>
					</tr>
					<tr>
						<td>
							<textarea name="response" placeholder="답변 내용을 입력해주세요"></textarea>
						</td>
						<td>
							<button>답변하기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>