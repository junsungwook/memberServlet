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
	$("#send").click(function(){
		if($("#id").val()==""){
			alert("아이디를 입력하세요");
			return false;
		}
		$.ajax({
			type:"get",
			url:"check.do",
			data:{"id":$("#id").val()},
			success:function(data){
				if(data.trim()=="ok"){
					alert("중복됐다");
				}
				else if(data.trim()=="no"){
					$(opener.document).find("#id").val($("#id").val());
					$(opener.document).find("#id2").val($("#id").val());
					self.close();
				}
			},
			error:function(e){
				alert("에러 : "+e)
			}
		});
	});
});
</script>
</head>
<body>                                                                                         
id입력 <input type="text" name="id" id="id">
<input type="button" id="send" value="중복확인">
</body>
</html>