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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function(){
            $.ajax({
                url : 'selectAll.do',
                dataType : 'json',
                success : function(r){
                    var tag = '';
                    for(i=0;i<r.length;i++){
                        tag += '<tr>';
                        tag += '<td class="delete_id">'+r[i].id+'</td>';
                        tag += '<td>'+r[i].name+'</td>';
                        tag += '<td>'+r[i].age+'</td>';
                        tag += '<td>'+r[i].grade+'</td>';
                        tag += "<td><button class='delete_btn' type='button'>삭제</button></td>";
                        tag += '</tr>';
                    }
                    $('tbody').html(tag);
                }
            });
	
            $(document).on("click", ".delete_btn", function(){
            	var d = 'id='+$(this).parent().parent().children('td:first-child').text();
            	$.ajax({
            		url : 'deleteMember.do',
            		data : d,
            		dataType : 'json',
            		success : function(r){
            			alert(r.message);
            			location.href='/';
            		}
            	});
            });
            $('.register_btn').click(function(){
                var d = $('#frm').serialize();
                $.ajax({
                    url : 'registerMember.do',
                    data : d,
                    dataType : 'json',
                    success : function(r){
                        if(r==0){
                            alert('회원 등록에 실패하였습니다.');
                        } else {
                            alert('회원 등록하였습니다.');
                        }
                        location.href='/';
                    }
                });
            });
            
        });
            
    </script>
    <style>
        form{
            text-align: center;
        }
        form input{
            text-align: center;
        }
        table{
            width: 1200px;
            margin: 0 auto;
        }
        td{
            width: calc( 100% / 5 );
        }
    </style>
</head>
<body>
    <form id="frm">
        <input type="text" name="id" placeholder="아이디">
        <input type="text" name="pass" placeholder="암호">
        <input type="text" name="name" placeholder="이름">
        <input type="text" name="age" placeholder="나이">
        <select name="grade">
            <option value="1">bronze</option>
            <option value="2">silver</option>
            <option value="3">gold</option>
            <option value="4">platinum</option>
        </select>
        <button type="button" class="register_btn">회원등록</button>
    </form>
    <hr>
    <table>
        <tbody>
        </tbody>
    </table>
</body>
</html>