<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../includes/header.jsp" %>


<body>
	<form action="register" method="post">
		<table border="1" class="table table-bordered">
			<tr><td>제목</td><td><input class="form-control" type="text" name="title"></td></tr>
			<tr><td>내용</td><td><textarea class="form-control" rows="20" cols="30" name="content"></textarea></td></tr> <!--textarea	는 넓이는 rows, 높이는 cols를 사용한 -->
			<tr><td>작성자</td><td><input class="form-control" type="text" name="writer"></td></tr>
			<tr><td colspan="2"><input type="submit" value="글쓰기"></td></tr>
		</table>
	</form>
</body>
</html>