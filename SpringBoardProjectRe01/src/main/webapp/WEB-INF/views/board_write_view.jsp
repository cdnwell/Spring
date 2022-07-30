<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기 페이지</title>
    <style>
        form{
            display: flex;
            flex-direction: column;
            width: 500px;
            margin: 0 auto;
        }
        form > input:first-child{
            margin-top: 50px;
            margin-bottom: 10px;
        }
        form > input:nth-child(2){
            margin-bottom: 10px;
        }
        form textarea{
            margin-bottom: 10px;
            height: 400px;
        }

        .button_box{
            width: 500px;
            margin: 0 auto;
            margin-bottom : 50px;
        }
        p{
            margin : 0;
            padding: 0;
            margin-bottom: 5px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function(){
            var count = 3;
            $('.btn_back').click(function(){
                history.back();
            });
            $('#plus').click(function(){
                if(count == 5) return;
                count++;
                $('form').append('<p><input type="file" name="file'+count+'"></p>');
            });
            $('#minus').click(function(){
                if(count == 1) return;
                count--;
                $('form').children('p').last().remove();
            });
            $('.btn_submit').click(function(){
                $('form').submit();
            });
        });
    </script>
</head>
<body>
	<c:if test="${sessionScope.id == null || sessionScope.login == null }">
		<script>
			alert("로그인 후 이용해주세요");
			location.href="/loginView.do";
		</script>
	</c:if>
    <jsp:include page="template/header.jsp" flush="false"></jsp:include>

    <form action="boardWrite.do" method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="제목">
        <input type="text" name="writer" placeholder="작성자" value="${sessionScope.id}" readonly>
        <textarea name="content" cols="30" rows="10" placeholder="내용을 입력해주세요"></textarea>
        <p><input type="file" name="file1">
            <button type="button" id="plus">+</button>
            <button type="button" id="minus">-</button>
        </p>
        <p><input type="file" name="file2"></p>
        <p><input type="file" name="file3"></p>
    </form>
    <div class="button_box">
        <button class="btn_submit">글쓰기</button>
        <button type="reset">초기화</button>
        <button class="btn_back">뒤로가기</button>
    </div>

    <jsp:include page="template/footer.jsp" flush="false"></jsp:include>
</body>
</html>