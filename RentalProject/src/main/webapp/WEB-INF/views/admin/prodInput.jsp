<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			
			let pname=$('#pname');
			if(!pname.val()){
				alert('상품명을 입력하세요');
				pname.focus();
				return false;
			}
			
			if(!$('#price').val()){
				alert('상품 정가를 입력하세요');
				$('#price').focus();
				return false;
			}
			
			return true;
		})
	})
</script>
<div class="container mt-5" id="content">

	<h1>상품등록-Product Register Page</h1>
	
	<form name="pf" id="pf" method="POST" action="prodInsert" enctype="multipart/form-data">
	<!-- enctype="multipart/form-data" -->
		<table class="table table-condensed mt-4">
			<tr>
				<td width="20%">
					<b>카테고리</b>
				</td>
				<td width="80%">
					<select name="upCg_code" id="upCg_code">
						<option value="">::상위 카테고리::</option>
						<c:forEach var="up" items="${upCgList}">
							<option value="${up.upCg_code}">${up.upCg_name}</option>
						</c:forEach>
					</select>
					<span id="downCg">
						<select name="downCg_code" id="downCg_code">
							<option value="">::하위 카테고리::</option>
							<option value="1">가전제품</option>
							<option value="2">컴퓨터</option>
						</select>
					</span>
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품명</b>
				</td>
				<td width="80%">
					<input type="text" name="pname" id="pname" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>제조사</b>
				</td>
				<td width="80%">
					<input type="text" name="pcompany" id="pcompany" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 스펙</b>
				</td>
				<td width="80%">
					<select name="pspec" id="pspec">
						<option value="NEW" selected>NEW</option>
						<option value="HIT">HIT</option>
						<option value="BEST">BEST</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 이미지</b>
				</td>
				<td width="80%">
					<input type="file" name="pimage" accept="images/*" class="form-control">
					<input type="file" name="pimage" accept="images/*" class="form-control">
					<input type="file" name="pimage" accept="images/*" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 수량</b>
				</td>
				<td width="80%">
					<input type="number" name="pqty" id="pqty" min="1" max="100" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 정가</b>
				</td>
				<td width="80%">
					<input type="number" name="price" id="price" min="0" required class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 판매가</b>
				</td>
				<td width="80%">
					<input type="number" name="saleprice" id="saleprice" min="0" required class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 설명</b>
				</td>
				<td width="80%">
					<textarea name="pcontent" id="pcontent" rows="5" cols="60" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 포인트</b>
				</td>
				<td width="80%">
					<input type="number" name="point" id="point" min="0" required class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-outline-primary">상품등록</button>
					<button class="btn btn-outline-danger">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>