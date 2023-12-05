<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.model.*" %>
<%@ page import="java.sql.*" %>
<%
	//1. post방식일때 한글 처리
	request.setCharacterEncoding("utf-8");
	//2. 사용자가 입력한 값 받기(name,userid,userpwd,hp1,hp2,hp3,post,addr1,addr2)
	String name=request.getParameter("name");
	String userid=request.getParameter("userid");
	String userpwd=request.getParameter("userpwd");
	String hp1=request.getParameter("hp1");
	String hp2=request.getParameter("hp2");
	String hp3=request.getParameter("hp3");
	String post=request.getParameter("post");
	String addr1=request.getParameter("addr1");
	String addr2=request.getParameter("addr2");
	//3. 유효성 체크 (name,userid,userpwd,hp1,hp2,hp3)
	if(name==null||userid==null||userpwd==null||hp1==null||hp2==null||hp3==null){
		response.sendRedirect("signup.jsp");
		return;
	}
	//4. 2번 값을 MemberVO객체에 전달
	MemberVO user=new MemberVO(name,userid,userpwd,hp1,hp2,hp3,post,addr1,addr2);
	//5. MemberDAO생성해서 insert()호출
	MemberDAO dao=new MemberDAO();
	int n=0;
	try{
		n=dao.insert(user);
	}catch(SQLException e){
		%>
		<script>
			alert("아이디를 이미 사용하고 있어요. 중복체크를 하세요");
			history.back();
		</script>
		<%
		return;
	}
	
	//6. 그 결과 메시지,이동경로 처리
	String msg=(n>0)?"회원가입 완료":"회원가입 실패";
	String loc=(n>0)?"memberList.jsp":"signup.jsp";
%>
<script>
	alert('<%=msg%>');
	location.href='<%=loc%>';
</script>
