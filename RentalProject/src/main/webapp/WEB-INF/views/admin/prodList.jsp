<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib-------------------------------------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ----------------------------------------------------- -->

<div class="container mt-5" id="content">

	<h1 class="text-center m-4">상품 목록-Product List Page</h1>
	
	<table class="table table-striped" id="table">
		<thead>
            <tr>
               <th>상품번호</th>
               <th>카테고리</th>
               <th>상품명</th>
               <th>이미지</th>
               <th>가    격</th>
               <th>수정|삭제</th>
            </tr>
        </thead>
		<tbody>
		<!-- --------------------------- -->
		<c:choose>
			<c:when test="${prodArr eq null or empty prodArr}">
				<tr>
					<td colspan="6">등록된 상품이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				
				<c:forEach var="item" items="${prodArr}">
					<tr>
						<td>
						<c:out value="${item.pnum}"/>						
						</td>
						<td>
						<c:out value="${item.upCg_name}"/>							
						>>
						<c:out value="${item.downCg_name}"/>
						</td>
						<td>
						<c:out value="${item.pname}"/>
						
						<span class="badge badge-info">
							<c:out value="${item.pspec}"/>
						</span>
						
						
						</td>
						<td>
							<img src="${myctx}/resources/product_images/${item.pimage1}"
								width="100px">						
						</td>
						<td>
							<del>
							정가: <fmt:formatNumber value="${item.price}" pattern="###,###" />   원
							</del><br>
							
							
							<b class="text-primary">판매가: <fmt:formatNumber value="${item.saleprice}" pattern="###,###" />원</b><br>
							
							<span class="badge badge-danger">    
								<c:out value="${item.percent}"/>
							 %할인
							 </span>
							
						</td>
						<td>
						<a href="#" onclick="goEdit('${item.pnum}')">수정</a>
						<a href="javascript:goDel('${item.pnum}')">삭제</a>
						</td>
					</tr>
				</c:forEach>
				
			</c:otherwise>
		</c:choose>
		
		<!-- --------------------------- -->
		</tbody>
	</table>
	<!--  수정 또는 삭제 FORM ---------------------- -->
	<form name="pf" method="post">
		<input type="hidden" name="pnum" id="pnum">
	</form>
	<!-- ---------------------------------------- -->
	
	<script>
		const goEdit=function(num){
			//alert(num);
			//pf.pnum.value=num;
			$('#pnum').val(num);//setter
			//val()  :   getter
			//val(값): setter
			//pf.action='prodEditForm';
			//pf.submit();
			$('form[name="pf"]').prop("action", "prodEditForm")
								.submit();
			//prop(key, value) : setter
			//prop(key)        : getter
		}//---------------------
		const goDel=function(num){
			//alert(num);
			let yn=confirm(num+"번 상품을 삭제할까요?");
			if(!yn) return;
			$('#pnum').val(num);
			pf.action='prodDelete';
			pf.submit();
		}//-----------------------
	
	</script>
	
	
</div>









