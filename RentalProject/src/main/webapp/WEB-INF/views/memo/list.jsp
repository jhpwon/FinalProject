<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-5">
	<h1 class="text-center">::한줄 메모장 목록::</h1>
	<br><br>
	<div class="text-center">
		<span>총 게시글 수: ${totalCount}개</span> /
		<span class="text-danger">현재 ${cpage} 페이지</span> / 총 ${pageCount} 페이지
	</div>
	<br><br>
	<table class="table table-hover" border="1">
		<tr>
			<th width="10%">글번호</th>
			<th width="60%">메모내용</th>
			<th width="20%">작성자</th>
			<th width="10%">삭제</th>
		</tr>
		<c:if test="${memoArr eq null or empty memoArr}">
			<tr>
				<td colspan="4">데이터가 없습니다</td>
			</tr>
		</c:if>
		<c:if test="${memoArr ne null and not empty memoArr}">
			<c:forEach var="memo" items="${memoArr}">
				<tr>
					<td>
						<c:out value="${memo.idx}" />
					</td>
					<td>
						<c:out value="${memo.msg}" />
						<span>
							[<c:out value="${memo.wdate}" />]
						</span>
					</td>
					<td>
						<c:out value="${memo.name}" />
					</td>
					<td><a href="javascript:del('${memo.idx}')">삭제</a></td>
				</tr>
			</c:forEach>
		</c:if>

		<tr>
			<td colspan="4" class="text-center">
				<c:if test="${prevBlock>0}">
					<a href="memoList?cpage=${prevBlock}">Prev</a> |
				</c:if>
				<c:forEach var="i" begin="${prevBlock+1}" end="${nextBlock-1}">
					<c:if test="${i<=pageCount}">
						[<a href="memoList?cpage=${i}">${i}</a>]
					</c:if>
				</c:forEach>
				<c:if test="${nextBlock<=pageCount}">
					| <a href="memoList?cpage=${nextBlock}">Next</a>
				</c:if>
			</td>
		</tr>
	</table>
	<p class="text-center">
		[<a href="memo">글쓰기</a>]
	</p>
</div>
<!-- 글 삭제 폼 -->
<form name="df" action="memoDelete" method="post">
	<input type="hidden" name="idx">
</form>
<script>
	function del(no){
		let yn=confirm(no+"번 글을 정말 삭제할까요?");
		if(!yn) return;
		//location.href="memoDelete?idx="+no;
		df.idx.value=no;
		df.submit();
		
	}
</script>