package com.multi.service;

import java.util.List;

import com.multi.model.CategoryVO;
import com.multi.model.ProductVO;

public interface AdminService {

	public List<CategoryVO> getUpcategory();

	public List<CategoryVO> getDowncategory(int upCg_code);

	public int categoryAdd(CategoryVO cvo);

	public int categoryDelete(int cg_code);

	/** [관리자 모드]- 상품 정보 등록하기 */
	public int productInsert(ProductVO prod);

	public List<ProductVO> productList();

	public ProductVO getProduct(int pnum);
	
	public int productUpdate(ProductVO prod);
	
	public int productDelete(int pnum);

}
