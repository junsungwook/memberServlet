<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
	<div class="container">
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
			<tbody>
				<tr>	
					<td>${guest.num }</td>
					<td>${guest.name }</td>
					<td>${guest.content }</td>
					<td>${guest.grade }</td>
					<td>${guest.created }</td>
					<td>${guest.ipaddr }</td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
