<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전체 성공</h2>
	<p>업로드 파일명 : ${filename }</p>
	<p>전체 레코드건수 : ${totallineCnt }건</p>
	<p>레코드건수 ${successCnt }건 입력 성공</p>

	<br />
	<table border="1">
		<thead>
			<tr>
				<td>ID</td>
				<td>PWD</td>
				<td>NAME</td>
				<td>LEVEL</td>
				<td>DESC</td>
				<td>REG_DATE</td>
			</tr>
		</thead>
		<tbody class="table_body">

		</tbody>
	</table>
	
	<input type="button" onclick="selectinfo()" value="조회" />
	
	<script src="https://code.jquery.com/jquery-3.6.9.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script><!-- 추가 -->
	<script>
		function selectinfo() {
			$.ajax({
				url:'<%=path%>/selectinfo', // RestController에서 받을 주소
				type : 'post',
				dataType : "json",
				async : false,// 동기식
				success : function(userinfo) {
					console.log(userinfo);

					var list = JSON.stringify(userinfo);
					
					str = '<TR>';
					
					$.each(userinfo, function(i){
						console.log(i);
						str += '<TD>' + userinfo[i].id + '</TD>'+
							'<TD>' + userinfo[i].pwd + '</TD>'+
							'<TD>' + userinfo[i].name + '</TD>'+
							'<TD>' + userinfo[i].level + '</TD>' +
							'<TD>' + userinfo[i].desc + '</TD>'+
							'<TD>' + userinfo[i].reg_date+ '</TD>';
						str += '</TR>';
					});
					
                  	$('.table_body').append(str);
				},
				error:function(){
					console.log("에러");
				}
			});
		}
	</script>


	<form action="deleteinfo">
		<input type="submit" value="전체삭제" />
	</form>
	<br />
	<button onclick="location.href='./uploadfile'">이전</button>
</body>
</html>