<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<style>
	.table td{
		text-align:left;
		padding:7px;
	}
</style>
<!-- CKEditor CDN------------------------------------------------------------- -->
<script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script> 
<!-- ------------------------------------------------------------------------ -->

<div class="container">

    <h1 class="text-center mt-4">Spring Board 글수정</h1>
    <br><br>
    <!--  
    파일업로드시
    [1] method="post"
    [2] enctype="multipart/form-data"
    -->
    <form name="boardF" action="write" method="post" enctype="multipart/form-data">
    	<!-- hidden data----------------------- -->
    	<input type="hidden" name="num" value="${vo.num }">
    	<input type="hidden" name="mode" value="edit">
    	<!-- ---------------------------------- -->
    	<table class="table table-bordered">
			<tr>
				<th style="width:20%">제목</th>
				<td style="width:80%">
					<input type="text" name="subject" value="${vo.subject}"
					 id="subject" placeholder="Subject" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글쓴이</th>
				<td style="width:80%">
					<input type="text" name="userid"  value="${vo.userid}"
					 id="name" placeholder="Name" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글내용</th>
				<td style="width:80%">
					<textarea rows="10" cols="50" name="content"
					id="content" placeholder="Content" class="form-control">${vo.content}</textarea>
				</td>
			</tr>
			<tr>
				<th style="width:20%">비밀번호</th>
				<td style="width:80%">
					<input type="password"
					 name="passwd" id="pwd" placeholder="Password" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">첨부파일</th>
				<td style="width:80%">
				<c:if test="${vo.filename ne null}">
					${vo.originFilename} [${vo.filesize}]bytes
					<!--  -->
					<!-- 첨부파일이 있다면 --> 
				<!-- 첨부파일명을 모두 소문자로 변환해서 fname변수에 할당 (확장자 체크 위해) -->
				<c:set var="fname" value="${fn:toLowerCase(vo.filename)}"/>
				
				<c:if test="${fn:endsWith(fname,'.png') or fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.gif') }">
					<img src="${pageContext.request.contextPath}/upload/${vo.filename}" style="width:3em">
				</c:if>
				 
				[ ${board.filesize} ]bytes
				
				
			
					<!--  -->
					<br>
					<!-- hidden data--------------------------------------------- -->
					<input type="hidden" name="old_filename" value="${vo.filename}">
					<!-- 업로드했던 첨부파일명을 hidden으로 넘긴다---------------------- -->
				</c:if>	
					<input type="file"
					 name="mfilename" id="filename"
					  placeholder="Attach File" class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center" style="text-align:center">
					<button type="button" onclick="check()" class="btn btn-success" id="btnWrite">글수정</button>
					<button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
				</td>
			</tr>
		</table>
    </form>

    <script>
    	$(function(){
    		CKEDITOR.replace('content');
    	})
    	
    
		function check(){    		
    		
			if(!boardF.userid.value){
				alert('작성자를 입력하세요');
				boardF.userid.focus();
				return;
			}
			if(CKEDITOR.instances.content.getData()==''){
				alert('글내용을 입력하세요');
				CKEDITOR.instances.content.focus();
				return;
			}
			
			if(!boardF.passwd.value){
				alert('글 비밀번호를 입력하세요');
				boardF.passwd.focus();
				return;
			}
			boardF.submit();
		}//check()------------------    
    </script>
    
</div>






    