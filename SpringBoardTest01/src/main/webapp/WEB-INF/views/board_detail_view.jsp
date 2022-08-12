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
        section {
            width: 1000px;
            margin: 40px auto;

        }

        .title_box {
            position: relative;
            border: 1px solid #b7b7b7;
            width: 100%;
            height: 100px;
            box-sizing: border-box;
            background-color: #d7d7d7;
        }

        .title_bDate {
            display: inline-block;
            width: 15%;
            text-align: center;
            padding-top: 30px;
        }

        .title_bDate_date {
            font-size: 13px;
            color: #676767;
        }

        .title_title {
            display: inline-block;
            position: absolute;
            width: 70%;
            text-align: center;
            height: 50px;
            top: 36px;
            font-size: 20px;
            font-weight: bold;
        }

        .title_bCount {
            display: inline-block;
            position: absolute;
            top: 30px;
            right: 15px;
        }

        .title_preferences {
            position: absolute;
            right: 10px;
            bottom: 6px;
        }

        .title_like,
        .title_hate {
            width: 20px;
            padding-top: 17px;
        }

        .title_like:hover,
        .title_hate:hover {
            filter: opacity(0.4) drop-shadow(0 0 0 #131C2B);
        }

        .title_bCount_views {
            font-size: 14px;
            font-family: serif;
        }



        .content_box {
            position: relative;
            width: 1000px;
            margin: 0 auto;
            height: 500px;
            border: 1px solid #b7b7b7;
            top: 26px;
            padding: 20px;
            box-sizing: border-box;
        }



        .file_box {
            position: relative;
            width: 1000px;
            margin: 0 auto;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
            margin-top: 25px;
            background-color: #d7d7d7;
        }

        .file_box p {
            padding: 0 15px;
            padding-bottom: 12px;
        }

        .file_box p:first-of-type {
            padding-top: 10px;
        }

        .file_box_download {
            display: inline-block;
            width: 15px;
            padding-right: 5px;
            padding-left: 12px;
            padding-top: 10px;
        }

        .file_box_span {
            display: inline-block;
            position: absolute;
            top: 7px;
            left: 35px;
        }




        .comment_box {
            width: 1000px;
            margin: 0 auto;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
            position: relative;
        }

        .comment_box_title {
            width: 100%;
            height: 40px;
            border-bottom: 1px solid #b7b7b7;
            padding-left: 15px;
            padding-top: 10px;
            font-size: 20px;
        }

        .comment_view_box {
            display: flex;
            flex-direction: row;
            width: 100%;
            border-bottom: 1px solid #b7b7b7;
        }

        .comment_view_box_info {
            min-height: 120px;
            width: 15%;
            border-right: 1px solid #b7b7b7;
            background-color: #d7d7d7;
            box-sizing: border-box;

            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .comment_view_box_info p:last-child {
            color: #676767;
            font-size: 13px;
        }

        .comment_view_box_content {
            width: 85%;
            padding: 25px;
            padding-bottom: 40px;
            position: relative;
            box-sizing: border-box;

        }

		.comment_view_box_delete a{
            display: inline-block;
            position: absolute;
            right: 106px;
            bottom: 9px;
        }

        .comment_view_box_delete a:link, .comment_view_box_delete a:visited{
            font-size: 14px;
        }

        .comment_view_box_preferences {
            position: absolute;
            right: 10px;
            bottom: 6px;
        }

        .comment_view_box_like,
        .comment_view_box_hate {
            width: 20px;
            padding-top: 17px;
        }

        .comment_view_box_like:hover,
        .comment_view_box_hate:hover {
            filter: opacity(0.4) drop-shadow(0 0 0 #131C2B);
        }

        .comment_write_box{
            display: flex;
            flex-direction: row;
            width: 100%;
            border-bottom: 1px solid #b7b7b7;
        }

        .comment_write_box_info {
            min-height: 170px;
            width: 15%;
            border-right: 1px solid #b7b7b7;
            background-color: #d7d7d7;
            box-sizing: border-box;

            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .comment_write_box_info p:last-child {
            color: #676767;
            font-size: 13px;
        }

        .comment_write_box_content{
            width: 85%;
            height: 200px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
        }

        .comment_write_box_content_mod{
            width: 100%;
            box-sizing: border-box;
            display: flex;
            flex-direction: row;
            position: relative;
        }
        .comment_write_box_content_mod textarea{
            border: none;
            padding: 20px;
            font-size: 16px;
            width: 100%;
            height: 120px;

            outline: none;
            resize: none;
        }
        .comment_write_box_content span{
            position: absolute;
            right: 110px;
            bottom: 12px;
        }

        .comment_write_box_content button{
            border: none;
            width: 100px;
            height: 40px;
            background-color: #d7d7d7;
            color: black;
            align-self: flex-end;
        }

        .comment_write_box_content button:hover{
            background-color: #676767;
            color: white;
        }

		.comment_not_login{
            width: 100%;
            text-align: center;
            font-size: 18px;
            padding: 10px 0;
        }

        .comment_not_login a:link, .comment_not_login a:visited{
            color: cornflowerblue;
        }
        
        
        
        .content_menu_box{
        	width :100%;
        	display :flex;
        	flex-direction : row;
        	justify-content: space-between;
        	box-sizing: border-box;
        }
        
        .menu_btn{
        	display : inline-block;
        	min-width : 70px;
        	background-color : #d7d7d7;
        	border : 1px solid #b7b7b7;
        	border-radius : 6px;
        	color : black;
        	text-decoration : none;
        	text-align : center;
        	padding : 7px;
        	margin : 4px;
        }
        
        .comment_delete_a{
        	text-decoration : none;
        	color : black;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function(){
            var content = $(".comment_write_content");
            var count = $(".comment_count");

            content.keyup(function(){
                if(content.val().length > 500){
                    content.val(content.val().substring(0,500));
                }
                var content_count = content.val().length;
                count.html(content_count+'/500' );
            });
            
            $('.boardLike').click(function(){
            	var d="bno=${requestScope.board.bno}";
            	$.ajax({
            		url : "boardLike.do",
            		data : d,
            		type : "get",
            		success : function(r){
            			if(r!=0){
	            			alert('이 게시글의 좋아요를 누르셨습니다.');
            			} else {
            				alert('이 게시글의 좋아요를 취소하셨습니다.');
            			}
            				location.reload();
            		}
            	});
            });
            
            $('.boardHate').click(function(){
            	var d="bno=${requestScope.board.bno}";
            	$.ajax({
            		url : "boardHate.do",
            		data : d,
            		type : "get",
            		success : function(r){
            			if(r!=0){
	            			alert('이 게시글의 싫어요를 누르셨습니다.');
            			} else {
            				alert('이 게시글의 싫어요를 취소하셨습니다.');
            			}
            			location.reload();
            		}
            	});
            });
        });
    </script>
</head>

<body>
	<jsp:include page="template/header.jsp"></jsp:include>
    <section>
        <div class="title_box">
            <div class="title_bDate">
               	${requestScope.board.writer }<br>
                <span class="title_bDate_date"> ${requestScope.board.bDate } </span>
            </div>
            <div class="title_title"> ${requestScope.board.title } </div>
            <div class="title_bCount">
                <b>${requestScope.board.bCount }</b> &nbsp;<span class="title_bCount_views">views</span>
            </div>
            <div class="title_preferences">
                <a class="boardLike" href="#"><img src="img/thumbs-up.png" class="title_like"></a> ${requestScope.board.bLike } <a class="boardHate" href="#"><img
                        src="img/thumbs-down.png" class="title_hate"></a> ${requestScope.board.bHate }
            </div>
        </div>
        <div class="content_box">
           ${requestScope.board.content }
        </div>
        <div class="file_box">
            <img src="img/download.png" class="file_box_download"><span class="file_box_span">파일 목록</span>
            <c:forEach var="f" items="${requestScope.flist }">
            <p><a href="fileDown.do?fno=${f.fno }&bno=${f.bno }">${f.fileName }</a></p>
            </c:forEach>
        </div>
        <div class="content_menu_box">
        	<div class="menu_list_box">
        		<a href="/?pageNo=${requestScope.pageNo }" class="menu_list menu_btn">목록으로</a>
        	</div>
        	<div class="move_btn_box">
        		<c:choose>
        			<c:when test="${requestScope.move.BEFORE == -1 }">
        				<a class="prev_btn menu_btn">이전 글이 없습니다</a>
        			</c:when>
        			<c:otherwise>
        				<a href="boardView.do?bno=${requestScope.move.BEFORE }" class="prev_btn menu_btn">이전 글</a>
        			</c:otherwise>
        		</c:choose>
	        	<c:choose>
	        		<c:when test="${requestScope.move.NEXT == -1 }">
	        			<a class="next_btn menu_btn">다음 글이 없습니다</a>
	        		</c:when>
	        		<c:otherwise>
	        			<a href="boardView.do?bno=${requestScope.move.NEXT }" class="next_btn menu_btn">다음 글</a>
	        		</c:otherwise>
	        	</c:choose>
	        	
        	</div>
        </div>
        <div class="comment_box">
            <div class="comment_box_title">
                comment
            </div>
            <c:forEach var="m" items="${requestScope.comment }">
            <div class="comment_view_box">
                <div class="comment_view_box_info">
                    <p>${ m.writer}</p>
                    <p>${ m.cdate}</p>
                </div>
                <div class="comment_view_box_content">
                    ${m.content}
                    <div class="comment_view_box_preferences">
                        <a href="commentLike.do?cno=${m.cno }&bno=${requestScope.board.bno}"><img src="img/thumbs-up.png" class="comment_view_box_like"></a> ${ m.clike}
                        <a href="commentHate.do?cno=${m.cno }&bno=${requestScope.board.bno}"><img src="img/thumbs-down.png" class="comment_view_box_hate"></a> ${ m.chate}
                    </div>
                    <c:if test="${m.writer == sessionScope.id }">
                    <div class="comment_view_box_delete">
                        <a href="commentDelete.do?cno=${m.cno }&bno=${m.bno}" class="comment_delete_a">댓글삭제</a>
                    </div>
                    </c:if>
                </div>
            </div>
            </c:forEach>
            <c:choose>
            <c:when test="${null ne sessionScope.id && null || sessionScope.login}">
            <div class="comment_write_box">
                <div class="comment_write_box_info">
                    <p>${sessionScope.id }</p>
                    <p>${sessionScope.date }</p>
                </div>
                <form class="comment_write_box_content" action="commentWrite.do" method="get">
                    <div class="comment_write_box_content_mod">
                        <input type="hidden" name="bno" value="${requestScope.board.bno }">
                        <textarea name="content" class="comment_write_content"></textarea>
                    </div>
                    <span class="comment_count">0/500 </span>
                    <button>전송</button>
                </form>
            </div>
            </c:when>
            <c:otherwise>
            	<div class="comment_not_login">
                	댓글 작성은 <a href="loginView.do">로그인</a> 후 가능합니다.
            	</div>
            </c:otherwise>
            </c:choose>
        </div>
    </section>
	
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>

</html>