<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<th></th>
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
						<td>
							<c:if test="${sessionScope.mdto.name==list.name}">
								<input type="button" value="삭제하기" onclick="location.href='delete.dodo?num=${list.num}'">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
		<!-- 이전 -->
		<c:if test="${startpage>blockpage }">
			<a href="javascript:getSearch(${startpage-blockpage },'${field }','${word }')">[이전]</a>
		</c:if>
		<!-- 페이지출력 -->
		<c:forEach begin="${startpage }" end="${endpage }" var="i">
			<c:if test="${currentPage eq i}" >
				${i }
			</c:if>
			<c:if test="${currentPage ne i}" >
				<a href="javascript:getSearch(${i },'${field }','${word }')">${i }</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endpage<totpage }">
			<a href="javascript:getSearch(${endpage+1 },'${field }','${word }')">[다음]</a>
		</c:if>
		</div>
	</div>	
</body>