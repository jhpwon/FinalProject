package com.multi.mapper;

import java.util.List;

import com.multi.model.CategoryVO;
import com.multi.model.ProductVO;

//mybatis-scan에 패키지 등록하면 스프링이 ProductMapper를 구현한 클래스를 대신 만든다. XXXProxy객체
//대신 ProductMapper.xml에서는 네임스페이스를 "패키지명.Mapper인터페이스명"으로 기술해야 하고
// 각 엘리먼트의 id는 XXXMapper의 추상메서드명과 같아야 한다 
public interface ProductMapper {

	// 상품등록
	int productInsert(ProductVO item);

	// 상품목록 가져오기
	List<ProductVO> productList();

	// 상위 카테고리 목록 가져오기
	List<CategoryVO> getUpCategory();

	// 상위 카테고리에 해당하는 하위 카테고리 목록 가져오기
	List<CategoryVO> getDownCategory(int upCg_code);

	public ProductVO getProduct(int pnum);

	public int productUpdate(ProductVO prod);

	public int productDelete(int pnum);

	List<ProductVO> selectByPspec(String pspec);

	List<ProductVO> selectByCategory(int cg_num);

}
