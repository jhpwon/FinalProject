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
		//1. �߰��� ��ǰ�� �̹� ��ٱ��Ͽ� ����ִ� ���==> ������ ���� ==>update��
		//2. �߰��� ��ǰ�� ��ٱ��Ͽ� ���� ��� ===> insert��
		//------------------------------------------------------
		// [1] userid_fk�� pnum_fk�� cart���̺� �ִ� ��ٱ��Ϲ�ȣ ��������
		Integer cnt=cartMapper.selectCartCountByPnum(cartVo);
		System.out.println("cnt:  "+cnt);
		//[1_1] ��ٱ��Ϲ�ȣ�� �ִٸ� => �̹� ��� ��ǰ => ������ ����
		if(cnt!=null) {
			int n=cartMapper.updateCartQty(cartVo);
			return n;
		}else {//cnt null �� ���
		
			//[1_2] ������ = 0 => insert�� ����
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
		//������ ���� ���� ó��
		//������ 0 ==> ����ó��
		//������ ����==> ���� ó��
		//������ ���==> ����ó��
		int qty=cartVo.getPqty();
		if(qty==0) {
			return this.cartMapper.delCart(cartVo.getCartNum());
		}else if(qty<0) {
			throw new NumberFormatException("������ ������ �Է��ϸ� �ȵ˴ϴ�");
		}else if(qty>50) {
			throw new NumberFormatException("������ 50�� �̳��θ� ���� �����ؿ�");
		}else {//��ȿ�� �������̶��
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
