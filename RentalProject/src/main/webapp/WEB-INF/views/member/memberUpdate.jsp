<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<script>
	function openWin(){
		let url="../idCheck.do";
		win=open(url, "idCheck", "width=600, height=600, left=200, top=200");
	}
	
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
		
		mf.submit();
	}
</script>
<div class="container">
	<h1>[ ${loginId} ]님 최원 정보 수정</h1>
	<br><br>
	<form name="mf" action="memberUpdateEnd.do" method="post">
	<table border="1" id="userTable">
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" value="${user.name}">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userid" value="${user.userid}" readonly>
						<button type="button" class="btn" onclick="openWin()">아이디 중복체크</button>
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
						<input type="text" name="hp2" value="${user.hp2}" class="hp" maxlength="4">
						<input type="text" name="hp3" value="${user.hp3}" class="hp" maxlength="4">
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" name="post" value="${user.post}" class="post">
						<button type="button" class="btn">우편번호 찾기</button>
					</td>
				</tr>
					<tr>
					<th>주소</th>
					<td>
						<input type="text" name="addr1" value="${user.addr1}" class="addr"><br>
						<input type="text" name="addr2" value="${user.addr2}" class="addr">
					</td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td> ${user.mileage} 점</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td> ${user.indate} </td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="text-align:center; height:28px">
						<button type="button" class="btn" onclick="check()">수정하기</button>
						<button type="reset" class="btn">다시쓰기</button>
					</td>
				</tr>
			</table>
			<br><br>
			<a>활동중지</a> | <a>회원탈퇴</a>
	</form>
</div>
<jsp:include page="/foot.jsp"/>