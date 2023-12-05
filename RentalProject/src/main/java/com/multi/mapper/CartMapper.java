package com.multi.mapper;

import java.util.List;

import com.multi.model.CartVO;

public interface CartMapper {
	
	Integer selectCartCountByPnum(CartVO cvo);
	//ȸ�����̵�, ��ǰ��ȣ
	int updateCartQty(CartVO cvo);
	int addCart(CartVO cvo);
	
	List<CartVO> selectCartView(String userid);
	CartVO getCartTotal(String userid);
	
	int delCart(int cartNum);
	int editCart(CartVO cvo);
	int delCartAll(String userid);

}
