<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전체/일부 실패</h2>
	<p>업로드 파일명 : ${filename }</p>
	<p>성공 ${successCnt }건, 실패 ${failCnt}건</p>
	<br />
	<table class="table" border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>내용</td>
			</tr>
		</thead>
		<c:forEach items="${failLinelist}" var="failLinelist">
			<tr>
				<td>${failLinelist.lineNumber}</td>
				<td>${failLinelist.lineText}</td>
			</tr>
		</c:forEach>
	</table>
	<form action="deleteinfo">
		<input type="submit" value="전체삭제" />
	</form>
</body>
</html>