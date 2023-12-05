package com.multi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class CartVO {
	
	private int cartNum;//��ٱ��Ϲ�ȣ
	private String userid_fk;//ȸ�����̵�
	private int pnum_fk;//��ǰ��ȣ
	private int pqty;//����
	private Date indate;//������¥
	
	//��ٱ��� ��ǰ����
	private String pname;
	private String pimage1;
	private int price;
	private int saleprice;
	private int point;
	
	private int totalPrice;//saleprice * pqty =>������ǰ�� �ѱݾ�
	private int totalPoint;//point * pqty => ������ǰ�� �� ��������Ʈ
	
	private int cartTotalPrice;//��ٱ��Ͽ� ���� ��� ��ǰ�� �ѱݾ�
	private int cartTotalPoint;
}////////////////////////////
