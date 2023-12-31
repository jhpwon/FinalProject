<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.multi.model.*" %>
<!-- taglib -------------------------------------------------  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- -------------------------------------------------------- -->    
<!-- memberList.jsp -->
<div class="container">
	<h1>전체 회원 목록 - [Admin Page]</h1>
	<br><br>
	
	<table border="1" id="userTable">
		<tr class="m1">
			<th>번호</th>
			<th width="20%">이름</th>
			<th width="15%">아이디</th>
			<th width="15%">연락처</th>
			<th width="15%">등록일</th>
			<th>마일리지</th>
			<th>회원상태</th>
			<th>상태관리</th>
		</tr>		
		<!--  ------------------------  -->
		<c:if test="${users ==null || empty users }">
			<tr>
				<td colspan="8">
					<b>데이터가 없습니다</b>
				</td>
			</tr>
		</c:if>
		<c:if test="${users!=null && not empty users }">
		<!-- forEach태그의 속성
			begin: 시작값 지정
			end  : 종료값 지정
			step : 증가치
			var  : 변수명 지정
			items : collection들을 지정
			varStatus : 반복문의 상태정보를 추출하기 위한 변수 지정
				=> count속성: 반복문의 횟수 1부터 시작
				=> index속성: 반복문 인덱스 정보 0부터 시작
		
		 -->
			<c:forEach var="user" items="${users}" varStatus="st">	
				<!-- users : ArrayList
					 user: MemberVO
				 -->		
					<tr>
						<td>${st.count}</td>
						<td>${user.name }</td>
						<td>${user.userid}</td>
						<td>${user.allHp}</td>
						<td>${user.indate}</td>
						<td>${user.mileage }</td>
						<td class="mstate${user.mstate}"> ${user.mstateStr}</td>
						<td>
						<!-- 활동회원이면 정지 출력 -->
						<c:if test="${user.mstate == 0 }">
							<a href="javascript:manage('${user.userid}', -1)">정지</a>
						</c:if>
						<!-- 정지회원이면 활동 출력 -->
						<c:if test="${user.mstate == -1 }">
							<a href="javascript:manage('${user.userid}', 0)">활동</a>
						</c:if>
							
						</td>
					</tr>
			</c:forEach>		
		</c:if>					
		<!-- ------------------------- -->
		
	</table>
	<!-- 회원 관리 form ------------------ -->		
	<form name="adminF">
		<input type="hidden" name="userid" >
		<input type="hidden" name="mstate">
	</form>

</div>


<script>
	const manage=function(uid, mstate){
		//alert(uid+"/ "+mstate);
		adminF.userid.value=uid;
		adminF.mstate.value=mstate;
		
		adminF.action="userMgr.do";
		adminF.method="post";
		adminF.submit();
	}

</script>













