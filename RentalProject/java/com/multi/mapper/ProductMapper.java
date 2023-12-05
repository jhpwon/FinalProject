package com.multi.mapper;

import java.util.List;

import com.multi.model.CategoryVO;
import com.multi.model.ProductVO;

//mybatis-scan�� ��Ű�� ����ϸ� �������� ProductMapper�� ������ Ŭ������ ��� �����. XXXProxy��ü
//��� ProductMapper.xml������ ���ӽ����̽��� "��Ű����.Mapper�������̽���"���� ����ؾ� �ϰ�
// �� ������Ʈ�� id�� XXXMapper�� �߻�޼����� ���ƾ� �Ѵ� 
public interface ProductMapper {

	// ��ǰ���
	int productInsert(ProductVO item);

	// ��ǰ��� ��������
	List<ProductVO> productList();

	// ���� ī�װ� ��� ��������
	List<CategoryVO> getUpCategory();

	// ���� ī�װ��� �ش��ϴ� ���� ī�װ� ��� ��������
	List<CategoryVO> getDownCategory(int upCg_code);

	public ProductVO getProduct(int pnum);

	public int productUpdate(ProductVO prod);

	public int productDelete(int pnum);

	List<ProductVO> selectByPspec(String pspec);

	List<ProductVO> selectByCategory(int cg_num);

}
