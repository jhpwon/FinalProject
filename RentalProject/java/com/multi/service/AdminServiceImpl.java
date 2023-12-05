package com.multi.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.mapper.ProductMapper;
import com.multi.model.CategoryVO;
import com.multi.model.ProductVO;
//서비스 계층: @Service
//servlet-context.xml component-scan 에 패키지 등록해야 함
@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	
	@Inject
	private ProductMapper prodMapper;

	@Override
	public List<CategoryVO> getUpcategory() {
		return prodMapper.getUpCategory();
	}
	@Override
	public List<CategoryVO> getDowncategory(int upCg_code) {
		return null;
	}
	@Override
	public int categoryAdd(CategoryVO cvo) {
		return 0;
	}
	@Override
	public int categoryDelete(int cg_code) {
		return 0;
	}

	@Override
	public int productInsert(ProductVO prod) {
		System.out.println("prodMapper: "+prodMapper);
		return prodMapper.productInsert(prod);
	}

	@Override
	public List<ProductVO> productList() {

		return prodMapper.productList();
	}
	@Override
	public ProductVO getProduct(int pnum) {
		return prodMapper.getProduct(pnum);
	}
	@Override
	public int productUpdate(ProductVO prod) {
		return prodMapper.productUpdate(prod);
	}
	@Override
	public int productDelete(int pnum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
