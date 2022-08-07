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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/ckeditor.js"></script>
    <script src="/UploadAdapter.js"></script>
    <script type="text/javascript">
    
        function uploadAdapterPlugin(editor){
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) =>{
                return new UploadAdapter(loader)
            }
        }
        
        $(function(){
        	
            var editor;
            
            ClassicEditor.create($("#content")[0],{
                extraPlugins:[uploadAdapterPlugin]
            })
            .then(editor => {
                console.log("에디터 초기화 완료",editor);
                myEditor = editor;
            }).catch(error => {
                console.log(error);
            });
            
            var plus = $(".file_plus_btn");
            var minus = $(".file_minus_btn");
            var count = 3;
            
            plus.click(function(){
                if(count == 5) return;
                count++;
                $(".write_box_file").append("<p><input type='file' name='file'></p>");
            });
            minus.click(function(){
                if(count == 1) return;
                    $(".write_box_file").children("p").last().remove();
                count--;
            });
        });
    </script>
    <style>
        section{
            width: 1000px;
            margin: 100px auto;
        }

        .write_box{
            margin: 50px auto;
            width: 1000px;
            display: flex;
            flex-direction: column;
        }
        .title_box {
            position: relative;
            border: 1px solid #b7b7b7;
            width: 100%;
            box-sizing: border-box;
            background-color: #d7d7d7;
            text-align: center;
            padding: 10px 0;
        }
        .title_bDate{
            color: #676767;
            padding: 10px 0;
            font-size: 14px;
        }

        .write_box_title{
            border: 1px solid #b7b7b7;
            padding: 10px;
            display: flex;
            flex-direction: row;
            width: 100%;
            box-sizing: border-box;
        }
        .write_box_title span{
           padding-top: 3px; 
        }

        .write_box_title input{
            border: 1px solid #b7b7b7;
            width: 94.5%;
            padding: 5px;
            margin-left: 10px;
            margin-top: 1px;
            border-radius: 3px;
        }


        .write_box_file {
            position: relative;
            width: 1000px;
            margin: 0 auto;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
            margin-top: 25px;
            background-color: #d7d7d7;
        }

        .write_box_file p {
            padding: 0 15px;
            padding-bottom: 12px;
        }

        .write_box_file button:first-of-type{  
            position: absolute;
            left: 110px;
            top: 10px;
            border: 1px solid black;
            background-color: white;
            width: 20px;
            border-radius: 3px;
            
        }
        .write_box_file button:last-of-type{
            position: absolute;
            top: 10px;
            left: 135px;
            border: 1px solid black;
            background-color: white;
            width: 20px;
            border-radius: 3px;
        }

        .write_box_file button:first-of-type:hover{
            background-color: #676767;
            color: white;
        }

        .write_box_file button:last-of-type:hover{
            background-color: #676767;
            color: white;
        }

        .write_box_file p:first-of-type {
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

        .ck-editor__editable_inline{
            min-height: 500px;
        }

        .write_box_btns{
            position: relative;
        }

        .write_box_img{
            display: inline-block;
            position: absolute;
            width: 15px;

            left: 21px;
            top: 12px;
        }

        .write_box_list {
            display: inline-block;
            position: absolute;
            left: 41px;
            padding-top: 11px;
        }

        .write_box_btns button{
            position: absolute;
            right: 0;
            width: 120px;
            border: 1px solid #b7b7b7;
            height: 40px;
            border-radius: 5px;
            background-color: #d7d7d7;
            margin-top: 5px;
        }
        
        .write_box_btns button:hover{
        	background-color: #676767;
            color: white;
        }
        
        .write_box_btns a{
            position: absolute;
            right: 130px;
            top: 5px;
            display: inline-block;
            width: 115px;
            border: 1px solid #b7b7b7;
            height: 38px;
            border-radius: 5px;
            background-color: #d7d7d7;
            text-decoration: none;
            color: black;
            font-size: 13px;
            text-align: center;
            
        }
    </style>
</head>
<body>

    <jsp:include page="template/header.jsp"></jsp:include>

    <section>
        <form class="write_box" action="boardWrite.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="writer" value="${sessionScope.id }">
            <div class="title_box">
                <span class="title_writer">작성자 : <b>${sessionScope.id}</b></span> |
                <span class="title_bDate"> date : ${sessionScope.date} </span>
            </div>
            <div class="write_box_title">
                <span>제목 </span>
                <input type="text" name="title">
            </div>
            <textarea name="content" id="content"></textarea>
            <div class="write_box_file">
                <img src="img/download.png" class="file_box_download"><span class="file_box_span">파일 목록</span>
                <button type="button" class="file_plus_btn">+</button>
                <button type="button" class="file_minus_btn">-</button>
                <p><input type="file" name="file"></p>
                <p><input type="file" name="file"></p>
                <p><input type="file" name="file"></p>
            </div>
            <div class="write_box_btns">
                <a href="main.do"><img src="img/list.png" class="write_box_img"> <span class="write_box_list">목록으로</span></a>
                <button>글쓰기</button>
            </div>
        </form>
    </section>
    
    <jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>