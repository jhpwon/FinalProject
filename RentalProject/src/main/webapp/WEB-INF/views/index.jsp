<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-5" id="content">
	<!-- HIT 상품 -->
	<%@ include file="/inc/mallHit.jspf" %>

	<br><br>
	<!-- NEW 상품 -->
	<%@ include file="/inc/mallNew.jspf" %>	
</div>