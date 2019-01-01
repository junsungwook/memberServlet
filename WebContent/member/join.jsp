<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	//비밀번호 유효성 검사인데 키 누를때 마다 반응 되도록
	$("#password").keyup(function(){
	     $("#password2").val("");
	     $("div").remove();
	     return false;
	 });
	 $("#password2").keyup(function() {
	     if ($("#password").val() != "") {
	         if ($("#password").val() != $("#password2").val()) {
	             $("div").remove();
	             //span안에 div를 없애고 새로 만드는 식으로 한다 
	             $("span").append("<div style='color:red'>비밀번호 틀림</div>");
	             return false;
	         } else {
	             $("div").remove();
	                $("span").append("<div style='color:green'>비밀번호 맞음</div>");
	          }
	     }
	 });
	$("#btn").click(function(){
		if($("#name").val()==""){
			alert("이름 입력");
			return false;
		}
		if($("#userid").val()==""){
			alert("아이디 입력");
			return false;
		}
		if($("#password").val()==""){
			alert("비밀번호 입력");
			return false;
		}
		if($("#email").val()==""){
			alert("메일 입력");
			return false;
		}
		if($("#password").val()==$("#password2").val()){
			$("#frm").submit();
		}
		else if($("#password").val()!=$("#password2").val()){
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
		$("#frm").submit();	
	});
});
//아이디 중복검사
function idCheck(){
	window.open("check.do","confirm","width=500 height=200");
}
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="join.do" id="frm" method="post">
	<table>
		<input type="hidden" name="id" id="id2">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name">*</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" id="id" disabled="disabled">*</td>
			<td><input type="button" value="중복 체크" onclick="window.open('idCheck.jsp','confirm','width=500 height=200')"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="password" id="password">*</td>
		</tr>
		<tr>
			<td>암호확인</td>
			<td><input type="password" id="password2">*<span></span></td>
			
		</tr>
		<tr>
			<td>나이</td>
			<td><input type="text" name="age" id="age"></td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" name="gender" value="male">남자
	        	<input type="radio" name="gender" value="female">여자
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" id="email"></td>
		</tr>
	</table>
		<input type="button" value="확인" id="btn">
		<input type="reset" value="취소">
		<input type="button" value="댓글창" onclick="location.href='../guestbook/guestInsert.jsp'">
	</form>
</body>
</html>