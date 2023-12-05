<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, user.model.*"%>
<jsp:include page="/top.jsp" />
<div class="container">
	<h1>전체 회원 목록 - [Admin Page]</h1>
	<br><br>
	<table border="1" id="userTable">
		<tr class="m1">
			<th>번호</th>
			<th width="20%">이 름</th>
			<th width="15%">아이디</th>
			<th width="15%">연락처</th>
			<th width="15%">등록일</th>
			<th>마일리지</th>
			<th>회원상태</th>
			<th>삭제</th>
		</tr>
		<%
			ArrayList<MemberVO> arr=(ArrayList<MemberVO>)request.getAttribute("users");
			if(arr==null||arr.size()==0){
				%>
				<tr>
					<td colspan="8">
						<b>데이터가 없습니다</b>
					</td>
				</tr>
				<%
			} else {
				for(int i=0;i<arr.size();i++){
					MemberVO user=arr.get(i);
					
		%>
					<tr>
						<td><%=(i+1) %></td>
						<td><%=user.getName() %></td>
						<td><%=user.getUserid() %></td>
						<td><%=user.getAllHp() %></td>
						<td><%=user.getIndate() %></td>
						<td><%=user.getMileage() %></td>
						<td class="mstate<%=user.getMstate()%>"><%=user.getMstateStr() %></td>
						<td><a href="javascript:manage('<%=user.getUserid()%>')">활동정지</a></td>
					</tr>
		<%		}
			}
		%>
	</table>
	<form name="adminF">
		<input type="hidden" name="userid">
	</form>
</div>
<<script>
	const manage=function(uid){
		adminF.userid.value=uid;
		adminF.action="admin/userMgr.do";
		adminF.method="post";
		adminF.submit();
	}
</script>
<jsp:include page="/foot.jsp" />