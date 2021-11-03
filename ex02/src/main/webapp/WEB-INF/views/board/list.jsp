<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!--  jstl -->

<%@ include file="../includes/header.jsp" %>

    <table border="1" class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		<tr>
			<th>No.</th><th>제목</th><th>작성자</th><th>작성일</th>
		</tr>
		
		<!-- 				변수명			배열명 -->
		<c:forEach var="boardlist" items="${list}">
			<tr>
				<td>${boardlist.bno}</td>
				<td><a href="/board/read?bno=${boardlist.bno}">${boardlist.title}</a>[${boardlist.replycnt}]</td>
				<td>${boardlist.writer}</td>
				<td>${boardlist.regDate}</td>
			</tr>
		</c:forEach>

	</table>
  	<div class="row">
  		<div class="col-lg-12">
  			<form id="searchForm" action="">
  			
  			<!-- 
  				url순서에 맞게 값들도 넣어져야 오류가 안생긴다.  
  				
  				http://localhost:8080/board/list?pageNum=1&amount=10&type=T&keyword=bno	
  													1		2		   3		4
  			-->
  				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 	<!-- 1 -->
  				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">		<!-- 2 -->
 
  				<select name="type">													<!-- 3 -->
  				<!-- 
  					name의 이름과 VO,DTO에 만든 모델의 변수이름을 같게 해야 한다. 
  					이름이 같아야 데이터가 그 객체에 저장된다.
  				-->
  					<option value="" <c:out value="${pageMaker.cri.type==null?'selected':''}"/>>--</option>
  					<option value="T"<c:out value="${pageMaker.cri.type=='T'?'selected':''}"/>>제목</option>
  					<option value="C"<c:out value="${pageMaker.cri.type=='C'?'selected':''}"/>>내용</option>
  					<option value="W"<c:out value="${pageMaker.cri.type=='W'?'selected':''}"/>>작성자</option>
  					<option value="TC"<c:out value="${pageMaker.cri.type=='TC'?'selected':''}"/>>제목+내용</option>
  					<option value="TW"<c:out value="${pageMaker.cri.type=='TW'?'selected':''}"/>>제목+작성자</option>
  					<option value="TWC"<c:out value="${pageMaker.cri.type=='TWC'?'selected':''}"/>>제목+작성자+내용</option>
  				</select>
  			
  				<input type="text" name="keyword" value="${pageMaker.cri.keyword}">		<!-- 4 -->
  				<button class="btn btn-primary">검색</button>
  			</form>
  		</div>
  	</div>

	
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
             <li><a href="/board/list?pageNum=${pageMaker.startPage-1}&amount=${pageMaker.cri.amount}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">Previous</a></li>
       	   </c:if>
           <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                                        
             <li class="paginate_button page-item ${pageMaker.cri.pageNum==num?'active':''}"><a href="/board/list?pageNum=${num}&amount=${pageMaker.cri.amount}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}" class="page-link">${num}</a></li>
                                     
           </c:forEach>
           <c:if test="${pageMaker.next}">
             	
             	<li><a href="/board/list?pageNum=${pageMaker.endPage+1}&amount=${pageMaker.cri.amount}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">Next</a></li>
          </c:if>
		   </ul>
    
	<%@ include file="../includes/footer.jsp" %>