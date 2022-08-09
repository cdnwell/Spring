<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	/*
	$(function(){
		$.ajax({
			url : 'qnaAdminList.do',
			dataType : 'json',
			type : 'post',
			success : function(r){
				var tag = '';
				var arr = r.result;
				if(r.status == 200){
					for(i=0;i<arr.length;i++){
						tag += '<tr>';
						tag += '<td>'+arr[i].qno+'</td>';
						tag += '<td>'+arr[i].title+'</td>';
						tag += '<td>'+arr[i].writer+'</td>';
						tag += '<td>'+arr[i].wdate+'</td>';
						tag += '<td>'+arr[i].status+'</td>';
						tag += '</tr>';
					}					
				} else {
					tag += '<tr>';
					tag += '<td>' + r.message + '</td>'; 
					tag += '</tr>';
				}
				$('tbody').html(tag);
			}
		});
		
	});
	*/
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<section>
		<h2>문의 내역</h2>
		<table class="result_table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>글읽음</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${requestScope.list }">
					<tr>
						<td>${obj.qno }</td>
						<td> <a href="adminQnaDetailView.do?qno=${obj.qno }">${obj.title }</a></td>
						<td>${obj.writer }</td>
						<td>${obj.wdate }</td>
						<td>
							<c:choose>
								<c:when test="${obj.status == 0 }">안읽음</c:when>
								<c:when test="${obj.status == 1 }">읽음</c:when>
								<c:otherwise>답변완료</c:otherwise>
							</c:choose>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
			<tr>
				<td colspan="6">
					<c:if test="${requestScope.page.previousPageGroup }">
						<a href="qnaAdminView.do?pageNo=${requestScope.page.startPageOfPageGroup-1 }">
							◀
						</a>
					</c:if>
					<c:forEach var="i" begin="${requestScope.page.startPageOfPageGroup}" end ="${requestScope.page.endPageOfPageGroup }">
						<a href="qnaAdminView.do?pageNo=${i }">${i }</a>
					</c:forEach>
					<c:if test="${requestScope.page.nextPageGroup }">
						<a href="qnaAdminView.do?pageNo=${requestScope.page.endPageOfPageGroup+1 }">
							▶
						</a>
					</c:if>
				</td>
			</tr>
		</table>
	</section>
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>