<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


	<script src="http://code.jquery.com/jquery-3.6.9.min.js"></script>

<body>
	<form action="upload" >
		<input type="file" id="file_text" name="file_text" accept=".txt"/><br />
		<input type="submit" id="uploadBtn" value="저장" />
	</form>
	
	
	<script>
	 // 파일 업로드 확장자 체크
    if( $("#file_text").val() != "" ){
         var ext = $('#docufile').val().split('.').pop().toLowerCase();
 	  if($.inArray(ext, ['txt']) == -1) {
 	     alert('등록 할수 없는 파일명입니다.');
 	     $("#file_text").val(""); // input file 파일명을 다시 지워준다.
 	     return;
	  }
     }
});
	</script>
	
</body>
</html>