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

        .update_box{
            width: 1000px;
            margin: 50px auto;
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            
        }


        
        .update_id{
            width: 100%;
            border: 1px solid #b7b7b7;
            text-align: center;
            padding: 20px;
            background-color: #d7d7d7;
            box-sizing: border-box;
        }

        .update_id_span{
            color: #676767;
            font-size: 14px;
        }
        


        .update_content_box{
            display: flex;
            flex-direction: column;
        }

        .update_content_box label{
            display: inline-block;
        }
        
        .update_pass_box{
            width: 100%;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
        }

        .update_pass_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .update_pass_chk1{
            padding-left: 20px;
            padding-top: 5px;
        }

        .pass_chk1{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 68px;
        }
        
        .update_pass_chk2{
            padding-left: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        
        .pass_chk2{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 30px;
        }
        
        .pass_chk1_p{
            padding-top: 7px;
            font-size: 14px;
            color: red;
            padding-left: 165px;
        }
        .pass_chk2_p{
            padding-bottom: 11px;
            font-size: 14px;
            color: red;
            padding-left: 165px;
        }



        .update_name_box{
            border: 1px solid #b7b7b7;
        }

        .update_name_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .update_name_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 20px;
        }

        .update_name_box input{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 103px;
        }

        .update_name_chk{
            color: red;
            font-size: 14px;
            margin-left: 165px;
            padding-bottom: 11px;
        }

        


        .update_nick_box{
            border: 1px solid #b7b7b7;
        }

        .update_nick_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .update_nick_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 20px;
        }

        .update_nick_box input{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 85px;
        }
        
        .update_nick_chk{
            color: red;
            font-size: 14px;
            margin-left: 165px;
            padding-bottom: 11px;
        }



        .update_gradeNo_box{
            border: 1px solid #b7b7b7;
        }

        .update_gradeNo_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .update_gradeNo_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 30px;
        }

        .update_gradeNo_box select{
            margin-left: 62px;
            padding: 10px;
            font-size: 18px;
        }



        .update_btns_box{
            position: relative;
            border: 1px solid #b7b7b7;
            height: 50px;
            box-sizing: border-box;
            
        }
        
        .update_btns_reset{
            width: 50%;
            box-sizing: border-box;
            height: 100%;
            position: absolute;
            border: none;
            border-right: 1px solid #b7b7b7;
        }

        .update_btns_send{
            position: absolute;
            right: 0;
            border: none;
            height: 100%;
            width: 50%;
            box-sizing: border-box;

        }

        .update_btns_send:hover,
        .update_btns_reset:hover{
            background-color: #676767;
            color: white;
        }

        /*
            유효성 검사 p태그 > .chk_p
        */
        .chk_p{
            display: none;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function pass_chk(){
            const regex = /^[a-zA-Z0-9]*$/;
            var pass1 = $('#pass1').val();
            var pass2 = $('#pass2').val();
            var pass_p =$('.pass_chk2_p');

            pass_p.css('color','red');
            pass_p.css('display','block');
            if(!regex.test(pass1) || !regex.test(pass2)){
                pass_p.html('패스워드는 영어 혹은 숫자로 이루어져야만 합니다.');
                return false;
            }

            if((pass1.length < 6 || pass1.length > 20) || (pass2.length < 6 || pass2.length > 20) ){
                pass_p.html('패스워드는 6-20자를 입력해주세요.');
                return false;
            }
            
            if(pass1 != pass2){
                pass_p.html('패스워드가 일치하지 않습니다.')
                return false;
            }

            pass_p.css('color','blue');
            pass_p.html('패스워드가 유효합니다.');
            return true;
        }
        function name_chk(){
            const regex = /^[가-힣]*$/
            var name = $('.name_chk').val();
            var name_p = $('.update_name_chk');

            name_p.css('color','red');
            name_p.css('display','block');
            if(!regex.test(name)){
                name_p.html('이름은 한글만 허용됩니다.');
                return false;
            }

            if(name.length>8 || name.length < 1){
                name_p.html('이름은 1-8글자 까지만 허용됩니다.')
                return false;
            }

            name_p.css('color','blue');
            name_p.html('이름이 유효합니다.');
            return true;
        }
        function nick_chk(){
            const regex = /^[a-zA-Z0-9가-힣]*$/;
            var nick = $('.nick_chk').val();
            var nick_p = $('.update_nick_chk');

            nick_p.css('color','red');
            nick_p.css('display','block');
            if(!regex.test(nick)){
                nick_p.html('닉네임은 한글 혹은 영어 숫자로 이루어져야 합니다.');
                return false;
            }

            if(nick.length>10 || nick.length < 2){
                nick_p.html('닉네임은 2-10 글자가 가능합니다.');
                return false;
            }

            nick_p.css('color','blue');
            nick_p.html('허용 가능한 닉네임입니다.');
            return true;

        }
        $(function(){
            $(".pass_chk1").keyup(pass_chk);
            $(".pass_chk2").keyup(pass_chk);
            $(".name_chk").keyup(name_chk);
            $(".nick_chk").keyup(nick_chk);

            $('.update_content_box').submit(function(r){
                if(!pass_chk()){
                    r.preventDefault();
                }else if(!name_chk()){
                    r.preventDefault();
                }else if(!nick_chk()){
                    r.preventDefault();
                }
            });
            
            $('.register_id_chk_btn').click(function(){
                var d = 'id='+$('.id_chk').val();
                console.log(d);
                $.ajax({
                    url : 'idCheck.do',
                    data : d,
                    success : function(r){
                        if(r==1){
                            id_chk_flag = true;
                            id_chk();
                        } else {
                            id_chk_flag = false;
                        }
                    }
                });
            });
            
        });
    </script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>

    <section>
        <div class="update_box">
            <div class="update_id">
                <b>${requestScope.member.id }</b><span class="update_id_span">님 정보 수정 </span>
            </div>
            <form class="update_content_box" action="update.do" method="post">
            	<input type="hidden" name="id" value="${requestScope.member.id }">
                <div class="update_pass_box">
                    <p class="update_pass_title">비밀번호 변경</p>
                    <p class="update_pass_chk1"><label for="pass1">비밀번호</label> <input type="password" name="passwd" class="pass_chk1" id="pass1"></p>
                    <p class="update_pass_chk2"><label for="pass2">비밀번호 확인</label> <input type="password" class="pass_chk2" id="pass2"></p>
                    <p class="pass_chk2_p chk_p"></p>
                </div>
                <div class="update_name_box">
                    <p class="update_name_title">이름 변경</p>
                    <span>이름 </span><input type="text" value="${requestScope.member.name }" name="name" class="name_chk">
                    <p class="update_name_chk chk_p"></p>
                </div>
                <div class="update_nick_box">
                    <p class="update_nick_title">닉네임 변경</p>
                    <span>닉네임 </span><input type="text" value="${requestScope.member.nick }" name="nick" class="nick_chk">
                    <p class="update_nick_chk chk_p"></p>
                </div>
                <div class="update_gradeNo_box">
                    <p class="update_gradeNo_title">회원등급 변경</p>
                    <span>회원등급 </span>
                    <select name="gradeNo">
                        <option value="1"
                        	<c:if test="${requestScope.member.gradeNo == 1 }">
                        		selected
                        	</c:if>
                        >새싹회원</option>
                        <option value="2"
                        	<c:if test="${requestScope.member.gradeNo == 2 }">
                        		selected
                        	</c:if>
                        >일반회원</option>
                        <option value="3"
                        	<c:if test="${requestScope.member.gradeNo == 3 }">
                        		selected
                        	</c:if>
                        >성실회원</option>
                        <option value="4"
                        	<c:if test="${requestScope.member.gradeNo == 4 }">
                        		selected
                        	</c:if>
                        >열심회원</option>
                        <option value="5"
                        	<c:if test="${requestScope.member.gradeNo == 5 }">
                        		selected
                        	</c:if>
                        >우수회원</option>
                        <option value="6"
                        	<c:if test="${requestScope.member.gradeNo == 6 }">
                        		selected
                        	</c:if>
                        >특별회원</option>
                        <option value="7"
                        	<c:if test="${requestScope.member.gradeNo == 7 }">
                        		selected
                        	</c:if>
                        >운영자</option>
                    </select>
                </div>
                <div class="update_btns_box">
                    <button type="reset" class="update_btns_reset">리셋</button>
                    <button class="update_btns_send">수정하기</button>
                </div>
            </form>
        </div>
    </section>
	
	<jsp:include page="template/footer.jsp"></jsp:include>    
</body>
</html>