<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
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
<body>
<form action="login.dodo" method="post">
	ID : <input type="text" name="id" id="id"><br>
	PWD : <input type="password" name="password" id="password"><br>
	<input type="submit" value="로그인" onclick="return loginCheck()">
	<input type="reset" value="취소">
</form>
<br><br>
${msg }
</body>
</html>