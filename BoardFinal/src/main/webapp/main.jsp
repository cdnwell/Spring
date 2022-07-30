<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  
   section{
      width: 1200px;
      margin:0 auto;
   }
   section table{
      width: 90%;
      border-collapse: collapse;
      margin : 0px auto;
   }
   section table td,section table th{
      border:1px solid black;
      text-align: center;
      padding:10px 0px;
   }
   section tfoot{
      font-size: 0px;
   }
   section tfoot a:link,section tfoot a:visited{
      display: inline-block;
      padding:0px 10px;
      text-decoration: none;
      color:black;
      font-size: 16px !important;
   }
   
   .current_page{
      display: inline-block;
      padding:0px 10px;
      font-size: 16px !important;
      color:red;
      font-weight: bold;
   }
   
   tfoot > tr > td{
   		position : relative;
   }
   .btn_write{
   		display : inline-block;
   		padding : 0px 20px !important;
   		position : absolute;
   		right : 0px;
   }
</style>
</head>
<body>
<!-- 회원등급이 1인지 확인을 한 후에 1이면 경고창을 띄워서 '권한이 없습니다.' 경고창을 띄운후 이전페이지로 이동 -->
<!-- 비교는 null부터 없는 값을 꺼낼 수는 없기 때문에 -->
<!-- 회원등급이 7이면 관리자이므로 메뉴에 홈 게시판 회원관리 메뉴를 출력한다. if를 써도 되고 choose를 써도 된다. -->
	<jsp:include page="template/header.jsp"></jsp:include>
	<section>
		<table>
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>닉네임</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>싫어요</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="board" items="${requestScope.board_list }">
				<tr>
					<td>${board.bno }</td>
					<td><a href="boardView.do?bno=${board.bno }">${board.title}</a></td>
					<td>${board.nick}</td>
					<td>${board.bdate}</td>
					<td>${board.bcount}</td>
					<td>${board.blike}</td>
					<td>${board.bhate}</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<!-- 게시판 페이징 처리 -->
				<tr>
					<td colspan="7">
						<!-- 이전 페이지 그룹으로 이동 -->
						<c:if test="${requestScope.pagging.previousPageGroup }">
						<a href="main.do?pageNo=${requestScope.pagging.startPageOfPageGroup - 1 }">
						◁
						</a>
						</c:if>
						<!-- 페이지 번호 출력 -->
						<c:forEach var="i" begin="${requestScope.pagging.startPageOfPageGroup }"
						end="${requestScope.pagging.endPageOfPageGroup }" >
							<c:choose>
								<c:when test="${i == requestScope.pagging.currentPageNo }">
									<span class="current_page">${i }</span>
								</c:when>
								<c:otherwise>
									<a href="main.do?pageNo=${i }">${i }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 다음 페이지 그룹으로 이동 -->
						<c:if test="${requestScope.pagging.nextPageGroup }">
							<a href="main.do?pageNo=${requestScope.pagging.endPageOfPageGroup + 1 }">▷</a>
						</c:if>
						<a href="board_write_view.jsp" class="btn_write">글쓰기</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</section>		
</body>
</html>





