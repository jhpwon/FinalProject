package com.multi.mapper;

import java.util.List;

import com.multi.model.CartVO;

public interface CartMapper {
	
	Integer selectCartCountByPnum(CartVO cvo);
	//회원아이디, 상품번호
	int updateCartQty(CartVO cvo);
	int addCart(CartVO cvo);
	
	List<CartVO> selectCartView(String userid);
	CartVO getCartTotal(String userid);
	
	int delCart(int cartNum);
	int editCart(CartVO cvo);
	int delCartAll(String userid);

}
