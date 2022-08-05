<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.result {
		margin:50px auto;
		width:1200px;
	}
	.result > table{
		width:100%;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function add_event_update(){
		$(".btnUpdate").click(function(){
			var arr = $(this).parent().parent().find("input");
			var d = "";
			$.each(arr,function(i,obj){
				d += $(obj).attr("name") + "=" + $(obj).val() + "&";
			});
			$.ajax({
				url:"memberUpdate.do",
				data : d,
				type: "post",
				success:function(r){
					if(r == 1)
						alert("회원 정보 수정 완료");
					else 
						alert("회원 정보 수정 실패");
					location.reload();
				}
			});
		});
	}
	
	function add_event_delete(){
		$(".btnDelete").click(function(){
			var d = "id="+$(this).parent().parent().find("input[name=id]").val();
			$.ajax({
				url:"memberDelete.do",
				data : d,
				type: "post",
				success:function(r){
					if(r == 1)
						alert("회원 정보 삭제 완료");
					else 
						alert("회원 정보 삭제 실패");
					//reload
					location.reload();
				}
			});
		});
	}
	

	$(function(){
		add_event_update();
		add_event_delete();
		$(".btn_search").click(function(){
			var d = $(this).parent().serialize();
			$.ajax({
				url : "memberSearch.do",
				data : d,
				dataType : "json",
				success : function(r){
					var tag = "";
					for(i=0;i<r.length;i++){
						tag += '<tr>';
						tag += '<td><input type="text" name="id" value="'+r[i].id+'" readonly></td>';
						tag += '<td><input type="text" name="passwd" value="'+r[i].passwd+'"></td>';
						tag += '<td><input type="text" name="name" value="'+r[i].name+'"></td>';
						tag += '<td><input type="text" name="nick" value="'+r[i].nick+'"></td>';
						tag += '<td><input type="text" name="gradeNo" value="'+r[i].gradeNo+'"></td>';
						tag += '<td><button type="button" class="btnUpdate">수정</button>';
						tag += '<button type="button" class="btnDelete">삭제</button>';
						//페이지 로드할떄 할일 넣어주고, 그다음에 추가된 엘리먼트는 할일이 안들어감. 동적엘리먼트(나중에 추가된 엘리먼트) 나중에 들어간건
						//기능이 안들어가있다. 태그가 들어가면 나중에 더 추가해줘야함. 버튼을 하이퍼링크로 바꾸면 동적엘리먼트 걱정안해도됨, button으로 ajax처리 할 필요가 없다.
						//애초에 링크로 처리하지 않기 때문에 그렇게는 하기 힘듦
						tag += '</td>';
						tag += '</tr>';
					}
					$(".search_result").html(tag);
					add_event_update();
					add_event_delete(); 
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="template/header.jsp" flush="false"></jsp:include>
	<div class="result">
	<form id="frm_search">
		<select name="kind">
			<option value="id">아이디</option>
			<option value="name">이름</option>
			<option value="grade">등급</option>
		</select>
		<input type="text" name="search">
		<!-- search의 서브밋 기능은 제거하기 -->
		<button type="button" class="btn_search">검색</button>
		<!-- submit 이벤트랑 onkeyup 이벤트로 처리 가능 -->
	</form>
	<table>
	<!-- 내용이 구분 안 될때는 thead 넣어주기 -->
	<thead>
	<tr>
		<th>아이디</th>
		<th>암호</th>
		<th>이름</th>
		<th>닉네임</th>
		<th>등급</th>
		<th>비고</th>
	</tr>
	</thead>
	<!-- 전체 회원 목록을 출력 -->
	<tbody class="search_result">
	<c:forEach var="obj" items="${requestScope.list }">
		<tr>
		<td><input type="text" name="id" value="${obj.id }" readonly></td>
		<td><input type="text" name="passwd" value="${obj.passwd }"></td>
		<td><input type="text" name="name" value="${obj.name }"></td>
		<td><input type="text" name="nick" value="${obj.nick }"></td>
		<td><input type="text" name="gradeNo" value="${obj.gradeNo }"></td>
		<td><button type="button" class="btnUpdate">수정</button>
		<button type="button" class="btnDelete">삭제</button>
		<!-- 삭제 및 수정 처리 Ajax로 삭제 처리 -->
		</td>
		</tr>
		
	</c:forEach>
	</tbody>
	</table>
	</div>
	<jsp:include page="template/footer.jsp" flush="false"></jsp:include>
</body>
</html>