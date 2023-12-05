<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<style>
		.logtd{
			text-align:left;
			padding-left:2em;
		}
	</style>
	<script>
		const send=function(){
			if(!loginF.userid.value){
				alert('아이디를 입력하세요');
				loginF.userid.focus();
				return false;
			}//if----
			
			if(!loginF.userpwd.value){
				alert('비밀번호를 입력하세요');
				loginF.userpwd.focus();
				return false;
			}
			
			return true;
		}//send()---------------
	
	</script>
	

	<div class="container">
		<h1 class="text-center mt-4">Login Page</h1>
		<br><br>
		<%-- el표현식을 이용한 쿠키 출력
				${cookie.키.value}
		  --%>
		<%-- 쿠키에 저장된 아이디: ${cookie.uid.value } --%>
		<form name="loginF" action="login" method="post" onsubmit="return send()">
											<!-- form 을 전송할때 send()함수가 true반환하면 전송하고,
													false를 반환하면 전송하지 않는다. -->
			<table class='table' style="width:60%;margin:auto;height:250px">
				<tr>
					<th class="m1">아 이 디</th>
					<td class="logtd">
						<input type="text" name="userid" id="userid"
							value="${cookie.uid.value}"
						 placeholder="ID" autofocus="autofocus">
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
						<label for="saveId" style="margin-right:2em">
						<%-- ${cookie} ==> Map 객체
							 ${cookie.키값} ==> Cookie객체
							 ${cookie.키값.value} ==> String 쿠키.getValue()
						 --%>
							<input type="checkbox"
							   <c:if test="${cookie.uid !=null}">
							   		checked
							   </c:if>
							 name="saveId" id="saveId" style="width:2.5em">
							아이디 저장
						</label>
					
						<button type="submit"
						 class="btn btn-info">로그인</button>
					</td>
				</tr>
			
			</table>
		
		</form>
		
	</div>
	
