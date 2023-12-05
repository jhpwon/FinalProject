<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<script>
	function check(){
		//이름
		if(!mf.name.value){
			alert('이름을 입력하세요');
			mf.name.focus();
			return;
		}
		//아이디
		if(!mf.userid.value){
			alert('아이디를 입력하세요');
			mf.userid.focus();
			return;
		}
		//비밀번호
		if(!mf.userpwd.value){
			alert('비밀번호를 입력하세요');
			mf.userpwd.focus();
			return;
		}
		//비밀번호,비밀번호 확인 값 일치여부
		if(mf.userpwd.value != mf.userpwd.value){
			alert('비밀번호가 서로 달라요');
			mf.userpwd2.select();
			return;
		}
		//연락처1,2,3
		if(!mf.hp1.value||!mf.hp2.value||!mf.hp3.value){
			alert("연락처를 모두 입력해야해요");
			mf.hp1.focus();
			return;
		}
		
		if(isNaN(mf.hp2.value)){
			alert('연락처는 숫자여야 함');
			mf.hp2.select();
			return;
		}
		
		if(isNaN(mf.hp3.value)){
			alert('연락처는 숫자여야 함');
			mf.hp3.select();
			return;
		}
		
		if(!mf.agree.checked){
			alert('이용 약관에 동의해야 함');
			mf.agree.focus();
			return;
		}
		
		
		mf.submit();
	}
</script>
<div class="container">
	<h1>Signup Page</h1>
	<br><br>
	<form name="mf" action="signupEnd.jsp" method="post">
	<table border="1" id="userTable">
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userid">
						<button type="button" class="btn">아이디 중복체크</button>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userpwd">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="userpwd2">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="hp1" class="hp">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
						</select>
						<input type="text" name="hp2" class="hp" maxlength="4">
						<input type="text" name="hp3" class="hp" maxlength="4">
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" name="post" class="post">
						<button type="button" class="btn">우편번호 찾기</button>
					</td>
				</tr>
					<tr>
					<th>주소</th>
					<td>
						<input type="text" name="addr1" class="addr"><br>
						<input type="text" name="addr2" class="addr">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label for="agree">
							이용약관에 동의하십니까?
							<input type="checkbox" name="agree" id="agree" style="width:2.5em">
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<iframe src="agree.html" width="90%" height="120px"></iframe>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="text-align:center; height:28px">
						<button type="button" class="btn" onclick="check()">회원가입</button>
						<button type="reset" class="btn">다시쓰기</button>
					</td>
				</tr>
			</table>
	</form>
</div>
<jsp:include page="/foot.jsp"/>