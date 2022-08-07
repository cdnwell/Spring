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

        .register_box{
            width: 1000px;
            margin: 50px auto;
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            
        }


        
        .register_id_box{
            width: 100%;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
        }

        .register_id_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .register_id_input{
            padding-left: 20px;
            padding-top: 5px;
        }

        .register_id_chk_btn{
            background-color: #d7d7d7;
            border: 1px solid #b7b7b7;
            outline: none;
            border-radius: 3px;
            width: 130px;
            height: 39px;
        }

        .register_id_chk_btn:hover{
            background-color: #676767;
            color: white;
        }
        
        .id_chk{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 84px;
            margin-bottom: 11px;
        }

        .id_chk_p{
            padding-bottom: 11px;
            font-size: 14px;
            padding-left: 165px;
        }

        .register_content_box{
            display: flex;
            flex-direction: column;
        }

        .register_content_box label{
            display: inline-block;
        }
        
        .register_pass_box{
            width: 100%;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
        }

        .register_pass_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .register_pass_chk1{
            padding-left: 20px;
            padding-top: 5px;
        }

        .pass_chk1{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 68px;
        }
        
        .register_pass_chk2{
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

        .register_name_box{
            border: 1px solid #b7b7b7;
        }

        .register_name_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .register_name_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 20px;
        }

        .register_name_box input{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 103px;
        }

        .register_name_chk{
            color: red;
            font-size: 14px;
            margin-left: 165px;
            padding-bottom: 11px;
        }

        


        .register_nick_box{
            border: 1px solid #b7b7b7;
        }

        .register_nick_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .register_nick_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 20px;
        }

        .register_nick_box input{
            padding: 8px;
            font-size: 18px;
            width: 400px;
            margin-left: 85px;
        }
        
        .register_nick_chk{
            color: red;
            font-size: 14px;
            margin-left: 165px;
            padding-bottom: 11px;
        }



        .register_gradeNo_box{
            border: 1px solid #b7b7b7;
        }

        .register_gradeNo_title{
            font-size: 19px;
            font-weight: bold;
            padding: 10px;
        }

        .register_gradeNo_box span{
            display: inline-block;
            padding-left: 20px;
            padding-top: 5px;
            padding-bottom: 30px;
        }

        .register_gradeNo_box select{
            margin-left: 62px;
            padding: 10px;
            font-size: 18px;
        }



        .register_btns_box{
            position: relative;
            border: 1px solid #b7b7b7;
            height: 50px;
            box-sizing: border-box;
            
        }
        
        .register_btns_reset{
            width: 50%;
            box-sizing: border-box;
            height: 100%;
            position: absolute;
            border: none;
            border-right: 1px solid #b7b7b7;
        }

        .register_btns_send{
            position: absolute;
            right: 0;
            border: none;
            height: 100%;
            width: 50%;
            box-sizing: border-box;

        }

        .register_btns_send:hover,
        .register_btns_reset:hover{
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
        var id_chk_flag = false;

        function id_chk(){
            const regex = /^[a-zA-Z0-9]*$/;
            var id = $('#id1').val();
            var id_p = $('.id_chk_p');

            id_p.css('color','red');
            id_p.css('display','block');
            
            if(!regex.test(id)){
                id_p.html('아이디는 영어 혹은 숫자로 이루어져야만 합니다.');
                id_chk_flag = false;
                return false;
            }

            if(id.length < 4 || id.length > 8){
                id_p.html('아이디는 4-8자로 입력해주세요');
                id_chk_flag = false;
                return false;
            }

            if(!id_chk_flag){
                id_p.html('아이디 중복 확인을 해주세요');
                id_chk_flag = false;
                return false;
            }

            id_p.css('color','blue');
            id_p.html('아이디가 유효합니다.');
            return true;
        }
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
            var name_p = $('.register_name_chk');

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
            var nick_p = $('.register_nick_chk');

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
            $(".id_chk").keyup(id_chk);

            $('.register_content_box').submit(function(r){
                if(!id_chk()){
                    r.preventDefault();
                }else if(!pass_chk()){
                    r.preventDefault();
                }else if(!name_chk()){
                    r.preventDefault();
                }else if(!nick_chk()){
                    r.preventDefault();
                }
            });
            
            $('.register_id_chk_btn').click(function(){
                var d = 'id='+$('.id_chk').val();
                $.ajax({
                    url : 'idCheck.do',
                    data : d,
                    success : function(r){
                        if(r==1){
                            id_chk_flag = true;
                            id_chk();
                        } else {
                            id_chk_flag = false;
                            var id_p = $('.id_chk_p');
                            id_p.css('color','red');
                            id_p.html('아이디가 중복됩니다.');
                        }
                    }
                });
            })

        });
    </script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
    <section>
        <div class="register_box">
            <form class="register_content_box" action="register.do" method="post">
                <div class="register_id_box">
                    <p class="register_id_title">아이디 등록</p>
                    <p class="register_id_input"><label for="id1">아이디</label> <input type="text" name="id" class="id_chk" id="id1">
                    <button class="register_id_chk_btn" type="button">아이디 중복확인</button>
                    </p>
                    <p class="id_chk_p chk_p"></p>
                </div>
                <div class="register_pass_box">
                    <p class="register_pass_title">비밀번호 등록</p>
                    <p class="register_pass_chk1"><label for="pass1">비밀번호</label> <input type="password" name="passwd" class="pass_chk1" id="pass1"></p>
                    <p class="register_pass_chk2"><label for="pass2">비밀번호 확인</label> <input type="password" class="pass_chk2" id="pass2"></p>
                    <p class="pass_chk2_p chk_p"></p>
                </div>
                <div class="register_name_box">
                    <p class="register_name_title">이름 등록</p>
                    <span>이름 </span><input type="text" name="name" class="name_chk">
                    <p class="register_name_chk chk_p"></p>
                </div>
                <div class="register_nick_box">
                    <p class="register_nick_title">닉네임 등록</p>
                    <span>닉네임 </span><input type="text" name="nick" class="nick_chk">
                    <p class="register_nick_chk chk_p"></p>
                </div>
                <div class="register_gradeNo_box">
                    <p class="register_gradeNo_title">회원등급 등록</p>
                    <span>회원등급 </span>
                    <select name="gradeNo">
                        <option value="1">새싹회원</option>
                        <option value="2">일반회원</option>
                        <option value="3">성실회원</option>
                        <option value="4">열심회원</option>
                        <option value="5">우수회원</option>
                        <option value="6">특별회원</option>
                        <option value="7">운영자</option>
                    </select>
                </div>
                <div class="register_btns_box">
                    <button type="reset" class="register_btns_reset">리셋</button>
                    <button class="register_btns_send">등록하기</button>
                </div>
            </form>
        </div>
    </section>
    <jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>