<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::아이디 중복체크 결과::</title>

<link rel="stylesheet" type="text/css" href="css/index.css">
<style>
	#userid{
		padding: 7px;
		border: 1px solid silver;
	}
	div{
		padding: 2em;
		width:90%;
		margin:2em auto;
	}
	.jumbotron,.navbar{
		display:none;
	}
	
</style>

<script>
	const setId=function(uid){
		//alert(uid);
		//부모창 아이디 입력란에 uid값 셋팅하기
		opener.document.mf.userid.value=uid;
		//팝업창 닫기
		window.close();
	}//---------------

	const checkId=function(){
		let obj=document.getElementById('userid');
		if(!obj.value){
			alert('아이디를 입력해야 해요');
			obj.focus();
			return;
		}
		idF.submit();//전송
	}//------------------------
</script>

</head>
<body>
	<div align="center" >
	
		<h3 style='color:blue'>${msg}</h3>
		
		<br><br>
		
		<c:if test="${result =='fail'}">
			<form name="idF" action="idCheck" method="post">
				
				<label for="userid">아이디 </label>
				<input type="text" name="userid" id="userid" placeholder="ID" autofocus="autofocus">
				<button type="button" onclick="checkId()" class="btn"> 확  인</button>
			</form>
		</c:if>
		<c:if test="${result=='success'}">
			<button  class="btn btn-success"
			  onclick="setId('${userid}')">닫  기</button>		
		</c:if>

	</div>
</body>
</html>






