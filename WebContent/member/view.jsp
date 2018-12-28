<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<div>
	<form action="update.do">
		<table>
			<tr>
				<td>
					비밀번호
				</td>
				<td>
					<input type="text" name="password" value="${member.password }">
				</td>
			</tr>
			<tr>
				<td>
					이름
				</td>
				<td>
					<input type="text" name="name" value="${member.name }">
				</td>
			</tr>
			<tr>
				<td>
					나이
				</td>
				<td>
					<input type="text" name="age" value="${member.age }">
				</td>
			</tr>
			<tr>
				<td>
					성별
				</td>
				<td>
					<input type="text" name="gender" value="${member.gender }">
				</td>
			</tr>
			<tr>
				<td>
					이메일
				</td>
				<td>
					<input type="text" name="email" value="${member.email }">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="정보수정">
					<input type="button" onclick="history.go(-1)" value="뒤로가기">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>