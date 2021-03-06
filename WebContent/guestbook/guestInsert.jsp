<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		var name = $("#name").val();
		var content = $("#content").val();
		var grade = $("input:radio[name=grade]:checked").val();
		var postString = "name="+name+"&content="+content+"&grade="+grade;
		$.ajax({
			type:"post",
			url:"create",
			data:postString,
			success:function(data){
				$("#results").html(data);
			},
			beforeSend : showRequest
		});
	});
	$("#btnSearch").click(function(){
		getSearch(1,$("#field").val(),$("#word").val());
	});
	getData(1);
})
function getSearch(pageNum,field,word){
	$("#results").load("search.dodo",{"pageNum":pageNum,"field":field,"word":word},function(data){
		$("#results").html(data);
	});
}
function getData(pageNum){
	$("#results").load("list",{"pageNum":pageNum},function(data){
		$("#results").html(data);
	})
}
function showRequest(){
    if($("#name").val()==""){
       alert("글쓴이를 입력하세요");
       $("#name").focus();
       return false;
    }
    if($("#content").val()==""){
       alert("내용을 입력하세요");
       $("#content").focus();
       return false;
    }
    return true;
 }
 //글자수 구하기
function textCount(obj,target){
	var len = obj.value.length; //입력한 글자수
	if(obj.size<len){
		alert("글자 수 초과");
		return;
	}
	$("#"+target).text(len); //target영역에 출력하면서  keyup이기 때문에 누를때마다 함수가 실행된다.
}
//상세보기
function fview(num){
	$.get("view",{"num":num},function(data){
		$("#view").html(data);
	});
}
</script>
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<div class="container">
<div align="right">
	<c:if test="${sessionScope.mdto==null }">
		<a href="login.jsp">로그인</a>&nbsp;&nbsp;
		<a href="../member/join.jsp">회원가입</a>
	</c:if>
	<c:if test="${sessionScope.mdto!=null }">
		${sessionScope.mdto.name}님이 로그인 하셨습니다
		<a href="logout.dodo">로그아웃</a>&nbsp;&nbsp;
	</c:if>
	</div>
	<form action="create">
		<table class="table">
			<tr>
				<td align="center">글쓴이</td>
				<td>
					<input type="text" id="name" size="20" name="name" disabled="disabled" onkeyup="textCount(this,'nameCount')" value="${sessionScope.mdto.name }">
						*20글자 이내
						(<span id="nameCount" style="color:blue;">0</span>)
					<input type="hidden" id="name2" size="20" name="name" value="${sessionScope.mdto.name }">
				</td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td>
					<input type="text" id="content" size="70" name="content" onkeyup="textCount(this,'contentCount')">
						*70글자 이내
						(<span id="contentCount" style="color:blue;">0</span>)
				</td>
			</tr>
			<tr>
				<td align="center">평가</td>
				<td>
					<input type="radio" name="grade" value="excellent" checked="checked">아주잘함(excellent)
					<input type="radio" name="grade" value="good">잘함(goood)
					<input type="radio" name="grade" value="normal">보통(normal)
					<input type="radio" name="grade" value="fail">노력(fail)
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="입력" id="send" class="btn btn-success">
				</td>
			</tr>
		</table>
	</form>
	<div align="right">
		<form name="search" id="search">
			<select name="field" id="field">
				<option value="name">이름</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="word" id="word">
			<input type="button" value="찾기" id="btnSearch">
		</form>
	</div>
	<br><br><br><br>
	<div id="results" align="center"></div>
	<div id="view" align="center"></div>
</div>
</body>
</html>