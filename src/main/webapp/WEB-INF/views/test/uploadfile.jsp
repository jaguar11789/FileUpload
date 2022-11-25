<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<script src="http://code.jquery.com/jquery-3.6.9.min.js"></script>

<body>

	<form action="textupload" enctype="multipart/form-data" method="post">
		<input type="file" name="file1" onchange="checkFile(this)"><br><br />
		<input type="submit" value="전송" />
		
	<script>
		function checkFile(f){
	
			var file = f.files;
	
			if(!/\.(dbfile)$/i.test(file[0].name)) alert('dbfile 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
	
			// 체크를 통과했다면 종료.
			else return;
			f.outerHTML = f.outerHTML;// 선택한 파일 초기화
		}
	</script>
	</form>
</body>
</html>