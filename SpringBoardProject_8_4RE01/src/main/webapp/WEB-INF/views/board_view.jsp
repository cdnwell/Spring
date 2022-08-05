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
    <link rel="stylesheet" href="css/wave.css">
    <link rel="stylesheet" href="css/header_footer.css">
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
        });
    </script>
</head>

<body>
    <header>
        <div>
            <span class="login_info">${sessionScope.name} 로그인</span>
            <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
                <defs>
                    <path id="gentle-wave"
                        d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
                </defs>
                <g class="parallax">
                    <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(0,0,0,0.7" />
                    <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(0,0,0,0.5)" />
                    <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(0,0,0,0.3)" />
                    <use xlink:href="#gentle-wave" x="48" y="7" fill="rgba(0,0,0,0.3)" />
                </g>
            </svg>
            <nav>
                <ul>
                    <li><a href="main.do">HOME</a></li>
                    <li><a href="logout.do">로그아웃</a></li>
                    <li><a href="#">회원정보수정</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <section>
        <div class="title_box">
            <div class="title_bDate">
                작성자<br>
                <span class="title_bDate_date"> 글 작성일 </span>
            </div>
            <div class="title_title"> 글 제목 </div>
            <div class="title_bCount">
                <b>조회숫자</b> &nbsp;<span class="title_bCount_views">views</span>
            </div>
            <div class="title_preferences">
                <a href=""><img src="img/thumbs-up.png" class="title_like"></a> 1 <a href=""><img
                        src="img/thumbs-down.png" class="title_hate"></a> 1
            </div>
        </div>
        <div class="content_box">
            글 내용
        </div>
        <div class="file_box">
            <img src="img/download.png" class="file_box_download"><span class="file_box_span">파일 목록</span>
            <p><a href="#">파일1</a></p>
            <p><a href="#">파일2</a></p>
            <p><a href="#">파일3</a></p>
        </div>
        <div class="comment_box">
            <div class="comment_box_title">
                comment
            </div>
            <c:forEach var="m" items="${requestScope.comment }">
            <div class="comment_view_box">
                <div class="comment_view_box_info">
                    <p>m.writer</p>
                    <p>m.cdate</p>
                </div>
                <div class="comment_view_box_content">
                    m.content
                    <div class="comment_view_box_preferences">
                        <a href=""><img src="img/thumbs-up.png" class="comment_view_box_like"></a> m.clike
                        <a href=""><img src="img/thumbs-down.png" class="comment_view_box_hate"></a> m.chate
                    </div>
                </div>
            </div>
            </c:forEach>
            <div class="comment_write_box">
                <div class="comment_write_box_info">
                    <p>${sessionScope.id }</p>
                    <p>${sessionScope.date }</p>
                </div>
                <form class="comment_write_box_content">
                    <div class="comment_write_box_content_mod">
                        <input type="hidden" name="cno" value="">
                        <input type="hidden" name="bno" value="">
                        <textarea name="content" class="comment_write_content"></textarea>
                    </div>
                    <span class="comment_count">0/500 </span>
                    <button>전송</button>
                </form>
            </div>
        </div>
    </section>

    <footer>
        <br>
        <span>이메일 : cdnwellhk@gmail.com</span><br>
        <span>홈페이지 : <a href="https://github.com/cdnwell">https://github.com/cdnwell</a></span><br>
        <span>copyrights&copy All rights reserved</span>
    </footer>
</body>

</html>