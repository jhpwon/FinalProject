<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib-------------------------------------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ----------------------------------------------------- -->
<script>
	const openPop=function(img){
		let url='resources/product_images/'+img;
		let obj=new Image();
		obj.src=url;
		//원래 크기
		let w=obj.width;//폭
		let h=obj.height;//높이
		console.log('w='+w+", h="+h);
		window.open(url,"preview","width="+w+"px, height="+h+"px, left=100px, top=100px")
	}//openPop--------------------
	const goCart=function(){
		frm.action="user/cartAdd";
		//frm.method='get';//나중에 post로 수정
		frm.submit();
	}//goCart----------------------------
	const goOrder=function(){
		$('#frm').prop('action','user/order')
			    // .prop('method','get')
			     .submit();
	}//goOrder--------------------------
	const goWish=function(){
		frm.action="user/wishAdd";
		frm.submit();
	}//goWish--------------------------
</script>
<div class="container mt-5" id="content">
  <table class="table">
               <thead>
                  <tr>
                     <th colspan="2"><h3 style="text-align:center">상품 정보</h3></th>
                  </tr>
               </thead>

               <tbody>
                  <tr>
                     <td align="center" width="50%">
                      
                     <a href="#" onclick="openPop('${item.pimage1}')">
                     <img
                           src="resources/product_images/${item.pimage1}"
                            class="img-fluid" style="width: 70%;"> <!-- </a> -->
                     </a></td>

                     <td align="left" width="50%" style="padding-left: 40px">
                        <h4>
                           <span class="badge badge-danger">${item.pspec}</span>
                        </h4> 
                        	상품번호: ${item.pnum} <br> 
                        	상품이름: ${item.pname} <br> 
                        	정가:<del>
                            <fmt:formatNumber value="${item.price}" pattern="###,###" />
                        	</del>원<br> 
                        	판매가:<span style="color: red; font-weight: bold">
                           <fmt:formatNumber value="${item.saleprice}" pattern="###,###" />
                     			</span>원<br> 
                     	    할인율:<span style="color: red">${item.percent} %</span><br>

                        POINT:<b style="color: green">[${item.point}]</b>POINT<br>

                        <!-- form시작---------- -->
                        <form name="frm" id="frm" method="POST">
                           <!-- 상품번호를 hidden으로 넘기자------ -->
                           <input type="hidden" name="pnum" value="${item.pnum}">                           
                           <!-- -------------------------------- -->
                           <label for="pqty">상품갯수</label> 
                           <input type="number" name="pqty"
                              id="pqty" min="1" max="50" size="2" value="1">

                        </form> 
                        <!-- form end------------ -->

                        <button type="button" onclick="goCart()" class="btn btn-primary">장바구니</button>
                        <button type="button" onclick="goOrder()"
                           class="btn btn-warning">주문하기</button>
                        <button type="button" onclick="goWish()" class="btn btn-danger">위시리시트</button>
                     </td>

                  </tr>
                  <tr style="border: 0">
                     <td align="center">
                     	<img src="resources/product_images/${item.pimage2}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                     <td align="center">
                     	<img src="resources/product_images/${item.pimage3}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <p>상품설명</p> 
                        <pre>${item.pcontent}</pre>
                     </td>
                  </tr>
               </tbody>

            </table>



</div>