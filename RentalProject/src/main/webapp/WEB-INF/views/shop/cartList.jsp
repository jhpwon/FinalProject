<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container mt-5" id="content">
	<h2 class="text-center text-secondary">${loginUser.name} [${loginUser.userid}] 님의 장바구니</h2>
	<br><br>
	<div>
		<form name="orderF" action="orderAdd" method="post">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>상품번호</th>
						<th>상품명</th>
						<th>수량</th>
						<th>단가</th>
						<th>금액</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${cartArr eq null or empty cartArr}">
						<tr>
							<td colspan="6">
								<b>장바구니에 담긴 상품이 없습니다</b>
							</td>
						</tr>
					</c:if>
					<!--
						varStatus 에 변수명을 지정하면
						해당 변수로 반복문의 상태정보를 알아낼 수 있다. 
						state.index : 인덱스값 (0 ~ 3)
						state.count : 반복문 횟수 (1 ~4)
					-->
					<c:if test="${cartArr ne null or not empty cartArr}">
						<c:forEach var="item" items="${cartArr}" varStatus="state">
							<tr>
								<td>
									<label>
										<input type="checkbox" name="pnum" id="pnum${state.index}" value="${item.pnum_fk}"> ${item.pnum_fk}
									</label>
								</td>
								<td>
									<h4>${item.pname}</h4>
									<a href="../prodDetail?pnum=${item.pnum_fk}" target="_blank">
										<img src="../resources/product_images/${item.pimage1}" class="img-fluid" style="width: 140px">
									</a>
								</td>
								<td>
									<input type="number" name="pqty" id="pqty${state.index}" value="${item.pqty}" min="1" max="50">
									<button type="button" class="btn btn-success" onClick="cartEdit('${item.cartNum}','${state.index}')">수정</button>
								</td>
								<td>
									<fmt:formatNumber value="${item.saleprice}" pattern="###,###" /> 원
									<br>
									<span class="badge badge-danger">${item.point }</span> POINT
								</td>
								<td>
									<fmt:formatNumber value="${item.totalPrice}" pattern="###,###" /> 원
									<br>
									<span class="badge badge-danger">${item.totalPoint}</span> POINT
								</td>
								<td>
									<a href="#" onclick="cartDel('${item.cartNum}')">삭제</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<!-- --------------------------- -->
					<tr>
						<td colspan="3">
							<h5>
								장바구니 총액:
								<span class='text-danger'>
									<fmt:formatNumber value="${cartSum.cartTotalPrice}" pattern="###,###" /> 원
								</span>
							</h5>
							<h5>
								장바구니 총포인트:
								<span class='text-success'>
									<fmt:formatNumber value="${cartSum.cartTotalPoint}" pattern="###,###" /> Point
								</span>
							</h5>
						</td>
						<td colspan="3">
							<button type="button" class="btn btn-outline-info" onclick="goOrder()">주문하기</button>
							<button type="button" class="btn btn-outline-danger" onclick="location.href='../index'">계속쇼핑</button>
							<button type="button" class="btn btn-outline-success" onclick="cartDelAll()">장바구니 비우기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<form name="df" id="df" action="cartDel">
			<input type="hidden" name="cartNum">
		</form>
		<form name="ef" id="ef" action="cartEdit">
			<input type="hidden" name="cartNum">
			<input type="hidden" name="pqty">
		</form>
	</div>
</div>
<script>
	function cartEdit(cnum, i){
		//alert(cnum+"/ "+i)
		let qty=$('#pqty'+i).val();
		//alert(qty);
		ef.cartNum.value=cnum;
		ef.pqty.value=qty;
		ef.method='post';
		ef.submit();
	}
	
	function cartDel(cnum){
		alert(cnum);
		let yn=confirm("정말 삭제할까요?");
		if(yn){
			//$('#df input[name="cartNum"]').val(cnum);
			df.cartNum.value=cnum;
			df.method='post';
			df.submit();
		}
		
	}
	
	function cartDelAll(){
		let yn=confirm("정말 모두 삭제할까요?")
		if(yn){
			location.href="cartDelAll";
		}
	}
</script>