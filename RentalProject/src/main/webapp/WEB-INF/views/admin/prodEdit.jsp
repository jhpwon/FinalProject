<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib-------------------------------------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ----------------------------------------------------- -->

<script>
	$(function(){
		$('#pf').on('submit', function(){
			if(!$('#upCg_code').val()){
				alert('상위 카테고리를 선택하세요');
				$('#upCg_code').focus();
				return false;
			}
			
			if(!$('#downCg_code').val()){
				alert('하위 카테고리를 선택하세요');
				$('#downCg_code').focus();
				return false;
			}
			
			let pname=$('#pname');//input객체
			if(!pname.val()){
				alert('상품명을 입력해야 해요');
				pname.focus();
				return false;//submit되지 않음
			}
			
			if(!$('#price').val()){
				alert('상품 정가를 입력하세요');
				$('#price').focus();
				return false;
			}
			return true;//submit된다
		})//pf폼이 전송될때 -----------------
		
	})//$() end---------------
</script>


<div class="container mt-5" id="content">

	<h1>상품 수정-Product Edit Page</h1>
	
	<form name="pf" id="pf" method="POST" action="prodInsert"  enctype="multipart/form-data" >
	
		<!-- hidden data (mode값이 edit이면 수정처리, mode파라미터가 없다면 디폴트값 insert로 지정하여 등록처리) -->
		<input type="hidden" name="mode" value="edit">
		<!-- ----------------------------------------------------------------------------- -->
	
			<!--enctype="multipart/form-data"  -->
		<table class="table table-condensed mt-4">
			<tr>
				<td width="20%">
				<b>카테고리</b>
				</td>
				<td width="80%">
					<select name="upCg_code" id="upCg_code">
						<option value="">::상위 카테고리::</option>
						<!-- ------------------  -->
						<c:forEach var="up" items="${upCgList}">
							<option value="${up.upCg_code}"  <c:if test="${item.upCg_code eq  up.upCg_code}">selected</c:if>        >
							${up.upCg_name}
							</option>
						</c:forEach>
						<!-- ----------------------- -->
					</select>
					
					<span id="downCg">
						<select name="downCg_code" id="downCg_code">
							<option value="">::하위 카테고리::</option>
							<!-- ------------------  -->
							<option value="1"  <c:if test="${item.downCg_code eq 1}" >selected</c:if> >가전제품</option>
							
							
							
							<option value="2"  <c:if test="${item.downCg_code eq 2}" >selected</c:if> >컴퓨터</option>
							<!-- ----------------------- -->
						</select>
					
					</span>
				
				</td>
			</tr>
			<tr>
				<td width="20%"><b>상품번호</b></td>
				<td width="80%">
				<input type="text" name="pnum" id="pnum"
				value="<c:out value='${item.pnum}'/>"
				 readonly class="form-control"  >
				</td>
			</tr>
			<tr>
				<td width="20%"><b>상품명</b></td>
				<td width="80%">
				<input type="text" name="pname" id="pname"
				value="<c:out value='${item.pname}'/>"
				 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%"><b>제조사</b></td>
				<td width="80%">
				<input type="text" name="pcompany" id="pcompany"
				value="<c:out value='${item.pcompany}'/>"
				 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 스펙</b>
				</td>
				<td width="80%">
				<span class="badge badge-info">  
					<c:out value="${item.pspec}"/>
				</span>
				<select name="pspec" id="pspec">
					<option value="NEW"  <c:if test="${item.pspec eq 'NEW'}">selected</c:if>>NEW</option>
					
					<option value="HIT" <c:if test="${item.pspec eq 'HIT'}">selected</c:if> >HIT</option>
					<option value="BEST" <c:if test="${item.pspec eq 'BEST'}">selected</c:if> >BEST</option>
				</select>
				
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 이미지</b>
				</td>
				<td width="80%">
					<c:if test="${item.pimage1 ne null}">
						<img src="<c:out value='../resources/product_images/${item.pimage1}'/>"  width="120px">
					</c:if>
					<c:if test="${item.pimage2 ne null}">
						<img src="<c:out value='../resources/product_images/${item.pimage2}'/>"  width="120px">
					</c:if>
					<c:if test="${item.pimage3 ne null}">
						<img src="<c:out value='../resources/product_images/${item.pimage3}'/>"  width="120px">
					</c:if>
					<br>
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>				
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 수량</b>
				</td>
				<td width="80%">
					<input type="number" name="pqty" id="pqty"
					value="<c:out value='${item.pqty}'/>"
					 min="1" max="100"
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 정가</b>
				</td>
				<td width="80%">
					<input type="number" name="price" id="price"
					value="<c:out value='${item.price}'/>"
					 min="0"  required
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 판매가</b>
				</td>
				<td width="80%">
					<input type="number" name="saleprice" id="saleprice"
					value="<c:out value='${item.saleprice}'/>"
					  min="0"
					 required 
					class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 설명</b>
				</td>
				<td width="80%">
					<textarea name="pcontent" id="pcontent"
					 rows="5" cols="60" class="form-control"><c:out value="${item.pcontent}"/></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 포인트</b>
				</td>
				<td width="80%">
					<input type="number" name="point" id="point"
					 min="0" required value="<c:out value='${item.point}'/>"
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-outline-primary">상품수정</button>				
					<button class="btn btn-outline-danger">다시쓰기</button>
				</td>
			</tr>
		</table>	
	
	
	</form>

</div>