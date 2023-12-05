<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

    <h1 class="text-center mt-4">Spring Board 글쓰기</h1>
    <br><br>
    <!--  
    파일업로드시
    [1] method="post"
    [2] enctype="multipart/form-data"
    -->
    <form name="boardF" action="../user/write" method="post" enctype="multipart/form-data">
    	<!-- -----hidden data--------------------------------------- -->
    	<input type="hidden" name="mode" value="write">
    	<!--mode값:  원본 글쓰기(write), 글수정(edit), 답변글쓰기(rewrite) -->
    	<!-- --------------------------------------------- -->
    	<table class="table table-bordered">
			<tr>
				<th style="width:20%">제목</th>
				<td style="width:80%">
					<input type="text" name="subject"
					 id="subject" placeholder="Subject" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글쓴이</th>
				<td style="width:80%">
					<input type="text" name="userid" value="${loginUser.userid}"
					<c:if test="${loginUser ne null}">
						readonly
					</c:if>
					 id="userid" placeholder="USER ID" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글내용</th>
				<td style="width:80%">
					<textarea rows="10" cols="50" name="content"
					id="content" placeholder="Content" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<th style="width:20%">비밀번호</th>
				<td style="width:80%">
					<input type="password"
					 name="passwd" id="passwd" placeholder="Password" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">첨부파일</th>
				<td style="width:80%">
					<input type="file"
					 name="mfilename" id="mfilename"
					  placeholder="Attach File" class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center" style="text-align:center">
					<button type="button" onclick="check()" class="btn btn-success" id="btnWrite">글쓰기</button>
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






    