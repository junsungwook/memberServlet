<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String pagefile=request.getParameter("page");
    if(pagefile==null){
    	pagefile="newitem";
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">   
<html>
<head>
<style>
	table{
		margin : auto;
		width : 960px;
		color : gray;
		border : 1px solid gray;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index page</title>
</head>
<body>
<div class="container">	
	<table class="table table-striped">
		<tr>
			<td height="43" colspan="3" align="left">
				<jsp:include page="top.jsp"/>
			</td>
		</tr>
		<tr>
			<td width="15%" align="right" valign="top"><br>
				<jsp:include page="left.jsp"/>
			</td>
			<td colspan="2" align="center">
				<jsp:include page='<%=pagefile+".jsp" %>'/>
			</td>
		</tr>
		<tr>
			<td width="100%" width="40" colspan="3">
				<jsp:include page="bottom.jsp"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>