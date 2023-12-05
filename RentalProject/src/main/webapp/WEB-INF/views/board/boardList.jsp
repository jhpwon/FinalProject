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
	<h1 class="text-center mt-4">Spring Board 목록</h1>
	<br><br>
	<a href="write.me">글쓰기</a>
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
			<!-- 페이지 네비게이션 ----- -->
			<c:forEach var="i" begin="1" end="${pageCount}" step="1">
			
			[<a href="list.me?cpage=${i}"  <c:if test="${cpage eq i}">class="active"</c:if>      >  			
			${i}			   
			</a>]
			
			</c:forEach>			
			<!-- -------------------- -->
			</td>
			<td colspan="2">
				총게시글 수: <span style="color:red;font-weight:bold">${totalCount}</span> 개
			</td>
		</tr>
	</table>

</div>




