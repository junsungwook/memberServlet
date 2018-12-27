<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function loginCheck(){
	if($("#userid").val()==""){
		alert("아이디 입력 하세요");
		return false;
	}
	if($("#pwd").val()==""){
		alert("비번 입력 하세요");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<form action="login.do" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" id="id"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="password" id="password"></td>
		</tr>
		<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인" onclick="return loginCheck()">&nbsp;&nbsp;
			<input type="reset" value="취소">&nbsp;&nbsp;
			<input type="button" value="회원 가입" onclick="location.href='join.jsp'">
		</td>
		</tr>
	</table>
</form>
</body>
</html>