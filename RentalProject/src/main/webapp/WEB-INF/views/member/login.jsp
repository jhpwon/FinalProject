<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.logtd{
		text-align:Left;
		padding-left:2em;
	}
</style>
<script>
	const send=function(){
		if(!loginF.userid.value){
			alert('아이디를 입력하세요');
			loginF.userid.focus();
			return false;
		}
		if(!loginF.userpwd.value){
			alert('비밀번호를 입력하세요');
			loginF.userpwd.focus();
			return false;
		}
		return true;
	}
</script>

<div class="container">
	<h1 class="text-center mt-4">Login page</h1>
	<br><br>
	<%-- 쿠키에 저장된 아이디: ${cookie.uid.value} --%>
	<form name="loginF" action="login" method="post" onsubmit="return send()">
		<table class="table" style="width:60%;margin:auto;height:250px">
			<tr>
				<th class="m1">아 이 디</th>
				<td class="logtd">
					<input type="text" name="userid" id="userid" value="${cookie.uid.value}" placeholder="ID" autofocus="autofocus">
				</td>
			</tr>
			
			<tr>
				<th class="m1">비밀번호</th>
				<td class="logtd">
					<input type="password" name="userpwd" id="userpwd" placeholder="Password">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<lable for="saveId" style="margin-right:2em">
						<input type="checkbox" 
							<c:if test="${cookie.uid != null}">
								checked
							</c:if>
						name="saveId" id="saveId" style="width:2.5em">
					 	아이디 저장
					</lable>
					<button type="submit" class="btn btn-info">로그인</button>
				</td>
			</tr>
		</table>
	</form>
</div>