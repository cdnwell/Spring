<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function(){
		$.ajax({
			url:'all.do',
			dataType:'json',
			success : function(r){
				var tag = "";
				if(r.code == 200){
					var arr = r.result;
					for(i=0;i<arr.length;i++){
						tag += "<tr>";
                        tag += "<td>"+arr[i].sno+" </td>";
                        tag += "<td>"+arr[i].name+" </td>";
                        tag += "<td>"+arr[i].major+" </td>";
                        tag += "<td>"+arr[i].score+" </td>";
                        tag += "</tr>";
					}
				} else {
					tag = r.message;
				}
				$(".container").html(tag);
			}
		});
		
		$(".btn").click(function(){
			var d = $("#frm").serialize();
			$.ajax({
				url : "search.do",
				data : d,
				dataType : "json",
				success : function(r){
					var tag = "";
					if(r.code == 200){
						var arr = r.result;
						for(i=0;i<arr.length;i++){
							tag += "<tr>";
							tag += "<td>"+arr[i].sno+" </td>";
							tag += "<td>"+arr[i].name+" </td>";
							tag += "<td>"+arr[i].major+" </td>";
							tag += "<td>"+arr[i].score+" </td>";
							tag += "</tr>";
						}
					} else {
						tag = r.message;
					}
					$(".container").html(tag);
				} 
			});
		});
	});
</script>
<style type="text/css">
    #frm{
        text-align: center;
    }
    table{
        text-align: center;
    }
    td{
        padding: 0px 10px;
    }
    .container_box{
    	display: flex;
    	flex-direction : column;
    	align-items: center;
    }
</style>
</head>
<body>
	<form id="frm">
		<select name="kind">
			<option value="sno">학번</option>
			<option value="name">이름</option>
			<option value="major">전공</option>
		</select>
		<input type="text" name="search"><button type="button" class="btn">검색</button>
		<button type="reset">리셋</button>
	</form>
	<hr>
	<!-- 결과값 넣을 부분 -->
	<div class="container_box">
	<table class="container">
    </table>
    </div>
</body>
</html>