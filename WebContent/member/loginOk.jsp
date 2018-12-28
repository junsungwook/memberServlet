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
	${sessionScope.id }님이 로그인 하셨습니다!
</div>
<table>
	<tr>
		<td>
			<input type="button" value="상세보기" onclick="location.href='view.do?id=${id}'">
		</td>
		<td>
			<input type="button" value="전체목록" onclick="location.href='list.do'">
		</td>
		<td>
			<input type="button" value="탈퇴" onclick="location.href='delete.do'">
		</td>
		<td>
			<input type="button" value="로그아웃" onclick="location.href='logout.do'">
		</td>
	</tr>
</table>

</body>
</html>