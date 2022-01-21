<%@page import="vo.WelcomeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn {
	display: inline-block;
	padding: 0.25rem 0.75rem;
	text-decoration: none;
	border-radius: 4px;
	background-color: #e60808;
	border: 1px solid #eb0909;
	color: #fff;
	transition: all 0.5s;
}

.btn:hover {
	background-color: #eb1313;
}

.list {
	list-style: none;
}

.list>li {
	margin-bottom: 5px;
}

* {
	margin: 0;
	padding: 0;
}

.container {
	width: 800px;
	margin: 0 auto;
}

.title {
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="title">관리자 페이지입니다.</h1>
		<form method="post" action="/admin">
			<input type="number" name="id" value="${maxId}" readonly /><br>
			<input type="text" name="msg" placeholder="명언을 입력하세요" /><br>
			<button type="submit">등록</button>
		</form>
		<h4 class="title">등록된 리스트</h4>
		<ul class="list">
			<%
			List<WelcomeVO> list = (List<WelcomeVO>) request.getAttribute("list");
			for (WelcomeVO vo : list) {
				out.write("<li>" + vo.msg + " <a class='btn' href='/admin/delete?id=" + vo.id + "'>삭제</a></li>");
			}
			%>
		</ul>
	</div>
</body>
</html>