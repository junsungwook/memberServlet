<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>아이디</th>
					<th>패스워드</th>
					<th>이름</th>
					<th>나이</th>
					<th>성별</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${arr}" var="list">
					<tr>	
						<td><a href="view.do?id=${list.id}">${list.id }</a></td>
						<td>${list.password }</td>
						<td>${list.name }</td>
						<td>${list.age }</td>
						<td>${list.gender }</td>
						<td>${list.email }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" onclick="history.go(-1)" value="뒤로가기">
		<input type="button" value="로그아웃" onclick="location.href='logout.do'">
	</div>	
</body>
</html>