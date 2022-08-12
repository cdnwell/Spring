<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

nav, section {
	width: 1200px;
	margin: 0 auto;
}

nav * {
	margin: 20px 0px;
}

table {
	margin: 0 auto;
	margin-top: 20px;
}

section p {
	text-align: center;
}
form {
	text-align: center;
	margin-bottom:10px;
}
td, th {
	padding: 10px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function() {
		$("button").click(function() {
			var param = $("#frm").serialize();
			$.ajax({
				url : "search.do",
				type : "get",
				data : param,
				dataType:"json",
				//정상처리부분
				success:function(d){
					console.log(d);
					var arr = d;
					var text = "";
					for(i=0;i<arr.length;i++){
						text += "<tr><td>"+arr[i].eno+ "</td><td>" + arr[i].name+ "</td><td>" + arr[i].department+ "</td><td>" + arr[i].position+ "</td></tr>"; 
					}
					$("#result").html(text)
				},
				//exception 처리
				error:function(xhr,text,error){
					console.log(xhr.status + " " + xhr.responseText);
					alert(xhr.responseText);
				}
			});
		});
	});
</script>
</head>
<body>
	<h2 style="padding:30px 0px;text-align: center;">사원정보 검색 시스템</h2>
	<section>
		<form id="frm">
				<select name="kind">
					<option value="eno">사번</option>
					<option value="name">이름</option>
					<option value="position">직급</option>
				</select>
				<input type="text" name="search">
			<button type="button">검색</button>
		</form>
		<hr>
		<table id="result">
			

		</table>

	</section>

</body>
</html>