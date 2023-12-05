<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function openWin(){
		let url="idCheck";
		win=open(url,"idCheck","width=600, height=600, left=200, top=200")
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
		if(mf.userpwd.value != mf.userpwd2.value){
			alert('비밀번호가 서로 달라요');
			mf.userpwd2.select();
			return;
		}
		
		//연락처1,2,3
		if(!mf.hp1.value || !mf.hp2.value || !mf.hp2.value){
			alert('연락처를 모두 입력해야 해요');
			mf.hp1.focus();
			return;
		}
		//isNaN(obj) : 숫자가 아니면 true를 반환하는 함수
		if(isNaN(mf.hp2.value)){
			alert('연락처는 숫자여야 해요');
			mf.hp2.select();
			return;
		}
		if(isNaN(mf.hp3.value)){
			alert('연락처는 숫자여야 해요');
			mf.hp3.select();
			return;
		}
		//alert(mf.agree.checked);//체크박스의 경우 checked속성을 이용
		if(!mf.agree.checked){//약관동의에 체크하지 않았다면
			alert('이용 약관에 동의해야 합니다');			
			return;
		}
		mf.submit();
		
	}//check()-------------


</script>

<!--  kakao 우편번호 api 사용----------------------  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- -------------------------------------------- -->
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('post').value = data.zonecode;
                document.getElementById("addr1").value = roadAddr;
                //document.getElementById("addr1").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>


<!-- -------------------------------------------- -->
	<div class="container" id="content">
		<h1 class="text-center mt-4">Signup Page</h1>
		<br><br>
		
		<form name="mf" action="signup" method="post">
	
		<table  id="userTable" class="table">				
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" class="form-control">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userid" readonly  class="form-control">
						<button type="button" class="btn btn-success" onclick="openWin()">아이디 중복체크</button>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userpwd"  class="form-control">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="userpwd2"  class="form-control">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="hp1" class="hp"  class="form-control">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
						</select>
					
						<input type="text" name="hp2" class="hp" maxlength="4"  class="form-control">-
						<input type="text" name="hp3" class="hp" maxlength="4"  class="form-control">						
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" name="post" id="post"   class="post" readonly  class="form-control">
						<button type="button" class="btn btn-success" onclick="execDaumPostcode()" >우편번호 찾기</button>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="addr1"  id="addr1" readonly  class="form-control"><br>
						<input type="text" name="addr2" id="addr2"  class="form-control">
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
					
						<iframe src="resources/agree.html" width="90%" height="120px"></iframe>
					
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" style="text-align:center;height:28px">
						<button type="button" class="btn btn-info" onclick="check()">회원가입</button>
						<button type="reset" class="btn btn-warning">다시쓰기</button>
					</td>
				</tr>
			</table>
			
		
		
		</form>
		
	</div>
	
	