<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.container{
		overflow-y: auto;
	}
</style>

<div class="container">
	<h1>Board 검색 결과 [검색어: ${findKeyword}] </h1>
	<br><br>
	<a href="write.me">글쓰기</a>
	<br><br>
	<!-- 검색 form 시작-----------------------  -->
	<div id="divFind">
		<form name="findF" id="findF" action="find.me"
						class="form-inline">
						<select name="findType" id="findType" class="form-control m-3">
							<option value="0">::검색 유형::</option>
							<option value="1">제 목</option>
							<option value="2">작성자</option>
							<option value="3">글내용</option>
						</select> 
						<input type="text" name="findKeyword" id="findKeyword"
							placeholder="검색어를 입력하세요" class="form-control m-3" required>
						<button class="btn btn-primary">검 색</button>
		</form>
	
	</div>
	<!-- ----------------------------------- -->
	<br><br>
	<table class="table">
		<tr class="m1">
			<th width="10%">글번호</th>
			<th width="40%">제  목</th>
			<th width="20%">작성자</th>
			<th width="20%">작성일</th>
			<th width="10%">조회수</th>			
		</tr>
		<c:if test="${boardArr==null or  empty boardArr}">
			<tr>
				<td colspan="5">
					<b>데이터가 없습니다</b>
				</td>
			</tr>
		</c:if>
		<c:if test="${boardArr!=null and not empty boardArr }">
		<!-- --------------------------- -->
			<c:forEach var="board" items="${boardArr}">
				<tr>
					<td>${board.no}</td>
					<td align="left" style="padding-left:20px">
						<a href="view.me?no=${board.no}">${board.subject}</a>
						<c:if test="${board.filename ne null}">
							<img src="../images/attach.png" style="width:1em">
						</c:if>
						
					</td>
					<td>${board.name}</td>
					<td>
						<fmt:formatDate value="${board.wdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${board.readnum }</td> 
				</tr>
			</c:forEach>
		<!-- --------------------------- -->
		</c:if>
		<tr>
			<td colspan="3">
			<!--  이전 5개-->
			<c:if test="${prevBlock > 0}">
				<a href="find.me?cpage=${prevBlock}">
					Prev
				</a> | 
			</c:if>
						
			<!-- 페이지 네비게이션 블럭 처리 ----- -->
			<c:forEach var="i" begin="${prevBlock+1}" end="${nextBlock-1}" step="1">
				<c:if test="${i<= pageCount }">
					[<a href="find.me?cpage=${i}"  <c:if test="${cpage eq i}">class="active"</c:if>      >  			
					${i}			   
					</a>]
				</c:if>
				
			</c:forEach>			
			<!-- -------------------- -->
			
			<!-- 이후 5개 -->
			<c:if test="${nextBlock <=pageCount }">
				| <a href="find.me?cpage=${nextBlock}">
					Next
				</a>
			</c:if>
			
			</td>
			<td colspan="2">
				총게시글 수: <span style="color:red;font-weight:bold">${totalCount}</span> 개
			</td>
		</tr>
	</table>

</div>




