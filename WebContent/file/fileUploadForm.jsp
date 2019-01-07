<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	#uploadFormArea{
		margin:auto;
		width:350px;
		border:1px solid black;
	}
	.td_title{
		font-size:xx-large;
		text-align:center;
	}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<section id="uploadFormArea">
<form action="fileUpload.jsp" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td colspan="2" class="td_title">파일 업로드 폼</td>
		</tr>
		<tr>
			<td><label for="name">올린사람 : </label></td><td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td><label for="subject">제목 : </label></td><td><input type="text" name="subject" id="subject"></td>
		</tr>
		<tr>
			<td><label for="filename1">파일명1 : </label></td><td><input type="file" name="filename1" id="filename1"></td>
		</tr>
		<tr>
			<td><label for="filename2">파일명2 : </label></td><td><input type="file" name="filename2" id="filename2"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="전송"></td>
		</tr>
	</table>
</form>
</section>
</body>
</html>