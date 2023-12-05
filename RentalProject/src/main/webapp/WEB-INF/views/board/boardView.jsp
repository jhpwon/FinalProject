<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- boardView.jsp -->
<style>
	td:nth-child(2n){
		text-align:left;
		padding-left:1.5em;
	}
</style>
<div class="container">
		<h1 class="text-center mt-4">Spring Board 내용</h1>
	<br>
	<br> 
	<!--/wview/12
		/board/write
	  -->
	<div class="text-center">  
		<a href="../write">글쓰기</a>|<a href="${myctx}/board/list">글목록</a>| <br>
	</div>
	
	<br>
	<br>
	<!-- eq :  == 와 동일. equals  -->
<c:if test="${board eq null }">
	<h3>해당 게시글은 존재하지 않아요</h3>
</c:if>	
<!-- ne: != 와 동일. not equals -->
<c:if test="${board ne null }">	
	<table class="table table-bordered">
		<tr>
			<td width="20%" class="m1"><b>글번호</b></td>
			<td width="30%">
			${board.num}
			</td>
			<td width="20%" class="m1"><b>작성일</b></td>
			<td width="30%">
			<fmt:formatDate value="${board.wdate}" pattern="yyyy-MM-dd hh:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td width="20%"  class="m1"><b>글쓴이</b></td>
			<td width="30%">${board.userid}</td>
			<td width="20%"  class="m1"><b>조회수</b></td>
			<td width="30%">${board.readnum}</td>
		</tr>
		<tr>

			<td width="20%"  class="m1"><b>첨부파일</b></td>
			<td colspan="3" class="text-left">&nbsp; 
			<!-- 첨부파일이 있다면 --> 
			<c:if test="${board.filename ne null}">
				<a	href="${myctx}/resources/board_upload/${board.filename}" download> 
				${board.originFilename}</a>
				
				<!-- 첨부파일명을 모두 소문자로 변환해서 fname변수에 할당 (확장자 체크 위해) -->
				<c:set var="fname" value="${fn:toLowerCase(board.filename)}"/>
				
				<c:if test="${fn:endsWith(fname,'.png') or fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.gif') }">
					<img src="${myctx}/resources/board_upload/${board.filename}" style="width:3em">
				</c:if>
				 
				[ ${board.filesize} ]bytes
				
				
			</c:if>
			
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>제목</b></td>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>글내용</b></td>
			<td colspan="3">
			
			<pre>${board.content}</pre>
			
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-center">
			<c:if test="${loginUser.userid eq board.userid }">
				<a href="#"	onclick="goEdit()">글수정</a>| 
				<a href="#" onclick="goDel()">글삭제</a> |
			</c:if>
			
			<a href="javascript:goRe()">답변쓰기</a>
				
		</td>
		</tr>
	</table>
</c:if>
	<br><br>
	<!-- 삭제 또는 수정 form 시작------------------------------- -->
	<form id="bf" name="bf" method="post">
		<!-- 수정 또는 삭제할 글번호 =>hidden -->
		<input type="hidden" name="num" value="${board.num}">
		<div id="divPwd" style="display:none" class="col-8 offset-2 text-center">
			<label for="pwd">비밀번호</label>
			<input type="password" name="passwd" id="pwd" placeholder="Password" required>
			<button class="btn btn-info" id="btn1"></button>
		</div>
	</form>
	<!-- --------------------------------------------------- -->
	
	<!-- 답변달기  form---------------------------------- -->
    <form name="reF" id="reF" action="${myctx}/user/rewrite" method="post">
      	<!-- hidden으로 부모글의 글번호(num)와 제목(subject)을 넘기자 -->
      	<input type="hidden" name="num" value="<c:out value="${board.num}"/>">
      	<input type="hidden" name="subject" value="<c:out value="${board.subject}"/>">
    </form>
   <!-- ---------------------------------------------- -->
	
	

</div><!--  .container end -->

<script>
	function goRe(){
		reF.submit();
	}//---------------------


	function goDel(){
		let yn=confirm("글을 삭제할까요?");
		if(yn){
			let obj=document.getElementById('divPwd');
			let btn1=document.getElementById('btn1');	
			obj.style.display='block';
			btn1.innerText='글삭제';
			bf.action='${myctx}/user/delete';//절대경로
		}//if---
		
	}//goDel()----------------
	
	
	function goEdit(){
		let obj=document.getElementById('divPwd');
		let btn1=document.getElementById('btn1');	
		obj.style.display='block';
		btn1.innerText='글수정';
		bf.action='${myctx}/user/edit';
	}//goDel()----------------
	
</script>


w



















