<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<c:if test="${count gt 0 }">
	<body>
		<div class="container">
			<div align="center" id="count">
				총 게시물 수 : ${count }
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>NO</th>
						<th>이름</th>
						<th>내용</th>
						<th>등급</th>
						<th>작성시간</th>
						<th>ip</th>
					</tr>
				</thead>
				<tbody id="result">
					<c:forEach items="${lists}" var="list">
						<tr>	
							<td>${list.num }</td>
							<td><a href="javascript:fview(${list.num })">${list.name }</a></td>
							<td>${list.content }</td>
							<td>${list.grade }</td>
							<td>${list.created }</td>
							<td>${list.ipaddr }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</body>
</c:if>