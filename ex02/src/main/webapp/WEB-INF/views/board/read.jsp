<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/header.jsp" %>

   <table class="table table-bordered" border="1" id="dataTable" width="100%" cellspacing="0">
       <tr><td>번호</td><td><input type="hidden" id="bno" name="bno" value="${read.bno}">${read.bno}</td></tr>
       <tr><td>제목</td><td>${read.title}</td></tr>
       <tr><td>내용</td><td>${read.content}</td></tr>
       <tr><td>작성자</td><td>${read.writer}</td></tr>
       <tr><td>작성일자</td><td>${read.regDate}</td></tr>
       
       <tr>
          <td colspan="2">
             <button><a href="/board/modify?bno=${read.bno}">글수정</a></button>
             <button><a href="/board/remove?bno=${read.bno}">글삭제</a></button>
          </td>
       </tr>
       
       <!-- 
       <tr>
          <td colspan="2">
             <button><a href="/board/modify?bno=${read.bno}">글수정</a></button>
             <button><a href="/board/remove">글삭제</a></button>
          </td>
       </tr>
        -->
   </table>
   <div class="row"><!-- row -->
      <div class="col-lg-12">
         <div class="panel panel-default">
            <div class="panel-heading">
               <i class="fa fa-comments fa-fw"></i>Reply
               <button id="addReplyBtn" class="btn btn-primary">글쓰기</button>
            </div><!--panel-heading -->
            <div class="panel-body">
               <ul class="chat">
               </ul>      
            </div><!--panel-body -->
            <div class="panel-footer">
         
            </div>
         </div><!--panel-default -->
      </div><!-- col-lg-12 -->
   </div><!-- row -->
   
<%@include file="../includes/footer.jsp" %>