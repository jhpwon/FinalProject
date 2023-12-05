package com.multi.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.mapper.CartMapper;
import com.multi.mapper.ProductMapper;
import com.multi.model.CartVO;
import com.multi.model.ProductVO;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductMapper prodMapper;
	
	@Inject
	private CartMapper cartMapper;

	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		
		return this.prodMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
	
		return this.prodMapper.selectByCategory(cg_num);
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		
		return this.prodMapper.getProduct(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		//1. 추가한 상품이 이미 장바구니에 담겨있는 경우==> 수량만 수정 ==>update문
		//2. 추가한 상품이 장바구니에 없는 경우 ===> insert문
		//------------------------------------------------------
		// [1] userid_fk와 pnum_fk로 cart테이블에 있는 장바구니번호 가져오기
		Integer cnt=cartMapper.selectCartCountByPnum(cartVo);
		System.out.println("cnt:  "+cnt);
		//[1_1] 장바구니번호가 있다면 => 이미 담긴 상품 => 수량만 수정
		if(cnt!=null) {
			int n=cartMapper.updateCartQty(cartVo);
			return n;
		}else {//cnt null 인 경우
		
			//[1_2] 개수가 = 0 => insert문 수행
			int n=cartMapper.addCart(cartVo);
			return n;
		}				
	}//--------------------------------------

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		//수량에 따라 로직 처리
		//수량이 0 ==> 삭제처리
		//수량이 음수==> 예외 처리
		//수량이 양수==> 수정처리
		int qty=cartVo.getPqty();
		if(qty==0) {
			return this.cartMapper.delCart(cartVo.getCartNum());
		}else if(qty<0) {
			throw new NumberFormatException("수량은 음수로 입력하면 안됩니다");
		}else if(qty>50) {
			throw new NumberFormatException("수량은 50개 이내로만 수정 가능해요");
		}else {//유효한 수량값이라면
			return this.cartMapper.editCart(cartVo);
		}
		
	}//-----------------------------

	@Override
	public List<CartVO> selectCartView(String userid) {
		
		return this.cartMapper.selectCartView(userid);
	}

	@Override
	public int delCart(int cartNum) {
		return this.cartMapper.delCart(cartNum);
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		
		return this.cartMapper.delCartAll(cartVo.getUserid_fk());
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(String userid) {
		return this.cartMapper.getCartTotal(userid);
	}

	@Override
	public void delCartByOrder(String userid, int pnum) {
		// TODO Auto-generated method stub
		
	}

}
